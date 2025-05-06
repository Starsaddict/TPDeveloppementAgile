package fr.utc.miage.shares;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
public class UtilisateurTest {
    //[Test]: (US#8) Consulter l’historique d’une action existante #12
    @Test
    public void voirHistoriqueCours() {
        Utilisateur utilisateur = new Utilisateur("Alice");
        ActionSimple aapl = new ActionSimple("AAPL");

        Jour jour1 = new Jour(2025, 120);
        Jour jour2 = new Jour(2025, 121);
        aapl.add(jour1, 150.0f);
        aapl.add(jour2, 155.0f);

        Map<Jour, Float> historique = utilisateur.AfficherHistoriqueCour(aapl);

        assertEquals(2, historique.size());
        assertTrue(historique.containsKey(jour1));
        assertTrue(historique.containsKey(jour2));
        assertEquals(150.0f, historique.get(jour1));
        assertEquals(155.0f, historique.get(jour2));
    }

    @Test
    public void testFiltreDerniers6Mois() {
        // Given: utilisateur connecté et consultation historique d'une action
        Utilisateur utilisateur = new Utilisateur("Alice");
        ActionSimple action = new ActionSimple("AAPL");

        LocalDate today = LocalDate.now();
        Jour jourRecent1 = new Jour(today.getYear(), today.getDayOfYear() - 10); // il y a 10 jours
        Jour jourRecent2 = new Jour(today.getYear(), today.getDayOfYear() - 50); // il y a 50 jours
        Jour jourAncien = new Jour(today.minusMonths(7).getYear(), today.minusMonths(7).getDayOfYear()); // il y a 7 mois

        action.add(jourRecent1, 150f);
        action.add(jourRecent2, 155f);
        action.add(jourAncien, 140f);

        Map<Jour, Float> historiqueComplet = utilisateur.AfficherHistoriqueCour(action);

        // Si le filtre est 6 mois
        Map<Jour, Float> historiqueFiltre = new HashMap<>();
        LocalDate sixMonthsAgo = today.minusMonths(6);

        for (Map.Entry<Jour, Float> entry : historiqueComplet.entrySet()) {
            LocalDate date = LocalDate.of(entry.getKey().getYear(), 1, 1).plusDays(entry.getKey().getDay() - 1);
            if (!date.isBefore(sixMonthsAgo)) {
                historiqueFiltre.put(entry.getKey(), entry.getValue());
            }
        }

        // Then: le système affiche seulement les dates et valeurs des 6 derniers mois
        assertEquals(2, historiqueFiltre.size());
        assertTrue(historiqueFiltre.containsKey(jourRecent1));
        assertTrue(historiqueFiltre.containsKey(jourRecent2));
        assertFalse(historiqueFiltre.containsKey(jourAncien));
    }

    @Test
    //pour VERIFIER QUE l'HISTORIQUE est vide si aucune action enregistré
    public void testUS8_HistoriqueActionVide() {
        ActionSimple action = new ActionSimple("NouvelleAction");

        Map<Jour, Float> historique = action.getMapCours();

        assertTrue(historique.isEmpty());
    }

    

}
