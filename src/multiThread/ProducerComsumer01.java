package multiThread;

public class ProducerComsumer01 {
    private static final String LOCK = "lock";
    private static final Integer FULL = 10;
    private static Integer count = 0;
    class Producer implements Runnable{
        @Override
        public void run() {
            for (int i=0;i<10;i++){
                try{
                    Thread.sleep(2000);
                }catch (Exception error){
                    error.printStackTrace();
                }
                synchronized (LOCK){
                    while(count.equals(FULL)){
                        try{
                            LOCK.wait();
                        }catch (Exception error){
                            error.printStackTrace();
                        }
                    }
                    count ++;
                    System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for(int i=0;i<10;i++){
                try{
                    Thread.sleep(2000);
                }catch (Exception error){
                    error.printStackTrace();
                }
                synchronized (LOCK){
                    while(count==0){
                        try{
                            LOCK.wait();
                        }catch (Exception error){
                            error.printStackTrace();
                        }
                    }
                    count --;
                    System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                    LOCK.notifyAll();
                }
            }
        }
    }

    public static void main(String[] args){
        ProducerComsumer01 producerComsumer01 = new ProducerComsumer01();
        new Thread(producerComsumer01.new Producer()).start();
        new Thread(producerComsumer01.new Consumer()).start();
        new Thread(producerComsumer01.new Producer()).start();
        new Thread(producerComsumer01.new Consumer()).start();
        new Thread(producerComsumer01.new Producer()).start();
        //new Thread(producerComsumer01.new Consumer()).start();

    }
}
