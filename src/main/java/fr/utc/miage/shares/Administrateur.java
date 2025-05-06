package fr.utc.miage.shares;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom) {
        super(nom);
    }

    // Add daily stock price
    public void enregistrerCours(ActionSimple action, Jour jour, float valeur) {
        action.enrgCours(jour, valeur);
    }

    // Adding a Composite Stock Component
    public void definirComposition(ActionComposee actionComposee, Action composant, float proportion) {
        actionComposee.ajouterAction(composant, proportion);
    }
}

