package com.gerkenip.rgp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.gerkenip.rgp.topology.RubeGoldbergPrinterTopology;

public class AlarmClock {

	private long duration;
	private long interval;
	
	public AlarmClock(long duration, long interval) {
		this.duration = duration;
		this.interval = interval;
	}
	
	public void go() {
		
		long completion = System.currentTimeMillis() + duration;
		long naps = duration / interval;
		
		for (long nap = 0; nap < (naps-1); nap++) {
			try { Thread.sleep(interval); } catch (InterruptedException e) { } 
			long left = completion - System.currentTimeMillis();
			long min = left / 60000;
			System.out.println("*");
			System.out.println("* Alarm Clock: "+min+" min remaining at "+(new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss").format(new Date())));
			System.out.println("*");
			if (RubeGoldbergPrinterTopology.quiesce) { 
				nap = naps; 
				completion = System.currentTimeMillis();
			}
		}
		
		long left = completion - System.currentTimeMillis();
		if (left > 0) {
			try { Thread.sleep(left); } catch (InterruptedException e) { } 
		}
		
	}
	
	public static void main(String[] args) {
		new AlarmClock(120000, 15000).go();
	}

}
