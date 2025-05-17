package Model;

import enums.NPCEnums;
import enums.Seasons;
import enums.WeatherEnum;
import Model.Items.Gift;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Game {
    protected static int player1TopLeftx = 0;
    protected static int player1TopLefty = 0;
    protected static int player1Width = 450;
    protected static int player1Height = 200;

    protected static int player2TopLeftx = 550;
    protected static int player2TopLefty = 0;
    protected static int player2Width = 449;
    protected static int player2Height = 200;

    protected static int player3TopLeftx = 0;
    protected static int player3TopLefty = 360;
    protected static int player3Width = 450;
    protected static int player3Height = 200;

    protected static int player4TopLeftx = 550;
    protected static int player4TopLefty = 360;
    protected static int player4Width = 449;
    protected static int player4Height = 200;

    protected static int BlackSmithTopLeftx = 75;
    protected static int BlackSmithTopLefty = 230;
    protected static int BlackSmithWidth = 100;
    protected static int BlackSmithHeight = 100;
    protected static int BlackSmithEnterancex = 125;
    protected static int BlackSmithEnterancey = 230;

    protected static int JojoMartTopLeftx = 200;
    protected static int JojoMartTopLefty = 230;
    protected static int JojoMartWidth = 100;
    protected static int JojoMartHeight = 100;
    protected static int JojoMartEnterancex = 250;
    protected static int JojoMartEnterancey = 230;

    protected static int PierresGeneralStoreTopLeftx = 325;
    protected static int PierresGeneralStoreTopLefty = 230;
    protected static int PierresGeneralStoreWidth = 100;
    protected static int PierresGeneralStoreHeight = 100;
    protected static int PierresGeneralStoreEnterancex = 375;
    protected static int PierresGeneralStoreEnterancey = 230;

    protected static int CarpentersShopTopLeftx = 450;
    protected static int CarpentersShopTopLefty = 230;
    protected static int CarpentersShopWidth = 100;
    protected static int CarpentersShopHeight = 100;
    protected static int CarpentersShopEnterancex = 500;
    protected static int CarpentersShopEnterancey = 230;

    protected static int FishShopTopLeftx = 575;
    protected static int FishShopTopLefty = 230;
    protected static int FishShopWidth = 100;
    protected static int FishShopHeight = 100;
    protected static int FishShopEnterancex = 625;
    protected static int FishShopEnterancey = 230;

    protected static int MarniesRanchTopLeftx = 700;
    protected static int MarniesRanchTopLefty = 230;
    protected static int MarniesRanchWidth = 100;
    protected static int MarniesRanchHeight = 100;
    protected static int MarniesRanchEnterancex = 750;
    protected static int MarniesRanchEnterancey = 230;

    protected static int TheStardropSaloonTopLeftx = 825;
    protected static int TheStardropSaloonTopLefty = 230;
    protected static int TheStardropSaloonWidth = 100;
    protected static int TheStardropSaloonHeight = 100;
    protected static int TheStardropSaloonEnterancex = 875;
    protected static int TheStardropSaloonEnterancey = 230;

    // Sebastian
    protected static int NPCSEBASTIANTopLeftX = 480;
    protected static int NPCSEBASTIANTopLeftY = 100;
//    protected int NPCSEBASTIANWidth = 1;
//    protected int NPCSEBASTIANHeight = 1;
//    protected int NPCSEBASTIANEntranceX;
//    protected int NPCSEBASTIANEntranceY;

    // Abigail
    protected static int NPCABIGAILTopLeftX = 500;
    protected static int NPCABIGAILTopLeftY = 100;
//    protected int NPCABIGAILWidth;
//    protected int NPCABIGAILHeight;
//    protected int NPCABIGAILEntranceX;
//    protected int NPCABIGAILEntranceY;

    // Harvey
    protected static int NPCHARVEYTopLeftX = 520;
    protected static int NPCHARVEYTopLeftY = 100;
//    protected int NPCHARVEYWidth;
//    protected int NPCHARVEYHeight;
//    protected int NPCHARVEYEntranceX;
//    protected int NPCHARVEYEntranceY;

    // Leah
    protected static int NPCLEAHTopLeftX = 480;
    protected static int NPCLEAHTopLeftY = 460;
//    protected int NPCLEAHWidth;
//    protected int NPCLEAHHeight;
//    protected int NPCLEAHEntranceX;
//    protected int NPCLEAHEntranceY;

    // Robin
    protected static int NPCROBINTopLeftX = 520;
    protected static int NPCROBINTopLeftY = 460;
//    protected int NPCROBINWidth;
//    protected int NPCROBINHeight;
//    protected int NPCROBINEntranceX;
//    protected int NPCROBINEntranceY;

    protected static int NPCSEBASTIANx = 480;
    protected static int NPCSEBASTIANy = 100;
    protected static int NPCABIGAILx = 500;
    protected static int NPCABIGAILy = 100;
    protected static int NPCHARVEYx = 520;
    protected static int NPCHARVEYy = 100;
    protected static int NPCLEAHx = 480;
    protected static int NPCLEAHy = 460;
    protected static int NPCROBINx = 520;
    protected static int NPCROBINy = 460;

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
    protected ArrayList<Trade> trades = new ArrayList<>();
    private final List<MarriageProposal> marriageProposals = new ArrayList<>();
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
}
