package com.lti.daos;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lti.models.Shoes;
import com.lti.models.User;
import com.lti.util.ConnectionUtil;

public class BidsDB implements BidDao {
	
//	public BidsDB() {
//		try (Connection con = ConnectionUtil.getConnectionFromFile()){
//			String sql = "drop view BIDS;\r\n"
//					+ "create view BIDS as\r\n"
//					+ "select c.customer_id ,c.customer_name , b.offer_price, b.payment_total , s.shoe_brand ,s.shoe_size,s.shoe_type,s.shoe_color,s.shoe_price,s.shoe_id \r\n"
//					+ "from customer c, bidlist b, items s\r\n"
//					+ "where c.customer_id = b.buyer_id and c.customer_id = ? and s.shoe_id = b.item_id;";
//			Statement s = con.createStatement();
//			s.execute(sql);
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	@Override
	public boolean updateStatus(int shoe_id, int customer_id, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int addItemBid(int buyer_id, int shoe_id, double bid_price,String item_status) {
		int id = -1;
		// TODO Auto-generated method stub
		String sql = "insert into project0.bidlist (item_id,buyer_id,offer_price,bid_date,payment_total,item_status) values (?,?,?,?,?) returning item_id;";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,shoe_id);
			ps.setInt(2, buyer_id);
			ps.setDouble(3, bid_price);
			ps.setObject(4, LocalDate.now());
			ps.setDouble(5,0);
			ps.setString(6, item_status);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				id = shoe_id;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}

	@Override
	public int removeItemBid(int shoe_id,int cust_id) {
		// TODO Auto-generated method stub
		String sql = "delete from project0.bidlist where item_id = ? and buyer_id = ?;";
		int rowChanged = -1;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, shoe_id);
			ps.setInt(2, cust_id);
			rowChanged = ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rowChanged;
	}

	@Override
	public List<User> getCustomerBids(int shoe_id) {
		// TODO Auto-generated method stub
		List<User> userBids = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select c.customer_name, c.customer_id from project0.customer c, project0.bidlist b "
					+ "where c.customer_id = b.buyer_id and b.buyer_id = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, shoe_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				User user = new User(rs.getString("customer_name"), rs.getInt("customer_id"));
				userBids.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userBids;
	}

	@Override
	public List<String> getBids(int cust_id) {
		// TODO Auto-generated method stub
		List<String> shoes = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select c.customer_id ,c.customer_name , b.offer_price, b.payment_total , s.shoe_brand ,s.shoe_size,s.shoe_type,s.shoe_color,s.shoe_price,s.shoe_id \r\n"
					+ "from project0.customer c, project0.bidlist b, project0.items s\r\n"
					+ "where c.customer_id = b.buyer_id and c.customer_id = ? and s.shoe_id = b.item_id;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cust_id);
			ResultSet rs = ps.executeQuery(sql);
			
			while(rs.next()) { 
				shoes.add(rs.toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return shoes;
	}

	@Override
	public String showStatus(int shoe_id, int cust_id) {
		String status = "";
		// TODO Auto-generated method stub
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select b.item_status, c.customer_name , b.offer_price, b.payment_total , s.shoe_brand ,s.shoe_size,s.shoe_type,s.shoe_color,s.shoe_price\r\n"
					+ "from project0.customer c, project0.bidlist b, project0.items s\r\n"
					+ "where c.customer_id = b.buyer_id and c.customer_id = ? and s.shoe_id = b.item_id and s.shoe_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cust_id);
			ps.setInt(2, shoe_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				status = rs.toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public boolean setItemStatus(int shoe_id, int cust_id, String status) {
		// TODO Auto-generated method stub
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "update project.bidlist set item_status = ? where buyer_id = ? and items_id = >;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,status);
			ps.setInt(2, cust_id);
			ps.setInt(3, shoe_id);
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public double getWeeklyPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalPayments() {
		// TODO Auto-generated method stub
		return 0;
	}



}
