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

/**
 * Allows the creation of simple Action objects.
 *
 * @author David Navarre &lt;David.Navarre at irit.fr&gt;
 */
public class ActionSimple extends Action {

    private static final int DEFAULT_ACTION_VALUE = 0;

    // attribut lien
    private final Map<Jour, Float> mapCours;

    // constructeur
    public ActionSimple(final String libelle) {
        // Action simple initialisée comme 1 action
        super(libelle);
        // init spécifique
        this.mapCours = new HashMap<>();
    }

    // Affichier le map Cours
    public Map<Jour, Float> getMapCours() {
        return mapCours;
    }

    public void enrgCours(final Jour j, final float v) {
        if (v < 0) {
            // empeche l'enregistrement d'une valeur négative
            throw new IllegalArgumentException("La valeur doit être positive.");
        }
        // empêche l'enregistrement en double pour un même jour
        if (this.getMapCours().containsKey(j)) {
            throw new IllegalStateException("Un cours est déjà enregistré pour ce jour.");
        }
        this.add(j, v);
    }

    public void add(Jour j, Float v) {
        mapCours.put(j, v);
    }

    @Override
    public float valeur(final Jour j) {
        if (this.mapCours.containsKey(j)) {
            return this.mapCours.get(j);
        } else {
            return DEFAULT_ACTION_VALUE;
        }
    }

}
