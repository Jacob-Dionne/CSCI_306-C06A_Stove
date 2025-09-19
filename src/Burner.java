
public class Burner {

	public final static int TIME_DURATION = 2;
	
	
	public enum Temperature {
		BLAZING,
		HOT,
		WARM,
		COLD;
	}
	
	private Temperature myTemperature;
	private Setting mySetting;
	private int timer;

	public Burner() {
		super();
		this.myTemperature = Temperature.COLD;
		this.mySetting = Setting.OFF;
		this.timer = 0;
	}
	
	public Temperature getMyTemperature() {
		return myTemperature;
	}

	public void plusButton() {
		timer = TIME_DURATION;
		switch(mySetting) {
			case OFF:
				mySetting = Setting.LOW;
				break;
			case LOW:
				mySetting = Setting.MEDIUM;
				break;
			case MEDIUM:
				mySetting = Setting.HIGH;
			default:
				break;
		}
	}
	
	public void minusButton() {
		timer = TIME_DURATION;
		switch(mySetting) {
		case HIGH:
			mySetting = Setting.MEDIUM;
			break;
		case MEDIUM:
			mySetting = Setting.LOW;
			break;
		case LOW:
			mySetting = Setting.OFF;
		default:
			break;
		}
	}
	
	public void updateTemperature() {
		if (timer > 0) {
			timer--;
			return;
		}
		
		switch(mySetting) {
		case LOW:
			this.increaseTemperature(Temperature.WARM);
			break;
		case MEDIUM:
			this.increaseTemperature(Temperature.HOT);
			break;
		case HIGH:
			this.increaseTemperature(Temperature.BLAZING);
			break;
		default:
			break;
		}
		
	}
	
	private void increaseTemperature(Temperature Target){
		if(myTemperature == Target) {
			return;
		}
		
		switch(myTemperature) {
		case COLD:
			myTemperature = Temperature.WARM;
			break;
		case WARM:
			myTemperature = Temperature.HOT;
			break;
		case HOT:
			myTemperature = Temperature.BLAZING;
			break;
		default:
			break;
		}
		timer = TIME_DURATION;
		
	}
	
	public void display() {
		String temperatureMessage = "";
		
		switch(myTemperature) {
		case COLD:
			temperatureMessage = "cooool";
			break;
		case WARM:
			temperatureMessage = "warm";
			break;
		case HOT:
			temperatureMessage = "CAREFUL";
			break;
		case BLAZING:
			temperatureMessage = "VERY HOT! DON'T TOUCH";
			break;
		}
		
		System.out.println("[" + mySetting.toString() + "]....." + temperatureMessage);
		
	}
	
	public static void main(String[] args) {
		Burner test = new Burner();
		
		//test incrementing in order
		System.out.println("__Suite 1___");
		
		test.display();
		System.out.println(test.getMyTemperature());
		
		for(int i =0; i < 3; ++i) {
			System.out.println("___TEST " + (i + 1) + " ___");
			test.plusButton();
			test.display();
			for(int j = 0; j < 3; ++j) {
				System.out.println(test.timer);
				test.updateTemperature();
			}
			//System.out.println(test.timer);
			test.display();
			System.out.println(test.getMyTemperature());
			System.out.println();
		}
		
		System.out.println("__Suite 2___");
		
		Burner test2 = new Burner();
		
		//test outcome of incrementing beyond setting
		for(int i = 0; i < 2; ++i) {
			test2.plusButton();
			test2.display();
		}
		for(int j = 0; j < 15; ++j) {
			System.out.println(test2.timer);
			test2.updateTemperature();
			test2.display();
		}
		
	}
}
