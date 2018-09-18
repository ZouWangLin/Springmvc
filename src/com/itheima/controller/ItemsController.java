package com.itheima.controller;
//��Ʒ�������������

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
//���Ƶ�ǰ�������url��������items��ͷ��
@RequestMapping("items")
public class ItemsController {
	//����ע��
	@Autowired
	private IItemsService itemsService;
	//������ת��·��,�������ö����method���Ʒ��ʷ�ʽ
	@RequestMapping(value={"/itemList.action","/itemsList.action"},method=RequestMethod.GET)
	public ModelAndView itemList(){
		//1.����service��ȡ���еĶ�������
		List<Items> list = itemsService.findAll();
		//2.����ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		//3.�������ݵ�������ȥ
		modelAndView.addObject("itemList", list);
		//4.������ת��ҳ��
		modelAndView.setViewName("/WEB-INF/jsp/itemList.jsp");
		//5.���ظö���
		return modelAndView;
	}
	
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView itemtoEdit(Integer id){
		//1.����service����id���Ҷ�Ӧ��Items����
		Items items = itemsService.findById(id);
		//2.����ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		//3.����ֵ
		modelAndView.addObject("item", items);
		//4.������ת��ҳ��
		modelAndView.setViewName("/WEB-INF/jsp/editItem.jsp");
		//5.�������úõĶ���
		return modelAndView;
	}
	
	/*@RequestMapping("/items/itemUpdate.action")
	public ModelAndView itemUpdate(Items items){
		//1.����service�������û�
		itemsService.updateItem(items);
		//2.����ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		//3.������ת��ͼ
		modelAndView.setViewName("success");
		return modelAndView;
	}*/
	
	@RequestMapping(value="/itemUpdate.action")
	public ModelAndView itemUpdate(QueryVo qv){
		//1.����service�������û�
		itemsService.updateItem(qv.getItems());
		//2.����ModelAndView����
		ModelAndView modelAndView = new ModelAndView();
		//3.������ת��ͼ
		modelAndView.setViewName("/items/itemList.action");
		return modelAndView;
	}
	
	//��������
	@RequestMapping("/itemAdd.action")
	public String itemAdd(Items items,MultipartFile pictureFile,HttpServletRequest request) throws Exception{
		//����service ���б���
		//1.��ȡ�ļ���
		String filename = pictureFile.getOriginalFilename();
		//2.Ϊ�˷�ֹ�����ظ������ǽ������µ�����
		 String newName = UUID.randomUUID().toString();
		 newName=newName.replaceAll("-", "");
		//3.��ȡ�ļ�����׺
		 String extension = FilenameUtils.getExtension(filename);
		 //4.�ļ��ϴ�
		 pictureFile.transferTo(new File("D://�ϴ�·��/",newName+"."+extension));
		 items.setPic(newName+"."+extension);
		 items.setCreatetime(new Date());
		 itemsService.save(items);
		 //5.�ض������ת�ǵü�/
		 String url="redirect:/"+"items/itemList.action";
		return url;
		
	}
	
	//��������ɾ��(��ȡǰ̨���͹�������������)
	@RequestMapping("batchDelete.action")
	public String batchDelete(Integer[] id){
		return "items/itemList.action";
	}
	
	//���������޸�(��ȡǰ̨ǰ̨���͹��������ݷ�װ��List����)
	@RequestMapping(value={"batchUpdate.action"})
	public String batchUpdate(QueryVo queryVo){
		//��֤���
		List<Items> itemList = queryVo.getItemList();
		System.out.println(itemList);
		return null;
	}
	
	@RequestMapping(value="updateItem/{id}.action")
	public String updateItem(@PathVariable Integer id,Model model){
		/*
		 * @PathVariable Integer id
		 * 		��ȡ�����ϵ�idֵ
		 * */
		Items items = itemsService.findById(id);
		//����������
		model.addAttribute("item", items);
		return "/WEB-INF/jsp/editItem.jsp";
	}
	
	//����json���ݵ������
	@RequestMapping(value="showJson.action")
	public void writeJson(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setContentType("application/json;charset=utf-8");
		Items items = new Items();
		items.setName("ƻ��");
		items.setPrice(30f);
		response.getWriter().println(JSON.toJSONString(items));
	}
	
	//�����ض���
	@RequestMapping(value="toIndex.action")
	public String  toIndex(){
		return "redirect:/index.jsp";
	}
	
	//�����쳣
	@RequestMapping(value="testException.action")
	public String testException() throws MyException{
		if(null==null){
			throw new MyException("Ԥ���쳣!");
		}
		return null;
	}
	//��ȡjson���ݷ�װ�ɶ���
	@RequestMapping(value="jsonToList.action")
	public @ResponseBody
	Items jsonToList(@RequestBody Items items){
		return items;
		
	}
	
	//��¼����(ģ��)
	@RequestMapping(value="login.action")
	public String login(User user,HttpSession session){
		if(user.getUsername().equals("С��")){
			session.setAttribute("user", user);
			return "redirect:/items/itemList.action";
		}
		return "redirect:/login.jsp";
		
	}
}
