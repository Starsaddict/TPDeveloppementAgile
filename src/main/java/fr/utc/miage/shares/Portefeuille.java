package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

public class Portefeuille {
    private final Map<Action, Integer> actions;

    public Portefeuille() {
        this.actions = new HashMap<>();
    }

    public void acheter(Action action, int quantite) {
        actions.put(action, actions.getOrDefault(action, 0) + quantite);
    }

    public void vendre(Action action, int quantite) {
        if (actions.containsKey(action)) {
            int q = actions.get(action) - quantite;
            if (q <= 0) {
                actions.remove(action);
            } else {
                actions.put(action, q);
            }
        }
    }

    public Map<Action, Integer> getActions() {
        return actions;
    }
}

