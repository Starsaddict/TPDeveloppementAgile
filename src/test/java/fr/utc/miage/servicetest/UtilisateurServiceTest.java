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
package fr.utc.miage.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.utc.miage.service.UtilisateurService;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Utilisateur;

public class UtilisateurServiceTest {

    private UtilisateurService service;
    private Utilisateur user;
    private ActionSimple stock;
    private Jour tradingDay;

    @BeforeEach
    void setUp() {
        service = new UtilisateurService();
        user = new Utilisateur("test_user");
        stock = new ActionSimple("AAPL");
        tradingDay = new Jour(2023, 100); // Day 100 of 2023

        // Initialize stock price and user balance
        stock.enrgCours(tradingDay, 150.0f);
        user.setSoldes(3000.0f); // Initial balance: 3000
    }

    /**
     * US#3 test#16: Normal purchase with sufficient balance
     * Should update portfolio and balance correctly
     */
    @Test
    void purchaseStock_WithValidQuantityAndBalance_ShouldUpdateAssets() {
        int quantity = 20;
        float expectedCost = 150.0f * quantity;

        service.acheterAction(user, stock, quantity, tradingDay);

        // Verify portfolio update
        assertEquals(quantity, user.getPortefeuille().getActions().get(stock));
        // Verify balance deduction
        assertEquals(3000 - expectedCost, user.getSoldes(), 0.001f);
        // Verify transaction history
        assertFalse(user.getHistorique().getTransactions().isEmpty());
    }
}
