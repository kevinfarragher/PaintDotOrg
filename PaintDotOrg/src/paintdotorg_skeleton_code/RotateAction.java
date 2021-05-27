package paintdotorg_skeleton_code;

import java.awt.event.MouseEvent;

public class RotateAction {
	//variable to keep track of degrees to rotate drawing
	private int degrees;
	
	public void mousePressed(MouseEvent me, DrawingArea drawingArea) {
		
	}
	
	public void mouseDragged(MouseEvent me, DrawingArea drawingArea) {
		
	}
	
	public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
		
	}
	//SETTERS
	public void setDegrees(int newDegrees) {
		degrees=newDegrees;
	}
	//GETTERS
	public int returnDegrees() {
		return degrees;
	}
}
