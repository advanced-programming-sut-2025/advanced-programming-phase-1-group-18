package Model;
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
}
