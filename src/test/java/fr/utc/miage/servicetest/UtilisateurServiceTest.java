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
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.jupiter.api.Test;

import fr.utc.miage.shares.ActionSimple;
import fr.utc.miage.shares.Jour;

public class UtilisateurServiceTest {

    //test#12 pour vérifier que l’historique qui est retourné contient tous les cours enregistrés
    @Test
    public void testUS8_HistoriqueCompletActionExistante() {
        ActionSimple action = new ActionSimple("Total");
        Jour j1 = new Jour(2025, 100);
        Jour j2 = new Jour(2025, 101);
        action.enrgCours(j1, 100.0f);
        action.enrgCours(j2, 110.0f);

        Map<Jour, Float> historique = action.getHistorique();

        assertEquals(2, historique.size());
        assertEquals(100.0f, historique.get(j1));
        assertEquals(110.0f, historique.get(j2));
    }
    @Test
    //test#20 pour VERIFIER QUE l'HISTORIQUE est vide si aucune action enregistré 
    public void testUS8_HistoriqueActionVide() {
        ActionSimple action = new ActionSimple("NouvelleAction");

        Map<Jour, Float> historique = action.getHistorique();

        assertTrue(historique.isEmpty());
    }

}
