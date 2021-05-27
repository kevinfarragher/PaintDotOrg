package paintdotorg;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class ImageDrawings extends NonShapeDrawings{
	
	//variables to store each image's image, start and end x and y coordinates, drag status, and when drawn
	LinkedList<BufferedImage>images= new LinkedList<BufferedImage>();
    LinkedList<Integer>images_startX= new LinkedList<Integer>();
    LinkedList<Integer>images_startY= new LinkedList<Integer>();
    LinkedList<Integer>images_endX= new LinkedList<Integer>();
    LinkedList<Integer>images_endY= new LinkedList<Integer>();
    LinkedList<Boolean>images_draggable= new LinkedList<Boolean>();
    LinkedList<Integer>images_drawNumber= new LinkedList<Integer>();
    
    //used to draw an image
    public void drawImage(DrawingArea drawingArea, Graphics g) {
    	
    	try {
    	g.drawImage(drawingArea.getUserIOActions().getLoadedImage(), 0, 0, drawingArea.getUserIOActions().getLoadedImage().getWidth(), drawingArea.getUserIOActions().getLoadedImage().getHeight(), drawingArea);
    	images.add(drawingArea.getUserIOActions().getLoadedImage());
    	images_startX.add(0);
    	images_startY.add(0);
    	images_endX.add(drawingArea.getUserIOActions().getLoadedImage().getWidth());
    	images_endY.add(drawingArea.getUserIOActions().getLoadedImage().getHeight());
    	
    	if(drawingArea.getUserIOActions().isLoadedImageDraggable()==true) {
    		images_draggable.add(true);
    	}
    	else {
    		images_draggable.add(false);
    	}
    	
      	drawingArea.setNumDrawings(drawingArea.getNumDrawings()+1);
      	images_drawNumber.add(drawingArea.getNumDrawings());
    	}catch (Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void deleteAll() {
    	images.removeAll(images);
        images_startX.removeAll(images_startX);
        images_startY.removeAll(images_startY);
        images_endX.removeAll(images_endX);
        images_endY.removeAll(images_endY);
        images_draggable.removeAll(images_draggable);
        images_drawNumber.removeAll(images_drawNumber);
    }
    
    //GETTERS
    public LinkedList getImages(){
    	return images;
    }
    
    public LinkedList getImagesStartXs(){
    	return images_startX;
    }
    
    public LinkedList getImagesStartYs(){
    	return images_startY;
    }
    
    public LinkedList getImagesEndXs(){
    	return images_endX;
    }
    
    public LinkedList getImagesEndYs(){
    	return images_endY;
    }
    
    public LinkedList getImagesDraggable(){
    	return images_draggable;
    }
    
    public LinkedList getImagesDrawNumbers(){
    	return images_drawNumber;
    }
   
}
