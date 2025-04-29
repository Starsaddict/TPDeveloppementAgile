package fr.utc.miage.shares;

import java.util.HashMap;
import java.util.Map;

public class ActionComposee extends Action {
    private final Map<Action, Float> composition;

    public ActionComposee(String libelle) {
        super(libelle);
        this.composition = new HashMap<>();
    }

    public void ajouterAction(Action action, float proportion) {
        this.composition.put(action, proportion);
    }

    @Override
    public float valeur(Jour j) {
        float total = 0f;
        for (Map.Entry<Action, Float> entry : composition.entrySet()) {
            total += entry.getKey().valeur(j) * entry.getValue();
        }
        return total;
    }

    public Map<Action, Float> getComposition() {
        return composition;
    }
}

