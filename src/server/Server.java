/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author Korisnik
 */
public class Server extends Thread {

    boolean kraj = false;
    ServerSocket serverSocket;
    List<ObradaKlijentskihZahteva> klijenti;

    public Server() {
        klijenti = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            int port = 9000;
            try {
                port = Integer.parseInt(konfiguracija.Konfiguracija.getInstance().getPropery("port"));
            } catch (NumberFormatException ex) {
                System.out.println("Greska pri citanju porta");
                return;
            }

            serverSocket = new ServerSocket(port);
            while (!kraj) {
                Socket soket = serverSocket.accept();
                System.out.println("Klijent je povezan");

                ObradaKlijentskihZahteva okz = new ObradaKlijentskihZahteva(soket);
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void zaustaviServer() throws IOException {
        for (ObradaKlijentskihZahteva okz : klijenti) {
            okz.prekiniNit();
        }
        kraj = true;
        serverSocket.close();
    }
}
