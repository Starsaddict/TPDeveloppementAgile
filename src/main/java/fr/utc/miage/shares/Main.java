package fr.utc.miage.shares;


    public class Main {
        public static void main(String[] args) {
            // 创建一个简单的股票和日期
            ActionSimple google = new ActionSimple("Google");
            Jour jour100 = new Jour(2025, 100);
            google.enrgCours(jour100, 142.3f); // 设定当天价格
    
            // 创建用户
            Utilisateur alice = new Utilisateur("Alice");
    
            // 用户查看这支股票当天的价格
            float valeur = alice.consulterValeurAction(google, jour100);
            System.out.println("Valeur de l'action " + google.getLibelle() +
                               " au jour " + jour100 + " : " + valeur);
        }
    }
    

