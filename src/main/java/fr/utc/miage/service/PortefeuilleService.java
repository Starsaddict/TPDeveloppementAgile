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
package fr.utc.miage.service;

import java.util.Map;

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;

public class PortefeuilleService {
    /**
     * US#7: Calculate total value of a portfolio on a given day.
     *
     * @param portefeuille the user's portfolio
     * @param jour         the day to evaluate
     * @return the total value
     * @throws IllegalArgumentException if an action has no value for that day
     */
    public static float calculerValeurTotale(Portefeuille portefeuille, Jour jour) {
        if (jour == null) {
            throw new IllegalArgumentException("Day cannot be null");
        }

        float total = 0f;

        for (Map.Entry<Action, Integer> entry : portefeuille.getActions().entrySet()) {
            Action action = entry.getKey();
            int quantite = entry.getValue();
            float valeur = action.valeur(jour);

            if (valeur == 0f) {
                throw new IllegalStateException("No value found for action '" + action.getLibelle() + "' on " + jour);
            }

            total += quantite * valeur;
        }

        return total;
    }
}
