import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    Stream.of(in.nextLine())
            .map(String::toLowerCase)
            .flatMap(x -> Arrays.stream(x.split("[\\p{Blank}\\p{Punct}]+")))
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
            .entrySet()
            .stream()
            .sorted(Main::compare)
            .limit(10)
            .forEach(e -> System.out.println(e.getKey()));
  }

  private static int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
    if (e1.getValue().equals(e2.getValue())) {
      return e1.getKey().compareTo(e2.getKey());
    } else {
      return -e1.getValue().compareTo(e2.getValue());
    }
  }
}
