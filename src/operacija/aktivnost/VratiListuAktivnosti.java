/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.aktivnost;

import java.util.ArrayList;
import java.util.List;
import model.Aktivnost;
import model.Projekat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuAktivnosti extends ApstraktnaGenerickaOperacija{
    List<Aktivnost> lista=new ArrayList<>();

    public List<Aktivnost> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
  
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        brokerSpecific.readAktByUg((Projekat) objekat, lista);
    }
    
}
