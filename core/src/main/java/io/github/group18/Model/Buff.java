package io.github.group18.Model;

import io.github.group18.enums.SkillEnum;

public class Buff
{
    public int EnergyIncrease;
    public int BuffHours;
    public SkillEnum BuffSkillType;
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

    public SkillEnum getBuffSkillType() {
        return BuffSkillType;
    }

    public void setBuffSkillType(SkillEnum buffSkillType) {
        BuffSkillType = buffSkillType;
    }
}
