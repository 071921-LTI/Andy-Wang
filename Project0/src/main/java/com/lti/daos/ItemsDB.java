package com.lti.daos;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lti.models.Shoes;
import com.lti.util.ConnectionUtil;

public class ItemsDB implements ItemsDao{
	private static Logger log = LogManager.getRootLogger();
	@Override
	public Shoes getItemById(int id) {
		Shoes shoe = null;
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0.items where shoe_id = ?";
			PreparedStatement ps= con.prepareStatement(sql);
			ps.setInt(1,id); //1 refers to first ? to parameterize
		
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) { 
				int shoe_id = rs.getInt("shoe_id");
				String shoe_brand = rs.getString("shoe_brand");
				int shoe_size = rs.getInt("shoe_size");
				String shoe_type = rs.getString("shoe_type");
				String shoe_color = rs.getString("shoe_color");
				
				shoe = new Shoes(shoe_id,shoe_brand,shoe_size,shoe_type,shoe_color);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		return shoe;
	}

	@Override
	public List<Shoes> getItems() {
		List<Shoes> shoes = new ArrayList<>();
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			String sql = "select * from project0.items;";
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) { 
				String shoe_brand = rs.getString("shoe_brand");
				int shoe_size = rs.getInt("shoe_size");
				String shoe_type = rs.getString("shoe_type");
				String shoe_color = rs.getString("shoe_color");
				int shoe_id = rs.getInt("shoe_id");
//				System.out.println(shoe_id + " " + shoe_brand + " " + shoe_size + " " +  shoe_type + " " + shoe_color + " " + shoe_price);
				Shoes shoe = new Shoes(shoe_id,shoe_brand,shoe_size,shoe_type,shoe_color);
				shoes.add(shoe);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		}
		return shoes;
	}

	@Override
	public boolean addItem(Shoes shoe) {
		String sql = "insert into project0.items (shoe_brand,shoe_size,shoe_type,shoe_color) values (?,?,?,?) returning shoe_id;";
		
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, shoe.getBrand());
			ps.setInt(2, shoe.getSize());
			ps.setString(3, shoe.getShoeType());
			ps.setString(4, shoe.getColor());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return true;
			}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
				e.printStackTrace();
			}
		return false;
	}
	@Override
	public boolean updateItem(Shoes shoe) {
		
		boolean status = false;
		String sql = "update project0.items set shoe_brand = ?,shoe_size= ?,shoe_type= ?,shoe_color= ? where shoe_id = ?";
		try (Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, shoe.getBrand());
			ps.setInt(2, shoe.getSize());
			ps.setString(3, shoe.getShoeType());
			ps.setString(4, shoe.getColor());
			ps.setInt(5,shoe.getId());

			int rowschanged = ps.executeUpdate();
			if (rowschanged > 0) {
				status = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			log.error("Exception was thrown: " + e.fillInStackTrace());
			e.printStackTrace();
		}


		return status;
	}

	@Override
	public int removeItem(Shoes shoe) {
		String sql = "delete from project0.items where shoe_id = ?;";
		int rowChanged = -1;
		
		try(Connection con = ConnectionUtil.getConnectionFromFile()){
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, shoe.getId());
			rowChanged = ps.executeUpdate();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				log.error("Exception was thrown: " + e.fillInStackTrace());
				e.printStackTrace();
			} catch (IOException e) {
				log.error("Exception was thrown: " + e.fillInStackTrace());
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return rowChanged;
	}

	@Override
	public List<Shoes> getShoebyCustomerId(int id) {
		return null;
	}

}
