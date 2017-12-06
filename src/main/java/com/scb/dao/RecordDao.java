package com.scb.dao;

import java.util.List;

import com.scb.po.Record;

public interface RecordDao {

	
	
	public String insertRecord(Record record);
	public String checkout(Record record); 
	public  Record findRecordByDate(String date,int pwId);
	public String checkin(Record record);
	public List<Record> findRecordAll(String date);
	
}
