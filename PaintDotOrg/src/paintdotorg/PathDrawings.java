package paintdotorg;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.LinkedList;

public class PathDrawings extends NonShapeDrawings{
	
	//variables to store pencil color and eraser color
	private Color pointColor=Color.BLACK; 
	private Color eraserColor=Color.WHITE; 
	
	//variables to store each path, their thickness, color, and when drawn
	LinkedList<GeneralPath> paths= new LinkedList<GeneralPath>();
    LinkedList<Integer>paths_thicknesses= new LinkedList<Integer>();
    LinkedList<Color>paths_colors= new LinkedList<Color>(); 
    LinkedList<Integer>paths_drawNumbers= new LinkedList<Integer>();
    
    //used to set pencil path's color
    public void setPencilColor(DrawingArea drawingArea, Graphics g) {
    	g.setColor(pointColor);
    }
    
    //used to set eraser path's color
    public void setEraserColor(DrawingArea drawingArea,Graphics g) {
    	g.setColor(eraserColor);
    }
    
    //used to set path's stroke
    public void setStroke(DrawingArea drawingArea, Graphics gfx) {
    	Graphics2D g = (Graphics2D) gfx;
    	g.setStroke(new BasicStroke(drawingArea.getDrawings().getLineThickness()));
    	drawingArea.getDrawings().setLineThickness(drawingArea.getDrawings().getLineThickness());
    }
    
    //used to draw path
    public void drawPath(DrawingArea drawingArea, Graphics gfx) {
    	 Graphics2D g = (Graphics2D) gfx;
    	 g.draw(drawingArea.getP());
    	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
	     paths_drawNumbers.add(drawingArea.getNumDrawings());
    }
    
    //done when mouse pressed and user is using pencil or erasing
    public void mousePressed(MouseEvent me, DrawingArea drawingArea) {
    	
    	drawingArea.setPath(new GeneralPath());
    	paths.add(drawingArea.getP());
        if(drawingArea.getDrawEraser()==true) {
        	paths_colors.add(eraserColor); 
        }
        else {
        	paths_colors.add(pointColor);
        }
        paths_thicknesses.add(drawingArea.getDrawings().getLineThickness());
        drawingArea.getP().moveTo(me.getX(), me.getY());
    }
    
    //done when mouse is dragged and user is using pencil or erasing
    public void mouseDragged(MouseEvent me, DrawingArea drawingArea) {
    	drawingArea.getP().lineTo(me.getX(), me.getY());
    }
    
    //done when mouse is released and user is using pencil or erasing
    public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
    	
    	paths.add(drawingArea.getP()); 
    	if(drawingArea.getDrawEraser()==true) {
        	paths_colors.add(eraserColor); 
        }
        else {
        	paths_colors.add(pointColor); 
        }
        paths_thicknesses.add(drawingArea.getDrawings().getLineThickness());
    	
    }
    
    public void deleteAll() {
    	paths.removeAll(paths);
        paths_thicknesses.removeAll(paths_thicknesses);
        paths_colors.removeAll(paths_colors);
        paths_drawNumbers.removeAll(paths_drawNumbers);
    }
    
    //SETTERS
    
    //sets point color for drawing with pencil
    public void setPointColor(Color newColor) {
    	pointColor=newColor;
    }
    
    //GETTERS
    public LinkedList getPaths() {
    	return paths;
    }
    
    public LinkedList getPathsThicknesses() {
    	return paths_thicknesses;
    }
    
    public LinkedList getPathsColors() {
    	return paths_colors;
    }
    
    public LinkedList getPathsDrawNumbers() {
    	return paths_drawNumbers;
    }
    
    
}
