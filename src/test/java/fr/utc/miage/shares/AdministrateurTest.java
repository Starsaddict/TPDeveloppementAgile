package fr.utc.miage.shares;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class AdministrateurTest {

    @Test
    public void testEnregistrerCoursValide() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        admin.enregistrerCours(action, jour, 300.5f);

        assertEquals(300.5f, action.valeur(jour));
    }

    @Test
    public void testEnregistrerCoursDoublon() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        admin.enregistrerCours(action, jour, 300.5f);

        // tentative d'enregistrement du mm jour -- doit échouer
        assertThrows(IllegalStateException.class, () -> {
            admin.enregistrerCours(action, jour, 310.0f);
        });
    }

    @Test
    public void testEnregistrerCoursNegatif() {
        Administrateur admin = new Administrateur("admin");
        ActionSimple action = new ActionSimple("Tesla");
        Jour jour = new Jour(2025, 120);

        assertThrows(IllegalArgumentException.class, () -> {
            admin.enregistrerCours(action, jour, -50.0f);
        });
    }
}
