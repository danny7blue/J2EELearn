package bean;

public class ThreadSynchronizedTest {
    public static void main(String[] args) {
           final MyTask myTask = new MyTask();
          Thread thread1 = new Thread( new Runnable() {
                  public void run() {
                       myTask.doTask1();
                 }
          });
          Thread thread2 = new Thread( new Runnable() {
                  public void run() {
                       myTask.doTask2();
                 }
          });
          thread1.start();
          thread2.start();
   }
}

class MyTask{
    public synchronized void doTask1() {
           for ( int i = 0; i < 5; i++) {
                 System. out.println( "1 This is real Tasking "+i);
          }
   }
    public void doTask2() {
           for ( int i = 0; i < 5; i++) {
                 System. out.println( "2 This is real Tasking "+i);
          }
   }
}
