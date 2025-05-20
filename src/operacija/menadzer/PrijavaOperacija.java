/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.menadzer;

import java.util.List;
import model.Menadzer;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class PrijavaOperacija extends ApstraktnaGenerickaOperacija {

    private Menadzer ulogovani;

    public Menadzer getUlogovani() {
        return ulogovani;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {        
        Menadzer menadzer = (Menadzer) objekat;
        boolean uspesno = brokerSpecific.prijavi(menadzer);
        if (uspesno) {
            ulogovani = menadzer;
        } else {
            ulogovani = null;
        }

    }

}
