
import java.util.ArrayList;


public class RoomEntry {
    private String name;
    private int seats;
    private static ArrayList<String> rooms = RoomQueries.getRooms();

    public RoomEntry(String name, int seats) {
        this.name = name;
        this.seats = seats;
    }

    public String getName() {
        return name;
    }

    public int getSeats() {
        return seats;
    }
    
    public static ArrayList<String> getRooms() {
        return rooms;
    }
}