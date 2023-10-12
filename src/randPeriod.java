import java.util.concurrent.Callable;

public class randPeriod implements Callable<float[]> {
    int N;
    float[] RParamsArr;
    float[] result = new float[3];

    public randPeriod(float[] RParamsArr) {
        this.RParamsArr = RParamsArr;
        this.N = RParamsArr.length;
    }
    public float[] call() throws Exception {
        for(int i = 0; i < N; i++){
            float element = RParamsArr[i];
            for(int j = i; j < N; j++){
                if (element == RParamsArr[j] && i != j){
                    result[0] = j - i;
                    result[1] = i;
                    result[2] = j;
                    return result;
                }
            }
        }
        result[0] = -1;
        result[1] = -1;
        result[2] = -1;
        return result;
    }
}
