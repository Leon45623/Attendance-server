package com.scb.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scb.dao.RecordDao;
import com.scb.dao.ResultDao;
import com.scb.po.Record;
import com.scb.po.Result;

//周一到周五 每天上午9点到下午 5点半每隔5分钟执行一次
@Service
@Transactional
public class QuartzJob {
	
	
	@Autowired
	private RecordDao recordDao;
	
	@Autowired
	private ResultDao resultDao;

    public void dosomething() {
        // Quartz 的任务调度
        System.out.println("开始周期性地循环执行任务。。。。");
    }
    
    
    
    
    
    
    public void findcheck(){
    	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    	Date date=new Date();
    	String d1=dateFormat.format(date);
    	System.out.println(d1);
    if(recordDao.findRecordAll(d1)!=null){
    	 List<Record>records	  =	recordDao.findRecordAll(d1);
    	 
    	Iterator<Record>iterator=records.iterator();
    	while(iterator.hasNext()){
    		Record record=iterator.next();
    		Date beginTime=record.getBeginTime();
    		Date endTime=record.getEndTime();
    		SimpleDateFormat fomate=new SimpleDateFormat("HH:mm");
    		if(beginTime!=null){
    			System.out.println("开始时间"+fomate.format(beginTime));;
    			System.out.println("小时"+ fomate.format(beginTime).split(":")[0]);
    			System.out.println("分钟"+ fomate.format(beginTime).split(":")[1]);
    			int hour=Integer.parseInt(fomate.format(beginTime).split(":")[0]);
    			int munite=Integer.parseInt(fomate.format(beginTime).split(":")[1]);
    			if((hour*60+munite)>540){
    				//迟到，添加信息
    				Result result=new Result();
    				result.setResult("迟到");
    				result.setIsNomal(0);
    				result.setDate(new Date());
    				result.setPwId(record.getPwId());
    				resultDao.update(result);
    				
    			}
    		}
    		if(endTime!=null){
    			
    			System.out.println("结束时间"+fomate.format(endTime));;
    			System.out.println("小时"+ fomate.format(endTime).split(":")[0]);
    			System.out.println("分钟"+ fomate.format(endTime).split(":")[1]);
    			
    			int hour=Integer.parseInt(fomate.format(endTime).split(":")[0]);
    			int munite=Integer.parseInt(fomate.format(endTime).split(":")[1]);
    			if((hour*60+munite)<1020){
    				//迟到，添加信息
    				Result result=new Result();
    				result.setResult("早退");
    				result.setIsNomal(0);
    				result.setDate(new Date());
    				result.setPwId(record.getPwId());
    				resultDao.update(result);
    			}
    			
    			
    		}
    		
    		
    	
    	}
    }
    }

}