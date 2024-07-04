package model.entities.enums;

public enum TransactionType {

	DEBIT(1), CREDIT(2);

	private int code;

	private TransactionType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static TransactionType valueOf(int code) {
		for (TransactionType value : TransactionType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid transaction code");
	}
}
