package appTest;

public class threadtest implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("hh");
	}
public static void main(String[] args) {
	threadtest ts=new threadtest();
	Thread t1=new Thread(ts);
	Thread t2=new Thread(ts);
	t1.start();
	t2.start();
	System.out.println(t1.getName());
	System.out.println(t2.getName());
	
}
}
