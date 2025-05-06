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

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class ActionComposeeTest {

    @Test
    public void testSetAndGetComposition() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.5f);
        comp.put(as2, 0.5f);

        ac.setComposition(comp);

        assertEquals(2, ac.getComposition().size());
        assertEquals(0.5f, ac.getComposition().get(as1));
        assertEquals(0.5f, ac.getComposition().get(as2));
    }

    @Test
    public void testValeurCalcul() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Jour jour = new Jour(2025, 120);
        as1.add(jour, 100f);
        as2.add(jour, 200f);

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.3f);
        comp.put(as2, 0.7f);

        ac.setComposition(comp);

        float expected = 100f * 0.3f + 200f * 0.7f;
        assertEquals(expected, ac.valeur(jour));
    }

    @Test
    public void testValeurSansCours() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Jour jour = new Jour(2025, 120);
        // 没有设置 cours，valeur() 应返回 0

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.5f);
        comp.put(as2, 0.5f);

        ac.setComposition(comp);

        assertEquals(0f, ac.valeur(jour));
    }

    @Test
    public void testVerifierSommeEgaleAUn() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.6f);
        comp.put(as2, 0.4f);

        ac.setComposition(comp);

        assertTrue(ac.verifier());
    }

    @Test
    public void testVerifierSommeSuperieureAUn() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.7f);
        comp.put(as2, 0.4f); // 总和1.1

        ac.setComposition(comp);

        assertFalse(ac.verifier());
    }

    @Test
    public void testVerifierSommeInferieureAUn() {
        ActionComposee ac = new ActionComposee("Composite");
        ActionSimple as1 = new ActionSimple("A1");
        ActionSimple as2 = new ActionSimple("A2");

        Map<Action, Float> comp = new HashMap<>();
        comp.put(as1, 0.3f);
        comp.put(as2, 0.6f); // 总和0.9

        ac.setComposition(comp);

        assertFalse(ac.verifier());
    }
}
