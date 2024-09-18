public class Philosopher extends Thread {
    private final int id;
    private final Table table;
    private final Lock[] chopsticks;
    private final EmptyTable emptyTable;
    private boolean movedToEmptyTable = false;

    public Philosopher(int id, Table table, Lock[] chopsticks, EmptyTable emptyTable) {
        this.id = id;
        this.table = table;
        this.chopsticks = chopsticks;
        this.emptyTable = emptyTable;
    }

    
}
