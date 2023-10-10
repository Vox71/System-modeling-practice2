import static java.lang.Math.abs;

public class expectedValue {
    float M;

    public expectedValue(int A_i, int B_i) {
        this.M = (float) (A_i + B_i) / 2;
    }
    public float getM_sum(float[] RParamsArr, int N){
        float M_sum = 0;
        for(int i = 0; i<N; i++){
            M_sum = M_sum + RParamsArr[i];
        };
        M_sum = M_sum / N;
        return M_sum;
    }
    public float epsM(float[] RParamsArr, int N){
        float M_sum = getM_sum(RParamsArr, N);
        return abs((M - M_sum) / M) * 100; //дописать модуль и eps
    }
}
