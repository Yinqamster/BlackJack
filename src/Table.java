public class Table {
    Shuffle shuffle;
    Check check;
    Player player;
    Dealer dealer;
    int which;
    int total;

    public Table() {
        shuffle = new Shuffle();
        check = new Check();
        player = new Player();
        dealer = new Dealer();
        which = 0;
        total = 1;
    }

    public void playGame() {
        player.makeBet();
        shuffle.giveNewCard(player);
        shuffle.giveNewCard(dealer);

        while (true) {
            int action = player.takeAction(which);
            if (action == Config.HITACTION || action == Config.SPLITACTION) {
                if (action == Config.SPLITACTION)
                    total++;
                shuffle.giveOneCard(player, which);
                if (check.checkBust(player, which)) {
                    player.endGame(Config.BUST, which);
                    if (total > which + 1)
                        which++;
                }
            }
            else if (action == Config.STANDACTION) {
                if (standAction() == 1)
                    break;
            }
            else if (action == Config.DOUBLEACTION) {
                shuffle.giveOneCard(player, which);
                if (check.checkBust(player, which)) {
                    player.endGame(Config.BUST, which);
                    if (total > which + 1)
                        which++;
                }
                else
                    if (standAction() == 1)
                        break;
            }
        }
        for (int i = 0; i < player.getHandCard().size(); i++)
            player.endGame(check.checkWin(player, dealer, i), i);
    }

    private int standAction() {
        if (total > which + 1) {
            which++;
            return 0;
        }
        shuffle.keepGive(dealer);
        return 1;
    }
}
