package io.github.group18.Network.common.models;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class ConnectionThread extends Thread {

    protected final ObjectInputStream objectInputStream;
    protected final ObjectOutputStream objectOutputStream;

    protected final BlockingQueue<Message> receivedMessagesQueue;
    protected String otherSideIP;
    protected int otherSidePort;
    protected Socket socket;
    protected AtomicBoolean end;
    protected boolean initialized = false;

    protected ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectOutputStream.flush();
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
    }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
        sendMessage(message);
        try {
            if (initialized) return receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
            socket.setSoTimeout(timeoutMilli);
            Object obj = objectInputStream.readObject();
            socket.setSoTimeout(0);
            if (obj instanceof Message) {
                return (Message) obj;
            } else {
                System.err.println("Received object is not a Message");
                return null;
            }
        } catch (Exception e) {
            System.err.println("Request Timed out or error: " + e.getMessage());
            return null;
        }
    }

    abstract public boolean initialHandshake();

    abstract protected boolean handleMessage(Message message);

    public synchronized void sendMessage(Message message) {
        try {
            objectOutputStream.writeObject(message);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        initialized = false;
        if (!initialHandshake()) {
            System.err.println("Initial HandShake failed with remote device.");
            end();
            return;
        }

        initialized = true;
        while (!end.get()) {
            try {
                Object obj = objectInputStream.readObject();
                if (!(obj instanceof Message)) {
                    System.err.println("Received unknown object type.");
                    continue;
                }
                Message message = (Message) obj;
                boolean handled = handleMessage(message);
                if (!handled) {
                    try {
                        receivedMessagesQueue.put(message);
                    } catch (InterruptedException e) {
                        // ignore
                    }
                }
            } catch (Exception e) {
                break;
            }
        }
        end();
    }

    public String getOtherSideIP() {
        return otherSideIP;
    }

    public void setOtherSideIP(String otherSideIP) {
        this.otherSideIP = otherSideIP;
    }

    public int getOtherSidePort() {
        return otherSidePort;
    }

    public void setOtherSidePort(int otherSidePort) {
        this.otherSidePort = otherSidePort;
    }

    public void end() {
        end.set(true);
        try {
            socket.close();
        } catch (IOException e) {}
    }
}
