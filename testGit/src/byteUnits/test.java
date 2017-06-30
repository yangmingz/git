package byteUnits;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class test {
public static void main(String[] args) {
	Map<String,String> mp=new HashMap<String,String>();
	mp.put("A", "123");
	mp.put("B", "456");
	for(String s: mp.keySet())
	{
		System.out.println("Key:"+s+"\n"+"value:"+mp.get(s));
	}
}
}
