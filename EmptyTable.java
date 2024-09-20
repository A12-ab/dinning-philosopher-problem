import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class EmptyTable {
    private final Lock[] chopsticks = new Lock[5]; // 5 chopsticks in empty table

    public EmptyTable() {
        // added lock to all the five chopstick
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }
    }

   
}