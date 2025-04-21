package Model;

import enums.Seasons;

import java.util.ArrayList;

public class Game {
    protected User Creator;
    protected int indexPlayerinControl;
    protected ArrayList<Player> Players;
//    protected Player CurrentPlayer;
    protected  Seasons CurrentSeason;
    protected  DateTime currentDateTime;


    public User getCreator() {
        return Creator;
    }

    public void setCreator(User creator) {
        Creator = creator;
    }

    public int getIndexPlayerinControl() {
        return indexPlayerinControl;
    }

    public void setIndexPlayerinControl(int indexPlayerinControl) {
        this.indexPlayerinControl = indexPlayerinControl;
    }

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }

//    public Player getCurrentPlayer() {
//        return CurrentPlayer;
//    }
//
//    public void setCurrentPlayer(Player currentPlayer) {
//        CurrentPlayer = currentPlayer;
//    }

    public Seasons getCurrentSeason() {
        return CurrentSeason;
    }

    public void setCurrentSeason(Seasons currentSeason) {
        CurrentSeason = currentSeason;
    }

    public DateTime getCurrentDateTime() {
        return currentDateTime;
    }

    public void setCurrentDateTime(DateTime currentDateTime) {
        this.currentDateTime = currentDateTime;
    }
}
