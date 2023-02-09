package td.tower;

import td.Block;
import td.Game;
import td.monster.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Catapult
 *
 * A catapult works in the following way. It will target on
 * one monster among all monsters that are in range. When there
 * are more than one monsters in range, pick ANY monster with
 * highest remaining health point.
 *
 * Then, it hits the target monster and other monsters located in
 * its 8 neighthor adjacent cells. For example,
 * ----------------------
 * | a | b | c | e |
 * | d | f | g | h |
 * | i | j | k | l |  ...
 * | m | n | o | p |
 * ----------------------
 * * If g is the target monster, monsters <b, c, e, f, g, h, j, k, l>
 * will receive damage.
 * * If m is the target monster, monsters <i, j, m, n>
 * will receive damage.
 *
 * Note: In the first example, even if monster b is out of the range
 * of the Tower, as long as Tower can hit g, b will also receive damage.
 *
 * Propoerty of Catapult:
 * Symbol : 'C'
 * Inital power: 4
 * Range : 6
 * cost : 7
 * upgrade power: 2
 * upgrade cost: 3
 *
 */
public class CatapultTower extends Tower {
    public CatapultTower(int row, int col, int power, int cost, int upgradeCost,int upgradePower, int range){
        super(row, col, 4, 7,3,2,6);
    }

    public char getSymbol(){
        return 'C';
    } //returns symbol



    public void action(List<Block>blocks) {
        List<Monster>monsters = new ArrayList<>();
        for(int i = 0; i < blocks.size(); i++) { // Filtering out the monsters from the block array and putting it in monsters array
            if (blocks.get(i) instanceof Monster)
                monsters.add(((Monster) blocks.get(i)));
        }

//
        for(int i = 0; i < monsters.size(); i++) {
            for (int j = 0; j < monsters.size(); j++) {
                if (monsters.get(i).getLife() > ((monsters.get(j)).getLife()) && isInRange(monsters.get(i))) { // the monster that has the highest life gets killed
                    monsters.get(i).reduceTheLifeOfMonster(getPower());

                }

                if (monsters.get(i).getCol() == monsters.get(j).getCol() + 1
                        && monsters.get(i).getRow() == monsters.get(j).getRow() && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent right cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getCol() == monsters.get(j).getCol() - 1
                        && monsters.get(i).getRow() == monsters.get(j).getRow() && isInRange(monsters.get(i))) { //checks if there is a monster in adjacent left cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() + 1
                        && monsters.get(i).getCol() == monsters.get(j).getCol() && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent down cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() - 1
                        && monsters.get(i).getCol() == monsters.get(j).getCol() && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent upper cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() + 1 &&
                        monsters.get(i).getCol() == monsters.get(j).getCol() - 1 && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent lower-left cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() + 1 &&
                        monsters.get(i).getCol() == monsters.get(j).getCol() + 1 && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent lower-right cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());
                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() - 1 &&
                        monsters.get(i).getCol() == monsters.get(j).getCol() - 1 && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent upper-left cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
                if (monsters.get(i).getRow() == monsters.get(j).getRow() - 1 &&
                        monsters.get(i).getCol() == monsters.get(j).getCol() + 1 && isInRange(monsters.get(i))) { // checks if there is a monster in adjacent upper-right cell

                    monsters.get(j).reduceTheLifeOfMonster(getPower());

                }
            }
        }
















    }


}
