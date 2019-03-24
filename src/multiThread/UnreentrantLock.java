package multiThread;

import java.util.concurrent.atomic.AtomicReference;

public class UnreentrantLock {
    private AtomicReference<Thread> owner = new AtomicReference<>();
    public void lock() {
        Thread current = Thread.currentThread();
        System.out.println(current.getName());
        System.out.println("owner: "+owner.get());
        for(;;){
            if(!owner.compareAndSet(null, current)){
                return;
            }
        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();
        //System.out.println(current.getName());
        owner.compareAndSet(current, null);
    }

    public static void main(String[] args){
        UnreentrantLock unreentrantLock = new UnreentrantLock();
        unreentrantLock.lock();
        unreentrantLock.lock();
        //System.out.println(Thread.currentThread().getName());
        unreentrantLock.unlock();
        unreentrantLock.unlock();
    }

}
