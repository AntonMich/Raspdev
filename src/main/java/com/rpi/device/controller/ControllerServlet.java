package com.rpi.device.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rpi.device.led.SimplePin;
import com.rpi.device.model.Device;
import com.rpi.device.model.DeviceDAO;



/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControllerServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1915454283771913631L;
	private DeviceDAO deviceDao;

	public void init() {
		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
		String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
		String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

		deviceDao = new DeviceDAO(jdbcURL, jdbcUsername, jdbcPassword);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertDevice(request, response);
				break;
			case "/delete":
				deleteDevice(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateDevice(request, response);
				break;
			case "/startscript":
				startDevice(request, response);
				break;
			default:
				listDevice(request, response);
				break;
			}
		} catch (SQLException | InterruptedException ex) {
			throw new ServletException(ex);
		}
	}

	private void startDevice(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, InterruptedException {
		List<Device> listDevice = deviceDao.listAllDevices();
		for(Device dev:listDevice){
			System.out.println(dev);
			SimplePin sPin=new SimplePin(dev.getGPIOnumber(), dev.getName());
			sPin.startAction();
		}
		response.sendRedirect("list");
		
		// TODO Auto-generated method stub
		
	}

	private void listDevice(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Device> listDevice = deviceDao.listAllDevices();
		request.setAttribute("listDevice", listDevice);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeviceList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeviceForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Device existingDevice = deviceDao.getDevice(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("DeviceForm.jsp");
		request.setAttribute("device", existingDevice);
		dispatcher.forward(request, response);

	}

	private void insertDevice(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String name = request.getParameter("name");
		int GPIOnumber = Integer.parseInt(request.getParameter("GPIOnumber"));
		String type = request.getParameter("type");

		Device newDevice = new Device(name, GPIOnumber, type);
		deviceDao.insertDevice(newDevice);
		response.sendRedirect("list");
	}

	private void updateDevice(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int GPIOnumber = Integer.parseInt(request.getParameter("GPIOnumber"));
		String type = request.getParameter("type");

		Device device = new Device(id, name, GPIOnumber, type);
		deviceDao.updateDevice(device);
		response.sendRedirect("list");
	}

	private void deleteDevice(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Device device = new Device(id);
		deviceDao.deleteDevice(device);
		response.sendRedirect("list");

	}

}
