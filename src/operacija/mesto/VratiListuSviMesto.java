/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.mesto;

import java.util.ArrayList;
import java.util.List;
import model.Mesto;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSviMesto extends ApstraktnaGenerickaOperacija{
    private List<Mesto> lista=new ArrayList<>();

    public List<Mesto> getLista() {
        return lista;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        
    }

    @Override
    protected void izvrsiOperaciju(Object objekat) throws Exception {
        lista=brokerGeneric.getAll(objekat);
    }
    
}
