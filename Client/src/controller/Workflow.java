/**
 * Initiate the program and manage the flow of the program. Contains a help method for the other classes to send objects to the server.
 * 
 * @author David Stromner
 * @version 2013-02-28
 */

package controller;

import model.Communication;
import model.schedule.ScheduleHandler;
import view.JSchedule;
import view.Window;

public class Workflow {
	private static Workflow workflow;
	private Communication communication;
	private Window window;
	private JSchedule jSchedule;
	private ScheduleHandler scheduleHandler;

	/**
	 * Set up all classes that workflow needs to manage
	 */
	private Workflow() {
		communication = new Communication();
		scheduleHandler = new ScheduleHandler();
		scheduleHandler.addObserver(communication);
		//jSchedule = new JSchedule();
		new Thread() {
			public void run() {
				window = new Window();
				window.setView("LoginGUI");
				// TODO Better solution to being able to call
				// communication.addObserver(this).
				window.addObserver(communication);
			}
		}.start();
	}

	public static Workflow getInstance() {
		if (workflow == null) {
			workflow = new Workflow();
		}

		return workflow;
	}
	
	/**
	 * @return instance of the window.
	 */
	public Window getWindow(){
		return window;
	}
	
	/**
	 * @return instance of communication.
	 */
	public Communication getCommunication(){
		return communication;
	}
	
	/**
	 * @return instance of JSchedule
	 */
	public JSchedule getJSchedule(){
		return jSchedule;
	}
	
	/**
	 * @return instance of ScheduleHandler
	 */
	public ScheduleHandler scheduleHandler(){
		return scheduleHandler;
	}
}