import java.util.ArrayList;
import java.util.List;

public class HandCard {
    List<Card> cards;

    public HandCard() {
        cards = new ArrayList<>();
    }

    public int[] getValue() {
        int total = 0;
        int ace = 0;
        for (Card card : cards) {
            if (card.getValue() < 10 && card.getValue() != 1)
                total += card.getValue();
            if (card.getValue() >= 10)
                total += 10;
            if (card.getValue() == 1)
                ace += 1;
        }
        List<Integer> rst = new ArrayList<>();
        for (int i = 0; i <= ace; i++)
            rst.add(total + i * 10 + ace - i);
        int[] temp = new int[rst.size()];
        for (int i = 0; i < rst.size(); i++)
            temp[i] = rst.get(i);
        return temp;
    }

    public boolean isBlackJack() {
        if (cards.size() != 2)
            return false;
        Card a = cards.get(0);
        Card b = cards.get(1);
        if (a.getValue() > 1 && a.getValue() <= 10)
            return false;
        if (a.getValue() == 1 && b.getValue() > 10)
            return true;
        return a.getValue() > 10 && b.getValue() == 1;
    }

    public List<Card> getCards() {
        return cards;
    }
}
