package paintdotorg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class LineDrawings extends ShapeDrawings {
	
	//variables to store each line's color, start and end x and y coordinates, thickness, and when drawn
	LinkedList<Color>lines_colors= new LinkedList<Color>(); 
    LinkedList<Integer>lines_startX= new LinkedList<Integer>();
    LinkedList<Integer>lines_startY= new LinkedList<Integer>();
    LinkedList<Integer>lines_endX= new LinkedList<Integer>();
    LinkedList<Integer>lines_endY= new LinkedList<Integer>();
    LinkedList<Integer>lines_thickness= new LinkedList<Integer>();
    LinkedList<Integer>lines_drawNumber= new LinkedList<Integer>();
    
    //used to draw a line
    public void drawLine(DrawingArea drawingArea, Graphics g){
 
    	if(drawingArea.getDrawings().getStartX()!=drawingArea.getDrawings().getEndX() || drawingArea.getDrawings().getStartY()!=drawingArea.getDrawings().getEndY()) {
	       	 g.drawLine(drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY(), drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getEndY());
	       //Information for line will be saved when mouse is released
	       	 if(drawingArea.getDragging()==false) {
	       		 lines_colors.add(drawingArea.getShapes().getFillColor());
		       	 lines_thickness.add(drawingArea.getDrawings().getLineThickness());
		       	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
	        	 lines_drawNumber.add(drawingArea.getNumDrawings());
	       	 }
    	}
    }
    
    public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
    	int endX=me.getX();
		int endY=me.getY();
		drawingArea.getDrawings().setEndX(endX);
        drawingArea.getDrawings().setEndY(endY);
		if(drawingArea.getDrawings().getStartX()!=endX || drawingArea.getDrawings().getStartY()!=endY) {
			lines_startX.add(drawingArea.getDrawings().getStartX());
			lines_startY.add(drawingArea.getDrawings().getStartY());
			lines_endX.add(drawingArea.getDrawings().getEndX());
			lines_endY.add(drawingArea.getDrawings().getEndY());
		}
    }
    
    public void deleteAll() {
    	lines_colors.removeAll(lines_colors);
    	lines_startX.removeAll(lines_startX);
    	lines_startY.removeAll(lines_startY);
    	lines_endX.removeAll(lines_endX);
    	lines_endY.removeAll(lines_endY);
    	lines_drawNumber.removeAll(lines_drawNumber);
    	lines_thickness.removeAll(lines_thickness);
    }
    
    //GETTERS
    public LinkedList getLinesColors(){
    	return lines_colors;
    }
    
    public LinkedList getLinesStartXs(){
    	return lines_startX;
    }
    
    public LinkedList getLinesStartYs(){
    	return lines_startY;
    }
    
    public LinkedList getLinesEndXs(){
    	return lines_endX;
    }
    
    public LinkedList getLinesEndYs(){
    	return lines_endY;
    }
    
    public LinkedList getLinesDrawNumbers(){
    	return lines_drawNumber;
    }
    
    public LinkedList getLinesThicknesses(){
    	return lines_thickness;
    }
    
}
