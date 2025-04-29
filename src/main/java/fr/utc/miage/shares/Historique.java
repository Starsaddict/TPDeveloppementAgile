package fr.utc.miage.shares;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Historique {
    private final List<Transaction> transactions;

    public Historique() {
        this.transactions = new ArrayList<>();
    }

    public void ajouterTransaction(Transaction t) {
        this.transactions.add(t);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public List<Transaction> getTransactionsParJour(Jour jour) {
        return transactions.stream()
                .filter(t -> t.getJour().equals(jour))
                .collect(Collectors.toList());
    }
}

