import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player extends Person{

	List<Bet> bet = new ArrayList<Bet>();
	private Wallet wallet = new Wallet();
	public Player() {}
	
	public Player(String name) {
		super(name);
	}
	
	public int takeAction(int index){
		System.out.println("Please select your action: ");
		System.out.println(Config.HITACTION + " Hit.");
		System.out.println(Config.STANDACTION+ " Stand.");
		System.out.println(Config.SPLITACTION + " Split.");
		System.out.println(Config.DOUBLEACTION + " Double up.");
		
		int currentMoney = wallet.getMoney();
		
		while(true) {
			int action = getNumberFromPlayer();
			while (action != Config.HITACTION && action != Config.STANDACTION
					&& action != Config.SPLITACTION && action != Config.DOUBLEACTION) {
				System.out.print("Please choose a correct action: ");
				getNumberFromPlayer();
			}
			
			switch (action) {
				case Config.HITACTION:
					return Config.HITACTION;
				case Config.STANDACTION:
					return Config.STANDACTION;
				case Config.SPLITACTION:{
					if(bet.size() != 1 || currentMoney < bet.get(0).getBet()
							|| handCard.size() != 1
							|| handCard.get(0).getCards().size() != 2
							|| !(handCard.get(0).getCards().get(0).getValue() == handCard.get(0).getCards().get(1).getValue())){
						System.out.print("You cannot take this action, please choose again:");
						break;
					}
					else {
						Bet bet1 = bet.get(0);
						Bet bet2 = new Bet(bet1.getBet());
						bet.add(bet2);
						HandCard handCardList1 = handCard.get(0);
						Card handCard2 = handCardList1.getCards().get(1);
						HandCard handCardList2 = new HandCard();
						handCardList2.getCards().add(handCard2);
						handCardList1.getCards().remove(1);
						handCard.add(handCardList2);
						wallet.setWallet(currentMoney - bet2.getBet());
						return Config.SPLITACTION;
					}
				}
				case Config.DOUBLEACTION: {
					int size = bet.size();
					//this shouldn't happen!!!
					if(!(index >= 0 && index < size)){
						System.out.println("wrong index");
						return -1;
					}
					
					if(currentMoney < bet.get(index).getBet()) {
						System.out.print("You cannot take this action, please choose again:");
						break;
					}
					else {
						int betNum = bet.get(index).getBet();
						bet.get(index).setBet(2 * betNum);
						wallet.setWallet(currentMoney - betNum);
						System.out.println(bet.get(index).getBet());
						return Config.DOUBLEACTION;
					}
				}
			}
		}
	}
	
	//TODO
	//should move to another common place!!
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
	
	
	public void makeBet(){
		
		//TODO
		int currentMoney = wallet.getMoney();
		System.out.print("Please input the money you want to bet: ");
		
		int money = getNumberFromPlayer();
		while(money > currentMoney || money > Config.MAXBET || money < Config.MINBET) {
			if(money > currentMoney) {
				System.out.print("The bet should be less than your total money, input again:");
			}
			else {
				System.out.println("Error: The bet should be more than " + Config.MINBET + " and less than " + Config.MAXBET);
				System.out.print("Please input again:");
			}
			
			money = getNumberFromPlayer();
		}
		
		bet.add(new Bet(money));
		wallet.setWallet(currentMoney - money);
		
//		System.out.println(money);
	}
	
	public void endGame(int result, int index) {
		switch(result) {
			case Config.PLAYERWIN: {
				System.out.println("You win!");
				//TODO
				wallet.winMoney(2*bet.get(index).getBet());
				break;
			}
			case Config.DEAL: {
				System.out.println("Deal!");
				//TODO
				wallet.winMoney(bet.get(index).getBet());
				break;
			}
			case Config.DEALERWIN: {
				System.out.println("You lose!");
				break;
			}
			case Config.BUST: {
				System.out.println("BUST!!!!");
				break;
			}
			//this shouldn't happen!!!
			default: {
				System.out.println("wrong result!!");
				break;
			}
		}
		
	}

}

