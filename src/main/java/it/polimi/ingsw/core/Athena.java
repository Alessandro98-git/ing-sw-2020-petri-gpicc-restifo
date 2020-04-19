package it.polimi.ingsw.core;

import it.polimi.ingsw.exceptions.NoBuildException;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Athena implements GodCard, Observer {
	private int typeGod = 1;
	private Player owner;
	int numPlayer = 4;
	String name = "Athena";
	String description = "Opponent’s Turn: If one of your Workers moved up on your last turn, opponent Workers cannot move up this turn.";
	List<Move> moves;
	List<Build> builds;

	public Athena(Player player) { this.owner = player; }

	public Athena(){
		this.owner = null;
		this.moves = null;
		this.builds = null;
	}

	public int getNumPlayer(){
		return numPlayer;
	}
	public Player getOwner(){
		return owner;
	}
	public int getTypeGod(){
		return typeGod;
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}

	private boolean wentUp = false;

	//Overridden update method of the Observer
	public void update(Observable obs, Object arg){
		Worker modifiedWorker = (Worker) obs;
		Cell[] positions = (Cell[]) arg;
		if(modifiedWorker == owner.getWorker1() || modifiedWorker == owner.getWorker2()){	//Then the worker returned as argument of the update method is held by the owner of Athena
			if(positions[1].getBuilding().getLevel() - positions[0].getBuilding().getLevel() >= 1) wentUp = true;
			else wentUp = false;
		}
	}

	/**
	 * @throws NoBuildException so that controller knows it must use the default action
	 */
	public List<Build> checkBuild(Map m, Worker w, int type) throws NoBuildException {
		throw new NoBuildException();
	}

	/**
	 * @param m represents the map
	 * @param w represents the worker moved by the player during this turn
	 * @param type represents the typeMove of this particular GodCard: 0 stands for a "simple move", 1 for a "conditioned move", 2 for a "defeat move", 3 for a "victory move"
	 * @return the cells where the Player's Worker can't move up
	 */
	public List<Move> checkMove(Map m, Worker w, int type){
		int myHeight = w.getPos().getBuilding().getLevel();
		moves = new ArrayList<>();

		if(wentUp){		//Athena's power's applied only if wentUp flag is true
			for(int x1 = 0; x1 <= 4; x1++){   //x1, y1 all the cells where I mustn't move: I have to check the whole map and return all the cells higher than mine for 1 or more blocks
				for(int y1 = 0; y1 <= 4; y1++){
					if(m.getCell(x1, y1).getBuilding().getLevel() - myHeight >= 1) moves.add(new Move(2, w.getPos(), m.getCell(x1, y1), w));
				}
			}
		}

		return moves;
	}
}