package Model;

import enums.Seasons;
import enums.WeatherEnum;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Game {
    protected User Creator;
    protected int indexPlayerinControl;
    protected ArrayList<Player> Players;
//    protected Player CurrentPlayer;
    protected  Seasons CurrentSeason;
    protected  DateTime currentDateTime;
    protected WeatherEnum currentWeather;
    protected Deque<WeatherEnum> weather = new ArrayDeque<>();

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
