package io.github.group18.Network.Server.App;

import io.github.group18.Controller.GameController;
import io.github.group18.Model.*;
import io.github.group18.Network.Client.App.ClientModel;

import java.net.Socket;
import java.util.Random;

import static io.github.group18.Controller.GameMenuController.startNewDay;

public class GameThread extends Thread {


    @Override
    public void run() {
        long lastMoveTime = System.currentTimeMillis();

        long gameTime = System.currentTimeMillis();

        while (true) {

            long currentTime = System.currentTimeMillis();
            if (currentTime - gameTime >= 5000) {
                gameTime = System.currentTimeMillis();
                if (App.getCurrentGame().getCurrentDateTime().getHour() == 23) {
                    sendNPCbackToHome();
                    getBackHome();
                    App.getCurrentGame().setCurrentDateTime(new DateTime(9, App.getCurrentGame().getCurrentDateTime().getDay() + 1));

                } else {
                    App.getCurrentGame().setCurrentDateTime(new DateTime(App.getCurrentGame().getCurrentDateTime().getHour() + 1,
                        App.getCurrentGame().getCurrentDateTime().getDay()));
                }
            }


            if (App.getCurrentGame() != null) {
                if (App.getCurrentGame().getCurrentDateTime().getHour() >= 12 &&
                    App.getCurrentGame().getCurrentDateTime().getHour() <= 16) {

                    if (App.getCurrentGame().getNPCSEBASTIAN() != null && App.getCurrentGame().getNPCABIGAIL() != null &&
                        App.getCurrentGame().getNPCHARVEY() != null && App.getCurrentGame().getNPCLEAH() != null &&
                        App.getCurrentGame().getNPCROBIN() != null) {

                        App.getCurrentGame().getNPCSEBASTIAN().setX(ClientModel.NPCSEBASTIANworkx);
                        App.getCurrentGame().getNPCSEBASTIAN().setY(ClientModel.NPCSEBASTIANworky);

                        App.getCurrentGame().getNPCABIGAIL().setX(ClientModel.NPCABIGAILworkx);
                        App.getCurrentGame().getNPCABIGAIL().setY(ClientModel.NPCABIGAILworky);

                        App.getCurrentGame().getNPCHARVEY().setX(ClientModel.NPCHARVEYworkx);
                        App.getCurrentGame().getNPCHARVEY().setY(ClientModel.NPCHARVEYworky);

                        App.getCurrentGame().getNPCLEAH().setX(ClientModel.NPCLEAHworkx);
                        App.getCurrentGame().getNPCLEAH().setY(ClientModel.NPCLEAHworky);

                        App.getCurrentGame().getNPCROBIN().setX(ClientModel.NPCROBINworkx);
                        App.getCurrentGame().getNPCROBIN().setY(ClientModel.NPCROBINworky);
                    }

                } else {
                    long currentTime1 = System.currentTimeMillis();
                    if (currentTime1 - lastMoveTime >= 2000) {
                        lastMoveTime = currentTime1;

                        Random random = new Random();

                        if (App.getCurrentGame().getNPCSEBASTIAN() != null &&
                            App.getCurrentGame().getNPCABIGAIL() != null &&
                            App.getCurrentGame().getNPCHARVEY() != null &&
                            App.getCurrentGame().getNPCLEAH() != null &&
                            App.getCurrentGame().getNPCROBIN() != null) {

                            int dirSebastian = random.nextInt(4);
                            int dirAbigail = random.nextInt(4);
                            int dirHarvey = random.nextInt(4);
                            int dirLeah = random.nextInt(4);
                            int dirRobin = random.nextInt(4);

                            moveNPC(App.getCurrentGame().getNPCSEBASTIAN(), dirSebastian);
                            moveNPC(App.getCurrentGame().getNPCABIGAIL(), dirAbigail);
                            moveNPC(App.getCurrentGame().getNPCHARVEY(), dirHarvey);
                            moveNPC(App.getCurrentGame().getNPCLEAH(), dirLeah);
                            moveNPC(App.getCurrentGame().getNPCROBIN(), dirRobin);


                        }
                    }

                }
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void moveNPC(NPC npc, int dir) {
        switch (dir) {
            case 0:
                npc.setX(npc.getX() + 1);
                break;
            case 1:
                npc.setX(npc.getX() - 1);
                break;
            case 2:
                npc.setY(npc.getY() + 1);
                break;
            case 3:
                npc.setY(npc.getY() - 1);
                break;
        }
    }

    private static void sendNPCbackToHome() {
        App.getCurrentGame().getNPCSEBASTIAN().setX(ClientModel.getNPCSEBASTIANx());
        App.getCurrentGame().getNPCSEBASTIAN().setY(ClientModel.getNPCSEBASTIANy());
        App.getCurrentGame().getNPCABIGAIL().setX(ClientModel.getNPCABIGAILx());
        App.getCurrentGame().getNPCABIGAIL().setY(ClientModel.getNPCABIGAILy());
        App.getCurrentGame().getNPCHARVEY().setX(ClientModel.getNPCHARVEYx());
        App.getCurrentGame().getNPCHARVEY().setY(ClientModel.getNPCHARVEYy());
        App.getCurrentGame().getNPCROBIN().setX(ClientModel.getNPCROBINx());
        App.getCurrentGame().getNPCROBIN().setY(ClientModel.getNPCROBINy());
        App.getCurrentGame().getNPCLEAH().setX(ClientModel.getNPCLEAHx());
        App.getCurrentGame().getNPCLEAH().setY(ClientModel.getNPCLEAHy());
    }

    public static void getBackHome() {
        int player1TopLeftx = 0;
        int player1TopLefty = 0;
        int player1Width = 450;
        int player1Height = 200;

        int player2TopLeftx = 550;
        int player2TopLefty = 0;
        int player2Width = 449;
        int player2Height = 200;

        int player3TopLeftx = 0;
        int player3TopLefty = 360;
        int player3Width = 450;
        int player3Height = 200;

        int player4TopLeftx = 550;
        int player4TopLefty = 360;
        int player4Width = 449;
        int player4Height = 200;
        Cord player1 = getTopLeftCottage(player1TopLeftx, player1TopLefty, player1TopLeftx + player1Width, player1TopLefty + player1Height);
        Cord player2 = getTopLeftCottage(player2TopLeftx, player2TopLefty, player2TopLeftx + player2Width, player2TopLefty + player2Height);
        Cord player3 = getTopLeftCottage(player3TopLeftx, player3TopLefty, player2TopLeftx + player3Width, player3TopLefty + player3Height);
        Cord player4 = getTopLeftCottage(player4TopLeftx, player4TopLefty, player4TopLeftx + player4Width, player4TopLefty + player4Height);
        for (int i = 0; i < App.getCurrentGame().getPlayers().size(); i++) {
            if (i == 0) {
                if (App.getCurrentGame().getPlayers().get(0).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(0).setX(player1.getX() + 5);
                    App.getCurrentGame().getPlayers().get(0).setY(player1.getY() + 5);
                }
            } else if (i == 1) {
                if (App.getCurrentGame().getPlayers().get(1).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(1).setX(player2.getX() + 5);
                    App.getCurrentGame().getPlayers().get(1).setY(player2.getY() + 5);
                }
            } else if (i == 2) {
                if (App.getCurrentGame().getPlayers().get(2).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(2).setX(player3.getX() + 5);
                    App.getCurrentGame().getPlayers().get(2).setY(player3.getY() + 5);
                }
            } else if (i == 3) {
                if (App.getCurrentGame().getPlayers().get(3).getEnergy() > 0) {
                    App.getCurrentGame().getPlayers().get(3).setX(player4.getX() + 5);
                    App.getCurrentGame().getPlayers().get(3).setY(player4.getY() + 5);
                }
            }
        }
    }

    public static Cord getTopLeftCottage(int firstX, int firstY, int secondX, int secondY) {
        for (int x = firstX; x < secondX; x++) {
            for (int y = firstY; y < secondY; y++) {
                if (App.getCurrentGame().getMap().get(x).get(y).getInside() instanceof Cottage) {
                    return new Cord(x, y);
                }
            }
        }
        return null;
    }
}

