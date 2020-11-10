package main.world;

import main.utils.Validations;

import java.util.Random;

/**
 * Class RandomWorldBuilder
 *
 * @author: Nathan Lauer
 * @email: lauern@bu.edu
 * Creation Date: 11/9/20
 * <p>
 * Please feel free to ask me any questions. I hope you're having a nice day!
 */
public class RandomWorldBuilder extends WorldBuilder {
    private final int commonCellCutoff;
    private final int nonAccessibleCellCutoff;

    /**
     * Standard constructor
     * Throws an IllegalArgumentException if any of the input values are negative
     * Throws an Exception if the percentages do not add up to 100.
     *
     * @param numRows the number of rows
     * @param numCols the number of cols
     * @param chanceOfCommonCell percentage chance of a CommonCell, on a scale 0 - 100
     * @param chanceOfNonAccessibleCell percentage chance of a NonAccessibleCell, on a scale 0 - 100
     * @param chanceOfMarketCell percentage chance of a MarketCell, on a scale 0 - 100
     */
    public RandomWorldBuilder(int numRows, int numCols, int chanceOfCommonCell, int chanceOfNonAccessibleCell, int chanceOfMarketCell) {
        super(numRows, numCols);
        Validations.nonNegative(chanceOfCommonCell, "chanceOfCommonCell");
        Validations.nonNegative(chanceOfNonAccessibleCell, "chanceOfNonAccessibleCell");
        Validations.nonNegative(chanceOfMarketCell, "chanceOfMarketCell");

        assert chanceOfCommonCell + chanceOfNonAccessibleCell + chanceOfMarketCell == 100;

        this.commonCellCutoff = chanceOfCommonCell; // e.g. 50
        this.nonAccessibleCellCutoff = chanceOfNonAccessibleCell + chanceOfCommonCell; // e.g. 80
        // marketCellCutoff is 100, but is just represented below by the else condition. Hence, no need for the variable.
    }

    /**
     * Helper method which actually performs the building
     *
     * @return a two dimensional array of generated Cells
     */
    @Override
    public Cell[][] build() {
        Cell[][] cells = new Cell[this.getNumRows()][this.getNumCols()];
        Random random = new Random();
        for(int i = 0; i < this.getNumRows(); i++) {
            for(int j = 0; j < this.getNumCols(); j++) {
                int chance = random.nextInt(101);
                Cell cell;
                if(chance <= this.commonCellCutoff) {
                    cell = new CommonCell(i, j);
                } else if (chance <= this.nonAccessibleCellCutoff) {
                    cell = new NonAccessibleCell(i, j);
                } else {
                    cell = new MarketCell(i, j);
                }
                cells[i][j] = cell;
            }
        }
        return cells;
    }

    /**
     * Abstract method which allows subclasses to check if the specific kind of World built was successfuly.
     *
     * @param cells the Cells constructed
     * @return true if the World was successfully built, false otherwise.
     */
    @Override
    public boolean builtSuccessfulWorld(Cell[][] cells) {
        return containsCommonCell(cells) && containsMarketCell(cells);
    }

    /**
     * Indicates whether or not the passed in cell array contains a CommonCell
     * @param cells the Cell array in question
     * @return true if the Cell array has at least one CommonCell, false otherwise.
     */
    private boolean containsCommonCell(Cell[][] cells) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell instanceof CommonCell) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Indicates whether or not the passed in Cell array contains a MarketCell.
     * @param cells the Cell array in question
     * @return true if the Cell array has at least one MarketCell, false otherwise.
     */
    private boolean containsMarketCell(Cell[][] cells) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                if (cell instanceof MarketCell) {
                    return true;
                }
            }
        }
        return false;
    }
}
