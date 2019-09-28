import java.util.List;

public class BlackJackDealer extends CardPlayer{
    public BlackJackDealer(String name) {
        super(name);
    }

    public BlackJackDealer() {
        super("Computer");
    }
    
    public void printDealerHandCard() {
		List<Card> card = handCard.get(0).getCards();
		System.out.print(card.get(0).getNumber() + "(" + card.get(0).getSuit() + ") ");
		System.out.println("*");
	}
}
