package io.github.group18.Network.Client.App;

import io.github.group18.Model.Player;

import java.net.Socket;

public class ClientModel {
    public static final int TIMEOUT_MILLIS = 500;

    public static String SERVER_HOST = "127.0.0.1"; // آدرس IP ترکر
    public static int SERVER_PORT = 12345;          // پورت ترکر (مثلاً 8080)
    public static ServerConnectionThread SERVER_CONNECTION_THREAD;
    public static Socket trackerSocket;

    private static Player player;
    private static boolean windowOpen = false;

    public static final int player1TopLeftx = 0;
    public static final int player1TopLefty = 0;
    public static final int player1Width = 450;
    public static final int player1Height = 200;

    public static final int player2TopLeftx = 550;
    public static final int player2TopLefty = 0;
    public static final int player2Width = 449;
    public static final int player2Height = 200;

    public static final int player3TopLeftx = 0;
    public static final int player3TopLefty = 360;
    public static final int player3Width = 450;
    public static final int player3Height = 200;

    public static final int player4TopLeftx = 550;
    public static final int player4TopLefty = 360;
    public static final int player4Width = 449;
    public static final int player4Height = 200;

    public static final int BlackSmithTopLeftx = 75;
    public static final int BlackSmithTopLefty = 230;
    public static final int BlackSmithWidth = 5;
    public static final int BlackSmithHeight = 5;
    public static final int BlackSmithEnterancex = 78;
    public static final int BlackSmithEnterancey = 232;

    public static final int JojoMartTopLeftx = 200;
    public static final int JojoMartTopLefty = 230;
    public static final int JojoMartWidth = 5;
    public static final int JojoMartHeight = 5;
    public static final int JojoMartEnterancex = 202;
    public static final int JojoMartEnterancey = 232;

    public static final int PierresGeneralStoreTopLeftx = 325;
    public static final int PierresGeneralStoreTopLefty = 230;
    public static final int PierresGeneralStoreWidth = 5;
    public static final int PierresGeneralStoreHeight = 5;
    public static final int PierresGeneralStoreEnterancex = 327;
    public static final int PierresGeneralStoreEnterancey = 232;

    public static final int CarpentersShopTopLeftx = 450;
    public static final int CarpentersShopTopLefty = 230;
    public static final int CarpentersShopWidth = 5;
    public static final int CarpentersShopHeight = 5;
    public static final int CarpentersShopEnterancex = 452;
    public static final int CarpentersShopEnterancey = 232;

    public static final int FishShopTopLeftx = 575;
    public static final int FishShopTopLefty = 230;
    public static final int FishShopWidth = 5;
    public static final int FishShopHeight = 5;
    public static final int FishShopEnterancex = 577;
    public static final int FishShopEnterancey = 232;

    public static final int MarniesRanchTopLeftx = 700;
    public static final int MarniesRanchTopLefty = 230;
    public static final int MarniesRanchWidth = 5;
    public static final int MarniesRanchHeight = 5;
    public static final int MarniesRanchEnterancex = 702;
    public static final int MarniesRanchEnterancey = 232;

    public static final int TheStardropSaloonTopLeftx = 825;
    public static final int TheStardropSaloonTopLefty = 230;
    public static final int TheStardropSaloonWidth = 5;
    public static final int TheStardropSaloonHeight = 5;
    public static final int TheStardropSaloonEnterancex = 827;
    public static final int TheStardropSaloonEnterancey = 232;

    // Sebastian
    public static final int NPCSEBASTIANTopLeftX = 480;
    public static final int NPCSEBASTIANTopLeftY = 100;

    // Abigail
    public static final int NPCABIGAILTopLeftX = 500;
    public static final int NPCABIGAILTopLeftY = 100;

    // Harvey
    public static final int NPCHARVEYTopLeftX = 520;
    public static final int NPCHARVEYTopLeftY = 100;

    // Leah
    public static final int NPCLEAHTopLeftX = 480;
    public static final int NPCLEAHTopLeftY = 460;

    // Robin
    public static final int NPCROBINTopLeftX = 520;
    public static final int NPCROBINTopLeftY = 460;

