package multiThread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Use ReentrantLock
 */
public class ProducerComsumer02 {
    private static int count = 0;
    private static final int FULL = 10;

    private Lock lock = new ReentrantLock();

    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    class Producer implements Runnable {
        @Override
        public void run() {
            for(int i=0;i<FULL;i++){
                try{
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                lock.lock();
                try{
                    while(count==FULL){
                        try{
                            notFull.await();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    count++;
                    System.out.println(Thread.currentThread().getName()+" Produce, Now Total: "+count);
                    notEmpty.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for(int i=0;i<FULL;i++){
                try{
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                lock.lock();
                try{
                    while(count==0){
                        try{
                            notEmpty.await();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    count--;
                    System.out.println(Thread.currentThread().getName()+" Consume, Now Total "+count);
                    notFull.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerComsumer02 test2 = new ProducerComsumer02();
        new Thread(test2.new Producer()).start();
        new Thread(test2.new Consumer()).start();
        new Thread(test2.new Producer()).start();
        new Thread(test2.new Consumer()).start();
        new Thread(test2.new Producer()).start();
        new Thread(test2.new Consumer()).start();
        new Thread(test2.new Producer()).start();
        new Thread(test2.new Consumer()).start();
    }
}
