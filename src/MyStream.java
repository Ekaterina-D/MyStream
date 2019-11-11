import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MyStream {

    public static void main(String[] args) {
        String symbols = "0123456789";
        long amount = 100;
        Map<String, Long> collect =
                new Random().ints(amount, 0, symbols.length())
                        .mapToObj(symbols::charAt)
                        .map(Object::toString)
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                        .entrySet()
                        .stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (x1, x2) -> {
                                    throw new IllegalStateException();
                                },
                                LinkedHashMap::new
                        ));
        for (Map.Entry<String, Long> stringObjectEntry : collect.entrySet()) {
            double percent = (double) stringObjectEntry.getValue() / amount;
            System.out.printf("%s=%.2f%s%n", stringObjectEntry.getKey(), percent * 100," %");
        }
    }

}