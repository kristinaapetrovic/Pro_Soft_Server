/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konekcija;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Konekcija {

    private static Konekcija instance;
    private Connection connection;

    private Konekcija() {

        try {
            String url = konfiguracija.Konfiguracija.getInstance().getPropery("url");
            String password = konfiguracija.Konfiguracija.getInstance().getPropery("password");
            String username = konfiguracija.Konfiguracija.getInstance().getPropery("username");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (SQLException ex) {
            Logger.getLogger(Konekcija.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(0);
        }

    }

    public static Konekcija getInstance() {
        if (instance == null) {
            instance = new Konekcija();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}
