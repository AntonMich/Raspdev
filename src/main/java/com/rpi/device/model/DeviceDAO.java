package com.rpi.device.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAO {
	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection jdbcConnection;
	
	public DeviceDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	protected void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = DriverManager.getConnection(
										jdbcURL, jdbcUsername, jdbcPassword);
		}
	}
	
	protected void disconnect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}
	
	public boolean insertDevice(Device device) throws SQLException {
		String sql = "INSERT INTO device (name, GPIOnumber, type) VALUES (?, ?, ?)";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, device.getName());
		statement.setInt(2, device.getGPIOnumber());
		statement.setString(3, device.getType());
		
		boolean rowInserted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowInserted;
	}
	
	public List<Device> listAllDevices() throws SQLException {
		List<Device> listDevice = new ArrayList<>();
		
		String sql = "SELECT * FROM device";
		
		connect();
		
		Statement statement = jdbcConnection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("device_id");
			String name = resultSet.getString("name");
			int GPIOnumber = resultSet.getInt("GPIOnumber");
			String type = resultSet.getString("type");
			
			Device device = new Device(id, name, GPIOnumber, type);
			listDevice.add(device);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listDevice;
	}
	
	public boolean deleteDevice(Device device) throws SQLException {
		String sql = "DELETE FROM device where device_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, device.getId());
		
		boolean rowDeleted = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowDeleted;		
	}
	
	public boolean updateDevice(Device device) throws SQLException {
		String sql = "UPDATE device SET name = ?, GPIOnumber = ?, type = ?";
		sql += " WHERE device_id = ?";
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setString(1, device.getName());
		statement.setInt(2, device.getGPIOnumber());
		statement.setString(3, device.getType());
		statement.setInt(4, device.getId());
		boolean rowUpdated = statement.executeUpdate() > 0;
		statement.close();
		disconnect();
		return rowUpdated;		
	}
	
	public Device getDevice(int id) throws SQLException {
		Device device = null;
		String sql = "SELECT * FROM device WHERE device_id = ?";
		
		connect();
		
		PreparedStatement statement = jdbcConnection.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String name = resultSet.getString("name");
			int GPIOnumber = resultSet.getInt("GPIOnumber");
			String type = resultSet.getString("type");
			
			device = new Device(id, name, GPIOnumber, type);
		}
		
		resultSet.close();
		statement.close();
		
		return device;
	}
}
