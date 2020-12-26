import java.util.ArrayList;


public class ReservationEntry {
    private String faculty;
    private String room;
    private String date;
    private int seats;
    private static ArrayList<String> reservations = new ArrayList<>();
    
    public ReservationEntry() {
        
    }
    
    public ReservationEntry(String faculty, String room, String date, int seats) {
        this.faculty = faculty;
        this.room = room;
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

    public String getRoom() {
        return room;
    }
    
    public static ArrayList<String> getReservations() {
        return reservations;
    }
    
    @Override
    public String toString() {
        return faculty + ": room " + room + " with " + seats + " seats" + " on " + date;
    }
    
    public void setReservationEntry(String faculty, String room, String date, int seats) {
        this.faculty = faculty;
        this.room = room;
        this.date = date;
        this.seats = seats;
    }

}