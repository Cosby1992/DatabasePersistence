package com.company;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    /*
    The main method is used here to demonstrate database persistence in java.
     */
    public static void main(String[] args) throws SQLException {

        //////////////////////////////////// TESTING CONNECTION ////////////////////////////////////////

        //creating an instance of ConnectionTools called conn
        ConnectionTools conn = new ConnectionTools();

        //opening connection to database using ConnectionTools
        conn.openConnection();

        //closing connection to database using ConnectionTools
        conn.closeConnection();


        ////////////////////////////////// EXECUTING INSERT QUERY ///////////////////////////////////////

        //sending INSERT query to database using ConnectionTools
        String sql = "INSERT INTO `users`(username, password) VALUES ('LTF', 'getSome')";
        conn.sendSqlStatement(sql);

        String sql2 = "INSERT INTO `users`(username, password) VALUES ('PresidentWhiteHair', 'PresidentialPassword123')";
        conn.sendSqlStatement(sql2);


        /////////////////////////////// FETCHING DATA FROM DATABASE /////////////////////////////////////

        //Retrieving data from database using ConnectionTools
        String query = "SELECT * FROM `users` WHERE `User ID` = 1;";
        ResultSet resultSet = conn.getResultSetFromQuery(query);

        //validating resultSet to make sure it's not empty
        if(resultSet != null){

            //initializing with error message
            int id = (int) Double.NaN;
            String username = "An error occurred fetching data from database";
            String password = "An error occurred fetching data from database";

            while (resultSet.next()){

                //assign values from database to variables
                id = resultSet.getInt("User ID");
                username = resultSet.getString("Username");
                password = resultSet.getString("Password");
            }


            //Show information from query in console out
            System.out.println();
            System.out.println();
            System.out.println("------------ Fetched Data -----------");
            System.out.println("User ID \t= " + id + "\n" +
                    "Username \t= " + username + "\n" +
                    "Password \t= " + password);

            System.out.println("-------------------------------------");
            System.out.println();
            System.out.println();


            //closing connection (connection needs to be open while data is being used)
            conn.closeConnection();

        } else {
            System.out.println("No values returned from database.");
        }

    }

}
