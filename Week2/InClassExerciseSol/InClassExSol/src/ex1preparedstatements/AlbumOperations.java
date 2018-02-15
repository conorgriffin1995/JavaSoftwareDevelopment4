package ex1preparedstatements;

import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pmage_000
 */
public class AlbumOperations {
    private Connection conn;
    private ResultSet rset;
    private PreparedStatement pstmt;
    private Statement stmt;

    // This method opens a connection to the Oracle database
    public void openDB() {
        try {
            OracleDataSource ods = new OracleDataSource();

	    // Tallaght
            // ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
            // ods.setUser("");
            // ods.setPassword("");
            
            // Home Oracle XE
            ods.setURL("jdbc:oracle:thin:HR/pmagee@localhost:1521:XE");
            ods.setUser("hr");
            ods.setPassword("passhr");

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }
    public void dropSequence() {
        String s1 = "drop sequence a_seq";
        System.out.println("Checking for existing sequences.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Sequence dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The sequence simply did not exist.
        }
    }
    public void dropTable() {
        String s1 = "DROP TABLE Album";
        System.out.println("Checking for existing tables.");

        try {
            pstmt = conn.prepareStatement(s1);
            // Drop the Contacts table.
            pstmt.execute();
            System.out.println("Album table dropped.");
        } catch (SQLException ex) {
            // No need to report an error.
            // The table simply did not exist.
        }
    }
     public void CreateAlbumTable() {
        try {

            // Create a Table
            String create = "CREATE TABLE Album" +
                     "(id NUMBER PRIMARY KEY, title VARCHAR2(40), artist VARCHAR2(30),price DECIMAL(5,2))";
            stmt = conn.createStatement();
            stmt.executeUpdate(create);

            // Creating a sequence
            String createseq = "create sequence a_seq increment by 1 start with 1";
            stmt = conn.createStatement();
            stmt.executeUpdate(createseq);

			// Insert data into table
            String insertString1 = "INSERT INTO Album(id,title,artist,price) "
                    + "values(a_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(insertString1);
            pstmt.setString(1, "Strangeland");
            pstmt.setString(2, "Keane");
            pstmt.setDouble(3, 9.99);
            pstmt.execute();
            
            pstmt.setString(1, "Hopes and Fears");
            pstmt.setString(2, "Keane");
            pstmt.setDouble(3, 8.99);
            pstmt.execute();
            
            pstmt.setString(1, "Parachutes");
            pstmt.setString(2, "ColdPlay");
            pstmt.setDouble(3, 10.99);
            pstmt.execute();
            
            pstmt.setString(1, "Pure Heroine");
            pstmt.setString(2, "Lorde");
            pstmt.setDouble(3, 8.99);
            pstmt.execute();
            
            pstmt.setString(1, "Second Coming");
            pstmt.setString(2, "Stone Roses");
            pstmt.setDouble(3, 7.99);
            pstmt.execute();
            
            pstmt.setString(1, "Golden Greats");
            pstmt.setString(2, "Ian Brown");
            pstmt.setDouble(3, 5.99);
            pstmt.execute();
        }
        catch (SQLException e) {
            System.out.print("SQL Exception " + e);
            System.exit(1);
        }
     }
     public void queryDB() {
        String sqlStatement = "SELECT * FROM Album";
        try {
            // Send the statement to the DBMS.
            stmt = conn.createStatement();
            rset = stmt.executeQuery(sqlStatement);

	    // Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%2d %-20s%-20s%5.2f\n",
                        rset.getInt(1),
                        rset.getString(2), rset.getString(3),rset.getDouble(4));
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
      public void updateRecord(int prodID, double price) {
        try {
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Album SET Price = ?"+
                                  "WHERE id = ?" ;

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setDouble(1, price);
            pstmt.setInt(2, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row updated in the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
       public void deleteRecord(int prodID) {
        try {
            // Create a string with an DELETE statement.
            String sqlStatement = "DELETE FROM album WHERE id = ?";

            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, prodID);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println(" row deleted from the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
       public void add(String t, String a, double p) {
        try {
            // Create a string with an INSERT statement.
            String sqlStatement = "INSERT INTO album VALUES (a_seq.nextVal,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, t);
            pstmt.setString(2, a);
            pstmt.setDouble(3, p);
            pstmt.executeUpdate();

            // Display the results.
            System.out.println("Row added to the table.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
     public void closeDB() {
        try {
            pstmt.close();
            rset.close();
            conn.close();
            System.out.print("Connection closed");
        } catch (SQLException e) {
            System.out.print("Could not close connection ");
        }
    }
    
}
