/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.jesponzor;

import java.util.ArrayList;
import java.util.List;
import model.JeSponzor;
import model.Projekat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuJeSponzor extends ApstraktnaGenerickaOperacija{
    List<JeSponzor> lista=new ArrayList<>();

    public List<JeSponzor> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
 
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.readSponzorByProject((Projekat) objekat, lista);
    }
    
}
