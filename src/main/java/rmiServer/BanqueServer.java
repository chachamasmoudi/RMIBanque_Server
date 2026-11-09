package rmiServer;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import rmiService.BanqueImpl;

public class BanqueServer {

    public static void main(String[] args) {
        try {
            // Démarrer le registre RMI sur le port 1099
            LocateRegistry.createRegistry(1099);

            // Créer l'objet distant
            BanqueImpl banque = new BanqueImpl();

            // Contexte JNDI chargé automatiquement depuis jndi.properties
            Context ctx = new InitialContext();

            // Binding de l'objet distant dans l'annuaire JNDI
            ctx.rebind("banque", banque);

            System.out.println("Serveur RMI Banque + JNDI prêt...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}