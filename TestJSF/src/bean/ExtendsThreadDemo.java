package bean;

class TestThread extends Thread{  
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
  
public class ExtendsThreadDemo{  
    public static void main(String[] args){  
        new TestThread().start();  
        new TestThread().start();  
        new TestThread().start();  
    }  
}
