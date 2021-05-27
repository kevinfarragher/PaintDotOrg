package paintdotorg_skeleton_code;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawingArea extends JPanel implements MouseListener, MouseMotionListener{
	
	//variables for drawings
	private Drawings drawings=new Drawings();
	private ShapeDrawings shapes=new ShapeDrawings();
	private RectangleDrawings rectangles=new RectangleDrawings();
	private CircleDrawings circles=new CircleDrawings();
	private LineDrawings lines=new LineDrawings();
	private TextDrawings texts=new TextDrawings();
	private PathDrawings paths=new PathDrawings();
	private ImageDrawings images=new ImageDrawings();
	
	//variables for drawing actions
	private FillAction fill=new FillAction();
	private SelectAction select=new SelectAction();
	
	//variable for I/O actions
	private IOActions userIOActions=new IOActions();
	
	//variable to keep track of whether mouse is being dragged or not
	private boolean dragging=false; 
	
	//variables to keep track of current user selection or action
	private boolean drawRectangle=false;
	private boolean drawCircle=false;
	private boolean drawLine=false;
	private boolean drawPencil=false;
	private boolean drawEraser=false;
	private boolean drawText=false;
	private boolean drawFill=false;
	private boolean drawImage=false;
	private boolean drawInitialize=true;
	private boolean drawSelect=false;
	
	//variable to keep track of number of drawings
	private int numDrawings=0;
	
	//variable used for path drawing
    GeneralPath p=new GeneralPath();
    
    //constructor to add mouse and mouse motion listeners to the drawing area
    public DrawingArea() {
        // mouse interaction:
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    ///// USED FOR DRAWING various drawings
    @Override
    public void paintComponent(Graphics gfx) {
        //Necessary for any paintComponent method. 
        super.paintComponent(gfx);
        Graphics2D g = (Graphics2D) gfx;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        //sets current stroke based on line thickness
        g.setStroke(new BasicStroke(drawings.getLineThickness()));
        
      //Loop used to redraw all old drawings and in the correct order
        for(int i=1;i<numDrawings+1;i++) {
        	
        	//redrawing paths
        	for(int j=0;j<paths.getPaths().size();j++) { 
		    	try {
			    	if((int)paths.getPathsDrawNumbers().get(j)==i) { 
				    	g.setStroke(new BasicStroke((int)paths.getPathsThicknesses().get(j)));
				    	g.setColor((Color)paths.getPathsColors().get(j)); 
				    	g.draw((GeneralPath)paths.getPaths().get(j)); 
			    	}
			    }catch(Exception e){
	            	break;
	            } 
		    }

		    //redrawing rectangles
		    for(int j=0;j<rectangles.getRectanglesColors().size();j++) { 
		    	try {
			    	if((int)rectangles.getRectanglesDrawNumbers().get(j)==i) {
			    		g.setStroke(new BasicStroke((int)rectangles.getRectanglesThicknesses().get(j)));
			    		g.setColor((Color)rectangles.getRectanglesColors().get(j)); 
			    		if((Boolean)rectangles.getRectanglesFilled().get(j)==false) {
				    		g.drawRect((int)rectangles.getRectanglesStartXs().get(j),(int)rectangles.getRectanglesStartYs().get(j),(int)rectangles.getRectanglesEndXs().get(j)-(int)rectangles.getRectanglesStartXs().get(j),(int)rectangles.getRectanglesEndYs().get(j)-(int)rectangles.getRectanglesStartYs().get(j)); 
			    		}
			    		else {
				    		g.fillRect((int)rectangles.getRectanglesStartXs().get(j),(int)rectangles.getRectanglesStartYs().get(j),(int)rectangles.getRectanglesEndXs().get(j)-(int)rectangles.getRectanglesStartXs().get(j),(int)rectangles.getRectanglesEndYs().get(j)-(int)rectangles.getRectanglesStartYs().get(j)); 
			    		}
	
			    	}
		    	}catch(Exception e){
		    		break;
		    	}
		    }
		    
		    //redrawing circles
		    for(int j=0;j<circles.getCirclesColors().size();j++) { 
		    	try {
			    	if((int)circles.getCirclesDrawNumbers().get(j)==i) {
			    		g.setStroke(new BasicStroke((int)circles.getCirclesThicknesses().get(j)));
			    		g.setColor((Color)circles.getCirclesColors().get(j)); 
			    		if((Boolean)circles.getCirclesFilled().get(j)==false) {
				    		g.drawOval((int)circles.getCirclesStartXs().get(j),(int)circles.getCirclesStartYs().get(j),(int)circles.getCirclesEndXs().get(j)-(int)circles.getCirclesStartXs().get(j),(int)circles.getCirclesEndYs().get(j)-(int)circles.getCirclesStartYs().get(j)); 
			    		}
			    		else {
				    		g.fillOval((int)circles.getCirclesStartXs().get(j),(int)circles.getCirclesStartYs().get(j),(int)circles.getCirclesEndXs().get(j)-(int)circles.getCirclesStartXs().get(j),(int)circles.getCirclesEndYs().get(j)-(int)circles.getCirclesStartYs().get(j)); 
			    		}
	
			    	}
		    	}catch(Exception e){
		    		break;
		    	}
		    }
 
		    //redrawing lines
		    for(int j=0;j<lines.getLinesColors().size();j++) { 
		    	try {
			    	if((int)lines.getLinesDrawNumbers().get(j)==i) {
			    		g.setStroke(new BasicStroke((int)lines.getLinesThicknesses().get(j)));
			    		g.setColor((Color)lines.getLinesColors().get(j)); 
			    		g.drawLine((int)lines.getLinesStartXs().get(j),(int)lines.getLinesStartYs().get(j),(int)lines.getLinesEndXs().get(j),(int)lines.getLinesEndYs().get(j)); 
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		   
		    //redrawing texts
		    for(int j=0;j<texts.getTextsStrings().size();j++) { 
			    try {
			    	if((int)texts.getTextsDrawNumbers().get(j)==i) {
			    		g.setColor((Color)texts.getTextsColors().get(j)); 
			    		Font textFontLocal=new Font((String)texts.getTextsNames().get(j),(int)texts.getTextsStyles().get(j),(int)texts.getTextsSizes().get(j));
			    		g.setFont(textFontLocal);
			    		g.drawString((String)texts.getTextsStrings().get(j), (int)texts.getTextsStartXs().get(j), (int)texts.getTextsStartYs().get(j));
			    	}
			    }catch(Exception e){
	            	break;
	            }
		    }
		    
		    //redrawing images
		    for(int j=0;j<images.getImages().size();j++) { 
		    	try {
			    	if((int)images.getImagesDrawNumbers().get(j)==i) {
			    		g.drawImage((BufferedImage)images.getImages().get(j), (int)images.getImagesStartXs().get(j), (int)images.getImagesStartYs().get(j), (int)images.getImagesEndXs().get(j)-(int)images.getImagesStartXs().get(j), (int)images.getImagesEndYs().get(j)-(int)images.getImagesStartYs().get(j), this);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
        }
        
        //END OF REDRAWING PREVIOUS DRAWINGS 
        
        //color set when drawing with pencil
        if(drawPencil==true) {
        	paths.setPencilColor(this, g);
        }
        
        //color set when erasing
        else if(drawEraser==true) {
        	paths.setEraserColor(this, g);
        }
        
        //color set when drawing a shape
        else if(drawRectangle==true || drawCircle==true || drawLine==true) {
        	shapes.setFillColor(this, g);
        }
        
        //color set when filling a shape
        else if(drawFill==true) {
        	fill.setFillColor(this, g);
        }
        
        //color set when drawing text
        else if(drawText==true) {
        	texts.setTextColor(this, g);
        }
        
        //stroke set when drawing with pencil or erasing. Based on selected line thickness.
        if(drawPencil==true || drawEraser==true) {
        	paths.setStroke(this, g);
        }
        
        //stroke set when drawing a shape. Based on selected line thickness
        else if(drawRectangle==true || drawCircle==true || drawLine==true) {
        	shapes.setStroke(this,g);
        }
        
        //action done when drawing in pencil or erasing
        if(drawPencil==true || drawEraser==true) {
	        paths.drawPath(this, g);
        }
        
        //action done when drawing a rectangle
        else if(drawRectangle==true) {
        	rectangles.drawRectangle(this,g);
        }
        
        //action done when drawing a circle
        else if(drawCircle==true) {
        	circles.drawCircle(this, g);
	    }
        
        //action done when drawing a line
        else if(drawLine==true) {
        	lines.drawLine(this, g);
	    }
        
        //action done when text is drawn on screen
        else if(drawText==true) {
        	texts.drawText(this, g);
        }
        
        //action done when image is loaded on screen
        else if(drawImage==true) {
        	images.drawImage(this, g);
	      	drawImage=false;
	      	drawPencil=true;
        }
        
        //when program is initiated white rectangle is drawn that covers drawing area.
        else if(drawInitialize==true) {
        	 rectangles.initializeDrawingArea(this, g);
 	         drawInitialize=false;
 	         drawPencil=true;
        }
    }

    ///// MOUSE INTERACTION
    
  //When mouse pressed, appropriate event is performed based on current selected action.
    @Override
    public void mousePressed(MouseEvent me) {
    	
    	dragging=false;
    	
    	//action performed when mouse is clicked and user is drawing with pencil or erasing.
    	if(drawPencil==true || drawEraser==true){
	        paths.mousePressed(me, this);
	        repaint();
    	}
    	
    	//action performed when mouse is clicked and user is drawing a rectangle, circle, or line
    	else if(drawRectangle==true||drawCircle==true||drawLine==true){
    		shapes.mousePressed(me, this);
    	}
    	
    	//action performed when mouse is clicked and user is drawing text.
    	else if(drawText==true){
    		texts.mousePressed(me, this);
    		repaint();
    	}
    	
    	//action performed when mouse is clicked and user is filling a shape with a color.
    	else if(drawFill==true) {
    		fill.mousePressed(me, this);
    		repaint();
    	}
    	
    	//action performed when mouse is clicked and user is selecting a shape or image
    	else if(drawSelect==true) {
    		select.mousePressed(me, this);
			 repaint();
	    }
    }
    
 // Implementation of MouseMotionListener
    //While mouse dragged, appropriate event is performed based on current selected action
     @Override
     public void mouseDragged(MouseEvent me) {
     	
    	 dragging=true;
    	 
     	//action performed when mouse is being dragged and the user is drawing with pencil or erasing
     	if(drawPencil==true || drawEraser==true){
 	        paths.mouseDragged(me, this);
     	}
     	
     	//action performed when mouse is being dragged and the user is drawing a rectangle, circle, or line
     	else if(drawRectangle==true || drawCircle==true || drawLine==true) {
     		shapes.mouseDragged(me, this);
     	}
     	
     	//action performed when mouse is being dragged and the user is selecting a shape or image.
     	else if(drawSelect==true) {
     		select.mouseDragged(me, this);
     	}
     	repaint();
     }
    
   //When mouse released, appropriate event is performed based on current selected action
    @Override
    public void mouseReleased(MouseEvent me) {
    	
    	dragging=false;
    	
    	//action performed when mouse is released and the user is drawing with a pencil or erasing
    	if(drawPencil==true || drawEraser==true){
	    	paths.mouseReleased(me, this);
    	}
    	
    	//action performed when mouse is released and the user is drawing a rectangle
    	else if(drawRectangle==true){
    		rectangles.mouseReleased(me, this);
    	}
    	
    	//action performed when mouse is released and the user is drawing a circle
    	else if(drawCircle==true){
    		circles.mouseReleased(me, this);
    	}
    	
    	//action performed when mouse is released and the user is drawing a line
    	else if(drawLine==true){
    		lines.mouseReleased(me, this);
    	}
    	
    	//action performed when mouse is released and user is selecting a shape or image to move.
    	else if(drawSelect==true) {
    		select.mouseReleased(me, this);
     	}
     	repaint();
    }
    
 // Implementation of MouseListener
    @Override
    public void mouseClicked(MouseEvent me) {
 
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {        
    }

    //END OF MOUSE INTERACTION
    
    //enables drawing a rectangle and disables everything else
    public void drawRectangle() { 
    	drawRectangle=true; 
    	drawCircle=false;
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
    //enables drawing a circle and disables everything else
    public void drawCircle() { 
    	drawCircle=true;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
    //enables drawing with pencil and disables everything else
    public void drawPencil() { 
    	drawPencil=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
   //enables erasing and disables everything else
    public void drawEraser() { 
    	drawEraser=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
  //enables drawing a line and disables everything else
    public void drawLine() {
    	drawLine=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
  //enables drawing text and disables everything else
    public void drawText() {
    	drawText=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
  //enables filling clicked shapes and disables everything else
    public void drawFill() {
    	drawFill=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawImage=false;
    	drawInitialize=false;
    	drawSelect=false;
    }
    
  //enables selecting a shape to move and disables everything else
    public void drawSelect() {
    	drawSelect=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawInitialize=false;
    }
    
    //called when program is loaded to initialize drawing screen
    public void initialize() {
    	drawInitialize=true;
    	drawCircle=false;
    	drawRectangle=false; 
    	drawLine=false;
    	drawPencil=false;
    	drawEraser=false;
    	drawText=false;
    	drawFill=false;
    	drawImage=false;
    	drawSelect=false;
    	repaint();
    }
    
    //SETTERS
    
    //sets path for pencil, eraser drawing
    public void setPath(GeneralPath newPath) {
    	p=newPath;
    }
    
    public void setDragging(boolean newDragging) {
    	dragging=newDragging;
    }
    
    public void setNumDrawings(int newNumDrawings) {
    	numDrawings=newNumDrawings;
    }
    
    public void setDrawLoadingImage(boolean newDrawLoadingImage) {
    	drawImage=newDrawLoadingImage;
    }
    
    public void setDrawCircle(boolean newDrawCircle) {
    	drawCircle=newDrawCircle;
    }
    
    public void setDrawRectangle(boolean newDrawRectangle) {
    	drawRectangle=newDrawRectangle;
    }
    
    public void setDrawLine(boolean newDrawLine) {
    	drawLine=newDrawLine;
    }
    
    public void setDrawPencil(boolean newDrawPencil) {
    	drawPencil=newDrawPencil;
    }
    
    public void setDrawEraser(boolean newDrawEraser) {
    	drawEraser=newDrawEraser;
    }
    
    public void setDrawText(boolean newDrawText) {
    	drawText=newDrawText;
    }
    
    public void setDrawFill(boolean newDrawFill) {
    	drawFill=newDrawFill;
    }
    
    public void setDrawSelect(boolean newDrawSelect) {
    	drawSelect=newDrawSelect;
    }
    
    public void setDrawInitialize(boolean newDrawInitialize) {
    	drawInitialize=newDrawInitialize;
    }
    
    //GETTERS
    
    public boolean getDrawEraser() {
    	return drawEraser;
    }
    
    public boolean getDragging() {
    	return dragging;
    }
    
    public GeneralPath getP() {
    	return p;
    }
    
    public int getNumDrawings() {
    	return numDrawings;
    }
    
    public PathDrawings getPaths() {
    	return paths;
    }
    
    public RectangleDrawings getRectangles() {
    	return rectangles;
    }
    
    public CircleDrawings getCircles() {
    	return circles;
    }
    
    public LineDrawings getLines() {
    	return lines;
    }
    
    public TextDrawings getTexts() {
    	return texts;
    }
    
    public ImageDrawings getImages() {
    	return images;
    }
    
    public IOActions getUserIOActions() {
    	return userIOActions;
    }
    
    public ShapeDrawings getShapes() {
    	return shapes;
    }
    
    public Drawings getDrawings() {
    	return drawings;
    }
    
    public SelectAction getSelect() {
    	return select;
    }
   
}