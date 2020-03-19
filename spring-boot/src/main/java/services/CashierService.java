package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import Connection.SqliteService;
import entities.Cashier;


@Service
public class CashierService {
	
	private static final Logger logger = LoggerFactory.getLogger(CashierService.class);
	private List<Cashier> result = new ArrayList<Cashier>();
	private Connection con = SqliteService.getConnection();
	
	private String SQL_GETALL = "SELECT * FROM CASHIER";
	private String SQL_SEARCH = "SELECT * FROM CASHIER WHERE NAME LIKE ? AND status <> -1";
	private String SQL_GETBYID = "SELECT * FROM CASHIER WHERE cashierid = ? AND status <> -1";
	private String SQL_INSERT = "INSERT INTO CASHIER(code,name,ipaddress,status) VALUES(?,?,?,?)";
	private String SQL_UPDATE = "UPDATE CASHIER SET code = ?, name = ?, ipaddress=?, status = ? WHERE cashierid = ?";
	private String SQL_DELETE = "UPDATE CASHIER SET status = -1 WHERE cashierid = ?";
	private String SQL_LOCK = "UPDATE CASHIER SET status = ? WHERE cashierid = ?";
	
	public List<Cashier> getAll()  {
		try {
			PreparedStatement stmt = con.prepareStatement(SQL_GETALL);
			ResultSet rs=stmt.executeQuery();
			result = new ArrayList<Cashier>();
			while(rs.next()) {
				result.add(new Cashier(rs.getInt("cashierid"),rs.getString("code"),rs.getString("name"),rs.getString("ipaddress"),rs.getInt("status")));
			}
		} catch (Exception e) {
			logger.error("CashierService.getAll | " + e.getMessage());
		}  	
		return result;
	}
	
	public List<Cashier> search(String key)  {
		try {
			PreparedStatement stmt = con.prepareStatement(SQL_SEARCH);
			stmt.setString(1, "%" + key + "%");
			ResultSet rs=stmt.executeQuery();
			result = new ArrayList<Cashier>();
			while(rs.next()) {
				result.add(new Cashier(rs.getInt("cashierid"),rs.getString("code"),rs.getString("name"),rs.getString("ipaddress"),rs.getInt("status")));
			}
		} catch (Exception e) {
			logger.error("CashierService.search | " + e.getMessage());
		}  	
		return result;
	}
	
	public Cashier getById(int id) {
		Cashier t = null;
		try {
			PreparedStatement stmt = con.prepareStatement(SQL_GETBYID);
			stmt.setInt(1, id);
			ResultSet rs=stmt.executeQuery();
			rs.next();
			t = new Cashier(rs.getInt("cashierid"),rs.getString("code"),rs.getString("name"),rs.getString("ipaddress"),rs.getInt("status"));
		} catch (Exception e) {
			logger.error("CashierService.getById | " + e.getMessage());
		}  
		return t;
	}
	public int save (Cashier cashier) {
		if(cashier.getName()==null || cashier.getCode()==null || cashier.getIpaddress()==null) {
			logger.error("TopicService.save - name, code, ipaddress not null");
			return 99;
		}
		try {
			if(cashier.getCashierid() == 0) {
				PreparedStatement stmt = con.prepareStatement(SQL_INSERT);
				stmt.setString(1, cashier.getCode());
				stmt.setString(2, cashier.getName());
				stmt.setString(3, cashier.getIpaddress());
				stmt.setInt(4, cashier.getStatus());
				stmt.executeUpdate();
			}
			else {
				PreparedStatement stmt = con.prepareStatement(SQL_UPDATE);
				stmt.setString(1, cashier.getCode());
				stmt.setString(2, cashier.getName());
				stmt.setString(3, cashier.getIpaddress());
				stmt.setInt(4, cashier.getStatus());
				stmt.setInt(5, cashier.getCashierid());
				stmt.executeUpdate();
			}
			return 0;
		} catch (SQLException e) {
			logger.error("CashierService.save | " + e.getMessage());
			return 99;
		} 
	}
	
	public int delete(int id) {
		try {
			PreparedStatement stmt = con.prepareStatement(SQL_DELETE);
			stmt.setInt(1, id);
			stmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			logger.error("CashierService.delete | " + e.getMessage());
			return 99;
		} 
	}
	
	public int lock(int id,int status) {
		try {
			PreparedStatement stmt = con.prepareStatement(SQL_LOCK);
			stmt.setInt(1, id);
			stmt.setInt(2, status);
			stmt.executeUpdate();
			return 0;
		} catch (SQLException e) {
			logger.error("CashierService.lock | " + e.getMessage());
			return 99;
		} 
	}

}

