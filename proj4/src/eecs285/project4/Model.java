package eecs285.project4;

import static eecs285.project4.Constants.*;

import java.util.HashSet;

/**
 * Model houses the information of all tanks and bullets.
 * In addition, Model notifies the View how the map should be drawn.
 * It will keep the View updated when tanks move around, fire, "die", etc.
 */
public class Model {
    private HashSet<AITank> AITanks;
    private Tank playerTank;
    private int[][] map;
    private View view;

    /**
     * Create a bunch of AI tanks and one player tank.
     */
    public Model() {
        AITanks = new HashSet<AITank>();
        for(int i = 0; i < 3; ++i) {
            AITanks.add(new AITank(5, 5, 5, this));
        }
        playerTank = new PlayerTank(5, 5, 5, this);
        map = new int[MAP_SIZE][MAP_SIZE];
        map[0][0] = PLAYER_TANK;
    }

    /**
     * Attach the specified view to the model 
     * and notify the view about the locations of all tanks
     */
    public void attach(View view) {
        this.view = view;
        for(Tank tank : AITanks) {
            view.addTank(tank);
        }
        view.addTank(playerTank);
    }

    /**
     * Let each AI Tank update themselves.
     */
    public void go() {
        for(AITank tank : AITanks) {
            tank.go();
        }
    }

    /**
     * Determine whether a tank can move to a specific location.
     * Return true if the move is valid, and update the map to reflect the new locatio.
     * Return false if the move is not valid.
     * If the move is valid, notify the view about the location change of a tank.
     */
    public synchronized boolean notifyLocation(Tank tank, final int direction) {
        int row = tank.getRow(), column = tank.getColumn();
        int number = tank.getNumber();
        switch(direction) {
            case UP:
                // the tank can safely move up if its y-coordinate
                // is greater than 0
                if(row > 0) {
                    map[row][column] = 0;
                    map[row - 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case DOWN:
                if(row < (NUM_BLOCKS - 1) * BLOCK_SIZE) {
                    map[row][column] = 0;
                    map[row + 1][column] = number;
                    view.repaint();
                    return true;
                }
                break;
            case LEFT:
                if(column > 0) {
                    map[row][column] = 0;
                    map[row][column - 1] = number;
                    view.repaint();
                    return true;
                }
                break;
            case RIGHT:
                if(column < (NUM_BLOCKS - 1) * BLOCK_SIZE) {
                    map[row][column] = 0;
                    map[row][column + 1] = number;
                    view.repaint();
                    return true;
                }
                break;
            default:
                assert(false);
        }
        return false;
    }
}
