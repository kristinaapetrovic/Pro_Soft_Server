/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Receiver;
import komunikacija.Request;
import komunikacija.Response;
import komunikacija.Sender;
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
import serverController.Controller;

/**
 *
 * @author Korisnik
 */
public class ObradaKlijentskihZahteva extends Thread {

    Socket socket;
    Sender sender;
    Receiver receiver;
    boolean kraj = false;

    public ObradaKlijentskihZahteva(Socket socket) {
        this.socket = socket;
        sender = new Sender(socket);
        receiver = new Receiver(socket);
    }

    @Override
    public void run() {
        while (!kraj) {
            Request request = (Request) receiver.receive();
            Response response = new Response();
            switch (request.getOperacija()) {
                case komunikacija.Operacija.PRIJAVA: {
                    prijava(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_MENADZERE: {
                    ucitajMenadzere(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_STRUCNE_SPREME: {
                    ucitajStrucneSpreme(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_MSS: {
                    ucitajMSS(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_MESTA: {
                    ucitajMesta(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_VRSTA_AKTIVNOSTI: {
                    ucitajVrstaAktivnosti(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_PROJEKTE: {
                    ucitajProjekte(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_SPONZORE: {
                    ucitajSponzore(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_MENADZERE_FILTER: {
                    ucitajMenadzereFilter(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_MESTA_FILTER: {
                    ucitajMestaFilter(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_SPONZOR_FILTER: {
                    ucitajSponzorFilter(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_SS_FILTER: {
                    ucitajSSFilter(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_VAKT_FILTER: {
                    ucitajVAFilter(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_MESTO: {
                    kreirajMesto(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_MESTO: {
                    obrisiMesto(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_MESTO: {
                    promeniMesto(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_SPONZOR: {
                    kreirajSponzor(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_SPONZOR: {
                    obrisiSponzor(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_SPONZOR: {
                    promeniSponzor(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_STRUCNA_SPREMA: {
                    promeniStrucnaSprema(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_STRUCNA_SPREMA: {
                    obrisiStrucnaSprema(request, response);
                    break;
                }
                case komunikacija.Operacija.UBACI_STRUCNA_SPREMA: {
                    ubaciStrucnaSprema(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_VRSTA_AKTIVNOSTI: {
                    promeniVrstaAktivnosti(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_VRSTA_AKTIVNOSTI: {
                    obrisiVrstaAktivnosti(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_VRSTA_AKTIVNOSTI: {
                    kreirajVrstaAktivnosti(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_MSS: {
                    kreirajMSS(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_MSS: {
                    obrisiMSS(request, response);
                    break;
                }
                case komunikacija.Operacija.OBRISI_MENADZER: {
                    obrisiMenadzer(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_MENADZER: {
                    promeniMenadzer(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_MENADZER: {
                    kreirajMenadzer(request, response);
                }
                case komunikacija.Operacija.PRETRAZI_PROJEKAT: {
                    pretraziProjekat(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_AKTIVNOST_PROJEKTA: {
                    ucitajAktivnostProjekta(request, response);
                    break;
                }
                case komunikacija.Operacija.UCITAJ_JESPONZOR_PROJEKTA: {
                    ucitajJeSponzorProjekta(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_UGOVOR: {
                    kreirajUgovor(request, response);
                    break;
                }
                case komunikacija.Operacija.KREIRAJ_AKTIVNOST: {
                    kreirajAktivnost(request, response);
                    break;

                }
                case komunikacija.Operacija.KREIRAJ_JESPONZOR: {
                    kreirajJeSponzor(request, response);
                    break;
                }
                case komunikacija.Operacija.PROMENI_AKTIVNOST: {
                    promeniAktivnost(request, response);
                    break;

                }

                default:
                    System.out.println("GRESKA, OPERACIJA NE POSTOJI");

            }

            sender.send(response);
        }

    }

    public void prekiniNit() throws IOException {
        kraj = true;
        socket.close();
        interrupt();
    }

    private void prijava(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        try {
            menadzer = Controller.getInstance().prijava(menadzer);
        } catch (Exception ex) {

            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(menadzer);
    }

    private void ucitajMenadzere(Request request, Response response) {
        List<Menadzer> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuSviMenadzer();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajStrucneSpreme(Request request, Response response) {
        List<StrucnaSprema> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviStrucnaSprema();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajMSS(Request request, Response response) {
        List<MSS> lista = new ArrayList<>();
        Menadzer menadzer = (Menadzer) request.getParametar();
        try {
            lista = Controller.getInstance().vratiListuMSS(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lista);
    }

    private void ucitajMesta(Request request, Response response) {
        List<Mesto> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviMesto();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajVrstaAktivnosti(Request request, Response response) {
        List<VrstaAktivnosti> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviVrstaAktivnosti();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajProjekte(Request request, Response response) {
        List<Projekat> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviProjekat();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajSponzore(Request request, Response response) {
        List<Sponzor> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuSviSponzor();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(lista);
    }

    private void ucitajMenadzereFilter(Request request, Response response) {
        List<Menadzer> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuMenadzer((Menadzer) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajMestaFilter(Request request, Response response) {
        List<Mesto> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuMesto((Mesto) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajSponzorFilter(Request request, Response response) {
        List<Sponzor> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSponzor((Sponzor) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajSSFilter(Request request, Response response) {
        List<StrucnaSprema> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuStrucnaSprema((StrucnaSprema) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajVAFilter(Request request, Response response) {
        List<VrstaAktivnosti> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuVrstaAktivnosti((VrstaAktivnosti) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }

        response.setOdgovor(lista);
    }

    private void kreirajMesto(Request request, Response response) {
        try {
            Controller.getInstance().kreirajMesto((Mesto) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void obrisiMesto(Request request, Response response) {
        try {
            Controller.getInstance().obrisiMesto((Mesto) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniMesto(Request request, Response response) {
        try {
            Controller.getInstance().promeniMesto((Mesto) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void kreirajSponzor(Request request, Response response) {
        try {
            Controller.getInstance().kreirajSponzor((Sponzor) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiSponzor(Request request, Response response) {

        try {
            Controller.getInstance().obrisiSponzor((Sponzor) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);

        }

    }

    private void promeniSponzor(Request request, Response response) {
        try {
            Controller.getInstance().promeniSponzor((Sponzor) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniStrucnaSprema(Request request, Response response) {

        try {
            Controller.getInstance().promeniStrucnaSprema((StrucnaSprema) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiStrucnaSprema(Request request, Response response) {
        try {
            Controller.getInstance().obrisiStrucnaSprema((StrucnaSprema) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void ubaciStrucnaSprema(Request request, Response response) {
        try {
            Controller.getInstance().ubaciStrucnaSprema((StrucnaSprema) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void promeniVrstaAktivnosti(Request request, Response response) {

        try {
            Controller.getInstance().promeniVrstaAktivnosti((VrstaAktivnosti) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiVrstaAktivnosti(Request request, Response response) {
        try {
            Controller.getInstance().obrisiVrstaAktivnosti((VrstaAktivnosti) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajVrstaAktivnosti(Request request, Response response) {
        try {
            Controller.getInstance().kreirajVrstaAktivnosti((VrstaAktivnosti) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajMSS(Request request, Response response) {
        try {
            Controller.getInstance().kreirajMSS((MSS) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void obrisiMSS(Request request, Response response) {
        try {
            Controller.getInstance().obrisiMSS((MSS) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiMenadzer(Request request, Response response) {
        try {
            Controller.getInstance().obrisiMenadzer((Menadzer) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniMenadzer(Request request, Response response) {
        try {
            Controller.getInstance().promeniMenadzer((Menadzer) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajMenadzer(Request request, Response response) {
        Menadzer menadzer = (Menadzer) request.getParametar();
        Menadzer men = null;
        try {
            men = Controller.getInstance().kreirajMenadzer(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.setOdgovor(men);

    }

    private void pretraziProjekat(Request request, Response response) {
        List<Projekat> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().pretraziProjekat((OpstiDomenskiObjekat) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajAktivnostProjekta(Request request, Response response) {
        List<Aktivnost> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuAktivnost((Projekat) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajJeSponzorProjekta(Request request, Response response) {
        List<JeSponzor> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuJeSponzor((Projekat) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void kreirajUgovor(Request request, Response response) {
        try {
            Controller.getInstance().kreirajUgovor((Projekat) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajAktivnost(Request request, Response response) {
        try {
            Controller.getInstance().kreiraAktivnost((Aktivnost) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajJeSponzor(Request request, Response response) {
        try {
            Controller.getInstance().kreirajJeSponzor((JeSponzor) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void promeniAktivnost(Request request, Response response) {
        try {
            Controller.getInstance().promeniAktivnost((Aktivnost) request.getParametar());
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

}
