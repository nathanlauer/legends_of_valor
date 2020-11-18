package main.world;
/**
 * @author Sandra Zhen
 * Builds the valor world consisting of monster nexus cells at top and hero nexus cells at bottom,
 * and inaccessible cells every blockIndex-th column
 */

import main.utils.Validations;

import java.util.Random;

public class ValorWorldBuilder extends WorldBuilder{
    private final int blockIndex = 3;//tracks the columns of inaccessible cells. Every blockIndex-th column is all inaccessible cells

    /**
     * Class represents distribution of cells in to valor world
     */
    public class ValorWorldDistribution{
        private int chanceOfBushCells,chanceOfPlainCells,chanceOfCaveCells,chanceOfKoulouCells;
        ValorWorldDistribution(){//manual default config
            this(20,40,20,20);
        }
        ValorWorldDistribution(int chanceOfBushCells,int chanceOfPlainCells,int chanceOfCaveCells,int chanceOfKoulouCells){
            Validations.nonNegative(chanceOfBushCells, "chanceOfBushCells");
            Validations.nonNegative(chanceOfPlainCells, "chanceOfPlainCells");
            Validations.nonNegative(chanceOfCaveCells, "chanceOfCaveCells");
            Validations.nonNegative(chanceOfKoulouCells, "chanceOfKoulouCells");

            assert chanceOfBushCells+chanceOfPlainCells + chanceOfCaveCells + chanceOfKoulouCells == 100;

            this.chanceOfBushCells=chanceOfBushCells;
            this.chanceOfPlainCells=chanceOfPlainCells;
            this.chanceOfCaveCells=chanceOfCaveCells;
            this.chanceOfKoulouCells=chanceOfKoulouCells;
        }
        int getchanceOfBushCells() {
            return chanceOfBushCells;
        }
        int getchanceOfCaveCells() {
            return chanceOfCaveCells;
        }
        int getchanceOfPlainCells() {
            return chanceOfPlainCells;
        }
        int getchanceOfKoulouCells() {
            return chanceOfKoulouCells;
        }
    }
    private ValorWorldDistribution valorWorldDistribution;
    public ValorWorldBuilder(int numRows, int numCols,ValorWorldDistribution valorWorldDistribution) {
        super(numRows, numCols);
        this.valorWorldDistribution = valorWorldDistribution;
    }

    @Override
    public Cell[][] build() {
        //generate board of random valor cells
        Cell[][] cells = new Cell[this.getNumRows()][this.getNumCols()];
        for(int i = 0; i < this.getNumRows(); i++) {
            for(int j = 0; j < this.getNumCols(); j++) {

                cells[i][j] = generateRandomValorCell(i,j);
            }
        }
        //replace first row with monster nexus cells
        for(int i = 0; i<cells[0].length;i++){
            cells[cells.length-1][i] = new MonsterNexusCell(cells.length-1,i);
        }
        //replace last row with hero nexus cells
        for(int i = 0; i<cells[0].length;i++){
            cells[0][i] = new HeroNexusCell(0,i);
        }
        //create columns of inaccessible cells
        for(int r = 0; r<cells.length;r++) {
            for (int i = blockIndex - 1; i < cells[0].length; i += blockIndex) {//nonaccess for every blockIndex-th cell of row
                cells[r][i] = new NonAccessibleCell(r, i);
            }
        }

        return cells;
    }

    /**
     * generates random valor cell at position (i,j)
     * @param i - row to generate cell in
     * @param j - col to generate cell in
     * @return generated cell
     */
    private Cell generateRandomValorCell(int i, int j){
        Random random = new Random();
        int bushCellCutoff = valorWorldDistribution.getchanceOfBushCells();
        int plainCellCutoff = valorWorldDistribution.getchanceOfPlainCells()+bushCellCutoff;
        int caveCellCutoff = valorWorldDistribution.getchanceOfCaveCells()+plainCellCutoff;
        int chance = random.nextInt(101);
        Cell cell;
        if(chance <= bushCellCutoff) {
            cell = new BushCell(i, j);
        }
        else if (chance <= plainCellCutoff) {
            cell = new PlainCell(i, j);
        }
        else if (chance <= caveCellCutoff) {
            cell = new CaveCell(i, j);
        } else {
            cell = new KoulouCell(i, j);
        }
        return cell;
    }

    @Override
    public boolean builtSuccessfulWorld(Cell[][] cells) {
        return false;
    }
}
