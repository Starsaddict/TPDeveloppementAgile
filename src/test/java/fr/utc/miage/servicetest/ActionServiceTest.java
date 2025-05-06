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

import fr.utc.miage.service.ActionService;
import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;

public class ActionServiceTest {
    
    private static final float DELTA = 0.001f;

    // [Test #13] Display value of a simple action for a given day
    @Test
    @DisplayName("[Test #13] Display value of a simple action for a given day")
    void testSimpleActionValueOnValidDay() {
        ActionSimple apple = new ActionSimple("Apple");
        Jour jour = new Jour(2025, 100);
        apple.enrgCours(jour, 150.0f);

        float valeur = ActionService.consulterValeurAction(apple, jour);
        assertEquals(150.0f, valeur, DELTA);
    }

    // Waiting for US#5 to be completed
    // [Test #17] Display value of a composed action for a given day

    // [Test #18] Show 0 if no value is registered for the day
    @Test
    @DisplayName("[Test #18] Show 0 if no value is registered for the day")
    void testActionValueOnDayWithNoData() {
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 102);            // no value registered

        float valeur = ActionService.consulterValeurAction(tesla, jour);
        assertEquals(0.0f, valeur, DELTA);
    }

    // [Test #23] Show error if no date is selected (null)
    @Test
    @DisplayName("[Test #23] Show error if no date is selected (null)")
    void testErrorWhenDateIsNull() {
        ActionSimple ibm = new ActionSimple("IBM");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ActionService.consulterValeurAction(ibm, null);
        });

        String msg = exception.getMessage().toLowerCase();
        assertTrue(msg.contains("day") || msg.contains("date"), "Please select a valid day!");
    }
}
