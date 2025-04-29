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

public class Transaction {
    private final Action action;
    private final int quantite;
    private final Jour jour;
    private final boolean achat;

    public Transaction(Action action, int quantite, Jour jour, boolean achat) {
        this.action = action;
        this.quantite = quantite;
        this.jour = jour;
        this.achat = achat;
    }

    public Action getAction() {
        return action;
    }

    public int getQuantite() {
        return quantite;
    }

    public Jour getJour() {
        return jour;
    }

    public boolean isAchat() {
        return achat;
    }
}
