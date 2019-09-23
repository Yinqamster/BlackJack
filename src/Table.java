public class Table {
    private Shuffle shuffle;
    private Check check;
    private Player player;
    private Dealer dealer;
    private int which; // which hand cards are the player manipulate now
    private int total; // how many hand cards the player has now
    private boolean flag; // whether a new game

    public Table() {
    	System.out.println("Welcome to the BlackJack game.");
    	System.out.println("The objective of the game is to accumulate a hand of cards that equals 21.");
    	System.out.println("Or a hand that has a card value greater than your opponents without exceeding 21.");
        shuffle = new Shuffle();
        check = new Check();
        String str = Utils.getName("player");
        int money = Utils.getMoney();
        player = new Player(str, money);
        boolean isMan = Utils.realMan(); //ask whether the dealer is a real man or not
        if (isMan) {
            str = Utils.getName("dealer");
            dealer = new Dealer(str);
        }
        else
            dealer = new Dealer();
        which = 0;
        total = 1;
        flag = true;
    }

    public void playGame() {
        // the body of the game, ask the player for its choice and ask the checker check whether the player bust or not for each round
        while (flag) {
            if(!player.makeBet())
                break;
            shuffle.newShuffle();
            shuffle.giveNewCard(player);
            shuffle.giveNewCard(dealer);
            System.out.print(dealer.getName() + "'s handcards:\t");
            Utils.printDealerHandCard(dealer);
            System.out.print(player.getName() + "'s handcards:\t");
            Utils.printHandCard(player, 0);
            while (true) {
                int action = player.takeAction(which);
                if (action == Config.HITACTION || action == Config.SPLITACTION) {
                    if (action == Config.SPLITACTION)
                        total++;
                    giveAndPrint();
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
                    giveAndPrint();
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

    private void giveAndPrint() {
        //give one card to the player and then print the hand cards
        shuffle.giveOneCard(player, which);
        System.out.print(dealer.getName() + "'s handcards:\t");
        Utils.printDealerHandCard(dealer);
        System.out.print(player.getName() + "'s handcards:\t");
        Utils.printHandCard(player, 0);
        if (total == 2) {
            // if the player split his hand cards, he has two hand card sets
            System.out.print(player.getName() + "'s second handcards:\t");
            Utils.printHandCard(player, 1);
        }
    }

    private int standAction() {
        // after the player stand, use this method to do the following work
        if (total > which + 1) {
            which++;
            return 0;
        }
        shuffle.keepGive(dealer);
        System.out.print(dealer.getName() + "'s handcards:\t");
        Utils.printHandCard(dealer, 0);
        System.out.print(player.getName() + "'s handcards:\t");
        Utils.printHandCard(player, 0);
        if (total == 2) {
            System.out.print(player.getName() + "'s second handcards:\t");
            Utils.printHandCard(player, 1);
        }
        return 1;
    }
}
