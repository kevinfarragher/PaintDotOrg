package paintdotorg_skeleton_code;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class IOActions {
	
	//variables to store save path and image to load
		private Path savePath=null;
		private File saveFile=null;
		private BufferedImage loadedImage;
		private boolean openRecent=false;
		
		//boolean to determine whether loaded image is draggable or not
	    private boolean loadedImageDraggable=true;

	//actions performed when saving. User can choose where to save a JPEG file of their drawings.
    public void saveAs(DrawingArea drawingArea) {
    	
    	BufferedImage image = new BufferedImage(drawingArea.getWidth(),drawingArea.getHeight(), BufferedImage.TYPE_INT_RGB);
    	try {
	    	JFileChooser jFile = new JFileChooser();
	        jFile.showSaveDialog(null);
	        savePath=jFile.getSelectedFile().toPath();
	        JOptionPane.showMessageDialog(null, savePath.toString());
	    	Graphics2D g2 = image.createGraphics();
			drawingArea.paint(g2);
			ImageIO.write(image, "JPEG", new File(savePath.toString()+".jpg"));
			JOptionPane.showMessageDialog(null, "The file was saved to: " +  savePath.toString(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
			saveFile=new File(savePath.toString()+".jpg");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Save path invalid/No save path chosen", "Save Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	
    }
    
  //actions performed when saving. Drawings saved to existing JPEG file path.
    public void save(DrawingArea drawingArea) {
    	
    	BufferedImage image = new BufferedImage(drawingArea.getWidth(),drawingArea.getHeight(), BufferedImage.TYPE_INT_RGB);
    	if(savePath==null) {
    		saveAs(drawingArea);
    	}
    	Graphics2D g2 = image.createGraphics();
		drawingArea.paint(g2);
		try{
			ImageIO.write(image, "JPEG", saveFile);
			JOptionPane.showMessageDialog(null, "The file was saved to: " +  savePath.toString(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Save path invalid/No save path chosen", "Save Error", JOptionPane.INFORMATION_MESSAGE);
		}
		
    }
    
  //Actions performed when file is chosen to be opened.
    public void open(DrawingArea drawingArea) {
    	
    	drawingArea.getPaths().deleteAll();
    	drawingArea.getRectangles().deleteAll();
    	drawingArea.getCircles().deleteAll();
    	drawingArea.getLines().deleteAll();
    	drawingArea.getTexts().deleteAll();
    	drawingArea.getImages().deleteAll();
    	
    	loadedImageDraggable=false;
    	drawingArea.setDrawLoadingImage(true);
    	drawingArea.setDrawCircle(false);
    	drawingArea.setDrawRectangle(false);
    	drawingArea.setDrawLine(false);
    	drawingArea.setDrawPencil(false);
    	drawingArea.setDrawEraser(false);
    	drawingArea.setDrawText(false);
    	drawingArea.setDrawFill(false);
    	drawingArea.setDrawInitialize(false);
    	drawingArea.setDrawSelect(false);
    	
    	try {
	    	JFileChooser jFile = new JFileChooser();
	    	FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","bmp");
	        jFile.addChoosableFileFilter(filter);
	        jFile.showOpenDialog(null);
	        Path loadPath = jFile.getSelectedFile().toPath();
			loadedImage=ImageIO.read(new File(loadPath.toString()));
			savePath=loadPath;
			saveFile=new File(loadPath.toString());
			drawingArea.repaint();
			JOptionPane.showMessageDialog(null,"The file " + loadPath.toString() + " has been opened","Open Project", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
		}
    	
    }
    
  //actions performed when a recent drawing file is opened
    public void openRecent(DrawingArea drawingArea,Path loadFilePath) {
    	
    	if(openRecent==false) {
    		openRecent=true;
    		return;
    	}
    	
    	drawingArea.getPaths().deleteAll();
    	drawingArea.getRectangles().deleteAll();
    	drawingArea.getCircles().deleteAll();
    	drawingArea.getLines().deleteAll();
    	drawingArea.getTexts().deleteAll();
    	drawingArea.getImages().deleteAll();
    	
    	loadedImageDraggable=false;
    	drawingArea.setDrawLoadingImage(true);
    	drawingArea.setDrawCircle(false);
    	drawingArea.setDrawRectangle(false);
    	drawingArea.setDrawLine(false);
    	drawingArea.setDrawPencil(false);
    	drawingArea.setDrawEraser(false);
    	drawingArea.setDrawText(false);
    	drawingArea.setDrawFill(false);
    	drawingArea.setDrawInitialize(false);
    	drawingArea.setDrawSelect(false);
		
    	try{
			loadedImage=ImageIO.read(new File(loadFilePath.toString()));
			savePath=loadFilePath;
			saveFile=new File(loadFilePath.toString());
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
		}
		drawingArea.repaint();
		JOptionPane.showMessageDialog(null,"The file " + loadFilePath.toString() + " has been opened","Open Project", JOptionPane.INFORMATION_MESSAGE);

    }
    
  //Actions performed when new project is create
    public void newProject(DrawingArea drawingArea) {
    	
    	savePath=null;
    	saveFile=null;
    	drawingArea.getPaths().deleteAll();
    	drawingArea.getRectangles().deleteAll();
    	drawingArea.getCircles().deleteAll();
    	drawingArea.getLines().deleteAll();
    	drawingArea.getTexts().deleteAll();
    	drawingArea.getImages().deleteAll();
    	
    	loadedImageDraggable=false;
    	drawingArea.setDrawLoadingImage(false);
    	drawingArea.setDrawCircle(false);
    	drawingArea.setDrawRectangle(false);
    	drawingArea.setDrawLine(false);
    	drawingArea.setDrawPencil(false);
    	drawingArea.setDrawEraser(false);
    	drawingArea.setDrawText(false);
    	drawingArea.setDrawFill(false);
    	drawingArea.setDrawInitialize(true);
    	drawingArea.setDrawSelect(false);
    	
    	drawingArea.initialize();
		JOptionPane.showMessageDialog(null,"A new project has been started","New Project", JOptionPane.INFORMATION_MESSAGE);

    }
    
  //Actions performed when image is imported from a file and inserted into drawing
    public void importImage(DrawingArea drawingArea) {
    	
    	loadedImageDraggable=true;
    	drawingArea.setDrawLoadingImage(true);
    	drawingArea.setDrawCircle(false);
    	drawingArea.setDrawRectangle(false);
    	drawingArea.setDrawLine(false);
    	drawingArea.setDrawPencil(false);
    	drawingArea.setDrawEraser(false);
    	drawingArea.setDrawText(false);
    	drawingArea.setDrawFill(false);
    	drawingArea.setDrawInitialize(false);
    	drawingArea.setDrawSelect(false);
    	
    	try {
	    	
    		JFileChooser file = new JFileChooser();
	        
    		//filter the files
	        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg","gif","png","bmp");
	        file.addChoosableFileFilter(filter);
	        int result = file.showOpenDialog(null);
	        
	        //if the user clicks on a save file in Jfilechooser
	        if(result == JFileChooser.APPROVE_OPTION){
	            File selectedFile = file.getSelectedFile();
	            String path = selectedFile.getAbsolutePath();
				loadedImage=ImageIO.read(new File(path));
			}     
        }catch (IOException e1) {
    		e1.printStackTrace();
    		JOptionPane.showMessageDialog(null, "Invalid load file/No file was chosen to load", "Load Error", JOptionPane.INFORMATION_MESSAGE);
    	}   
		drawingArea.repaint(); 
    }
	
    //SETTERS
    public void setSavePath(Path newSavePath) {
    	savePath=newSavePath;
    }
    
    public void setSaveFile(File newSaveFile) {
    	saveFile=newSaveFile;
    }
    
    public void setLoadedImage(BufferedImage newLoadedImage) {
    	loadedImage=newLoadedImage;
    }
    
    public void setOpenRecent(boolean newOpenRecent) {
    	openRecent=newOpenRecent;
    }
    
    public void setLoadedImageDraggable(boolean newLoadedImageDraggable) {
    	loadedImageDraggable=newLoadedImageDraggable;
    }
    
    //GETTERS

	public Path getSavePath() {
		return savePath;
	}

	public File getSaveFile() {
		return saveFile;
	}

	public BufferedImage getLoadedImage() {
		return loadedImage;
	}

	public boolean isOpenRecent() {
		return openRecent;
	}

	public boolean isLoadedImageDraggable() {
		return loadedImageDraggable;
	}
    
}
