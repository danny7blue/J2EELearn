package bean;

import java.util.concurrent.ThreadPoolExecutor;

class TestThread1 implements Runnable{  
    private int ticket = 5;  
    public void run(){  
        for (int i=0;i<10;i++)  
        {  
            if(ticket > 0){  
                System.out.println("ticket = " + ticket--);  
            }  
        }  
    }  
}  
  
public class ImplementRunnableDemo{  
    public static void main(String[] args){  
    	ThreadPoolExecutor threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue)
    	TestThread1 my = new TestThread1();  
        new Thread(my).start();  
        new Thread(my).start();  
        new Thread(my).start();  
    }  
}
