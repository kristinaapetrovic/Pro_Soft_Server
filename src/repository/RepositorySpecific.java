/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import java.util.List;
import model.Aktivnost;
import model.JeSponzor;
import model.MSS;
import model.Menadzer;
import model.Mesto;
import model.OpstiDomenskiObjekat;
import model.Projekat;
import model.Sponzor;
import model.StrucnaSprema;
import model.VrstaAktivnosti;

/**
 *
 * @author Korisnik
 */
public interface RepositorySpecific {
    
    boolean readSponzorWithMesto(List<Sponzor> lista);

    boolean readUgovor(List<Projekat> lista);

    boolean readMestoWithCondition(Mesto mesto, List<Mesto> listaMesta);

    boolean readVrstaAktWithCondition(VrstaAktivnosti vrstaAkt, List<VrstaAktivnosti> lista);

    boolean readSSWithCondition(StrucnaSprema ss, List<StrucnaSprema> listaSS);

    boolean readSponzorkWithCondition(Sponzor sponzor, List<Sponzor> lista);

    boolean readUgovorWithCondition(OpstiDomenskiObjekat param, List<Projekat> listaUgovora);

    boolean readSSByM(List<StrucnaSprema> lista, Menadzer m);

    boolean readAktByUg(Projekat pu, List<Aktivnost> lista);

    boolean readMenWithCondition(Menadzer kriterijumMen, List<Menadzer> lista);

    boolean readSponzorByProject(Projekat pu, List<JeSponzor> lista);

    boolean readMSS(List<MSS> lista, Menadzer men);

    public boolean prijavi(Menadzer menadzer);
}
