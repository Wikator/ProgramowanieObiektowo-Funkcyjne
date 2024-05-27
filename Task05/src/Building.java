import java.time.LocalDate;

public abstract sealed class Building permits Apartment, House {
    protected final String street;
    protected final int houseNumber;
    protected final String city;
    protected final String postalCode;
    protected final int area;
    protected final float price;
    protected final LocalDate date;

    protected Building(String street, int houseNumber,
                       String city, String postalCode,
                       int area, float price, LocalDate date) {

        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.area = area;
        this.price = price;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public int getArea() {
        return area;
    }

    public float getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public abstract String toString();
}
