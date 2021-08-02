package com.lti.daos;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.lti.models.BidList;
import com.lti.models.Shoes;
import com.lti.models.User;
import com.lti.util.ConnectionUtil;

public class BidsDB implements BidDao {
	

	@Override
	public int addItemBid(int buyer_id, int shoe_id, double bid_price,String item_status) {
		int id = -1;
		// TODO Auto-generated method stub
		String sql = "insert into project0.bidlist (item_id,buyer_id,offer_price,bid_date,payment_total,item_status) values (?,?,?,?,?,?) returning item_id;";
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
		String sql = "delete from project0.bidlist where item_id = ? and buyer_id = ?;"
				+ "delete from project0.items where shoe_id = ?;";
		int rowChanged = -1;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, shoe_id);
			ps.setInt(2, cust_id);
			ps.setInt(3, shoe_id);
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
	public int removeItemBids(int shoe_id) {
		// TODO Auto-generated method stub
		String sql = "delete from project0.bidlist where item_id = ?;"
				+ "delete from project0.items where shoe_id = ?;";
		int rowChanged = -1;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, shoe_id);
			ps.setInt(2, shoe_id);
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
					+ "where c.customer_id = b.buyer_id and b.item_id = ?";
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
		String status = " ";
		// TODO Auto-generated method stub
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select b.item_status, c.customer_name , b.offer_price, b.payment_total , s.shoe_id,s.shoe_price\r\n"
					+ "from project0.customer c, project0.bidlist b, project0.items s\r\n"
					+ "where c.customer_id = b.buyer_id and c.customer_id = ? and s.shoe_id = b.item_id and s.shoe_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cust_id);
			ps.setInt(2, shoe_id);
			ResultSet rs = ps.executeQuery();
			if (rs.next())
				for (int i = 1; i < 7; i++) {
					status += rs.getString(i) + "		" ;
				}
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
	public int setItemStatus(int shoe_id, int cust_id, String status) {
		// TODO Auto-generated method stub
		int rowChanged = 0;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "update project0.bidlist set item_status = ? where buyer_id = ? and item_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,status);
			ps.setInt(2, cust_id);
			ps.setInt(3, shoe_id);
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
	public double getWeeklyPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double totalPayments() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int editItemBid(int cust_id, int shoe_id,double bid_price) {
		// TODO Auto-generated method stub
		int rowchanged = 0;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "update project0.bidlist set offer_price = ? where buyer_id = ? and item_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1,bid_price);
			ps.setInt(2, cust_id);
			ps.setInt(3, shoe_id);
			rowchanged = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rowchanged;
	}

	@Override
	public List<BidList> getAllBids() {
		// TODO Auto-generated method stub
		List<BidList> allBids = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0.bidlist;";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) { 
				double offer = rs.getDouble("offer_price");
				String status = rs.getString("item_status");
				double payment = rs.getDouble("payment_total");
				int shoe_id = rs.getInt("item_id");
				int cust_id = rs.getInt("buyer_id");
				BidList bid = new BidList(shoe_id,cust_id,offer,payment,status);
				allBids.add(bid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allBids;
	}
	
	@Override
	public List<BidList> getAllBidsByUser(int cust_id){
		List<BidList> allBids = new ArrayList<>();
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0.bidlist where buyer_id = ?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, cust_id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) { 
				double offer = rs.getDouble("offer_price");
				String status = rs.getString("item_status");
				double payment = rs.getDouble("payment_total");
				int shoe_id = rs.getInt("item_id");
				BidList bid = new BidList(shoe_id,cust_id,offer,payment,status);
				allBids.add(bid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allBids;
	}


}
