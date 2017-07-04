package androidCallBack;


public class You implements Runnable{ 
private String who; 
private ContactInterface callBack;  
public You() { 
   // TODO Auto-generated constructor stub 
} 
  
//调用此方法就表示有人联系你了，注册到你这来 
public void setCallBack(String who,String question,ContactInterface callBack) { 
  this.who = who;   
  System.out.println("你说：当前联系到我的人是"+who+"，问题是"+question); 
  this.callBack =callBack; 
} 
   
public void handleThings(){ 
  //假如你现在正在想问题的答案，需要一点时间 
  for(int i=0;i<100000;i++){  
     if(i == 0){ 
       System.out.println("你正在思考问题....."); 
     } 
  } 
    
  String answer = "答案是A"; 
  //想到问题的办法了 
  System.out.println("你说：想到答案了，准备打回去给"+who+"告诉他答案"); 
//  接口约定的返回方式，通过这个调用方和被调用方通讯
  callBack.callBackByTel(answer); 
} 

/* (non-Javadoc) 
* @see java.lang.Runnable#run() 
*/
@Override
public void run() { 
 try { 
   Thread.sleep(1000); 
   handleThings(); 
 } catch (Exception e) { 
   e.printStackTrace(); 
 } 
}  
} 