/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SchedulePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class VariableClass {

     public static ArrayList monthName = new ArrayList();

    public static ArrayList<Integer> schedId = new ArrayList();
    public static ArrayList sched = new ArrayList();
    public static ArrayList task = new ArrayList();
    public static ArrayList title = new ArrayList();
    public static ArrayList type = new ArrayList();
    public static ArrayList conductedBy = new ArrayList();
    public static ArrayList status = new ArrayList();
    public static ArrayList schedIdentifier = new ArrayList();
    public static ArrayList equipment = new ArrayList();
    public static ArrayList location = new ArrayList();
    public static ArrayList equipmentLocation = new ArrayList();
    public static ArrayList imageSched = new ArrayList();

    public static ArrayList equipmentList = new ArrayList();
    public static ArrayList<Integer> equipmentId = new ArrayList();

    public static ArrayList userStatus = new ArrayList();
    public static ArrayList username = new ArrayList();
    public static ArrayList password = new ArrayList();
    public static ArrayList fname = new ArrayList();
    public static ArrayList lname = new ArrayList();
    public static ArrayList mname = new ArrayList();
    public static ArrayList bday = new ArrayList();
    public static ArrayList email = new ArrayList();
    public static ArrayList contact = new ArrayList();
    public static ArrayList imgUser = new ArrayList();
    public static ArrayList initials = new ArrayList();
    public static ArrayList<Integer> userId = new ArrayList();

    public static int userlog = 0;
    public static int theresLogin = 0;

    public static int mainScreenTbl = 0;
    public static int mainScreenSelected = 0;

    public static void loadAdmin() {

    }

    public static void createDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/";
            Connection conn = DriverManager.getConnection(url, "root", "admin");
            Statement s = conn.createStatement();
            int Result = s.executeUpdate("CREATE DATABASE IF NOT EXISTS TrafficSystems");
            conn.close();
        } catch (Exception e) {
            System.out.println(e);

        }

    }

    public static void createSched() {
//    public static ArrayList<Integer> schedId = new ArrayList();
//    public static ArrayList sched = new ArrayList();
//    public static ArrayList task = new ArrayList();
//    public static ArrayList title = new ArrayList();
//    public static ArrayList type = new ArrayList();
//    public static ArrayList conductedBy = new ArrayList();
//    public static ArrayList status = new ArrayList();
//    public static ArrayList schedIdentifier = new ArrayList();
//    public static ArrayList equipment = new ArrayList();
//    public static ArrayList location = new ArrayList();
//    public static ArrayList equipmentLocation = new ArrayList();
//    public static ArrayList imageSched = new ArrayList();
        String myTableName = "CREATE TABLE IF NOT EXISTS sched ( \n"
                + "        schedId INT NOT NULL AUTO_INCREMENT, \n"
                + "        sched VARCHAR(50),\n"
                + "        task VARCHAR(1000),\n"
                + "        title VARCHAR(50),\n"
                + "        type VARCHAR(50),\n"
                + "        conductedBy VARCHAR(50),\n"
                + "        status VARCHAR(50),\n"
                + "        schedIdentifier VARCHAR(50),\n"
                + "        equipment VARCHAR(50),\n"
                + "        equipmentLocation varchar(50),  \n"
                + "        imageSched varchar(50),  \n"
                + "        location varchar(50),PRIMARY KEY (`schedId`));";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/trafficsystems";
            Connection conn = DriverManager.getConnection(url, "root", "admin");

            Statement statement = conn.createStatement();
            //The next line has the issue
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");
            conn.close();
        } catch (Exception e) {
            System.out.println("An error has occurred on Table Creation");
            System.out.println(e);
        }

    }

    public static void createUser() {
//    public static ArrayList userStatus = new ArrayList();
//    public static ArrayList username = new ArrayList();
//    public static ArrayList password = new ArrayList();
//    public static ArrayList fname = new ArrayList();
//    public static ArrayList lname = new ArrayList();
//    public static ArrayList mname = new ArrayList();
//    public static ArrayList bday = new ArrayList();
//    public static ArrayList email = new ArrayList();
//    public static ArrayList contact = new ArrayList();
//    public static ArrayList imgUser = new ArrayList();
//    public static ArrayList initials = new ArrayList();
//    public static ArrayList<Integer> userId = new ArrayList();
        String myTableName = "CREATE TABLE IF NOT EXISTS user ( \n"
                + "        userId INT NOT NULL AUTO_INCREMENT, \n"
                + "        userStatus VARCHAR(50),\n"
                + "        username VARCHAR(50),\n"
                + "        password VARCHAR(50),\n"
                + "        fname VARCHAR(50),\n"
                + "        lname VARCHAR(50),\n"
                + "        mname VARCHAR(50),\n"
                + "        bday VARCHAR(50),\n"
                + "        email VARCHAR(50),\n"
                + "        contact varchar(50),  \n"
                + "        imgUser varchar(50),  \n"
                + "        initials varchar(50),PRIMARY KEY (`userId`));";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/trafficsystems";
            Connection conn = DriverManager.getConnection(url, "root", "admin");

            Statement statement = conn.createStatement();
            //The next line has the issue
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");
            conn.close();
        } catch (Exception e) {
            System.out.println("An error has occurred on Table Creation");
            System.out.println(e);
        }

    }

    public static void createEquipment() {

        String myTableName = "CREATE TABLE IF NOT EXISTS equipment ( \n"
                + "        equipmentId INT NOT NULL AUTO_INCREMENT, \n"
                + "        equipmentList varchar(50),PRIMARY KEY (`equipmentId`));";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/trafficsystems";
            Connection conn = DriverManager.getConnection(url, "root", "admin");

            Statement statement = conn.createStatement();
            //The next line has the issue
            statement.executeUpdate(myTableName);
            System.out.println("Table Created");
            conn.close();
        } catch (Exception e) {
            System.out.println("An error has occurred on Table Creation");
            System.out.println(e);
        }

    }

    public static void loadSched() {
//    public static ArrayList<Integer> schedId = new ArrayList();
//    public static ArrayList sched = new ArrayList();
//    public static ArrayList task = new ArrayList();
//    public static ArrayList title = new ArrayList();
//    public static ArrayList type = new ArrayList();
//    public static ArrayList conductedBy = new ArrayList();
//    public static ArrayList status = new ArrayList();
//    public static ArrayList schedIdentifier = new ArrayList();
//    public static ArrayList equipment = new ArrayList();
//    public static ArrayList location = new ArrayList();
//    public static ArrayList equipmentLocation = new ArrayList();
//    public static ArrayList imageSched = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver"); //mysql connector
            String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
            Connection connection = DriverManager.getConnection(company_db);
            String mysql_query = "SELECT * FROM sched";
            Statement st = connection.createStatement(); // create the java statement
            ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

            VariableClass.schedId.removeAll(schedId);
            VariableClass.sched.removeAll(sched);
            VariableClass.task.removeAll(task);
            VariableClass.title.removeAll(title);
            VariableClass.type.removeAll(type);
            VariableClass.conductedBy.removeAll(conductedBy);
            VariableClass.status.removeAll(status);
            VariableClass.schedIdentifier.removeAll(schedIdentifier);
            VariableClass.equipment.removeAll(equipment);
            VariableClass.location.removeAll(location);
            VariableClass.equipmentLocation.removeAll(equipmentLocation);
            VariableClass.imageSched.removeAll(imageSched);

            int count = 0;
            while (rs.next()) {
                ++count; //count the results (rows)

                VariableClass.schedId.add(rs.getInt("schedId"));
                VariableClass.sched.add(rs.getString("sched"));
                VariableClass.task.add(rs.getString("task"));
                VariableClass.title.add(rs.getString("title"));
                VariableClass.type.add(rs.getString("type"));
                VariableClass.conductedBy.add(rs.getString("conductedBy"));
                VariableClass.status.add(rs.getString("status"));
                VariableClass.schedIdentifier.add(rs.getString("schedIdentifier"));
                VariableClass.equipment.add(rs.getString("equipment"));
                VariableClass.location.add(rs.getString("location"));
                VariableClass.equipmentLocation.add(rs.getString("equipmentLocation"));
                VariableClass.imageSched.add(rs.getString("imageSched"));

            }
            if (count == 0) {
                System.out.println("No sched found!");
            }
            st.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Something wrong happened! ");
            System.out.println(e.getMessage());
        }
        
    }

    public static void loadUser() {

//    public static ArrayList userStatus = new ArrayList();
//    public static ArrayList username = new ArrayList();
//    public static ArrayList password = new ArrayList();
//    public static ArrayList fname = new ArrayList();
//    public static ArrayList lname = new ArrayList();
//    public static ArrayList mname = new ArrayList();
//    public static ArrayList bday = new ArrayList();
//    public static ArrayList email = new ArrayList();
//    public static ArrayList contact = new ArrayList();
//    public static ArrayList imgUser = new ArrayList();
//    public static ArrayList initials = new ArrayList();
//    public static ArrayList<Integer> userId = new ArrayList();
        try {
            Class.forName("com.mysql.jdbc.Driver"); //mysql connector
            String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
            Connection connection = DriverManager.getConnection(company_db);
            String mysql_query = "SELECT * FROM user";
            Statement st = connection.createStatement(); // create the java statement
            ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

            VariableClass.userStatus.removeAll(userStatus);
            VariableClass.username.removeAll(username);
            VariableClass.password.removeAll(password);
            VariableClass.fname.removeAll(fname);
            VariableClass.lname.removeAll(lname);
            VariableClass.mname.removeAll(mname);
            VariableClass.bday.removeAll(bday);
            VariableClass.email.removeAll(email);
            VariableClass.contact.removeAll(contact);
            VariableClass.imgUser.removeAll(imgUser);
            VariableClass.initials.removeAll(initials);
            VariableClass.userId.removeAll(userId);
           
            

            int count = 0;
            while (rs.next()) {
                ++count; //count the results (rows)

                VariableClass.userStatus.add(rs.getString("userStatus"));
                VariableClass.username.add(rs.getString("username"));
                VariableClass.password.add(rs.getString("password"));
                VariableClass.fname.add(rs.getString("fname"));
                VariableClass.lname.add(rs.getString("lname"));
                VariableClass.mname.add(rs.getString("mname"));
                VariableClass.bday.add(rs.getString("bday"));
                VariableClass.email.add(rs.getString("email"));
                VariableClass.contact.add(rs.getString("contact"));
                VariableClass.imgUser.add(rs.getString("imgUser"));
                VariableClass.initials.add(rs.getString("initials"));
                VariableClass.userId.add(rs.getInt("userId"));
                
            

            }
            if (count == 0) {
                username.add("admin");
                password.add("admin");
                fname.add("admin");
                lname.add("admin");
                mname.add("admin");
                bday.add("1/1/2000");
                email.add("admin@yahoo.com");
                contact.add("11111111111");
                imgUser.add("Img\\" + "admin" + ".png");
                initials.add("admin");
                userStatus.add("Unblocked");
                userId.add(1);
                
                String sql = "INSERT INTO user (userId, initials,username,password,fname,lname,mname,bday,email,contact,userStatus,imgUser)"
                        + "VALUES (?, ? , ?, ? ,? , ? , ? , ? , ? , ? ,? , ?)";

                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, 0);
                preparedStatement.setString(2, "admin");
                preparedStatement.setString(3, "admin");
                preparedStatement.setString(4, "admin");
                preparedStatement.setString(5, "admin");
                preparedStatement.setString(6, "admin");
                preparedStatement.setString(7, "admin");
                preparedStatement.setString(8, "1/1/2000");
                preparedStatement.setString(9, "admin@yahoo.com");
                preparedStatement.setString(10, "11111111111");
                preparedStatement.setString(11, "Unblocked");
                preparedStatement.setString(12, "Img\\" + "admin" + ".png");
                preparedStatement.executeUpdate();
             
            }
            st.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Something wrong happened! ");
            System.out.println(e.getMessage());
        }
        
        
    }

    public static void loadEquipment() {

//    public static ArrayList equipmentList = new ArrayList();
//    public static ArrayList<Integer> equipmentId = new ArrayList();
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainScreen.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        try {
            Class.forName("com.mysql.jdbc.Driver"); //mysql connector
            String company_db = "jdbc:mysql://localhost:3306/trafficsystems?user=root&password=admin";
            Connection connection = DriverManager.getConnection(company_db);
            String mysql_query = "SELECT * FROM equipment";
            Statement st = connection.createStatement(); // create the java statement
            ResultSet rs = st.executeQuery(mysql_query); // execute the query, and get a java resultset

            VariableClass.equipmentList.removeAll(equipmentList);
            VariableClass.equipmentId.removeAll(equipmentId);

            int count = 0;
            while (rs.next()) {
                ++count; //count the results (rows)

                int equipmentIdData = (rs.getInt("equipmentId"));
                String equipmentLocationData = rs.getString("equipmentList");

                equipmentId.add(equipmentIdData);
                equipmentList.add(rs.getString("equipmentList"));

            }
            if (count == 0) {
                System.out.println("No equipment found!");
            }
            st.close();

        } catch (Exception e) {
            System.out.println("Something wrong happened! ");
            System.out.println(e.getMessage());
        }
       

    }

    public static void main(String[] args) {

        monthName.add("January");
        monthName.add("Febuary");
        monthName.add("March");
        monthName.add("April");
        monthName.add("May");
        monthName.add("June");
        monthName.add("July");
        monthName.add("August");
        monthName.add("September");
        monthName.add("October");
        monthName.add("November");
        monthName.add("December");

        createDatabase();
        createSched();
        createUser();
        createEquipment();
        loadSched();
        loadUser();
        loadEquipment();
        
        MainScreen mainscreen = new MainScreen();
        mainscreen.setVisible(true);

    }

}
