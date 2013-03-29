package guestbook.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.*;
import java.util.*;

public class GuestbookModel {

    private static GuestbookModel con;
    private Connection connection;
    private Statement statement;
    boolean checkmail;

    private GuestbookModel() {
    }

    public static GuestbookModel getInstance() {
        if (con == null)
            con = new GuestbookModel();
        return con;
    }

    public void MsSQLinit(String driver, String db, String user, String pass) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(db, user, pass);
            checkmail = false;
            statement = connection.createStatement();
        } catch (Exception ex) {
            try {
                BufferedWriter br = new BufferedWriter(new FileWriter("logs"));
                br.write(ex.getMessage());
                br.close();
            } catch (Exception e) {
            }
            ex.printStackTrace();

        }
    } // init

    public void destroy() {
        try {
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } // destroy

    public boolean getCheckmail() {
        return checkmail;
    }

    public List<Map<String,String>> getMessageList()  {
        ResultSet resultset = null;
        List<Map<String,String>> result= new ArrayList<Map<String,String>>();
        try {
            resultset = statement.executeQuery("select * from Users");
            while (resultset.next())  {
                Map<String,String> map = new LinkedHashMap<String, String>();
                map.put("Date",resultset.getString("Date"));
                map.put("Name",resultset.getString("Name"));
                map.put("Ip",resultset.getString("IPAddr"));
                map.put("Email",resultset.getString("Email"));
                map.put("Message",resultset.getString("Message"));
                result.add(map);
            }
        } catch(SQLException ex) {
            ex.getMessage();
        }
        return result;
    }

    public void update(String name, String mail, String msg, String ip) {
        String command = "insert into Users(IPAddr,Name,Message,DATE,Email) values ('"
                + ip + "', '" + name + "', '" + msg + "', default, '" + mail + "')";

        try {
            statement.executeUpdate(command);
        } catch (SQLException ex) {
            checkmail = true;
            ex.getMessage();
        }
    } //update
}
