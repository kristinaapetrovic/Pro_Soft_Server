/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfiguracija;

import java.io.File;
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
    private Properties konf=new Properties();
    private static final String CONFIG_PATH = "C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\0_ProSoft_Server\\config.properties";

    private Konfiguracija() {
        if (konfiguracijaPostoji()) {

            try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
                konf.load(fis);
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, "Greška pri učitavanju konfiguracije", ex);
            }
        }

    }

    public static Konfiguracija getInstance() {
        if (instance == null) {
            instance = new Konfiguracija();
        }
        return instance;
    }

    public String getPropery(String key) {
        return konf.getProperty(key, "n/a");
    }

    public void setProperty(String key, String value) {
        konf.setProperty(key, value);
    }

    public void sacuvajIzmene() {

        try {
            konf.store(new FileOutputStream(CONFIG_PATH), null);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean konfiguracijaPostoji() {
        File configFile = new File(CONFIG_PATH);
        return configFile.exists() && configFile.length() > 0;
    }

    public void kreirajPrazanFajl() {
        File configFile = new File(CONFIG_PATH);

        if (!configFile.exists()) {
            try {
                if (configFile.createNewFile()) {
                    System.out.println("Konfiguracioni fajl je uspešno kreiran: " + CONFIG_PATH);
                } else {
                    System.out.println("Nešto nije u redu, fajl nije kreiran.");
                }
            } catch (IOException ex) {
                Logger.getLogger(Konfiguracija.class.getName()).log(Level.SEVERE, "Greška pri kreiranju konfiguracionog fajla", ex);
            }
        }
    }

}
