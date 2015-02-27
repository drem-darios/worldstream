package com.worldstream.api.server.thread;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Logger;

import com.worldstream.api.server.builder.Builder;
import com.worldstream.api.server.builder.CommandBuilder;
import com.worldstream.api.server.command.Command;
import com.worldstream.api.server.conn.ServerStream;

/**
 * @author Drem Darios
 */
public class WorkerThread implements Runnable {

	private static final Logger logger = Logger.getLogger(WorkerThread.class
			.getName());
	private ServerStream stream;

	public WorkerThread(Socket sock) {
		try {
			stream = new ServerStream(sock);
			stream.openStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			processClientCommand();
			stream.closeStream();
		} catch (IOException e) {
			logger.severe("Error: " + e.getStackTrace());
		}
	}

	private void processClientCommand() throws IOException {
		String request = stream.read();
		logger.info("*** PROCCESSING COMMANDS ***");
		Builder<Command> builder = new CommandBuilder();
		Command command = builder.build(request);
		logger.info("Command: " + command.getCommandName());
		logger.info("Request: " + request);
		String result = command.processCommand();
		logger.info("Result: " + result);
		stream.write(result);

		processClientCommand();
	}
}
