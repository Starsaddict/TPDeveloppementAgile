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

public class Utilisateur {
    private final String nom;
    private final Portefeuille portefeuille;
    private final Historique historique;

    public Utilisateur(String nom) {
        this.nom = nom;
        this.portefeuille = new Portefeuille();
        this.historique = new Historique();
    }

    public String getNom() {
        return nom;
    }

    public Portefeuille getPortefeuille() {
        return portefeuille;
    }

    public Historique getHistorique() {
        return historique;
    }

}
