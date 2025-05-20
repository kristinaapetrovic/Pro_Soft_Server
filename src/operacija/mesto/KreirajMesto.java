/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class KreirajMesto extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if (brokerGeneric.existsInBD(objekat) && !brokerGeneric.isDeleted(objekat)) {
            throw new Exception("Vec postoji objekat");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        if (brokerGeneric.isDeleted(objekat)) {
            brokerGeneric.edit(objekat);
        } else {
            brokerGeneric.add(objekat);
        }
    }

}
