package com.scb.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.cxf.jaxrs.ext.multipart.Multipart;
import org.apache.cxf.jaxrs.ext.multipart.MultipartBody;

import com.scb.po.Employee;
import com.scb.po.PageBean;
import com.scb.po.Record;
import com.scb.po.Result;
import com.scb.po.Seat;
import com.scb.vo.AcceptOneVo;
import com.scb.vo.EmployeeVo;
import com.scb.vo.PageBeanVo;
import com.scb.vo.RecordVo;

@Consumes(MediaType.APPLICATION_JSON)
public interface UserService {

	


	@POST
	@Path(value="/register")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public AcceptOneVo register(Employee employee );

	
	@POST
	@Path(value="/login")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)	
	public EmployeeVo login(Employee employee );    
	
	
	
	@POST
	@Path(value="/addphone")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Employee registerDeatil(Employee employee);
	
	
	@POST
	@Path(value="/search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EmployeeVo> search(AcceptOneVo acceptOneVo);
	
	@POST
	@Path(value="/findmysheet")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public RecordVo findmysheet(Record record) throws Exception;
	
	
	
	@POST
	@Path(value="/findAllEmployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Employee>findAllEmployee();
	
	
	@POST
	@Path(value="/findAllseat")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Seat>findAllseat();
	
	
	@POST
	@Path(value="/findSeatById")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EmployeeVo findSeatById(Seat seat);
	
	
	@POST
	@Path(value="/addRemake")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AcceptOneVo addRemake(Result result);
	
	
	@POST
	@Path(value="/findEmployeelist")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EmployeeVo>findEmployeelist(Employee employee);
	
	
	
	
	
	
	
	
	@POST
	@Path(value="/checkin")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AcceptOneVo checkin(Seat seat);
	
	

	@POST
	@Path(value="/checkout")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AcceptOneVo checkout(Seat seat);
	
	
	
	
	@POST
	@Path(value="/findResultByDate")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Result> findResultByDate(Result result);
	
	@POST
	@Path(value="/findEmployeeByResult")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EmployeeVo> findEmployeeByResult(Result result);
	
	
	@POST
	@Path(value="/findchecknow")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EmployeeVo> findchecknow(Result result);
	
	@POST
	@Path(value="/findisChecked")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<EmployeeVo> findisChecked(Result result);
	
	
	
	@POST
	@Path(value="/findPageBean")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public PageBean findPageBean(PageBeanVo pageBeanVo);
	

	
	@POST
	@Path(value="/findemployeeById")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EmployeeVo findemployeeById(Employee employee);
	
	
	
	
	@POST
	@Path(value="/addemployee")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AcceptOneVo addemployee(Employee employee);
	
	
	@POST
	@Path(value="/upload")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.MULTIPART_FORM_DATA )  
	public EmployeeVo upload( List<Attachment>attachments,@Context HttpServletRequest request, @Multipart(value="pwId",type="text/plain")String pwId );
	
	
	@POST
	@Path(value="/findUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public EmployeeVo findUser(Employee employee);
	
	
	@POST
	@Path(value="/updateUser")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public AcceptOneVo updateUser(Employee employee);
	
	
}
