package product;

public class Category {
    private String title;
    private double vat;

    public Category(String title, double vat) {
        this.title = title;
        this.vat = vat;
    }

    public static double calculateVAT(double price, double vat) {
        return price * (vat / 100);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getTitle() {
        return title;
    }

    public double getVat() {
        return vat;
    }

    @Override
    public String toString() {
        StringBuilder categoryInfo = new StringBuilder();
        categoryInfo.append("Category: ").append(title).append('\n')
                .append("VAT: ").append(vat).append("%");

        return categoryInfo.toString();
    }
}
