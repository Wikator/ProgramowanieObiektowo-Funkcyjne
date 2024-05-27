import java.time.LocalDate;

public final class House extends Building {

    private final float landArea;

    public House(String street, int houseNumber,
                 String city, String postalCode,
                 int area, float price, float landArea,
                 LocalDate date) {

        super(street, houseNumber, city, postalCode, area, price, date);
        this.landArea = landArea;
    }

    @Override
    public String toString() {

        return "Street: " + street + "\n" +
                "House number: " + houseNumber + "\n" +
                "City: " + city + "\n" +
                "Postal code: " + postalCode + "\n" +
                "Area: " + area + "\n" +
                "Price: " + price + "\n" +
                "Land area: " + landArea + "\n" +
                "Price: " + price + "\n" +
                "Date: " + date + "\n";
    }
}
