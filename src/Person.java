import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
//	protected Wallet wallet;
	protected List<HandCard> handCard;

	public Person(){
		handCard = new ArrayList<HandCard>();
	}
	
	public Person(String name) {
		this();
		this.name = name;
	}
	
//	public Wallet getWallet(){
//		return this.wallet;
//	}

	public void deleteHandCard() {
		handCard.clear();
	}
	
	public void giveCard(Card card, int index) {
		//This shouldn't be happen
		if(index < 0 || (handCard.size()!= 0 && index >= handCard.size())) {
			System.out.println("wrong index2");
			return;
		}
		if(handCard.size() == 0) {
			HandCard newHandCard = new HandCard();
			newHandCard.getCards().add(card);
			handCard.add(newHandCard);
		}
		else {
			handCard.get(index).getCards().add(card);
		}
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<HandCard> getHandCard() {
		return this.handCard;
	}
	
	
}
