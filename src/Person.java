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

	public void deleteHandCard() {
		handCard.clear();
	}
	
	public void giveCard(Card card, int index) {
		//This shouldn't be happen
		if(index < 0 || index >= handCard.size()) {
			System.out.println("wrong index");
			return;
		}
		handCard.get(index).getCards().add(card);
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<HandCard> getHandCard() {
		return this.handCard;
	}
	
	
}
