package creatures;

import huglife.Creature;
import huglife.Action;
import huglife.Direction;
import huglife.Occupant;

import java.util.Map;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;


public class Clorus extends Creature {

    private int r;

    private int b;

    private int g;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        b = 0;
        g = 0;
        energy = e;
    }

    public Color color() {
        r = 34;
        b = 231;
        return color(r, g, b);
    }

    public void move() {
        energy -= 0.03;
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public Creature replicate() {
        Clorus baby = new Clorus(energy*0.5);
        energy *= 0.5;
        return baby;
    }

    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> neighborsCreature = new ArrayDeque<>();
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyNeighbors.addFirst(d);
            } else if (neighbors.get(d).name().equals("plip")){
                neighborsCreature.addFirst(d);
            }
        }

        // case 1
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        //case 2
        else if (!neighborsCreature.isEmpty()){
            int plipNum = neighborsCreature.size();
            double prob = 1.0 / plipNum;
            double rand = Math.random();
            int i = 0;
            for (Direction _d : neighborsCreature) {
                if (rand>=i*prob && rand < (i+1)*prob) {
                    return new Action(Action.ActionType.ATTACK, _d);
                }
                i++;
            }
        }

        //case 3
        else if (energy > 1) {
            double prob = 1.0 / emptyNeighbors.size();
            double rand = Math.random();
            int i = 0;
            for (Direction _d : emptyNeighbors) {
                if (rand>=i*prob && rand < (i+1)*prob) {
                    return new Action(Action.ActionType.REPLICATE, _d);
                }
                i++;
            }
        }

        //case 4
        else {
            double prob = 1.0 / emptyNeighbors.size();
            double rand = Math.random();
            int i = 0;
            for (Direction _d : emptyNeighbors) {
                if (rand>=i*prob && rand < (i+1)*prob) {
                    return new Action(Action.ActionType.MOVE, _d);
                }
                i++;
            }
        }
        return new Action(Action.ActionType.STAY);
    }

}

