package testLinkConver;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tools.ExcelProvider4Test;
import tools.getPath;

public class testlink {
	static String testname="拉卡拉驱动跟新";
	StringBuffer sl=new StringBuffer("");
	public static final String headstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "\n" + "<testsuite name=\"\">"
			+ "\n" + "<node_order><![CDATA[]]></node_order>" + "\n" + "<details><![CDATA[]]>" + "\n"
			+ "</details><testsuite name=\""+testname+"\">" + "\n" + "<node_order><![CDATA[0]]></node_order>" + "\n"
			+ "<details><![CDATA[]]>" + "\n" + "</details>"+"\n";

	public static String convert2xml(xmlModel data) {
		String testcase = "<testcase internalid=\"" + data.getInternalid().trim() + "\"" + " name=\""
				+ data.getName().trim() + "\"" + ">" + "\n" + "<node_order><![CDATA[" + data.getNode_order().trim()
				+ "]]></node_order>" + "\n" + "<externalid><![CDATA[" + data.getExternalid().trim()
				+ "]]></externalid>" + "\n" + "<version><![CDATA[" + data.getVersion().trim() + "]]></version>" + "\n"
				// &nbsp; summary,actions,expectedresults，preconditions必填
				+ "<summary><![CDATA[<p>&nbsp;" + data.getSummary().trim() + "</p>]]></summary>" + "\n"
				+ "<preconditions><![CDATA[<p>&nbsp;" + data.getPreconditions().trim() + "</p>]]></preconditions>"
				+ "\n"

				+ "<execution_type><![CDATA[" + data.getExecution_type().trim() + "]]></execution_type>" + "\n"
				+ "<importance><![CDATA[" + data.getImportance().trim() + "]]></importance>" + "\n" + "<steps>" + "\n"
				+ "<step>" + "\n" + "<step_number><![CDATA[" + data.getStep_number().trim() + "]]></step_number>"
				+ "\n"
				// &nbsp;
				+ "<actions><![CDATA[<p>&nbsp;" + data.getActions().trim() + "</p>]]></actions>" + "\n"
				+ "<expectedresults><![CDATA[<p>&nbsp;" + data.getExpectedresults().trim()
				+ "</p>]]></expectedresults>" + "\n"

				+ "<execution_type><![CDATA[" + data.getExecution_type().trim() + "]]></execution_type>" + "\n"
				+ "</step>" + "\n" + "</steps>" + "\n" + "</testcase>";
		return testcase;
	}

	@Test(alwaysRun = true, dataProvider = "testlink", enabled = true, timeOut = 60000)
	public void testLinkconvert(Map<String, String> data) {
		xmlModel xm=new xmlModel();
		xm.setInternalid(data.get("internalid"));
		xm.setName(data.get("name"));
		xm.setNode_order(data.get("node_order"));
		xm.setExternalid(data.get("externalid"));
		xm.setVersion(data.get("version"));
		xm.setSummary(data.get("summary"));
		xm.setPreconditions(data.get("preconditions"));
		xm.setExecution_type(data.get("execution_type"));
		xm.setImportance(data.get("importance"));
		xm.setStep_number(data.get("step_number"));
		xm.setActions(data.get("actions"));
		xm.setExpectedresults(data.get("expectedresults"));
		String body = convert2xml(xm);
		sl.append(body);
//		System.out.println(sl.toString());
		
	}

	@DataProvider(name = "testlink", parallel = false)
	public Iterator<Object[]> data4testngDatadriven() throws IOException {
		return new ExcelProvider4Test(this, "testlink");
	}

	// 累初始化
	@BeforeClass
	public void beforeClass() {
		sl.append(headstr);
	}
	@AfterClass
	public void afterClass() {
		sl.append("\n" + "</testsuite></testsuite>");
		System.err.println(sl.toString());
	}
}
