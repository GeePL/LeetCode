package multiThread;

import org.omg.PortableServer.THREAD_POLICY_ID;

public class UnreentrantLock02 {
    private boolean isLocked = false;
    public synchronized void lock() throws InterruptedException {
        while(isLocked)
            wait();
        isLocked = true;
    }
    public synchronized void unLock() {
        isLocked = false;
        notify();
    }
    public static void main(String[] args){
        Lock lock = new Lock();
        try{
            lock.lock();
        }catch (Exception e){
            e.printStackTrace();
        }
        try{
            lock.lock();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("lock");
        try {
            lock.unlock();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

class Lock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;
    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while(isLocked && lockedBy != thread){
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }
    public synchronized void unlock() throws Exception{
        if(lockedCount==0){
            throw new Exception("must be locked first");
        }
        if(Thread.currentThread() == this.lockedBy){
            lockedCount --;
            if(lockedCount == 0){
                isLocked = false;
                notify();
            }
        }
    }
}
