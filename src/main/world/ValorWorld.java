package main.world;

import javafx.geometry.Pos;
import main.attributes.Position;
import main.legends.*;
import main.utils.Colors;

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
        lanesInsertedHero = 0;
        lanesInsertedMonster=0;
        spawnNewMonsters();
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
     * given a Hero, “respawns” them in their Nexus
     * @param hero
     */
    public void respawnHero(Hero hero){
        Position position = spawnPositions.get(hero);
//        setHeroLocation(hero,position.getPositionRow(),position.getPositionCol());
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
