public class BlackJack {
    public static void main(String[] args) {
        System.out.print("How many players in the game? ");
        int a = Utils.getNumberFromPlayer();
        BlackJackTable t = new BlackJackTable(a);
        t.playGame();
    }
}
