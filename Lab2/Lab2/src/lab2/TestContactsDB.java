package lab2;

import java.util.Scanner;


public class TestContactsDB {
    
    public static void main(String[] args) {
        
        String ID,Name,Address,Phone,Email;
        
        ContactsDB cdb = new ContactsDB();
        cdb.openDB();
        cdb.dropTable();
        cdb.buildContactsTable();
        cdb.showDB();
        cdb.queryDB();
        
        Scanner in = new Scanner(System.in);
                System.out.println("New contact\n Enter ID: ");
                ID = in.nextLine();
                System.out.println("Enter Name: ");
                Name = in.nextLine();
                System.out.println("Enter Address: ");
                Address = in.nextLine();
                System.out.println("Enter Phone: ");
                Phone = in.nextLine();
                System.out.println("Enter Email: ");
                Email = in.nextLine();
                
                cdb.insert(ID,Name,Address,Phone,Email);
                cdb.showDB();
                cdb.closeDB();
       
        
    }
    
}
