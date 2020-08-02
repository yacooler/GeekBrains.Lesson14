import java.util.Arrays;

public class MainApp {

    static final int SIZE = 10000000;

    public static void main(String[] args) {
        doTest();
    }

    private static void doTest() {
        long startMillis;
        float[] arr = new float[SIZE];


        Arrays.fill(arr,1.0F);
        startMillis = System.currentTimeMillis();
        singleTreadMethod(arr);
        System.out.println("single thread:" + (System.currentTimeMillis() - startMillis) / 1000.0F );


        Arrays.fill(arr,1.0F);
        startMillis = System.currentTimeMillis();
        multiThreadMethod(arr);
        System.out.println("multi thread:" + (System.currentTimeMillis() - startMillis) / 1000.0F );
    }

    public static void singleTreadMethod(float[] arr){
        Thread thread = new Thread(new CalcExpression(arr));
        thread.start();

        //Один из обходных вариантов "в лоб" дождаться окончания потока, если не использовать join
        while (thread.isAlive());

    }

    static public void multiThreadMethod(float[] arr){
        float[] leftArray = new float[SIZE/2 + SIZE%2];
        float[] rightArray = new float[SIZE/2];

        System.arraycopy(arr,0,leftArray, 0, SIZE/2 + SIZE%2);
        System.arraycopy(arr,SIZE/2 + SIZE%2 - 1, rightArray, 0, SIZE/2);

        Thread thread1 = new Thread(new CalcExpression(leftArray));
        Thread thread2 = new Thread(new CalcExpression(rightArray));

        thread1.start();
        thread2.start();

        //join вызывается для текущего потока и останавливает его выполнение пока не отработают дочерние потоки
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(leftArray,0, arr,0,SIZE/2 + SIZE%2);
        System.arraycopy(rightArray,0, arr,SIZE/2 + SIZE%2 - 1,SIZE/2);

    }


}
