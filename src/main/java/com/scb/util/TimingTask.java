package com.scb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scb.dao.EmployeeDao;
import com.scb.dao.RecordDao;
import com.scb.dao.ResultDao;
import com.scb.dao.SeatDao;
import com.scb.po.Employee;
import com.scb.po.Record;
import com.scb.po.Result;
//姣忓ぉ鏃╀笂1鐐瑰畾鏃�鐢熸垚鏂扮殑result琛ㄥ拰record琛紝璁剧疆seat涓虹┖锛屽悓鏃秛pdateResult.
@Service
@Transactional
public class TimingTask {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private SeatDao seatDao;
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private ResultDao resultDao;
	
	
	
	// 姣忓ぉ鏅氫笂11鐐规悳绱ecord,update result,涔嬪悗 set 搴т綅
	 public void dosomething() {
		 
		 
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		     Date date=new Date();
		  String d1=   dateFormat.format(date);
		List<Record> records= recordDao.findRecordAll(d1);
		
		Iterator<Record>iterator=records.iterator();
		while(iterator.hasNext()){
			
			Record record=iterator.next();
			Date beginTime=record.getBeginTime();
			Date endTime=record.getEndTime();
			//旷工
			if(beginTime==null&&endTime==null){
				Result result=new Result();
				result.setResult("旷工");
				result.setIsNomal(0);
				result.setDate(new Date());
				result.setPwId(record.getPwId());
				resultDao.update(result);
				
			}
			//漏刷卡
			if(beginTime!=null&&endTime==null){
				Result result=new Result();
				result.setResult("漏刷卡");
				result.setIsNomal(0);
				result.setDate(new Date());
				result.setPwId(record.getPwId());
				resultDao.update(result);
				
			}
		}
		seatDao.update();
	       
	    }
	 
	 
	 
	 //姣忓ぉ鏅氫笂涓�偣鐢熸垚鏂扮殑record鍜宺esult
	 public void insert() throws Exception{
		 
			List<Employee>employees=	employeeDao.findAllEmployee();
			Iterator<Employee>iterator=employees.iterator();
			while(iterator.hasNext()){
				Employee employee=iterator.next();
				employee.getPwId();
				Record record=new Record();
				record.setBeginTime(null);
				SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
				Date date=new Date();
				String d1=dataFormater.format(date);
			Date d2=	dataFormater.parse(d1);
			
			
			SimpleDateFormat Formater=new SimpleDateFormat("yyyy-MM");
			    String d3=Formater.format(date);
				record.setDate(d2);
				record.setEndTime(null);
				record.setLocation(null);
				record.setPwId(employee.getPwId());
				record.setMonth(d3);
				recordDao.insertRecord(record);
				
				Result result=new Result();
				result.setPwId(employee.getPwId());
				result.setDate(d2);
				result.setIsCheck(0);
				result.setIsNomal(1);
				result.setResult("正常");
				result.setSuperId(Integer.parseInt(employee.getSuperId()));
				result.setName(employee.getName());
				result.setMonth(d3);
				resultDao.insertResult(result);
			}
		 
		 
	 }
	 
	 
}
