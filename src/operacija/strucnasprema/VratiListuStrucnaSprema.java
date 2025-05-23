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
public class VratiListuStrucnaSprema extends ApstraktnaGenerickaOperacija{
    List<StrucnaSprema> lista=new ArrayList<>();
    StrucnaSprema ss;

    public VratiListuStrucnaSprema(StrucnaSprema ss) {
        this.ss = ss;
    }

    public List<StrucnaSprema> getLista() {
        return lista;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {
   
    }

    @Override
    protected void izvrsiOperaciju(Object objekat ) throws Exception {
        brokerSpecific.readSSWithCondition(ss, lista);
    }
    
}
