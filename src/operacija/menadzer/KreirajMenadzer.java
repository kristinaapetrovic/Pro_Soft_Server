/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.menadzer;

import email.EmailPoruka;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.mail.MessagingException;
import model.Menadzer;
import niti.ObradaKlijentskihZahteva;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class KreirajMenadzer extends ApstraktnaGenerickaOperacija {
    private Menadzer menadzer;

    public Menadzer getMenadzer() {
        return menadzer;
    }


    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (broker.existsInBD(objekat) && !broker.isDeleted(objekat)) {
            throw new Exception("Vec postoji objekat");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        menadzer=(Menadzer) objekat;
        Random random = new Random();
        int kod = 10000 + random.nextInt(90000);
        
        try {
            EmailPoruka.autentifikacija(menadzer.getEmail(), String.valueOf(kod));
        } catch (MessagingException ex) {
            System.out.println("Greska u slanju koda za autentifikaciju");
            return;
        }
        try {
            menadzer.setLozinka(hashing.Hash.kriptuj(kod + ""));
        } catch (NoSuchAlgorithmException ex) {
            //todo
        }
        if(broker.isDeleted(objekat)){
            broker.edit(menadzer);
        }else{
            broker.add(menadzer);
        }
  
    }
    
}
