package com.jxust.ssm.autowired;
import org.testng.annotations.Test;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;  
//继承AbstractTestNGSpringContextTests,来启动SpringContext,方便其他的测试类集成BaseTest使用
@ContextConfiguration(locations={"classpath*:**/spring-mybatis.xml","classpath*:**/springmvc.xml"})
//@ContextConfiguration(locations={"classpath*:**/springmvc.xml"})

public class DaoImpl_usetestng extends AbstractTestNGSpringContextTests {  
    @Autowired  
    private DaoImpl daoimpl;  
    @Test  
    public void test() {  
        daoimpl.sayHello();  
    }  
}  