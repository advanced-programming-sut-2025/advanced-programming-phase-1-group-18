package io.github.group18.Network.Client.App;

import java.net.Socket;

public class ClientModel {
    public static final int TIMEOUT_MILLIS = 500;

    public static String SERVER_HOST = "127.0.0.1"; // آدرس IP ترکر
    public static int SERVER_PORT = 12345;          // پورت ترکر (مثلاً 8080)
    public static ServerConnectionThread SERVER_CONNECTION_THREAD;
    public static Socket trackerSocket;

    private static boolean exitFlag = false;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void initFromArgs() throws Exception {

        try {

            SERVER_PORT = 12345;

            // 4. Create tracker connection thread
            trackerSocket = new Socket(SERVER_HOST, SERVER_PORT);
            SERVER_CONNECTION_THREAD = new ServerConnectionThread(trackerSocket);

            // 5. Create peer listener thread
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("Initialization not implemented yet");
        }
    }

    public static void endAll() {
        try {
            exitFlag = true;
            // TODO: Implement cleanup
            // 1. End tracker connection
            ServerConnectionThread.currentThread().interrupt();
            // 2. End all torrent threads
            System.exit(0);
            // 3. Clear file lists
        } catch (Exception e) {
            throw new UnsupportedOperationException("Cleanup not implemented yet");
        }
    }

