
package app;
import java.util.concurrent.Semaphore;


public class ReadAndWrite {
    

    static Semaphore mutex1 = new Semaphore(1);
    static Semaphore mutex2 = new Semaphore(1);
    static Semaphore mutex3 = new Semaphore(1);

    static Semaphore readLock = new Semaphore(1);
    static Semaphore writeLock = new Semaphore(1);
    static int readCount = 0;
    static int writeCount = 0;


}

    

