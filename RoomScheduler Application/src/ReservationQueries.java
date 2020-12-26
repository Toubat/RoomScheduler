import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;

public class ReservationQueries {
    private static Connection connection;
    private static PreparedStatement getReservationsByDate;
    private static PreparedStatement getRoomsReservedByDate;
    private static PreparedStatement addReservationEntry;
    private static PreparedStatement getReservationsByFaculty;
    private static PreparedStatement getReservationByFacultyAndDate;
    private static PreparedStatement getReservationsByRoom;
    private static PreparedStatement hasReserved;
    private static PreparedStatement reservedOnDate;
    private static PreparedStatement deleteReservation;
    private static ResultSet resultSet;
    
    public static ArrayList<String> getRoomsReservedByDate(String dateString) {
        // return a list of rooms reserved in a specific date.
        connection = DBConnection.getConnection();
        ArrayList<String> rooms = new ArrayList<>();
        try {
            getRoomsReservedByDate = connection.prepareStatement("select room from reservations where date = (?)");
            getRoomsReservedByDate.setString(1, dateString);
            resultSet = getRoomsReservedByDate.executeQuery();
            while(resultSet.next())
            {
                rooms.add(resultSet.getString(1));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        return rooms;
    }
    
    public static void addReservationEntry(ReservationEntry entry) {
        // add a new reservation entry
        connection = DBConnection.getConnection();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try {
            addReservationEntry = connection.prepareStatement("insert into reservations (faculty, room, date, seats, timestamp) values (?,?,?,?,?)");
            addReservationEntry.setString(1, entry.getFaculty());
            addReservationEntry.setString(2, entry.getRoom());
            addReservationEntry.setString(3, entry.getDate());
            addReservationEntry.setInt(4, entry.getSeats());
            addReservationEntry.setTimestamp(5, currentTimestamp);
            addReservationEntry.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }

    public static ArrayList<ReservationEntry> getReservationsByDate(String dateString) {
        // get all reservations on a particular date
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservationsByDate = connection.prepareStatement("select faculty, room, date, seats from reservations where date = (?) order by faculty");
            getReservationsByDate.setString(1, dateString);
            resultSet = getReservationsByDate.executeQuery();
            while (resultSet.next()) {
                reservations.add(new ReservationEntry(
                        resultSet.getString("faculty"), 
                        resultSet.getString("room"), 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return reservations;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByFaculty(String name) {
        // get all reservations belonging to a faculty member
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservationsByFaculty = connection.prepareStatement("select room, date, seats from reservations where faculty = (?) order by timestamp");
            getReservationsByFaculty.setString(1, name);
            resultSet = getReservationsByFaculty.executeQuery();
            while (resultSet.next()) {
                reservations.add(new ReservationEntry(
                        name, 
                        resultSet.getString("room"), 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
     
        return reservations;
    }
    
    public static ReservationEntry getReservationByFacultyAndDate(String name, String date) {
        // get a reservation entry with specified name and date
        connection = DBConnection.getConnection();
        ReservationEntry entry = new ReservationEntry();
        try {
            getReservationByFacultyAndDate = connection.prepareStatement("select room, date, seats from reservations where faculty = (?) and date = (?)");
            getReservationByFacultyAndDate.setString(1, name);
            getReservationByFacultyAndDate.setString(2, date);
            resultSet = getReservationByFacultyAndDate.executeQuery();
            if (resultSet.next()) {
                entry.setReservationEntry(
                        name, 
                        resultSet.getString("room"), 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats")); 
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return entry;
    }
    
    public static ArrayList<ReservationEntry> getReservationsByRoom(String room) {
        // get all reservations for a particular room
        connection = DBConnection.getConnection();
        ArrayList<ReservationEntry> reservations = new ArrayList<>();
        try {
            getReservationsByRoom = connection.prepareStatement("select faculty, date, seats from reservations where room = (?) order by timestamp");
            getReservationsByRoom.setString(1, room);
            resultSet = getReservationsByRoom.executeQuery();
            while (resultSet.next()) {
                reservations.add(new ReservationEntry(
                        resultSet.getString("faculty"), 
                        room, 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
     
        return reservations;
    } 
    
    public static void deleteReservation(String name, String date) {
        // delete reservation, given room name and date
        connection = DBConnection.getConnection();
        try {
             deleteReservation = connection.prepareStatement("delete from reservations where room = (?) and date = (?)");
             deleteReservation.setString(1, name);
             deleteReservation.setString(2, date);
             deleteReservation.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static void deleteReservation(String name) {
        // delete reservation, given room name
        connection = DBConnection.getConnection();
        try {
             deleteReservation = connection.prepareStatement("delete from reservations where room = (?)");
             deleteReservation.setString(1, name);
             deleteReservation.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static boolean roomHasReserved(String name, String date) {
        // check if a room has been reserved on a specific date
        connection = DBConnection.getConnection();
        boolean isAvailable = true;
        try {
            hasReserved = connection.prepareStatement("select room from reservations where room = (?) and date = (?)");
            hasReserved.setString(1, name);
            hasReserved.setString(2, date);
            resultSet = hasReserved.executeQuery();
            if (resultSet.next()) {
                isAvailable = true;
            } else {
                isAvailable = false;
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return isAvailable;
    }
    
    public static boolean reservedOnDate(String faculty, String date) {
        // check if faculty has reserved room on a specific date
        connection = DBConnection.getConnection();
        boolean reserved = true;
        try {
            reservedOnDate = connection.prepareStatement("select room from reservations where faculty = (?) and date = (?)");
            reservedOnDate.setString(1, faculty);
            reservedOnDate.setString(2, date);
            resultSet = reservedOnDate.executeQuery();
            if (resultSet.next()) {
                reserved = true;
            } else {
                reserved = false;
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return reserved;
    }
    
  
}
