/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mss;

import java.util.ArrayList;
import java.util.List;
import model.MSS;
import model.Menadzer;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuMSS extends ApstraktnaGenerickaOperacija {


    private List<MSS> lista=new ArrayList<>();
    private Menadzer men;
    public List<MSS> getLista() {
        return lista;
    }

    public VratiListuMSS(Menadzer men) {
        this.men = men;
    }

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.readMSS(lista, men);
     
    }

}