    public static final int NPCSEBASTIANx = 480;
    public static final int NPCSEBASTIANy = 100;
    public static final int NPCABIGAILx = 500;
    public static final int NPCABIGAILy = 100;
    public static final int NPCHARVEYx = 520;
    public static final int NPCHARVEYy = 100;
    public static final int NPCLEAHx = 480;
    public static final int NPCLEAHy = 460;
    public static final int NPCROBINx = 520;
    public static final int NPCROBINy = 460;

    public static final int mapWidth = 1000;
    public static final int mapHeight = 560;
    public static final int TILE_SIZE = 50;

    private static final float cameraLerpSpeed = 8f;
    private static final int lookAheadTiles = 4;

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
            ServerConnectionThread.currentThread().interrupt();
            // 2. End all torrent threads
            System.exit(0);
            // 3. Clear file lists
        } catch (Exception e) {
            throw new UnsupportedOperationException("Cleanup not implemented yet");
        }
    }

    public static void connectServer() {
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

    public static Socket getTrackerSocket() {
        return trackerSocket;
    }

    public static void setTrackerSocket(Socket trackerSocket) {
        ClientModel.trackerSocket = trackerSocket;
    }

    public static int getPlayer1TopLeftx() {
        return player1TopLeftx;
    }

    public static int getPlayer1TopLefty() {
        return player1TopLefty;
    }


    public static int getPlayer1Width() {
        return player1Width;
    }


    public static int getPlayer1Height() {
        return player1Height;
    }


    public static int getPlayer2TopLeftx() {
        return player2TopLeftx;
    }


    public static int getPlayer2TopLefty() {
        return player2TopLefty;
    }


    public static int getPlayer2Width() {
        return player2Width;
    }


    public static int getPlayer2Height() {
        return player2Height;
    }


    public static int getPlayer3TopLeftx() {
        return player3TopLeftx;
    }


    public static int getPlayer3TopLefty() {
        return player3TopLefty;
    }


    public static int getPlayer3Width() {
        return player3Width;
    }


    public static int getPlayer3Height() {
        return player3Height;
    }


    public static int getPlayer4TopLeftx() {
        return player4TopLeftx;
    }


    public static int getPlayer4TopLefty() {
        return player4TopLefty;
    }


    public static int getPlayer4Width() {
        return player4Width;
    }


    public static int getPlayer4Height() {
        return player4Height;
    }


    public static int getBlackSmithTopLeftx() {
        return BlackSmithTopLeftx;
    }

    public static int getBlackSmithTopLefty() {
        return BlackSmithTopLefty;
    }

    public static int getBlackSmithWidth() {
        return BlackSmithWidth;
    }


    public static int getBlackSmithHeight() {
        return BlackSmithHeight;
    }


    public static int getBlackSmithEnterancex() {
        return BlackSmithEnterancex;
    }


    public static int getBlackSmithEnterancey() {
        return BlackSmithEnterancey;
    }


    public static int getJojoMartTopLeftx() {
        return JojoMartTopLeftx;
    }


    public static int getJojoMartTopLefty() {
        return JojoMartTopLefty;
    }


    public static int getJojoMartWidth() {
        return JojoMartWidth;
    }


    public static int getJojoMartHeight() {
        return JojoMartHeight;
    }


    public static int getJojoMartEnterancex() {
        return JojoMartEnterancex;
    }


    public static int getJojoMartEnterancey() {
        return JojoMartEnterancey;
    }


    public static int getPierresGeneralStoreTopLeftx() {
        return PierresGeneralStoreTopLeftx;
    }


    public static int getPierresGeneralStoreTopLefty() {
        return PierresGeneralStoreTopLefty;
    }


    public static int getPierresGeneralStoreWidth() {
        return PierresGeneralStoreWidth;
    }


    public static int getPierresGeneralStoreHeight() {
        return PierresGeneralStoreHeight;
    }


    public static int getPierresGeneralStoreEnterancex() {
        return PierresGeneralStoreEnterancex;
    }


    public static int getPierresGeneralStoreEnterancey() {
        return PierresGeneralStoreEnterancey;
    }


    public static int getCarpentersShopTopLeftx() {
        return CarpentersShopTopLeftx;
    }


    public static int getCarpentersShopTopLefty() {
        return CarpentersShopTopLefty;
    }


    public static int getCarpentersShopWidth() {
        return CarpentersShopWidth;
    }


    public static int getCarpentersShopHeight() {
        return CarpentersShopHeight;
    }


    public static int getCarpentersShopEnterancex() {
        return CarpentersShopEnterancex;
    }


    public static int getCarpentersShopEnterancey() {
        return CarpentersShopEnterancey;
    }


    public static int getFishShopTopLeftx() {
        return FishShopTopLeftx;
    }


    public static int getFishShopTopLefty() {
        return FishShopTopLefty;
    }


    public static int getFishShopWidth() {
        return FishShopWidth;
    }


    public static int getFishShopHeight() {
        return FishShopHeight;
    }


    public static int getFishShopEnterancex() {
        return FishShopEnterancex;
    }


    public static int getFishShopEnterancey() {
        return FishShopEnterancey;
    }


    public static int getMarniesRanchTopLeftx() {
        return MarniesRanchTopLeftx;
    }


    public static int getMarniesRanchTopLefty() {
        return MarniesRanchTopLefty;
    }


    public static int getMarniesRanchWidth() {
        return MarniesRanchWidth;
    }

    public static int getMarniesRanchHeight() {
        return MarniesRanchHeight;
    }

    public static int getMarniesRanchEnterancex() {
        return MarniesRanchEnterancex;
    }

    public static int getMarniesRanchEnterancey() {
        return MarniesRanchEnterancey;
    }

    public static int getTheStardropSaloonTopLeftx() {
        return TheStardropSaloonTopLeftx;
    }


    public static int getTheStardropSaloonTopLefty() {
        return TheStardropSaloonTopLefty;
    }


    public static int getTheStardropSaloonWidth() {
        return TheStardropSaloonWidth;
    }


    public static int getTheStardropSaloonHeight() {
        return TheStardropSaloonHeight;
    }

    public static int getTheStardropSaloonEnterancex() {
        return TheStardropSaloonEnterancex;
    }



    public static int getTheStardropSaloonEnterancey() {
        return TheStardropSaloonEnterancey;
    }



    public static int getNPCSEBASTIANTopLeftX() {
        return NPCSEBASTIANTopLeftX;
    }



    public static int getNPCSEBASTIANTopLeftY() {
        return NPCSEBASTIANTopLeftY;
    }



    public static int getNPCABIGAILTopLeftX() {
        return NPCABIGAILTopLeftX;
    }



    public static int getNPCABIGAILTopLeftY() {
        return NPCABIGAILTopLeftY;
    }



    public static int getNPCHARVEYTopLeftX() {
        return NPCHARVEYTopLeftX;
    }



    public static int getNPCHARVEYTopLeftY() {
        return NPCHARVEYTopLeftY;
    }



    public static int getNPCLEAHTopLeftX() {
        return NPCLEAHTopLeftX;
    }



    public static int getNPCLEAHTopLeftY() {
        return NPCLEAHTopLeftY;
    }


    public static int getNPCROBINTopLeftX() {
        return NPCROBINTopLeftX;
    }

    public static int getNPCROBINTopLeftY() {
        return NPCROBINTopLeftY;
    }


    public static int getNPCSEBASTIANx() {
        return NPCSEBASTIANx;
    }


    public static int getNPCSEBASTIANy() {
        return NPCSEBASTIANy;
    }


    public static int getNPCABIGAILx() {
        return NPCABIGAILx;
    }

    public static int getNPCABIGAILy() {
        return NPCABIGAILy;
    }


    public static int getNPCHARVEYx() {
        return NPCHARVEYx;
    }


    public static int getNPCHARVEYy() {
        return NPCHARVEYy;
    }

    public static int getNPCLEAHx() {
        return NPCLEAHx;
    }

    public static int getNPCLEAHy() {
        return NPCLEAHy;
    }

    public static int getNPCROBINx() {
        return NPCROBINx;
    }

    public static int getNPCROBINy() {
        return NPCROBINy;
    }

    public static boolean isExitFlag() {
        return exitFlag;
    }

    public static void setExitFlag(boolean exitFlag) {
        ClientModel.exitFlag = exitFlag;
    }

    public static Player getPlayer() {
        return player;
    }

    public static void setPlayer(Player player) {
        ClientModel.player = player;
    }

    public static boolean isWindowOpen() {
        return windowOpen;
    }

    public static void setWindowOpen(boolean windowOpen) {
        ClientModel.windowOpen = windowOpen;
    }
}
