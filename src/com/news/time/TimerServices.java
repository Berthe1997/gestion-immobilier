package com.news.time;

import java.util.Date;
import java.util.TimerTask;

public class TimerServices extends TimerTask {

	public void run() {
		try {
	     
			System.out.println("L'execution � cette heure est tr�s importante : " + new Date());

	    } catch (Exception ex) {
	        System.out.println("error running thread " + ex.getMessage());
	    }
	}

}
