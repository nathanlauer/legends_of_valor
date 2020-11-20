package main.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.attributes.Position;
import main.legends.Hero;
import main.legends.LegendList;
import main.utils.GetUserCommand;
import main.utils.GetUserListNumericalInput;
import main.utils.GetUserNumericInput;
import main.utils.Output;
import main.utils.UserCommand;

public class ValorWorldInteraction extends WorldInteraction{
	private final ValorWorld valorWorld;
	private List<Hero> chosenHeroes;
	
	public ValorWorldInteraction(ValorWorld valorWorld) {
		// TODO Auto-generated constructor stub
		super(valorWorld);
		this.valorWorld = valorWorld;
		chosenHeroes = LegendList.getInstance().getChosenHeroes();
	}
	
	public void run() {
        boolean finished = false;
        while(!finished) {
        	for(Hero hero: chosenHeroes) {
	            Output.drawWorld(valorWorld);
	            UserCommand command = new GetUserCommand().run();
	            switch (command) {
	                case INFO:
	                    Output.displayNominalInformation(LegendList.getInstance().getChosenHeroes());
	                case UP:
	                    attemptMove(hero, Direction.UP);
	                    break;
	                case DOWN:
	                    attemptMove(hero, Direction.DOWN);
	                    break;
	                case LEFT:
	                    attemptMove(hero, Direction.LEFT);
	                    break;
	                case RIGHT:
	                    attemptMove(hero, Direction.RIGHT);
	                    break;
	                case TELEPORT:
	                	attemptMove(hero, Direction.TELEPORT);
	                	break;
	                case BACK:
	                	attemptMove(hero, Direction.BACK);
	                	break;
	                case QUIT:
	                    finished = true;
	                    break;
	                default:
	                    throw new RuntimeException("Unknown command!");
	            }
        	}
        }
        System.out.println("Thanks for playing!");
    }
	
	 private void attemptMove(Hero hero, Direction direction) {//same as super() except for adding Hero hero as parameter;
	        String failure = "Unable to move " + direction + "! Please enter a different move.";
	        if(valorWorld.canMove(hero, direction)) {
	            try {
	                valorWorld.move(hero, direction);
	            } catch (InvalidMoveDirection e) {
	                System.out.println(failure);
	            }
	        } else {
	            System.out.println(failure);
	        }
	 }
}
