/**
 * 
 */
package main.attributes;

/**
 * 
 *
 */
public class Position{
	private int row, col;
	private int rowSpawn, colSpawn;
	
	public Position(){
		
	}
	public void setSpawnPosition(int row, int col) {
		this.rowSpawn = row;
		this.colSpawn = col;
	}
	public void setPosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void setPostionRow(int row) {
		this.row = row;
	}
	
	public void setPositionCol(int col) {
		this.col = col;
	}
	
	public void respawn() {
		row = rowSpawn;
		col = colSpawn;
	}
	
	public int getPositionRow() {
		return row;
	}
	
	public int getPositionCol() {
		return col;
	}
}