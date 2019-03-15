package multiThread;

public class TakeTurnsPrint implements Runnable{
    private final Object prev ;
    private final Object self ;
    private String name;
    private TakeTurnsPrint(String name, Object prev, Object self){
        this.name = name;
        this.prev = prev;
        this.self = self;
    }
    @Override
    public void run() {
        int count = 10;
        while(count>0){
            // 获得prev后进入
            synchronized (prev){
                // 获得self后进入
                synchronized (self){
                    System.out.print(name);
                    count--;
                    // 主动释放self，唤醒其他在等待self的线程
                    self.notify();
                }
                // synchronized语句块结束，释放self
                try{
                    // 主动释放prev， 同时本线程进入休眠，直到其他线程唤醒
                    prev.wait();
                }catch (Exception error){
                    error.printStackTrace();
                }
                //prev.notify();
            }
            //synchronized语句块结束，释放prev
        }
    }

    public static void main(String[] args){
        Object a = new Object();
        Object b = new Object();
        Object c = new Object();
        TakeTurnsPrint takeTurnsPrint1 = new TakeTurnsPrint("A", c, a);
        TakeTurnsPrint takeTurnsPrint2 = new TakeTurnsPrint("B", a, b);
        TakeTurnsPrint takeTurnsPrint3 = new TakeTurnsPrint("C", b, c);
        new Thread(takeTurnsPrint1).start();
        try{
            Thread.sleep(1000);
        }catch (Exception err){
            err.printStackTrace();
        }
        new Thread(takeTurnsPrint2).start();
        try{
            Thread.sleep(1000);
        }catch (Exception err){
            err.printStackTrace();
        }
        new Thread(takeTurnsPrint3).start();
        try{
            Thread.sleep(1000);
        }catch (Exception err){
            err.printStackTrace();
        }


    }
}
