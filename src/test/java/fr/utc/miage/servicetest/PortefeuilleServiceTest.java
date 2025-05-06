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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import fr.utc.miage.service.PortefeuilleService;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;
import fr.utc.miage.shares.Portefeuille;

public class PortefeuilleServiceTest {

    // [ Test #25 ] Normal calculation of total value
    @Test
    @DisplayName("[ Test #25 ] Normal calculation of total value")
    void testCalculValeurTotaleDuPortefeuille() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple apple = new ActionSimple("Apple");
        Jour jour = new Jour(2025, 100);
        apple.enrgCours(jour, 150.0f);

        portefeuille.getActions().put(apple, 2);   // 2 * 150 = 300

        float total = PortefeuilleService.calculerValeurTotale(portefeuille, jour);
        assertEquals(300.0f, total, 0.001f);
    }

    // [Test #38 ] Report error - action no price
    @Test
    @DisplayName("[Test #38 ] Report error - action no price")
    void testErreurSiValeurIndisponiblePourJour() {
        Portefeuille portefeuille = new Portefeuille();
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 101);   // No price set

        portefeuille.getActions().put(tesla, 1);

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            PortefeuilleService.calculerValeurTotale(portefeuille, jour);
        });

        assertTrue(exception.getMessage().contains("No value found for action"));
    }
}
