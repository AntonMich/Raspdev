package com.rpi.device.led;

import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class SimplePin {
	private int GPIO;
	private String name;

	public SimplePin(int gPIO, String name) {
		super();
		GPIO = gPIO;
		this.name = name;
	}

	public void startAction() throws InterruptedException {

		// provision gpio pin #01 as an output pin and turn on
		RaspiPin rp=new RaspiPin();
		Pin myPin=rp.getPinByAddress(GPIO);

		final GpioPinDigitalOutput pin = GPIOController.gpio.provisionDigitalOutputPin(myPin, name, PinState.HIGH);

		// set shutdown state for this pin
		pin.setShutdownOptions(true, PinState.LOW);

		System.out.println("--> GPIO state should be: ON"+myPin.getName());

		Thread.sleep(5000);

		// turn off gpio pin #01
		pin.low();
		System.out.println("--> GPIO state should be: OFF");

		Thread.sleep(5000);
	}
}
