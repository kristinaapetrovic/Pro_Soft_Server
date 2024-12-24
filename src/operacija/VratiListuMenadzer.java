/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.util.ArrayList;
import java.util.List;
import model.Menadzer;

/**
 *
 * @author Korisnik
 */
public class VratiListuMenadzer extends ApstraktnaGenerickaOperacija{
    private Menadzer menadzer;
    private List <Menadzer> lista=new ArrayList<>();

    public VratiListuMenadzer(Menadzer menadzer) {
        this.menadzer = menadzer;
    }

    public List<Menadzer> getLista() {
        return lista;
    }
    
    

    @Override
    protected void preduslovi(Object objekat) throws Exception {
  
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.readMenWithCondition(menadzer, lista);
    }
    
    
}
