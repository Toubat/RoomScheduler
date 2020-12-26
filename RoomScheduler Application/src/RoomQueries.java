import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RoomQueries {
    private static Connection connection;
    private static PreparedStatement getAllPossibleRooms;
    private static PreparedStatement addRoom;
    private static PreparedStatement dropRoom;
    private static ResultSet resultSet;
    
    public static ArrayList<String> getRooms() {
        // get all room entries
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<>();
        try {
            getAllPossibleRooms = connection.prepareStatement("select name from rooms");
            resultSet = getAllPossibleRooms.executeQuery();
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    
    public static ArrayList<RoomEntry> getRoomsBySeats(int seats) {
        // return a list of rooms with seats larger than or equal to SEATS.
        connection = DBConnection.getConnection();
        ArrayList<RoomEntry> rooms = new ArrayList<>();
        try {
            getAllPossibleRooms = connection.prepareStatement("select name, seats from rooms where seats >= (?) order by seats");
            getAllPossibleRooms.setInt(1, seats);
            resultSet = getAllPossibleRooms.executeQuery();
            while(resultSet.next())
            {
                String str = resultSet.getString("name");
                int seat = resultSet.getInt("seats");
                rooms.add(new RoomEntry(str, seat));
                System.out.println("DONE!");
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static void addRoom(String name, int seats) {
        // add a new room
        connection = DBConnection.getConnection();
        try {
            addRoom = connection.prepareStatement("insert into rooms (name, seats) values (?,?)");
            addRoom.setString(1, name);
            addRoom.setInt(2, seats);
            addRoom.executeUpdate();
            RoomEntry.getRooms().add(name);
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropRoom(String room) {
        // drop room
        connection = DBConnection.getConnection();
        try {
            dropRoom = connection.prepareStatement("delete from rooms where name = (?)");
            dropRoom.setString(1, room);
            dropRoom.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    
}
