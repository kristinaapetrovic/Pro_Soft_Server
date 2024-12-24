/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.ArrayList;
import java.util.List;
import model.VrstaAktivnosti;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviVrstaAktivnosti extends ApstraktnaGenerickaOperacija {
    List<VrstaAktivnosti> lista=new ArrayList<>();

    public List<VrstaAktivnosti> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
   
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista=broker.getAll(objekat, kljuc);
    }
    
}
