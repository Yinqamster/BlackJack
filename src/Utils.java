import java.util.List;
import java.util.Scanner;

public class Utils {
	
	public static int getNumberFromPlayer(){
		Scanner scanner = new Scanner(System.in);
		String str = scanner.nextLine();
		while(!str.matches("^[0-9]*$")) {
			System.out.print("Please input a correct number: ");
			str = scanner.nextLine();
		}
		int num = Integer.parseInt(str);
		return num;
	}
	
	public static void printHandCard(Person p, int index) {
		List<HandCard> handCardList = p.getHandCard();
		List<Card> handCard = handCardList.get(index).getCards();
		for(int i = 0; i < handCard.size(); i++) {
			System.out.print(handCard.get(i).getNumber() + " ");
		}
		System.out.println();
	}
	
	public static void printBet(Player p, int index) {
		System.out.println(p.getBet().get(index).getBet());
	}
	
	public static void printWallet(Person p) {
		System.out.println(p.getWallet().getMoney());
	}
	
	public static void printName(Person p){
		System.out.println(p.getName());
	}
}
