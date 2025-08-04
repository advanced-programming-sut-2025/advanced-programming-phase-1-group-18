package io.github.group18.Network.common.models;


import io.github.group18.Network.common.utils.JSONUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

abstract public class ConnectionThread extends Thread {
    public DataInputStream dataInputStream;
    public DataOutputStream dataOutputStream;
    public final BlockingQueue<Message> receivedMessagesQueue;
    public String otherSideIP;
    public int otherSidePort;
    public Socket socket;
    public AtomicBoolean end;
    public boolean initialized = false;

    public ConnectionThread(Socket socket) throws IOException {
        this.socket = socket;
        this.dataInputStream = new DataInputStream(socket.getInputStream());
        this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
        this.receivedMessagesQueue = new LinkedBlockingQueue<>();
        this.end = new AtomicBoolean(false);
    }

    public Message sendAndWaitForResponse(Message message, int timeoutMilli) {
//        System.out.println("2(in sendAndWaitForResponse)");
        sendMessage(message);
        try {
//            System.out.println("2.2(we have sent the message)");
            if (initialized) {
                Message msg = receivedMessagesQueue.poll(timeoutMilli, TimeUnit.MILLISECONDS);
//                System.out.println("2.3(checking the message)" + msg.getBody().toString());
                return msg;
            }
//            System.out.println("2.5(not initialized)");
            socket.setSoTimeout(timeoutMilli);
//            System.out.println("3");
            var result = JSONUtils.fromJson(dataInputStream.readUTF());
//            System.out.println("4");
            socket.setSoTimeout(0);
            return result;
        } catch (Exception e) {
//            System.out.println("5");
            System.err.println("Request Timed out.");
            return null;
        }
    }

    abstract public boolean initialHandshake();

    abstract public boolean handleMessage(Message message);

    public synchronized void sendMessage(Message message) {
        try {
            String JSONString = JSONUtils.toJson(message);

            try {
                dataOutputStream.writeUTF(JSONString);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public synchronized void readMessage() {
//        try {
//            dataInputStream.readUTF();
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("Read message rid");
//        }
//    }
//
//    public synchronized void clearSockets()
//    {
//        try {
//            receivedMessagesQueue.clear();
//            this.dataInputStream = new DataInputStream(socket.getInputStream());
//            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @Override
    public void run() {
        initialized = false;
        if (!initialHandshake()) {
            System.err.println("Inital HandShake failed with remote device.");
            end();
            return;
        }

        initialized = true;
        while (!end.get()) {
            try {
                String receivedStr = dataInputStream.readUTF();
                Message message = JSONUtils.fromJson(receivedStr);
//                System.out.println("(someone has send a message to server and now server is checking the msg)");
                boolean handled = handleMessage(message);
//                System.out.println("(handled)" + handled);
                if (!handled) try {
                    receivedMessagesQueue.put(message);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
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
        } catch (IOException e) {
        }
    }
}
