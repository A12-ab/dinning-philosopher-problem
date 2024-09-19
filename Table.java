import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final int tableId;
    private final Lock tableLock = new ReentrantLock();
    private final Lock[] chopsticks = new Lock[5]; // 5 chopsticks per table
    private int philosophersAtTable = 5; // Start with 5 philosophers

    public Table(int tableId) {
        this.tableId = tableId;
        // Initialize chopstick locks for this table
        for (int i = 0; i < 5; i++) {
            chopsticks[i] = new ReentrantLock();
        }
    }

  
}