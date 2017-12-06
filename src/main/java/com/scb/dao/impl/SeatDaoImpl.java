package com.scb.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.scb.dao.SeatDao;
import com.scb.po.Seat;
@Repository
public class SeatDaoImpl implements SeatDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	public String updateSeat(Seat seat) {
		String hql = "update Seat set isUsed=?, pwId=? where seatId=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,seat.getIsUsed());
		query.setInteger(1,seat.getPwId());
		query.setInteger(2,seat.getSeatId());
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}



	public Seat findSeatBypwId(int pwId) {
		String hql="from Seat where pwId=? ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, pwId);
		List<Seat> seats=query.list();
	    if(seats.size()==0){	
	    	return null;
	    }else{
	    	
	    	return seats.get(0);
	    }
	}



	public List<Seat> findAllSeat() {
			String hql="from Seat  ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Seat> seats=query.list();
		return seats;
	   
	}



	public Seat findSeatById(int seatId) {
		// TODO Auto-generated method stub
		String hql="from Seat where seatId=? ";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0, seatId);
		List<Seat> seats=query.list();
	    if(seats.size()==0){	
	    	return null;
	    }else{
	    	
	    	return seats.get(0);
	    }
	}



	public String update() {
		String hql = "update Seat set isUsed=?, pwId=? ";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setInteger(0,0);
		query.setInteger(1,0);
		int j = query.executeUpdate();
		if (j > 0) {
			return "success";
		} else {
			return null;
		}
	}

}
