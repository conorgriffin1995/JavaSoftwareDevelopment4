package exercise2AR;

import java.sql.*; // Needed for JDBC classes

import oracle.jdbc.pool.OracleDataSource;

public class ContactOperations {

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rset;
    private Statement stmt;

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
        int val = 0;
        try{
            String seq = "SELECT a_seq.nextVal from Contact";
            ResultSet rs = stmt.executeQuery(seq);
            if(rs.next()){
                val = rs.getInt(1);
                System.out.println(val);
            }
            
            // Create a string with an ADD statement.       
            String queryString = "select a_seq.nextVal from Contacts";
            pstmt = conn.prepareStatement(queryString,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rset = pstmt.executeQuery();
            
            rset.moveToInsertRow();
            rset.updateInt(1, id);
            rset.updateString(2, name);
            rset.updateString(3, address);
            rset.updateString(4, phone);
            rset.updateString(5, email);
            rset.insertRow();
            
            //Display the results
            System.out.println("Row added to the table.");
            
        }catch(SQLException e2){
            System.out.println("Error going to previous row");
            System.exit(1);
        }
        
    }
    
    public void updateRecord(int id, String phone){
        try {
            String queryString = "select id,name,address,phone,email from Contact where id = "+id;
            pstmt = conn.prepareStatement(queryString,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            rset = pstmt.executeQuery();
            
            if(rset.next()) //try to go to 1st row
            {
                rset.updateString(4, phone);
                System.out.println("Updating..");
                rset.updateRow();
            } else{
                System.out.println("Record not found");
            }
            System.out.println("Record Updated");
        } catch(SQLException e){
            System.out.print("SQL Exception - Record could not be updated" + e);
            System.exit(1);
        }
        
    }
    
    public void deleteRecord(int id){
        try{
            String deleteString = "select id, name, address, phone, email from Contact where id =" +id;
            pstmt = conn.prepareStatement(deleteString,
                    ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            
            rset = pstmt.executeQuery();
            if(rset.next())
            {
                System.out.println("Deleting..");
                rset.deleteRow();
                System.out.println("Row deleted");
            }
            else
            {
                System.out.println("Record not found");
            }
        } catch(SQLException e){
            System.out.print("SQL Exception - error deleting record" + e);
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
