package multiThread;

import java.util.*;

public class InterruptTest implements Runnable {

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            System.out.println("Thread is running");
            try{
                Thread.sleep(200);
            }catch (InterruptedException e){
                System.out.println("Something interrupted me...");
                System.out.println(Thread.currentThread().isInterrupted());// false
                Thread.currentThread().interrupt();
                System.out.println(Thread.currentThread().isInterrupted());// true
            }
        }
    }


    public static void main(String[] args){
        InterruptTest interruptTest = new InterruptTest();
        Thread thread = new Thread(interruptTest);
        thread.start();
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        thread.interrupt();
        Map<Integer, Integer> map = new HashMap<>();
        for(Map.Entry<Integer, Integer> entry:map.entrySet()){

        }
    }
}
