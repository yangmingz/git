package tools;

import java.io.File;
import java.io.IOException;

public class getPath {
public static String getPath(Object ob) {
	String path=null;
	try {
		path = new File("./").getCanonicalPath() + "/src/main/resources/data/excel/"
				+ ob.getClass().getName().replaceAll("\\.", "/") + ".xls";
		System.err.println("当前数据驱动路径为： " + path);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return path;
}
}
