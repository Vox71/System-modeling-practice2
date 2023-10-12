import java.util.concurrent.Callable;

public class random implements Callable<float[]> {
    float max;
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
        float min = 0;
        float max_i = max;
        for(int i = 0; i < N; i++){
            RParamsArr[i] = generateRandom();
            if(RParamsArr[i]<min){
                min = RParamsArr[i];
            }
            if(RParamsArr[i]>max_i){
                max_i = RParamsArr[i];
            }
        }
        System.out.println("Минимальное значение при N = "+ N + ": " + min);
        System.out.println("Максимальное значение  при N = "+ N + ": " + max_i);
        return RParamsArr;
    }
}
