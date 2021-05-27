package paintdotorg;

import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class SelectAction{
	
	//variables used when user is using the select feature
		private int selectStartX=0;
		private int selectStartY=0;
		private int selectEndX=0;
		private int selectEndY=0;
		private int selectedDrawingNumber=0;
	
	public void mousePressed(MouseEvent me, DrawingArea drawingArea) {
		//each shape and image containing inside itself the point where the mouse was clicked is added to this list.
		LinkedList<Integer>drawingsHavingClick= new LinkedList<Integer>();
		selectedDrawingNumber=0;
		
		//loops through each rectangle to see if it contains the point where the mouse was clicked. If so, is added to the list of shapes with the click.
		for(int j=0;j<drawingArea.getRectangles().getRectanglesColors().size();j++) { 
			try {
				if((Boolean)drawingArea.getRectangles().getRectanglesDraggable().get(j)==true) {
			    	if(me.getX()>=(int)drawingArea.getRectangles().getRectanglesStartXs().get(j) && me.getX()<=(int)drawingArea.getRectangles().getRectanglesEndXs().get(j) && me.getY()>=(int)drawingArea.getRectangles().getRectanglesStartYs().get(j) && me.getY()<=(int)drawingArea.getRectangles().getRectanglesEndYs().get(j)) {
				    	drawingsHavingClick.add((int)drawingArea.getRectangles().getRectanglesDrawNumbers().get(j));	
			    	}
				}
    		}catch(Exception e){
            	break;
            }
	    }
	    
		//loops through each circle to see if it contains the point where the mouse was clicked. If so, is added to the list of shapes with the click.
		for(int j=0;j<drawingArea.getCircles().getCirclesColors().size();j++) { 
			try {
		    	if(me.getX()>=(int)drawingArea.getCircles().getCirclesStartXs().get(j) && me.getX()<=(int)drawingArea.getCircles().getCirclesEndXs().get(j) && me.getY()>=(int)drawingArea.getCircles().getCirclesStartYs().get(j) && me.getY()<=(int)drawingArea.getCircles().getCirclesEndYs().get(j)) {
		    		drawingsHavingClick.add((int)drawingArea.getCircles().getCirclesDrawNumbers().get(j));
		    	}
    		}catch(Exception e){
            	break;
            }
	    }
	    
		//loops through each line to see if it contains the point where the mouse was clicked. If so, is added to the list of shapes with the click.
	    for(int j=0;j<drawingArea.getLines().getLinesColors().size();j++) {
	    	try {
	    		if(((me.getX()>=(int)drawingArea.getLines().getLinesStartXs().get(j))||(me.getX()>=(int)drawingArea.getLines().getLinesEndXs().get(j))) && ((me.getX()<=(int)drawingArea.getLines().getLinesEndXs().get(j))||(me.getX()<=(int)drawingArea.getLines().getLinesStartXs().get(j))) && ((me.getY()>=(int)drawingArea.getLines().getLinesStartYs().get(j))||(me.getY()>=(int)drawingArea.getLines().getLinesEndYs().get(j))) && ((me.getY()<=(int)drawingArea.getLines().getLinesEndYs().get(j))||(me.getY()<=(int)drawingArea.getLines().getLinesStartYs().get(j)))) {
		    		drawingsHavingClick.add((int)drawingArea.getLines().getLinesDrawNumbers().get(j));
	    		}
	    	}catch(Exception e){
            	break;
            }
	    }
	    
	  //loops through each image to see if it contains the point where the mouse was clicked. If so, is added to the list of shapes with the click.
		for(int j=0;j<drawingArea.getImages().getImages().size();j++) { 
			try {
				if((Boolean)drawingArea.getImages().getImagesDraggable().get(j)==true) {
			    	if(me.getX()>=(int)drawingArea.getImages().getImagesStartXs().get(j) && me.getX()<=(int)drawingArea.getImages().getImagesEndXs().get(j) && me.getY()>=(int)drawingArea.getImages().getImagesStartYs().get(j) && me.getY()<=(int)drawingArea.getImages().getImagesEndYs().get(j)) {
				    	drawingsHavingClick.add((int)drawingArea.getImages().getImagesDrawNumbers().get(j));
			    	}
				}
    		}catch(Exception e){
            	break;
            }
	    }
	    
	    //determines most recent shape or image drawn out of all shapes or images with the click 
		if(drawingsHavingClick.size()!=0) {
		    int max = drawingsHavingClick.get(0);
		    for (int j = 0; j < drawingsHavingClick.size(); j++) {
		    	try {
		        if (drawingsHavingClick.get(j) > max) {
		            max = drawingsHavingClick.get(j);
		        }
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    drawingArea.getDrawings().setStartX(me.getX());
		    drawingArea.getDrawings().setStartY(me.getY());
		    selectedDrawingNumber=max;
		    
		    //sets initial x and y coordinates when select is being used by user an user clicked in a rectangle
		    for(int j=0;j<drawingArea.getRectangles().getRectanglesColors().size();j++) {
		    	try{
			    	if((int)drawingArea.getRectangles().getRectanglesDrawNumbers().get(j)==max) {
			    		selectStartX=(int)drawingArea.getRectangles().getRectanglesStartXs().get(j);
			    		selectStartY=(int)drawingArea.getRectangles().getRectanglesStartYs().get(j);
			    		selectEndX=(int)drawingArea.getRectangles().getRectanglesEndXs().get(j);
			    		selectEndY=(int)drawingArea.getRectangles().getRectanglesEndYs().get(j);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    
		  //sets initial x and y coordinates when select is being used by user an user clicked in a circle
		    for(int j=0;j<drawingArea.getCircles().getCirclesColors().size();j++) {
		    	try{
			    	if((int)drawingArea.getCircles().getCirclesDrawNumbers().get(j)==max) {
			    		selectStartX=(int)drawingArea.getCircles().getCirclesStartXs().get(j);
			    		selectStartY=(int)drawingArea.getCircles().getCirclesStartYs().get(j);
			    		selectEndX=(int)drawingArea.getCircles().getCirclesEndXs().get(j);
			    		selectEndY=(int)drawingArea.getCircles().getCirclesEndYs().get(j);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    
		  //sets initial x and y coordinates when select is being used by user an user clicked in a line
		    for(int j=0;j<drawingArea.getLines().getLinesColors().size();j++) {
		    	try{
			    	if((int)drawingArea.getLines().getLinesDrawNumbers().get(j)==max) {
			    		selectStartX=(int)drawingArea.getLines().getLinesStartXs().get(j);
			    		selectStartY=(int)drawingArea.getLines().getLinesStartYs().get(j);
			    		selectEndX=(int)drawingArea.getLines().getLinesEndXs().get(j);
			    		selectEndY=(int)drawingArea.getLines().getLinesEndYs().get(j);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    
		  //sets initial x and y coordinates when select is being used by user an user clicked in an image
		    for(int j=0;j<drawingArea.getImages().getImages().size();j++) {
		    	try{
		    		if((Boolean)drawingArea.getImages().getImagesDraggable().get(j)==true) {
				    	if((int)drawingArea.getImages().getImagesDrawNumbers().get(j)==max) {
				    		selectStartX=(int)drawingArea.getImages().getImagesStartXs().get(j);
				    		selectStartY=(int)drawingArea.getImages().getImagesStartYs().get(j);
				    		selectEndX=(int)drawingArea.getImages().getImagesEndXs().get(j);
				    		selectEndY=(int)drawingArea.getImages().getImagesEndYs().get(j);
				    	}
		    		}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		}
	}
	
	public void mouseDragged(MouseEvent me, DrawingArea drawingArea) {
		mouseDraggedReleased(me, drawingArea);
	}
	
	public void mouseReleased(MouseEvent me, DrawingArea drawingArea) {
		mouseDraggedReleased(me, drawingArea);
	}
	
	public void mouseDraggedReleased(MouseEvent me, DrawingArea drawingArea) {
		
		drawingArea.getDrawings().setEndX(me.getX());
		drawingArea.getDrawings().setEndY(me.getY());
 		int changeX=drawingArea.getDrawings().getEndX()-drawingArea.getDrawings().getStartX();
 		int changeY=drawingArea.getDrawings().getEndY()-drawingArea.getDrawings().getStartY();
 		
 		//changes rectangle's start and end x and y coordinates if it is being moved by the user.
	    for(int j=0;j<drawingArea.getRectangles().getRectanglesColors().size();j++) {
	    	try{
		    	if((int)drawingArea.getRectangles().getRectanglesDrawNumbers().get(j)==selectedDrawingNumber) {
		    		drawingArea.getRectangles().getRectanglesStartXs().add(j,selectStartX+changeX);
		    		drawingArea.getRectangles().getRectanglesStartXs().remove(j+1);
		    		drawingArea.getRectangles().getRectanglesEndXs().add(j,selectEndX+changeX);
		    		drawingArea.getRectangles().getRectanglesEndXs().remove(j+1);
		    		drawingArea.getRectangles().getRectanglesStartYs().add(j,selectStartY+changeY);
		    		drawingArea.getRectangles().getRectanglesStartYs().remove(j+1);
		    		drawingArea.getRectangles().getRectanglesEndYs().add(j,selectEndY+changeY);
		    		drawingArea.getRectangles().getRectanglesEndYs().remove(j+1);
		    	}
	    	}catch(Exception e){
            	break;
            }
	    }
	    
 		//changes circle's start and end x and y coordinates if it is being moved by the user.
	    for(int j=0;j<drawingArea.getCircles().getCirclesColors().size();j++) {
	    	try{
		    	if((int)drawingArea.getCircles().getCirclesDrawNumbers().get(j)==selectedDrawingNumber) {
		    		drawingArea.getCircles().getCirclesStartXs().add(j,selectStartX+changeX);
		    		drawingArea.getCircles().getCirclesStartXs().remove(j+1);
		    		drawingArea.getCircles().getCirclesEndXs().add(j,selectEndX+changeX);
		    		drawingArea.getCircles().getCirclesEndXs().remove(j+1);
		    		drawingArea.getCircles().getCirclesStartYs().add(j,selectStartY+changeY);
		    		drawingArea.getCircles().getCirclesStartYs().remove(j+1);
		    		drawingArea.getCircles().getCirclesEndYs().add(j,selectEndY+changeY);
		    		drawingArea.getCircles().getCirclesEndYs().remove(j+1);
		    	}
	    	}catch(Exception e){
            	break;
            }
	    }
 		
	    //changes line's start and end x and y coordinates if it is being moved by the user.
	    for(int j=0;j<drawingArea.getLines().getLinesColors().size();j++) {
	    	try{
		    	if((int)drawingArea.getLines().getLinesDrawNumbers().get(j)==selectedDrawingNumber) {
		    		drawingArea.getLines().getLinesStartXs().add(j,selectStartX+changeX);
		    		drawingArea.getLines().getLinesStartXs().remove(j+1);
		    		drawingArea.getLines().getLinesEndXs().add(j,selectEndX+changeX);
		    		drawingArea.getLines().getLinesEndXs().remove(j+1);
		    		drawingArea.getLines().getLinesStartYs().add(j,selectStartY+changeY);
		    		drawingArea.getLines().getLinesStartYs().remove(j+1);
		    		drawingArea.getLines().getLinesEndYs().add(j,selectEndY+changeY);
		    		drawingArea.getLines().getLinesEndYs().remove(j+1);
		    	}
	    	}catch(Exception e){
            	break;
            }
	    }
	    
	  //changes image's start and end x and y coordinates if it is being moved by the user.
	    for(int j=0;j<drawingArea.getImages().getImages().size();j++) {
	    	try{
		    	if((int)drawingArea.getImages().getImagesDrawNumbers().get(j)==selectedDrawingNumber) {
		    		drawingArea.getImages().getImagesStartXs().add(j,selectStartX+changeX);
		    		drawingArea.getImages().getImagesStartXs().remove(j+1);
		    		drawingArea.getImages().getImagesEndXs().add(j,selectEndX+changeX);
		    		drawingArea.getImages().getImagesEndXs().remove(j+1);
		    		drawingArea.getImages().getImagesStartYs().add(j,selectStartY+changeY);
		    		drawingArea.getImages().getImagesStartYs().remove(j+1);
		    		drawingArea.getImages().getImagesEndYs().add(j,selectEndY+changeY);
		    		drawingArea.getImages().getImagesEndYs().remove(j+1);
		    	}
	    	}catch(Exception e){
            	break;
            }
	    }
	}
	
	//SETTERS
	public void setSelectedDrawingNumber(int newSelectedDrawingNumber) {
    	selectedDrawingNumber=newSelectedDrawingNumber;
    }
    
    public void setSelectStartX(int newSelectStartX) {
    	selectStartX=newSelectStartX;
    }
    
    public void setSelectStartY(int newSelectStartY) {
    	selectStartY=newSelectStartY;
    }
    
    public void setSelectEndX(int newSelectEndX) {
    	selectEndX=newSelectEndX;
    }
    
    public void setSelectEndY(int newSelectEndY) {
    	selectEndY=newSelectEndY;
    }
    
    //GETTERS
    public int getSelectedDrawingNumber() {
    	return selectedDrawingNumber;
    }
    
    public int getSelectStartX() {
    	return selectStartX;
    }
    
    public int getSelectStartY() {
    	return selectStartY;
    }
    
    public int getSelectEndX() {
    	return selectEndX;
    }
    
    public int getSelectEndY() {
    	return selectEndY;
    }
}
