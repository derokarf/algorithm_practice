package chapter21;

public class Geometric extends CustomRandom {

  //Donald Knuth
  @Override
  public int getVal(int param) {
    double p = 1.0 / param;
    return (int) (Math.ceil(Math.log(Math.random()) / Math.log(1.0 - p)));
  }

  @Override
  public String getName() {
    return "Geometric";
  }
}
