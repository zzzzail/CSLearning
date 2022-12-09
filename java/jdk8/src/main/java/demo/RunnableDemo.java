package demo;

/**
 * @author Zail
 * @version 1.0
 * @date 2022/5/22
 */
public class RunnableDemo {
    
    public static void main(String[] args) {
        MyRun run = new MyRun();
        Thread myThread = new Thread(run);
        myThread.start();
        
    }
    
    static class MyRun implements Runnable {
    
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }
    
}
