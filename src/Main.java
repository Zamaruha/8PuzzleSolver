public class Main {
  /**
   */
  // TODO
  // So the problem is,
  // 1) all states need to be stored
  // 2) some of them will have same manhattan distance
  // 3) there is a need to be able to fetch a state with lowest manhattan distance
  // 4) duplicates should be checked
  // 5) performance is relevant
  // 6) this can be achieved by only editing solve() and expand()
  private static String solve() {
    // long time = System.currentTimeMillis();
    // State state = new State();
    // HashSet<State> duplicates = new HashSet<>();
    // HashMap<Integer, Set<State>> todo = new HashMap<>();
    // HashMap<Integer, Set<State>> children = new HashMap<>();
    // todo.put( state.getCurrentManhattanDistance(),);
    // while (!todo.isEmpty()) {
    // int currentIndex = Collections.min(todo.keySet());
    // State current = todo.get(currentIndex);
    // todo.remove(currentIndex);
    // if (current.isSolution()) {
    // current.printSolution();
    // double diff = (System.currentTimeMillis() - time) / 1000.0;
    // System.out.println("Duration: " + diff + "s");
    // return current.toString();
    // } else {
    // duplicates.put(current.getFreeFieldIndex(), current);
    // children.putAll(current.expand());
    // Iterator<Entry<Integer, State>> iter = children.entrySet().iterator();
    // while (iter.hasNext()) {
    // Entry<Integer, State> currentChildStateEntry = iter.next();
    // if (!duplicates.contains(currentChildStateEntry)) {
    // todo.putAll(currentChildStateEntry);
    // }
    // }
    // duplicates.addAll(children);
    // children.clear();
    // }
    // }
    return "No solution was found";
  }

  public static void main(String[] args) {
    try {
      System.out.println("Solution: " + '\n');
      System.out.println(solve());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
