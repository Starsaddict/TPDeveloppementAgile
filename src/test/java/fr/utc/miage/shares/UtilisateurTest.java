package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class UtilisateurTest {

    @Test
    public void testValeurActionSimplePourUnJourDonne() {
        Utilisateur user = new Utilisateur("Alice");
        ActionSimple apple = new ActionSimple("Apple");
        Jour jour = new Jour(2025, 100);
        apple.enrgCours(jour, 150.5f);

        float valeur = user.consulterValeurAction(apple, jour);
        assertEquals(150.5f, valeur, 0.001f);
    }

    @Test
    public void testValeurActionComposeePourUnJourDonne() {
        Utilisateur user = new Utilisateur("Bob");
        ActionSimple google = new ActionSimple("Google");
        ActionSimple amazon = new ActionSimple("Amazon");

        Jour jour = new Jour(2025, 101);
        google.enrgCours(jour, 100f);
        amazon.enrgCours(jour, 200f);

        ActionComposee techIndex = new ActionComposee("TechIndex");
        techIndex.ajouterAction(google, 0.6f);
        techIndex.ajouterAction(amazon, 0.4f);

        float valeur = user.consulterValeurAction(techIndex, jour);
        assertEquals(140f, valeur, 0.001f); // 0.6 * 100 + 0.4 * 200
    }

    @Test
    public void testValeurNonDisponiblePourDate() {
        Utilisateur user = new Utilisateur("Charlie");
        ActionSimple tesla = new ActionSimple("Tesla");
        Jour jourSansValeur = new Jour(2025, 102); // 无设定值

        float valeur = user.consulterValeurAction(tesla, jourSansValeur);
        assertEquals(0f, valeur, 0.001f); // 默认返回0值
    }

    @Test
    public void testAucuneDateChoisie() {
        Utilisateur user = new Utilisateur("Diane");
        ActionSimple ibm = new ActionSimple("IBM");

        // 假设你要模拟"未选择日期"，你可以传入null
        Exception exception = assertThrows(NullPointerException.class, () -> {
            user.consulterValeurAction(ibm, null);
        });

        assertTrue(exception.getMessage() == null || exception.getMessage().isEmpty());
    }
}

