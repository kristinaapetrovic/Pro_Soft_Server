/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverController;

import java.util.List;
import model.MSS;
import model.Menadzer;
import model.Mesto;
import model.Projekat;
import model.Sponzor;
import model.StrucnaSprema;
import model.VrstaAktivnosti;
import operacija.mesto.KreirajMesto;
import operacija.sponzor.KreirajSponzor;
import operacija.vrstaaktivnosti.KreirajVrstaAktivnosti;
import operacija.mesto.ObrisiMesto;
import operacija.sponzor.ObrisiSponzor;
import operacija.strucnasprema.ObrisiStrucnaSprema;
import operacija.vrstaaktivnosti.ObrisiVrstaAktivnosti;
import operacija.menadzer.PrijavaOperacija;
import operacija.mesto.PromeniMesto;
import operacija.sponzor.PromeniSponzor;
import operacija.strucnasprema.PromeniStrucnaSprema;
import operacija.vrstaaktivnosti.PromeniVrstaAktivnosti;
import operacija.strucnasprema.UbaciStrucnaSprema;
import operacija.mss.VratiListuMSS;
import operacija.menadzer.VratiListuMenadzer;
import operacija.mesto.VratiListuMesto;
import operacija.sponzor.VratiListuSponzor;
import operacija.strucnasprema.VratiListuStrucnaSprema;
import operacija.menadzer.VratiListuSviMenadzer;
import operacija.mesto.VratiListuSviMesto;
import operacija.projekat.VratiListuSviProjekat;
import operacija.sponzor.VratiListuSviSponzor;
import operacija.strucnasprema.VratiListuSviStrucnaSprema;
import operacija.vrstaaktivnosti.VratiListuSviVrstaAktivnosti;
import operacija.vrstaaktivnosti.VratiListuVrstaAktivnosti;

/**
 *
 * @author Korisnik
 */
public class Controller {

    private static Controller instance;

    private Controller() {

    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public Menadzer prijava(Menadzer menadzer) throws Exception {
        PrijavaOperacija operacija = new PrijavaOperacija();
        operacija.izvrsi(menadzer, null);
        return operacija.getUlogovani();
    }

    public List<Menadzer> vratiListuSviMenadzer() throws Exception {
        VratiListuSviMenadzer operacija = new VratiListuSviMenadzer();
        operacija.izvrsi(new Menadzer(), null);
        return operacija.getLista();
    }

    public List<StrucnaSprema> vratiListuSviStrucnaSprema() throws Exception {
        VratiListuSviStrucnaSprema operacija = new VratiListuSviStrucnaSprema();
        operacija.izvrsi(new StrucnaSprema(), null);
        return operacija.getLista();
    }

    public List<MSS> vratiListuMSS(Menadzer menadzer) throws Exception {
        VratiListuMSS operacija = new VratiListuMSS(menadzer);
        operacija.izvrsi(new MSS(), null);
        return operacija.getLista();

    }

    public List<Mesto> vratiListuSviMesto() throws Exception {
        VratiListuSviMesto operacija = new VratiListuSviMesto();
        operacija.izvrsi(new Mesto(), null);
        return operacija.getLista();
    }

    public List<VrstaAktivnosti> vratiListuSviVrstaAktivnosti() throws Exception {
        VratiListuSviVrstaAktivnosti operacija = new VratiListuSviVrstaAktivnosti();
        operacija.izvrsi(new VrstaAktivnosti(), null);
        return operacija.getLista();
    }

    public List<Projekat> vratiListuSviProjekat() throws Exception {
        VratiListuSviProjekat operacija = new VratiListuSviProjekat();
        operacija.izvrsi(new Projekat(), null);
        return operacija.getLista();
    }

    public List<Sponzor> vratiListuSviSponzor() throws Exception {
        VratiListuSviSponzor operacija = new VratiListuSviSponzor();
        operacija.izvrsi(new Sponzor(), null);
        return operacija.getLista();
    }

    public List<Menadzer> vratiListuMenadzer(Menadzer menadzer) throws Exception {
        VratiListuMenadzer operacija = new VratiListuMenadzer(menadzer);

        operacija.izvrsi(menadzer, null);

        return operacija.getLista();
    }

    public List<Mesto> vratiListuMesto(Mesto mesto) throws Exception {
        VratiListuMesto operacija = new VratiListuMesto(mesto);
        operacija.izvrsi(mesto, null);
        return operacija.getLista();
    }

    public List<Sponzor> vratiListuSponzor(Sponzor sponzor) throws Exception {
        VratiListuSponzor operacija = new VratiListuSponzor(sponzor);
        operacija.izvrsi(sponzor, null);
        return operacija.getLista();
    }

    public List<StrucnaSprema> vratiListuStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        VratiListuStrucnaSprema operacija = new VratiListuStrucnaSprema(strucnaSprema);
        operacija.izvrsi(strucnaSprema, null);
        return operacija.getLista();
    }

    public List<VrstaAktivnosti> vratiListuVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        VratiListuVrstaAktivnosti operacija = new VratiListuVrstaAktivnosti(vrstaAktivnosti);
        operacija.izvrsi(vrstaAktivnosti, null);
        return operacija.getLista();
    }

    public void kreirajMesto(Mesto mesto) throws Exception {
        KreirajMesto operacija = new KreirajMesto();
        operacija.izvrsi(mesto, null);
    }

    public void obrisiMesto(Mesto mesto) throws Exception {
        ObrisiMesto operacija = new ObrisiMesto();
        operacija.izvrsi(mesto, null);
    }

    public void promeniMesto(Mesto mesto) throws Exception {
        PromeniMesto operacija = new PromeniMesto();
        operacija.izvrsi(mesto, null);
    }

    public void kreirajSponzor(Sponzor sponzor) throws Exception {
        KreirajSponzor operacija = new KreirajSponzor();
        operacija.izvrsi(sponzor, null);
    }

    public void obrisiSponzor(Sponzor sponzor) throws Exception {

        ObrisiSponzor operacija = new ObrisiSponzor();
        operacija.izvrsi(sponzor, null);

    }

    public void promeniSponzor(Sponzor sponzor) throws Exception {
        PromeniSponzor operacija=new PromeniSponzor();
        operacija.izvrsi(sponzor, null);
    }

    public void promeniStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        PromeniStrucnaSprema operacija=new PromeniStrucnaSprema();
        operacija.izvrsi(strucnaSprema, null);
    }

    public void obrisiStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        ObrisiStrucnaSprema operacija=new ObrisiStrucnaSprema();
        operacija.izvrsi(strucnaSprema, null);
    }

    public void ubaciStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        UbaciStrucnaSprema operacija=new UbaciStrucnaSprema();
        operacija.izvrsi(strucnaSprema, null);
    }

    public void promeniVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        PromeniVrstaAktivnosti operacija=new PromeniVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, null);
    }

    public void obrisiVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        ObrisiVrstaAktivnosti operacija = new ObrisiVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, null);
    }

    public void kreirajVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        KreirajVrstaAktivnosti operacija= new KreirajVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, null);
    }

}
