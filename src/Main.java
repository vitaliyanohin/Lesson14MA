import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    ArrayList<String> lst = new ArrayList<>();
    while (in.hasNext()) {
      lst.add(in.next());
    }
    Map<String, Long> map = new HashMap<>();
    ArrayList<String> finalLst = lst.stream()
            .map(String::toLowerCase)
            .flatMap(x -> Arrays.stream(x.split("[\\\\p{Blank}\\\\p{Punct}]+")))
            .collect(Collectors.toCollection(ArrayList::new));
    finalLst.stream().peek(x -> {
      long result = finalLst.stream().filter(x::equals).count();
      map.put(x, result);
    }).count();
    map.entrySet()
            .stream()
            .sorted(Main::compare)
            .limit(10).forEach(x -> System.out.println(x.getKey()));
  }

  private static int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
    if (e1.getValue().equals(e2.getValue())) {
      return e1.getKey().compareTo(e2.getKey());
    } else {
      return -e1.getValue().compareTo(e2.getValue());
    }
  }
}


