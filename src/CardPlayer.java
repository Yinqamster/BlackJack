import java.util.ArrayList;
import java.util.List;

public class CardPlayer extends Person{
	
	protected List<HandCard> handCard;

	public CardPlayer(){
		System.out.println("No default constructor, please call CardPlayer(String).");
	}
	
	public CardPlayer(String name) {
		super(name);
		handCard = new ArrayList<HandCard>();
	}
	
	public void deleteHandCard() {
		//delete the person's all hand cards
		handCard.clear();
	}
	
	public void giveCard(Card card, int index) {
		//This shouldn't be happen
		if(index < 0 || (handCard.size()!= 0 && index >= handCard.size())) {
			System.out.println("wrong index2");
			return;
		}
		//add one card into handcard list
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
		return super.getName();
	}
	
	public List<HandCard> getHandCard() {
		return this.handCard;
	}
	
	
}
