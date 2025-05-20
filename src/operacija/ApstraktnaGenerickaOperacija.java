/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import java.sql.SQLException;
import repository.db.DbRepository;
import repository.db.impl.DbRepositoryGenericImpl;
import repository.RepositoryGeneric;
import repository.RepositorySpecific;
import repository.db.impl.DbRepositorySpecificImpl;
/**
 *
 * @author Korisnik
 */
public abstract class ApstraktnaGenerickaOperacija {

    protected final RepositoryGeneric brokerGeneric;
    protected final RepositorySpecific brokerSpecific;

    public ApstraktnaGenerickaOperacija() {
        this.brokerGeneric = new DbRepositoryGenericImpl();
        this.brokerSpecific=new DbRepositorySpecificImpl();
    }

    public final void izvrsi(Object objekat, String kljuc) throws Exception {

        try {
            preduslovi(objekat);
            zaspocniTransakciju();
            izvrsiOperaciju(objekat);
            if (!kljuc.equalsIgnoreCase("citanje") && !kljuc.equalsIgnoreCase("prijava")) {
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
        ((DbRepository) brokerGeneric).connect();
    }

    protected abstract void izvrsiOperaciju(Object objekat) throws Exception;

    private void potvrdiTransakciju(Object object, String kljuc) throws SQLException {
        ((DbRepository) brokerGeneric).history(object, kljuc);
        ((DbRepository) brokerGeneric).commit();
    }

    private void ponistiTransakciju() throws SQLException {
        ((DbRepository) brokerGeneric).rollback();
    }

    private void ugasiKonekciju() throws SQLException {
        ((DbRepository) brokerGeneric).disconnect();
    }

}
