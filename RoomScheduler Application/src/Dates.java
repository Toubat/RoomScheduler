import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dates
{
    private static Connection connection;
    private static ArrayList<Date> date = new ArrayList<>();
    private static PreparedStatement addDate;
    private static PreparedStatement getDateList;
    private static ResultSet resultSet;
    
    public static void addDate(int year, int month, int day) {
        connection = DBConnection.getConnection();
        try
        {
            
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setString(1, "" + year + "-" + month + "-" + day);
            addDate.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllDates() {
        // get all dates
        connection = DBConnection.getConnection();
        ArrayList<String> date = new ArrayList<>();
        try
        {
            getDateList = connection.prepareStatement("select date from dates order by date");
            resultSet = getDateList.executeQuery();
            
            while(resultSet.next())
            {
                date.add(resultSet.getDate(1).toString());
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return date;
        
    }
    
    public static void addDate(String date) {
        // add a new date
        connection = DBConnection.getConnection();
        try {
            addDate = connection.prepareStatement("insert into dates (date) values (?)");
            addDate.setString(1, date);
            addDate.executeUpdate();
        } catch(SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
