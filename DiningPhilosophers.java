import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {
    private static final int NUM_TABLES = 5;

    public static void main(String[] args) {
        Lock[][] forks = new Lock[NUM_TABLES][5];
        Table[] tables = new Table[NUM_TABLES];
        EmptyTable emptyTable = new EmptyTable();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < NUM_TABLES; i++) {
            tables[i] = new Table(i);
            for (int j = 0; j < 5; j++) {
                forks[i][j] = new ReentrantLock(); 
            }
        }
        Thread[] philosopherThreads = new Thread[NUM_TABLES * 5];
        int threadIndex = 0;

        for (int i = 0; i < NUM_TABLES; i++) {
            for (int j = 0; j < 5; j++) {
                char label = (char) ('A' + i * 5 + j);
                Philosopher philosopher = new Philosopher(label, tables[i], forks[i][j], forks[i][(j + 1) % 5], emptyTable, startTime);
                philosopherThreads[threadIndex] = new Thread(philosopher);  
                philosopherThreads[threadIndex].start();  
                threadIndex++;
            }
        }

        while (!emptyTable.isDeadlocked()) {
            try {
                Thread.sleep(100);  // Add a small delay to reduce CPU usage
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        long elapsedTime = System.currentTimeMillis() - startTime;
        System.out.println("Time taken for the sixth table to deadlock: " + elapsedTime + " ms.");
    }
}
