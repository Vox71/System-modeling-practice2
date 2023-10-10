import java.util.concurrent.*;
class randomThread implements Callable<float[]> {
    long a;
    int b;
    long m;
    int N;
    int A_i;
    int B_i;
    public randomThread(long a, int b, long m, int N, int A_i, int B_i) {
        this.a = a;
        this.b = b;
        this.m = m;
        this.N = N;
        this.A_i = A_i;
        this.B_i = B_i;
    }

    public float[] call() throws Exception {
        return randomNumbers.sequence(a, b, m, N, A_i, B_i);
    }
}