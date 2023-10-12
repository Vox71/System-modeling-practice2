import java.util.concurrent.Callable;

import static java.lang.Math.abs;

public class expectedValue implements Callable<Float> {
    float M;
    float[] RParamsArr;
    int N;


    public expectedValue(int A_i, int B_i, float[] RParamsArr) {
        this.RParamsArr = RParamsArr;
        this.N = RParamsArr.length;
        this.M = (float) (A_i + B_i) / 2;
    }

    public float getM_sum() {
        float M_sum = 0;
        for (int i = 0; i < N; i++) {
            M_sum = M_sum + RParamsArr[i];
        }
        ;
        M_sum = M_sum / N;
        return M_sum;
    }
    public Float call() throws Exception {
        float M_sum = getM_sum();
        return abs((M - M_sum) / M) * 100;
    }
}
