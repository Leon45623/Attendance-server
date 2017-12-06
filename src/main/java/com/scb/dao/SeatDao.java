package com.scb.dao;

import java.util.List;

import com.scb.po.Seat;

public interface SeatDao {

	
	public String updateSeat(Seat seat);
	
	public Seat findSeatBypwId(int pwId);
	
	public List<Seat>findAllSeat();
	
	public Seat findSeatById(int seatId);
	
	public String update();
}
