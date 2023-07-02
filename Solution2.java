import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution2 {
  public static void main(String[] argv) {
    String[] teleporters1 = {"3,1", "4,2", "5,10"};
    String[] teleporters2 = {"5,10", "6,22", "39,40", "40,49", "47,29"};
    String[] teleporters3 = {"6,18", "36,26", "41,21", "49,55", "54,52",
                              "71,58", "74,77", "78,76", "80,73", "92,85"};
    String[] teleporters4 = {"97,93", "99,81", "36,33", "92,59", "17,3",
                              "82,75", "4,1", "84,79", "54,4", "88,53",
                              "91,37", "60,57", "61,7", "62,51", "31,19"};

    System.out.println(destinations(teleporters1, 6, 0, 20));  // [1, 2, 6, 10]
    System.out.println(destinations(teleporters2, 6, 46, 100));  // [48, 49, 50, 51, 52, 29]
    System.out.println(destinations(teleporters2, 10, 0, 50));  // [1, 2, 3, 4, 7, 8, 9, 10, 22]
    System.out.println(destinations(teleporters3, 10, 95, 100));  // [96, 97, 98, 99, 100]
    System.out.println(destinations(teleporters3, 10, 70, 100));  // [72, 73, 75, 76, 77, 79, 58]
    System.out.println(destinations(teleporters4, 6, 0, 100));  // [1, 2, 3, 5, 6]
  }

  public static List<Integer> destinations(String[] teleporters, int dieSides, int start, int end) {
    List<Integer> result = new ArrayList<>();
    Set<Integer> visited = new HashSet<>();
    visited.add(start);

    dfs(teleporters, dieSides, start, end, visited, result);

    return result;
  }

  private static void dfs(String[] teleporters, int dieSides, int current, int end, Set<Integer> visited, List<Integer> result) {
    if (current == end) {
      result.add(current);
      return;
    }

    for (int i = 1; i <= dieSides; i++) {
      int next = current + i;
      if (next > end) {
        next = end;
      }

      for (String teleporter : teleporters) {
        String[] parts = teleporter.split(",");
        int from = Integer.parseInt(parts[0]);
        int to = Integer.parseInt(parts[1]);
        if (from == next) {
          next = to;
          break;
        }
      }

      if (!visited.contains(next)) {
        visited.add(next);
        dfs(teleporters, dieSides, next, end, visited, result);
        visited.remove(next);
      }
    }
  }
}
