package it.polimi.ingsw;

// necessary imports from other packages of the project
import it.polimi.ingsw.network.Server;

/**
 * This class is the base App class for servers, it starts the server as
 * an object, the server stand up for the whole execution.
 */
public class ServerApp {
	public static void main(String[] args) {
		new Thread(new Server()).start();
	}
}