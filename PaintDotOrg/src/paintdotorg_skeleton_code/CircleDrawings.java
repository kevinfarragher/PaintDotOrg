package paintdotorg_skeleton_code;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class CircleDrawings extends ShapeDrawings {
	
	//variables to store each circle's color, starting and ending x and y coordinates, when drawn, thickness, and fill status
	LinkedList<Color>circles_colors= new LinkedList<Color>(); 
    LinkedList<Integer>circles_startX= new LinkedList<Integer>();
    LinkedList<Integer>circles_startY= new LinkedList<Integer>();
    LinkedList<Integer>circles_endX= new LinkedList<Integer>();
    LinkedList<Integer>circles_endY= new LinkedList<Integer>();
    LinkedList<Integer>circles_drawNumber= new LinkedList<Integer>();
    LinkedList<Integer>circles_thickness= new LinkedList<Integer>();
    LinkedList<Boolean>circles_filled= new LinkedList<Boolean>();
    
    //used to draw a circle
    public void drawCircle(DrawingArea drawingArea, Graphics g){
    	
    	if(drawingArea.getDrawings().getStartX()!=drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()!=drawingArea.getDrawings().getEndY()) {
        	if(drawingArea.getDrawings().getStartX()<drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()<drawingArea.getDrawings().getEndY()) {
        		g.drawOval(drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY(), drawingArea.getDrawings().getEndX()-drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getEndY()-drawingArea.getDrawings().getStartY());
        	}
        	else if(drawingArea.getDrawings().getStartX()>drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()>drawingArea.getDrawings().getEndY()) {
        		g.drawOval(drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getEndY(), drawingArea.getDrawings().getStartX()-drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getStartY()-drawingArea.getDrawings().getEndY());
        	}
        	else if(drawingArea.getDrawings().getStartX()>drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()<drawingArea.getDrawings().getEndY()) {
        		g.drawOval(drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getStartY(), drawingArea.getDrawings().getStartX()-drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getEndY()-drawingArea.getDrawings().getStartY());
        	}
        	else if(drawingArea.getDrawings().getStartX()<drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()>drawingArea.getDrawings().getEndY()) {
        		g.drawOval(drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getEndY(), drawingArea.getDrawings().getEndX()-drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY()-drawingArea.getDrawings().getEndY());
        	}
        	//Information for rectangle will be saved when mouse is released
        	if(drawingArea.getDragging()==false) {
        		 circles_colors.add(drawingArea.getShapes().getFillColor());
        		 circles_thickness.add(drawingArea.getDrawings().getLineThickness());
        		 circles_filled.add(false);
	        	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
	        	 circles_drawNumber.add(drawingArea.getNumDrawings()); 
        	}
    	}
    }
    
    //done when mouse is released and user is drawing a circle
    public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
    	
    	int endX=me.getX();
		int endY=me.getY();
		drawingArea.getDrawings().setEndX(endX);
        drawingArea.getDrawings().setEndY(endY);
		if(drawingArea.getDrawings().getStartX()!=endX && drawingArea.getDrawings().getStartY()!=endY) {
    		if(drawingArea.getDrawings().getStartX()<endX && drawingArea.getDrawings().getStartY()<endY) {
    			circles_startX.add(drawingArea.getDrawings().getStartX());
    			circles_startY.add(drawingArea.getDrawings().getStartY());
        		circles_endX.add(drawingArea.getDrawings().getEndX());
        		circles_endY.add(drawingArea.getDrawings().getEndY());
    		}
    		else if(drawingArea.getDrawings().getStartX()>endX && drawingArea.getDrawings().getStartY()>endY) {
    			circles_startX.add(drawingArea.getDrawings().getEndX());
    			circles_startY.add(drawingArea.getDrawings().getEndY());
        		circles_endX.add(drawingArea.getDrawings().getStartX());
        		circles_endY.add(drawingArea.getDrawings().getStartY());
    		}
    		else if(drawingArea.getDrawings().getStartX()>endX && drawingArea.getDrawings().getStartY()<endY) {
    			circles_startX.add(drawingArea.getDrawings().getEndX());
    			circles_startY.add(drawingArea.getDrawings().getStartY());
        		circles_endX.add(drawingArea.getDrawings().getStartX());
        		circles_endY.add(drawingArea.getDrawings().getEndY());
    		}
    		else if(drawingArea.getDrawings().getStartX()<endX && drawingArea.getDrawings().getStartY()>endY) {
    			circles_startX.add(drawingArea.getDrawings().getStartX());
    			circles_startY.add(drawingArea.getDrawings().getEndY());
    			circles_endX.add(drawingArea.getDrawings().getEndX());
    			circles_endY.add(drawingArea.getDrawings().getStartY());
    		}
		}
    }
    
    //deletes all circles
    public void deleteAll() {
    	circles_colors.removeAll(circles_colors);
        circles_startX.removeAll(circles_startX);
        circles_startY.removeAll(circles_startY);
        circles_endX.removeAll(circles_endX);
        circles_endY.removeAll(circles_endY);
        circles_drawNumber.removeAll(circles_drawNumber);
        circles_thickness.removeAll(circles_thickness);
        circles_filled.removeAll(circles_filled);
    }
    
    //GETTERS
    public LinkedList getCirclesColors(){
    	return circles_colors;
    }
    
    public LinkedList getCirclesStartXs(){
    	return circles_startX;
    }
    
    public LinkedList getCirclesStartYs(){
    	return circles_startY;
    }
    
    public LinkedList getCirclesEndXs(){
    	return circles_endX;
    }
    
    public LinkedList getCirclesEndYs(){
    	return circles_endY;
    }
    
    public LinkedList getCirclesDrawNumbers(){
    	return circles_drawNumber;
    }
    
    public LinkedList getCirclesThicknesses(){
    	return circles_thickness;
    }
    
    public LinkedList getCirclesFilled(){
    	return circles_filled;
    }
}
