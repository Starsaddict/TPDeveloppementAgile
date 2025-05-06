package fr.utc.miage.shares;

public class ConsulterValeurAction {
    
    // US#2 Query the value of a certain stock on a certain day
    public float consulterValeurAction(Action action, Jour jour) {
        return action.valeur(jour);
    }
}
