package multiThread;

import sun.awt.windows.ThemeReader;

public class TakeTurnsPrint2 {
    private static final String LOCK1 = "lock1";
    private static final String LOCK2 = "lock2";
    private static final Integer FULL = 10;

    class Print implements Runnable{
        String name;
        Print(String name){
            this.name = name;
        }
        @Override
        public void run() {
            for(int i=0;i<FULL;i++){
                synchronized (LOCK1){
                    System.out.println(name);
                    LOCK1.notify();
                    try{
                        LOCK1.wait();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }
            }
        }
    }
    public static void main(String[] args){
        TakeTurnsPrint2 takeTurnsPrint2 = new TakeTurnsPrint2();
        Thread thread1 = new Thread(takeTurnsPrint2.new Print("A"));
        Thread thread2 = new Thread(takeTurnsPrint2.new Print("B"));
        thread1.start();
        try{
            Thread.sleep(1000);
        }catch (Exception e){
            e.printStackTrace();
        }
        thread2.start();
    }
}
