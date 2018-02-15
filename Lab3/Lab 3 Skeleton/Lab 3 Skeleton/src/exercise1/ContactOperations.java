package exercise1;

import java.sql.*; // Needed for JDBC classes

import oracle.jdbc.pool.OracleDataSource;

public class ContactOperations {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rset;

    public void openDB() {

        try {
            // Load the Oracle JDBC driver
            OracleDataSource ods = new OracleDataSource();

            ods.setURL("jdbc:oracle:thin:@//10.10.2.7:1521/global1");
            ods.setUser("X00111602");
            ods.setPassword("db06Sep95");           

            conn = ods.getConnection();
            System.out.println("connected.");
        } catch (Exception e) {
            System.out.print("Unable to load driver " + e);
            System.exit(1);
        }
    }

    public void buildContactsTable() {
        try {
            String sql1 = "CREATE TABLE Contacts "
                    + "(id NUMBER PRIMARY KEY,name VARCHAR2(35) , address VARCHAR2(35),pnumber  VARCHAR2(20), email VARCHAR2(30))";
            // Get a Statement object.
            pstmt = conn.prepareStatement(sql1);
            // Create the table.
            pstmt.executeUpdate();

            String sql2 = "INSERT INTO Contacts VALUES (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sql2);

            // Insert row #1.
            pstmt.setInt(1, 1);
            pstmt.setString(2, "Peter");
            pstmt.setString(3, "23 Lime Lane");
            pstmt.setString(4, "018976543");
            pstmt.setString(5, "p.cassidy@b.com");
            pstmt.executeUpdate();

            // Insert row #2.
            pstmt.setInt(1, 2);
            pstmt.setString(2, "Donal");
            pstmt.setString(3, "2 Shelbourne rd");
            pstmt.setString(4, "012446578\t");
            pstmt.setString(5, "d.oreilly@b.com");
            pstmt.executeUpdate();

            // Insert row #3.
            pstmt.setInt(1, 3);
            pstmt.setString(2, "Mary");
            pstmt.setString(3, "4 Richmond rd");
            pstmt.setString(4, "019887654");
            pstmt.setString(5, "m.lawlor@b.com");
            pstmt.executeUpdate();

            // Insert row #4.
            pstmt.setInt(1, 4);
            pstmt.setString(2, "Glen");
            pstmt.setString(3, "4 Richmond Lane");
            pstmt.setString(4, "011223876");
            pstmt.setString(5, "g.whelan@b.com");
            pstmt.executeUpdate();

            System.out.println("Contacts table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }

    public void queryDB() {
        String sqlStatement = "SELECT * FROM Contacts";
        try {
            pstmt = conn.prepareStatement(sqlStatement);
            // Send the statement to the DBMS.
            rset = pstmt.executeQuery(sqlStatement);

			// Display the contents of the result set.
            // The result set will have three columns.
            while (rset.next()) {
                System.out.printf("%-2d %-10s %-30s %-15s%-15s\n",
                        rset.getInt(1),
                        rset.getString(2),
                        rset.getString(3),
                        rset.getString(4), 
                        rset.getString(5));
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }

      
    }
    
   
    public void dropTables() {
        System.out.println("Checking for existing tables.");

        try {
            // Get a Statement object.
            pstmt = conn.prepareStatement("DROP TABLE Contacts");

            try {
                // Drop the Contacts table.
                pstmt.execute();
                System.out.println("Contacts table dropped.");
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }
    
    public void add(int id, String name, String address, String phone, String email ){
        try{
            // Create a string with an ADD statement.       
            String sqlStatement = "INSERT INTO Contacts VALUES (?,?,?,?,?)";
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, address);
            pstmt.setString(4, phone);
            pstmt.setString(5, email);
            pstmt.executeUpdate();
            
            //Display the results
            System.out.println("Row added to the table.");
            
        }catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }
        
    }
    
    public void updateRecord(int ID, String phone){
        try{
            // Create a string with an UPDATE statement.
            String sqlStatement = "UPDATE Contact SET Phone = ?"+
                                  "WHERE id = ?" ;
            
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setString(1, phone);
            pstmt.setInt(2, ID);
            pstmt.executeUpdate();
            
            System.out.println(" row added to the table.");
        } catch(SQLException ex){
            System.out.println("ERROR: " + ex.getMessage());
        }  
            
        
    }
    
    public void deleteRecord(int ID){
        try{                   
            // Create a string with an DELETE statement.
            String sqlStatement = "Delete FROM Contact WHERE id = ?";
        
            pstmt = conn.prepareStatement(sqlStatement);
            pstmt.setInt(1, ID);
            pstmt.executeUpdate();
            
            //Display the results.
            System.out.println(" row deleted from the table.");
        } catch(SQLException ex){
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
