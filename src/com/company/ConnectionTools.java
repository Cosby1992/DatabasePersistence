package com.company;

/**
 * This class contains tools to use and manipulate data in a database.
 * With an instance of ConnectionTools it is possible to persist data in a database as well
 * as fetching this data again
 */

import java.sql.*;

public class ConnectionTools {


    private Connection connection = null;
    private PreparedStatement statement = null;
    private String sql = "";

    public ConnectionTools(){
    }


    /**
     * this method connects to a database
     */
    public void openConnection(){

        //Loading JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver failed to load");
            e.printStackTrace();
        }

        //opening connection to database
        try {
            setConnection(DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/database_test1","root",""));
            //using my database settings ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
            System.out.println("Connection was made successfully");
        } catch (SQLException e) {
            System.out.println("Connection failed");
            e.printStackTrace();
        }
    }

    /**
     * this method closes the connection to the database
     */
    public void closeConnection(){


        //Close connection
        if(getConnection() != null) {
            try {
                getConnection().close();
                System.out.println("Connection closed");
            } catch (SQLException sqlException) {
                System.out.println("Connection failed to close!");
                sqlException.printStackTrace();
            }

        }
    }


    /**
     * This method sends a statement to the database, statement is defined in parameter
     * @param sqlStatement query to execute
     */
    public void sendSqlStatement(String sqlStatement){

        //open connection to database
        openConnection();

        //prepare statement
        try {
            setStatement(getConnection().prepareStatement(sqlStatement));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //execute statement
        try {
            getStatement().executeUpdate();
            System.out.println("This statement was sent to your database: \n" + sqlStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //close connection
        closeConnection();
    }

    /**
     * This method runs a query returning the data it obtains from this query as a ResultSet.
     * @param query query to get data
     * @return ResultSet containing data returned from query
     */
    public ResultSet getResultSetFromQuery(String query){

        //open connection to database
        openConnection();

        //initialize return value with null
        ResultSet resultSet = null;

        //prepare statement
        try {
            setStatement(getConnection().prepareStatement(query));
            System.out.println("Statement prepared: " + query);
        } catch (SQLException e) {
            System.out.println();
            e.printStackTrace();
        }

        //execute query and assign returned value to resultSet
        try {
            resultSet = getStatement().executeQuery();
            System.out.println("Query executed successfully: " + query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //return null or result from query
        return resultSet;

    }


    //////////////////////////////////////////// GETTERS AND SETTERS //////////////////////////////////////////////////
    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }




}
