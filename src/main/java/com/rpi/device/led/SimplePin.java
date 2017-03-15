package com.rpi.device.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.rpi.device.model.Device;

public class SimplePin {
	private static GpioPinDigitalOutput pin;
	
	public static void actionPin(Device device){
		if (device.getStatus()==0){
			getPin(device).low();
		} else {getPin(device).high();}
	}
	
	private static GpioPinDigitalOutput getPin(Device device) {
		if (pin==null){
			GpioController gpio=GpioFactory.getInstance();
			pin=gpio.provisionDigitalOutputPin(RaspiPin.getPinByAddress(device.getGPIOnumber()),
					"MyLed", PinState.LOW);
		}
		return pin;
	}
	
}
