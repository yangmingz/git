package testGit;

public class TestThread {
    public static void main(String[] args) {  
        MyThread m1 = new MyThread(1);  
        MyThread m2 = new MyThread(2);  
        m1.start();  
        m2.start();  
    }  
}  
  
final class MyThread extends Thread {  
    private int val;  
  
    public MyThread(int v) {  
        val = v;  
    }  
    //这种做法其实是非线程安全的  
    public synchronized void print1(int v) {  
        for (int i = 0; i < 100; i++) {  
            System.out.print(v);  
        }  
    }  
  
    public void print2(int v) {  
        //线程安全  
        synchronized (MyThread.class) {  
            for (int i = 0; i < 100; i++) {  
                System.out.print(v);  
            }  
        }  
    }  
    
    class tclass implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(this.getClass());
			
		}}
  
    public void run() {  
        print1(val);  
        // print2(val);  
    }  
}  
