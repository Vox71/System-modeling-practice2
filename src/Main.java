import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long a = 22695477;
        int b = 1;
        long m = 4294967296L;
        int A_i = 0;
        int B_i = 10;
        int N_e2 = 100;
        int N_e3 = 1000;
        int N_e4 = 10000;
        int N_e5 = 100000;
        float[] RParamsArr_e2 = new float[N_e2], RParamsArr_e3 = new float[N_e3], RParamsArr_e4 = new float[N_e4], RParamsArr_e5 = new float[N_e5];
        ExecutorService RParamsArr_executor;
        System.out.println("Запуск потоков");
        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<float[]>> list = new ArrayList<Future<float[]>>();
        for(int j = 2; j <= 5; j++){
            Future<float[]> future = executor.submit(new randomThread(a, b, m, (int) pow(10, j), A_i, B_i));
            list.add(future);
        }
        RParamsArr_e2 = list.get(0).get();
        RParamsArr_e3 = list.get(1).get();
        RParamsArr_e4 = list.get(2).get();
        RParamsArr_e5 = list.get(3).get();
        for(Future<float[]> fut : list) {
            try {
                System.out.println(Arrays.toString(fut.get()));
            }
            catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Остановка потоков");
        executor.shutdown();
        float M = (float) (A_i + B_i) / 2;
        float D = (float) ((pow((B_i - A_i), 2)) / 12);
        System.out.println("Математическое ожидание: " + M);
        System.out.println("Дисперия: " + D);
        System.out.println(Arrays.toString(RParamsArr_e2));
        float epsM = (new expectedValue(0, 10)).epsM(RParamsArr_e2, N_e2);
        System.out.println(epsM);
        float epsD = (new dispersion(0, 10)).epsD(RParamsArr_e2, N_e2);
        System.out.println(epsD);
        float[] TEST_1 = (new randPeriod(RParamsArr_e2, N_e2).getPeriod());
        float[] TEST_2 = (new randPeriod(RParamsArr_e3, N_e3).getPeriod());
        float[] TEST_3 = (new randPeriod(RParamsArr_e4, N_e4).getPeriod());
        float[] TEST_4 = (new randPeriod(RParamsArr_e5, N_e5).getPeriod());
        System.out.println((Arrays.toString(TEST_1) + " " + Arrays.toString(TEST_2) + " " + Arrays.toString(TEST_3) +
                " " + Arrays.toString(TEST_4)));
    }
}