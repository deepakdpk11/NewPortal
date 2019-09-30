package com.MCPortal.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.MCPortal.Model.BatchInfo;
import com.MCPortal.dao.MCDAO;

@Controller
public class PortalController {
	 
	@Autowired
	MCDAO dao;
	 
	 @Autowired
	 private SessionFactory sessionfactory;
	 
	@RequestMapping("/")
	ModelAndView index()
	{
		System.out.println("Inside index");
		return new ModelAndView("login");
	}
	 
	@RequestMapping("/login")
	ModelAndView Login( HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		System.out.println("Inside Login with username" + username );
		session.setAttribute("Username", username);
		return new ModelAndView("home");
	}
	
	@RequestMapping("/home")
	ModelAndView home()
	{
		System.out.println("Inside home");
		return new ModelAndView("home");
	}
	
	@RequestMapping("/attendance_management")
	ModelAndView attendance_management()
	{
		System.out.println("Inside attendance_management");
		return new ModelAndView("attendance_sheet");
	}
	
	@RequestMapping("/fee_management")
	ModelAndView fee_management()
	{
		System.out.println("Inside fee_management");
		return new ModelAndView("");
	}
	
	@RequestMapping("/student_management")
	ModelAndView student_management()
	{
		System.out.println("Inside student_management");
		return new ModelAndView("student_sheet");
	}
	
	@RequestMapping("/batch_management")
	ModelAndView batch_management( HttpServletRequest request  )
	{
		ModelAndView modelview = new ModelAndView("batch_sheet");
		modelview.addObject("userrole", "user");
		System.out.println("Inside batch_management");
		Session hbsession = null;
		Transaction txn = null;
		List<BatchInfo> list = new ArrayList<BatchInfo>();
		HttpSession session = request.getSession();
		try {
			hbsession = sessionfactory.openSession();
			list = dao.getBatchList(hbsession);
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			//mv print the error page
		}
		finally
		{
			if( hbsession != null )
			{
				hbsession.close();
			}
		}
	 	modelview.addObject("batchdata",list);
		return modelview;
	}
	
	@RequestMapping("/go_add_batch")
	ModelAndView go_add_batch(HttpServletRequest request )
	{
		HttpSession session = request.getSession();
		String value=(String)session.getAttribute("Username"); 
		System.out.println("username is "+ value );
		ModelAndView modelview = new ModelAndView("add_batch");
		return modelview;
	}
	
	@RequestMapping("/add_batch")
	String add_batch(HttpServletRequest request )
	{
		Session hbsession = null;
		Transaction txn = null;
		try {
			HttpSession session = request.getSession();
			String value=(String)session.getAttribute("Username"); 
			System.out.println("username is "+ value );
			System.out.println("batch_data----->" + request.getParameter("Batch_Name"));
			System.out.println("batch_data----->" + request.getParameter("Batch_Time"));
			System.out.println("batch_data----->" + request.getParameter("Batch_Days"));
			System.out.println("batch_data----->" + request.getParameter("Batch_Fee_Amt"));
			System.out.println("batch_data----->" + request.getParameter("Batch_status"));
			BatchInfo batch_data = new BatchInfo();
			batch_data.setBatch_name(request.getParameter("Batch_Name"));
			batch_data.setBatch_time(request.getParameter("Batch_Time"));
			batch_data.setBatch_days(request.getParameter("Batch_Days"));
			batch_data.setBatch_fee_Amt(request.getParameter("Batch_Fee_Amt"));
			batch_data.setBatch_status(request.getParameter("Batch_status"));
			hbsession = sessionfactory.openSession();
			txn =  hbsession.beginTransaction();
			hbsession.save(batch_data);
			txn.commit();
		}catch( Exception ex )
		{
			ex.printStackTrace();
			txn.rollback();
			//return the error page
		}
		finally
		{
			if( hbsession != null )
			{
				hbsession.close();
			}
		}
		return "redirect:/batch_management";
	}
	
	@RequestMapping("/editbatch/{id}")
	String edit_batch( @PathVariable(name = "id")String id, HttpServletRequest request, ModelAndView mv )
	{
		
		List<BatchInfo> list = new ArrayList<BatchInfo>();
		Session hbsession = null;
		try {
			
			HttpSession session = request.getSession();
			//String id = request.getParameter("Id");
			hbsession = sessionfactory.openSession();
			System.out.println("id is "+ id );
			list = dao.getBatchListById(id, hbsession);
			//modelview.addObject("batchdata",list);
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			//return the error page
		}
		finally
		{
			if( hbsession != null )
			{
				hbsession.close();
			}
			System.out.println("Finally executed");
		}
		mv.setViewName("add_batch");
		ModelAndView modelview = new ModelAndView("add_batch");
		return "redirect:/edit_batch";
	}
	
	@RequestMapping("/deletebatch/{id}")
	String delete_batch( @PathVariable(name = "id")String id, HttpServletRequest request, ModelAndView mv )
	{
		Session hbsession = null;
		Transaction txn = null;
		List<BatchInfo> list = new ArrayList<BatchInfo>();
		try {
			//String id = request.getParameter("Id");
			hbsession = sessionfactory.openSession();
			System.out.println("id is "+ id );
		    dao.DeleteById(id, hbsession);
			//modelview.addObject("batchdata",list);
		}
		catch( Exception ex )
		{
			ex.printStackTrace();
			//return the error page
		}
		finally
		{
			if( hbsession != null )
			{
				hbsession.close();
			}
			System.out.println("Finally executed");
		}
		return "redirect:/batch_management";
	}
	
}
