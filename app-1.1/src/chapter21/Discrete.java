package chapter21;

public class Discrete extends CustomRandom {

  @Override
  public int getVal(int param) {
    if (Math.random() < 0.5) {
      return (int) 0;
    }
    return (int) 1;
  }

  @Override
  public String getName() {
    return "Discrete";
  }
}
