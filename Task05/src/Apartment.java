import java.time.LocalDate;

public final class Apartment extends Building {
    private final int apartmentNumber;
    private final int floorNumber;

    public Apartment(String street, int houseNumber,
                     int apartmentNumber, String city,
                     String postalCode, int floorNumber,
                     int area, float price, LocalDate date) {

        super(street, houseNumber, city, postalCode, area, price, date);
        this.apartmentNumber = apartmentNumber;
        this.floorNumber = floorNumber;
    }

    public float getFloorNumber() {
        return floorNumber;
    }

    @Override
    public String toString() {

        return "Street: " + street + "\n" +
                "House number: " + houseNumber + "\n" +
                "Apartment number: " + apartmentNumber + "\n" +
                "City: " + city + "\n" +
                "Postal code: " + postalCode + "\n" +
                "Area: " + area + "\n" +
                "Floor number: " + floorNumber + "\n" +
                "Price: " + price + "\n" +
                "Date: " + date + "\n";
    }

}
