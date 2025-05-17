package Model;

import java.util.ArrayList;

public class Friendship
{
    protected Player player1;
    protected Player player2;
    protected int xp; // experience points for this friendship
    protected int level;
    protected DateTime lastInteractionDate;
    protected ArrayList<String> talkHistory = new ArrayList<>();

    public Friendship(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.xp = 0;
        this.level = 0;
        this.lastInteractionDate = new DateTime(0,0);
    }

    public boolean isBetween(Player a, Player b) {
        return (a == player1 && b == player2) || (a == player2 && b == player1);
    }

    public void interact(int xpChange) {
        if (xpChange > 0 && isFirstInteractionToday()) {
            this.xp += xpChange;
        } else if (xpChange < 0) {
            this.xp += xpChange; // negative change allowed multiple times
        }

        checkLevelChange();
        lastInteractionDate = App.getCurrentGame().getCurrentDateTime();
    }

    public boolean isFirstInteractionToday() {
        return App.getCurrentGame().getCurrentDateTime().getDay()!=lastInteractionDate.getDay();
    }

    public void updateDecay() {
        if (App.getCurrentGame().getCurrentDateTime().getDay() != lastInteractionDate.getDay()+1) {
            if (xp == 0 && level > 1) {
                level--;
                xp = maxXpForLevel(level) - 10;
            } else {
                xp = Math.max(0, xp - 10);
            }
        }
    }

    public void checkLevelChange() {
        int max = maxXpForLevel(level);
        if (xp >= max) {
            level++;
            xp -= max;
        } else if (xp < 0 && level > 1) {
            level--;
            xp = maxXpForLevel(level) - 10;
        }
    }

    public int maxXpForLevel(int level) {
        return (this.getLevel()+1)*100;
//        return 100 + (level - 1) * 50;
    }

    public ArrayList<String> getTalkHistory() {
        return talkHistory;
    }

    public void setTalkHistory(ArrayList<String> talkHistory) {
        this.talkHistory = talkHistory;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public DateTime getLastInteractionDate() {
        return lastInteractionDate;
    }

    public void setLastInteractionDate(DateTime lastInteractionDate) {
        this.lastInteractionDate = lastInteractionDate;
    }

}