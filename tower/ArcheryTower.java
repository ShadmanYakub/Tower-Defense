package td.tower;

import java.util.ArrayList;
import java.util.List;
import td.monster.Monster;
import td.Block;

/**
 * Archery
 *
 * The archery tower will aim only one monster that has positive, non-zero
 * health point. If there are multiple monster that are in range,
 * pick the one that is nearest to "home".
 *
 * Property of Archery tower:
 * Symbol : 'A'
 * Initial power: 5
 * Range : 3
 * cost : 5
 * upgrade power: 1
 * upgrade cost: 2
 */
public class ArcheryTower extends Tower {


    public ArcheryTower(int row, int col, int power, int cost, int upgradeCost,int upgradePower, int range){
        super(row,col, 5, 5, 2,1,3);

    }
    public char getSymbol(){
        return 'A';
    } // returns symbol



    @Override
    public void action(List<Block> blocks) {

        List<Monster> monsters = new ArrayList<>(); // filters out the monsters from the block arraylist and adds to the monsters arraylist
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof Monster)
                monsters.add((Monster) blocks.get(i));
        }



        for (int i = 0; i < blocks.size(); i++) { // if one monster in range

            if (countMonsters(blocks) == 1 && isInRange(blocks.get(i)) && blocks.get(i) instanceof Monster) {
                ((Monster) blocks.get(i)).reduceTheLifeOfMonster(getPower());
            }
        }



       for(int i = 0; i < monsters.size(); i++) { // if more than one monster in range
           for (int j = 0; j < monsters.size(); j++) {
               if (countMonsters(blocks) > 1 && moreThanOneInRange(blocks)) {
                   if (distanceFromHome(monsters.get(i)) < distanceFromHome(monsters.get(j))) {
                       monsters.get(i).reduceTheLifeOfMonster(getPower());
                   }
               }
           }
       }






    }








    public boolean moreThanOneInRange(List<Block>blocks){ // checks if there are more than one monster in range
        List<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) instanceof Monster)
                monsters.add((Monster) blocks.get(i));
        }
        int cnt = 0;
        for(int i = 0; i < monsters.size(); i++){
                if(isInRange(monsters.get(i))){
                    cnt++;
                }
            }

        return cnt > 1;

    }
    public int countMonsters(List<Block>blocks){ // counts the monsters in the block arrayList
        int cnt = 0;
        for(int i = 0; i < blocks.size(); i++){
            if(blocks.get(i) instanceof Monster)
                cnt++;
        }
        return cnt;
    }



        




}
