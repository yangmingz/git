package androidCallBack;

public class Me { 
	   
	   public static void main(String[] args){  
	       
	     Me me = new Me(); 
	     me.hasQuestion(); 
	       
	   } 
	     
	   private void hasQuestion(){ 
	     //现在有问题想不出来答案，想去问你 
	     You you = new You(); 
//	     调用YOu并传入参数注册，相当于传入回调指针，同时一个匿名内部类实现接口并提供回调函数
	     you.setCallBack("蜗牛", "某道题答案是什么？", new ContactInterface() { 
	         
	      @Override
	      public void callBackByTel(String answer) { 
	        System.out.println("我说：嗯，好的，我收到答案了:"+answer+"，谢谢"); 
	           
	      } 
	    }); 
	    //你接到电话，起床开始思考问题 
	    new Thread(you).start(); 
	   } 
	} 