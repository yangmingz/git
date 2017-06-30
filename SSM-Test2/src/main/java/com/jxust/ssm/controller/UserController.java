package com.jxust.ssm.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jxust.ssm.pojo.User;
import com.jxust.ssm.service.UserService;
@Controller
//spring mvc 支持如下的返回方式：ModelAndView, Model, ModelMap, Map,View, String, void。
public class UserController {
private static Logger logger=Logger.getLogger(UserController.class);
	/**
	 * 使用@Autowired也可以，@Autowired默认按类型装配
	 * @Resource 默认按名称装配，当找不到与名称匹配的bean才会按类型装配。
	 */
	@Resource 
	private UserService userService;

	/**
	 * 测试查询
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/showUser")
	public String testtoshowUser(@RequestParam(value = "id") Integer id, Model model) {
		logger.debug("id is:"+id);
		System.out.println("id:" + id);
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "showUser";
	}

	/**
	 * 测试添加数据
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/insertUser")
	public String testinsertUser() {
		User user = new User();
		user.setUserName("李清照");
		user.setPassword("3232322");
		user.setAge(22);
		int count = userService.insert(user);
		System.out.println("插入" + count + "条数据成功");
		return "showUser";
	}
	@RequestMapping("/mydemo")
	public String testmydemo2() {
		return "mydemo2";
	}
//	新加这段代码找不到handles，请以下项目就可以了
	 @RequestMapping(value="getJosn.do", produces="text/html;charset=UTF-8")  
	 @ResponseBody  
	 public String getTabJson(HttpServletRequest request,HttpServletResponse response){
	     String json = "{\"无主题\":\"https://wuzhuti.cn\"}";  
	     return json;  
	 }
	    @RequestMapping(value="twoB.do")  
	    public void twoBCode(HttpServletRequest request,HttpServletResponse response) {  
	        //.......... 此处省略 N行  
	        try {  
	            response.setContentType("type=text/html;charset=UTF-8");  
	            String s = "一堆字符串......";  
	            response.getWriter().write(s);  
	        } catch (Exception e) {   
	            e.printStackTrace();  
	        }  
	        return;  
	    }  
//	添加注解解决返回string乱码问题
	@RequestMapping(value="/load",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String load(@RequestParam String name,@RequestParam String password) throws IOException{
		if(name.contains("11"))
			return "非法输入";
		if(password.contains("11"))
			return "非法输入";
        System.out.println(name+" : "+password);  
        //return name+" : "+password;
        MyDog dog=new MyDog();
        dog.setName("小哈");
        dog.setAge("1岁");
        dog.setColor("深灰");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(dog);
        System.out.println(jsonString);
        return jsonString;
    }
}
