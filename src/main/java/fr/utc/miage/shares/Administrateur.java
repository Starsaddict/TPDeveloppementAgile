package fr.utc.miage.shares;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom) {
        super(nom);
    }

    // 添加每日股价
    public void enregistrerCours(ActionSimple action, Jour jour, float valeur) {
        action.enrgCours(jour, valeur);
    }

    // 添加复合股票组件
    public void definirComposition(ActionComposee actionComposee, Action composant, float proportion) {
        actionComposee.ajouterAction(composant, proportion);
    }
}

