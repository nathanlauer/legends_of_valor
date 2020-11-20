package main.world;

import javafx.geometry.Pos;
import main.attributes.Position;
import main.legends.*;
import main.utils.Colors;
import main.utils.GetUserNumericInput;

import java.util.*;

/**
 * Represents a world with vertical lanes
 * @author Sandra Zhen
 */
public class ValorWorld extends World {
    private final int laneWidth = 2;
    private final int numLanes = 3;
    private final int space = 1;
    private int lanesInsertedHero;
    private int laneTeleportTo;
    private HashMap<Hero,Boolean> teleported;
    private HashMap<Hero,Position> teleportPositions;
    private HashMap<Integer, Integer> farthestRow;
    private int lanesInsertedMonster;
    private HashMap<Hero,Position> spawnPositions;
    private HashMap<Monster,Position> monsterPositions;

    private Lane[] lanes;
    /**
     * Standard constructor, builds a Laned World
     * @param worldBuilder class which builds this World
     */
    public ValorWorld(WorldBuilder worldBuilder) {
        super(worldBuilder);
        lanes = divideIntoLanes(getCells(),numLanes,space);
        spawnPositions = new HashMap<>();
        monsterPositions = new HashMap<>();
        teleported = new HashMap<>();
        teleportPositions = new HashMap<>();
        farthestRow = new HashMap<>();
        lanesInsertedHero = 0;
        laneTeleportTo = 0;
        lanesInsertedMonster=0;
        spawnNewMonsters();
    }

    /**
     * hero can move to next cell if the cell does not have a hero occupied already, is not inaccessible cell and out of bound.
     */
    @Override
    public boolean canMove(Hero hero,Direction direction) {
        boolean allowed = false;
        switch (direction) {
            case UP:
                allowed = getHeroRow(hero) > 0 && !isHeroInCell(getCellAt(getHeroRow(hero)-1, getHeroCol(hero))) && !isMonsterInCell(getCellAt(getHeroRow(hero), getHeroCol(hero)))  ;
                break;
            case DOWN:
                allowed = getHeroRow(hero) < numRows() - 1 && !isHeroInCell(getCellAt(getHeroRow(hero)+1, getHeroCol(hero)));
                break;
            case LEFT:
                allowed = getHeroCol(hero) > 0 && !cellIsNonAccessible(getHeroRow(hero), getHeroCol(hero) - 1) && !isHeroInCell(getCellAt(getHeroRow(hero), getHeroCol(hero)-1));
                break;
            case RIGHT:
                allowed = getHeroCol(hero) < numCols() - 1 && !cellIsNonAccessible(getHeroRow(hero), getHeroCol(hero) + 1) && !isHeroInCell(getCellAt(getHeroRow(hero), getHeroCol(hero)+1));;
                break;
            case TELEPORT:
            	allowed = canTeleport(hero);
            	break;
            case BACK:
            	allowed = canGoBack(hero); 
            	break;
            default:
                throw new RuntimeException("Unknown direction!");
        }
        return allowed;
    }
    
   
    
    /**
     * Monster can only move down to next cell and the cell cannot have a monster occupied already, and it cannot be out of bound.
     * @param monster
     * @param direction
     * @return
     */
    public boolean canMove(Monster monster, Direction direction) {
    	boolean allowed = false;
    	if(!direction.equals(Direction.DOWN)) {
    		throw new RuntimeException("movement direction unsupported");
    	} else {//a monster can only move down and the cell can't have a monster occupied already.
    		allowed = getMonsterRow(monster) < numRows() - 1 &&  !isMonsterInCell(getCellAt(getMonsterRow(monster)+1,getMonsterCol(monster)));
    	}
    	return allowed;
    }
    
    /**
     * Hero can only teleport to a different lane. However, hero can always teleport back to his departure lane.
     * @param hero
     * @return
     */
    public boolean canTeleport(Hero hero) {
    	boolean canTeleport = false;
    	if(teleported.containsKey(hero)) {//hero can always teleport back unless the departure point is occupied by another hero
    		if(teleported.get(hero)) {
    			int prevRow = teleportPositions.get(hero).getRow();
    			int prevCol = teleportPositions.get(hero).getCol();
    			if(isHeroInCell(getCellAt(prevRow,prevCol))) {
    				canTeleport = false;
    			} else {
    				canTeleport = true;					
    			}
    		}    				
    	} else {
	    	canTeleport = isValidLane(hero);
    	}
    	return canTeleport;
    }
    
