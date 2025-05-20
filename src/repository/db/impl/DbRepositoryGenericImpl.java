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
public class DbRepositoryGenericImpl implements DbRepository<OpstiDomenskiObjekat> {

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

        st = con.getConnection().createStatement();
        String upit = "INSERT INTO " + param.vratiImeKlase() + " " + param.vratiListuAtributa() + "" + " VALUES " + param.vratiVrednostiAtributa();
        System.out.println(upit);
        st.executeUpdate(upit);
        st.close();

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
            System.out.println(upit);
            st = con.getConnection().createStatement();

            st.executeUpdate(upit);
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbRepositoryGenericImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
