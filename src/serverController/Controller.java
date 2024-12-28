/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serverController;

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
import operacija.aktivnost.KreirajAktivnost;
import operacija.aktivnost.PromeniAktivnost;
import operacija.aktivnost.VratiListuAktivnosti;
import operacija.jesponzor.KreirajJeSponzor;
import operacija.jesponzor.VratiListuJeSponzor;
import operacija.menadzer.KreirajMenadzer;
import operacija.menadzer.ObrisiMenadzer;
import operacija.mesto.KreirajMesto;
import operacija.sponzor.KreirajSponzor;
import operacija.vrstaaktivnosti.KreirajVrstaAktivnosti;
import operacija.mesto.ObrisiMesto;
import operacija.sponzor.ObrisiSponzor;
import operacija.strucnasprema.ObrisiStrucnaSprema;
import operacija.vrstaaktivnosti.ObrisiVrstaAktivnosti;
import operacija.menadzer.PrijavaOperacija;
import operacija.menadzer.PromeniMenadzer;
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
import operacija.mss.KreirajMSS;
import operacija.mss.ObrisiMSS;
import operacija.projekat.KreirajUgovor;
import operacija.projekat.PretraziProjekat;
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
    private Menadzer ulogovani;

    public Menadzer getUlogovani() {
        return ulogovani;
    }
    
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
        operacija.izvrsi(menadzer, "prijava");
        ulogovani=operacija.getUlogovani();
        return ulogovani;
    }

    public List<Menadzer> vratiListuSviMenadzer() throws Exception {
        VratiListuSviMenadzer operacija = new VratiListuSviMenadzer();
        operacija.izvrsi(new Menadzer(), "citanje");
        return operacija.getLista();
    }

    public List<StrucnaSprema> vratiListuSviStrucnaSprema() throws Exception {
        VratiListuSviStrucnaSprema operacija = new VratiListuSviStrucnaSprema();
        operacija.izvrsi(new StrucnaSprema(), "citanje");
        return operacija.getLista();
    }

    public List<MSS> vratiListuMSS(Menadzer menadzer) throws Exception {
        VratiListuMSS operacija = new VratiListuMSS(menadzer);
        operacija.izvrsi(new MSS(), "citanje");
        return operacija.getLista();

    }

    public List<Mesto> vratiListuSviMesto() throws Exception {
        VratiListuSviMesto operacija = new VratiListuSviMesto();
        operacija.izvrsi(new Mesto(), "citanje");
        return operacija.getLista();
    }

    public List<VrstaAktivnosti> vratiListuSviVrstaAktivnosti() throws Exception {
        VratiListuSviVrstaAktivnosti operacija = new VratiListuSviVrstaAktivnosti();
        operacija.izvrsi(new VrstaAktivnosti(), "citanje");
        return operacija.getLista();
    }

    public List<Projekat> vratiListuSviProjekat() throws Exception {
        VratiListuSviProjekat operacija = new VratiListuSviProjekat();
        operacija.izvrsi(new Projekat(), "citanje");
        return operacija.getLista();
    }

    public List<Sponzor> vratiListuSviSponzor() throws Exception {
        VratiListuSviSponzor operacija = new VratiListuSviSponzor();
        operacija.izvrsi(new Sponzor(), "citanje");
        return operacija.getLista();
    }

    public List<Menadzer> vratiListuMenadzer(Menadzer menadzer) throws Exception {
        VratiListuMenadzer operacija = new VratiListuMenadzer(menadzer);

        operacija.izvrsi(menadzer, "citanje");

        return operacija.getLista();
    }

    public List<Mesto> vratiListuMesto(Mesto mesto) throws Exception {
        VratiListuMesto operacija = new VratiListuMesto(mesto);
        operacija.izvrsi(mesto, "citanje");
        return operacija.getLista();
    }

    public List<Sponzor> vratiListuSponzor(Sponzor sponzor) throws Exception {
        VratiListuSponzor operacija = new VratiListuSponzor(sponzor);
        operacija.izvrsi(sponzor, "citanje");
        return operacija.getLista();
    }

    public List<StrucnaSprema> vratiListuStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        VratiListuStrucnaSprema operacija = new VratiListuStrucnaSprema(strucnaSprema);
        operacija.izvrsi(strucnaSprema, "citanje");
        return operacija.getLista();
    }

    public List<VrstaAktivnosti> vratiListuVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        VratiListuVrstaAktivnosti operacija = new VratiListuVrstaAktivnosti(vrstaAktivnosti);
        operacija.izvrsi(vrstaAktivnosti, "citanje");
        return operacija.getLista();
    }

    public void kreirajMesto(Mesto mesto) throws Exception {
        KreirajMesto operacija = new KreirajMesto();
        operacija.izvrsi(mesto, "kreiranje");
    }

    public void obrisiMesto(Mesto mesto) throws Exception {
        ObrisiMesto operacija = new ObrisiMesto();
        operacija.izvrsi(mesto, "brisanje");
    }

    public void promeniMesto(Mesto mesto) throws Exception {
        PromeniMesto operacija = new PromeniMesto();
        operacija.izvrsi(mesto, "azuriranje");
    }

    public void kreirajSponzor(Sponzor sponzor) throws Exception {
        KreirajSponzor operacija = new KreirajSponzor();
        operacija.izvrsi(sponzor, "kreiranje");
    }

    public void obrisiSponzor(Sponzor sponzor) throws Exception {

        ObrisiSponzor operacija = new ObrisiSponzor();
        operacija.izvrsi(sponzor, "brisanje");

    }

    public void promeniSponzor(Sponzor sponzor) throws Exception {
        PromeniSponzor operacija = new PromeniSponzor();
        operacija.izvrsi(sponzor, "azuriranje");
    }

    public void promeniStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        PromeniStrucnaSprema operacija = new PromeniStrucnaSprema();
        operacija.izvrsi(strucnaSprema, "azuriranje");
    }

    public void obrisiStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        ObrisiStrucnaSprema operacija = new ObrisiStrucnaSprema();
        operacija.izvrsi(strucnaSprema, "brisanje");
    }

    public void ubaciStrucnaSprema(StrucnaSprema strucnaSprema) throws Exception {
        UbaciStrucnaSprema operacija = new UbaciStrucnaSprema();
        operacija.izvrsi(strucnaSprema, "kreiranje");
    }

    public void promeniVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        PromeniVrstaAktivnosti operacija = new PromeniVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, "azuriranje");
    }

    public void obrisiVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        ObrisiVrstaAktivnosti operacija = new ObrisiVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, "brisanje");
    }

    public void kreirajVrstaAktivnosti(VrstaAktivnosti vrstaAktivnosti) throws Exception {
        KreirajVrstaAktivnosti operacija = new KreirajVrstaAktivnosti();
        operacija.izvrsi(vrstaAktivnosti, "kreiranje");
    }

    public void kreirajMSS(MSS mss) throws Exception {
        KreirajMSS operacija = new KreirajMSS();
        operacija.izvrsi(mss, "kreiranje");
    }

    public void obrisiMSS(MSS mss) throws Exception {
        ObrisiMSS operacija = new ObrisiMSS();
        operacija.izvrsi(mss, "brisanje");
    }

    public void obrisiMenadzer(Menadzer menadzer) throws Exception {
        ObrisiMenadzer operacija = new ObrisiMenadzer();
        operacija.izvrsi(menadzer, "brisanje");
    }

    public void promeniMenadzer(Menadzer menadzer) throws Exception {
        PromeniMenadzer operacija = new PromeniMenadzer();
        operacija.izvrsi(menadzer, "azuriranje");
    }

    public Menadzer kreirajMenadzer(Menadzer menadzer) throws Exception {
        KreirajMenadzer operacija = new KreirajMenadzer();
        operacija.izvrsi(menadzer, "kreiranje");
        return operacija.getMenadzer();
    }

    public List<Projekat> pretraziProjekat(OpstiDomenskiObjekat opstiDomenskiObjekat) throws Exception {
        PretraziProjekat operacija = new PretraziProjekat();
        operacija.izvrsi(opstiDomenskiObjekat, "citanje");
        return operacija.getLista();
    }

    public List<Aktivnost> vratiListuAktivnost(Projekat projekat) throws Exception {
        VratiListuAktivnosti operacija = new VratiListuAktivnosti();
        operacija.izvrsi(projekat, "citanje");
        return operacija.getLista();
    }

    public List<JeSponzor> vratiListuJeSponzor(Projekat projekat) throws Exception {
        VratiListuJeSponzor operacija = new VratiListuJeSponzor();
        operacija.izvrsi(projekat, "citanje");
        return operacija.getLista();
    }

    public void kreirajUgovor(Projekat projekat) throws Exception {
        KreirajUgovor operacija=new KreirajUgovor();
        operacija.izvrsi(projekat, "kreiranje");
    }

    public void kreiraAktivnost(Aktivnost aktivnost) throws Exception {
        KreirajAktivnost operacija=new KreirajAktivnost();
        operacija.izvrsi(aktivnost, "kreiranje");
    }

    public void kreirajJeSponzor(JeSponzor jeSponzor) throws Exception {
        KreirajJeSponzor operacija=new KreirajJeSponzor();
        operacija.izvrsi(jeSponzor, "kreiranje");
    }

    public void promeniAktivnost(Aktivnost aktivnost) throws Exception {
        PromeniAktivnost operacija=new PromeniAktivnost();
        operacija.izvrsi(aktivnost, "azuriranje");
    }

}
