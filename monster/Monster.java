package td.monster;

import td.Block;
import java.util.List;

import td.tower.ArcheryTower;
import td.tower.CatapultTower;
import td.tower.LaserTower;
import td.tower.Tower;
/**
 * Monster class.
 *
 * On each turn it moves to the right by one cell. If it steps on
 * a tower (i.e., share the same coordinate as the tower), the tower
 * will be destroyed.
 *
 * A monster reaches home will end the game.
 *
 * If a monster has health point 0 or negative, it cannot move anymore
 * and shall be removed from the game.
 *
 * A monster shall return the last digit of its health as its symbol.
 * For example, if a monster has a health 10, it should return the character '0'
 * If a monster has a health of 31, it should return the character '1'
 *
 *
 * There are some methods to be included in this class. Yet, you need to
 * deduce what methods are needed from other java files.
 *
 *
 */
public class Monster extends Block {

    private int life;
    //private char symbol; use the getSymbol method from Blocks class.
    // need a method from the tower classes that will return the coordinates of the towers
    // need a method from the game that will keep track of the turn. Because after each turn the methods will be implemented.
    public Monster(int row, int col, int life){
        super(row, col);
        this.life = life;

    }
    public void moveMonster(List<Block>blocks){ //moves monster to the right and checks if it should be removed
        this.col += 1;
        removeMonster(blocks);



    }

    public void reduceTheLifeOfMonster(int damage){ // reduces the life of monster
        life -= damage;

    }


    public void removeMonster(List<Block>blocks){
        // removes the towers if monsters coordinate equals the towers
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i) instanceof Tower) {
                if (blocks.get(i) instanceof ArcheryTower)
                    if (this.row == blocks.get(i).getRow() && this.col == blocks.get(i).getCol())
                        blocks.get(i).remove();
                if (blocks.get(i) instanceof LaserTower)
                    if (this.row == blocks.get(i).getRow() && this.col == blocks.get(i).getCol())
                        blocks.get(i).remove();
                if (blocks.get(i) instanceof CatapultTower)
                    if (this.row == blocks.get(i).getRow() && this.col == blocks.get(i).getCol())
                        blocks.get(i).remove();
            }

        }

       if(this.life == 0 | this.life < 0) // removes monster if the health point is zero or negative
           this.remove();


    }
    public int getLife(){ // returns life of monster
        return life;
    }


    public String toString(){ // used to print monster info in option1 of console display
        return "Monster: " + getSymbol() + " " + "[" + life + "]";
    }
    public char getSymbol(){
        String lifeInString = Integer.toString(life); // converted integer life to a string
        return lifeInString.charAt(lifeInString.length() - 1); // takes the last digit
    }
    public void action(List<Block>blocks){
            moveMonster(blocks);
    }
}
