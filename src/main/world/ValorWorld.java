package main.world;

import javafx.geometry.Pos;
import main.attributes.Position;
import main.legends.Hero;
import main.legends.Legend;
import main.legends.Monster;

import java.util.*;

/**
 * Represents a world with vertical lanes
 * @author Sandra Zhen
 */
public class ValorWorld extends World{
    private final int laneWidth = 2;
    private final int numLanes = 3;
    private final int space = 1;
    private HashMap<Hero,Position> spawnPositions;
    private Lane[] lanes;
    /**
     * Standard constructor, builds a Laned World
     * @param worldBuilder class which builds this World
     */
    public ValorWorld(WorldBuilder worldBuilder) {
        super(worldBuilder);
        lanes = divideIntoLanes(getCells(),numLanes,space);
        spawnPositions = new HashMap<>();

    }

    @Override
    protected void placeHero(Hero hero) {
        boolean foundCommonCell = false;
        Random random = new Random();
        while (!foundCommonCell) {
            int row = random.nextInt(this.numRows());
            for (int col = 0; col < numCols(); col++) {
                Cell cell = getCellAt(row, col);
                if (cell instanceof HeroNexusCell) {
                    foundCommonCell = true;
                    setHeroLocation(hero, row, col);

                    spawnPositions.put(hero,new Position(row,col));
                    break;
                }
            }
        }
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
     * given a Hero, “respawns” them in their Nexus
     * @param hero
     */
    public void respawnHero(Hero hero){
        Position position = spawnPositions.get(hero);
        setHeroLocation(hero,position.getPositionRow(),position.getPositionCol());
    }

    /**
     * Spawns new monsters in valor world
     * @return list of spawned monsters
     */
    public List<Monster> spawnNewMonsters(){
        //TODO implement
    }

    public Monster spawnNewMonsterInLane(Lane lane){
        //TODO implement
    }


}
