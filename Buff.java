package Model;
public class Buff
{
    protected int EnergyIncrease;
    protected int BuffHours;
    protected Skill BuffSkill;
    // Getter and Setter for EnergyIncrease
    public int getEnergyIncrease() {
        return EnergyIncrease;
    }

    public void setEnergyIncrease(int EnergyIncrease) {
        this.EnergyIncrease = EnergyIncrease;
    }

    // Getter and Setter for BuffHours
    public int getBuffHours() {
        return BuffHours;
    }

    public void setBuffHours(int BuffHours) {
        this.BuffHours = BuffHours;
    }

    // Getter and Setter for BuffSkill
    public Skill getBuffSkill() {
        return BuffSkill;
    }

    public void setBuffSkill(Skill BuffSkill) {
        this.BuffSkill = BuffSkill;
    }

}
