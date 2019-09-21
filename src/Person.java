import java.util.ArrayList;
import java.util.List;

/public class Person {
	private String name;
	protected Wallet wallet;
	protected List<List<HandCard>> handCard;

	public Person(){
		handCard = new ArrayList<List<HandCard>>();
	}
	
	public Person(String name) {
		this();
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
	
	
}
