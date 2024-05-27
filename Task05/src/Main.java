import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Main {
    private static final OfferList offerList = new OfferList();

    public static void main(String[] args) {
        boolean repeat = true;
        while (repeat) {
            printOptions();

            switch (getScanner().nextInt()) {
                case 1 -> addHouse();
                case 2 -> addApartment();
                case 3 -> showRelevantHouses();
                case 4 -> showRelevantApartments();
                case 5 -> showFilteredRelevantHouses();
                case 6 -> showFilteredRelevantApartments();
                case 7 -> seedOffers();
                case 8 -> repeat = false;
                default -> System.out.println("Invalid choice");
            }
        }
    }

    //region Main Methods

    private static void addHouse() {
        String street = getStringFromUser("Enter street: ");
        int houseNumber = getIntFromUser("Enter house number: ");
        String city = getStringFromUser("Enter city: ");
        String postalCode = getStringFromUser("Enter postal code: ");
        int area = getIntFromUser("Enter area: ");
        float price = getFloatFromUser("Enter price: ");
        float landArea = getFloatFromUser("Enter land area: ");
        LocalDate date = getDateFromUser("Enter date (yyyy-MM-dd): ");

        House house = new House(street, houseNumber, city, postalCode, area, price, landArea, date);
        offerList.addOffer(house);

        System.out.println("House added successfully\n\n");
    }

    private static void addApartment() {
        String street = getStringFromUser("Enter street: ");
        int houseNumber = getIntFromUser("Enter house number: ");
        int apartmentNumber = getIntFromUser("Enter apartment number: ");
        String city = getStringFromUser("Enter city: ");
        String postalCode = getStringFromUser("Enter postal code: ");
        int floorNumber = getIntFromUser("Enter floor number: ");
        int area = getIntFromUser("Enter area: ");
        float price = getFloatFromUser("Enter price: ");
        LocalDate date = getDateFromUser("Enter date (yyyy-MM-dd): ");

        Apartment apartment = new Apartment(street, houseNumber, apartmentNumber, city,
                postalCode, floorNumber, area, price, date);

        offerList.addOffer(apartment);

        System.out.println("Apartment added successfully\n\n");
    }

    private static void showRelevantHouses() {
       LocalDate currentDate = LocalDate.now();
        Predicate<Building> predicate = o -> (o.getDate().isAfter(currentDate) || o.getDate().isEqual(currentDate))
                && o instanceof House;

        List<Building> relevantHouses = offerList.getFilteredOffers(predicate);
        relevantHouses.forEach(System.out::println);
    }

    private static void showRelevantApartments() {
        LocalDate currentDate = LocalDate.now();
        Predicate<Building> predicate = o -> (o.getDate().isAfter(currentDate) || o.getDate().isEqual(currentDate))
                && o instanceof Apartment;

        List<Building> relevantApartments = offerList.getFilteredOffers(predicate);
        relevantApartments.forEach(System.out::println);
    }

    private static void showFilteredRelevantHouses() {
        LocalDate currentDate = LocalDate.now();

        String city = getStringFromUser("Enter city: ");
        int area = getIntFromUser("Enter minimal area: ");

        Predicate<Building> predicate = o -> (o.getDate().isAfter(currentDate) || o.getDate().isEqual(currentDate))
                && o.getCity().equals(city)
                && o.getArea() >= area
                && o instanceof House;

        List<Building> relevantHouses = offerList.getFilteredOffers(predicate);
        relevantHouses.forEach(System.out::println);
    }

    private static void showFilteredRelevantApartments() {
        LocalDate currentDate = LocalDate.now();

        String city = getStringFromUser("Enter city: ");
        int maxPrice = getIntFromUser("Enter maximum price: ");
        int minFloorNumber = getIntFromUser("Enter minimum floor number: ");

        Predicate<Building> predicate = o -> (o.getDate().isAfter(currentDate) || o.getDate().isEqual(currentDate))
                && o.getCity().equals(city)
                && o.getPrice() <= maxPrice
                && ((Apartment) o).getFloorNumber() >= minFloorNumber;

        List<Building> relevantApartments = offerList.getFilteredOffers(predicate);
        relevantApartments.forEach(System.out::println);
    }

    private static void seedOffers() {
        offerList.addOffer(new House("Street1", 1, "City1", "12345", 100, 100000, 100, LocalDate.now().minusDays(2)));
        offerList.addOffer(new House("Street2", 2, "City2", "23456", 200, 200000, 200, LocalDate.now().minusDays(1)));
        offerList.addOffer(new House("Street3", 3, "City3", "34567", 300, 300000, 300, LocalDate.now()));
        offerList.addOffer(new House("Street4", 4, "City4", "45678", 400, 400000, 400, LocalDate.now().plusDays(1)));
        offerList.addOffer(new House("Street5", 5, "City5", "56789", 500, 500000, 500, LocalDate.now().plusDays(2)));
        offerList.addOffer(new Apartment("Street1", 1, 1, "City1", "12345", 1, 100, 100000, LocalDate.now().minusDays(3)));
        offerList.addOffer(new Apartment("Street2", 2, 2, "City2", "23456", 2, 200, 200000, LocalDate.now().minusDays(2)));
        offerList.addOffer(new Apartment("Street3", 3, 3, "City3", "34567", 3, 300, 300000, LocalDate.now().minusDays(1)));
        offerList.addOffer(new Apartment("Street4", 4, 4, "City4", "45678", 4, 400, 400000, LocalDate.now()));
        offerList.addOffer(new Apartment("Street5", 5, 5, "City5", "56789", 5, 500, 500000, LocalDate.now().plusDays(1)));
        offerList.addOffer(new Apartment("Street6", 6, 6, "City6", "67890", 6, 600, 600000, LocalDate.now().plusDays(2)));
        offerList.addOffer(new Apartment("Street7", 7, 7, "City7", "78901", 7, 700, 700000, LocalDate.now().plusDays(3)));

        System.out.println("Offers seeded successfully\n\n");
    }

    //endregion

    //region Input Methods

    private static String getStringFromUser(String prompt)
    {
        System.out.println(prompt);
        return getScanner().next();
    }

    private static int getIntFromUser(String prompt)
    {
        try {
            System.out.println(prompt);
            return getScanner().nextInt();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a whole number");
            return getIntFromUser(prompt);
        }
    }

    private static float getFloatFromUser(String prompt)
    {
        try {
            System.out.println(prompt);
            return getScanner().nextFloat();
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter a number");
            return getFloatFromUser(prompt);
        }
    }

    private static LocalDate getDateFromUser(String prompt)
    {
        try {
            System.out.println(prompt);
            return LocalDate.parse(getScanner().next());
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid input. Please enter a time in the format HH:mm");
            return getDateFromUser(prompt);
        }
    }

    //endregion

    //region Print Methods

    private static void printOptions() {
        System.out.println("1. Add house");
        System.out.println("2. add apartment");
        System.out.println("3. Show relevant houses");
        System.out.println("4. Show relevant apartments");
        System.out.println("5. Show filtered relevant houses");
        System.out.println("6. Show filtered relevant apartments");
        System.out.println("7. Seed offers");
        System.out.println("8. Exit");
    }

    //endregion

    //region Utils

    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    //endregion
}