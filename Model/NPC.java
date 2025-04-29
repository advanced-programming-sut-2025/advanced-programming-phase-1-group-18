package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import Model.Items.Item;
public class NPC {
    protected String name;
    protected String job;
    protected ArrayList<String> raftars;
    protected ArrayList<String> alayeghs;
    protected ArrayList<String> salayeghs;
    protected ArrayList<Item> favorites;
    protected String livePlace;
    protected int friendshipLevel;
    public Result meetNPC(String npcName)
    {
        return new Result(true, "");
    }
    public Result giftNPC(String npcName,String item)
    {
        return new Result(true, "");
    }
    public void friendshipList()
    {

    }
    public void questsList()
    {

    }
    public Result questsFinish(int index)
    {
        return new Result(true, "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public ArrayList<String> getRaftars() {
        return raftars;
    }

    public void setRaftars(ArrayList<String> raftars) {
        this.raftars = raftars;
    }

    public ArrayList<String> getAlayeghs() {
        return alayeghs;
    }

    public void setAlayeghs(ArrayList<String> alayeghs) {
        this.alayeghs = alayeghs;
    }

    public ArrayList<String> getSalayeghs() {
        return salayeghs;
    }

    public void setSalayeghs(ArrayList<String> salayeghs) {
        this.salayeghs = salayeghs;
    }

    public ArrayList<Item> getFavorites() {
        return favorites;
    }

    public void setFavorites(ArrayList<Item> favorites) {
        this.favorites = favorites;
    }

    public String getLivePlace() {
        return livePlace;
    }

    public void setLivePlace(String livePlace) {
        this.livePlace = livePlace;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
