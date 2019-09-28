import java.util.List;
import java.util.Scanner;

public class Utils {
	private static Scanner scanner = new Scanner(System.in);

	public static int numOfPlayer() {
		System.out.println("Please input the number of players: ");
		int number = getNumberFromPlayer();
		while(number != 1 && number != 2) {
			System.out.println("Only one or two player(s) are allowed, please input again: ");
			number = getNumberFromPlayer();
		}
		return number;
	}
	
	public static String getName(String name) {
		String str;
		do {
			System.out.print("What's " + name + "'s name: ");
			str = scanner.nextLine();
		} while (str.length() == 0);
		return str;
	}

	public static boolean realMan() {
		String str;
		do {
			System.out.print("Is the dealer the real man? Y/y for yes, other for no:");
			str = scanner.nextLine();
		} while (str.length() == 0);
		return (str.charAt(0) == 'y' || str.charAt(0) == 'Y');
	}

	public static int getMoney() {
		System.out.print("How many money do you have: (default 200, please make sure you have more than 10) ");
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
		return m == 0 ? 200 : m;
	}

	public static char nextGame() {
		String str;
		do {
			System.out.print("New game? Y/y for yes, other for no: ");
			str = scanner.nextLine();
		} while (str.length() == 0);
		return str.charAt(0);
	}
	
	public static int getNumberFromPlayer(){
		String str = scanner.nextLine();
		while(!str.matches("^[0-9]*$") || str.isEmpty()) {
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
			System.out.print(handCard.get(i).getNumber() + "(" + handCard.get(i).getSuit() + ") ");
		}
		System.out.println();
	}

	public static void printDealerHandCard(Person d) {
		List<HandCard> handCardList = d.getHandCard();
		List<Card> handCard = handCardList.get(0).getCards();
		System.out.print(handCard.get(0).getNumber() + "(" + handCard.get(0).getSuit() + ") ");
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
