/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.sql.SQLException;
import model.Menadzer;
import model.OpstiDomenskiObjekat;
import repository.Repository;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGeneric;
import serverController.Controller;

/**
 *
 * @author Korisnik
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {

        try {
            preduslovi(objekat);
            zaspocniTransakciju();
            izvrsiOperaciju(objekat);
            if(!kljuc.equalsIgnoreCase("citanje") && !kljuc.equalsIgnoreCase("prijava") ){
                potvrdiTransakciju(objekat, kljuc);
            }
        } catch (Exception ex) {
            ponistiTransakciju();
          
            throw ex;
        } finally {
            // ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object objekat) throws Exception;

    private void zaspocniTransakciju() {
        ((DbRepository) broker).connect();
    }

    protected abstract void izvrsiOperaciju(Object objekat) throws Exception;

    private void potvrdiTransakciju(Object object, String kljuc) throws SQLException {
        ((DbRepository) broker).history(object, kljuc);
        ((DbRepository) broker).commit();
    }

    private void ponistiTransakciju() throws SQLException {
        ((DbRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws SQLException {
        ((DbRepository) broker).disconnect();
    }

    

}
