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
public class ObrisiMesto extends ApstraktnaGenerickaOperacija{
    Mesto mesto;

    public ObrisiMesto(Mesto mesto) {
        this.mesto = mesto;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
  
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete(objekat);
    }
    
}