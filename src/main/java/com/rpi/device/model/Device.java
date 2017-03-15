package com.rpi.device.model;

public class Device {
	protected int id;
	protected String name;
	protected int GPIOnumber;
	protected String type;
	protected int status;
	
	public Device() {
	}
	public Device(int id) {
		super();
		this.id=id;
		
	}
	public Device(int id, String name, int gPIOnumber, String type, int status) {
		super();
		this.id = id;
		this.name = name;
		GPIOnumber = gPIOnumber;
		this.type = type;
		this.status=status;
	}
	public Device(String name, int gPIOnumber, String type, int status) {
		super();
		this.name = name;
		GPIOnumber = gPIOnumber;
		this.type = type;
		this.status=status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGPIOnumber() {
		return GPIOnumber;
	}
	public void setGPIOnumber(int gPIOnumber) {
		GPIOnumber = gPIOnumber;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", GPIOnumber=" + GPIOnumber + ", type=" + type + "]";
	}

}
