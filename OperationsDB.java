import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

public class OperationsDB {

    // JDBC driver name and database URL
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    private final String DB_URL = "jdbc:mysql://localhost:3306/AIRPORT";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "root@123";

    Statement stmt = null;

    private Connection conn;
    
    public OperationsDB(){
        try{
            //STEP 2: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            //STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println("Database connected succesfully");
        } catch(Exception e) {
            System.out.println("Something is wrong");
            e.printStackTrace();
 
       }      
    }
    
        public void CreateTable (String tblName) {
            try {
                Statement stmt = conn.createStatement();

                String sql = "CREATE TABLE "+ tblName  +
                    " (id INTEGER not NULL, " +
                    " first VARCHAR(255), " + 
                    " last VARCHAR(255), " + 
                    " age INTEGER, " + 
                    " PRIMARY KEY ( id ))"; 
                
                System.out.println(sql);
                
                stmt.executeUpdate(sql);
                System.out.println("Created table in given database...");

                conn.close();
            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Error when creating Table");
            }
        }

        public void InsertTable (String tblName) {
            try {
                System.out.println("Creating Statement...");
                Statement stmt = conn.createStatement();

                System.out.println("Inserting records into the table...");
                String sql = "INSERT INTO "+ tblName +
                            " VALUES (100, 'Zara', 'Ali', 18)";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO "+ tblName +" VALUES (101, 'Himsara', 'Gallege', 25)";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO " + tblName +
                            " VALUES (102, 'Jason', 'Momoa', 30)";
                stmt.executeUpdate(sql);
                sql = "INSERT INTO " + tblName +
                            " VALUES(103, 'Will', 'Smith', 28)";
                stmt.executeUpdate(sql);
                System.out.println("Inserted records into the table...");

                conn.close();
            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Error when Inserting record");
            }
        }

        public void ReadTable (String tblName) {
            try{
                Statement stmt = conn.createStatement();

                String sql = "SELECT id, first, last, age FROM " + tblName;
                ResultSet rs = stmt.executeQuery(sql);
                //STEP 5: Extract data from result set
                while(rs.next()){
                    //Retrieve by column name
                    int id  = rs.getInt("id");
                    int age = rs.getInt("age");
                    String first = rs.getString("first");
                    String last = rs.getString("last");

                    //Display values
                    System.out.print("ID: " + id);
                    System.out.print(", Age: " + age);
                    System.out.print(", First: " + first);
                    System.out.println(", Last: " + last);
            }
            rs.close();
            } catch(Exception e){
                    e.printStackTrace();
                    System.out.println("Error when Reading from Table");
            }
        }

        public void DeleteTable (String condition, String tblName) {
            try {
                stmt = conn.createStatement();

                String sql = "DELETE FROM  " + tblName +
                            " WHERE " + condition;
                try {stmt.executeUpdate(sql);
                } catch (Exception e){
                    System.out.println("Unable to Delete");
                }

        
                // Now you can extract all the records
                // to see the remaining records
                sql = "SELECT id, first, last, age FROM " + tblName;;
                ResultSet rs = stmt.executeQuery(sql);
                rs.close();

                ReadTable(tblName);

            } catch(Exception e){
                e.printStackTrace();
                System.out.println("Error when Deleting a record from Table");
            }
        }
}