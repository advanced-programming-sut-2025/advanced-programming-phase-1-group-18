package io.github.group18.Network.Client.Controller;

import io.github.group18.Network.common.models.Message;

import java.io.File;
import java.util.HashMap;

public class C2SConnectionController {
    public static Message handleCommand(Message message) {

        Message response = new Message();
        try {
            // 1. Parse command from message
            String commandName = message.getFromBody("command");

            // 2. Call appropriate handler
            response = switch (commandName.toLowerCase()) {
                case "status" -> status();
//                case "get_files_list" -> getFilesList();
//                case "get_sends" -> getSends();
//                case "get_receives" -> getReceives();
                default -> response;
            };
        } catch (Exception e) {
            throw new UnsupportedOperationException("handleCommand not implemented yet");
        }
        return response;
    }

//    private static Message getReceives() {
//        // TODO: Return information about received files
//        /*
//         * creating body of message for
//         * get receives
//         */
//        HashMap<String, Object> body = new HashMap<>();
//        body.put("response", "ok");//response
//        body.put("command", "get_receives");//command
//        body.put("received_files", PeerApp.getReceivedFiles());//received files
//        return new Message(body, Message.Type.response);
////		throw new UnsupportedOperationException("getReceives not implemented yet");
//    }
//
//    private static Message getSends() {
//        // TODO: Return information about sent files
//        HashMap<String, Object> body = new HashMap<>();
//        body.put("command", "get_sends");
//        body.put("response", "ok");
//        body.put("sent_files", PeerApp.getSentFiles());
//        return new Message(body, Message.Type.response);

    /// /		throw new UnsupportedOperationException("getSends not implemented yet");
//    }
//
//    public static Message getFilesList() {
//        // TODO: Return list of files in shared folder
//        try {
//            HashMap<String, Object> body = new HashMap<>();
//            body.put("command", "get_files_list");
//            body.put("response", "ok");
//
//            HashMap<String, String> files = new HashMap<>(); // این Map برای نگهداری فایل‌ها و هش‌هاشونه
//
//            // حالا، پوشه اشتراکی رو اسکن می‌کنیم
//            if (PeerApp.SHARED_FOLDER_PATH != null) {
//                File sharedFolder = PeerApp.SHARED_FOLDER_PATH.toFile();
//                if (sharedFolder.exists() && sharedFolder.isDirectory()) {
//                    File[] listOfFiles = sharedFolder.listFiles();
//                    if (listOfFiles != null) {
//                        for (File file : listOfFiles) {
//                            if (file.isFile()) {
//                                String fileName = file.getName();
//                                String filePath = file.getAbsolutePath();
//
//                                String md5Hash = MD5Hash.HashFile(filePath);
//
//                                if (md5Hash != null) {
//                                    files.put(fileName, md5Hash);
//                                } else {
//                                    System.err.println("Warning: Could not calculate MD5 hash for file: " + fileName);
//                                }
//                            }
//                        }
//                    }
//                } else {
//                    System.err.println("Error: Shared folder does not exist or is not a directory: " +
//                        PeerApp.SHARED_FOLDER_PATH);
//                    body.put("response", "error");
//                    body.put("error_message", "Shared folder invalid.");
//                }
//            } else {
//                System.err.println("Error: SHARED_FOLDER_PATH is not initialized.");
//                body.put("response", "error");
//                body.put("error_message", "Shared folder path not set.");
//            }
//
//            body.put("files", files); // Map فایل‌ها و هش‌ها رو به body اضافه می‌کنیم
//
//            // ساخت پیام نهایی با type "response"
//            Message res = new Message(body, Message.Type.response);
//            return res;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new UnsupportedOperationException("getFilesList not implemented yet");
//        }
//    }
    public static Message status() {
        // TODO: Return peer status information
        try {
            HashMap<String, Object> body = new HashMap<>();
            body.put("command", "status");
            body.put("response", "ok");
            Message res = new Message(body, Message.Type.response, Message.Menu.Basic);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            throw new UnsupportedOperationException("status not implemented yet");
        }
    }
}

//    public static Message sendFileRequest(P2TConnectionThread tracker, String fileName) throws Exception {
//        // TODO: Send file request to tracker and handle response
//        try {
//            HashMap<String, Object> body = new HashMap<>();
//            body.put("name", fileName);
//            Message res = new Message(body, Message.Type.file_request);
//            return tracker.sendAndWaitForResponse(res, TIMEOUT_MILLIS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        // 1. Build request message
//        // 2. Send message and wait for response
//        // 3. raise exception if error or return response
//        return null;
//}
