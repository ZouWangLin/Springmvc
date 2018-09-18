package com.itheima.controller;
//商品订单管理控制器

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.itheima.entity.Items;
import com.itheima.entity.QueryVo;
import com.itheima.entity.User;
import com.itheima.exception.MyException;
import com.itheima.service.IItemsService;
@Controller
//限制当前类的所有url，都是以items开头的
@RequestMapping("items")
public class ItemsController {
	//属性注入
	@Autowired
	private IItemsService itemsService;
	//配置跳转的路径,可以配置多个，method限制访问方式
	@RequestMapping(value={"/itemList.action","/itemsList.action"},method=RequestMethod.GET)
	public ModelAndView itemList(){
		//1.调用service获取所有的订单对象
		List<Items> list = itemsService.findAll();
		//2.创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		//3.设置数据到对象中去
		modelAndView.addObject("itemList", list);
		//4.设置跳转的页面
		modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
		//5.返回该对象
		return modelAndView;
	}
	
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView itemtoEdit(Integer id){
		//1.调用service根据id查找对应的Items对象
		Items items = itemsService.findById(id);
		//2.创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		//3.设置值
		modelAndView.addObject("item", items);
		//4.设置跳转的页面
		modelAndView.setViewName("/WEB-INF/jsp/editItem.jsp");
		//5.返回设置好的对象
		return modelAndView;
	}
	
	/*@RequestMapping("/items/itemUpdate.action")
	public ModelAndView itemUpdate(Items items){
		//1.调用service，根据用户
		itemsService.updateItem(items);
		//2.创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		//3.设置跳转视图
		modelAndView.setViewName("success");
		return modelAndView;
	}*/
	
	@RequestMapping(value="/itemUpdate.action")
	public ModelAndView itemUpdate(QueryVo qv){
		//1.调用service，根据用户
		itemsService.updateItem(qv.getItems());
		//2.创建ModelAndView对象
		ModelAndView modelAndView = new ModelAndView();
		//3.设置跳转视图
		modelAndView.setViewName("/items/itemList.action");
		return modelAndView;
	}
	
	//增加数据
	@RequestMapping("/itemAdd.action")
	public String itemAdd(Items items,MultipartFile pictureFile,HttpServletRequest request) throws Exception{
		//调用service 进行保存
		//1.获取文件名
		String filename = pictureFile.getOriginalFilename();
		//2.为了防止名字重复，我们进行重新的命名
		 String newName = UUID.randomUUID().toString();
		 newName=newName.replaceAll("-", "");
		//3.获取文件名后缀
		 String extension = FilenameUtils.getExtension(filename);
		 //4.文件上传
		 pictureFile.transferTo(new File("D://上传路径/",newName+"."+extension));
		 items.setPic(newName+"."+extension);
		 items.setCreatetime(new Date());
		 itemsService.save(items);
		 //5.重定向的跳转记得加/
		 String url="redirect:/"+"items/itemList.action";
		return url;
		
	}
	
	//进行批量删除(获取前台传送过来的数组数据)
	@RequestMapping("batchDelete.action")
	public String batchDelete(Integer[] id){
		return "items/itemList.action";
	}
	
	//进行批量修改(获取前台前台传送过来的数据封装成List集合)
	@RequestMapping(value={"batchUpdate.action"})
	public String batchUpdate(QueryVo queryVo){
		//验证结果
		List<Items> itemList = queryVo.getItemList();
		System.out.println(itemList);
		return null;
	}
	
	@RequestMapping(value="updateItem/{id}.action")
	public String updateItem(@PathVariable Integer id,Model model){
		/*
		 * @PathVariable Integer id
		 * 		获取参数上的id值
		 * */
		Items items = itemsService.findById(id);
		//设置域数据
		model.addAttribute("item", items);
		return "/WEB-INF/jsp/editItem.jsp";
	}
	
	//返回json数据到浏览器
	@RequestMapping(value="showJson.action")
	public void writeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=utf-8");
		Items items = new Items();
		items.setName("苹果");
		items.setPrice(30f);
		response.getWriter().println(JSON.toJSONString(items));
	}
	
	//测试重定向
	@RequestMapping(value="toIndex.action")
	public String  toIndex(){
		return "redirect:/index.jsp";
	}
	
	//测试异常
	@RequestMapping(value="testException.action")
	public String testException() throws MyException{
		if(null==null){
			throw new MyException("预期异常!");
		}
		return null;
	}
	//获取json数据封装成对象
	@RequestMapping(value="jsonToList.action")
	public @ResponseBody
	Items jsonToList(@RequestBody Items items){
		return items;
		
	}
	
	//登录方法(模拟)
	@RequestMapping(value="login.action")
	public String login(User user,HttpSession session){
		if(user.getUsername().equals("小明")){
			session.setAttribute("user", user);
			return "redirect:/items/itemList.action";
		}
		return "redirect:/login.jsp";
		
	}
}
