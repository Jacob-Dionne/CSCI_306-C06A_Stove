/**
 * Setting Class
 * 
 * @author Jacob Dionne
 * @author Phillip Hernandez
 * 
 * Purpose & Responsibilities: 
 * Enum for constant settings values. Holds a value for Burner class.
 * 
 * Started work on Monday September, 19 2025
 */
public enum Setting {
	OFF ("---"), LOW("--+"), MEDIUM("-++"), HIGH("+++");
	private String value;

	private Setting(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
	
}
