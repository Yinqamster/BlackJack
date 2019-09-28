public class BlackJack {
    public static void main(String[] args) {
        BlackJackTable t = new BlackJackTable(Config.PLAYERNUM);
        t.playGame();
    }
}
