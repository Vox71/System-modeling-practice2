import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.*;
import java.util.Collections.*;

import static java.lang.Math.max;
import static java.lang.Math.pow;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //Задаем все необходимые переменные
        long a = 22695477;
        int b = 1;
        long m = 4294967296L; //L на конце пишем потому что джава по другому не поймет что у нас тут число long, он все еще
        int A_i = 0;          //будет думать что присваиваем числу long m число int 4294967296, а таких больших интов не бывает
        int B_i = 10;
        //ЗАДАНИЕ 1. см. класс randomNumbers

/////////////////////////////////////////////////////
        //ЗАДАНИЕ 2.
        //вызываем ExecutorService и говорим ему что максимум может быть 4 потока. Вобщем объект executor будет заниматься всеми нашими потоками
        ExecutorService executor = Executors.newFixedThreadPool(4);
        //тут мы делаем список объектов Future, которые будут содержать будущий return из вызванных потоков. for что бы собрать все потоки
        List<Future<float[]>> list = new ArrayList<>();
        //executor.submit(new randomThread(a, b, m, (int) pow(10, j), A_i, B_i)) отсюда вернутся потоки, return которых пихаем в Future
        List<float[]> arrayList = new ArrayList<>();
        for(int j = 0; j < 4; j++){
            Future<float[]> future = executor.submit(new randomThread(a, b, m, (int) pow(10, (j+2)), A_i, B_i));
            list.add(future);
            arrayList.add(list.get(j).get()); //второй .get отвечает за получение return из штуки которую мы в future запихнули
        }

        //это необязательный вывод всех результатов всех потоков. В терминал идеи не помещается, но пусть будет
//        for(Future<float[]> fut : list) {
//            try {
//                System.out.println(Arrays.toString(fut.get()));
//            }
//            catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
        executor.shutdown(); //выключаем executorservice, после этого программа продолжит работу как обычно
//////////////////////////////////////////////
//        //ЗАДАНИЕ 7. Выполняем его ТАК ЖЕ как и 2, но вместо нашего алгоритма используем Math.random. Пока могу сказать
//        // что наш алгоритм выполняет работу быстрее чем Math.random
//        ExecutorService executor = Executors.newFixedThreadPool(4);
//        //тут мы делаем список объектов Future, которые будут содержать будущий return из вызванных потоков. for что бы собрать все потоки
//        List<Future<float[]>> list = new ArrayList<>();
//        //executor.submit(new randomThread(a, b, m, (int) pow(10, j), A_i, B_i)) отсюда вернутся потоки, return которых пихаем в Future
//        List<float[]> arrayList = new ArrayList<>();
//        for(int j = 0; j < 4; j++){
//            Future<float[]> future = executor.submit(new random(10, (int) pow(10, (j+2))));
//            list.add(future);
//            arrayList.add(list.get(j).get()); //второй .get отвечает за получение return из штуки которую мы в future запихнули
//        }

