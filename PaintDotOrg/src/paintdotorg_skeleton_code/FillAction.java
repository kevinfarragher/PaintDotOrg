package paintdotorg_skeleton_code;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class FillAction {
	
	public void setFillColor(DrawingArea drawingArea, Graphics g) {
		g.setColor(drawingArea.getShapes().getFillColor());
	}

	public void mousePressed(MouseEvent me, DrawingArea drawingArea) {
		
		//each shape containing inside itself the point where the mouse was clicked is added to this list.
		LinkedList<Integer>drawingsHavingClick= new LinkedList<Integer>();
		
		//loops through each rectangle to see if it contains the point where the mouse was clicked. If so, is added to the list of shapes with the click.
		for(int j=0;j<drawingArea.getRectangles().getRectanglesColors().size();j++) { 
			try {
		    	if(me.getX()>=(int)drawingArea.getRectangles().getRectanglesStartXs().get(j) && me.getX()<=(int)drawingArea.getRectangles().getRectanglesEndXs().get(j) && me.getY()>=(int)drawingArea.getRectangles().getRectanglesStartYs().get(j) && me.getY()<=(int)drawingArea.getRectangles().getRectanglesEndYs().get(j)) {
		    		drawingsHavingClick.add((int)drawingArea.getRectangles().getRectanglesDrawNumbers().get(j));
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
	    
	    //determines most recent shape drawn out of all shapes with the click 
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
		    
		    //changes rectangle fill color and fill status if it is found to be the most recent shape drawn out of all shapes with the click
		    for(int j=0;j<drawingArea.getRectangles().getRectanglesColors().size();j++) {
		    	try{
			    	if((int)drawingArea.getRectangles().getRectanglesDrawNumbers().get(j)==max) {
			    		drawingArea.getRectangles().getRectanglesColors().add(j, drawingArea.getShapes().getFillColor());
			    		drawingArea.getRectangles().getRectanglesColors().remove(j+1);
			    		drawingArea.getRectangles().getRectanglesFilled().add(j, true);
			    		drawingArea.getRectangles().getRectanglesFilled().remove(j+1);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    
		    //changes circle fill color and fill status if it is found to be the most recent shape drawn out of all shapes with the click
		    for(int j=0;j<drawingArea.getCircles().getCirclesColors().size();j++) {
		    	try{
			    	if((int)drawingArea.getCircles().getCirclesDrawNumbers().get(j)==max) {
			    		drawingArea.getCircles().getCirclesColors().add(j, drawingArea.getShapes().getFillColor());
			    		drawingArea.getCircles().getCirclesColors().remove(j+1);
			    		drawingArea.getCircles().getCirclesFilled().add(j, true);
			    		drawingArea.getCircles().getCirclesFilled().remove(j+1);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		    
		  //changes line fill color if it is found to be the most recent shape drawn out of all shapes with the click
		    for(int j=0;j<drawingArea.getLines().getLinesDrawNumbers().size();j++) { //new code
		    	try {
			    	if((int)drawingArea.getLines().getLinesDrawNumbers().get(j)==max) {
			    		drawingArea.getLines().getLinesColors().add(j, drawingArea.getShapes().getFillColor());
			    		drawingArea.getLines().getLinesColors().remove(j+1);
			    	}
		    	}catch(Exception e){
	            	break;
	            }
		    }
		}
	}
}
