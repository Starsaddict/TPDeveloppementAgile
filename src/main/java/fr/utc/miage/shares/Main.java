package fr.utc.miage.shares;


    public class Main {
        public static void main(String[] args) {
            // Create a simple stock and date
            ActionSimple google = new ActionSimple("Google");
            Jour jour100 = new Jour(2025, 100);
            google.enrgCours(jour100, 142.3f); // 设定当天价格
    
    
            // The user checks the stock price for the day
            float valeur = ActionMethode.consulterValeurAction(google, jour100);
            System.out.println("Valeur de l'action " + google.getLibelle() +
                               " au jour " + jour100 + " : " + valeur);
        }
    }
    

