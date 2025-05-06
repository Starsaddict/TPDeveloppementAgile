package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class ConsulterValeurActionTest {
    @Test
    public void testAfficherValeurActionSimple() {
        ConsulterValeurAction consultant = new ConsulterValeurAction();
        Jour jour = new Jour(2025, 100);
        ActionSimple google = new ActionSimple("Google");
        google.enrgCours(jour, 150.0f);

        float valeur = consultant.consulterValeurAction(google, jour);
        assertEquals(150.0f, valeur, 0.001);
    }

    @Test
    public void testAfficherValeurActionComposee() {
        ConsulterValeurAction consultant = new ConsulterValeurAction();
        Jour jour = new Jour(2025, 100);
        ActionSimple google = new ActionSimple("Google");
        ActionSimple amazon = new ActionSimple("Amazon");

        google.enrgCours(jour, 150.0f);
        amazon.enrgCours(jour, 100.0f);

        ActionComposee techBundle = new ActionComposee("TechBundle");
        techBundle.ajouterAction(google, 0.6f);
        techBundle.ajouterAction(amazon, 0.4f);

        float expected = 150.0f * 0.6f + 100.0f * 0.4f;
        float valeur = consultant.consulterValeurAction(techBundle, jour);

        assertEquals(expected, valeur, 0.001);
    }

    @Test
    public void testValeurNonDisponiblePourDate() {
        ConsulterValeurAction consultant = new ConsulterValeurAction();
        Jour jourInconnu = new Jour(2025, 200);
        ActionSimple google = new ActionSimple("Google");

        float valeur = consultant.consulterValeurAction(google, jourInconnu);
        assertEquals(0.0f, valeur, 0.001);
    }

    @Test
    public void testErreurSiDateNull() {
        ConsulterValeurAction consultant = new ConsulterValeurAction();
        ActionSimple google = new ActionSimple("Google");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            consultant.consulterValeurAction(google, null);
        });

        assertTrue(exception.getMessage().contains("date"));
    }

}
