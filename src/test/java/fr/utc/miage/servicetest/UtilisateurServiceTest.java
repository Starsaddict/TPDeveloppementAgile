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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.utc.miage.service.UtilisateurService;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Transaction;
import fr.utc.miage.shares.Utilisateur;

class UtilisateurServiceTest {

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
        stock.enrgCours(tradingDay, 150.0f); // Stock price: 150
        user.setSoldes(2000.0f); // Initial balance: 2000
    }

    /**
     * US#3
     * Test#16: Purchase with valid quantity and sufficient balance
     * Should update portfolio, deduct balance, and log transaction
     */
    @Test
    void purchaseStock_WithValidInput_ShouldSucceed() {
        int quantity = 10;
        float expectedCost = 150.0f * quantity; // 150 * 10 = 1500

        service.acheterAction(user, stock, quantity, tradingDay);

        // Verify portfolio update
        assertEquals(quantity,
                user.getPortefeuille().getActions().get(stock),
                "Stock quantity should be increased by " + quantity);

        // Verify balance deduction
        assertEquals(2000 - expectedCost,
                user.getSoldes(), 0.001f,
                "Balance should be reduced by " + expectedCost);

        // Verify transaction history
        assertFalse(user.getHistorique().getTransactions().isEmpty(),
                "Transaction history should not be empty");
        Transaction transaction = user.getHistorique().getTransactions().get(0);
        assertEquals(stock, transaction.getAction(), "Transaction should record the correct stock");
        assertEquals(quantity, transaction.getQuantite(), "Transaction should record the correct quantity");
    }

    /**
     * US#3
     * Test#22: Attempt to purchase with invalid quantity (zero or negative)
     * Should throw IllegalArgumentException and reject transaction
     */
    @Test
    void purchaseStock_WithInvalidQuantity_ShouldThrowException() {
        // Test zero quantity
        assertThrows(IllegalArgumentException.class,
                () -> service.acheterAction(user, stock, 0, tradingDay),
                "Expected exception for zero quantity");

        // Test negative quantity
        assertThrows(IllegalArgumentException.class,
                () -> service.acheterAction(user, stock, -5, tradingDay),
                "Expected exception for negative quantity");

        // Verify no changes to portfolio or balance
        assertTrue(user.getPortefeuille().getActions().isEmpty(), "Portfolio should remain empty");
        assertEquals(2000.0f, user.getSoldes(), 0.001f, "Balance should remain unchanged");
    }

    /**
     * US#3
     * Test#19: Attempt to purchase with insufficient balance
     * Should throw IllegalStateException and reject transaction
     */
    @Test
    void purchaseStock_WithInsufficientBalance_ShouldThrowException() {
        // Set insufficient balance
        user.setSoldes(100.0f); // Balance: 100 < 150 * 1 = 150
        int quantity = 1;

        // Verify exception
        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> service.acheterAction(user, stock, quantity, tradingDay),
                "Expected exception for insufficient balance");

        // Verify error message
        assertTrue(exception.getMessage().contains("Insufficient balance"),
                "Error message should indicate insufficient balance");

        // Verify no changes to portfolio or balance
        assertTrue(user.getPortefeuille().getActions().isEmpty(), "Portfolio should remain empty");
        assertEquals(100.0f, user.getSoldes(), 0.001f, "Balance should remain unchanged");
    }

    /**
     * US#4
     * Test#27: Sell valid quantity
     * - Should reduce shares in portfolio
     * - Should update balance correctly
     * - Should record sell transaction
     */
    @Test
    void sellStock_WithValidQuantity_ShouldSucceed() {
        // set portefeuille
        user.getPortefeuille().getActions().put(stock, 20);
        int sellQuantity = 10;
        float expectedProceeds = 150.0f * sellQuantity;

        service.vendreAction(user, stock, sellQuantity, tradingDay);

        // verify action change
        assertEquals(10, user.getPortefeuille().getActions().get(stock));

        // verify soldes change
        assertEquals(2000 + expectedProceeds, user.getSoldes(), 0.001f);

        // verify transaction change
        Transaction transaction = user.getHistorique().getTransactions().get(0);
        assertEquals(stock, transaction.getAction());
        assertEquals(sellQuantity, transaction.getQuantite());
        assertFalse(transaction.isAchat());
    }

    /**
     * US#4
     * Test#28: Sell more than owned shares
     * - Should throw IllegalStateException
     * - Should preserve original portfolio and balance
     */
    @Test
    void sellStock_WithExcessiveQuantity_ShouldThrowException() {
        // set portefeuille
        user.getPortefeuille().getActions().put(stock, 20);
        int sellQuantity = 30;

        // verify exception de erruer
        assertThrows(IllegalStateException.class,
                () -> service.vendreAction(user, stock, sellQuantity, tradingDay));

        // Verify portfolio remains unchanged (20 shares)
        assertEquals(20, user.getPortefeuille().getActions().get(stock),
        "Portfolio should remain unchanged with 20 shares");
        assertEquals(2000.0f, user.getSoldes(), 0.001f,
        "Balance should remain unchanged");
    }

    /**
     * US#4 
     * Test#41 : Sell all owned shares
     * - Should remove stock from portfolio
     * - Should update balance correctly
     */
    @Test
    void sellAllStock_ShouldRemoveFromPortfolio() {
        // Setup initial portfolio
        user.getPortefeuille().getActions().put(stock, 15);
        float expectedProceeds = 150.0f * 15;

        service.vendreAction(user, stock, 15, tradingDay);

        // Verify stock removed from portfolio
        assertFalse(user.getPortefeuille().getActions().containsKey(stock));
        
        // Verify balance update
        assertEquals(2000 + expectedProceeds, user.getSoldes(), 0.001f);
    }

    /**
     * US#4 
     * Test#50: Invalid quantity (zero or negative)
     * - Should throw IllegalArgumentException
     * - Should preserve original state
     */
    @Test
    void sellStock_WithInvalidQuantity_ShouldThrowException() {
        user.getPortefeuille().getActions().put(stock, 10);

        assertThrows(IllegalArgumentException.class,
            () -> service.vendreAction(user, stock, 0, tradingDay));

        assertThrows(IllegalArgumentException.class,
            () -> service.vendreAction(user, stock, -5, tradingDay));
    }
}
