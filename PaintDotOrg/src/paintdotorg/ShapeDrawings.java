package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class ShapeDrawings extends Drawings{
	
	//variable to store fill color
	private Color fillColor=Color.BLACK;
	
	//used to set shape's fill color
	 protected void setFillColor(DrawingArea drawingArea,Graphics g) {
		g.setColor(fillColor);
	}
	
	 //used to set shape's stroke
	 protected void setStroke(DrawingArea drawingArea, Graphics gfx) {
		Graphics2D g = (Graphics2D) gfx;
		g.setStroke(new BasicStroke(drawingArea.getDrawings().getLineThickness()));
		drawingArea.getDrawings().setLineThickness(drawingArea.getDrawings().getLineThickness());
	}
	
	 //used when mouse pressed and user is drawing a shape
	 protected void mousePressed(MouseEvent me, DrawingArea drawingArea) {
		drawingArea.getDrawings().setStartX(me.getX());
		drawingArea.getDrawings().setStartY(me.getY());
	}
	
	 //used when mouse dragged and user is drawing a shape
	 protected void mouseDragged(MouseEvent me, DrawingArea drawingArea) {
 		drawingArea.getDrawings().setEndX(me.getX());
		drawingArea.getDrawings().setEndY(me.getY());
	}
	 
	//SETTERS
	 public void setFillColor(Color newFillColor) {
		 fillColor=newFillColor;
	 }
	 
	//GETTERS
	public Color getFillColor() {
		return fillColor;
	}
	
}
