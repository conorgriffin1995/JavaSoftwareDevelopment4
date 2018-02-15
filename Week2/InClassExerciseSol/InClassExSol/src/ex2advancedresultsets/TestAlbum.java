/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2advancedresultsets;

import java.util.Scanner;

/**
 *
 * @author pmage_000
 */
public class TestAlbum {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);

        AlbumOperations ao = new AlbumOperations();
        ao.openDB();
        ao.dropSequence();
        ao.dropTable();
        ao.CreateAlbumTable();

        while (true) {
            System.out.println("Please press 1 to view album details");
            System.out.println("Please press 2 if you want to update a price");
            System.out.println("Please press 3 to delete an album record");
            System.out.println("Please press 4 to add a new album");
            System.out.println("Press 5 to quit");

            int choice = in.nextInt();
            in.nextLine();
            
            switch (choice) {
                case 1: ao.queryDB();
                        break;
                case 2: System.out.println("please enter the id of the album you wish to update");
                        int id = in.nextInt();
                        in.nextLine();
                        System.out.println("Please enter the new price for "+id);
                        double newPrice = in.nextDouble();
                        ao.updateRecord(id, newPrice);
                        break;
                case 3: System.out.println("please enter the id of the album you wish to delete");
                        int idDelete = in.nextInt();
                        in.nextLine();
                        ao.deleteRecord(idDelete);                  
                        break;
                case 4: System.out.println("please enter the name of the artist you wish to add");
                        String nameAdd = in.nextLine();
                        System.out.println("please enter the nameD of the album you wish to add");
                        String idAdd = in.nextLine();
                        System.out.println("please enter the price of the album you wish to add");
                        double priceAdd = in.nextDouble();
                        ao.add(nameAdd, idAdd, priceAdd);
                        break;
                case 5: System.exit(0);
                        break;
                default:System.out.println("Invalid Option entered");
                        break;                   
                }
            } 
        }	
    
}
