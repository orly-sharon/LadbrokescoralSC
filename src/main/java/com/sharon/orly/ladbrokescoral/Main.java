package com.sharon.orly.ladbrokescoral;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
	
		Driver driver = new Driver();
		try {

			driver.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
