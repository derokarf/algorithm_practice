package chapter21;

import java.util.Random;

public class Poisson extends CustomRandom {

  //Donald Knuth
  @Override
  public int getVal(int mean) {
    Random r = new Random();
    double L = Math.exp(-mean);
    int k = 0;
    double p = 1.0;
    do {
      p = p * r.nextDouble();
      k++;
    } while (p > L);
    return k - 1;
  }

  @Override
  public String getName() {
    return "Poisson";
  }
}
