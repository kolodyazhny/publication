package ua.nure.kolodiazhny.SummaryTask04.entities;

/**
 * ����� ����������� �������� �������������� �������.
 */
public class Publication {
    /**
     * id �������������� ������� � ��.
     */
    private int id;
    /**
     * �������� �������������� �������.
     */
    private String title;
    /**
     * ���� �������������� �������.
     */
    private int price;
    /**
     * ������������, ����������� ������������� �������.
     */
    private String publisher;
    /**
     * ������� �������� �������������� �������
     */
    private String description;
    /**
     * ����� ISSN �������������� �������.
     */
    private String ISSN;

    public Publication(){}

    public Publication(int id, String title, int price, String publisher, String description, String ISSN){
        this.setId(id);
        this.setTitle(title);
        this.setPrice(price);
        this.setPublisher(publisher);
        this.setDescription(description);
        this.setISSN(ISSN);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    @Override
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        sb.append("Periodical{");
        sb.append("title = '").append(title).append("'");
        sb.append(", price = ").append(price);
        sb.append(", publisher = ").append(publisher);
        sb.append(", description = '").append(description).append("'");
        sb.append(", ISSN = '").append(ISSN).append("'");
        sb.append("}");
        return sb.toString();
    }
}