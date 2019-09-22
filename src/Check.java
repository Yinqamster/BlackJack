import java.util.List;

public class Check {
	public Check() {
		
	}
	
	public boolean checkBust(Person P, int which) {
		List<HandCard> hc = P.getHandCard();
		int[] v = hc.get(which).getValue();
		for (int i : v) {
			if (i <= 21)
				return false;
		}
		return true;
	}
	
	private int checkTotal(Person P, int which) {
		List<HandCard> hc = P.getHandCard();
		int[] v = hc.get(which).getValue();
		return v[which];
	}
	
	public int checkWin(Player P, Person D, int which) {
		if (!checkBust(P, which) && !checkBust(D, which)) {
			if (checkTotal(P, which)>checkTotal(D, 0)) {
				return Config.PLAYERWIN;
			}
			if (checkTotal(P, which)==checkTotal(D, 0)) {
				if (checkTotal(P, which) < 21)
					return Config.DEAL;
				if (P.getHandCard().get(which).isBlackJack() && D.getHandCard().get(which).isBlackJack())
					return Config.DEAL;
				if (P.getHandCard().get(which).isBlackJack())
					return Config.PLAYERWIN;
				if (D.getHandCard().get(which).isBlackJack())
					return Config.DEALERWIN;
				return Config.DEAL;
			}
			else return Config.DEALERWIN;
		}
		else {
			if (checkBust(P, which))
				return Config.DEALERWIN;
			if (checkBust(D, which))
				return Config.PLAYERWIN;
			return Config.DEAL;
		}
	}
}
