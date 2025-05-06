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

public class Administrateur extends Utilisateur {

    public Administrateur(String nom) {
        super(nom);
    }

    // enrg possible si pas de cours pour ce jour
    public void enrgCours(ActionSimple as, final Jour j, final float v) {
        if (v < 0) {
            // empeche l'enregistrement d'une valeur négative
            throw new IllegalArgumentException("La valeur doit être positive.");
        }
        //empêche l'enregistrement en double pour un même jour
        if (as.getMapCours().containsKey(j)) {
            throw new IllegalStateException("Un cours est déjà enregistré pour ce jour.");
        }
        as.add(j,v);
    }

}
