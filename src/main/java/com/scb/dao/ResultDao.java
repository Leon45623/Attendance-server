package com.scb.dao;

import java.util.List;

import com.scb.po.PageBean;
import com.scb.po.Result;
import com.scb.vo.PageBeanVo;

public interface ResultDao {

	
	public String insertResult(Result result);
	public Result findResultByIdAndDate(String date,int pwId);
	public List<Result> fintResultByMonth(String month);
	public List<Result> findResultByMonthAndResult(String month,String result);
	public int findmonthnum(int pwId,String month,String result);
	public List<Result>findResultByDate(String date,int superId);
	public List<Result>findResultisChecked(String date,int superId);
	public String updateResult(Result result);
	
	public String update(Result result);
	public PageBean findPageBean(PageBeanVo pageBeanVo) ;
}
