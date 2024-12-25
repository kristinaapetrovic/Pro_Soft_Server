/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import model.Mesto;

/**
 *
 * @author Korisnik
 */
public class KreirajMesto extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (broker.existsInBD(objekat) && !broker.isDeleted(objekat)) {
            throw new Exception("Vec postoji objekat");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        if (broker.isDeleted(objekat)) {
            broker.edit(objekat);
        } else {
            broker.add(objekat);
        }
    }

}