    /**
     * check if hero picks a valid lane to teleport to
     * @param hero
     * @return
     */
    public boolean isValidLane(Hero hero) {
    	String failure = "You can only teleport to a different lane.";
    	String prompt = "which lane would you like to teleport to?";
		List<String> options = new ArrayList<>(Arrays.asList("Top lane", "Mid lane", "Bot lane"));
		int choice = new GetUserNumericInput(new Scanner(System.in), prompt, options).run();
		int heroCol = heroPositions.get(hero).getCol();
		if((choice == 0 && (heroCol ==0 || heroCol ==1))||(choice ==1 && (heroCol ==3 || heroCol ==4))|| (choice == 2 &&(heroCol ==6|| heroCol == 7))){ 
			System.out.println(failure);
			return false;
		} else {
			laneTeleportTo = choice;
			return true;
		}
    }
    /**
     * Hero teleports to another lane
     * @param hero
     */
    public void teleport(Hero hero) {
    	if(teleported.containsKey(hero)) {
    		if(teleported.get(hero)) {//teleport back
    			teleportBack(hero);
    		}
    	} else {//teleport to a new lane.
    		teleportTo(hero);
    	}
    }
    
    /**
     * Hero teleports back to his origin lane of departure. However, if the initial departure point is behind monster,
     * teleport to the furtherest(to monsterNexus) monster location in that lane.  
     * @param hero
     */
    public void teleportBack(Hero hero) {
    	int teleportedRow = teleportPositions.get(hero).getRow();
		int teleportedCol = teleportPositions.get(hero).getCol();
		Position monsterPos = getFarthestMonster(teleportedRow, teleportedCol);
		if(monsterPos != null) {	
			this.setHeroLocation(hero, monsterPos.getRow(), monsterPos.getCol());		
		} else {
			this.setHeroLocation(hero, teleportPositions.get(hero).getRow(), teleportPositions.get(hero).getCol());
		}
		teleportPositions.remove(hero);
		teleported.put(hero, false);
    }
    
    /**
     * Hero teleports to a new lane. Hero will be teleported to the closest(to monsterNexus) already- explored point in that lane(with no monster behind).
     * @param hero
     */
    public void teleportTo(Hero hero) {	
    	Position curPos = heroPositions.get(hero);
		int targetCol = laneTeleportTo*3;
		int smallestSeen = Integer.MAX_VALUE;
		int farthestCol = 0;
		boolean found = false;
		for(Integer col : farthestRow.keySet()) {//find the furtherest row of the lane
			if(col == targetCol || col == targetCol +1) {
				found = true;
				if(col < smallestSeen) {
					smallestSeen = col;
					farthestCol = col;
				}
			}
		}
		if(found) {
	    	int targetRow = farthestRow.get(farthestCol);
			Position monsterPos = getFarthestMonster(farthestCol, targetRow);
			if(monsterPos!= null) {	
				this.setHeroLocation(hero, monsterPos.getRow(), monsterPos.getCol());	
			} else {
				if(isHeroInCell(getCellAt(targetRow,farthestCol))) {
					if(farthestCol%2 == 0)
						this.setHeroLocation(hero, targetRow, farthestCol+1);
					else
						this.setHeroLocation(hero, targetRow, farthestCol-1);
				} else {
					this.setHeroLocation(hero, targetRow, farthestCol);
				}
			}
		} else {// if no further path has been explored, transports to nexus of that lane.
			this.setHeroLocation(hero, this.numRows()-1, targetCol);
		}
		teleportPositions.put(hero, curPos);
    	teleported.put(hero, true);
    }
    /**
     * Check if there's monster behind the target position. If so, return the farthest Monster position.
     * @param targetCol
     * @param targetRow
     * @return
     */
    public Position getFarthestMonster(int targetCol, int targetRow) {
		int largestSeen = Integer.MIN_VALUE;
		boolean monsterBehind = false;
		Position farthestPos = null;
		for(Position pos : monsterPositions.values()) {
			if(pos.getCol() == targetCol || pos.getCol() == targetCol+1 || pos.getCol() == targetCol-1) {
				if(pos.getRow()>targetRow) {
					monsterBehind = true;
					if(largestSeen < pos.getRow()) {
						largestSeen = pos.getRow();
						farthestPos = pos;
					}
				}
			}
		}
		if(monsterBehind) {
			return farthestPos;
		} else {
			return null;
		}
    }
    /**
     * Hero makes a move.
     */
    @Override
    public void move(Hero hero,Direction direction) throws InvalidMoveDirection {
        if (!canMove(hero,direction)) {
            throw new InvalidMoveDirection("Cannot move in this direction!");
        }
        switch (direction) {
            case UP:
                this.setHeroLocation(hero,getHeroRow(hero) - 1, getHeroCol(hero));
                farthestRow.put(getHeroCol(hero),getHeroRow(hero));
                break;
            case DOWN:
                this.setHeroLocation(hero,getHeroRow(hero) + 1, getHeroCol(hero));
                break;
            case LEFT:
                this.setHeroLocation(hero,getHeroRow(hero), getHeroCol(hero) - 1);
                break;
            case RIGHT:
                this.setHeroLocation(hero,getHeroRow(hero), getHeroCol(hero) + 1);
                break;
            case TELEPORT:
            	teleport(hero);
            	break;
            case BACK:
            	respawnHero(hero);
            	break;
            default:
                throw new InvalidMoveDirection("Unknown move direction!");
    	}

        // Now, enter the new Cell
        Cell cell = getCellAt(getHeroRow(hero), getHeroCol(hero));
        List<Hero> heroList = new ArrayList<>();
        heroList.add(hero);
        if (!cell.canEnter(heroList)) {
            throw new InvalidMoveDirection("Unable to enter the cell!");
        }
        cell.enter(heroList); // may cause certain events to occur, like entering a Market or starting a Fight.
    }
    
