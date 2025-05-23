/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija.sponzor;

import java.util.ArrayList;
import java.util.List;
import model.Sponzor;
import operacija.ApstraktnaGenerickaOperacija;

/**
 *
 * @author Korisnik
 */
public class VratiListuSponzor extends ApstraktnaGenerickaOperacija{
    private Sponzor sponzor;
    private List<Sponzor> lista=new ArrayList<>();

    public List<Sponzor> getLista() {
        return lista;
    }

    public VratiListuSponzor(Sponzor sponzor) {
        this.sponzor = sponzor;
    }
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
   
    }

    @Override
    protected void izvrsiOperaciju(Object objekat ) throws Exception {
        brokerSpecific.readSponzorkWithCondition(sponzor, lista);
    }
    
    
    
}
