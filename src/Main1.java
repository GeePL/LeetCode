
public class Main1{
    int ticketId = 0;
    String lock = "LOCK";
    public static void main ( String[] args){
        Main1 main1 = new Main1();
        Thread thread1 = new Thread(main1.new Seller());
        Thread thread2 = new Thread(main1.new Seller());
        thread1.start();
        thread2.start();
    }
    class Seller implements Runnable {
        boolean flag = true;
        @Override
        public void run(){
            while(flag){
                synchronized(lock){
                    if(ticketId == 100) {
                        try{
                            lock.wait();
                            flag = false;
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println(ticketId);
                    System.out.println(Thread.currentThread().getName() + " " + ticketId);
                    ticketId++;
                }
            }

        }
    }

}
