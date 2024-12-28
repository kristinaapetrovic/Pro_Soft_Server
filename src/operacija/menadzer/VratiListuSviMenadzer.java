/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.menadzer;

import java.util.ArrayList;
import java.util.List;
import model.Menadzer;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviMenadzer extends ApstraktnaGenerickaOperacija {

    private List<Menadzer> lista=new ArrayList<>();

    public List<Menadzer> getLista() {
        return lista;
    }


    @Override
    protected void preduslovi(Object objekat) throws Exception {
  
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        lista=broker.getAll(objekat);
    }
    
}
