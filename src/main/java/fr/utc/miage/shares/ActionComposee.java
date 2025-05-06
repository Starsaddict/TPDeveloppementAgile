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

import java.util.HashMap;
import java.util.Map;

public class ActionComposee extends Action {
    private final Map<Action, Float> composition;



    public ActionComposee(String libelle) {
        super(libelle);
        this.composition = new HashMap<>();
    }

    @Override
    public float valeur(Jour j) {
        float total = 0f;
        for (Map.Entry<Action, Float> entry : composition.entrySet()) {
            total += entry.getKey().valeur(j) * entry.getValue();
        }
        return total;
    }

    public Map<Action, Float> getComposition() {
        return composition;
    }

    public void setComposition(Map<Action, Float> composition) {
        this.composition.clear();
        this.composition.putAll(composition);
    }


    public boolean verifier(){
        float total = 0;
        for (Float entry : composition.values()) {
            total += entry;
        }
        return Math.abs(total - 1.0f) < 0.001f;

    }



}
