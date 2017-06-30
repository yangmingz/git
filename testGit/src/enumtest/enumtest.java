package enumtest;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

public class enumtest {
//	枚举是个对象，看成内部类那样
public enum location {
	UPLEFT,LOWLEFT, UPRIGHT, LOWRIGHT, CENTRE
}
public static void testenum(location location){
	switch(location){
	case UPLEFT:
		System.out.println(location);
		break;
	case LOWLEFT:
		System.out.println(location);
		break;
	default:
		System.out.println("no found ");
	}	
}
public static void main(String[] args) {
	testenum(location.CENTRE);
	testenum(location.LOWLEFT);
/*	testenum(enumentiy.location.LOWLEFT);
	testenum(enumentiy.location.CENTRE);*/
	
}
}
