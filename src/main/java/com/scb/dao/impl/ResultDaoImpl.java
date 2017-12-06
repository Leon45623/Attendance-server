package com.scb.dao.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Repository;

import com.scb.dao.ResultDao;
import com.scb.po.PageBean;
import com.scb.po.Record;
import com.scb.po.Result;
import com.scb.vo.PageBeanVo;
import com.scb.vo.ResultVo;
@Repository
public class ResultDaoImpl implements ResultDao {
	@Autowired
	private SessionFactory sessionFactory;
	public String insertResult(Result result) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().save(result);
		return "success";
	}
	public Result findResultByIdAndDate(String date, int pwId) {
		String hql = "from Result where date=? and pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,date);
      query.setInteger(1, pwId);
		List<Result> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records.get(0);
		}
	}
	public List<Result> fintResultByMonth(String month) {
		String hql = "from Result where month=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,month);
		List<Result> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records;
		}
	}
	public List<Result> findResultByMonthAndResult(String month, String result) {
		String hql = "from Result where month=? and result=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,month);
      query.setString(1, result);
		List<Result> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records;
		}
	}
	public int findmonthnum(int pwId, String month, String  result) {
		String hql = "from Result where month=? and pwId=? and result=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0, month);
      query.setInteger(1, pwId);
      query.setString(2, result);
		List<Result> records= query.list();
		return records.size();
		
	}
	public List<Result> findResultByDate(String date,int superId) {
		String hql = "from Result where date=? and isCheck=? and isNomal=?  and superId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,date);
      query.setInteger(1, 0);
      query.setInteger(2, 0);
      query.setInteger(3, superId);
		List<Result> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records;
		}
	}
	public String updateResult(Result result) {
		String hql = "update Result set isCheck=?,checkInfo=?,remake=? where pwId=? and  date =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, result.getIsCheck());
        query.setString(1, result.getCheckInfo());
        query.setString(2, result.getRemake());
        query.setInteger(3, result.getPwId());
    	SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
    	String date=dataFormater.format(result.getDate());
        query.setString(4, date);
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}
	public String update(Result result) {
		String hql = "update Result set isNomal=?,result=? where pwId=? and  date =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.setInteger(0, result.getIsNomal());
        query.setString(1, result.getResult());
        query.setInteger(2, result.getPwId());
    	SimpleDateFormat dataFormater=new SimpleDateFormat("yyyy-MM-dd");
    	String date=dataFormater.format(result.getDate());
        query.setString(3, date);
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}
	@Override
	public PageBean findPageBean(PageBeanVo pageBeanVo)  {
		
		PageBean pageBean=new PageBean();
		int currentPage=pageBeanVo.getCurrentPage();
		int pageSize=pageBeanVo.getPageSize();
		
		
		
		
		
		String hql="from Result where superId=? and   isNomal=? and isCheck=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		//设置起始行号以及查询的个数
		query.setInteger(0, Integer.parseInt(pageBeanVo.getSuperId()));
		query.setInteger(1, 0);
		query.setInteger(2, 0);
		int beginRows=(currentPage-1)*pageSize;
		int rowCount=pageSize;
		query.setFirstResult(beginRows);
		query.setMaxResults(rowCount);
		//总行数
		int lists=query.list().size() ;
//		System.out.println(lists);
//		if(pageSize*currentPage>=lists){
//			List datas=new ArrayList<>();
//			List<Result> teachers=query.list();
//			Iterator<Result> iter=teachers.iterator();
//			while(iter.hasNext()){
//				Result teacher=iter.next();
//				datas.add(teacher);
//			}
//			pageBean.setData(datas);
//			pageBean.setPageSize(lists);
//			pageBean.setTotalRows(lists);
//			pageBean.setCurrentPage(currentPage);
//			return pageBean;
//		}
			if(lists >0){
			List datas=new ArrayList<>();
			List<Result> teachers=query.list();
			Iterator<Result> iter=teachers.iterator();
			while(iter.hasNext()){
				Result result=iter.next();
		
                ResultVo resultVo=new ResultVo();
                resultVo.setCheckInfo(result.getCheckInfo());
                resultVo.setId(result.getId());
                resultVo.setIsCheck(result.getIsCheck());
                resultVo.setIsNomal(result.getIsNomal());
                resultVo.setMonth(result.getMonth());
                resultVo.setName(result.getName());
                resultVo.setPwId(result.getPwId());
                resultVo.setRemake(result.getRemake());
                resultVo.setResult(result.getResult());
                resultVo.setSuperId(result.getSuperId());   
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			String d1=	dateFormat.format(result.getDate());
			resultVo.setDate(d1);
			
				datas.add(resultVo);
		
				
			}
			pageBean.setData(datas);
			pageBean.setPageSize(pageSize);
			String hql2="from Result where superId=? and   isNomal=? and isCheck=?";
			Query query2=sessionFactory.getCurrentSession().createQuery(hql2);
			query2.setString(0, pageBeanVo.getSuperId());
			query2.setInteger(1, 0);
			query2.setInteger(2, 0);
			if(query2.list().size()>0){
				System.out.println(query2.list().size());
				int totalRows=query2.list().size();
				if(currentPage*pageSize<totalRows){
				pageBean.setTotalRows(totalRows);
				}
			}
			pageBean.setCurrentPage(currentPage);
			System.out.println(pageBean.getCurrentPage()+"---"+pageBean.getPageSize()+"----"+pageBean.getTotalPages()+"----"+pageBean.getTotalRows());
			System.out.println("pageBean-->totalPages--->"+pageBean.getTotalPages());
			return pageBean;
			
		}else{
			
			
			return  null;
		}
	}
	@Override
	public List<Result> findResultisChecked(String date, int superId) {
		String hql = "from Result where date=? and isCheck=? and isNomal=?  and superId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,date);
      query.setInteger(1, 1);
      query.setInteger(2, 0);
      query.setInteger(3, superId);
		List<Result> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records;
		}
	}

}
