import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;


public class WaitlistQueries {
    private static Connection connection;
    private static PreparedStatement getWaitlist;
    private static PreparedStatement getWaitlistBySeats;
    private static PreparedStatement getWaitlistBySeatsAndDate;
    private static PreparedStatement addWaitlistEntry;
    private static PreparedStatement getWaitlistByFaculty;
    private static PreparedStatement deleteWaitlistEntry;
    private static ResultSet resultSet;
    
    public static void addWaitlistEntry(String faculty,  String date, int seats) {
        // add a new waitlist entry
        connection = DBConnection.getConnection();
        java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        try {
            addWaitlistEntry = connection.prepareStatement("insert into waitlist (faculty, date, seats, timestamp) values (?,?,?,?)");
            addWaitlistEntry.setString(1, faculty);
            addWaitlistEntry.setString(2, date);
            addWaitlistEntry.setInt(3, seats);
            addWaitlistEntry.setTimestamp(4, currentTimestamp);
            addWaitlistEntry.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
    
    public static ArrayList<WaitlistEntry> getWaitlist() {
        // get all waitlist entries
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlist = connection.prepareStatement("select faculty, date, seats from waitlist order by timestamp");
            resultSet = getWaitlist.executeQuery();
            while (resultSet.next()) {
                waitlist.add(new WaitlistEntry(
                        resultSet.getString("faculty"), 
                        resultSet.getString("date"),
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
    
     public static ArrayList<WaitlistEntry> getWaitlistBySeats(int seats) {
        // get waitlist entry by seats
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlistBySeats = connection.prepareStatement("select faculty, date, seats from waitlist where seats <= (?) order by timestamp");
            getWaitlistBySeats.setInt(1, seats);
            resultSet = getWaitlistBySeats.executeQuery();
            while (resultSet.next()) {
                waitlist.add(new WaitlistEntry(
                        resultSet.getString("faculty"), 
                        resultSet.getString("date"),
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
     
     public static WaitlistEntry getWaitlistBySeatsAndDate(int seats, String date) {
        // get waitlist entry by seats and date
        connection = DBConnection.getConnection();
        WaitlistEntry waitlist = new WaitlistEntry();
        try {
            getWaitlistBySeatsAndDate = connection.prepareStatement("select faculty, date, seats from waitlist where seats <= (?) and date = (?) order by timestamp");
            getWaitlistBySeatsAndDate.setInt(1, seats);
            getWaitlistBySeatsAndDate.setString(2, date);
            resultSet = getWaitlistBySeatsAndDate.executeQuery();
            if (resultSet.next()) {
                waitlist.setWaitlistEntry(
                        resultSet.getString("faculty"), 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats"));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        
        return waitlist;
    }
     
    public static ArrayList<WaitlistEntry> getWaitlistByFaculty(String faculty) {
        // get waitlist entry by faculty
        connection = DBConnection.getConnection();
        ArrayList<WaitlistEntry> waitlist = new ArrayList<>();
        try {
            getWaitlistByFaculty = connection.prepareStatement("select faculty, date, seats from waitlist where faculty = (?) order by timestamp");
            getWaitlistByFaculty.setString(1, faculty);
            resultSet = getWaitlistByFaculty.executeQuery();
            while (resultSet.next()) {
                waitlist.add(new WaitlistEntry(
                        resultSet.getString("faculty"), 
                        resultSet.getString("date"), 
                        resultSet.getInt("seats")));
            }
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
        System.out.println(waitlist);
        return waitlist;
    }
     
   
     
    public static void deleteWaitlistEntry(String faculty, String date) {
        // delete waitlist entry, given faculty name and date
        connection = DBConnection.getConnection();
        try {
            System.out.printf("Faculty: %s, date: %s\n", faculty, date);
            deleteWaitlistEntry = connection.prepareStatement("delete from waitlist where faculty = (?) and date = (?)");
            deleteWaitlistEntry.setString(1, faculty);
            deleteWaitlistEntry.setString(2, date);
            deleteWaitlistEntry.executeUpdate();
        } catch (Exception sqlException) {
            sqlException.printStackTrace();
        }
    }
}
