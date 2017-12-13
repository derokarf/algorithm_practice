package chapter21;

public class Gauss extends CustomRandom {

  @Override
  public int getVal(int param) {
    int res = 0;
    for (int i = 0; i < param; i++) {
      res = res + (int) (Math.random() * param);
    }
    return res;
  }

  @Override
  public String getName() {
    return "Gauss";
  }
}
