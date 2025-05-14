/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbinit;

import java.io.File;
import java.sql.Statement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.core.MySQLDatabase;
import liquibase.database.jvm.JdbcConnection;
import java.sql.ResultSet;
import liquibase.resource.DirectoryResourceAccessor;

/**
 *
 * @author Korisnik
 */
public class DatabaseInitializer {

    public DatabaseInitializer(String url, String user, String pass, String dbName) {
        if (doNotExistWithTables(url, user, pass, dbName)) {
            createDatabase(url, user, pass, dbName);
            runLiquibase(url, user, pass, dbName);
        }
    }

    private static void createDatabase(String url, String user, String pass, String dbName) {
        try (Connection conn = DriverManager.getConnection(url, user, pass); Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Kreirana/nadjena baza: " + dbName);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void runLiquibase(String url, String user, String pass, String dbName) {
        try (Connection conn = DriverManager.getConnection(url +"/"+ dbName, user, pass)) {
            Database database = new MySQLDatabase();
            database.setConnection(new JdbcConnection(conn));

            File file = new File("src\\resources\\skripte\\sifarnici");

            DirectoryResourceAccessor resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }

            file = new File("src\\resources\\skripte\\slozeni");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }
            file = new File("src\\resources\\skripte\\asklase");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }
            file = new File("src\\resources\\skripte\\slabi");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }

            file = new File("src\\resources\\skripte\\insert-sifarnici");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }

            file = new File("src\\resources\\skripte\\insert-slozeni");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }
            file = new File("src\\resources\\skripte\\insert-asklase");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }
            file = new File("src\\resources\\skripte\\insert-slabi");

            resourceAccessor = new DirectoryResourceAccessor(file);

            for (File sqlFile : file.listFiles()) {
                if (sqlFile.getName().endsWith(".sql")) {
                    System.out.println("Pokrećem migraciju sa fajlom: " + sqlFile.getName());
                    Liquibase liquibase = new Liquibase(sqlFile.getName(), resourceAccessor, database);
                    liquibase.update("");
                }
            }

            System.out.println("Gotovo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean doNotExistWithTables(String url, String user, String pass, String dbName) {
        try {
            String upit = "SELECT COUNT(*) \n"
                    + "FROM INFORMATION_SCHEMA.TABLES \n"
                    + "WHERE TABLE_SCHEMA = '" + dbName + "';";

            Connection conn = DriverManager.getConnection(url, user, pass);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(upit);
            rs.next();

            return rs.getInt(1) < 3;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseInitializer.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
