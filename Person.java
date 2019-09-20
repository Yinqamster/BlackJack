import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;
	private Wallet wallet;
	private List<HandCard> handCard = new ArrayList<HandCard>();
	
	public Person(){};
	
	public Person(String name) {
		this.name = name;
	}
	
	public void initCard() {
		handCard.clear();
	}
	
	public void giveCard(Card card) {
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<HandCard> getHandCard() {
		return this.handCard;
	}
	
}
