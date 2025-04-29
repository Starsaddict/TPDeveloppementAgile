package fr.utc.miage.shares;

public class Transaction {
    private final Action action;
    private final int quantite;
    private final Jour jour;
    private final boolean achat;

    public Transaction(Action action, int quantite, Jour jour, boolean achat) {
        this.action = action;
        this.quantite = quantite;
        this.jour = jour;
        this.achat = achat;
    }

    public Action getAction() {
        return action;
    }

    public int getQuantite() {
        return quantite;
    }

    public Jour getJour() {
        return jour;
    }

    public boolean isAchat() {
        return achat;
    }
}
