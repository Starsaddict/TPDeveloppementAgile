/*
 * Copyright 2025 David Navarre &lt;David.Navarre at irit.fr&gt;.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.utc.miage.shares;

import java.util.Map;

public class Administrateur extends Utilisateur {

    public Administrateur(String nom) {
        super(nom);
    }

    // enregistrer possible si pas de cours pour ce jour
    public void enregistrerCours(ActionSimple as, final Jour j, final float v) {
        as.enrgCours(j,v);
    }

    public void definirComposition(ActionComposee actionComposee, Map<Action, Float> composition) {
        for (Action action : composition.keySet()) {
            if (!(action instanceof ActionSimple)) {
                throw new IllegalArgumentException("Une ou plusieurs actions simples sont introuvables");
            }
        }
        float total = 0;
        for (Float weight : composition.values()) {
            if (weight < 0 || weight >1 ) {
                throw new IllegalArgumentException("Chaque pourcentage doit être compris entre 0 et 1.");
            }
            total += weight;
        }
        if (Math.abs(total - 1.0f) > 0.001f) {
            throw new IllegalArgumentException("La somme des pourcentages doit être égale à 1.");
        }
        actionComposee.setComposition(composition);
    }

}
