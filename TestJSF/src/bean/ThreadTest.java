package bean;

public class ThreadTest {
    public static void main(String[] args) {
          MyThread thread = new MyThread();
          Thread th1= new Thread(thread);
          Thread th2= new Thread(thread);
          th1.start();
          th2.start();
   }

}

class MyThread implements Runnable{
    @Override
    public synchronized void run() {
           for( int i=0;i<10;i++)
                 System. out.println(Thread. currentThread().getName()+" counter:"+i);
   }
}
