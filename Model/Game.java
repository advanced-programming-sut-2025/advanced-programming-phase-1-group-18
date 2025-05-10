package Model;

import enums.NPCEnums;
import enums.Seasons;
import enums.WeatherEnum;
import Model.Items.Gift;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Game {
    protected int player1TopLeftx = 0;
    protected int player1TopLefty = 0;
    protected int player1Width = 50;
    protected int player1Height = 50;

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

    // Sebastian
    protected int NPCSEBASTIANTopLeftX;
    protected int NPCSEBASTIANTopLeftY;
    protected int NPCSEBASTIANWidth;
    protected int NPCSEBASTIANHeight;
    protected int NPCSEBASTIANEntranceX;
    protected int NPCSEBASTIANEntranceY;

    // Abigail
    protected int NPCABIGAILTopLeftX;
    protected int NPCABIGAILTopLeftY;
    protected int NPCABIGAILWidth;
    protected int NPCABIGAILHeight;
    protected int NPCABIGAILEntranceX;
    protected int NPCABIGAILEntranceY;

    // Harvey
    protected int NPCHARVEYTopLeftX;
    protected int NPCHARVEYTopLeftY;
    protected int NPCHARVEYWidth;
    protected int NPCHARVEYHeight;
    protected int NPCHARVEYEntranceX;
    protected int NPCHARVEYEntranceY;

    // Leah
    protected int NPCLEAHTopLeftX;
    protected int NPCLEAHTopLeftY;
    protected int NPCLEAHWidth;
    protected int NPCLEAHHeight;
    protected int NPCLEAHEntranceX;
    protected int NPCLEAHEntranceY;

    // Robin
    protected int NPCROBINTopLeftX;
    protected int NPCROBINTopLeftY;
    protected int NPCROBINWidth;
    protected int NPCROBINHeight;
    protected int NPCROBINEntranceX;
    protected int NPCROBINEntranceY;

    protected int NPCSEBASTIANx;
    protected int NPCSEBASTIANy;
    protected int NPCABIGAILx;
    protected int NPCABIGAILy;
    protected int NPCHARVEYx;
    protected int NPCHARVEYy;
    protected int NPCLEAHx;
    protected int NPCLEAHy;
    protected int NPCROBINx;
    protected int NPCROBINy;

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
    protected ArrayList<Friendship> friendships = new ArrayList<>();
    protected ArrayList<Gift> gifts = new ArrayList<>();
    protected NPC NPCSEBASTIAN;
    protected NPC NPCABIGAIL;
    protected NPC NPCHARVEY;
    protected NPC NPCLEAH;
    protected NPC NPCROBIN;


    public Game() {

    }

    public void initializeFriendships() {
//        friendships = new ArrayList<>();
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

    public int getNPCROBINy() {
        return NPCROBINy;
    }

    public void setNPCROBINy(int NPCROBINy) {
        this.NPCROBINy = NPCROBINy;
    }

    public int getNPCROBINx() {
        return NPCROBINx;
    }

    public void setNPCROBINx(int NPCROBINx) {
        this.NPCROBINx = NPCROBINx;
    }

    public int getNPCLEAHy() {
        return NPCLEAHy;
    }

    public void setNPCLEAHy(int NPCLEAHy) {
        this.NPCLEAHy = NPCLEAHy;
    }

    public int getNPCLEAHx() {
        return NPCLEAHx;
    }

    public void setNPCLEAHx(int NPCLEAHx) {
        this.NPCLEAHx = NPCLEAHx;
    }

    public int getNPCHARVEYy() {
        return NPCHARVEYy;
    }

    public void setNPCHARVEYy(int NPCHARVEYy) {
        this.NPCHARVEYy = NPCHARVEYy;
    }

    public int getNPCHARVEYx() {
        return NPCHARVEYx;
    }

    public void setNPCHARVEYx(int NPCHARVEYx) {
        this.NPCHARVEYx = NPCHARVEYx;
    }

    public int getNPCABIGAILy() {
        return NPCABIGAILy;
    }

    public void setNPCABIGAILy(int NPCABIGAILy) {
        this.NPCABIGAILy = NPCABIGAILy;
    }

    public int getNPCABIGAILx() {
        return NPCABIGAILx;
    }

    public void setNPCABIGAILx(int NPCABIGAILx) {
        this.NPCABIGAILx = NPCABIGAILx;
    }

    public int getNPCSEBASTIANy() {
        return NPCSEBASTIANy;
    }

    public void setNPCSEBASTIANy(int NPCSEBASTIANy) {
        this.NPCSEBASTIANy = NPCSEBASTIANy;
    }

    public int getNPCSEBASTIANx() {
        return NPCSEBASTIANx;
    }

    public void setNPCSEBASTIANx(int NPCSEBASTIANx) {
        this.NPCSEBASTIANx = NPCSEBASTIANx;
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

    public int getNPCSEBASTIANTopLeftX() {
        return NPCSEBASTIANTopLeftX;
    }

    public void setNPCSEBASTIANTopLeftX(int NPCSEBASTIANTopLeftX) {
        this.NPCSEBASTIANTopLeftX = NPCSEBASTIANTopLeftX;
    }

    public int getNPCSEBASTIANTopLeftY() {
        return NPCSEBASTIANTopLeftY;
    }

    public void setNPCSEBASTIANTopLeftY(int NPCSEBASTIANTopLeftY) {
        this.NPCSEBASTIANTopLeftY = NPCSEBASTIANTopLeftY;
    }

    public int getNPCSEBASTIANWidth() {
        return NPCSEBASTIANWidth;
    }

    public void setNPCSEBASTIANWidth(int NPCSEBASTIANWidth) {
        this.NPCSEBASTIANWidth = NPCSEBASTIANWidth;
    }

    public int getNPCSEBASTIANHeight() {
        return NPCSEBASTIANHeight;
    }

    public void setNPCSEBASTIANHeight(int NPCSEBASTIANHeight) {
        this.NPCSEBASTIANHeight = NPCSEBASTIANHeight;
    }

    public int getNPCSEBASTIANEntranceX() {
        return NPCSEBASTIANEntranceX;
    }

    public void setNPCSEBASTIANEntranceX(int NPCSEBASTIANEntranceX) {
        this.NPCSEBASTIANEntranceX = NPCSEBASTIANEntranceX;
    }

    public int getNPCSEBASTIANEntranceY() {
        return NPCSEBASTIANEntranceY;
    }

    public void setNPCSEBASTIANEntranceY(int NPCSEBASTIANEntranceY) {
        this.NPCSEBASTIANEntranceY = NPCSEBASTIANEntranceY;
    }

    public int getNPCABIGAILTopLeftX() {
        return NPCABIGAILTopLeftX;
    }

    public void setNPCABIGAILTopLeftX(int NPCABIGAILTopLeftX) {
        this.NPCABIGAILTopLeftX = NPCABIGAILTopLeftX;
    }

    public int getNPCABIGAILTopLeftY() {
        return NPCABIGAILTopLeftY;
    }

    public void setNPCABIGAILTopLeftY(int NPCABIGAILTopLeftY) {
        this.NPCABIGAILTopLeftY = NPCABIGAILTopLeftY;
    }

    public int getNPCABIGAILWidth() {
        return NPCABIGAILWidth;
    }

    public void setNPCABIGAILWidth(int NPCABIGAILWidth) {
        this.NPCABIGAILWidth = NPCABIGAILWidth;
    }

    public int getNPCABIGAILHeight() {
        return NPCABIGAILHeight;
    }

    public void setNPCABIGAILHeight(int NPCABIGAILHeight) {
        this.NPCABIGAILHeight = NPCABIGAILHeight;
    }

    public int getNPCABIGAILEntranceX() {
        return NPCABIGAILEntranceX;
    }

    public void setNPCABIGAILEntranceX(int NPCABIGAILEntranceX) {
        this.NPCABIGAILEntranceX = NPCABIGAILEntranceX;
    }

    public int getNPCABIGAILEntranceY() {
        return NPCABIGAILEntranceY;
    }

    public void setNPCABIGAILEntranceY(int NPCABIGAILEntranceY) {
        this.NPCABIGAILEntranceY = NPCABIGAILEntranceY;
    }

    public int getNPCHARVEYTopLeftX() {
        return NPCHARVEYTopLeftX;
    }

    public void setNPCHARVEYTopLeftX(int NPCHARVEYTopLeftX) {
        this.NPCHARVEYTopLeftX = NPCHARVEYTopLeftX;
    }

    public int getNPCHARVEYTopLeftY() {
        return NPCHARVEYTopLeftY;
    }

    public void setNPCHARVEYTopLeftY(int NPCHARVEYTopLeftY) {
        this.NPCHARVEYTopLeftY = NPCHARVEYTopLeftY;
    }

    public int getNPCHARVEYWidth() {
        return NPCHARVEYWidth;
    }

    public void setNPCHARVEYWidth(int NPCHARVEYWidth) {
        this.NPCHARVEYWidth = NPCHARVEYWidth;
    }

    public int getNPCHARVEYHeight() {
        return NPCHARVEYHeight;
    }

    public void setNPCHARVEYHeight(int NPCHARVEYHeight) {
        this.NPCHARVEYHeight = NPCHARVEYHeight;
    }

    public int getNPCHARVEYEntranceX() {
        return NPCHARVEYEntranceX;
    }

    public void setNPCHARVEYEntranceX(int NPCHARVEYEntranceX) {
        this.NPCHARVEYEntranceX = NPCHARVEYEntranceX;
    }

    public int getNPCHARVEYEntranceY() {
        return NPCHARVEYEntranceY;
    }

    public void setNPCHARVEYEntranceY(int NPCHARVEYEntranceY) {
        this.NPCHARVEYEntranceY = NPCHARVEYEntranceY;
    }

    public int getNPCLEAHTopLeftX() {
        return NPCLEAHTopLeftX;
    }

    public void setNPCLEAHTopLeftX(int NPCLEAHTopLeftX) {
        this.NPCLEAHTopLeftX = NPCLEAHTopLeftX;
    }

    public int getNPCLEAHTopLeftY() {
        return NPCLEAHTopLeftY;
    }

    public void setNPCLEAHTopLeftY(int NPCLEAHTopLeftY) {
        this.NPCLEAHTopLeftY = NPCLEAHTopLeftY;
    }

    public int getNPCLEAHWidth() {
        return NPCLEAHWidth;
    }

    public void setNPCLEAHWidth(int NPCLEAHWidth) {
        this.NPCLEAHWidth = NPCLEAHWidth;
    }

    public int getNPCLEAHHeight() {
        return NPCLEAHHeight;
    }

    public void setNPCLEAHHeight(int NPCLEAHHeight) {
        this.NPCLEAHHeight = NPCLEAHHeight;
    }

    public int getNPCLEAHEntranceX() {
        return NPCLEAHEntranceX;
    }

    public void setNPCLEAHEntranceX(int NPCLEAHEntranceX) {
        this.NPCLEAHEntranceX = NPCLEAHEntranceX;
    }

    public int getNPCLEAHEntranceY() {
        return NPCLEAHEntranceY;
    }

    public void setNPCLEAHEntranceY(int NPCLEAHEntranceY) {
        this.NPCLEAHEntranceY = NPCLEAHEntranceY;
    }

    public int getNPCROBINTopLeftX() {
        return NPCROBINTopLeftX;
    }

    public void setNPCROBINTopLeftX(int NPCROBINTopLeftX) {
        this.NPCROBINTopLeftX = NPCROBINTopLeftX;
    }

    public int getNPCROBINTopLeftY() {
        return NPCROBINTopLeftY;
    }

    public void setNPCROBINTopLeftY(int NPCROBINTopLeftY) {
        this.NPCROBINTopLeftY = NPCROBINTopLeftY;
    }

    public int getNPCROBINWidth() {
        return NPCROBINWidth;
    }

    public void setNPCROBINWidth(int NPCROBINWidth) {
        this.NPCROBINWidth = NPCROBINWidth;
    }

    public int getNPCROBINHeight() {
        return NPCROBINHeight;
    }

    public void setNPCROBINHeight(int NPCROBINHeight) {
        this.NPCROBINHeight = NPCROBINHeight;
    }

    public int getNPCROBINEntranceX() {
        return NPCROBINEntranceX;
    }

    public void setNPCROBINEntranceX(int NPCROBINEntranceX) {
        this.NPCROBINEntranceX = NPCROBINEntranceX;
    }

    public int getNPCROBINEntranceY() {
        return NPCROBINEntranceY;
    }

    public void setNPCROBINEntranceY(int NPCROBINEntranceY) {
        this.NPCROBINEntranceY = NPCROBINEntranceY;
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
