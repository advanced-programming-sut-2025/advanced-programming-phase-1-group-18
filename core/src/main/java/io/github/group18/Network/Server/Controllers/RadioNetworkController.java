package io.github.group18.Network.Server.Controllers;

import io.github.group18.Network.Server.App.ServerModel;
import io.github.group18.Network.common.models.Message;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RadioNetworkController {

    private static Map<String, byte[][]> chunkBuffers = new ConcurrentHashMap<>();
    private static Map<String, Integer> chunkCounts = new ConcurrentHashMap<>();

    public static Message handleMessage(Message message) {
        System.out.println("voiabkomw9 umef9uer9 u g9rmg uu8g9y");
        switch (message.getType()) {
            case RadioUploadChunk:
                return handleRadioUploadChunk(message);
            default:
                return null;
        }
    }

    private static Message handleRadioUploadChunk(Message message) {
        System.out.println("kir be shabake");
        String filename = message.getFromBody("filename");
        int chunkIndex =  Integer.parseInt(message.getFromBody("chunkIndex"));
        int totalChunks = (int) ((double) message.getFromBody("totalChunks"));
        String chunkData = message.getFromBody("chunkData");

        byte[] chunkBytes = java.util.Base64.getDecoder().decode(chunkData);


        chunkBuffers.putIfAbsent(filename, new byte[totalChunks][]);
        chunkCounts.putIfAbsent(filename, 0);

        byte[][] chunks = chunkBuffers.get(filename);


        if (chunks[chunkIndex] == null) {
            chunks[chunkIndex] = chunkBytes;
            chunkCounts.put(filename, chunkCounts.get(filename) + 1);
        }

        System.out.println("Received chunk " + chunkIndex + " of " + totalChunks + " for file " + filename);
        System.out.println("Current count: " + chunkCounts.get(filename));

        if (chunkCounts.get(filename) == totalChunks) {
            try {
                java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
                for (byte[] c : chunks) {
                    baos.write(c);
                }
                byte[] fullFileBytes = baos.toByteArray();


                String fullEncoded = java.util.Base64.getEncoder().encodeToString(fullFileBytes);


                int chunkSize = 30000;
                int totalChunksToSend = (int) Math.ceil((double) fullEncoded.length() / chunkSize);

                for (int i = 0; i < totalChunksToSend; i++) {
                    int start = i * chunkSize;
                    int end = Math.min(start + chunkSize, fullEncoded.length());
                    String chunkDataToSend = fullEncoded.substring(start, end);

                    HashMap<String, Object> bodyChunk = new HashMap<>();
                    bodyChunk.put("filename", filename);
                    bodyChunk.put("chunkIndex", i);
                    bodyChunk.put("totalChunks", totalChunksToSend);
                    bodyChunk.put("chunkData", chunkDataToSend);

                    Message chunkMsg = new Message(bodyChunk, Message.Type.RadioBroadcast, Message.Menu.Radio);
                    ServerModel.broadcast(chunkMsg);
                }

                System.out.println("Broadcasted file in " + totalChunksToSend + " chunks: " + filename);


                chunkBuffers.remove(filename);
                chunkCounts.remove(filename);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        HashMap<String, Object> ack = new HashMap<>();
        ack.put("status", "chunk received");
        ack.put("chunkIndex", chunkIndex);
        ack.put("totalChunks", totalChunks);

        return new Message(ack, Message.Type.RadioUploadChunk, Message.Menu.Radio);
    }
}
