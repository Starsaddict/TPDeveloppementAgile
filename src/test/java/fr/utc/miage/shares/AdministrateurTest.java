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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AdministrateurTest {

    @Test
    public void testEnregistrerCoursValide() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        admin.enregistrerCours(action, jour, 300.5f);

        assertEquals(300.5f, action.valeur(jour));
    }

    @Test
    public void testEnregistrerCoursDoublon() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        admin.enregistrerCours(action, jour, 300.5f);

        // tentative d'enregistrement du mm jour -- doit échouer
        assertThrows(IllegalStateException.class, () -> {
            admin.enregistrerCours(action, jour, 310.0f);
        });
    }

    @Test
    public void testEnregistrerCoursNegatif() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        assertThrows(IllegalArgumentException.class, () -> {
            admin.enregistrerCours(action, jour, -50.0f);
        });
    }
}
