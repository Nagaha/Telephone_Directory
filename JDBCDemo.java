import java.sql.*;
import java.util.Scanner;

public class JDBCDemo {
    static Scanner sc=new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        System.out.println(".....Welcome to Telephone directory.....");
        boolean ch=true;
        while(ch){
            System.out.println("1.Read directory");
            System.out.println("2.Insert new data");
            System.out.println("3.Update the present data");
            System.out.println("4.Remove the data");
            System.out.println("5.Search the data");
            System.out.println("Enter your choice:");
            int op=sc.nextInt();
            switch (op){
                case 1: {
                    readRecords();
                    break;
                }
                case 2:{
                    insertNew();
                    break;
                }
                case 3:{
                    updateRecord();
                    break;
                }
                case 4:{
                    deleteRecord();
                    break;
                }
                case 5:{
                    searchRecord();
                    break;
                }
                default: {
                    System.out.println("Invalid input");
                }
            }
            System.out.println("Do you want to continue:(true/false) ");
            try {
                ch = sc.nextBoolean();
            }
            catch (Exception e){
                System.out.println("Invalid choice");
                return;
            }

        }
    }

    public static void searchRecord() throws Exception{
        String url = "jdbc:mysql://localhost:3306/db_name";
        String userName = "your_username";
        String passWord = "your_password";


        System.out.println("1.Search with Name: ");
        System.out.println("2.Search with city: ");
        System.out.println("3.Search with number: ");
        System.out.println("Enter your choice: ");
        int ch=sc.nextInt();
        switch(ch){
            case 1: {
                try {
                    System.out.println("Enter the Name: ");
                    String name = sc.next();
                    if (name.length() < 1) {
                        System.out.println("Invalid name");
                    } else {
                        String query = "select * from details where name=" + "'" + name + "'";
                        Connection con = DriverManager.getConnection(url, userName, passWord);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        while( rs.next()) {
                            System.out.println("Name is " + rs.getString(1));
                            System.out.println("Number " + rs.getString(2));
                            System.out.println("City is " + rs.getString(3));
                            System.out.println("...........");
                        }
                    }
                }
                catch(Exception e){
                    System.out.println("No records found");
                }

                break;
            }
            case 2:
            {
                System.out.println("Enter the city: ");
                String city=sc.next();
                if (city.length() < 1) {
                    System.out.println("Invalid city");
                } else {
                    try {
                        String query = "select * from details where City=" + "'" + city + "'";
                        Connection con = DriverManager.getConnection(url, userName, passWord);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);

                        while( rs.next()) {
                            System.out.println("Name is " + rs.getString(1));
                            System.out.println("Number " + rs.getString(2));
                            System.out.println("City is " + rs.getString(3));
                            System.out.println("...........");
                        }
                    }
                    catch (Exception e){
                        System.out.println("No Records found");
                    }
                }
                break;
            }
            case 3: {
                try {
                    System.out.println("Enter the Number: ");
                    String num = sc.next();
                    System.out.println(num);
                    if (num.length() < 1) {
                        System.out.println("Invalid number");
                    } else {
                        String query = "select * from details where Ph_num=" + "'" + num + "'";
                        Connection con = DriverManager.getConnection(url, userName, passWord);
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(query);
                       while( rs.next()) {
                           System.out.println("Name is " + rs.getString(1));
                           System.out.println("Number " + rs.getString(2));
                           System.out.println("City is " + rs.getString(3));
                           System.out.println("...........");
                       }
                    }
                }
                catch(Exception e){
                    System.out.println("No records found");
                }

                break;
            }

        }



    }

    //Reading from the database
    public static void readRecords() throws Exception {
        String url = "jdbc:mysql://localhost:3306/db_name";
        String userName = "your_username";
        String passWord = "your_password";
        String query = "select * from details";
//All this information of your mysql workbench

        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Name is " + rs.getString(1));
            System.out.println("Number " + rs.getString(2));
            System.out.println("City is " + rs.getString(3));
            System.out.println("...........");
        }

        con.close();
    }


    //insert with variables
    public static void insertNew() throws Exception {
        String url = "jdbc:mysql://localhost:3306/db_name";
        String userName = "your_username";
        String passWord = "your_password";

        System.out.println("Enter the Name: ");
        String Name=sc.next();
        System.out.println("Enter the Number: ");
        String Number=sc.next();
        System.out.println("Enter the city: ");
        String City=sc.next();

        String query = "insert into details values (?,?,?);";


        Connection con = DriverManager.getConnection(url, userName, passWord);

        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, Name);
        pst.setString(2, Number);
        pst.setString(3, City);
        int rows = pst.executeUpdate();

        System.out.println("Number of rows affected: " + rows);
        con.close();
    }


    //delete
    public static void deleteRecord() throws Exception {
         String url = "jdbc:mysql://localhost:3306/db_name";
        String userName = "your_username";
        String passWord = "your_password";

        System.out.println("Enter the number: ");
        String Number=sc.next();

        String query = "delete from details where Ph_num = " + "'"+Number+"'";


        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: " + rows);
        con.close();
    }

    //update
    public static void updateRecord() throws Exception {
        String url = "jdbc:mysql://localhost:3306/db_name";
        String userName = "your_username";
        String passWord = "your_password";


        System.out.println("Enter the name : ");
        String Name=sc.next();
        System.out.println("Enter the number: ");
        String Number=sc.next();

        String query = "update details set Name="+"'"+Name+"'"+"where Ph_num="+"'"+Number+"'"+";";


        Connection con = DriverManager.getConnection(url, userName, passWord);
        Statement st = con.createStatement();
        int rows = st.executeUpdate(query);

        System.out.println("Number of rows affected: " + rows);
        con.close();
    }
}
