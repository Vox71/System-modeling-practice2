import java.util.concurrent.*;

public class randomNumbers {
    public static long rand(long a, int b, long m, long x_i){
        return (a * x_i + b) % m;
    }
    public static float[] sequence(long a, int b, long m, int N, float A_i, float B_i){
        long[] RNumsArr = new long[N];
        RNumsArr[0] = rand(a, b, m, 1);
        for(int i = 1; i < N; i++){
            RNumsArr[i] = rand(a, b, m, RNumsArr[i-1]);
        }
        float[] RParamsArr = new float[N];
        for(int i = 0; i < N; i++){
            RParamsArr[i] =  A_i + (B_i - A_i) * ((float) RNumsArr[i] / m);
            System.out.println("прикол");

        }
        return RParamsArr;
    }
}

