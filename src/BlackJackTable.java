import java.util.ArrayList;
import java.util.List;

public class BlackJackTable implements Table {
    private Shuffle shuffle;
    private BlackJackRules check;
    private List<Player> players;
    private Dealer dealer;
    private boolean flag; // whether a new game

    public BlackJackTable(int playerNum) {
        int all = playerNum;
    	System.out.println("Welcome to the BlackJack game.");
    	System.out.println("The objective of the game is to accumulate a hand of cards that equals 21.");
    	System.out.println("Or a hand that has a card value greater than your opponents without exceeding 21.");
    	players = new ArrayList<>();
        shuffle = new Shuffle();
        check = new BlackJackRules();
        String str;
        while (playerNum > 0) {
            System.out.print("The information of player " + (all - playerNum + 1) + ". ");
            str = Utils.getName("player");
            int money = Utils.getMoney();
            players.add(new Player(str, money));
            playerNum--;
        }
        boolean isMan = Utils.realMan(); //ask whether the dealer is a real man or not
        if (isMan) {
            str = Utils.getName("dealer");
            dealer = new Dealer(str);
        }
        else
            dealer = new Dealer();
        flag = true;
    }

    public void playGame() {
        // the body of the game, ask the player for its choice and ask the checker check whether the player bust or not for each round
        while (flag) {
            for (Player player : players)
                player.initTotal();
            shuffle.giveNewCard(dealer);
            shuffle.newShuffle();
            for (Player player : players) {
                if(!player.makeBet())
                    break;
                shuffle.giveNewCard(player);
                System.out.print(dealer.getName() + "'s handcards:\t");
                Utils.printDealerHandCard(dealer);
                System.out.print(player.getName() + "'s handcards:\t");
                Utils.printHandCard(player, 0);
                while (true) {
                    int action = player.takeAction();
                    if (action == Config.HITACTION) {
                        shuffle.giveOneCard(player, player.getWhich());
                        print(player);
                        if (check.checkBust(player, player.getWhich())) {
                            player.endGame(Config.BUST);
                            if (player.isOver())
                                break;
                        }
                    }
                    else if (action == Config.SPLITACTION) {
                        print(player);
                    }
                    else if (action == Config.STANDACTION) {
                        if (standAction(player) == 1)
                            break;
                    }
                    else if (action == Config.DOUBLEACTION) {
                        shuffle.giveOneCard(player, player.getWhich());
                        print(player);
                        if (check.checkBust(player, player.getWhich())) {
                            player.endGame(Config.BUST);
                            if (player.isOver())
                                break;
                        }
                        if (standAction(player) == 1)
                            break;
                    }
                }
            }
            for (Player player : players) {
                player.initWhich();
                for (int i = 0; i < player.getHandCard().size(); i++)
                    player.endGame(check.checkWin(player, dealer, i));
            }
            char c = Utils.nextGame();
            if (c != 'y' && c != 'Y')
                flag = false;
        }
    }

    public void printResult() {
        for (Player player : players) {
            System.out.print(player.getName() + "'s final money of in wallet is: ");
            System.out.println(player.getWallet().getMoney());
        }
    }

    private void print(Player p) {
        //give one card to the player and then print the hand cards
        System.out.print(dealer.getName() + "'s handcards:\t");
        Utils.printDealerHandCard(dealer);
        System.out.print(p.getName() + "'s handcards:\t");
        Utils.printHandCard(p, 0);
        if (p.getTotal() == 2) {
            // if the player split his hand cards, he has two hand card sets
            System.out.print(p.getName() + "'s handcards 2:\t");
            Utils.printHandCard(p, 1);
        }
    }

    private int standAction(Player p) {
        // after the player stand, use this method to do the following work
        if (!p.isOver())
            return 0;
        shuffle.keepGive(dealer);
        System.out.print(dealer.getName() + "'s handcards:\t");
        Utils.printHandCard(dealer, 0);
        System.out.print(p.getName() + "'s handcards:\t");
        Utils.printHandCard(p, 0);
        if (p.getTotal() == 2) {
            System.out.print(p.getName() + "'s handcards 2:\t");
            Utils.printHandCard(p, 1);
        }
        return 1;
    }
}
