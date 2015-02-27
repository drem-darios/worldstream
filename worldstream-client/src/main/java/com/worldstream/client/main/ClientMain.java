package com.worldstream.client.main;

import java.util.logging.Logger;

import com.worldstream.client.Client;
import com.worldstream.client.actions.PublishAction;
import com.worldstream.client.actions.SubscribeAction;
import com.worldstream.client.conn.Connection;

/**
 * @author drem
 */
public class ClientMain {
	private static final Logger Log = Logger.getLogger(ClientMain.class.getSimpleName());
	private static Connection conn = new Connection("localhost", 8080);
    private static Client client;
    

	public static void main(String args[]) {
		client = new Client(conn);
		int mode = Integer.valueOf(args[0]);
		if (mode == 0) {
			Log.info("Handling publish action");
			new Publisher().start();
		} else {
			new Subscriber().start();
			Log.info("Handling subscribe action");
			
		}
	}

	private static class Publisher extends Thread {
		@Override
		public void run() {
//			while(true) {
//				
//				try {
//					Thread.sleep(1000);	
//				} catch(Exception e) {
//					
//				}
//				
//			}
			client.handleAction(new PublishAction());
		}
	}
	
	private static class Subscriber extends Thread {
		@Override
		public void run() {
//			while(true) {
//				
//				try {
//					Thread.sleep(3000);
//				}catch (Exception e) {
//					
//				}	
//			}
			client.handleAction(new SubscribeAction());
		}
	}
}
