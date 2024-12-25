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
import operacija.KreirajMesto;
import operacija.KreirajSponzor;
import operacija.ObrisiMesto;
import operacija.ObrisiSponzor;
import operacija.PrijavaOperacija;
import operacija.PromeniMesto;
import operacija.PromeniSponzor;
import operacija.VratiListuMSS;
import operacija.VratiListuMenadzer;
import operacija.VratiListuMesto;
import operacija.VratiListuSponzor;
import operacija.VratiListuStrucnaSprema;
import operacija.VratiListuSviMenadzer;
import operacija.VratiListuSviMesto;
import operacija.VratiListuSviProjekat;
import operacija.VratiListuSviSponzor;
import operacija.VratiListuSviStrucnaSprema;
import operacija.VratiListuSviVrstaAktivnosti;
import operacija.VratiListuVrstaAktivnosti;

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

}
