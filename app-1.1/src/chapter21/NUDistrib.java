package chapter21;

public class NUDistrib {

  public static void main(String[] args) {
    CustomRandom[] listRandomBox = {new Gauss(), new Poisson(), new Geometric(), new Discrete()};
    SortBase[] list = {new SelectionSort(), new InsertionSort(), new ShellSort()};
    DoublingTest testerSort = new DoublingTest(list);
    for (CustomRandom randomBox : listRandomBox) {
      System.out.println(randomBox.getName());
      testerSort.testingCustomRND(1000, 300000, randomBox);
    }

  }
}
