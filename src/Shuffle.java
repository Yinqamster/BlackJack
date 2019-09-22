public class Shuffle {
    private Card[] cards;
    private int[] mask;

    public Shuffle(int n) {
        cards = new Card[n * Config.CARDNUM];
        for (int num = 0; num < n; num++) {
            for (int suit = 0; suit < Config.SUITS.length; suit++) {
                for (int number = 1; number <= Config.CARDRANGE; number++) {
                    int index = num * Config.CARDNUM + suit * Config.CARDRANGE + number;
                    cards[index - 1] = new Card(Config.SUITS[suit], Config.NUMBERS[number - 1], number);
                }
            }
        }
        mask = new int[Config.CARDNUM];
        for (int i = 0; i < Config.CARDNUM; i++)
            mask[i] = 0;
    }

    public Shuffle() {
        this(1);
    }

    public void newShuffle() {
        for (int i = 0; i < mask.length; i++) {
            mask[i] = 0;
        }
    }

    public void giveNewCard(Person p) {
        p.deleteHandCard();
        giveOneCard(p, 0);
        giveOneCard(p, 0);
    }

    public void giveOneCard(Person p, int which) {
        int a = (int)(Math.random() * (Config.CARDNUM));
        while (mask[a] == 1) {
            a = (int)(Math.random() * (Config.CARDNUM));
        }
        mask[a] = 1;
        p.giveCard(cards[a], which);
    }

    public void keepGive(Dealer dealer) {
        int[] v = dealer.getHandCard().get(0).getValue();
        int max = 0;
        for (int n : v) {
            if (n > max && n <= 21)
                max = n;
        }
        System.out.println(max);
        if (max < 17) {
            giveOneCard(dealer, 0);
            keepGive(dealer);
        }
    }
}
