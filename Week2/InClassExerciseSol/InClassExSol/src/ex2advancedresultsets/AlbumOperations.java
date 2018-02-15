package ex2advancedresultsets;

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
            String createseq = "create sequence a_seq increment by 1 start with 1 NOCACHE";
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

      public void updateRecord(int id, double price) {
        try {
            String queryString = "select id, title,artist,price from album where id = "+id ;
            pstmt = conn
                    .prepareStatement(queryString,
                            ResultSet.TYPE_SCROLL_INSENSITIVE,
                            ResultSet.CONCUR_UPDATABLE);
            rset = pstmt.executeQuery();

            if (rset.next()) // try to go to 1st row
            {
                rset.updateDouble(4, price);
                System.out.println("Updating..");
                rset.updateRow();
            } else {
                System.out.println("Record not found");
            }
            System.out.println("Record updated");
        } catch (SQLException e) {
            System.out.print("SQL Exception - Record could not be updated" + e);
            System.exit(1);
        }
    }
       public void deleteRecord(int id) {
        try {
            String deleteString = "select id, title,artist,price from album where id ="+id;
            pstmt = conn.prepareStatement(deleteString,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);

            rset = pstmt.executeQuery();
            if (rset.next()) // try to go to row
            {
                System.out.println("Deleting..");
                rset.deleteRow();  // delete the row from the database and result set
                System.out.println("Row deleted");
            }
            else
            {
                System.out.println("Record not found");
            }
        } catch (SQLException e) {
            System.out.print("SQL Exception - error deleting record" + e);
        }
    }
      public void add(String t, String a, double p) {
        int val = 0;
        try {
            String seq = "select a_seq.nextVal from album";
            ResultSet rs = stmt.executeQuery(seq);
            if (rs.next()) {
                val = rs.getInt(1);
                System.out.println(val);
            }

            String queryString = "select id, title,artist,price from album";
            pstmt = conn.prepareStatement(queryString,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rset = pstmt.executeQuery();

            rset.moveToInsertRow();
            rset.updateInt(1, val);
            rset.updateString(2, t);
            rset.updateString(3, a);
            rset.updateDouble(4, p);
            rset.insertRow();

        } catch (SQLException e2) {
            System.out.println("Error going to previous row");
            System.exit(1);
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
