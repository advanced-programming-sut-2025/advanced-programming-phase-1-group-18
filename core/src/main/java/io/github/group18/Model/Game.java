package io.github.group18.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import io.github.group18.Network.Client.App.ClientModel;
import io.github.group18.enums.Seasons;
import io.github.group18.enums.WeatherEnum;
import io.github.group18.Model.Items.Gift;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {
    public ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
    public final User Creator;
    public ArrayList<Player> Players;
    public Seasons CurrentSeason;
    public DateTime currentDateTime;
    public WeatherEnum currentWeather;
    public Deque<WeatherEnum> weather = new ArrayDeque<>();
    public BlackSmithMarket blackSmithMarket;
    public CarpentersShopMarket carpentersShopMarket;
    public FishShopMarket fishShopMarket;
    public JojoMartMarket jojoMartMarket;
    public MarniesRanchMarket marniesRanchMarket;
    public PierresGeneralStoreMarket pierresGeneralStoreMarket;
    public TheStardropSaloonMarket theStardropSaloonMarket;
    public ArrayList<Friendship> friendships = new ArrayList<>();
    public ArrayList<Gift> gifts = new ArrayList<>();
    public ArrayList<Trade> trades = new ArrayList<>();
    private List<MarriageProposal> marriageProposals = new ArrayList<>();
    public NPC NPCSEBASTIAN;
    public NPC NPCABIGAIL;
    public NPC NPCHARVEY;
    public NPC NPCLEAH;
    public NPC NPCROBIN;

    public Game(User user) {
        Creator = user;
    }

    public void initializeFriendships() {
//        friendships = new ArrayList<>();
        for (int i = 0; i < Players.size(); i++) {
            for (int j = i + 1; j < Players.size(); j++) {
                Player p1 = Players.get(i);
                Player p2 = Players.get(j);
                Friendship f = new Friendship(p1, p2);
                f.setLevel(4);
                friendships.add(f);
            }
        }
    }

    public Player getPlayerByUsername(String username) {
        for (Player p : this.Players)
            if (p.getOwner().getUsername().equals(username))
                return p;
        return null;
    }

    public Friendship getOrCreateFriendship(Player p1, Player p2) {
        for (Friendship f : friendships) {
            if (f.isBetween(p1, p2)) return f;
        }
        Friendship newF = new Friendship(p1, p2);
        friendships.add(newF);
        return newF;
    }

    public void endOfDayUpdate() {
        for (Friendship f : friendships) {
            f.updateDecay();
        }
    }

    public ArrayList<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(ArrayList<Friendship> friendships) {
        this.friendships = friendships;
    }

    public ArrayList<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(ArrayList<Gift> gifts) {
        this.gifts = gifts;
    }

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

    public ArrayList<Player> getPlayers() {
        return Players;
    }

    public void setPlayers(ArrayList<Player> players) {
        Players = players;
    }

    public BlackSmithMarket getBlackSmithMarket() {
        return blackSmithMarket;
    }

    public void setBlackSmithMarket(BlackSmithMarket blackSmithMarket) {
        this.blackSmithMarket = blackSmithMarket;
    }

    public CarpentersShopMarket getCarpentersShopMarket() {
        return carpentersShopMarket;
    }

    public void setCarpentersShopMarket(CarpentersShopMarket carpentersShopMarket) {
        this.carpentersShopMarket = carpentersShopMarket;
    }

    public FishShopMarket getFishShopMarket() {
        return fishShopMarket;
    }

    public void setFishShopMarket(FishShopMarket fishShopMarket) {
        this.fishShopMarket = fishShopMarket;
    }

    public JojoMartMarket getJojoMartMarket() {
        return jojoMartMarket;
    }

    public void setJojoMartMarket(JojoMartMarket jojoMartMarket) {
        this.jojoMartMarket = jojoMartMarket;
    }

    public MarniesRanchMarket getMarniesRanchMarket() {
        return marniesRanchMarket;
    }

    public void setMarniesRanchMarket(MarniesRanchMarket marniesRanchMarket) {
        this.marniesRanchMarket = marniesRanchMarket;
    }

    public PierresGeneralStoreMarket getPierresGeneralStoreMarket() {
        return pierresGeneralStoreMarket;
    }

    public void setPierresGeneralStoreMarket(PierresGeneralStoreMarket pierresGeneralStoreMarket) {
        this.pierresGeneralStoreMarket = pierresGeneralStoreMarket;
    }

    public TheStardropSaloonMarket getTheStardropSaloonMarket() {
        return theStardropSaloonMarket;
    }

    public void setTheStardropSaloonMarket(TheStardropSaloonMarket theStardropSaloonMarket) {
        this.theStardropSaloonMarket = theStardropSaloonMarket;
    }

    public NPC getNPCSEBASTIAN() {
        return NPCSEBASTIAN;
    }

    public void setNPCSEBASTIAN(NPC NPCSEBASTIAN) {
        this.NPCSEBASTIAN = NPCSEBASTIAN;
    }

    public NPC getNPCABIGAIL() {
        return NPCABIGAIL;
    }

    public void setNPCABIGAIL(NPC NPCABIGAIL) {
        this.NPCABIGAIL = NPCABIGAIL;
    }

    public NPC getNPCHARVEY() {
        return NPCHARVEY;
    }

    public void setNPCHARVEY(NPC NPCHARVEY) {
        this.NPCHARVEY = NPCHARVEY;
    }

    public NPC getNPCLEAH() {
        return NPCLEAH;
    }

    public void setNPCLEAH(NPC NPCLEAH) {
        this.NPCLEAH = NPCLEAH;
    }

    public NPC getNPCROBIN() {
        return NPCROBIN;
    }

    public void setNPCROBIN(NPC NPCROBIN) {
        this.NPCROBIN = NPCROBIN;
    }

    public Seasons getCurrentSeason() {
        return Seasons.valueOf(App.getCurrentGame().getCurrentDateTime().getSeason());
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

    public List<MarriageProposal> getMarriageProposals() {
        return marriageProposals;
    }

    public void addMarriageProposal(MarriageProposal proposal) {
        marriageProposals.add(proposal);
    }

    public void removeMarriageProposal(MarriageProposal proposal) {
        marriageProposals.remove(proposal);
    }

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    public User getCreator() {
        return Creator;
    }

    public static ArrayList<Player> getFriendsOf(Player player) {
        ArrayList<Player> friends = new ArrayList<>();
        for (Friendship f : App.getCurrentGame().getFriendships()) {
            if (f.getPlayer1() == player) {
                friends.add(f.getPlayer2());
            } else if (f.getPlayer2() == player) {
                friends.add(f.getPlayer1());
            }
        }
        return friends;
    }

}
