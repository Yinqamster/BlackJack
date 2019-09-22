import java.util.List;
import java.util.Scanner;

public class Utils {
	private static Scanner scanner = new Scanner(System.in);

	public static String getName() {
		System.out.print("What's your name: ");
		return scanner.nextLine();
	}

	public static boolean realMan() {
		System.out.print("Is the dealer the real man? Y/y for yes, other for no:");
		char c = nextGame();
		return (c == 'y' || c == 'Y');
	}

	public static int getMoney() {
		System.out.print("How many money do you have: ");
		boolean flag = true;
		int m = 0;
		while (flag) {
			boolean temp = false;
			m = 0;
			String str = scanner.nextLine();
			char[] a = str.toCharArray();
			for (char c : a) {
				if (c < '0' || c > '9') {
					System.out.println("Wrong money format, please input again.");
					temp = true;
					break;
				}
				m = m * 10 + c - '0';
			}
			flag = temp;
		}
		return m;
	}

	public static char nextGame() {
		System.out.print("New game? Y/y for yes, other for no: ");
		String str = scanner.nextLine();
		return str.charAt(0);
	}
	
	public static int getNumberFromPlayer(){
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

	public static void printDealerHandCard(Person d) {
		List<HandCard> handCardList = d.getHandCard();
		List<Card> handCard = handCardList.get(0).getCards();
		System.out.print(handCard.get(0).getNumber() + " ");
		System.out.println("*");
	}
	
	public static void printBet(Player p, int index) {
		System.out.println(p.getBet().get(index).getBet());
	}
	
	public static void printWallet(Player p) {
		System.out.println(p.getWallet().getMoney());
	}
	
	public static void printName(Person p){
		System.out.println(p.getName());
	}
}
