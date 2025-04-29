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

    public void acheter(Action action, int quantite, Jour jour) {
        portefeuille.acheter(action, quantite);
        historique.ajouterTransaction(new Transaction(action, quantite, jour, true));
    }

    public void vendre(Action action, int quantite, Jour jour) {
        portefeuille.vendre(action, quantite);
        historique.ajouterTransaction(new Transaction(action, quantite, jour, false));
    }

    // US#2 Query the value of a certain stock on a certain day
    public float consulterValeurAction(Action action, Jour jour) {
        return action.valeur(jour);
    }
}

