import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class State implements Comparable<State> {
  private byte[] fields;
  private State parent;

  /**
   * 
   */
  public State() {
    fields = new byte[9];
    fields[0] = 0;
    fields[1] = 6;
    fields[2] = 3;
    fields[3] = 1;
    fields[4] = 4;
    fields[5] = 7;
    fields[6] = 2;
    fields[7] = 5;
    fields[8] = 8;
  }

  public State(State parent) {
    this.fields = parent.fields.clone();
    this.parent = parent;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + Arrays.hashCode(fields);
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    State other = (State) obj;
    if (!Arrays.equals(fields, other.fields))
      return false;
    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Comparable#compareTo(java.lang.Object)
   */
  @Override
  public int compareTo(State o) {
    if (this.getCurrentManhattanDistance() == o.getCurrentManhattanDistance()) {
      return 0;
    } else if (this.getCurrentManhattanDistance() > o.getCurrentManhattanDistance()) {
      return 1;
    } else if (this.getCurrentManhattanDistance() < o.getCurrentManhattanDistance()) {
      return -1;
    } else {
      throw new Error(
          "States " + this.toString() + " and " + o.toString() + " could not be compared");
    }
  }

  @Override
  public String toString() {
    return "State [fields=" + Arrays.toString(fields) + ", parent=" + parent + "]";
  }

  public boolean isSolution() {
    for (int i = 0; i < fields.length; i++) {
      if (fields[i] != i) {
        return false;
      }
    }
    return true;
  }

  public int getFreeFieldIndex() {
    for (int i = 0; i < fields.length; i++) {
      if (fields[i] == 0) {
        return i;
      }
    }
    throw new Error("No free field found");
  }

  public ArrayList<Integer> getNeighboursOfField(int index) {
    ArrayList<Integer> neighbours = new ArrayList<Integer>();
    switch (index) {
      case 0:
        neighbours.add(1);
        neighbours.add(3);
        break;
      case 1:
        neighbours.add(0);
        neighbours.add(2);
        neighbours.add(4);
        break;
      case 2:
        neighbours.add(1);
        neighbours.add(5);
        break;
      case 3:
        neighbours.add(0);
        neighbours.add(4);
        neighbours.add(6);
        break;
      case 4:
        neighbours.add(1);
        neighbours.add(3);
        neighbours.add(5);
        neighbours.add(7);
        break;
      case 5:
        neighbours.add(2);
        neighbours.add(4);
        neighbours.add(8);
        break;
      case 6:
        neighbours.add(3);
        neighbours.add(7);
        break;
      case 7:
        neighbours.add(4);
        neighbours.add(6);
        neighbours.add(8);
        break;
      case 8:
        neighbours.add(5);
        neighbours.add(7);
        break;
    }
    return neighbours;
  }

  public void switchFreeFieldWithField(int index) {
    if (!getNeighboursOfField(getFreeFieldIndex()).contains(index)) {
      throw new Error("Selected field " + index + " is not a neighbour of the free field");
    } else {
      fields[getFreeFieldIndex()] = fields[index];
      fields[index] = 0;
    }
  }

  public int getAmountOfStepsToTargetIndex(int currentIndex, int targetIndex) {
    if (currentIndex == targetIndex) {
      return 0;
    }
    int amountOfSteps = 0;
    HashSet<Integer> neighbours = new HashSet<Integer>();
    neighbours.addAll(getNeighboursOfField(currentIndex));
    for (int maximalAmountOfSteps = 0; maximalAmountOfSteps < 4; maximalAmountOfSteps++) {
      amountOfSteps++;
      if (neighbours.contains(targetIndex)) {
        return amountOfSteps;
      } else {
        Iterator<Integer> iter = neighbours.iterator();
        while (iter.hasNext()) {
          neighbours.add(iter.next());
        }
      }
    }
    throw new Error("There was no way found from " + currentIndex + "  to " + targetIndex);
  }

  public int getCurrentManhattanDistance() {
    int manhattanDistance = 0;
    for (int i = 0; i < fields.length; i++) {
      manhattanDistance += getAmountOfStepsToTargetIndex(fields[i], i);
    }
    return manhattanDistance;
  }

  public ArrayList<Integer> getMovablePuzzles() {
    ArrayList<Integer> output = new ArrayList<Integer>();
    output.addAll(getNeighboursOfField(getFreeFieldIndex()));
    return output;
  }

  public void makeMoveFrom(int index) {
    fields[getFreeFieldIndex()] = fields[index];
    fields[index] = 0;
  }

  void printSolution() {
    State s = this;
    StringBuilder solution = new StringBuilder();
    int i = 0;
    while (s != null) {
      solution.insert(0, s.toString() + " =>\n");
      i++;
      s = s.parent;
    }
    System.out.println(solution);
    System.out.println(i + " moves");
  }

  public HashMap<Integer, State> expand() {
    HashMap<Integer, State> children = new HashMap<>();
    ArrayList<Integer> movablePuzzles = getMovablePuzzles();
    Iterator<Integer> iter = movablePuzzles.iterator();
    while (iter.hasNext()) {
      State child = new State(this);
      int current = iter.next();
      child.makeMoveFrom(current);
      int manhattanDistance = child.getCurrentManhattanDistance();
      children.put(manhattanDistance, child);
    }
    return children;
  }

}
