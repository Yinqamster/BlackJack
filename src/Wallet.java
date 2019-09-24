
public class Wallet {
	private int money;
	
	public Wallet() {
		money = Config.DEFAULTMONEY;
	}
	
	public int getMoney() {
		return this.money;
	}
	
	public void setWallet(int num) {
		this.money = num;
	}
	
	public void winMoney(int num) {
		this.money = this.money + num;
	}
	
	public void loseMoney(int num) {
		this.money = this.money - num;
	}
}
