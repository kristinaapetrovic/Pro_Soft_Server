/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import konekcija.Konekcija;
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
import repository.db.DbRepository;
import serverController.Controller;

/**
 *
 * @author Korisnik
 */
public class DbRepositoryGeneric implements DbRepository<OpstiDomenskiObjekat> {

    private Konekcija con = Konekcija.getInstance();
    private Statement st;

    @Override
    public List<OpstiDomenskiObjekat> getAll(OpstiDomenskiObjekat param) throws Exception {
        try {
            List<OpstiDomenskiObjekat> lista = new ArrayList<>();
            st = con.getConnection().createStatement();
            String upit = "SELECT * FROM " + param.vratiImeKlase() + " " + param.nijeObrisan();

            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = param.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.Napuni(rs)) {
                        lista.add(noviObjekat);
                    }
                } catch (Exception ex) {

                }
            }
            rs.close();
            st.close();

            return lista;
        } catch (SQLException ex) {

            return null;
        }
    }

    @Override
    public void add(OpstiDomenskiObjekat param) throws Exception {
        try {
            st = con.getConnection().createStatement();
            String upit = "INSERT INTO " + param.vratiImeKlase() + " " + param.vratiListuAtributa() + "" + " VALUES " + param.vratiVrednostiAtributa();
            st.executeUpdate(upit);
            st.close();

        } catch (SQLException ex) {

            System.out.println("Neuspesno ubacivanje objekta " + param.vratiImeKlase() + " u bazu");

        }
        System.out.println("Objekat " + param.vratiImeKlase() + " je uspesno ubacen u bazu");

    }

    @Override
    public void edit(OpstiDomenskiObjekat param) throws Exception {
        try {
            st = con.getConnection().createStatement();
            String upit = "UPDATE " + param.vratiImeKlase() + " SET " + param.postaviVrednostiAtributa() + " WHERE " + param.vratiUslovPostoji();
            st.executeUpdate(upit);
            st.close();
        } catch (SQLException ex) {

            System.out.println("Neuspesna promena objekta " + param.vratiImeKlase() + " iz baze");

        }
        System.out.println("Objekat " + param.vratiImeKlase() + " je uspesno promenjen u bazi");

    }

    @Override
    public void delete(OpstiDomenskiObjekat param) throws Exception {

        try {
            st = con.getConnection().createStatement();
            String upit = "UPDATE " + param.vratiImeKlase() + " SET " + param.obrazacZaBrisanje() + " WHERE " + param.vratiUslovZaNadjiSlog();

            st.executeUpdate(upit);
            st.close();

        } catch (SQLException ex) {

            System.out.println("Neuspesno brisanje objekta " + param.vratiImeKlase() + " iz baze");

        }

        System.out.println("Objekat " + param.vratiImeKlase() + " je uspesno obrisan iz baze");

    }

    @Override
    public List<OpstiDomenskiObjekat> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean existsInBD(OpstiDomenskiObjekat odo) {

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovPostoji();
            slogovi = st.executeQuery(upit);
            boolean postoji = slogovi.next();
            if (postoji) {
                System.out.println("U bazi postoji objekat " + odo.vratiImeKlase());
                return true;
            }

            System.out.println("U bazi ne postoji objekat " + odo.vratiImeKlase());

            return false;
        } catch (SQLException ex) {

            System.out.println("Neuspesna provera postojanja");
            return false;
        }
    }

    @Override
    public boolean isDeleted(OpstiDomenskiObjekat odo) {

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovPostoji() + " AND " + odo.obrazacZaBrisanje();
            slogovi = st.executeQuery(upit);
            boolean postoji = slogovi.next();
            if (postoji) {
                System.out.println("U bazi postoji objekat " + odo.vratiImeKlase());
                return true;
            }

            System.out.println("U bazi ne postoji objekat " + odo.vratiImeKlase());

            return false;
        } catch (SQLException ex) {

            System.out.println("Neuspesna provera postojanja");
            return false;
        }
    }

    @Override
    public boolean readSponzorWithMesto(List<Sponzor> lista) {
        try {

            st = con.getConnection().createStatement();
            String upit = "SELECT * FROM sponzor s JOIN mesto m ON s.postanskiBroj=m.postanskiBroj WHERE obrisanGI=0";
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                Mesto mesto = new Mesto();
                mesto.Napuni(rs);
                Sponzor glavniIzvodjac = new Sponzor();
                glavniIzvodjac.Napuni(rs);
                glavniIzvodjac.setMesto(mesto);
                lista.add(glavniIzvodjac);
            }
            return true;
        } catch (SQLException ex) {

            System.out.println("Neuspesno citanje objekta radnik iz baze");
            return false;
        }

    }

    @Override
    public boolean readUgovor(List<Projekat> lista) {

        try {
            String upit = "SELECT * FROM projektniugovor u JOIN menadzer men USING (jmbg)";
            st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while (rs.next()) {

                Menadzer menadzer = new Menadzer();
                menadzer.Napuni(rs);

                Projekat ugovor = new Projekat();
                ugovor.Napuni(rs);

                ugovor.setMenadzer(menadzer);

                lista.add(ugovor);
            }

            return true;
        } catch (SQLException ex) {

            System.out.println("Neuspesno citanje objekta ugovor o radu iz baze");
            return false;
        }
    }

    @Override
    public boolean readMestoWithCondition(Mesto mesto, List<Mesto> listaMesta) {

        String upit = "";
        if (existsInBD(mesto) && mesto.getNazivMesto().isEmpty()) {
            upit = "SELECT * FROM mesto WHERE " + mesto.vratiUslovZaNadjiSlog();
        } else if (!existsInBD(mesto) && mesto.getNazivMesto().isEmpty()) {
            return false;
        } else if (!existsInBD(mesto) && !mesto.getNazivMesto().isEmpty()) {
            upit = "SELECT * FROM mesto WHERE " + mesto.vratiUslovZaNadjiSlogove();
        } else {
            upit = "SELECT * FROM mesto WHERE " + mesto.vratiUslovZaNadjiSlog() + " OR " + mesto.vratiUslovZaNadjiSlogove();
        }

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            while (slogovi.next()) {
                Mesto novoMesto = new Mesto();
                novoMesto.Napuni(slogovi);
                listaMesta.add(novoMesto);
            }

        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    public boolean readVrstaAktWithCondition(VrstaAktivnosti vrstaAkt, List<VrstaAktivnosti> lista) {
        String upit = "SELECT * FROM vrstaaktivnosti WHERE " + vrstaAkt.vratiUslovZaNadjiSlogove();

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            while (slogovi.next()) {
                VrstaAktivnosti va  = new VrstaAktivnosti();
                va.Napuni(slogovi);
                lista.add(va);
            }

        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readSSWithCondition(StrucnaSprema ss, List<StrucnaSprema> listaSS) {
        String upit = "SELECT * FROM strucnasprema WHERE " + ss.vratiUslovZaNadjiSlogove();

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            while (slogovi.next()) {
                StrucnaSprema novaSS = new StrucnaSprema();
                novaSS.Napuni(slogovi);
                listaSS.add(novaSS);
            }

        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readSponzorkWithCondition(Sponzor sponzor, List<Sponzor> lista) {

        String upit = "";
        if (sponzor.getNazivFirme().isEmpty() && sponzor.getMesto() != null) {
            upit = "SELECT * FROM sponzor g JOIN mesto m USING (postanskiBroj) WHERE " + sponzor.vratiUslovZaNadjiSlogove();
        } else if (!sponzor.getNazivFirme().isEmpty() && sponzor.getMesto() == null) {
            upit = "SELECT * FROM sponzor g JOIN mesto m USING (postanskiBroj) WHERE nazivFirme LIKE LOWER('" + sponzor.getNazivFirme().toLowerCase() + "%')";
        } else {
            upit = "SELECT * FROM sponzor g JOIN mesto m USING (postanskiBroj) WHERE " + sponzor.vratiUslovZaNadjiSlogove() + " OR " + "nazivFirme LIKE LOWER('" + sponzor.getNazivFirme().toLowerCase() + "%')";
        }

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            while (slogovi.next()) {
                Mesto mesto = new Mesto();
                mesto.Napuni(slogovi);
                Sponzor gi = new Sponzor();
                gi.Napuni(slogovi);
                gi.setMesto(mesto);
                lista.add(gi);
            }

        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readUgovorWithCondition(OpstiDomenskiObjekat odo, List<Projekat> listaUgovora) {

        String upit = "SELECT DISTINCT u.*, men.*\n"
                + "FROM projektniugovor u \n"
                + "LEFT JOIN jesponzor USING (regBroj) \n"
                + "LEFT JOIN sponzor g USING (maticniBroj)\n"
                + "LEFT JOIN menadzer men USING (jmbg)\n"
                + "LEFT JOIN mesto m USING (postanskiBroj)\n"
                + "LEFT JOIN aktivnost a USING (regBroj)\n"
                + "LEFT JOIN vrstaaktivnosti va USING (idVrstaAktivnosti) \n"
                + "WHERE " + odo.vratiUslovZaNadjiSlog();

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            int provera = 0;

            while (slogovi.next()) {
                provera = 1;
                //Mesto mesto = new Mesto();
                //Sponzor gi = new Sponzor();
                Menadzer menadzer = new Menadzer();
                Projekat ugovor = new Projekat();

                //mesto.Napuni(slogovi);
//                gi.Napuni(slogovi);
//                gi.setMesto(mesto);
                menadzer.Napuni(slogovi);

                ugovor.Napuni(slogovi);

                ugovor.setMenadzer(menadzer);

                if (!listaUgovora.contains(ugovor)) {
                    listaUgovora.add(ugovor);
                }

            }

            if (provera == 0) {
                return false;
            }

        } catch (SQLException ex) {

            return false;
        }

        return listaUgovora.size() != 0;
    }

    @Override
    public boolean readSSByM(List<StrucnaSprema> lista, Menadzer m) {

        String upit = "SELECT * FROM mss ms JOIN menadzer m USING (jmbg) JOIN strucnasprema s USING (idStrucnaSprema) WHERE " + m.vratiUslovZaNadjiSlog();

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);
            while (slogovi.next()) {
                StrucnaSprema ss = new StrucnaSprema();
                ss.Napuni(slogovi);
                lista.add(ss);
            }
        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readAktByUg(Projekat pu, List<Aktivnost> lista) {

        String upit = "SELECT * FROM aktivnost a LEFT JOIN projektniugovor u USING (regBroj) LEFT JOIN vrstaaktivnosti va USING (idVrstaAktivnosti) WHERE " + pu.vratiUslovZaNadjiSlog();

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);
            while (slogovi.next()) {
                Projekat pu1 = new Projekat();
                pu1.Napuni(slogovi);

                VrstaAktivnosti vak = new VrstaAktivnosti();
                vak.Napuni(slogovi);

                Aktivnost akt = new Aktivnost();
                akt.Napuni(slogovi);
                akt.setVrstaAktivnosti(vak);
                akt.setProjektniUgovor(pu1);

                lista.add(akt);
            }
        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readMenWithCondition(Menadzer kriterijumMen, List<Menadzer> lista) {
        String upit = "SELECT * FROM menadzer WHERE imePrezime LIKE LOWER('%" + kriterijumMen.getImePrezime() + "%')";

        try {
            ResultSet slogovi;
            st = con.getConnection().createStatement();
            slogovi = st.executeQuery(upit);

            while (slogovi.next()) {
                Menadzer menadzer = new Menadzer();
                menadzer.Napuni(slogovi);
                lista.add(menadzer);
            }

        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readSponzorByProject(Projekat pu, List<JeSponzor> lista) {

        String upit = "SELECT * FROM jesponzor js JOIN projektniugovor p USING (regBroj) JOIN sponzor s USING (maticniBroj) JOIN mesto m USING (postanskiBroj) WHERE " + pu.vratiUslovZaNadjiSlog();

        try {
            ResultSet rs;
            st = con.getConnection().createStatement();
            rs = st.executeQuery(upit);
            while (rs.next()) {
                JeSponzor js = new JeSponzor();
                Sponzor sponzor = new Sponzor();
                Mesto mesto = new Mesto();

                mesto.Napuni(rs);

                sponzor.Napuni(rs);
                sponzor.setMesto(mesto);

                js.setProjekat(pu);
                js.setSponzor(sponzor);
                js.Napuni(rs);
                lista.add(js);

            }
        } catch (SQLException ex) {

            return false;
        }

        return true;
    }

    @Override
    public boolean readMSS(List<MSS> lista, Menadzer men) {

        String upit = "SELECT * FROM mss JOIN menadzer men USING (jmbg) JOIN strucnasprema ss using (idStrucnaSprema) WHERE " + men.vratiUslovZaNadjiSlog() + " AND obrisanaMSS=0";

        try {
            ResultSet rs;
            st = con.getConnection().createStatement();
            rs = st.executeQuery(upit);

            while (rs.next()) {
                StrucnaSprema ss = new StrucnaSprema();
                Menadzer menadzer = new Menadzer();
                ss.Napuni(rs);
                menadzer.Napuni(rs);
                MSS mss = new MSS();
                mss.Napuni(rs);
                mss.setMenadzer(menadzer);
                mss.setStrucnaSprema(ss);

                lista.add(mss);
            }

        } catch (SQLException ex) {
            return false;
        }

        return true;
    }

    @Override
    public OpstiDomenskiObjekat readByPK(OpstiDomenskiObjekat odo) {
        try {

            st = con.getConnection().createStatement();
            String upit = "SELECT * FROM " + odo.vratiImeKlase() + " WHERE " + odo.vratiUslovZaNadjiSlog();
            ResultSet rs = st.executeQuery(upit);

            while (rs.next()) {
                OpstiDomenskiObjekat noviObjekat;
                try {
                    noviObjekat = odo.getClass().getDeclaredConstructor().newInstance();
                    if (noviObjekat.Napuni(rs)) {
                        return noviObjekat;
                    }
                } catch (Exception ex) {

                }
            }

            return null;
        } catch (SQLException ex) {
            return null;
        }
    }

    @Override
    public void history(Object object, String kljuc) {
        try {
            OpstiDomenskiObjekat odo = (OpstiDomenskiObjekat) object;
            Menadzer izvrsilac = Controller.getInstance().getUlogovani();
            OpstiDomenskiObjekat stari = readByPK(odo);
            String jsonStari = "null";
            String jsonNovi = "null";
            if (kljuc.equalsIgnoreCase("azuriranje")) {
                jsonStari = stari.toJson();
                jsonNovi = odo.toJson();
            } else if (kljuc.equalsIgnoreCase("brisanje")) {
                jsonStari = odo.toJson();
            } else {//kreiranje
                jsonNovi = odo.toJson();
            }
            
            java.util.Date datum = new java.util.Date();
            java.sql.Date datumSQL = new java.sql.Date(datum.getTime());
            String upit = "INSERT INTO history (vreme,menadzer,vrstaOperacije,objekatBaze,staraVrednost,novaVrednost) VALUES ('" + datumSQL + "','" + izvrsilac.getJmbg() + "','" + kljuc + "','" + odo.vratiImeKlase() + "','" + jsonStari + "','" + jsonNovi + "')";
            st = con.getConnection().createStatement();
            
            st.executeUpdate(upit);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
