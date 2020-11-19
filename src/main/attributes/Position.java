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
		row = 0;
		col = 0;
	}
	public Position(int row, int col){
		this.row = row;
		this.col = col;
	}
	public void setSpawnPosition(int row, int col) {
		this.rowSpawn = row;
		this.colSpawn = col;
	}
	public void setPosition(int row, int col){
		this.row = row;
		this.col = col;
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.col = col;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
}