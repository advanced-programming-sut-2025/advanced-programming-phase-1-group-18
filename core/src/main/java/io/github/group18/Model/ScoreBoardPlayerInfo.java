package io.github.group18.Model;

public class ScoreBoardPlayerInfo {
    String username;
    int skill;
    int gold;

    public ScoreBoardPlayerInfo(String username, int skill, int gold) {
        this.username = username;
        this.skill = skill;
        this.gold = gold;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }
}
