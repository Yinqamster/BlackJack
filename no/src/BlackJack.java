public class BlackJack {
    public static void main(String[] args) {
        Table t = new Table(Config.PLAYERNUM);
        t.playGame();
    }
}
