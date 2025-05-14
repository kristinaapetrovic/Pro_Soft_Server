/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import dbinit.DatabaseInitializer;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Korisnik
 */
public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "admin";
        String dbName = "prosoft44";
        DatabaseInitializer bdInit = new DatabaseInitializer(url, user, pass, dbName);
        
        
        
        
         //boolean uspesno=DatabaseInitializer.baseExistWithTables(url, user, pass, dbName);
         //System.out.println(uspesno);
//        String filePath = "C:\\Users\\Korisnik\\Documents\\NetBeansProjects\\0_ProSoft_Server\\src\\resources\\skriptaZaBazu.sql"; // Promeni na svoju putanju
//        File file = new File(filePath);
//
//        if (file.exists()) {
//            System.out.println("Fajl postoji: " + filePath);
//        } else {
//            System.out.println("Fajl ne postoji: " + filePath);
//        }

//        Mesto m1 = new Mesto("5656", "dka", true);
//        Mesto m2 = new Mesto("5656", "dka", true);
//        Mesto m3 = new Mesto("5656", "dka", true);
//        List<Mesto> lista = new ArrayList<>();
//        lista.add(m1);
//        lista.add(m2);
//        lista.add(m2);
//       // List<Mesto> lista = new ArrayList<>();
//       // lista.add(new Mesto("12","Beograd", true));
//        // lista.add(new Menadzer("Kristina", "email@example.com"));
//        try {
//                    String json = jsonFormat.JSONFormat.toJson(m1);
//        System.out.println(json);
//        System.out.println(jsonFormat.JSONFormat.toJson(lista));
//            String loz="";
//        try {
//            loz = hashing.Hash.kriptuj("admin");
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
//            System.out.println(loz);
//        } catch (NoSuchAlgorithmException ex) {
//            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
//        }
        

    }
}