    /**
     * Monster makes a move. Monster can only move down.
     * @param monster
     * @param direction
     * @throws InvalidMoveDirection
     */
    public void move(Monster monster, Direction direction) throws InvalidMoveDirection{
    	if(!canMove(monster, direction)) {
    		throw new InvalidMoveDirection("Cannot move in this direction!");
    	}
    	if(direction.equals(Direction.DOWN)) {
    		setMonsterLocation(monster, getMonsterRow(monster)+1, getMonsterCol(monster));
    	}
    }
    
    public int getMonsterRow(Monster monster) {
    	Position pos = monsterPositions.get(monster);
    	return pos.getRow();
    }
    
    public int getMonsterCol(Monster monster) {
    	Position pos = monsterPositions.get(monster);
    	return pos.getCol();
    }
    @Override
    protected void placeHero(Hero hero) {
        int col = lanesInsertedHero * (laneWidth + 1); // plus one accounts for separator between lanes
        int row = this.numRows() - 1;
        Cell cell = getCellAt(row, col);
        assert cell instanceof HeroNexusCell;

        // Insert this Hero to this Cell
        setHeroLocation(hero, row, col);
        spawnPositions.put(hero,new Position(row,col));

        // And increment the number of lanes that we have inserted a Hero to
        lanesInsertedHero++;
    }

    /**
     * Given a cell, draws the middle row for the cell according to the
     * semantics of the specific World.
     *
     * @param cell The cell to be drawn
     * @return String representing the middle row of a Cell.
     */
    @Override
    public String drawMiddleRow(Cell cell, String color) {
        // Draw the Hero on the left side, or empty space if no Hero
        String leftSide = "  "; // assume two spaces
        if(isHeroInCell(cell)) {
            Hero hero = getHeroInCell(cell);
            leftSide = Colors.ANSI_GREEN + hero.getName().substring(0, 2);
        }

        // Draw the Monster on the right side, or empty space if no Monster
        String rightSide = "  ";
        if(this.isMonsterInCell(cell)) {
            Monster monster = getMonsterInCell(cell);
            rightSide = Colors.ANSI_RED + monster.getName().substring(0, 2);
        }

        return color + "|" +
                leftSide +
                " " +
                rightSide +
                color + "| "
                + Colors.ANSI_RESET;
    }


