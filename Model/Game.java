package Model;

import enums.Seasons;
import enums.WeatherEnum;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Game {
    protected int player1TopLeftx;
    protected int player1TopLefty;
    protected int player1Width;
    protected int player1Height;
    protected int player2TopLeftx;
    protected int player2TopLefty;
    protected int player2Width;
    protected int player2Height;
    protected int player3TopLeftx;
    protected int player3TopLefty;
    protected int player3Width;
    protected int player3Height;
    protected int player4TopLeftx;
    protected int player4TopLefty;
    protected int player4Width;
    protected int player4Height;
    protected ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
    protected User Creator;
    protected int indexPlayerinControl;
    protected ArrayList<Player> Players;
    //    protected Player CurrentPlayer;
    protected Seasons CurrentSeason;
    protected DateTime currentDateTime;
    protected WeatherEnum currentWeather;
    protected Deque<WeatherEnum> weather = new ArrayDeque<>();


    public ArrayList<ArrayList<Kashi>> getMap() {
        return Map;
    }

    public void setMap(ArrayList<ArrayList<Kashi>> Map) {
        this.Map = Map;
    }

    public Deque<WeatherEnum> getWeather() {
        return weather;
    }

    public void setWeather(Deque<WeatherEnum> weather) {
        this.weather = weather;
    }

    public WeatherEnum getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(WeatherEnum currentWeather) {
        this.currentWeather = currentWeather;
    }

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

    public int getPlayer1TopLeftx() {
        return player1TopLeftx;
    }

    public void setPlayer1TopLeftx(int player1TopLeftx) {
        this.player1TopLeftx = player1TopLeftx;
    }

    public int getPlayer1TopLefty() {
        return player1TopLefty;
    }

    public void setPlayer1TopLefty(int player1TopLefty) {
        this.player1TopLefty = player1TopLefty;
    }

    public int getPlayer1Width() {
        return player1Width;
    }

    public void setPlayer1Width(int player1Width) {
        this.player1Width = player1Width;
    }

    public int getPlayer1Height() {
        return player1Height;
    }

    public void setPlayer1Height(int player1Height) {
        this.player1Height = player1Height;
    }

    public int getPlayer2TopLeftx() {
        return player2TopLeftx;
    }

    public void setPlayer2TopLeftx(int player2TopLeftx) {
        this.player2TopLeftx = player2TopLeftx;
    }

    public int getPlayer2TopLefty() {
        return player2TopLefty;
    }

    public void setPlayer2TopLefty(int player2TopLefty) {
        this.player2TopLefty = player2TopLefty;
    }

    public int getPlayer2Width() {
        return player2Width;
    }

    public void setPlayer2Width(int player2Width) {
        this.player2Width = player2Width;
    }

    public int getPlayer2Height() {
        return player2Height;
    }

    public void setPlayer2Height(int player2Height) {
        this.player2Height = player2Height;
    }

    public int getPlayer3TopLeftx() {
        return player3TopLeftx;
    }

    public void setPlayer3TopLeftx(int player3TopLeftx) {
        this.player3TopLeftx = player3TopLeftx;
    }

    public int getPlayer3TopLefty() {
        return player3TopLefty;
    }

    public void setPlayer3TopLefty(int player3TopLefty) {
        this.player3TopLefty = player3TopLefty;
    }

    public int getPlayer3Width() {
        return player3Width;
    }

    public void setPlayer3Width(int player3Width) {
        this.player3Width = player3Width;
    }

    public int getPlayer3Height() {
        return player3Height;
    }

    public void setPlayer3Height(int player3Height) {
        this.player3Height = player3Height;
    }

    public int getPlayer4TopLeftx() {
        return player4TopLeftx;
    }

    public void setPlayer4TopLeftx(int player4TopLeftx) {
        this.player4TopLeftx = player4TopLeftx;
    }

    public int getPlayer4TopLefty() {
        return player4TopLefty;
    }

    public void setPlayer4TopLefty(int player4TopLefty) {
        this.player4TopLefty = player4TopLefty;
    }

    public int getPlayer4Width() {
        return player4Width;
    }

    public void setPlayer4Width(int player4Width) {
        this.player4Width = player4Width;
    }

    public int getPlayer4Height() {
        return player4Height;
    }

    public void setPlayer4Height(int player4Height) {
        this.player4Height = player4Height;
    }

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
