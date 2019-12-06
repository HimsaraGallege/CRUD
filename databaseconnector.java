//STEP 1. Import required packages
import java.sql.*; //CRUD 
import java.util.Scanner;

public class databaseconnector {
    
    public static void main(String[] args) {
        while(moreoperations = true) {
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            
            System.out.println("Enter table name: ");
            String tblName = myObj.nextLine();  // Read table name

            System.out.println("What do you want to do?");
            System.out.println("CREATETABLE OR INSERTRECORD OR READTABLE OR DELETETABLE");
            System.out.println("Enter one of the above operators");
            String operator = myObj.nextLine(); //Read operator

            switch (operator) {
                case "CREATETABLE":
                    db.CreateTable(tblName);
                    break;
                case "INSERTRECORD":
                    db.InsertTable(tblName);
                    break;
                case "READTABLE":
                    db.ReadTable(tblName);
                    break;
                case "DELETETABLE":
                    System.out.println("Enter condition for deleting a record");
                    String condition = myObj.nextLine(); //Read Condition
                    OperationsDB db = new OperationsDB();
                    db.DeleteTable(condition,tblName);
                    break;
                default:
                    System.out.println("Enter a valid operation");
            }
            System.out.println("Are you done? (Y/N)");
            String moreoperations = myObj.nextLine();
            
            //db.CreateTable(tblName);
            // db.InsertTable(tblName);
            //db.ReadTable(tblName);
            //db.DeleteTable(condition,tblName);
        }
    }
}