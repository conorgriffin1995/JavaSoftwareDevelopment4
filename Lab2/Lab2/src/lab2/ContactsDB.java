package lab2;

import oracle.jdbc.pool.OracleDataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ContactsDB {
    
    private Connection conn;
    private Statement stmt;
    private ResultSet rset;

    public void openDB() {
        Scanner in = new Scanner(System.in);
        try {
           
            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");               
            ods.setUser("X0011602");
            ods.setPassword("06Sep95");          

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (SQLException e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    
    public void dropTable() {
        
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            try {
                // Drop the Coffee table.
                stmt.execute("DROP TABLE Contacts");
                System.out.println("Contact table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
    
    public void buildContactsTable(){
        
        try {
            // Get a Statement object.
            stmt = conn.createStatement();

            // Create the table.
            stmt.executeUpdate("CREATE TABLE Contacts "
                    + "(ID VARCHAR(3) NOT NULL PRIMARY KEY, Name VARCHAR(15), Address VARCHAR(30), Phone VARCHAR(11), Email VARCHAR(30))");

            // Insert row #1.
            stmt.execute("INSERT INTO Contacts VALUES (1 "
                    + "'Peter', " + "'23 Lime Lane', " + "'018976543', " + "'p.cassidy@b.com'");

            // Insert row #2.
            stmt.execute("INSERT INTO Contacts VALUES (2 "
                    + "'Donal', " + "'2 Shelbourne rd', " + "'012446578', " + "'d.oreilly@b.com'");

            // Insert row #3.
            stmt.execute("INSERT INTO Contacts VALUES (3 "
                    + "'Mary', " + "'4 Richmond rd', " + "'019887654', " + "'m.lawlor@b.com'");

            // Insert row #4.
            stmt.execute("INSERT INTO Contacts VALUES (4 "
                    + "'Glen', " + "'4 Richmond Lane', " + "'011223876', " + "'g.whelan@b.com'");             

            System.out.println("Contacts table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    
    public void showDB(){
        
        String sqlStatement = "Select * From Contacts";
        try{
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sqlStatement);
            System.out.println("");
        while (rset.next()) {
                System.out.printf("%25s %10s %5.2f\n",
                        rset.getString("ID"),
                        rset.getString("Name"),
                        rset.getString("Address"),
                        rset.getString("Phone"),
                        rset.getString("Email"));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
    
    public void queryDB() {
        String sqlStatement1 = "SELECT COUNT(ID) FROM Contacts";
        
        String sqlStatement2 = "SELECT Phone FROM Contacts WHERE Name = 'Peter'";
        
        String sqlStatement3 = "SELECT * FROM Contacts ORDER BY Name";

        try {
            // Query 1
            stmt = conn.createStatement();

            rset = stmt.executeQuery(sqlStatement1);
            while (rset.next()) {
                System.out.printf("==============Query1============\n \tThe total number of records in the table is:",
                        rset.getInt(1));                        
            }
            
            // Query 2
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sqlStatement2);
            while(rset.next()) {
                System.out.printf("==============Query2=============\n \tThe phone number for Peter is: ",
                        rset.getInt(1));
            }
            
            // Query 3  
            stmt = conn.createStatement();
            
            rset = stmt.executeQuery(sqlStatement3);
            
            while(rset.next()){
                System.out.printf("==============Query3=============\n",
                        rset.getString("ID"),
                        rset.getString("Name"),
                        rset.getString("Address"),
                        rset.getString("Phone"),
                        rset.getString("Email"));
            }
            
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    public void insert(String ID, String Name, String Address, String Phone, String Email){
        
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO Contacts "
                    + "(ID, Name, Address, Phone, Email) "
                    + "VALUES ("
                    + ID + ",' "
                    + Name + "','"
                    + Address +"','" 
                    + Phone + "','"
                    + Email +"')";
        
            // Send the statement to the DBMS.
            int rows = stmt.executeUpdate(sqlStatement);
            
        }catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }            
            
        
    }
    
    public void closeDB() {
        try {
            stmt.close();
            //rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    
}
