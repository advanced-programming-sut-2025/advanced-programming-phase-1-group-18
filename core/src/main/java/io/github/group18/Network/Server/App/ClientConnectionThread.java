package io.github.group18.Network.Server.App;

import io.github.group18.Network.Server.Controllers.ServerConnectionController;
import io.github.group18.Network.common.models.ConnectionThread;
import io.github.group18.Network.common.models.Message;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;

import static io.github.group18.Network.Server.App.ServerApp.TIMEOUT_MILLIS;

public class ClientConnectionThread extends ConnectionThread {
    private HashMap<String, String> fileAndHashes;

    public ClientConnectionThread(Socket socket) throws IOException {
        super(socket);
    }

    @Override
    public boolean initialHandshake() {
        try {
            // TODO: Implement initial handshake
            refreshStatus();
//            refreshFileList();
            ServerApp.addClientConnection(this);
            return true;
            // Refresh peer status (IP and port), Get peer's file list, Add connection to tracker's connection list
//			throw new UnsupportedOperationException("Initial handshake not implemented yet");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void refreshStatus() {
        // TODO: Implement status refresh
        // Send status command and update peer's IP and port and wait for response
        // then update peer's IP and port
        HashMap<String, Object> body = new HashMap<>();
        body.put("command", "status");
        Message res = sendAndWaitForResponse(new Message(body, Message.Type.command), TIMEOUT_MILLIS);
        assert res != null;
//		throw new UnsupportedOperationException("Status refresh not implemented yet");
    }
//
//    public void refreshFileList() {
//        // TODO: Implement file list refresh
//        HashMap<String, Object> body = new HashMap<>();
//        body.put("command", "get_files_list");
//        Message res = sendAndWaitForResponse(new Message(body, Message.Type.command), TIMEOUT_MILLIS);
//        assert res != null;
//        fileAndHashes = new HashMap<>(res.getFromBody("files"));
//        // Request and update peer's file list
////		throw new UnsupportedOperationException("File list refresh not implemented yet");
//    }

    @Override
    protected boolean handleMessage(Message message) {
        if (message.getType() == Message.Type.Is_Unique) {
            sendMessage(ServerConnectionController.handleCommand(message));
            return true;
        } else if (message.getType()!= null) {
            sendMessage(ServerConnectionController.handleCommand(message));
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        super.run();
        ServerApp.removeClientConnection(this);
    }

//    public Map<String, String> getFileAndHashes() {
//        return Map.copyOf(fileAndHashes);
//    }
}
