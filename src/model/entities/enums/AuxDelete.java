package model.entities.enums;

public enum AuxDelete {

	Despesa(1), Entrada(2);
	
	private int code;

	private AuxDelete(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static AuxDelete valueOf(int code) {
		for (AuxDelete value : AuxDelete.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid transaction code");
	}

}
