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
public class VratiListuVrstaAktivnosti extends ApstraktnaGenerickaOperacija{
    private VrstaAktivnosti vakt;
    private List<VrstaAktivnosti> lista=new ArrayList<>();

    public VratiListuVrstaAktivnosti(VrstaAktivnosti vakt) {
        this.vakt = vakt;
    }

    public List<VrstaAktivnosti> getLista() {
        return lista;
    }
    

    @Override
    protected void preduslovi(Object objekat) throws Exception {
   
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.readVrstaAktWithCondition(vakt, lista);
    }
    
}
