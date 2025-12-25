class SharedCounter {
    int count = 0;

    synchronized void increment() {
        count++;
        System.out.println("Shared Counter = " + count);
    }
}
class NumberThread extends Thread {
    SharedCounter counter;

    NumberThread(SharedCounter c) {
        counter = c;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Number: " + i);
            counter.increment();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

class SquareThread implements Runnable {
    SharedCounter counter;

    SquareThread(SharedCounter c) {
        counter = c;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println("Square: " + (i * i));
            counter.increment();
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}

public class EasyMultithreadingDemo {
    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();

        NumberThread t1 = new NumberThread(counter);
        Thread t2 = new Thread(new SquareThread(counter));

        t1.start();
        t2.start();
    }
}
