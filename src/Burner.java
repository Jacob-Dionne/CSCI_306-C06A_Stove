/**
 * Burner Class
 * 
 * @author Jacob Dionne
 * @author Phillip Hernandez
 * 
 * Purpose & Responsibilities: 
 * Responsible for changing temperate and settings of the burner.
 * 
 * Started work on Monday September, 19 2025
 */
public class Burner {

	public final static int TIME_DURATION = 2;
	
	
	public enum Temperature {
		BLAZING,
		HOT,
		WARM,
		COLD;
	}
	/* private enum for handling temperature changes
	 * INCREASE: the burner must increase to reach its desired temperature
	 * DECREASE: the burner must decrease to reach its desired temperature
	 * DESTINATION: the burner is at its desired temperature
	 */
	private enum State { 
		INCREASE, 
		DECREASE, 
		DESTINATION; 
	}
	
	private Temperature myTemperature;
	private Setting mySetting;
	/*
	 * decided to use state enum for determining which direction to  
	 * change the temperature because it is a clear way of communicating logic
	 * as opposed to a boolean 
	 */ 
	private State myState;
	private int timer;
	
	public Burner() {
		super();
		this.myTemperature = Temperature.COLD;
		this.mySetting = Setting.OFF;
		this.timer = 0;
		this.myState = State.DESTINATION;
	}
	
	public Temperature getMyTemperature() {
		return myTemperature;
	}

	public void plusButton() {
		timer = TIME_DURATION;
		myState = State.INCREASE;
		
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
		myState = State.DECREASE;
		
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
		if(myState == State.DESTINATION) {
			return;
		}
		
		if (timer > 1) {
			timer--;
			return;
		}
		
		
		Temperature target = null; 
		//determine target temperature based on current setting
		switch(mySetting) {
		case OFF:
			target = Temperature.COLD;
			break;
		case LOW:
			target = Temperature.WARM;
			break;
		case MEDIUM:
			target = Temperature.HOT;
			break;
		case HIGH:
			target = Temperature.BLAZING;
			break;
		}
		
		if(myTemperature == target) {
			myState = State.DESTINATION;
		}
		
		//handle heat changes using current state
		switch(myState) {
		case DESTINATION: 
			break;
		case INCREASE:
			//chose to handle increasing / decreasing temperature in a different 
			//function to improve readability
			increaseTemperature();
		break;
		case DECREASE:
			decreaseTemperature();
		break;
		}
		
	}
	
	private void increaseTemperature(){
		
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
	
	private void decreaseTemperature(){
		
		switch(myTemperature) {
		case WARM:
			myTemperature = Temperature.COLD;
			break;
		case HOT:
			myTemperature = Temperature.WARM;
			break;
		case BLAZING:
			myTemperature = Temperature.HOT;
			break;
		default:
			break;
		}
		timer = TIME_DURATION;
		
	}
	
	public void display() {
		String temperatureMessage = "";
		
		//convert myTemperature to a message equivalent
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
		//print in desired format
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
			for(int j = 0; j < 2; ++j) {
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
		for(int i = 0; i < 3; ++i) {
			test2.plusButton();
			test2.display();
		}
		for(int j = 0; j < 5*3; ++j) {
			System.out.println(test2.timer);
			test2.updateTemperature();
			test2.display();
		}
		
		System.out.println("\n__Suite 3___");
		//test decrementing
		for(int i = 0; i < 3; ++i) {
			test2.minusButton();
			test2.display();
		}
		for(int j = 0; j < 5*3; ++j) {
			System.out.println(test2.timer);
			test2.updateTemperature();
			test2.display();
		}
	}
}
