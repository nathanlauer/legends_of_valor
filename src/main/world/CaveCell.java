package main.world;

/**
 * Cave cell
 * @author Sandra Zhen
 */
import main.legends.Hero;

import java.util.List;

public class CaveCell extends Cell{
    @Override
    public boolean canEnter(Hero hero) {
        return false;
    }

    @Override
    public void enter(Hero hero) {

    }

    @Override
    public List<String> draw(int heroesRow, int heroesCol) {
        return null;
    }
}
