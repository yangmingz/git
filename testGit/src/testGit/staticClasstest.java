package testGit;

public class staticClasstest {
private static staticClasstest testclass=new staticClasstest();
private static staticClasstest newinstence() {
	
	return testclass;
}
public static void main(String[] args) {
	staticClasstest st=new staticClasstest();
			Class c=st.newinstence().getClass();
			System.out.println(c.getClassLoader());
}
}
/*//懒汉式单例类.在第一次调用的时候实例化自己   
public class Singleton {  
  private Singleton() {}  
  private static Singleton single=null;  
  //静态工厂方法   
  public static Singleton getInstance() {  
       if (single == null) {    
           single = new Singleton();  
       }    
      return single;  
  }  
} */

//静态内部类
/*public class Singleton {    
    private static class LazyHolder {    
       private static final Singleton INSTANCE = new Singleton();    
    }    
    private Singleton (){}    
    public static final Singleton getInstance() {    
       return LazyHolder.INSTANCE;    
    }    
}  */
//饿汉式单例类.在类初始化时，已经自行实例化   
/*public class Singleton1 {  
  private Singleton1() {}  
  private static final Singleton1 single = new Singleton1();  
  //静态工厂方法   
  public static Singleton1 getInstance() {  
      return single;  
  }  
} */