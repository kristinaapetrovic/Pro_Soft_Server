/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package test1;

import java.util.Date;
import model.Aktivnost;
import model.Projekat;
import model.VrstaAktivnosti;

/**
 *
 * @author Korisnik
 */
public class Main {

    public static void main(String[] args) {
        Projekat proj = new Projekat();
        proj.setRegBroj("1235/2024");
        Aktivnost akt = new Aktivnost(proj, 1, "Lalala", "Lalala", true, new VrstaAktivnosti(10, "", false), new Date());
              String upit = "SELECT * FROM aktivnost a LEFT JOIN projektniugovor u USING (regBroj) LEFT JOIN vrstaaktivnosti va USING (idVrstaAktivnosti) WHERE " + proj.vratiUslovZaNadjiSlog();  System.out.println(upit);
    }
}
