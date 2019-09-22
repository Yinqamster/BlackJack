public class Table {
    private Shuffle shuffle;
    private Check check;
    private Player player;
    private Dealer dealer;
    private int which;
    private int total;
    private boolean flag;

    public Table() {
    	System.out.println("Welcome to the BlackJack game, the objective of the game is to accumulate a hand of cards that equals 21 (Blackjack!) or a hand that has a card value greater than your opponents without exceeding 21.");
        shuffle = new Shuffle();
        check = new Check();
        String str = Utils.getName();
        int money = Utils.getMoney();
        player = new Player(str, money);
        boolean isMan = Utils.realMan();
        if (isMan) {
            str = Utils.getName();
            dealer = new Dealer(str);
        }
        else
            dealer = new Dealer();
        which = 0;
        total = 1;
        flag = true;
    }

    public void playGame() {
        while (flag) {
            if(!player.makeBet())
                break;
            shuffle.newShuffle();
            shuffle.giveNewCard(player);
            shuffle.giveNewCard(dealer);
            System.out.print("The dealer's cards: ");
            Utils.printDealerHandCard(dealer);
            System.out.print("The player's cards: ");
            Utils.printHandCard(player, 0);
            while (true) {
                int action = player.takeAction(which);
                if (action == Config.HITACTION || action == Config.SPLITACTION) {
                    if (action == Config.SPLITACTION)
                        total++;
                    shuffle.giveOneCard(player, which);
                    System.out.print("The dealer's cards: ");
                    Utils.printDealerHandCard(dealer);
                    System.out.print("The Player's cards: ");
                    Utils.printHandCard(player, 0);
                    if (check.checkBust(player, which)) {
                        player.endGame(Config.BUST, which);
                        if (total > which + 1)
                            which++;
                        else
                            break;
                    }
                }
                else if (action == Config.STANDACTION) {
                    if (standAction() == 1)
                        break;
                }
                else if (action == Config.DOUBLEACTION) {
                    shuffle.giveOneCard(player, which);
                    System.out.print("The dealer's cards: ");
                    Utils.printDealerHandCard(dealer);
                    System.out.print("The player's cards: ");
                    Utils.printHandCard(player, 0);
                    if (check.checkBust(player, which)) {
                        player.endGame(Config.BUST, which);
                        if (total > which + 1)
                            which++;
                        else
                            break;
                    }
                    else
                    if (standAction() == 1)
                        break;
                }
            }
            for (int i = 0; i < player.getHandCard().size(); i++)
                player.endGame(check.checkWin(player, dealer, i), i);
            char c = Utils.nextGame();
            if (c != 'y' && c != 'Y')
                flag = false;
        }
    }

    private int standAction() {
        if (total > which + 1) {
            which++;
            return 0;
        }
        shuffle.keepGive(dealer);
        System.out.print("The dealer's cards: ");
        Utils.printHandCard(dealer, 0);
        System.out.print("The player's cards: ");
        Utils.printHandCard(player, 0);
        return 1;
    }
}
