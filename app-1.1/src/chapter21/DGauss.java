package chapter21;

public class DGauss {
  
  int getVal(int param) {
    int res = 0;
    for (int i = 0; i < param; i++) {
      res = res + (int)(Math.random()*param);
    }
    return res;
  }
}
