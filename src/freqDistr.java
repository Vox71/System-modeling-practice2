import java.util.concurrent.Callable;

public class freqDistr implements Callable<float[]> {
    int intervalsCount;
    int N;
    float[] RParamsArr;
    int A;
    int B;

    public freqDistr(int intervalsCount, float[] RParamsArr, int A, int B) {
        this.RParamsArr = RParamsArr;
        this.N = RParamsArr.length;
        this.A = A;
        this.B = B;
        this.intervalsCount = intervalsCount;
    }

    public float[] call() throws Exception {
        float dY = (float) (B - A) / intervalsCount;
        float[] freq = new float[intervalsCount];
        for (int j = 0; j < N; j++) {
            float Yc = RParamsArr[j];
            int fN = (int) (Yc / dY);
            freq[fN] = freq[fN] + 1;
        }
        for (int i = 0; i < intervalsCount; i++) {
            freq[i] = freq[i] / (N * dY);
        }
        return freq;
    }
}

