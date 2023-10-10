import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class dispersion {
    float D;
    public dispersion(int A_i, int B_i) {
        this.D = (float) ((pow((B_i - A_i), 2)) / 12);
    }
    public float epsD(float[] RParamsArr, int N){
        float D_sum = 0;
        float expValue = (new expectedValue(0, 10)).getM_sum(RParamsArr, N);
        for(int i = 0; i < N; i++){
            D_sum = (D_sum + ((float) pow(RParamsArr[i], 2)));
        }
        D_sum = D_sum / N;
        float epsD = (float) (((D_sum) - pow(expValue, 2)) * (N / (N - 1)));
        epsD = abs((D - epsD) / D) * 100;
        return epsD;
    }
}
