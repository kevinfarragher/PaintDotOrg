package paintdotorg_skeleton_code;

public class Drawings {
	//variable to store line thickness when drawing, erasing, and for shapes
	private int lineThickness=1;
		
	//variables to store the mouse's start and end coordinates when performing certain actions
	private int startX=0;
	private int startY=0;
	private int endX=0;
	private int endY=0;
	
	public int getLineThickness() {
		return lineThickness;
	}
	public void setLineThickness(int lineThickness) {
		this.lineThickness = lineThickness;
	}
	public int getStartX() {
		return startX;
	}
	public void setStartX(int startX) {
		this.startX = startX;
	}
	public int getStartY() {
		return startY;
	}
	public void setStartY(int startY) {
		this.startY = startY;
	}
	public int getEndX() {
		return endX;
	}
	public void setEndX(int endX) {
		this.endX = endX;
	}
	public int getEndY() {
		return endY;
	}
	public void setEndY(int endY) {
		this.endY = endY;
	}
	
	
}
