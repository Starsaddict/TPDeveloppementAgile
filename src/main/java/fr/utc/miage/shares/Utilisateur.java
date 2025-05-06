package fr.utc.miage.shares;

public class Utilisateur {
    private final String nom;
    private final Portefeuille portefeuille;
    private final Historique historique;

    public Utilisateur(String nom) {
        this.nom = nom;
        this.portefeuille = new Portefeuille();
        this.historique = new Historique();
    }

    public String getNom() {
        return nom;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public Historique getHistorique() {
        return historique;
    }
}

