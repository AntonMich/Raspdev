package com.rpi.device.led;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SimplePin {
	private int GPIO;
	private String name;
	private static GpioPinDigitalOutput pin;

	public SimplePin(int gPIO, String name) {
		super();
		GPIO = gPIO;
		this.name = name;
	}
	public void onPin() throws InterruptedException{
		getPin().high();
		Thread.sleep(3000);
	}
	public void offPin() throws InterruptedException{
		getPin().low();
		Thread.sleep(3000);
	}

	public static GpioPinDigitalOutput getPin() {
		if (pin==null){
			GpioController gpio=GpioFactory.getInstance();
			pin=gpio.provisionDigitalOutputPin(RaspiPin.GPIO_17, "MyLed", PinState.LOW);
		}
		return pin;
	}
	
}
