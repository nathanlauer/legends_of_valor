package main.world;

public class ValorWorldBuilder extends WorldBuilder{
    public ValorWorldBuilder(int numRows, int numCols) {
        super(numRows, numCols);
    }

    @Override
    public Cell[][] build() {
        return new Cell[0][];
    }

    @Override
    public boolean builtSuccessfulWorld(Cell[][] cells) {
        return false;
    }
}
