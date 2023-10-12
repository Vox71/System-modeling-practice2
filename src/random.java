import java.util.concurrent.Callable;

public class random implements Callable<float[]> {
    int max;
    int N;

    public random(int max, int N) {
        this.max = max;
        this.N = N;
    }

    public float generateRandom(){
        return (float) (Math.random() * max);
    }

    public float[] call() throws Exception {
        float[] RParamsArr = new float[N];
        for(int i = 0; i < N; i++){
            RParamsArr[i] = generateRandom();
        }
        return RParamsArr;
    }
}
