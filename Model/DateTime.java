package Model;

import enums.Seasons;

public class DateTime
{
    protected int Hour;
    protected int Day;

    public DateTime(int hour, int day) {
        Hour = hour;
        Day = day;
    }

    public int getDay() {
        return Day;
    }

    public void setDay(int day) {
        this.Day = day;
    }

    public int getHour() {
        return Hour;
    }

    public void setHour(int hour) {
        this.Hour = hour;
    }

    public String getSeason(){
        int day = Day % 112;
        int season = day / 28;
        switch(season){
            case 0:
                return Seasons.Spring.toString();
            case 1:
                return Seasons.Summer.toString();
            case 2:
                return Seasons.Fall.toString();
            case 3:
                return Seasons.Winter.toString();
        }
        return "";
    }
}