package td.tower;

import td.Block;
import td.Game;
import td.monster.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Laser Tower
 *
 * A Laser tower can shoot all monsters on its left (whose has a
 * smaller col than the tower) on the same row.
 *
 * All monster will receive the same number of damage.
 *
 * Propoerty of laser tower:
 * Symbol : 'L'
 * Inital power: 4
 * Range : N/A (you can place any value here)
 * cost : 7
 * upgrade power: 2
 * upgrade cost: 3
 */
public class LaserTower extends Tower {


    public LaserTower(int row, int col, int power, int cost, int upgradeCost, int upgradePower, int range) {
        super(row, col, 4, 7, 3, 2, 0);
    }

    public char getSymbol() {
        return 'L';
    } // return symbol


    public void action(List<Block> blocks) {
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) { // Filters out the monsters from the block array and putting it in monsters array
            if (blocks.get(i) instanceof Monster)
                monsters.add((Monster) blocks.get(i));
        }
        for(int i = 0; i < blocks.size(); i++){ // kills monsters
            if(blocks.get(i) instanceof Monster){
                if(blocks.get(i).getCol() < col && blocks.get(i).getRow() == row){
                    ((Monster) blocks.get(i)).reduceTheLifeOfMonster(getPower());
                }
            }
        }



    }
}






