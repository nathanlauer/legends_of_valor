package main.games;

import main.Runner;
import main.attributes.Position;
import main.fight.Attack;
import main.fight.FightMove;
import main.fight.GetUserFightMove;
import main.fight.InvalidFightMoveException;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;
import main.utils.*;
import main.world.Direction;
import main.world.InvalidMoveDirection;
import main.world.ValorWorld;
import main.world.World;

import java.util.*;

/**
 * Class LegendsOfValorTurn implements TurnExecutor, and is therefore a TurnBasedGame,
 * and represents a single move within the game Legends of Valor. Within a turn,
 * a Monster may attack a Hero or move forward, or a Hero may choose some type
 * of Move or fight action against a Monster in range.
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/17/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class LegendsOfValorTurn implements TurnExecutor {
    private Legend current;
    private boolean firstTurn;
    private ListIterator<Hero> heroIterator;
    private ListIterator<Monster> monsterIterator;
    private boolean finished;
    private boolean firstRound;

    /**
     * Standard constructor
     * @param heroes the Heroes playing in this Turn
     * @param monsters the Monsters playing in this Turn
     */
    public LegendsOfValorTurn(List<Hero> heroes, List<Monster> monsters, boolean firstRound) {
        heroIterator = heroes.listIterator();
        monsterIterator = monsters.listIterator();
        current = null; // will be set in setupNextTurn method
        firstTurn = true;
        finished = false;
        this.firstRound = firstRound;
    }

    /**
     * Resets this TurnExecutor to the beginning
     */
    @Override
    public void reset() {
        // Don't use this method for Valor, since Monsters are added along the way, and
        // we don't want to worry about concurrent modification exceptions.
        // Instead, the LegendsOfValorRound class should create a new instance of this
        // class for each round, passing in whichever Heroes and Monsters are
        // currently in existence.
        throw new RuntimeException("Don't use this method in Legends of Valor!");
    }

    /**
     * Indicates whether or not the current legend is a Monster
     * @return true if current is a Monster, false otherwise
     */
    private boolean currentIsMonster() {
        return current instanceof Monster;
    }

    /**
     * Indicates whether or not the current legend is a Hero
     * @return true is current is a Hero, false otherwise
     */
    private boolean currentIsHero() {
        return !currentIsMonster();
    }

    /**
     * Performs any required setup for the next turn.
     */
    @Override
    public void setupNextTurn() throws InvalidNextTurnException {
        if(firstTurn) {
            setupFirstTurn();
            firstTurn = false;
        } else {
            setupLaterTurns();
        }

        // And draw the World, so the user has a sense of what is going on
        Output.printSeparator();
        Output.drawWorld(Runner.getInstance().getWorld());
    }

    /**
     * Private helper function which sets up the first turn
     * @throws InvalidNextTurnException if no Heroes have any health power
     */
    private void setupFirstTurn() throws InvalidNextTurnException {
        boolean found = false;
        while(heroIterator.hasNext() && !found) {
            Hero hero = heroIterator.next();
            if(!hero.hasFainted()) {
                current = hero;
                found = true;
            }
        }
        if(!found) {
            throw new InvalidNextTurnException("No valid Hero for the first move!");
        }
        firstTurn = false;
    }

    /**
     * Private helper function which sets up a turn that is not the first turn.
     * Checks whether the previous Legend was a Monster or a Hero, and then
     * marks current as the next opposite Legend (e.g. if a Monster just went,
     * chooses the next Hero, and vise versa).
     *
     * There is a possibility that there are no valid Monsters in the World,
     * and hence, in such a situation, Heroes should move consecutively. For
     * example, it's plausible that all Monsters have been killed, and only
     * Heroes remain.
     *
     * In the semantics of Legends of Valor, however, the opposite is not possible.
     * Heroes are respawned in their Nexus as soon as they faint, and hence
     * there is not actually a possibility that there may be no Heroes on the board.
     */
    private void setupLaterTurns() {
        // Not the first turn. Check to see if a Hero or a Monster went previously.
        if(monsterShouldGoNext()) {
            monsterGoesNext();
        } else {
            heroGoesNext();
        }
    }

    /**
     * Indicates whether or not the next Turn should be taken by a Monster.
     * @return true if the last Legend to go was a Hero, false otherwise.
     */
    private boolean monsterShouldGoNext() {
        return currentIsHero();
    }

    /**
     * Helper function which handles the situation where a Monster is
     * supposed to go next. As noted in the above function, there is
     * a possible scenario where there are actually no living Monsters.
     */
    private void monsterGoesNext() {
        boolean found = false;
        while(monsterIterator.hasNext() && !found) {
            Monster monster = monsterIterator.next();
            if(monster.isAlive()) {
                current = monster;
                found = true;
            }
        }

        // If there are no living Monsters, then go to the next Hero
        if(!found) {
            heroGoesNext();
        }
    }

    /**
     * Private helper function which handles the situation where a Hero is
     * supposed to go next.
     */
    private void heroGoesNext() {
        boolean found = false;
        while(heroIterator.hasNext() && !found) {
            Hero hero = heroIterator.next();
            if(!hero.hasFainted()) {
                current = hero;
                found = true;
            }
        }

        if(!found) {
            // Check if there are Monsters that need to go
            if(monsterIterator.hasNext()) {
                current = monsterIterator.next();
            } else {
                finished = true;
            }
        }
    }

    /**
     * Plays the next turn
     */
    @Override
    public void playNextTurn() {
        // Do not execute a move if we are in a completed state
        if(finished) { return; }

        if(currentIsMonster()) {
            playNextMonstersTurn();
        } else {
            playNextHeroesTurn();
        }
    }

    /**
     * Helper function which plays the next Monster's turn.
     * A Monster's turn follows simple logic: if there is a Hero in range, attack it.
     * Otherwise, move forward (forward for Monsters is down)
     */
    private void playNextMonstersTurn() {
        displayCurrentMonsterStatus();
        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        List<Hero> heroesInRange = world.getHeroesInRange((Monster)current);
        if(heroesInRange.size() > 0){
            Hero toAttack = heroesInRange.get(0);
            FightMove attack = new Attack(current, Collections.singletonList(toAttack));
            try {
                attack.execute();
            } catch (InvalidFightMoveException e) {
                e.printStackTrace();
                // Shouldn't happen
            }
        } else {
            // Build a downward move for this Monster
            try {
                world.move((Monster)current, Direction.DOWN);
            } catch (InvalidMoveDirection e) {
                // Don't do anything - it's possible that a Monster tries
                // to move down to a Cell that is already occupied by another
                // Monster. In this case, just ignore it, the Monster doesn't do anything
            }
        }
    }

    /**
     * Helper function which outputs the status of the current Monster
     */
    private void displayCurrentMonsterStatus() {
        Output.printSeparator();
        Monster monster = (Monster)current;
        String firstTwoLetters = Colors.ANSI_RED + monster.getName().substring(0, 2) + Colors.ANSI_RESET;
        System.out.println("It is " + monster.getName() + " turn. Identified by (" + firstTwoLetters + ") on the map. Status:");
        Output.printOutputables(Collections.singletonList(monster));
    }

    /**
     * Helper function which plays the next Hero's turn.
     * For a Hero, we prompt the user for their desired action for the relevant Hero,
     * and then execute it if possible.
     */
    private void playNextHeroesTurn() {
        displayCurrentHeroStatus();
        // If it's the first round, prompt the user if they'd like their Hero to enter the Market
        if(firstRound) {
            promptHeroToEnterMarket();
        }

        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        List<Monster> monstersInRange = world.getMonstersInRange((Hero)current);
        boolean wantsToAttack = false;
        if(monstersInRange.size() > 0) {
            String prompt = "There are Monsters in range you can attack. Would you like to attack one?";
            wantsToAttack = new GetUserYesNoInput().run(prompt);
        }

        if(wantsToAttack) {
            heroAttackMonster(monstersInRange);
        } else {
            moveHero();
        }
    }

    /**
     * Helper function which prompts the Hero if they'd like to enter the Market
     */
    private void promptHeroToEnterMarket() {
        Hero hero = (Hero)current;
        String prompt = "Would you like " + hero.getName() + " to enter the Market? You can come back to the Market at any point as well";
        boolean enter = new GetUserYesNoInput().run(prompt);
        if(enter) {
            ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
            world.enterHeroToMarketIfPossible(hero);
        }
    }

    /**
     * Helper function which walks a user through the process of attacking a Monster
     * as their turn.
     */
    private void heroAttackMonster(List<Monster> monstersInRange) {
        String prompt = "Which Monster would you like to attack?";
        List<String> options = new ArrayList<>();
        for(Monster monster : monstersInRange) {
            options.add(monster.toString());
        }
        int chosen = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();
        Monster toAttack = monstersInRange.get(chosen);

        // The user does not actually have to attack this Monster. They can choose
        // to cast a spell or a Potion instead
        FightMove fightMove = new GetUserFightMove((Hero) current, Collections.singletonList(toAttack)).run();
        try {
            fightMove.execute();
            if(!toAttack.isAlive()) {
                processDeadMonster(toAttack);
            }
        } catch (InvalidFightMoveException e) {
            e.printStackTrace();
            // Shouldn't happen
        }
    }

    /**
     * Processes the situation when a Monster has been killed by a Hero
     * @param monster the Monster that died
     */
    private void processDeadMonster(Monster monster) {
        // Heroes gain 2 experience and level * 100 coins for killing a Monster
        Hero hero = (Hero)current;
        int gainedCoins = 100 * monster.getLevel().getLevel();
        hero.getCoffer().addCoins(gainedCoins);
        boolean leveledUp = hero.getExperience().increaseExperience(2);
        System.out.println(hero.getName() + " has killed a Monster! Gains " + gainedCoins + " coins and two experience points.");
        if(leveledUp) {
            System.out.println(hero.getName() + " leveled up! Now at Level " + hero.getLevel().getLevel());
        }
    }

    /**
     * Helper function which moves a Hero for their turn.
     */
    private void moveHero() {
        boolean enteredLegalMove = false;
        Hero hero = (Hero)current;
        while(!enteredLegalMove) {
            UserCommand command = new GetUserCommand().run();
            switch (command) {
                case UP:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.UP);
                    break;
                case DOWN:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.DOWN);
                    break;
                case LEFT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.LEFT);
                    break;
                case RIGHT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.RIGHT);
                    break;
                case TELEPORT:
                    enteredLegalMove = attemptMoveIfPossible(hero, Direction.TELEPORT);
                    break;
                case BACK:
                    enteredLegalMove = attemptMoveIfPossible(hero,Direction.BACK);
                    break;
                default:
                    throw new RuntimeException("Unknown command!");
            }
        }
    }

    private void displayCurrentHeroStatus() {
        Output.printSeparator();
        Hero hero = (Hero)current;
        String firstTwoLetters = Colors.ANSI_GREEN + hero.getName().substring(0, 2) + Colors.ANSI_RESET;
        System.out.println("It is " + hero.getName() + " turn! Identified by (" + firstTwoLetters + ") on the map. Status:");
        Output.printOutputables(Collections.singletonList(hero));
    }

    /**
     * Attempts to move in the Direction indicated
     * @param hero Hero whose move it is
     * @param direction Direction to move the Hero
     */
    private boolean attemptMoveIfPossible(Hero hero, Direction direction) {
        String failure = "Unable to move " + direction + "! Please enter a different move.";
        World world = Runner.getInstance().getWorld();
        try {
            world.move(hero, direction);
        } catch (InvalidMoveDirection e) {
            System.out.println(failure);
            return false;
        }
        return true;
    }


    /**
     * Performs any processing necessary at the completion of the previous turn.
     */
    @Override
    public void processEndOfTurn() {
        ValorWorld world = (ValorWorld)Runner.getInstance().getWorld();
        if(world.heroInMonstersNexus()) {
            finished = true;
        }

        if(world.monsterInHeroesNexus()) {
            finished = true;
        }
    }

    /**
     * Indicates whether or not all turns have finished.
     *
     * @return true if all turns have completed, false otherwise.
     */
    @Override
    public boolean finishedAllTurns() {
        if(finished) {
            heroIterator = null;
            monsterIterator = null;
        }
        return finished;
    }
}
