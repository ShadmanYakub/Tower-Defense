package td;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import td.monster.Monster;
import td.tower.*;

/**
 * A View class. To display the information on screen and also
 * to take user's control.
 */
public class ConsoleDisplay implements Displayable {
    /**
     * The controller object game.
     */
    protected Game game;




    /**
     * Entry point. Don't touch
     */
    public static void main(String[] args) {
        new ConsoleDisplay();
    }

    /**
     * Constructor. To construct the game object and call game.run();
     */


    public ConsoleDisplay() {
        this.game = new Game(this);
        game.run();

    }

    /**
     * To display the score, money, map and character on screen.
     */
    @Override
    public void display() {


        System.out.println("Score: " + game.getScore() + " |  Money: " + game.getMoney());
        System.out.println("----------------");



        for(int i = 0; i < Game.HEIGHT; i++){
            for(int j = 0; j < Game.WIDTH; j++){
                    if (game.getBlockByLocation(i,j) != null) // if no monster or tower then print blank
                        System.out.print(game.getBlockByLocation(i,j).getSymbol()); // otherwise print symbol
                    else
                        System.out.print(' ');



            }
            System.out.print("oooo");
            System.out.println();
        }



    }
    /**
     * To accept user input (build tower, upgrade tower, view blocks).
     *
     * This method has been done for you.
     * You should not modify it.
     * You are not allowed to modify it.
     */
    @Override
    public void userInput() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            printInstruction();
            try {
                switch (scanner.nextInt()) {
                    case 1:
                        option1();
                        break;
                    case 2:
                        option2();
                        break;
                    case 3:
                        option3();
                        break;
                    case 4:
                        return;
                    default:
                        throw new InvalidInputException("Invalid option! Pick only 1-4");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            display();
        }
    }

    /**
     * Given method.
     *
     * You are not supposed to change this method.
     * But you can change if you wish.
     */
    private void printInstruction() {
        System.out.println("Please pick one of the following: ");
        System.out.println("1. View a tower/monster");
        System.out.println("2. Build a new Tower");
        System.out.println("3. Upgrade a Tower");
        System.out.println("4. End a turn");
    }





    public void option1(){

        Scanner in = new Scanner(System.in);
        System.out.println("Enter the coordinate of the tower/monster row followed by column ");
        try{
            int row = in.nextInt();
            int col = in.nextInt();

            System.out.println("----------------");
            if (game.getBlockByLocation(row, col) != null) {
                for (int i = 0; i < Game.HEIGHT; i++) {
                    for (int j = 0; j < Game.WIDTH; j++) {
                        if (game.getBlockByLocation(i, j) != null) // if existing monster or tower then print symbol
                            System.out.print(game.getBlockByLocation(row, col).getSymbol());
                        if (game.getBlockByLocation(row, col) instanceof Tower) {
                            if (game.getBlockByLocation(row, col) instanceof ArcheryTower) {
//
                                if (Math.abs(i - row) + Math.abs(j - col) <= ((ArcheryTower) game.getBlockByLocation(row, col)).getRange())
                                    System.out.print('#'); // if in range then print #
                                else
                                    System.out.print(' '); // else print blank
//
                            } else if (game.getBlockByLocation(row, col) instanceof Tower) {
                                if (game.getBlockByLocation(row, col) instanceof CatapultTower) {
                                    if (game.getBlockByLocation(i, j) != null) // if no monster or tower then print blank
                                        System.out.print(game.getBlockByLocation(row, col).getSymbol());
                                    if (Math.abs(i - row) + Math.abs(j - col) <= ((CatapultTower) game.getBlockByLocation(row, col)).getRange())
                                        System.out.print('#');
                                    else
                                        System.out.print(' ');
                                }
                            } else if (game.getBlockByLocation(row, col) instanceof Tower) {
                                if (game.getBlockByLocation(row, col) instanceof LaserTower) {
                                    if (game.getBlockByLocation(i, j) != null) // if no monster or tower then print blank
                                        System.out.print(game.getBlockByLocation(row, col).getSymbol());

                                    if (i == row && j < col)
                                        System.out.print('#');
                                    else
                                        System.out.print(' ');
                                }
                            }


                        }
                        System.out.print("oooo");
                        System.out.println();
                    }
                }




            }

            for(int i = 0; i < game.blocks.size(); i++){
                if(game.blocks.get(i).getRow() == row && game.blocks.get(i).getCol() == col) {
                    if (game.blocks.get(i) instanceof Tower) { // if tower then print tower's toString method
                        System.out.println(game.blocks.get(i).toString());

                    }

                    if (game.blocks.get(i) instanceof Monster)
                        System.out.println(game.blocks.get(i).toString()); // if monster then printsMonster's toString method

                }
                else
                    System.out.println("null");

            }




//


        }catch (Exception e){
            System.out.println("null");

        }





    }
    public void option2(){
        Scanner in = new Scanner(System.in);
        System.out.println("You can build the following towers:");
        System.out.println("1. Archer Tower($5); 2. Laser Tower($7); 3. Catapult Tower($7)");
        int option = in.nextInt();
        try {
            System.out.println("Which row?");
            int row = in.nextInt();
            System.out.println("Which column?");
            int col = in.nextInt();

            if (option == 1)
                if (game.build(option, row, col)) { // if true then builds  archery tower
                    game.blocks.add(new ArcheryTower(row, col, 5, 5, 2, 1, 3));

                }


            if (option == 2)
                if (game.build(option, row, col)) { // if true then builds  archery tower
                    game.blocks.add(new LaserTower(row, col, 4, 7, 3, 2, 0));

                }



            if (option == 3)
                if (game.build(option, row, col)) { // if true then builds  archery tower
                    game.blocks.add(new CatapultTower(row, col, 4, 7, 3, 2, 6));

                }


            }catch(InputMismatchException e){
                System.out.println("null");
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("Sorry, the option is invalid. Please check if you have enough money. You can " +
                        "only build on a cell without any tower or monster. You cannot build on column 0 too!");
            }


    }
    public void option3(){
        int row = 0;
        int col = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the row of thw tower?");
        try {
             row = in.nextInt();
        }catch (InputMismatchException e){
            System.out.println("null");
        }
        System.out.println("Enter the column of the tower?");
        try {
            col = in.nextInt();
        }catch (InputMismatchException e){
            System.out.println("null");
        }
        for(int i = 0; i < game.blocks.size(); i++){
            if(game.blocks.get(i).getRow() == row && game.blocks.get(i).getCol() == col)
                if(game.blocks.get(i) instanceof ArcheryTower){
                    if(game.upgrade(row, col)) //if upgrade is true
                        ((ArcheryTower) game.blocks.get(i)).upgrade(); // upgrades the power of the archery tower


                } else if (game.blocks.get(i) instanceof  LaserTower) {
                    if(game.upgrade(row, col)) // if upgrade is true
                        ((LaserTower) game.blocks.get(i)).upgrade(); // upgrades the power of the laser tower

                } else if (game.blocks.get(i) instanceof  CatapultTower) {
                    if(game.upgrade(row,col)) //if upgrade is true
                        ((CatapultTower) game.blocks.get(i)).upgrade(); // upgrades the power of the catapult tower

                }

        }

    }
    public void gameOver(){

        System.out.println("Game Over!");

    }

}
