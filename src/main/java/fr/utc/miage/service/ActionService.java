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

import fr.utc.miage.shares.Action;
import fr.utc.miage.shares.Jour;

public class ActionService {
    /**
     * US#2: Get the value of a stock on a specific day.
     *
     * @param action The stock to check (can be ActionSimple or ActionComposee)
     * @param jour   The day for which the value is requested
     * @return       The value of the stock on the given day
     * @throws IllegalArgumentException if the day is null
     */
    public static float consulterValeurAction(Action action, Jour jour) {
        if (jour == null) {
            throw new IllegalArgumentException("Please select a valid day!");
        }
        return action.valeur(jour);
    }
}
