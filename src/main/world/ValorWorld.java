package main.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Represents a world with vertical lanes
 * @author Sandra Zhen
 */
public class ValorWorld extends World{
    private final int laneWidth = 2;
    private final int numLanes = 3;
    private final int space = 1;

    private Lane[] lanes;
    /**
     * Standard constructor, builds a Laned World
     * @param worldBuilder class which builds this World
     */
    public ValorWorld(WorldBuilder worldBuilder) {
        super(worldBuilder);
        lanes = divideIntoLanes(getCells(),numLanes,space);

    }

    /**
     * Marks the location of the Heroes, by inserting the Heroes into a random Nexus
     * cell on the World.
     */
    public void placeHeroes() {
        boolean foundCommonCell = false;
        Random random = new Random();
        while(!foundCommonCell) {
            int row = random.nextInt(super.numRows());
            for(int col = 0; col < super.numCols(); col++) {
                Cell cell = getCellAt(row, col);
                if(cell instanceof NexusCell) {
                    foundCommonCell = true;
                    super.setHeroesLocation(row,col);
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



}
