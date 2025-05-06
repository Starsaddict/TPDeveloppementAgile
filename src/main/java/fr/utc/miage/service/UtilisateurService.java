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
import fr.utc.miage.shares.Transaction;
import fr.utc.miage.shares.Utilisateur;

public class UtilisateurService {

    /**
     * US#3
     * Purchase a quantity of stock and update the portfolio
     * 
     * @param utilisateur User performing the operation
     * @param action      Stock to purchase
     * @param quantite    Purchase quantity
     * @param jour        Transaction date
     */

    public void acheterAction(Utilisateur utilisateur, Action action, int quantite, Jour jour) {
        // check quantite > 0
        if (quantite <= 0) {
            throw new IllegalArgumentException("Purchase quantity must be positive");
        }

        // get prix total
        float prixUn = action.valeur(jour);
        float totalprix = prixUn * quantite;

        // check soldes more than cost
        if (utilisateur.getSoldes() < totalprix) {
            throw new IllegalStateException(
                    "Insufficient balance, Required " + totalprix + ", available: " + utilisateur.getSoldes());
        }

        // debit soldes
        utilisateur.setSoldes(utilisateur.getSoldes() - totalprix);

        // get Portefeuille
        Map<Action, Integer> actions = utilisateur.getPortefeuille().getActions();

        // Merge stock quantity (add if it exists, otherwise create a new entry
        actions.merge(action, quantite, Integer::sum);

        // Record transaction history
        Transaction transaction = new Transaction(action, quantite, jour, true);
        utilisateur.getHistorique().ajouterTransaction(transaction);
    }


    /**
     * US#4
     * Sell a quantity of stock and update the portfolio
     * 
     * @param utilisateur User performing the operation
     * @param action      Stock to sell
     * @param quantite    Sell quantity
     * @param jour        Transaction date
     */
    public void vendreAction(Utilisateur utilisateur, Action action, int quantite, Jour jour) {
        // Check quantity validity
        if (quantite <= 0) {
            throw new IllegalArgumentException("Sale quantity must be positive");
        }

        // Get portfolio and check stock ownership
        Map<Action, Integer> portfolio = utilisateur.getPortefeuille().getActions();
        int ownedQuantity = portfolio.getOrDefault(action, 0);

        if (ownedQuantity < quantite) {
            throw new IllegalStateException(
                    "Insufficient stock quantity. Owned: " + ownedQuantity + ", requested: " + quantite);
        }

        // Calculate proceeds
        float price = action.valeur(jour);
        float totalProceeds = price * quantite;

        // Update balance
        utilisateur.setSoldes(utilisateur.getSoldes() + totalProceeds);

        // Update portfolio
        int newQuantity = ownedQuantity - quantite;
        if (newQuantity == 0) {
            portfolio.remove(action);
        } else {
            portfolio.put(action, newQuantity);
        }

        // Record transaction
        Transaction transaction = new Transaction(action, quantite, jour, false);
        utilisateur.getHistorique().ajouterTransaction(transaction);
    }

}
