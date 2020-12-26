import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class WaitlistEntry {
    private String faculty;
    private String date;
    private int seats;
    private static ArrayList<String> waitlist = new ArrayList<>();
    
    public WaitlistEntry() {

    }
    
    public WaitlistEntry(String faculty, String date, int seats) {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public String getDate() {
        return date;
    }

    public String getFaculty() {
        return faculty;
    }
    
    public static ArrayList<String> getWaitlist() {
        return waitlist;
    }
    
    public void setWaitlistEntry(String faculty, String date, int seats) {
        this.faculty = faculty;
        this.date = date;
        this.seats = seats;
    }
    
    @Override
    public String toString() {
        return faculty + ": room with " + seats + " seats on " + date;
    }
}