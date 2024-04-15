import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GradeList {
    private final ArrayList<Double> grades = new ArrayList<>();
    private final List<Double> possibleGrades = List.of(2.0, 3.0, 3.5, 4.0, 4.5, 5.0);

    public boolean add(Double grade) {
        if (!possibleGrades.contains(grade))
            return false;

        grades.add(grade);
        return true;
    }

    public Optional<Double> average()
    {
        if (grades.isEmpty())
            return Optional.empty();

        Double sum = grades.stream().reduce(0.0, Double::sum);
        return Optional.of(sum / grades.size());
    }

    public Optional<Double> maxValue()
    {
        if (grades.isEmpty())
            return Optional.empty();

        Double maxValue = grades.stream().skip(1).reduce(grades.getFirst(), (acc, curr) -> {
            if (curr > acc)
                return curr;
            else
                return acc;
        });

        return Optional.of(maxValue);
    }

    public Optional<Double> minValue()
    {
        if (grades.isEmpty())
            return Optional.empty();

        Double minValue = grades.stream().skip(1).reduce(grades.getFirst(), (acc, curr) -> {
            if (curr < acc)
                return curr;
            else
                return acc;
        });

        return Optional.of(minValue);
    }
}
