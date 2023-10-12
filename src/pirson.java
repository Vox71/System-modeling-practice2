import static java.lang.Math.pow;

public class pirson {
    int k;
    public pirson(int k) {
        this.k = k;
    }
    public float getPirson(float[] resY){
        float x = 0;
        for(int i = 0; i < k; i++) {
            x = (float) (x + ((pow(((double) 1 / k - resY[i]), 2)) /resY[i]));
        }
        return x;
    }
}
