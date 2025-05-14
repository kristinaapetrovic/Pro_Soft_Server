/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import com.fasterxml.jackson.core.type.TypeReference;
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
        Menadzer menadzer = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);
        try {
            Menadzer men = Controller.getInstance().prijava(menadzer);
            String obj = jsonFormat.JSONFormat.toJson(men);
            response.setOdgovor(obj);

        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
    }

    private void ucitajMenadzere(Request request, Response response) {
        List<Menadzer> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuSviMenadzer();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajStrucneSpreme(Request request, Response response) {
        List<StrucnaSprema> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviStrucnaSprema();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajMSS(Request request, Response response) {
        List<MSS> lista = new ArrayList<>();
        Menadzer menadzer = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);
        try {
            lista = Controller.getInstance().vratiListuMSS(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajMesta(Request request, Response response) {
        List<Mesto> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviMesto();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajVrstaAktivnosti(Request request, Response response) {
        List<VrstaAktivnosti> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviVrstaAktivnosti();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajProjekte(Request request, Response response) {
        List<Projekat> lista = new ArrayList<>();

        try {
            lista = Controller.getInstance().vratiListuSviProjekat();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajSponzore(Request request, Response response) {
        List<Sponzor> lista = new ArrayList<>();
        try {
            lista = Controller.getInstance().vratiListuSviSponzor();
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        System.out.println(lista.get(0).getMaticniBroj());
        response.setOdgovor(lista);
    }

    private void ucitajMenadzereFilter(Request request, Response response) {
        List<Menadzer> lista = new ArrayList<>();
        Menadzer men = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);
        try {
            lista = Controller.getInstance().vratiListuMenadzer(men);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);

    }

    private void ucitajMestaFilter(Request request, Response response) {
        List<Mesto> lista = new ArrayList<>();

        Mesto mesto = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Mesto.class);
        try {
            lista = Controller.getInstance().vratiListuMesto(mesto);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajSponzorFilter(Request request, Response response) {
        List<Sponzor> lista = new ArrayList<>();
        Sponzor sponzor = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Sponzor.class);
        try {
            lista = Controller.getInstance().vratiListuSponzor(sponzor);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        response.setOdgovor(lista);
    }

    private void ucitajSSFilter(Request request, Response response) {
        List<StrucnaSprema> lista = new ArrayList<>();
        StrucnaSprema ss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), StrucnaSprema.class);
        try {
            lista = Controller.getInstance().vratiListuStrucnaSprema(ss);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        response.setOdgovor(lista);
    }

    private void ucitajVAFilter(Request request, Response response) {
        List<VrstaAktivnosti> lista = new ArrayList<>();
        VrstaAktivnosti vakt = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), VrstaAktivnosti.class);
        try {
            lista = Controller.getInstance().vratiListuVrstaAktivnosti((VrstaAktivnosti) request.getParametar());
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            response.setExc(ex);
        }
        response.setOdgovor(lista);
    }

    private void kreirajMesto(Request request, Response response) {
        try {
            Mesto mesto = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Mesto.class);
            Controller.getInstance().kreirajMesto(mesto);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void obrisiMesto(Request request, Response response) {
        Mesto mesto = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Mesto.class);
        try {
            Controller.getInstance().obrisiMesto(mesto);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniMesto(Request request, Response response) {
        Mesto mesto = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Mesto.class);
        try {
            Controller.getInstance().promeniMesto(mesto);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void kreirajSponzor(Request request, Response response) {
        Sponzor sponzor = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Sponzor.class);
        try {
            Controller.getInstance().kreirajSponzor(sponzor);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiSponzor(Request request, Response response) {
        Sponzor sponzor = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Sponzor.class);

        try {
            Controller.getInstance().obrisiSponzor(sponzor);
        } catch (Exception ex) {
            response.setExc(ex);

        }

    }

    private void promeniSponzor(Request request, Response response) {
        Sponzor sponzor = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Sponzor.class);

        try {
            Controller.getInstance().promeniSponzor(sponzor);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniStrucnaSprema(Request request, Response response) {
        StrucnaSprema ss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), StrucnaSprema.class);

        try {
            Controller.getInstance().promeniStrucnaSprema(ss);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiStrucnaSprema(Request request, Response response) {
        StrucnaSprema ss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), StrucnaSprema.class);

        try {
            Controller.getInstance().obrisiStrucnaSprema(ss);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void ubaciStrucnaSprema(Request request, Response response) {
        StrucnaSprema ss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), StrucnaSprema.class);

        try {
            Controller.getInstance().ubaciStrucnaSprema(ss);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void promeniVrstaAktivnosti(Request request, Response response) {
        VrstaAktivnosti va  = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), VrstaAktivnosti.class);

        try {
            Controller.getInstance().promeniVrstaAktivnosti(va);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiVrstaAktivnosti(Request request, Response response) {
        VrstaAktivnosti va  = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), VrstaAktivnosti.class);

        try {
            Controller.getInstance().obrisiVrstaAktivnosti(va);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajVrstaAktivnosti(Request request, Response response) {
        VrstaAktivnosti va  = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), VrstaAktivnosti.class);

        try {
            Controller.getInstance().kreirajVrstaAktivnosti(va);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajMSS(Request request, Response response) {
        MSS mss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), MSS.class);

        try {
            Controller.getInstance().kreirajMSS(mss);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void obrisiMSS(Request request, Response response) {
        MSS mss = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), MSS.class);

        try {
            Controller.getInstance().obrisiMSS(mss);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void obrisiMenadzer(Request request, Response response) {
        Menadzer menadzer = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);

        try {
            Controller.getInstance().obrisiMenadzer(menadzer);
        } catch (Exception ex) {
            response.setExc(ex);
        }

    }

    private void promeniMenadzer(Request request, Response response) {
        Menadzer menadzer = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);

        try {
            Controller.getInstance().promeniMenadzer(menadzer);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajMenadzer(Request request, Response response) {
        Menadzer menadzer = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Menadzer.class);
        Menadzer men = null;
        try {
            men = Controller.getInstance().kreirajMenadzer(menadzer);
        } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        String odg = jsonFormat.JSONFormat.toJson(men);
        response.setOdgovor(odg);
    }

    private void pretraziProjekat(Request request, Response response) {
        List<Projekat> lista = new ArrayList<>();
        OpstiDomenskiObjekat odo = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), new TypeReference<OpstiDomenskiObjekat>() {
        });

        try {
            lista = Controller.getInstance().pretraziProjekat(odo);
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajAktivnostProjekta(Request request, Response response) {
        List<Aktivnost> lista = new ArrayList<>();
        Projekat projekat = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Projekat.class);
        try {
            lista = Controller.getInstance().vratiListuAktivnost(projekat);
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void ucitajJeSponzorProjekta(Request request, Response response) {
        List<JeSponzor> lista = new ArrayList<>();
        Projekat projekat = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Projekat.class);
        try {
            lista = Controller.getInstance().vratiListuJeSponzor(projekat);
        } catch (Exception ex) {
            response.setExc(ex);
        }

        response.setOdgovor(lista);
    }

    private void kreirajUgovor(Request request, Response response) {
        Projekat projekat = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Projekat.class);

        try {
            Controller.getInstance().kreirajUgovor(projekat);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void kreirajAktivnost(Request request, Response response) {
        Aktivnost aktivnost = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Aktivnost.class);

        try {
            Controller.getInstance().kreiraAktivnost(aktivnost);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            response.setExc(ex);

        }
    }

    private void kreirajJeSponzor(Request request, Response response) {
        JeSponzor js = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), JeSponzor.class);

        try {
            Controller.getInstance().kreirajJeSponzor(js);
        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

    private void promeniAktivnost(Request request, Response response) {
        Aktivnost aktivnost = jsonFormat.JSONFormat.fromJson(request.getParametar().toString(), Aktivnost.class);

        try {
            Controller.getInstance().promeniAktivnost(aktivnost);

        } catch (Exception ex) {
            response.setExc(ex);
        }
    }

}
