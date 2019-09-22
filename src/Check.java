import java.util.List;

public class Check {
	public Check() {
		
	}
	
	public boolean checkBust(Person P, int which) {
		List<HandCard> hc = P.getHandCard();
		int[] v = ((HandCard) hc).getValue();
		if (v[which]>21) {
			return true;
		}
		else return false;
	}
	
	public int checkTotal(Person P, int which) {
		List<HandCard> hc = P.getHandCard();
		int[] v = ((HandCard) hc).getValue();
		return v[which];
	}
	
	public int checkWin(Player P, Person D, int which) {
		if (checkBust(P, which)==false) {
			if (checkTotal(P, which)>checkTotal(D, 0)) {
				return 1;
			}
			if (checkTotal(P, which)==checkTotal(D, 0)) {
				return 0;
			}
			else return -1;
		}
		else return -1;		
	}
}
