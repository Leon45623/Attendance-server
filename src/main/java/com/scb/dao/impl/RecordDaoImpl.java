package com.scb.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scb.dao.RecordDao;
import com.scb.po.Record;
@Repository
public class RecordDaoImpl implements RecordDao {

	
	
	@Autowired
	private SessionFactory sessionFactory;

	public String insertRecord(Record record) {
		// TODO Auto-generated method stub
		
		
		System.out.println(record);
		sessionFactory.getCurrentSession().save(record);
		return "success";
	}

	public String checkout(Record record) {
		String hql = "update Record set endTime=? where pwId=? and  date =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setTimestamp(0,new  Date());
      query.setInteger(1, record.getPwId());
      query.setTimestamp(2, record.getDate());
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}

	public Record findRecordByDate(String date,int pwId) {
		// TODO Auto-generated method stub
		String hql = "from Record where date=? and pwId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,date);
      query.setInteger(1, pwId);
		List<Record> records= query.list();
		if (records.size()== 0) {
			return null;
		} else {
			return records.get(0);
		}
	
	}

	public String checkin(Record record) {
		String hql = "update Record set beginTime=? ,location=? where pwId=? and  date =?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setTimestamp(0,new  Date());
      query.setString(1, record.getLocation());
      query.setInteger(2, record.getPwId());
      query.setTimestamp(3, record.getDate());
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}

	public List<Record> findRecordAll(String date) {
		String hql = "from Record where date=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
      query.setString(0,date);
		List<Record> records= query.list();
			return records;
		
	}
	
	
}
