package guestbook.model;

import java.sql.*;
import java.io.*;

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
            } catch(Exception e) {
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

    public ResultSet getResultSet() {
        ResultSet resultset = null;
        try {
            resultset = statement.executeQuery("select * from Users");
        } catch (SQLException ex) {
            ex.getMessage();
        }
        return resultset;
    } //getResultSet

    public void update(String name, String mail, String msg, String ip) {
		/*String command = "insert into Users values ('" + ip + "', '" + name
				+ "', '" + msg + "', default, '" + mail + "')";*/
        String command = "insert into Users(IPAddr,Name,Message,DATE,Email) values ('" + ip + "', '" + name
                + "', '" + msg + "', default, '" + mail + "')";

        try {
            statement.executeUpdate(command);
        } catch (com.microsoft.sqlserver.jdbc.SQLServerException ex) {
            checkmail = true;
            ex.getMessage();
        } catch (SQLException ex) {
            checkmail = true;
            ex.getMessage();
        }
    } //update
}
