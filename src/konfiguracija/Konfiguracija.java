/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Konfiguracija {

    private static Konfiguracija instance;
    private Properties konf;

    private Konfiguracija() {

        konf = new Properties();

        try {
            //DODATI URL KADA NAPRAVIMO .config fajl
            konf.load(new FileInputStream("C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\PROTOTIP2_ProjSoft\\config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getPropery(String key) {
        return konf.getProperty(key, "n/a"); //ako nema tog kljuca vraca n/a
    }

    public void setProperty(String key, String value) {
        konf.setProperty(key, value);
    }

    public void sacuvajIzmene() {

        try {
            //dodati naziv fajla .config
            konf.store(new FileOutputStream("C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\PROTOTIP2_ProjSoft\\config.properties"), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
