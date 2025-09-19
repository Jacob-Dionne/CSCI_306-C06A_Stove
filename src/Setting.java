
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