    /**
     * Divides world into n lanes, with space between each lane
     * @param cells - board of cells to divide into lanes
     * @param numLanes - number of lanes to divide world into
     * @param space - number of cells between each lane
     * @return array of lanes
     */
    public Lane[] divideIntoLanes(Cell [][] cells,int numLanes,int space){
        int laneColStart = 0;
        Lane[] lanes = new Lane[numLanes];
        for(int i =0;i<lanes.length;i++){
            lanes[i] = new Lane();
            for(int row = 0; row<cells.length;row++){ //add each row of the cell board to the lane
                for(int j = laneColStart;j<Math.min(laneColStart+laneWidth,cells[0].length);j++){
                    lanes[i].addCell(cells[row][j]);
                }
            }

            laneColStart+=laneWidth+space;
        }

        return lanes;
    }

    /**
     * Returns lane that this cell belongs to
     * @param cell
     * @return
     */
    private Lane getLane(Cell cell){
        for(int i = 0; i<lanes.length;i++){
            if(lanes[i].contains(cell)){
                return lanes[i];
            }
        }
        return null;
    }
    
    /**
     * A hero can always go back to his nexus unless it is occupied by another hero.
     * @param hero
     * @return
     */
    public boolean canGoBack(Hero hero) {
    	Position position = spawnPositions.get(hero);
        if(isHeroInCell(getCellAt(position.getRow(),position.getCol()))) {
        	return false;
        }
        return true;
    }

    /**
     * given a Hero, “respawns” them in their Nexus
     * @param hero
     */
    public void respawnHero(Hero hero){
        Position position = spawnPositions.get(hero);
        heroPositions.put(hero, position);
    }
    /**
     * Sets the Monster location
     * @param monster - identifier for hero
     * @param row the new row for the hero
     * @param col the new col for the hero
     */
    public void setMonsterLocation(Monster monster,int row, int col) {
        checkValidity(row, col);
        monsterPositions.put(monster,new Position(row,col));
    }
    /**
     * Spawns new monsters in valor world
     * @return list of spawned monsters
     */
    public List<Monster> spawnNewMonsters(){
        List<Monster > monsterList = LegendList.getInstance().getCorrespondingMonsters();
        List<Monster> monsters = new ArrayList<>();
        for(int i = 0; i<monsterList.size();i++){
            int col = 0; // +1 accounts for separate between lanes
            Monster monster = null;
            try {
                //try to clone the monster and set its position to empty nexus cell
                monster = (Monster)(monsterList.get(i).clone());
                Cell emptyMonsterNexus=null;
                do{
                    col = lanesInsertedMonster * (laneWidth + space);
                    //spawn in one of the columns of the lane randomly
                    col+=new Random().nextInt(laneWidth);

                    emptyMonsterNexus = getEmptyMonsterNexusCell(col);
                    lanesInsertedMonster++;
                }while(emptyMonsterNexus==null);
                setMonsterLocation(monster,emptyMonsterNexus.getRow(),emptyMonsterNexus.getCol());
                monsters.add(monster);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }


        }
        return monsters;
    }

    /**
     * Gets the first empty monster nexus cell in a specified column
     * @param col
     * @return
     */
    private Cell getEmptyMonsterNexusCell(int col){
        if(col<0||col>=getCells()[0].length)return null;
        Cell[][] cells = getCells();
        for(int i = 0; i<cells.length;i++){
            if(cells[i][col] instanceof MonsterNexusCell) {
                if (!isMonsterInCell(cells[i][col])) {
                    return cells[i][col];
                }
            }
        }
        return null;
    }




    /**
     * Indicates whether or not there is a Monster in the passed in Cell
     * @param cell the Cell in question
     * @return true if there is Monster present in the Cell, false otherwise
     */
    public boolean isMonsterInCell(Cell cell) {
        for (Map.Entry<Monster,Position> entry : monsterPositions.entrySet()) {
            Position position = entry.getValue();
            if(cell.hasPosition(position)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Monster in the passed Cell, or null if there are none
     * @param cell the Cell in question
     * @return Monster in the passed Cell, or null if none
     */
    public Monster getMonsterInCell(Cell cell) {
        if(!isMonsterInCell(cell)) {
            return null;
        }

        for (Map.Entry<Monster,Position> entry : monsterPositions.entrySet()) {
            Position position = entry.getValue();
            if(cell.hasPosition(position)) {
                return entry.getKey();
            }
        }
        return null;
    }
}
