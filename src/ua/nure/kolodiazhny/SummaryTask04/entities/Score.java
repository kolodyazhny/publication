package ua.nure.kolodiazhny.SummaryTask04.entities;

/**
 * �����, ����������� �������� ���������� �����.
 */
public class Score {
    /**
     * ��� ��������� �����.
     */
    private String name;
    /**
     * ����� �����
     */
    private String cardNumber;
    /**
     * ����� �� ������ ������� �����.
     */
    private int cvc;

    private int id;

    public Score () {};

    public Score(String name, String cardNumber, int cvc) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
    }
    public Score(int id, String name, String cardNumber, int cvc) {
    	this.id = id;
        this.name = name;
        this.cardNumber = cardNumber;
        this.cvc = cvc;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

		public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}