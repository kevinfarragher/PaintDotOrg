package paintdotorg_skeleton_code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class TextDrawings extends NonShapeDrawings {
	
	//variables to store text and its name, style, size, and color
	private String textToDisplay="default";
	private String textName="Arial";
	private int textStyle=Font.PLAIN;
	private int textSize=12;
	private Color textColor=Color.BLACK;
	
	//variables to store each text's color, string, start x and y coordinates, name, style, size, and when drawn.
	LinkedList<Color>texts_color= new LinkedList<Color>(); 
    LinkedList<String>texts_string= new LinkedList<String>(); 
    LinkedList<Integer>texts_startX= new LinkedList<Integer>();
    LinkedList<Integer>texts_startY= new LinkedList<Integer>();
    LinkedList<String>texts_name= new LinkedList<String>();
    LinkedList<Integer>texts_style= new LinkedList<Integer>();
    LinkedList<Integer>texts_size= new LinkedList<Integer>();
    LinkedList<Integer>texts_drawNumber= new LinkedList<Integer>();
    
    //sets text color
    public void setTextColor(DrawingArea drawingArea, Graphics g) {
    	
    	g.setColor(textColor);
		
	}
    
    //draws text
    public void drawText(DrawingArea drawingArea, Graphics g) {
    	
    	Font textFont=new Font(textName,textStyle,textSize);
  	     g.setFont(textFont);
     	 g.drawString(textToDisplay, drawingArea.getDrawings().getStartX(), drawingArea.getDrawings().getStartY());
     	 //Information for text is saved
     	 texts_color.add(textColor);
    	 texts_string.add(textToDisplay);
    	 texts_name.add(textName);
    	 texts_style.add(textStyle);
    	 texts_size.add(textSize);
         texts_startX.add(drawingArea.getDrawings().getStartX());
 	     texts_startY.add(drawingArea.getDrawings().getStartY());
    	 drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
    	 texts_drawNumber.add(drawingArea.getNumDrawings());
    	
    }
    
    //mouse pressed and user is drawing text
    public void mousePressed(MouseEvent me, DrawingArea drawingArea) {
    	
    	drawingArea.getDrawings().setStartX(me.getX());
    	drawingArea.getDrawings().setStartY(me.getY());
		drawingArea.getDrawings().setStartX(drawingArea.getDrawings().getStartX());
		drawingArea.getDrawings().setStartY(drawingArea.getDrawings().getStartY());
		
    }
    
    public void deleteAll() {
    	texts_color.removeAll(texts_color);
        texts_string.removeAll(texts_string);
        texts_startX.removeAll(texts_startX);
        texts_startY.removeAll(texts_startY);
        texts_name.removeAll(texts_name);
        texts_style.removeAll(texts_style);
        texts_size.removeAll(texts_size);
        texts_drawNumber.removeAll(texts_drawNumber);
    }
    
    //SETTERS
  
    //sets text color
    public void setTextColor(Color newTextColor) {
    	textColor=newTextColor;
    }
    
    //sets text to display
    public void setTextToDisplay(String newTextToDisplay) {
    	textToDisplay=newTextToDisplay;
    }
    
    //sets text name
    public void setTextName(String newTextName) {
    	textName=newTextName;
    }
    
    //sets text style
    public void setTextStyle(int newTextStyle) {
    	textStyle=newTextStyle;
    }
    
    //sets text size
    public void setTextSize(int newTextSize) {
    	textSize=newTextSize;
    }
    
    //GETTERS
    public LinkedList getTextsColors() {
    	return texts_color;
    }
    
    public LinkedList getTextsStrings() {
    	return texts_string;
    }
    
    public LinkedList getTextsStartXs() {
    	return texts_startX;
    }
    
    public LinkedList getTextsStartYs() {
    	return texts_startY;
    }
    
    public LinkedList getTextsNames() {
    	return texts_name;
    }
    
    public LinkedList getTextsStyles() {
    	return texts_style;
    }
    
    public LinkedList getTextsSizes() {
    	return texts_size;
    }
    
    public LinkedList getTextsDrawNumbers() {
    	return texts_drawNumber;
    }
}
