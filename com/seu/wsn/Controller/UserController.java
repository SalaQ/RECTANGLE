package com.seu.wsn.Controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.seu.wsn.Core.Pojo.User;
import com.seu.wsn.Core.StaticConst.WebConst;
import com.seu.wsn.Service.TestInfoService;
import com.seu.wsn.Service.UserService;

/**
 * 
 * @ClassName: UserController 
 * @Description: userController
 * @author: CSS
 * @date: 2016-10-23 上午11:25:32
 */
@Controller
public class UserController{
	@Autowired
	private UserService userService;
	@Autowired
	private TestInfoService testInfoService;
	/**
	 * 
	 * @Title: setTestInfoService 
	 * @Description: 注入testInfoService
	 * @param testInfoService
	 * @return: void
	 */
	public void setTestInfoService(TestInfoService testInfoService) {
		this.testInfoService = testInfoService;
	}

	/**
	 * 
	 * @Title: setUserService 
	 * @Description: 注入userService
	 * @param userService
	 * @return: void
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/wsnHome")
	public String wsnHome(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName){
		return  WebConst.INTRODUCTION;
	}
	
	@RequestMapping("/uwHome")
	public String uwHome(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName){
		return  WebConst.UWINTRODUCTION;
	}
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 用户登录请求
	 * @param req
	 * @param resp
	 * @param model
	 * @param userName
	 * @param pwd
	 * @return
	 * @return: String
	 */
	@RequestMapping("/login")
	public String login(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName,String pwd,String testName,String testType){
		
		User user = userService.select(userName);
		if(user!=null){ 
			if(userName.equals(user.getUserName())&&pwd.equals(user.getPwd())){
				req.getSession().setAttribute(WebConst.USERNAME, userName);
				req.getSession().setAttribute(WebConst.TESTNAME, testName);
				model.put(WebConst.START_CONN, "false");
				if (testType.equals("物联网感知层测试")) {
					return  WebConst.INTRODUCTION;
				} else if (testType.equals("水声通信网络测试")) {
					return  WebConst.UWINTRODUCTION;
				}		
			}
		}
		
		model.put(WebConst.ERROR_MSG, "nameOrpwd");
		return  WebConst.INDEX;
	}
	/**
	 * 
	 * @Title: register 
	 * @Description: 返回注册页面
	 * @param req
	 * @param resp
	 * @param model
	 * @param userName
	 * @param pwd
	 * @return
	 * @return: String
	 */
	@RequestMapping("/register")
	public String register(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName,String pwd){
		return  WebConst.USER_REGISTER;
	}
	/**
	 * 
	 * @Title: loginUser 
	 * @Description: 用户登录
	 * @param req
	 * @param resp
	 * @param model
	 * @param userName
	 * @param pwd
	 * @return
	 * @return: String
	 */
	@RequestMapping("/userLogin")
	public String loginUser(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName,String pwd){
		return  WebConst.INDEX;
	}
	/**
	 * 
	 * @Title: checkUnique 
	 * @Description: 判断用户名是否唯一
	 * @param req
	 * @param resp
	 * @param model
	 * @param userName
	 * @return: void
	 */
	@RequestMapping("/checkUnique")
	@ResponseBody
	public ModelMap checkUnique(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName){
		User user = userService.select(userName);
		if(user==null){
			model.put(WebConst.MESSAGE, "true");
		}else{
			model.put(WebConst.MESSAGE, "false");
		}
		return model;
	}
	/**
	 * 
	 * @Title: registerUser 
	 * @Description: 用户注册
	 * @param req
	 * @param resp
	 * @param model
	 * @param userName
	 * @param pwd
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/registerUser")
	@ResponseBody
	public ModelMap registerUser(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String userName,String pwd){
		userService.insert(userName,pwd);
		model.put(WebConst.MESSAGE, "true");
		return model;
	}

	/**
	 * 
	 * @Title: userList 
	 * @Description: 获取用户列表
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 * @return: String
	 */
	@RequestMapping("/list")
	public String userList(HttpServletRequest req,HttpServletResponse resp,ModelMap model){
		
		model.put("list", userService.userList());
		
		return WebConst.USER_LOGIN;
	}
	/**
	 * 
	 * @Title: deleteTestInfoList 
	 * @Description: 删除选定的测试名称（管理员用户使用）
	 * @param req
	 * @param resp
	 * @param model
	 * @param testIdList
	 * @return
	 * @return: ModelMap
	 */
	@RequestMapping("/deleteTestInfoList")
	@ResponseBody
	public ModelMap deleteTestInfoList(HttpServletRequest req,HttpServletResponse resp,ModelMap model,
			String testIdList){
		String[] list =  testIdList.split("&");
		for(int i=1;i<list.length;i++){
			testInfoService.deleteTestInfo(list[i]);
		}
		return model;
	}

}