//        //это необязательный вывод всех результатов всех потоков. В терминал идеи не помещается, но пусть будет
//        for(Future<float[]> fut : list) {
//            try {
//                System.out.println(Arrays.toString(fut.get()));
//            }
//            catch (InterruptedException | ExecutionException e) {
//                e.printStackTrace();
//            }
//        }
//        executor.shutdown(); //выключаем executorservice, после этого программа продолжит работу как обычно
//////////////////////////////////////////////////////////////


        //ЗАДАНИЕ 3.1
        float M = (float) (A_i + B_i) / 2; //мат ожидание
        float D = (float) ((pow((B_i - A_i), 2)) / 12); //дисперия
        System.out.println("Математическое ожидание: " + M);
        System.out.println("Дисперия: " + D);

        //ЗАДАНИЕ 3.2, см. классы expectedValue и dispersion, первый отвечает за генерацию погрешности мат ожидания для последовательности чисел, а второй дисперсию для той же последовательсти
        //Тут мы создаем новый ExecutorService что бы запустить потоки, которые будут считать погрешности мат ожидания для каждого RParamsArr
        ExecutorService executorExpected = Executors.newFixedThreadPool(4);
        List<Future<Float>> listExpectedFuture = new ArrayList<>();
        List<Float> expectedValueList = new ArrayList<>();

        for(int j = 0; j < 4; j++){
            Future<Float> future = executorExpected.submit(new expectedValue(0, 10, arrayList.get(j)));
            listExpectedFuture.add(future);
            expectedValueList.add(listExpectedFuture.get(j).get());
            System.out.println("Погрешность математического ожидания при N^"+ (j+2) + ": " + expectedValueList.get(j));
        }
        executorExpected.shutdown();

        //ровно то же делаем и с дисперией, потоки все такое
        ExecutorService executorDispersion = Executors.newFixedThreadPool(4);
        List<Future<Float>> listDispersionFuture = new ArrayList<>();
        List<Float> dispersionList = new ArrayList<>();
        for(int j = 0; j < 4; j++){
            Future<Float> future = executorDispersion.submit(new dispersion(0, 10, arrayList.get(j)));
            listDispersionFuture.add(future);
            dispersionList.add(listDispersionFuture.get(j).get());
            System.out.println("Погрешность дисперсии при N^"+ (j+2) + ": " + dispersionList.get(j));
        }
        executorDispersion.shutdown();

        //ЗАДАНИЕ 4.1. см. класс randPeriod.
        //ЗАДАНИЕ 4.2.
        //тут ровно так же потоки делаем для высчитывания периода
        ExecutorService executorPeriod = Executors.newFixedThreadPool(4);
        List<Future<float[]>> listPeriodFuture = new ArrayList<>();
        List<float[]> periodList = new ArrayList<>();
        for(int j = 0; j < 4; j++){
            Future<float[]> future = executorPeriod.submit(new randPeriod(arrayList.get(j)));
            listPeriodFuture.add(future);
            periodList.add(listPeriodFuture.get(j).get());
            System.out.println("Период последовательности чисел при N^:"+ (j+2) + ": " + Arrays.toString(periodList.get(j)));
        }
        executorPeriod.shutdown();

        //ЗАДАНИЕ 5. см. класс freqDistr.
        //ЗАДАНИЕ 6. используем freqDistr из задания 5 для каждой последовательности чисел, получаем список из resY,
        // которые потом используем для построение гистограммы.
        //и то же самое ждя относительной частоты случайных чисел, и значения критерия Пирсона
        ExecutorService executorFreq = Executors.newFixedThreadPool(4);
        List<Future<float[]>> listFreqFuture = new ArrayList<>();
        List<float[]> freqList = new ArrayList<>();
        for(int j = 0; j < 4; j++){
            Future<float[]> future = executorFreq.submit(new freqDistr(10, arrayList.get(j), A_i, B_i));
            listFreqFuture.add(future);
            freqList.add(listFreqFuture.get(j).get());
            System.out.println("Относительная частота случайных числе из выборки при N^:"+ (j+2) + ": " + Arrays.toString(freqList.get(j)));
            System.out.println("Значение критерия Пирсона: " + new pirson(10).getPirson(freqList.get(j)));
        }

        // Тут выводим гистограмму. Использовалась библиотека swing, с помощью который выводим контейнеры JFrame. Внутри
        // контейнера рисуем гистограмму с помощью методов swing. Ниже предоставлен код для вызова одной гистрограммы.
        // Код не позволяет выводить больше одной гистограммы, так как данные будут перекрвыаться. Гистограмма имеет resX,
        // создающий линию X от 0 до 10, чего хватает для выполнения задания. Y генерируется из полученных ранее частот,
        // которые хранятся в списке freqList
        GrGis gis = new GrGis(freqList.get(3), "RParamsArr_e5");
        gis.setVisible(true);
        gis.createGis(freqList.get(3));
    }
}