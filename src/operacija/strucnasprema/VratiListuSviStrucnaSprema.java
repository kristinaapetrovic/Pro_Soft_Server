/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.strucnasprema;

import java.util.ArrayList;
import java.util.List;
import model.StrucnaSprema;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviStrucnaSprema  extends ApstraktnaGenerickaOperacija{

    List<StrucnaSprema> lista=new ArrayList<>();

    public List<StrucnaSprema> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        lista=broker.getAll(objekat, null);
    }
    
}
