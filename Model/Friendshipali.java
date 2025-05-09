package Model;

public class Friendshipali {
    private Player player;
    private int friendshipLevel;

    public Friendshipali(Player player, int friendshipLevel) {
        this.player = player;
        this.friendshipLevel = friendshipLevel;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getFriendshipLevel() {
        return friendshipLevel;
    }

    public void setFriendshipLevel(int friendshipLevel) {
        this.friendshipLevel = friendshipLevel;
    }
}
