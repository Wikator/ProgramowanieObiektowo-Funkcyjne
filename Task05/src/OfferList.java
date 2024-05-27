import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class OfferList {
    private final List<Building> offers = new ArrayList<>();

    public void addOffer(Building offer) {
        offers.add(offer);
    }

    public List<Building> getFilteredOffers(Predicate<Building> predicate) {
        // return offers.stream().filter(predicate).toList();

        List<Building> filteredOffers = new ArrayList<>();

        for (Building offer : offers) {
            if (predicate.test(offer)) {
                filteredOffers.add(offer);
            }
        }

        return filteredOffers;
    }
}
