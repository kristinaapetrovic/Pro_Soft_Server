/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.projekat;

import java.util.ArrayList;
import java.util.List;
import model.Projekat;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviProjekat extends ApstraktnaGenerickaOperacija {

    private List<Projekat> lista = new ArrayList<>();

    @Override
    protected void preduslovi(Object objekat) throws Exception {

    }

    @Override
    protected void izvrsiOperaciju(Object objekat ) throws Exception {
        broker.readUgovor(lista);
    }

    public List<Projekat> getLista() {
        return lista;
    }

}
