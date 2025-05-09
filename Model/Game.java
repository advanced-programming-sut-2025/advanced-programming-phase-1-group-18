package Model;

import Model.Items.Gift;
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

    protected int BlackSmithTopLeftx;
    protected int BlackSmithTopLefty;
    protected int BlackSmithWidth;
    protected int BlackSmithHeight;
    protected int BlackSmithEnterancex;
    protected int BlackSmithEnterancey;

    protected int JojoMartTopLeftx;
    protected int JojoMartTopLefty;
    protected int JojoMartWidth;
    protected int JojoMartHeight;
    protected int JojoMartEnterancex;
    protected int JojoMartEnterancey;

    protected int PierresGeneralStoreTopLeftx;
    protected int PierresGeneralStoreTopLefty;
    protected int PierresGeneralStoreWidth;
    protected int PierresGeneralStoreHeight;
    protected int PierresGeneralStoreEnterancex;
    protected int PierresGeneralStoreEnterancey;

    protected int CarpentersShopTopLeftx;
    protected int CarpentersShopTopLefty;
    protected int CarpentersShopWidth;
    protected int CarpentersShopHeight;
    protected int CarpentersShopEnterancex;
    protected int CarpentersShopEnterancey;

    protected int FishShopTopLeftx;
    protected int FishShopTopLefty;
    protected int FishShopWidth;
    protected int FishShopHeight;
    protected int FishShopEnterancex;
    protected int FishShopEnterancey;

    protected int MarniesRanchTopLeftx;
    protected int MarniesRanchTopLefty;
    protected int MarniesRanchWidth;
    protected int MarniesRanchHeight;
    protected int MarniesRanchEnterancex;
    protected int MarniesRanchEnterancey;

    protected int TheStardropSaloonTopLeftx;
    protected int TheStardropSaloonTopLefty;
    protected int TheStardropSaloonWidth;
    protected int TheStardropSaloonHeight;
    protected int TheStardropSaloonEnterancex;
    protected int TheStardropSaloonEnterancey;


    protected ArrayList<ArrayList<Kashi>> Map = new ArrayList<>();
    protected User Creator;
    protected int indexPlayerinControl;
    protected ArrayList<Player> Players;
    protected Seasons CurrentSeason;
    protected DateTime currentDateTime;
    protected WeatherEnum currentWeather;
    protected Deque<WeatherEnum> weather = new ArrayDeque<>();
    protected BlackSmithMarket blackSmithMarket;
    protected CarpentersShopMarket carpentersShopMarket;
    protected FishShopMarket fishShopMarket;
    protected JojoMartMarket jojoMartMarket;
    protected MarniesRanchMarket marniesRanchMarket;
    protected PierresGeneralStoreMarket pierresGeneralStoreMarket;
    protected TheStardropSaloonMarket theStardropSaloonMarket;

    protected ArrayList<Friendship> friendships;
    protected ArrayList<Gift> gifts;

    public Game() {
        ArrayList<Cord> cords = new ArrayList<>();
        for (int i = BlackSmithTopLeftx; i <= BlackSmithTopLeftx + BlackSmithWidth; i++) {
            for (int j = BlackSmithTopLefty; j <= BlackSmithTopLefty + BlackSmithHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        blackSmithMarket = new BlackSmithMarket();
        blackSmithMarket.fillStock();
        blackSmithMarket.adaptMap(cords, BlackSmithEnterancex, BlackSmithEnterancey, BlackSmithTopLeftx, BlackSmithTopLefty, BlackSmithTopLeftx + BlackSmithWidth, BlackSmithTopLefty + BlackSmithHeight);

        cords = new ArrayList<>();
        for (int i = CarpentersShopTopLeftx; i <= CarpentersShopTopLeftx + CarpentersShopWidth; i++) {
            for (int j = CarpentersShopTopLefty; j <= CarpentersShopTopLefty + CarpentersShopHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        carpentersShopMarket = new CarpentersShopMarket();
        //ino rah bendaz
        //TODO
        //carpentersShopMarket.fillStock();
        carpentersShopMarket.adaptMap(cords, CarpentersShopEnterancex, CarpentersShopEnterancey, CarpentersShopTopLeftx, CarpentersShopTopLefty, CarpentersShopTopLeftx + CarpentersShopWidth, CarpentersShopTopLefty + CarpentersShopHeight);

        cords = new ArrayList<>();
        for (int i = FishShopTopLeftx; i <= FishShopTopLeftx + FishShopWidth; i++) {
            for (int j = FishShopTopLefty; j <= FishShopTopLefty + FishShopHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        fishShopMarket = new FishShopMarket();
        fishShopMarket.fillStock();
        fishShopMarket.adaptMap(cords, FishShopEnterancex, FishShopEnterancey, FishShopTopLeftx, FishShopTopLefty, FishShopTopLeftx + FishShopWidth, FishShopTopLefty + FishShopHeight);

        cords = new ArrayList<>();
        for (int i = JojoMartTopLeftx; i <= JojoMartTopLeftx + JojoMartWidth; i++) {
            for (int j = JojoMartTopLefty; j <= JojoMartTopLefty + JojoMartHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        jojoMartMarket = new JojoMartMarket();
        jojoMartMarket.fillStock(getCurrentSeason());
        jojoMartMarket.adaptMap(cords, JojoMartEnterancex, JojoMartEnterancey, JojoMartTopLeftx, JojoMartTopLefty, JojoMartTopLeftx + JojoMartWidth, JojoMartTopLefty + JojoMartHeight);

        cords = new ArrayList<>();
        for (int i = MarniesRanchTopLeftx; i <= MarniesRanchTopLeftx + MarniesRanchWidth; i++) {
            for (int j = MarniesRanchTopLefty; j <= MarniesRanchTopLefty + MarniesRanchHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        marniesRanchMarket = new MarniesRanchMarket();
        marniesRanchMarket.fillStock();
        marniesRanchMarket.adaptMap(cords, MarniesRanchEnterancex, MarniesRanchEnterancey, MarniesRanchTopLeftx, MarniesRanchTopLefty, MarniesRanchTopLeftx + MarniesRanchWidth, MarniesRanchTopLefty + MarniesRanchHeight);

        cords = new ArrayList<>();
        for (int i = PierresGeneralStoreTopLeftx; i <= PierresGeneralStoreTopLeftx + PierresGeneralStoreWidth; i++) {
            for (int j = PierresGeneralStoreTopLefty; j <= PierresGeneralStoreTopLefty + PierresGeneralStoreHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        pierresGeneralStoreMarket = new PierresGeneralStoreMarket();
        pierresGeneralStoreMarket.fillStock(getCurrentSeason());
        pierresGeneralStoreMarket.adaptMap(cords, PierresGeneralStoreEnterancex, PierresGeneralStoreEnterancey, PierresGeneralStoreTopLeftx, PierresGeneralStoreTopLefty, PierresGeneralStoreTopLeftx + PierresGeneralStoreWidth, PierresGeneralStoreTopLefty + PierresGeneralStoreHeight);

        cords = new ArrayList<>();
        for (int i = TheStardropSaloonTopLeftx; i <= TheStardropSaloonTopLeftx + TheStardropSaloonWidth; i++) {
            for (int j = TheStardropSaloonTopLefty; j <= TheStardropSaloonTopLefty + TheStardropSaloonHeight; j++) {
                cords.add(new Cord(i, j));
            }
        }
        theStardropSaloonMarket = new TheStardropSaloonMarket();
        theStardropSaloonMarket.fillStock();
        theStardropSaloonMarket.adaptMap(cords, TheStardropSaloonEnterancex, TheStardropSaloonEnterancey, TheStardropSaloonTopLeftx, TheStardropSaloonTopLefty, TheStardropSaloonTopLeftx + TheStardropSaloonWidth, TheStardropSaloonTopLefty + TheStardropSaloonHeight);
    }
    public void initializeFriendships() {
        for (int i = 0; i < Players.size(); i++) {
            for (int j = i + 1; j < Players.size(); j++) {
                Player p1 = Players.get(i);
                Player p2 = Players.get(j);
                Friendship f = new Friendship(p1, p2);
                friendships.add(f);
            }
        }
    }
    public Player getPlayerByUsername(String username) {
        for (Player p : this.Players)
            if (p.getUsername().equals(username))
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

    public int getBlackSmithEnterancex() {
        return BlackSmithEnterancex;
    }

    public void setBlackSmithEnterancex(int blackSmithEnterancex) {
        BlackSmithEnterancex = blackSmithEnterancex;
    }

    public int getBlackSmithEnterancey() {
        return BlackSmithEnterancey;
    }

    public void setBlackSmithEnterancey(int blackSmithEnterancey) {
        BlackSmithEnterancey = blackSmithEnterancey;
    }

    public int getJojoMartEnterancex() {
        return JojoMartEnterancex;
    }

    public void setJojoMartEnterancex(int jojoMartEnterancex) {
        JojoMartEnterancex = jojoMartEnterancex;
    }

    public int getJojoMartEnterancey() {
        return JojoMartEnterancey;
    }

    public void setJojoMartEnterancey(int jojoMartEnterancey) {
        JojoMartEnterancey = jojoMartEnterancey;
    }

    public int getPierresGeneralStoreEnterancex() {
        return PierresGeneralStoreEnterancex;
    }

    public void setPierresGeneralStoreEnterancex(int pierresGeneralStoreEnterancex) {
        PierresGeneralStoreEnterancex = pierresGeneralStoreEnterancex;
    }

    public int getPierresGeneralStoreEnterancey() {
        return PierresGeneralStoreEnterancey;
    }

    public void setPierresGeneralStoreEnterancey(int pierresGeneralStoreEnterancey) {
        PierresGeneralStoreEnterancey = pierresGeneralStoreEnterancey;
    }

    public int getCarpentersShopEnterancex() {
        return CarpentersShopEnterancex;
    }

    public void setCarpentersShopEnterancex(int carpentersShopEnterancex) {
        CarpentersShopEnterancex = carpentersShopEnterancex;
    }

    public int getCarpentersShopEnterancey() {
        return CarpentersShopEnterancey;
    }

    public void setCarpentersShopEnterancey(int carpentersShopEnterancey) {
        CarpentersShopEnterancey = carpentersShopEnterancey;
    }

    public int getFishShopEnterancex() {
        return FishShopEnterancex;
    }

    public void setFishShopEnterancex(int fishShopEnterancex) {
        FishShopEnterancex = fishShopEnterancex;
    }

    public int getFishShopEnterancey() {
        return FishShopEnterancey;
    }

    public void setFishShopEnterancey(int fishShopEnterancey) {
        FishShopEnterancey = fishShopEnterancey;
    }

    public int getMarniesRanchEnterancex() {
        return MarniesRanchEnterancex;
    }

    public void setMarniesRanchEnterancex(int marniesRanchEnterancex) {
        MarniesRanchEnterancex = marniesRanchEnterancex;
    }

    public int getMarniesRanchEnterancey() {
        return MarniesRanchEnterancey;
    }

    public void setMarniesRanchEnterancey(int marniesRanchEnterancey) {
        MarniesRanchEnterancey = marniesRanchEnterancey;
    }

    public int getTheStardropSaloonEnterancex() {
        return TheStardropSaloonEnterancex;
    }

    public void setTheStardropSaloonEnterancex(int theStardropSaloonEnterancex) {
        TheStardropSaloonEnterancex = theStardropSaloonEnterancex;
    }

    public int getTheStardropSaloonEnterancey() {
        return TheStardropSaloonEnterancey;
    }

    public void setTheStardropSaloonEnterancey(int theStardropSaloonEnterancey) {
        TheStardropSaloonEnterancey = theStardropSaloonEnterancey;
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

    public int getBlackSmithTopLeftx() {
        return BlackSmithTopLeftx;
    }

    public void setBlackSmithTopLeftx(int blackSmithTopLeftx) {
        BlackSmithTopLeftx = blackSmithTopLeftx;
    }

    public int getBlackSmithTopLefty() {
        return BlackSmithTopLefty;
    }

    public void setBlackSmithTopLefty(int blackSmithTopLefty) {
        BlackSmithTopLefty = blackSmithTopLefty;
    }

    public int getBlackSmithWidth() {
        return BlackSmithWidth;
    }

    public void setBlackSmithWidth(int blackSmithWidth) {
        BlackSmithWidth = blackSmithWidth;
    }

    public int getBlackSmithHeight() {
        return BlackSmithHeight;
    }

    public void setBlackSmithHeight(int blackSmithHeight) {
        BlackSmithHeight = blackSmithHeight;
    }

    public int getJojoMartTopLeftx() {
        return JojoMartTopLeftx;
    }

    public void setJojoMartTopLeftx(int jojoMartTopLeftx) {
        JojoMartTopLeftx = jojoMartTopLeftx;
    }

    public int getJojoMartTopLefty() {
        return JojoMartTopLefty;
    }

    public void setJojoMartTopLefty(int jojoMartTopLefty) {
        JojoMartTopLefty = jojoMartTopLefty;
    }

    public int getJojoMartWidth() {
        return JojoMartWidth;
    }

    public void setJojoMartWidth(int jojoMartWidth) {
        JojoMartWidth = jojoMartWidth;
    }

    public int getJojoMartHeight() {
        return JojoMartHeight;
    }

    public void setJojoMartHeight(int jojoMartHeight) {
        JojoMartHeight = jojoMartHeight;
    }

    public int getPierresGeneralStoreTopLeftx() {
        return PierresGeneralStoreTopLeftx;
    }

    public void setPierresGeneralStoreTopLeftx(int pierresGeneralStoreTopLeftx) {
        PierresGeneralStoreTopLeftx = pierresGeneralStoreTopLeftx;
    }

    public int getPierresGeneralStoreTopLefty() {
        return PierresGeneralStoreTopLefty;
    }

    public void setPierresGeneralStoreTopLefty(int pierresGeneralStoreTopLefty) {
        PierresGeneralStoreTopLefty = pierresGeneralStoreTopLefty;
    }

    public int getPierresGeneralStoreWidth() {
        return PierresGeneralStoreWidth;
    }

    public void setPierresGeneralStoreWidth(int pierresGeneralStoreWidth) {
        PierresGeneralStoreWidth = pierresGeneralStoreWidth;
    }

    public int getPierresGeneralStoreHeight() {
        return PierresGeneralStoreHeight;
    }

    public void setPierresGeneralStoreHeight(int pierresGeneralStoreHeight) {
        PierresGeneralStoreHeight = pierresGeneralStoreHeight;
    }

    public int getCarpentersShopTopLeftx() {
        return CarpentersShopTopLeftx;
    }

    public void setCarpentersShopTopLeftx(int carpentersShopTopLeftx) {
        CarpentersShopTopLeftx = carpentersShopTopLeftx;
    }

    public int getCarpentersShopTopLefty() {
        return CarpentersShopTopLefty;
    }

    public void setCarpentersShopTopLefty(int carpentersShopTopLefty) {
        CarpentersShopTopLefty = carpentersShopTopLefty;
    }

    public int getCarpentersShopWidth() {
        return CarpentersShopWidth;
    }

    public void setCarpentersShopWidth(int carpentersShopWidth) {
        CarpentersShopWidth = carpentersShopWidth;
    }

    public int getCarpentersShopHeight() {
        return CarpentersShopHeight;
    }

    public void setCarpentersShopHeight(int carpentersShopHeight) {
        CarpentersShopHeight = carpentersShopHeight;
    }

    public int getFishShopTopLeftx() {
        return FishShopTopLeftx;
    }

    public void setFishShopTopLeftx(int fishShopTopLeftx) {
        FishShopTopLeftx = fishShopTopLeftx;
    }

    public int getFishShopTopLefty() {
        return FishShopTopLefty;
    }

    public void setFishShopTopLefty(int fishShopTopLefty) {
        FishShopTopLefty = fishShopTopLefty;
    }

    public int getFishShopWidth() {
        return FishShopWidth;
    }

    public void setFishShopWidth(int fishShopWidth) {
        FishShopWidth = fishShopWidth;
    }

    public int getFishShopHeight() {
        return FishShopHeight;
    }

    public void setFishShopHeight(int fishShopHeight) {
        FishShopHeight = fishShopHeight;
    }

    public int getMarniesRanchTopLeftx() {
        return MarniesRanchTopLeftx;
    }

    public void setMarniesRanchTopLeftx(int marniesRanchTopLeftx) {
        MarniesRanchTopLeftx = marniesRanchTopLeftx;
    }

    public int getMarniesRanchTopLefty() {
        return MarniesRanchTopLefty;
    }

    public void setMarniesRanchTopLefty(int marniesRanchTopLefty) {
        MarniesRanchTopLefty = marniesRanchTopLefty;
    }

    public int getMarniesRanchWidth() {
        return MarniesRanchWidth;
    }

    public void setMarniesRanchWidth(int marniesRanchWidth) {
        MarniesRanchWidth = marniesRanchWidth;
    }

    public int getMarniesRanchHeight() {
        return MarniesRanchHeight;
    }

    public void setMarniesRanchHeight(int marniesRanchHeight) {
        MarniesRanchHeight = marniesRanchHeight;
    }

    public int getTheStardropSaloonTopLeftx() {
        return TheStardropSaloonTopLeftx;
    }

    public void setTheStardropSaloonTopLeftx(int theStardropSaloonTopLeftx) {
        TheStardropSaloonTopLeftx = theStardropSaloonTopLeftx;
    }

    public int getTheStardropSaloonTopLefty() {
        return TheStardropSaloonTopLefty;
    }

    public void setTheStardropSaloonTopLefty(int theStardropSaloonTopLefty) {
        TheStardropSaloonTopLefty = theStardropSaloonTopLefty;
    }

    public int getTheStardropSaloonWidth() {
        return TheStardropSaloonWidth;
    }

    public void setTheStardropSaloonWidth(int theStardropSaloonWidth) {
        TheStardropSaloonWidth = theStardropSaloonWidth;
    }

    public int getTheStardropSaloonHeight() {
        return TheStardropSaloonHeight;
    }

    public void setTheStardropSaloonHeight(int theStardropSaloonHeight) {
        TheStardropSaloonHeight = theStardropSaloonHeight;
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
