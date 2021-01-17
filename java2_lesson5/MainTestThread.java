package java2_lesson5;


public class MainTestThread {

    public static final int SIZE = 10000000;
    public static final int h = SIZE / 2;

    public static void main(String[] args) {

        MainTestThread MainTestThread = new MainTestThread();
        MainTestThread.methodA();
        MainTestThread.methodB();
    }

    public void methodA() {
        System.out.println("Start A");
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();
        for (int i = 0; i < SIZE; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("End A. - Прошло времени, мс: " + elapsed);
    }

    public void methodB() {
        System.out.println("Start B");
        float[] arr = new float[SIZE];
        for (int i = 0; i < SIZE; i++) {
            arr[i] = 1;
        }

        long start = System.currentTimeMillis();
        float[] arr1 = new float[h];
        float[] arr2 = new float[h];
        System.arraycopy(arr, 0, arr1, 0, h);
        System.arraycopy(arr, h, arr2, 0, h);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr1[i] = (float) (arr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }, "Т1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < h; i++) {
                arr2[i] = (float) (arr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }, "Т2");

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, h);
        System.arraycopy(arr2, 0, arr, h, h);

        long finish = System.currentTimeMillis();
        long elapsed = finish - start;
        System.out.println("End B. - Прошло времени, мс: " + elapsed);
    }


}


