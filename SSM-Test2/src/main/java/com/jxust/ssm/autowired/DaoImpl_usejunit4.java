package com.jxust.ssm.autowired;
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
//  使用JNUIT的加载配置文件方式
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath*:**/spring-mybatis.xml","classpath*:**/springmvc.xml"})  
public class DaoImpl_usejunit4 {  
    @Autowired  
    private DaoImpl daoimpl;  
    @Test  
    public void test() {  
        daoimpl.sayHello();  
    }  
}  