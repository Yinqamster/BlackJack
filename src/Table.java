public class Table {
    private Shuffle shuffle;
    private Check check;
    private Player player;
    private Dealer dealer;
    private int which;
    private int total;
    private boolean flag;

    public Table() {
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
            shuffle.giveNewCard(player);
            shuffle.giveNewCard(dealer);
            Utils.printDealerHandCard(dealer);
            Utils.printHandCard(player, 0);
            while (true) {
                int action = player.takeAction(which);
                if (action == Config.HITACTION || action == Config.SPLITACTION) {
                    if (action == Config.SPLITACTION)
                        total++;
                    shuffle.giveOneCard(player, which);
                    Utils.printDealerHandCard(dealer);
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
                    Utils.printDealerHandCard(dealer);
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
        Utils.printHandCard(dealer, 0);
        Utils.printHandCard(player, 0);
        return 1;
    }
}
