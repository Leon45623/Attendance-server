package com.scb.service.impl;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.attachment.ContentDisposition;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;
import org.apache.poi.openxml4j.opc.internal.FileHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.scb.dao.RecordDao;
import com.scb.dao.ResultDao;
import com.scb.dao.EmployeeDao;
import com.scb.dao.SeatDao;
import com.scb.po.Record;
import com.scb.po.Result;
import com.scb.po.Employee;
import com.scb.po.PageBean;
import com.scb.po.Seat;

import com.scb.service.UserService;
import com.scb.vo.AcceptOneVo;
import com.scb.vo.EmployeeVo;
import com.scb.vo.PageBeanVo;
import com.scb.vo.RecordVo;





@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SeatDao seatDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private ResultDao resultDao;
	
	
	private String getIp()    {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			String ip=addr.getHostAddress();
			System.out.println("ipwei"+ip);
			String abc="http://"+ip+":8080";
			return abc;
	}
	
	
	






	public EmployeeVo login(Employee employee) {
	
		if(employeeDao.getEmployee(employee)!=null){
			EmployeeVo employeeVo=new EmployeeVo();
			Employee e1=employeeDao.getEmployee(employee);			
			employeeVo.setName(e1.getName());
			employeeVo.setPhone(e1.getPhone());
			employeeVo.setPosition(e1.getPosition());
			employeeVo.setPwId(e1.getPwId());
			employeeVo.setSuperId(	e1.getSuperId());
			employeeVo.setHeadportrait(this.getIp()+e1.getHeadportrait());
			return employeeVo;
		}else {
			return null;
		}	
	}









	public AcceptOneVo register(Employee employee) {
		// TODO Auto-generated method stub
   AcceptOneVo acceptOneVo=new AcceptOneVo();
   employee.setHeadportrait("/Attendance/images/defaultuser.jpg");
	if( employeeDao.addEmployee(employee)!=null){
		Employee e1=employeeDao.addEmployee(employee);
		int pwId=e1.getPwId();
		acceptOneVo.setMsg(String.valueOf(pwId));
		return acceptOneVo;
	}else{
		return null;
	}
}









	public Employee registerDeatil(Employee employee) {
		// TODO Auto-generated method stub
		String msg=employeeDao.updateEmployee(employee);
		if(msg=="success"){
			
			return employee;
			
		}else{
			return null;
		}
	
	}









	public List<Employee> findAllEmployee() {
		// TODO Auto-generated method stub
		if(employeeDao.findAllEmployee()!=null){
		List<Employee>employees=employeeDao.findAllEmployee();
		return employees;
		}else{
		return null;
		}
	}










	public AcceptOneVo checkin(Seat seat) {
		// TODO Auto-generated method stub
		AcceptOneVo acceptOneVo=new AcceptOneVo();
		Seat s3=seatDao.findSeatById(seat.getSeatId());
		if(s3.getPwId()!=0){
			
			acceptOneVo.setMsg("check");
		}else{
			
			
			
			if(seatDao.updateSeat(seat)!=null){		
				String	msg=seatDao.updateSeat(seat);	
				Date date=new Date();
				SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
				String d1=dataFormater.format(date);
				try {
					Date d2=dataFormater.parse(d1);
					Record record=new Record();
					record.setPwId(seat.getPwId());
					record.setDate(d2);
				record.setBeginTime(date);
				System.out.println(seatDao.findSeatById(seat.getSeatId()).getLocation()+"zhelishi:");
				record.setLocation(seatDao.findSeatById(seat.getSeatId()).getLocation());
			String m=	recordDao.checkin(record);
				acceptOneVo.setMsg(m);
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				};
			
		}
	
		
		
		return acceptOneVo;
	}









	public AcceptOneVo checkout(Seat seat) {
		// TODO Auto-generated method stub
		AcceptOneVo acceptOneVo=new AcceptOneVo();
		if(seatDao.findSeatBypwId(seat.getPwId())!=null){
			Seat s1=seatDao.findSeatBypwId(seat.getPwId());
			Seat s2=new Seat();
			s2.setArea(s1.getArea());
			s2.setIsUsed(0);
			s2.setSeatId(s1.getSeatId());
			s2.setLocation(s1.getLocation());
			s2.setPwId(0);
			String msg=seatDao.updateSeat(s2);
			if(msg=="success"){
				
				 
				try {
					Record record=new Record();
					record.setPwId(seat.getPwId());
					
					record.setEndTime(new Date());
					Date date=new Date();
					SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
					String d1=dataFormater.format(date);
					Date	d2 = dataFormater.parse(d1);		
					record.setDate(d2);
					String m=recordDao.checkout(record);
					acceptOneVo.setMsg(m);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else{
				
				acceptOneVo.setMsg("error");
			}

		}else{
			acceptOneVo.setMsg("none");
			
		}
		
		
		return acceptOneVo;
	}










	public List<EmployeeVo> findEmployeelist(Employee employee) {
		// TODO Auto-generated method stub
		
		int pwId=employee.getPwId();
		String superId=String.valueOf(pwId);
		List<EmployeeVo>employeeVos=new ArrayList<EmployeeVo>();
		if(employeeDao.findEmployeeById(superId)!=null){
			
		List<Employee>	employees=employeeDao.findEmployeeById(superId);
			Iterator<Employee>iterator=employees.iterator();
			while(iterator.hasNext()){
				EmployeeVo employeeVo=new EmployeeVo();
				Employee e2=iterator.next();
			 employeeVo.setName(e2.getName());
		        employeeVo.setPhone(e2.getPhone());
		      employeeVo.setPosition(e2.getPosition());
		        employeeVo.setSuperId(e2.getSuperId());
		        employeeVo.setPwId(e2.getPwId());
		        employeeVo.setHeadportrait(this.getIp()+e2.getHeadportrait());
			        if(seatDao.findSeatBypwId(e2.getPwId())!=null){    	 
			        	Seat s1=seatDao.findSeatBypwId(e2.getPwId());
			          employeeVo.setArea(s1.getArea());
			            employeeVo.setIsUsed(s1.getIsUsed());
			            employeeVo.setLocation(s1.getLocation());
			            employeeVo.setSeatId(s1.getSeatId());
			        }
			  
			        employeeVos.add(employeeVo);
			}
			
		}
		return employeeVos;
	}









	public List<Seat> findAllseat() {
		// TODO Auto-generated method stub
		
		List<Seat>seats=seatDao.findAllSeat();
		
		return seats;
	}








	public EmployeeVo findSeatById(Seat seat) {
		// TODO Auto-generated method stub
		EmployeeVo employeeVo =new EmployeeVo();
		Seat s1=seatDao.findSeatById(seat.getSeatId());
        employeeVo.setArea(s1.getArea());
        employeeVo.setIsUsed(s1.getIsUsed());
        employeeVo.setLocation(s1.getLocation());
        employeeVo.setSeatId(s1.getSeatId());
        employeeVo.setPwId(s1.getPwId());
      
        if(s1.getPwId()!=0){
        	if(employeeDao.findEmployeeBypwId(s1.getPwId())!=null){
       Employee employee= employeeDao.findEmployeeBypwId(s1.getPwId());
        employeeVo.setName(employee.getName());
        employeeVo.setPhone(employee.getPhone());
        employeeVo.setPosition(employee.getPosition());
        employeeVo.setSuperId(employee.getSuperId());
        employeeVo.setHeadportrait(this.getIp()+employee.getHeadportrait());
        	}
        }
		return employeeVo;
	}









	public List<EmployeeVo> search(AcceptOneVo acceptOneVo) {
		// TODO Auto-generated method stub
		String msg=acceptOneVo.getMsg();
		List<EmployeeVo>employeeVos=new ArrayList<EmployeeVo>();
		if(employeeDao.search(msg)!=null){
		List<Employee>employees=employeeDao.search(msg);
		
		Iterator<Employee>iterator=employees.iterator();
		while(iterator.hasNext()){
			EmployeeVo employeeVo=new EmployeeVo();
			Employee e2=iterator.next();
		 employeeVo.setName(e2.getName());
	        employeeVo.setPhone(e2.getPhone());
	      employeeVo.setPosition(e2.getPosition());
	        employeeVo.setSuperId(e2.getSuperId());
	        employeeVo.setPwId(e2.getPwId());
	        employeeVo.setHeadportrait(this.getIp()+e2.getHeadportrait());
		        if(seatDao.findSeatBypwId(e2.getPwId())!=null){    	 
		        	Seat s1=seatDao.findSeatBypwId(e2.getPwId());
		          employeeVo.setArea(s1.getArea());
		            employeeVo.setIsUsed(s1.getIsUsed());
		            employeeVo.setLocation(s1.getLocation());
		            employeeVo.setSeatId(s1.getSeatId());
		        }
		  
		        employeeVos.add(employeeVo);
		}
		
		}
		return employeeVos;
	}









	public RecordVo findmysheet(Record record) throws Exception {
		// TODO Auto-generated method stub
		Date date=record.getDate();
		SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat beginFormater=new SimpleDateFormat("HH:mm");
		String d1=dataFormater.format(date);
		int pwId=record.getPwId();
		RecordVo recordVo=new RecordVo();
		if(recordDao.findRecordByDate(d1, pwId)!=null){
			Record r1=recordDao.findRecordByDate(d1, pwId);
			recordVo.setId(r1.getId());
			recordVo.setPwId(r1.getPwId());
			if(r1.getDate()!=null){
			recordVo.setDate(dataFormater.format(r1.getDate()));
			Result result=resultDao.findResultByIdAndDate(d1, pwId);
			recordVo.setResult(result.getResult());
			}
			if(r1.getBeginTime()!=null){
			recordVo.setBeginTime(beginFormater.format(r1.getBeginTime()));
			}
			if(r1.getEndTime()!=null){
			recordVo.setEndTime(beginFormater.format(r1.getEndTime()));
			}
		   recordVo.setLocation(r1.getLocation());
		}
           
		
		
		return recordVo;
	}


















	public List<Result> findResultByDate(Result result) {
		// TODO Auto-generated method stub
		String month=result.getMonth();
		List<Result>results=null;
	    if(resultDao.fintResultByMonth(month)!=null){
	 results=resultDao.fintResultByMonth(month);
	    }
		return results;
	}









	public List<EmployeeVo> findEmployeeByResult(Result result) {
		// TODO Auto-generated method stub
		List<EmployeeVo>employeeVos=new ArrayList<EmployeeVo>();
		if(resultDao.findResultByMonthAndResult(result.getMonth(), result.getResult())!=null){
			
		List<Result>results=	resultDao.findResultByMonthAndResult(result.getMonth(), result.getResult());
		Iterator<Result>iterator=results.iterator();
		while(iterator.hasNext()){
			
			Result r1=iterator.next();
		    Employee employee=employeeDao.findEmployeeBypwId(r1.getPwId());
		    EmployeeVo employeeVo=new EmployeeVo();
		    employeeVo.setName(employee.getName());
		    employeeVo.setPwId(employee.getPwId());
		    employeeVo.setSuperId(employee.getSuperId());
			int size=resultDao.findmonthnum(employee.getPwId(), result.getMonth(), result.getResult());
			employeeVo.setExceptionNum(size);
			employeeVo.setHeadportrait(this.getIp()+employee.getHeadportrait());
			employeeVos.add(employeeVo);
		}
			
		};
		
		return employeeVos;
	}









	public List<EmployeeVo> findchecknow(Result result) {
		// TODO Auto-generated method stub
		SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
		Date date=result.getDate();
		String d1=dataFormater.format(date);
		List<EmployeeVo>employeeVos=new ArrayList<EmployeeVo>();
		if(resultDao.findResultByDate(d1,result.getSuperId())!=null){
		List<Result> results=resultDao.findResultByDate(d1,result.getSuperId());
		Iterator<Result>iterator=results.iterator();
		while(iterator.hasNext()){
			Result r1=iterator.next();
			Employee employee=employeeDao.findEmployeeBypwId(r1.getPwId());
			EmployeeVo employeeVo=new EmployeeVo();
			employeeVo.setHeadportrait(this.getIp()+employee.getHeadportrait());
			employeeVo.setResult(r1.getResult());
			employeeVo.setName(employee.getName());
			employeeVo.setPwId(employee.getPwId());
			employeeVo.setCheckInfo(r1.getCheckInfo());
			employeeVo.setRemake(r1.getRemake());
			employeeVos.add(employeeVo);
			
			
		}
		}
		return employeeVos;
	}









	public AcceptOneVo addRemake(Result result) {
		// TODO Auto-generated method stub
		
		
		result.setIsCheck(1);
	
		System.out.println("zheli"+result.getDate());
		String msg=resultDao.updateResult(result);
		AcceptOneVo acceptOneVo=new AcceptOneVo();
		acceptOneVo.setMsg(msg);
		return acceptOneVo;
	}









	@Override
	public PageBean findPageBean(PageBeanVo pageBeanVo) {
		// TODO Auto-generated method stub
		PageBean pageBean=null;
		if(resultDao.findPageBean(pageBeanVo)!=null){
			pageBean=resultDao.findPageBean(pageBeanVo);
			System.out.println(pageBean);
		}
		
	
		return pageBean;
	}









	@Override
	public List<EmployeeVo> findisChecked(Result result) {
		SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
		Date date=result.getDate();
		String d1=dataFormater.format(date);
		List<EmployeeVo>employeeVos=new ArrayList<EmployeeVo>();
		if(resultDao.findResultisChecked(d1,result.getSuperId())!=null){
		List<Result> results=resultDao.findResultisChecked(d1,result.getSuperId());
		Iterator<Result>iterator=results.iterator();
		while(iterator.hasNext()){
			Result r1=iterator.next();
			Employee employee=employeeDao.findEmployeeBypwId(r1.getPwId());
			EmployeeVo employeeVo=new EmployeeVo();
			employeeVo.setResult(r1.getResult());
			employeeVo.setName(employee.getName());
			employeeVo.setPwId(employee.getPwId());
			employeeVo.setCheckInfo(r1.getCheckInfo());
			employeeVo.setRemake(r1.getRemake());
			employeeVo.setHeadportrait(this.getIp()+employee.getHeadportrait());
			employeeVos.add(employeeVo);
			
			
		}
		}
		return employeeVos;
	}









	@Override
	public EmployeeVo findemployeeById(Employee employee) {
		// TODO Auto-generated method stub
		int pwId=employee.getPwId();
	Employee e1=	employeeDao.findEmployeeBypwId(pwId);
		EmployeeVo employeeVo=new EmployeeVo();
		employeeVo.setPwId(e1.getPwId());
		employeeVo.setName(e1.getName());
		employeeVo.setSuperId(e1.getSuperId());
		employeeVo.setPhone(e1.getPhone());
		employeeVo.setPosition(e1.getPosition());
		employeeVo.setHeadportrait(this.getIp()+e1.getHeadportrait());
		if(seatDao.findSeatBypwId(pwId)!=null){
			Seat seat=seatDao.findSeatBypwId(pwId);
			employeeVo.setLocation(seat.getLocation());
			employeeVo.setArea(seat.getArea());
			
			
		}
		
		
		
		
		return employeeVo;
	}









	@Override
	public AcceptOneVo addemployee(Employee employee) {
		
		int pwId=employee.getPwId();
		String superId=employee.getSuperId();
	String msg=	employeeDao.updateEmployeeSuper(superId, pwId);
	AcceptOneVo acceptOneVo=new AcceptOneVo();
	acceptOneVo.setMsg(msg);
		return acceptOneVo;
	}









	@Override
	public EmployeeVo upload(List<Attachment> attachments, HttpServletRequest request, String pwId) {
		 String path=ContextLoader.getCurrentWebApplicationContext().getServletContext().getRealPath("/images");
		   System.out.println(path);
		 if (attachments.size() > 0)  
	            System.out.println("ok");  
	  
	        for (Attachment attach : attachments) {  
	            DataHandler dh = attach.getDataHandler();  
	            System.out.println(attach.getContentType().toString());  
	  
	            if (attach.getContentType().toString().equals("text/plain")) {  
	                try {  
	                    System.out.println(dh.getName());  
	                    System.out.println(writeToString(dh.getInputStream()));  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                }  
	            } else {  
	                try {  
	                    writeToFile(dh.getInputStream(),  
	                    		path +"/"+ dh.getName());  
	                  
	    		    
	                    Employee employee=new Employee();
	                    employee.setHeadportrait("/Attendance/images/" + dh.getName());
	                    employee.setPwId(Integer.parseInt(pwId));
	    		      employeeDao.updateHead(employee);
	                } catch (IOException e) {  
	                    e.printStackTrace();  
	                }  
	                
	                
	            }  
	       
	        
		     
	        
	        }
	        
	        
	        Employee e1=employeeDao.findEmployeeBypwId(Integer.parseInt(pwId));
			
	  	  EmployeeVo employeeVo=new EmployeeVo();
	  	  
	  		employeeVo.setHeadportrait(this.getIp()+e1.getHeadportrait());
	  		
	  		return employeeVo;
	  
		
	}
	
	



	 private void writeToFile(InputStream ins, String path) {  
	        try {  
	            OutputStream out = new FileOutputStream(new File(path));  
	            int read = 0;  
	            byte[] bytes = new byte[1024];  
	  
	            while ((read = ins.read(bytes)) != -1) {  
	                out.write(bytes, 0, read);  
	            }  
	            out.flush();  
	            out.close();  
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        } catch (IOException e) {  
	            e.printStackTrace();  
	        }  
	    }

	  private String writeToString(InputStream ins) throws Exception {  
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        byte[] b = new byte[1024];  
	        int i = -1;  
	        while ((i = ins.read(b)) != -1) {  
	            out.write(b, 0, i);  
	        }  
	        ins.close();  
	        return new String(out.toByteArray(), "UTF-8");  
	    }









	@Override
	public EmployeeVo findUser(Employee employee) {
		
		Employee e1=employeeDao.findEmployeeBypwId(employee.getPwId());
		
	  EmployeeVo employeeVo=new EmployeeVo();
	  
		employeeVo.setHeadportrait(this.getIp()+e1.getHeadportrait());
		
		return employeeVo;
	}









	@Override
	public AcceptOneVo updateUser(Employee employee) {
		// TODO Auto-generated method stub
		
		
		
		String msg=employeeDao.updatedetail(employee);
		AcceptOneVo acceptOneVo=new AcceptOneVo();
		acceptOneVo.setMsg(msg);
		return acceptOneVo;
	}


































}
