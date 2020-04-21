package it.polimi.ingsw.util.observers;

// necessary imports from other packages of the project
import it.polimi.ingsw.util.exceptions.WrongPhaseException;

// necessary imports of Java SE
import java.util.ArrayList;
import java.util.List;

public class ObservableObject {
	private List<ObserverObject> observers;

	// constructors
	public ObservableObject() {
		observers = new ArrayList<>();
	}

	// modifiers and updaters
	public void addObserver(ObserverObject obs) throws IllegalArgumentException {
		if (observers.contains(obs)) {
			throw new IllegalArgumentException();
		}

		observers.add(obs);
	}
	public void removeObserver(ObserverObject obs) throws IllegalArgumentException {
		if (!observers.contains(obs)) {
			throw new IllegalArgumentException();
		}

		observers.remove(obs);
	}
	public void notifyColors(Object playerColors) throws NullPointerException {
		if (playerColors == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updateColors(playerColors);
		}
	}
	public void notifyGods(Object playerGods) throws NullPointerException {
		if (playerGods == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updateGods(playerGods);
		}
	}
	public void notifyPositions(Object netMap, boolean finished) throws NullPointerException {
		if (netMap == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updatePositions(netMap,finished);
		}
	}
	public void notifyMove(Object netMap) throws NullPointerException {
		if (netMap == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updateMove(netMap);
		}
	}
	public void notifyBuild(Object netMap) throws NullPointerException {
		if (netMap == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updateBuild(netMap);
		}
	}
	public void notifyQuit(String playerName) throws NullPointerException {
		if (playerName == null) {
			throw new NullPointerException();
		}
		for (ObserverObject obs : observers) {
			obs.updateQuit(playerName);
		}
	}
}
