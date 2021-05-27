package paintdotorg;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class RectangleDrawings extends ShapeDrawings {

	//variables to store each rectangle's color, starting and ending x and y coordinates, when drawn, thickness, fill status, and drag status
	LinkedList<Color>rectangles_colors= new LinkedList<Color>(); 
    LinkedList<Integer>rectangles_startX= new LinkedList<Integer>();
    LinkedList<Integer>rectangles_startY= new LinkedList<Integer>();
    LinkedList<Integer>rectangles_endX= new LinkedList<Integer>();
    LinkedList<Integer>rectangles_endY= new LinkedList<Integer>();
    LinkedList<Integer>rectangles_drawNumber= new LinkedList<Integer>();
    LinkedList<Integer>rectangles_thickness= new LinkedList<Integer>();
    LinkedList<Boolean>rectangles_filled= new LinkedList<Boolean>();
    LinkedList<Boolean>rectangles_draggable= new LinkedList<Boolean>();
    
    //used to draw rectangle
    public void drawRectangle(DrawingArea drawingArea, Graphics g){
   
    	if(drawingArea.getDrawings().getStartX()!=drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()!=drawingArea.getDrawings().getEndY()) {
        	if(drawingArea.getDrawings().getStartX()<drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()<drawingArea.getDrawings().getEndY()) {
        		g.drawRect(drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY(), drawingArea.getDrawings().getEndX()-drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getEndY()-drawingArea.getDrawings().getStartY());
        	}
        	else if(drawingArea.getDrawings().getStartX()>drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()>drawingArea.getDrawings().getEndY()) {
        		g.drawRect(drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getEndY(), drawingArea.getDrawings().getStartX()-drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getStartY()-drawingArea.getDrawings().getEndY());
        	}
        	else if(drawingArea.getDrawings().getStartX()>drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()<drawingArea.getDrawings().getEndY()) {
        		g.drawRect(drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getStartY(), drawingArea.getDrawings().getStartX()-drawingArea.getDrawings().getEndX(), drawingArea.getDrawings().getEndY()-drawingArea.getDrawings().getStartY());
        	}
        	else if(drawingArea.getDrawings().getStartX()<drawingArea.getDrawings().getEndX() && drawingArea.getDrawings().getStartY()>drawingArea.getDrawings().getEndY()) {
        		g.drawRect(drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getEndY(), drawingArea.getDrawings().getEndX()-drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY()-drawingArea.getDrawings().getEndY());
        	}
        	//Information for rectangle will be saved when mouse is released
        	if(drawingArea.getDragging()==false) {
        		 rectangles_colors.add(drawingArea.getShapes().getFillColor());
	        	 rectangles_thickness.add(drawingArea.getDrawings().getLineThickness());
	        	 rectangles_filled.add(false);
	        	 rectangles_draggable.add(true);
	        	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
	 	         rectangles_drawNumber.add(drawingArea.getNumDrawings());
        	}
    	}
    }
    
    //used to initialize drawing area
    public void initializeDrawingArea(DrawingArea drawingArea, Graphics g) {
    	 
    	 g.setColor(Color.WHITE);
	   	 g.drawRect(0, 0, drawingArea.getWidth(), drawingArea.getHeight());
	   	 rectangles_startX.add(0);
	   	 rectangles_startY.add(0);
	   	 rectangles_endX.add(drawingArea.getWidth());
	   	 rectangles_endY.add(drawingArea.getHeight());
	   	 rectangles_colors.add(Color.WHITE);
	   	 rectangles_thickness.add(1);
	   	 rectangles_filled.add(true);
	   	 rectangles_draggable.add(false);
	   	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
         rectangles_drawNumber.add(drawingArea.getNumDrawings());
	   	
    }
    
    //used when mouse is released and user is drawing a rectangle
    public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
    	
    	int endX=me.getX();
		int endY=me.getY();
		drawingArea.getDrawings().setEndX(endX);
        drawingArea.getDrawings().setEndY(endY);
		if(drawingArea.getDrawings().getStartX()!=endX && drawingArea.getDrawings().getStartY()!=endY) {
    		if(drawingArea.getDrawings().getStartX()<endX && drawingArea.getDrawings().getStartY()<endY) {
    			rectangles_startX.add(drawingArea.getDrawings().getStartX());
        		rectangles_startY.add(drawingArea.getDrawings().getStartY());
        		rectangles_endX.add(drawingArea.getDrawings().getEndX());
        		rectangles_endY.add(drawingArea.getDrawings().getEndY());
    		}
    		else if(drawingArea.getDrawings().getStartX()>endX && drawingArea.getDrawings().getStartY()>endY) {
    			rectangles_startX.add(drawingArea.getDrawings().getEndX());
        		rectangles_startY.add(drawingArea.getDrawings().getEndY());
        		rectangles_endX.add(drawingArea.getDrawings().getStartX());
        		rectangles_endY.add(drawingArea.getDrawings().getStartY());
    		}
    		else if(drawingArea.getDrawings().getStartX()>endX && drawingArea.getDrawings().getStartY()<endY) {
    			rectangles_startX.add(drawingArea.getDrawings().getEndX());
        		rectangles_startY.add(drawingArea.getDrawings().getStartY());
        		rectangles_endX.add(drawingArea.getDrawings().getStartX());
        		rectangles_endY.add(drawingArea.getDrawings().getEndY());
    		}
    		else if(drawingArea.getDrawings().getStartX()<endX && drawingArea.getDrawings().getStartY()>endY) {
    			rectangles_startX.add(drawingArea.getDrawings().getStartX());
        		rectangles_startY.add(drawingArea.getDrawings().getEndY());
        		rectangles_endX.add(drawingArea.getDrawings().getEndX());
        		rectangles_endY.add(drawingArea.getDrawings().getStartY());
    		}
		}
    }
    
    public void deleteAll() {
    	rectangles_colors.removeAll(rectangles_colors);
    	rectangles_startX.removeAll(rectangles_startX);
    	rectangles_startY.removeAll(rectangles_startY);
    	rectangles_endX.removeAll(rectangles_endX);
    	rectangles_endY.removeAll(rectangles_endY);
    	rectangles_drawNumber.removeAll(rectangles_drawNumber);
    	rectangles_thickness.removeAll(rectangles_thickness);
    	rectangles_filled.removeAll(rectangles_filled);
    }
    
    //GETTERS
    public LinkedList getRectanglesColors(){
    	return rectangles_colors;
    }
    
    public LinkedList getRectanglesStartXs(){
    	return rectangles_startX;
    }
    
    public LinkedList getRectanglesStartYs(){
    	return rectangles_startY;
    }
    
    public LinkedList getRectanglesEndXs(){
    	return rectangles_endX;
    }
    
    public LinkedList getRectanglesEndYs(){
    	return rectangles_endY;
    }
    
    public LinkedList getRectanglesDrawNumbers(){
    	return rectangles_drawNumber;
    }
    
    public LinkedList getRectanglesThicknesses(){
    	return rectangles_thickness;
    }
    
    public LinkedList getRectanglesFilled(){
    	return rectangles_filled;
    }
    
    public LinkedList getRectanglesDraggable(){
    	return rectangles_draggable;
    }
    
}
