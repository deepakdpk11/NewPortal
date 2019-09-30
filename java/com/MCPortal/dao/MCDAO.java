package com.MCPortal.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.MCPortal.Model.BatchInfo;

import org.hibernate.Transaction;

@SuppressWarnings("unchecked")
@Service
@Repository
public class MCDAO {

 @Autowired
 private SessionFactory sessionFactory;
 
 public void addBatchdata(BatchInfo batch_data) {
        // TODO Auto-generated method stub
    Session session = sessionFactory.getCurrentSession();
    session.save(batch_data);
    System.out.println("session saved....");
    //getSession().save(batch_data);
}

//public void create(BatchInfo batch_data){
//	//String.query = "INSERT INTO person (id, first_name, last_name) VALUES (" + batch_data.getBatch_name() + "," + batch_data.getBatch_fee_Amt()+ "," +  batch_data.getBatch_days()+ "," + batch_data.getBatch_status());
//    String query ="INSERT INTO `testdb`.`batchinfo` (`id`, `batch_name`, `batch_time`, `batch_days`, `batch_fee_Amt`, `batch_status`) VALUES ('3', 'Kids Batch1', '10-11', 'ALL days', '1000', 'active');";
//	hbsession.createQuery(query);
//	System.out.println("session saved....");
//	return;
//}
 
public List<BatchInfo> getBatchListById(String batchid, Session hbsession){
	String query =" from BatchInfo where Id='"+batchid+"'";
	List<BatchInfo> batchList = (List<BatchInfo>)hbsession.createQuery(query).list();
	return batchList;
}

public List<BatchInfo> getBatchList(Session hbsession){
	String query =" from BatchInfo";
	List<BatchInfo> batchList = (List<BatchInfo>)hbsession.createQuery(query).list();
	return batchList;
}

public void DeleteById(String batchid, Session hbsession){
	String query ="Delete from BatchInfo where Id='"+batchid+"'";
	Transaction txn = null; 
	txn =  hbsession.beginTransaction();
	hbsession.createQuery(query).executeUpdate();
	txn.commit();
	System.out.println("Delete query executed");
	return;
}


	/*
	 * public List<Employee> getEmpByTeam(String team, Session hbsession){ String
	 * query =" from Employee where team='"+team+"'"; List<Employee> empList =
	 * (List<Employee>)hbsession.createQuery(query).list(); return empList; }
	 * 
	 * public List<Admin> getAdminUser(String username, Session hbsession){ String
	 * query =" from Admin where upper(username)=upper('"+username+"')"; List<Admin>
	 * empList = (List<Admin>)hbsession.createQuery(query).list(); return empList; }
	 * 
	 * 
	 * 
	 * public List<Engineer> getEngineerDetails(String addCond, String frmdate,
	 * String todate, Session hbsession){ String cond = ""; if(addCond !=null &&
	 * !addCond.isEmpty()) { cond = " and addedBy in("+addCond+")"; } String query
	 * =" from Engineer where date between date('"+frmdate
	 * +"') and date('"+frmdate+"') "+cond; List<Engineer> list =
	 * (List<Engineer>)hbsession.createQuery(query).list(); return list; }
	 * 
	 * public List<Conveyance> getConveyanceDetails(String addCond, String frmdate,
	 * String todate, Session hbsession){ String cond = ""; if(addCond !=null &&
	 * !addCond.isEmpty()) { cond = " and addedBy in("+addCond+")"; } String query
	 * =" from Conveyance where conveyDate between date('"+frmdate
	 * +"') and date('"+frmdate+"') "+cond; List<Conveyance> list =
	 * (List<Conveyance>)hbsession.createQuery(query).list(); return list; }
	 * 
	 * 
	 * public String getEmpTeam(String empid, Session hbsession) { String query
	 * =" select team from Employee where empId='"+empid+"'"; String name =
	 * (String)hbsession.createQuery(query).uniqueResult(); return name; }
	 * 
	 * 
	 * public String getEmployeename(String empid, Session hbsession) { String query
	 * =" select empName from Employee where empId='"+empid+"'"; String name =
	 * (String)hbsession.createQuery(query).uniqueResult(); return name; }
	 * 
	 * public List<Team> getTeamList(String teamId, String teamName, Session
	 * hbsession) { String cond = ""; if(teamId!=null && !teamId.isEmpty()) { cond =
	 * " and teamId='"+teamId+"'"; } if(teamName!=null && !teamName.isEmpty()) {
	 * cond += " and upper(teamName)= upper('"+teamName+"')"; } String query
	 * =" from Team where status='1' "+cond; List<Team> list =
	 * (List<Team>)hbsession.createQuery(query).list(); return list; }
	 * 
	 * public int getTeamSequance(Session hbsession) { String query
	 * =" SELECT max(TEAM_ID) FROM team"; Integer value =
	 * (Integer)hbsession.createSQLQuery(query).uniqueResult(); return
	 * value.intValue()+1; }
	 * 
	 * 
	 * public String getTeamName(String teamId, Session hbsession) { String query
	 * =" select teamName from Team where teamId='"+teamId+"'"; String name =
	 * (String)hbsession.createQuery(query).uniqueResult(); name = (name==null ||
	 * name.isEmpty())?teamId:name; return name; }
	 * 
	 * public String getDeptName(String departmentId, Session hbsession) { String
	 * query
	 * =" select departmentName from Department where departmentId='"+departmentId+
	 * "'"; String name = (String)hbsession.createQuery(query).uniqueResult(); name
	 * = (name==null || name.isEmpty())?departmentId:name; return name; } public
	 * List<Department> getDepartmentList(String deptId, String deptName, Session
	 * hbsession) { String cond = ""; if(deptId!=null && !deptId.isEmpty()) { cond =
	 * " and departmentId='"+deptId+"'"; } if(deptName!=null && !deptName.isEmpty())
	 * { cond += " and upper(departmentName)= upper('"+deptName+"')"; }
	 * 
	 * String query =" from Department where status='1' "+cond; List<Department>
	 * list = (List<Department>)hbsession.createQuery(query).list(); return list; }
	 * 
	 * public List<Employee> getPendingApprovalList(Session hbsession) { String
	 * query =" from Employee where status='P'"; List<Employee> list =
	 * (List<Employee>)hbsession.createQuery(query).list(); return list; }
	 */
}


