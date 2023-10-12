import java.util.concurrent.Callable;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class dispersion implements Callable<Float> {
    float D;
    float[] RParamsArr;
    int N;
    public dispersion(int A_i, int B_i, float[] RParamsArr) {
        this.RParamsArr = RParamsArr;
        this.N = RParamsArr.length;
        this.D = (float) ((pow((B_i - A_i), 2)) / 12);
    }
    public Float call() throws Exception {
        float D_sum = 0;
        float expValue = (new expectedValue(0, 10, RParamsArr)).getM_sum();
        for(int i = 0; i < N; i++){
            D_sum = (D_sum + ((float) pow(RParamsArr[i], 2)));
        }
        D_sum = D_sum / N;
        float epsD = (float) (((D_sum) - pow(expValue, 2)) * (N / (N - 1)));
        return abs((D - epsD) / D) * 100;
    }
}
