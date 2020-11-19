package main.world;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lane is a group of cells
 * @author Sandra Zhen
 */
public class Lane{
    private List<Cell> cells;
    public Lane(){
        cells = new ArrayList<>();
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    /**
     * Returns whether or not cell is in lane
     * @param cell
     * @return
     */
    public boolean contains(Cell cell){
        return this.cells.contains(cell);
    }


    @Override
    public String toString(){
        return Arrays.toString(cells.toArray());
    }

}
