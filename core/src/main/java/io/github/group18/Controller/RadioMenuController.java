package io.github.group18.Controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.Network.common.models.Message;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.*;

public class RadioMenuController {

    private static Music currentMusic;
    public static File latestReceivedFile = null;

    public static void uploadMusicFileChunked(File file) {
        try {
            byte[] fileBytes = java.nio.file.Files.readAllBytes(file.toPath());
            int chunkSize = 32 * 1024;
            int totalChunks = (fileBytes.length + chunkSize - 1) / chunkSize;

            for (int i = 0; i < totalChunks; i++) {
                int start = i * chunkSize;
                int end = Math.min(fileBytes.length, (i + 1) * chunkSize);
                byte[] chunk = java.util.Arrays.copyOfRange(fileBytes, start, end);
                String encodedChunk = java.util.Base64.getEncoder().encodeToString(chunk);
                if (encodedChunk == null || encodedChunk.isEmpty()) {
                    System.out.println("Warning: encodedChunk is null or empty at chunk " + i);
                } else {

                    HashMap<String, Object> body = new HashMap<>();
                    body.put("filename", file.getName());
                    body.put("chunkIndex", String.valueOf(i));
                    body.put("totalChunks", totalChunks);
                    body.put("chunkData", encodedChunk);
                    System.out.println(body.toString());
                    System.out.println("ooooffffff");
                    Message message = new Message(body, Message.Type.RadioUploadChunk, Message.Menu.Radio);
                    ClientModel.getServerConnectionThread().sendMessage(message);
                    System.out.println("1");
                }


            }
            System.out.println("Music file chunked upload complete: " + file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Map<String, List<String>> chunkBuffer = new HashMap<>();
    private static Map<String, Integer> chunkCounts = new HashMap<>();

    public static void receiveAndPlayFullMusicFile(Message message)
    {
        System.out.println("son of a bitch");
        String filename = message.getFromBody("filename");
        String filedata = message.getFromBody("filedata");

        try {
            byte[] fileBytes = Base64.getDecoder().decode(filedata);
            File tempFile = new File(Gdx.files.getLocalStoragePath() + filename);
            try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                fos.write(fileBytes);
            }

            System.out.println("File exists: " + tempFile.exists() + ", path: " + tempFile.getAbsolutePath());
            latestReceivedFile = tempFile;
            System.out.println("🎵 uinniunu: " + filename);
            playMusic(tempFile);

        } catch (Exception e) {
            System.out.println("fuck");
            e.printStackTrace();
        }
    }


    public static void receiveAndStoreMusicChunk(Message message) {
        String filename = message.getFromBody("filename");
        Object chunkIndexObj = message.getFromBody("chunkIndex");
        Object totalChunksObj = message.getFromBody("totalChunks");

        System.out.println("Message body: " + message.getBody());

        if (chunkIndexObj == null || totalChunksObj == null) {
            System.out.println("uihiuihuiuijiu");
            return;
        }

        int chunkIndex, totalChunks;
        try {
            if (chunkIndexObj instanceof Double) {
                chunkIndex = ((Double) chunkIndexObj).intValue();
            } else if (chunkIndexObj instanceof Integer) {
                chunkIndex = (Integer) chunkIndexObj;
            } else {
                System.out.println("kos");
                return;
            }

            if (totalChunksObj instanceof Double) {
                totalChunks = ((Double) totalChunksObj).intValue();
            } else if (totalChunksObj instanceof Integer) {
                totalChunks = (Integer) totalChunksObj;
            } else {
                System.out.println("w0r9ig");
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String chunkData = message.getFromBody("chunkData");

        chunkBuffer.computeIfAbsent(filename, k -> new ArrayList<>());

        List<String> chunks = chunkBuffer.get(filename);

        while (chunks.size() < totalChunks) {
            chunks.add(null);
        }
        chunks.set(chunkIndex, chunkData);
        chunkCounts.put(filename, totalChunks);

        if (!chunks.contains(null)) {

            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                for (String chunk : chunks) {
                    byte[] decoded = Base64.getDecoder().decode(chunk);
                    baos.write(decoded);
                }
                byte[] fullFileBytes = baos.toByteArray();

                File tempFile = new File(Gdx.files.getLocalStoragePath() + filename);
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    fos.write(fullFileBytes);
                }

                latestReceivedFile = tempFile;
                System.out.println("Music file received and stored (chunked): " + filename);
                // playMusic(tempFile);


                chunkBuffer.remove(filename);
                chunkCounts.remove(filename);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public static void playMusic(File file) {
        try {
            if (currentMusic != null) {
                currentMusic.stop();
                currentMusic.dispose();
            }
            currentMusic = Gdx.audio.newMusic(Gdx.files.absolute(file.getAbsolutePath()));
            currentMusic.setLooping(true);
            currentMusic.play();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stopCurrentMusic() {
        if (currentMusic != null) {
            currentMusic.stop();
            currentMusic.dispose();
            currentMusic = null;
        }
    }


}
