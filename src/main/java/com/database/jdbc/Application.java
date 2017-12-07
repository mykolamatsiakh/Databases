package com.database.jdbc;

import java.sql.*;
import java.util.Scanner;


public class Application {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/lab3";

    // Database credentials
    static final String USER = "root";
    static final String PASS = "sutabu66";

    public static void main(String args[]) {
        Statement stmt = null;
        Connection conn = null;
        try {
//            insertPresidentName();
//            UpdatePresidentCountry();
//            callProcedureForInsertToPresident();
//            deletePresident();
            System.out.print("\nConnecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.print("\nInserting records into table...");
            stmt = conn.createStatement();
            String select = "SELECT * FROM Constitution";
            String sql = "INSERT INTO Constitution " +
                    "VALUES (7, 'm,qdmsa', 'Mamay', 9)";
//            stmt.executeUpdate(select);
        }catch (SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    private static void insertPresidentName() throws SQLException {
        Connection conn = null;
        Scanner inputId = new Scanner(System.in);
        System.out.println("Input your president id: ");
        int presidentId = Integer.parseInt(inputId.next());

        Scanner input = new Scanner(System.in);
        System.out.println("Input your president name: ");
        String presidentName = input.next();

        Scanner inputSurname = new Scanner(System.in);
        System.out.println("Input your president surnamename: ");
        String presidentSurname = inputSurname.next();

        Scanner inputCountry = new Scanner(System.in);
        System.out.println("Input your president country: ");
        String presidentCountry = inputCountry.next();

        Scanner inputEmail = new Scanner(System.in);
        System.out.println("Input your president email: ");
        String presidentEmail = inputEmail.next();

        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        // 3. executing SELECT query
        //   PreparedStatements can use variables and are more efficient
        PreparedStatement preparedStatement;
        String query = "INSERT INTO President (IDPresident,Name,Surname, Country, Email)"+" VALUES (?,?,?,?,?)";
        preparedStatement=conn.prepareStatement(query);
        preparedStatement.setInt(1, presidentId);
        preparedStatement.setString(2, presidentName);
        preparedStatement.setString(3, presidentSurname);
        preparedStatement.setString(4, presidentCountry);
        preparedStatement.setString(5, presidentEmail);
        int n=preparedStatement.executeUpdate();
        conn.close();
        System.out.println("Count rows that inserted: "+n);

    }

    private static void UpdatePresidentCountry() throws SQLException {
        Connection conn = null;
        Scanner inputId = new Scanner(System.in);
        System.out.println("Input your president country: ");
        String presidentCountry = inputId.next();
        conn = DriverManager.getConnection(DB_URL, USER, PASS);

        String query = "update President set Country = ? where IDPresident = 2";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, presidentCountry);
        preparedStmt.executeUpdate();
        conn.close();
        System.out.println("Update successful");

    }


    private static void callProcedureForInsertToPresident() throws SQLException {
        Connection conn = null;
        Scanner inputId = new Scanner(System.in);
        System.out.println("Input your president id: ");
        int presidentId = Integer.parseInt(inputId.next());

        Scanner input = new Scanner(System.in);
        System.out.println("Input your president name: ");
        String presidentName = input.next();

        Scanner inputSurname = new Scanner(System.in);
        System.out.println("Input your president surnamename: ");
        String presidentSurname = inputSurname.next();

        Scanner inputCountry = new Scanner(System.in);
        System.out.println("Input your president country: ");
        String presidentCountry = inputCountry.next();

        Scanner inputEmail = new Scanner(System.in);
        System.out.println("Input your president email: ");
        String presidentEmail = inputEmail.next();

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        CallableStatement callableStatement;
        String query = "{call insertIn(?, ?, ?, ?, ?)}";
        callableStatement = conn.prepareCall(query);
        callableStatement.setInt("IDPresidentIn", presidentId);
        callableStatement.setString("SurnameIn", presidentSurname);
        callableStatement.setString("NameIn", presidentName);
        callableStatement.setString("CountryIn", presidentCountry);
        callableStatement.setString("EmailIn", presidentEmail);
        ResultSet rs = callableStatement.executeQuery();
        conn.close();
    }

    private static void deletePresident() throws SQLException {
        Scanner input = new Scanner(System.in);
        System.out.println("Input a president name for delete: ");
        int id = Integer.valueOf(input.next());
        Connection conn;

        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        PreparedStatement preparedStatement;
        String query = "DELETE FROM President WHERE IDPresident=?";
        preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        conn.close();
    }


}