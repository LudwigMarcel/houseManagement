package model.entities.enums;

public enum Category {

	FOOD(1), TRANSPORT(2), UTILITIES(3), SALARY(4), ENTERTAINMENT(5), RENT(6), CLOTHING(7), HEALTH(8), STREAMING(9),
	OTHER(10);

	private int code;

	private Category(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Category valueOf(int code) {
		for (Category value : Category.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid transaction code");
	}

}
