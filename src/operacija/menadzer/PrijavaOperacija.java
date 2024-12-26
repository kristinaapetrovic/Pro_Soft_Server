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
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        List<Menadzer> lista=broker.getAll((Menadzer) objekat, null);
        
        for(Menadzer men:lista){
            if(men.isAktivanNalog() && men.getEmail().equals(((Menadzer)objekat).getEmail()) && men.getLozinka().equals(((Menadzer)objekat).getLozinka())){
                ulogovani=men;
                System.out.println(ulogovani.toString()+" "+ulogovani.getDatumRodjenja());
                return;
            }
        }
        ulogovani=null;
    }

}
