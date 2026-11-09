package rmiService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

import metier.Compte;

public class BanqueImpl extends UnicastRemoteObject implements IBanque {

    private static final long serialVersionUID = 1L;

    private final Map<Integer, Compte> comptes = new HashMap<>();

    public BanqueImpl() throws RemoteException {
        super();
    }

    @Override
    public synchronized String creerCompte(Compte c) throws RemoteException {
        if (comptes.containsKey(c.getCode())) {
            System.out.println("Création refusée : le compte " + c.getCode() + " existe déjà");
            return "Erreur : le compte " + c.getCode() + " existe déjà";
        }
        comptes.put(c.getCode(), c);
        System.out.println("Compte créé : " + c);
        return "Compte " + c.getCode() + " créé avec un solde initial de " + c.getSolde();
    }

    @Override
    public synchronized String getInfoCompte(int code) throws RemoteException {
        Compte c = comptes.get(code);
        if (c == null) {
            return "Erreur : le compte " + code + " n'existe pas";
        }
        return c.toString();
    }
}