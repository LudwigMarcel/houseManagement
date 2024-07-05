package model.entities.enums;

public enum Category {

	Alimentação(1), Transporte(2), Utilidades(3), Salário(4), Lazer(5), Aluguel(6), Vestuário(7), Saúde(8), Streaming(9),
	Outros(10);

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