    public static void connectServer() {
        // TODO: Start Server connection thread
        // Check if thread exists and not running, then Start thread
        try {
            if (SERVER_CONNECTION_THREAD != null && !SERVER_CONNECTION_THREAD.isAlive()) {
                SERVER_CONNECTION_THREAD.start();
                System.out.println("Connected to " + SERVER_HOST + ":" + SERVER_PORT);
            }
        } catch (Exception e) {
            System.out.println("Failed to connect to " + SERVER_HOST + ":" + SERVER_PORT);
            throw new UnsupportedOperationException("Server connection not implemented yet");
        }
    }

//    public static void startListening() {
//        // TODO: Start peer listener thread
//        try {
//            if (P2P_LISTENER_THREAD != null && !P2P_LISTENER_THREAD.isAlive()) {
//                P2P_LISTENER_THREAD.start();
//            }
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Peer listener thread not implemented yet");
//        }
//        // Check if thread exists and not running, then Start thread
//    }

//    public static void removeTorrentP2PThread(TorrentP2PThread torrentP2PThread) {
//        // TODO: Remove and cleanup torrent thread
//        try {
//            torrentP2PThread.interrupt();
//            ACTIVE_P2P_CONNECTIONS.remove(torrentP2PThread);
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Torrent P2P thread not implemented yet");
//        }
//    }
//
//    public static void addTorrentP2PThread(TorrentP2PThread torrentP2PThread) {
//        // TODO: Add new torrent thread
//        try {
//            // 1. Check if thread is valid
//            if (torrentP2PThread == null || TORRENT_P2P_THREAD.contains(torrentP2PThread)) {
//                return;
//            }
//            ACTIVE_P2P_CONNECTIONS.add(torrentP2PThread);
//            // 2. Check if already exists
//            // 3. Add to list
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Torrent P2P thread not implemented yet");
//        }
//    }
//
//    public static String getSharedFolderPath() {
//        // TODO: Get shared folder path'
//        try {
//            return SHARED_FOLDER_PATH.toString();
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Shared folder path not implemented yet");
//        }
//    }
//
//    public static void addSentFile(String receiver, String fileNameAndHash) {
//        // TODO: Add file to sent files list
////        receiver = receiver.substring(0, receiver.length() - 2);
//        try {
//            for (String receiverIpPort : SENT_FILES.keySet()) {
//                if (receiverIpPort.equals(receiver)) {
//                    SENT_FILES.get(receiverIpPort).add(fileNameAndHash);
//                    return;
//                }
//            }
//
//            SENT_FILES.put(receiver, new LinkedList<>(Collections.singletonList(fileNameAndHash)));
////            SENT_FILES.put(receiver, Collections.singletonList(fileNameAndHash));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnsupportedOperationException("Sent files not implemented yet");
//        }
//    }
//
//    public static void addReceivedFile(String sender, String fileNameAndHash) {
//        // TODO: Add file to received files list
//        try {
//            for (String receiverIpPort : RECEIVED_FILES.keySet()) {
//                if (receiverIpPort.equals(sender)) {
//                    RECEIVED_FILES.get(receiverIpPort).add(fileNameAndHash);
//                    return;
//                }
//            }
//            RECEIVED_FILES.put(sender, new LinkedList<>(Collections.singletonList(fileNameAndHash)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnsupportedOperationException("Received files not implemented yet");
//        }
//    }
//
//    public static String getPeerIP() {
//        // TODO: Get peer IP address
//        try {
//            return SELF_IP_ADDRESS;
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Peer IP not implemented yet");
//        }
//    }
//
//    public static int getPeerPort() {
//        // TODO: Get peer port
//        try {
//            return SELF_PORT;
//        } catch (Exception e) {
//            throw new UnsupportedOperationException("Peer port not implemented yet");
//        }
//    }
//
//    public static Map<String, List<String>> getSentFiles() {
//        // TODO: Get copy of sent files map
//        return Map.copyOf(SENT_FILES);
////		throw new UnsupportedOperationException("Sent files not implemented yet");
//    }
//
//    public static Map<String, List<String>> getReceivedFiles() {
//        // TODO: Get copy of received files map
//        return Map.copyOf(RECEIVED_FILES);
////		throw new UnsupportedOperationException("Received files not implemented yet");
//    }

//    public static ServerConnectionThread getP2TConnection() {
//        // TODO: Get Server connection thread
//        return SERVER_CONNECTION_THREAD;
////		throw new UnsupportedOperationException("Server connection not implemented yet");
//    }
//
//    public static void requestDownload(String ip, int port, String filename, String md5) throws Exception {
//        // TODO: Implement file download from peer
//        // 1. Check if file already exists
//        // مرحله ۱: بررسی وجود فایل قبلی
//
//        Message requestMsg = getRequestMsg(filename, md5);
//
//        // مرحله ۳: برقراری ارتباط با همتا
//        try (Socket peerSocket = new Socket()) {
//            peerSocket.connect(new InetSocketAddress(ip, port));
//
//            try (DataOutputStream dos = new DataOutputStream(peerSocket.getOutputStream());
//                 InputStream peerInput = peerSocket.getInputStream()) {
//
//                dos.writeUTF(JSONUtils.toJson(requestMsg));
//                dos.flush();
//
//                // مرحله ۴: دریافت و ذخیره فایل
//                Path destination = SHARED_FOLDER_PATH.resolve(filename);
//                Files.copy(peerInput, destination, StandardCopyOption.REPLACE_EXISTING);
//
//                String actualMd5 = MD5Hash.HashFile(destination.toString());
//
//                if (actualMd5 == null || !actualMd5.equals(md5)) {
//                    Files.deleteIfExists(destination);
//                    throw new IllegalStateException("The file has been downloaded from peer but is corrupted!");
//                }
//
//                // مرحله ۶: ثبت فایل دریافتی
//                PeerApp.addReceivedFile(ip + ":" + port, filename + " " + actualMd5);
//            }
//        }
//    }
//
//    private static Message getRequestMsg(String filename, String md5) {
//        File targetFile = new File(SHARED_FOLDER_PATH.toFile(), filename);
//        if (targetFile.exists()) {
//            throw new UnsupportedOperationException("You already have the file!");
//        }
//
//        // مرحله ۲: ساخت پیام درخواست
//        HashMap<String, Object> mess = new HashMap<>();
//        mess.put("name", filename);
//        mess.put("md5", md5);
//        mess.put("receiver_ip", PeerApp.SELF_IP_ADDRESS);
//        mess.put("receiver_port", getPeerPort());
//
//        Message requestMsg = new Message(mess, Message.Type.download_request);
//        return requestMsg;
//    }

    public static String getServerHost() {
        return SERVER_HOST;
    }

    public static void setServerHost(String serverHost) {
        SERVER_HOST = serverHost;
    }

    public static int getServerPort() {
        return SERVER_PORT;
    }

    public static void setServerPort(int serverPort) {
        SERVER_PORT = serverPort;
    }

    public static ServerConnectionThread getServerConnectionThread() {
        return SERVER_CONNECTION_THREAD;
    }

    public static void setServerConnectionThread(ServerConnectionThread serverConnectionThread) {
        SERVER_CONNECTION_THREAD = serverConnectionThread;
    }
}
