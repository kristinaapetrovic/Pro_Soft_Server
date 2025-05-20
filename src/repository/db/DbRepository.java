/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;


import java.sql.SQLException;
import konekcija.Konekcija;
import repository.RepositoryGeneric;

/**
 *
 * @author Korisnik
 */
public interface DbRepository<T> extends RepositoryGeneric<T> {

    default public void connect() {
        Konekcija.getInstance().getConnection();
    }

    default public void disconnect() throws SQLException {
        Konekcija.getInstance().getConnection().close();
    }

    default public void commit() throws SQLException {
        Konekcija.getInstance().getConnection().commit();
    }

    default public void rollback() throws SQLException {
        Konekcija.getInstance().getConnection().rollback();
    }
    
    

}
