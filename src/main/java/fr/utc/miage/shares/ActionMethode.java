package fr.utc.miage.shares;

public class ActionMethode {

    /**
     * US#2: Get the value of a stock on a specific day.
     *
     * @param action The stock to check (can be ActionSimple or ActionComposee)
     * @param jour   The day for which the value is requested
     * @return       The value of the stock on the given day
     * @throws IllegalArgumentException if the day is null
     */
    public static float consulterValeurAction(Action action, Jour jour) {
        if (jour == null) {
            throw new IllegalArgumentException("Please select a valid day!");
        }
        return action.valeur(jour);
    }
}
