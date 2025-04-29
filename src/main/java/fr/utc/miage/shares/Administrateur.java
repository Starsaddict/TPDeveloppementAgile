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

    // 添加每日股价
    public void enregistrerCours(ActionSimple action, Jour jour, float valeur) {
        action.enrgCours(jour, valeur);
    }

    // 添加复合股票组件
    public void definirComposition(ActionComposee actionComposee, Action composant, float proportion) {
        actionComposee.ajouterAction(composant, proportion);
    }
}

