package paintdotorg;

import java.util.regex.*;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.awt.GraphicsEnvironment;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * 
 * @author linds
 *
 */

public class Driver {
	
	//variable for purchase tracking
	private PurchaseTracker purchaseTracker=new PurchaseTracker();

	public static void main(String args[]) {
		new Driver();
	}
		
	Driver(){
		//creates frame
		JFrame frame=new JFrame();
		frame.setBounds(0, 0, 940, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creates drawing area
		JPanel drawingArea=new DrawingArea();
		drawingArea.setBounds(10,100,900,410);
		drawingArea.setBackground(Color.WHITE);
		
		//creates background
		JPanel background=new JPanel();
		background.setBounds(0,0,750,550);
		background.setBackground(Color.DARK_GRAY);
		
		//creates tools area
		JPanel tools=new JPanel();
		tools.setBounds(0,0,940,90); 
		tools.setBackground(Color.LIGHT_GRAY);
		
		//Combo box used to open and load recent files
		JComboBox selectOpenRecent=new JComboBox();
		selectOpenRecent.setBounds(89,57,71,21);
		selectOpenRecent.setToolTipText("Load recently opened files");
		selectOpenRecent.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectOpenRecent);
			selectOpenRecent.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isOpenRecentBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the open recent feature purchased. The price to unlock the feature is $5. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the open recent feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setOpenRecentBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-5);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				selectOpenRecent.setBorder(BorderFactory.createEmptyBorder());
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 Path loadFilePath=(Path) selectOpenRecent.getSelectedItem();
					 ((DrawingArea) drawingArea).getUserIOActions().openRecent((DrawingArea) drawingArea,loadFilePath);
				 } });	
		
		//Button to save drawings as a JPEG file
		JButton saveAsButton=new JButton("Save As");
		saveAsButton.setBounds(12,10,71,21);
		saveAsButton.setToolTipText("Save your current drawings as a JPEG file to new file location");
		saveAsButton.setFont(new Font("Dialog", Font.PLAIN, 9));
		frame.add(saveAsButton);
			saveAsButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){  
					 ((DrawingArea) drawingArea).getUserIOActions().saveAs((DrawingArea) drawingArea);
				 } });
		
		//Button to save drawings to existing file path as a JPEG.
		JButton saveButton=new JButton("Save");
		saveButton.setBounds(12,34,71,21);
		saveButton.setToolTipText("Save your current drawings as a JPEG file to current file location");
		frame.add(saveButton);
			saveButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){   
					 ((DrawingArea) drawingArea).getUserIOActions().save((DrawingArea) drawingArea);
				 } });
			
		//Button to open a previous drawing from a file
		JButton openButton=new JButton("Open");
		openButton.setBounds(12,57,71,21);
		openButton.setToolTipText("Open a previous drawing");
		openButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(openButton);
			openButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isOpenBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the open feature purchased. The price to unlock the feature is $5. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the open feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setOpenBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-5);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				openButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 ((DrawingArea) drawingArea).getUserIOActions().open((DrawingArea) drawingArea);
					 Path filePath=((DrawingArea) drawingArea).getUserIOActions().getSavePath();
					 selectOpenRecent.addItem(filePath);
				 } });
		
		//Button to export drawings as a JPEG file
		JButton exportButton=new JButton("Export");
		exportButton.setBounds(89,34,71,21);
		exportButton.setToolTipText("Export your current drawings as a JPEG file");
		exportButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(exportButton);
			exportButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isExportBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the export feature purchased. The price to unlock the feature is $5. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the export feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setExportBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-5);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				exportButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 ((DrawingArea) drawingArea).getUserIOActions().saveAs((DrawingArea) drawingArea);
				 } });
			
		//Button to start a new project
		JButton newProjectButton=new JButton("New Project");
		newProjectButton.setBounds(89,10,71,21);
		newProjectButton.setToolTipText("Start a new project");
		newProjectButton.setFont(new Font("Dialog", Font.PLAIN, 6));
		newProjectButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(newProjectButton);
			newProjectButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isNewProjectBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the new project feature purchased. The price to unlock the feature is $5. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the new project feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setNewProjectBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-5);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				newProjectButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						} 
					 ((DrawingArea) drawingArea).getUserIOActions().newProject((DrawingArea) drawingArea);
				 } });
		
		//Button to enable drawing in pencil
		JButton pencilButton=new JButton("Pencil");
		pencilButton.setBounds(164,10,58,34);
		pencilButton.setToolTipText("Enable drawing with a pencil");
		pencilButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		frame.add(pencilButton);
			pencilButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){  
					((DrawingArea) drawingArea).drawPencil();
				 } });
		
		//Button to enable erasing
		JButton eraserButton=new JButton("Eraser");
		eraserButton.setBounds(164,45,58,34);
		eraserButton.setToolTipText("Enable erasing");
		eraserButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		eraserButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(eraserButton);
			eraserButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					if(purchaseTracker.isEraserBought()==false) {
						try {
							String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the eraser feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
							if(luhnCheck(creditCardNumber))
			    			{
			    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the eraser feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
			    				purchaseTracker.setEraserBought(true);
			    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
			    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
			    				eraserButton.setBorder(UIManager.getBorder("Button.border"));
			    			}
							else {
								JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
								return;
						    }
						}catch(Exception ef){
							JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
			    			return;
						}
					}
					((DrawingArea) drawingArea).drawEraser();
				 } });
		
		//Button to enable filling on any clicked shape.
		JButton fillButton=new JButton("Fill");
		fillButton.setBounds(228,10,58,34);
		fillButton.setToolTipText("Enable filling a clicked shape");
		fillButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		fillButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(fillButton);
			fillButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isFillBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the fill feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the fill feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setFillBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				fillButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
						((DrawingArea) drawingArea).drawFill();
				 } });	
		
		//Button for drawing a rectangle shape.
		JButton rectangleButton=new JButton("Rectangle");
		rectangleButton.setBounds(292,10,64,21);
		rectangleButton.setToolTipText("Draw a rectangle on the screen");
		rectangleButton.setFont(new Font("Dialog", Font.PLAIN, 6));
		rectangleButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(rectangleButton);
			rectangleButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isRectangleBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the rectangle feature purchased. The price to unlock the feature is $20. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the rectangle feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setRectangleBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-20);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				rectangleButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
						((DrawingArea) drawingArea).drawRectangle();
	
				 } });
		
		//Button for drawing a circle shape.
		JButton circleButton=new JButton("Circle");
		circleButton.setBounds(292,34,64,21);
		circleButton.setToolTipText("Draw a circle on the screen");
		circleButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		circleButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(circleButton);
			circleButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase if not bought
					 if(purchaseTracker.isCircleBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the circle feature purchased. The price to unlock the feature is $20. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the circle feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setCircleBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-20);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				circleButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
						((DrawingArea) drawingArea).drawCircle();
				 } });	  
		
		//Button for drawing a line shape
		JButton lineButton=new JButton("Line");
		lineButton.setBounds(292,57,64,21);
		lineButton.setToolTipText("Draw a line on the screen");
		lineButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		lineButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(lineButton);
			lineButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase if not bought
					 if(purchaseTracker.isLineBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the line feature purchased. The price to unlock the feature is $20. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the line feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setLineBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-20);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				lineButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
						((DrawingArea) drawingArea).drawLine();
				 } });
		
		//Slider to change line thickness when drawing, erasing, and for shapes
		JSlider lineThicknessSlider=new JSlider(JSlider.VERTICAL,1,10,5);
		lineThicknessSlider.setBounds(359,10,18,67);
		lineThicknessSlider.setToolTipText("Set line thickness for drawing in pencil, erasing, and shape borders");
		lineThicknessSlider.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(lineThicknessSlider);
			ChangeListener lineThicknessChangeListener=new ChangeListener() {
				  public void stateChanged(ChangeEvent e) {
					//popup to purchase if not bought
					  if(purchaseTracker.isLineThicknessBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the line thickness feature purchased. The price to unlock the feature is $15. If you wish to buy, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the line thickness feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setLineThicknessBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-15);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				lineThicknessSlider.setBorder(UIManager.getBorder("JSlider.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					  int newThickness=2;
					  JSlider source = (JSlider)e.getSource();
					  newThickness = (int)source.getValue();
				        if (!source.getValueIsAdjusting()) {
				        	((DrawingArea) drawingArea).getDrawings().setLineThickness(newThickness);
				        }
				  }
			  };
	    lineThicknessSlider.addChangeListener(lineThicknessChangeListener);
		
		JLabel typeText=new JLabel("Type text:");
		typeText.setBounds(380,13,58,13);
		frame.add(typeText);
		
		//Textfield to type desired text to display.
		JTextField typedText=new JTextField("default");
		typedText.setEnabled(false);
		typedText.setBounds(441,10,118,20);
		typedText.setToolTipText("Type desired text to display");
		frame.add(typedText);
		
		//Button to enable putting text on screen when clicked
		JButton textButton=new JButton("Text");
		textButton.setBounds(228,45,58,34);
		textButton.setToolTipText("Enable drawing desired text on screen where clicked ");
		textButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		textButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textButton);
			textButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isTextBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the text feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the text feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setTextBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				textButton.setBorder(UIManager.getBorder("Button.border"));
				    				typedText.setEnabled(true);
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 ((DrawingArea) drawingArea).drawText(); 
					 String newText=typedText.getText();
					 if(newText==null) {
						 newText="default";
						 typedText.setText("default");
					 } 
					 ((DrawingArea) drawingArea).getTexts().setTextToDisplay(newText); 
				 } });
		
		//Button to choose color to draw in with pencil
		JButton pencilColorButton=new JButton("<html>Pencil<br />Color</html>");
		pencilColorButton.setBounds(380,34,58,40);
		pencilColorButton.setToolTipText("Choose the pencil's color");
		pencilColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(pencilColorButton);
			pencilColorButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isPencilColorBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the pencil color feature purchased. The price to unlock the feature is $15. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the pencil color feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setPencilColorBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-15);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				pencilColorButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 Color newColor=JColorChooser.showDialog(null, "Stroke Color Chooser", Color.black);
					 ((DrawingArea) drawingArea).getPaths().setPointColor(newColor);
					 pencilButton.doClick();
				 } });  
		
		//Button to choose fill color for shape when clicked on.
		JButton fillColorButton=new JButton("<html>Fill<br />Color</html>");
		fillColorButton.setBounds(441,34,58,40);
		fillColorButton.setToolTipText("Choose the color for the fill of a clicked shape");
		fillColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(fillColorButton);
			fillColorButton.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){ 
					//popup to purchase if not bought
					 if(purchaseTracker.isFillColorBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the fill color feature purchased. The price to unlock the feature is $15. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the fill color feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setFillColorBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-15);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				fillColorButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 Color newColor=JColorChooser.showDialog(null, "Fill Color Chooser", Color.black); 
					 ((DrawingArea) drawingArea).getShapes().setFillColor(newColor);
					 fillButton.doClick();
				 } });	
		
		//Button to choose text color
		JButton textColorButton=new JButton("<html>Text<br />Color</html>");
		textColorButton.setBounds(501,34,58,40);
		textColorButton.setToolTipText("Choose the color to display text in");
		textColorButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(textColorButton);
			textColorButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isTextColorBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the text color feature purchased. The price to unlock the feature is $15. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the text color feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setTextColorBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-15);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				textColorButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 Color newTextColor=JColorChooser.showDialog(null, "Text Color Chooser", Color.black); 
					 ((DrawingArea) drawingArea).getTexts().setTextColor(newTextColor);
					 textButton.doClick();
				 } });
		
		JLabel textFont=new JLabel("Text font:");
		textFont.setBounds(565,15,57,13);
		frame.add(textFont);
		
		//Combo box to select text font
		JComboBox selectTextFont=new JComboBox();
		String fonts[] = 
			    GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			    for ( int i = 0; i < fonts.length; i++ )
			    {
			      selectTextFont.addItem(fonts[i]);
			    }
		selectTextFont.setBounds(625,10,121,21);
		selectTextFont.setToolTipText("Select desired text font");
		selectTextFont.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectTextFont);
			selectTextFont.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isTextFontBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the text font feature purchased. The price to unlock the feature is $10. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the text font feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setTextFontBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-10);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				selectTextFont.setBorder(BorderFactory.createEmptyBorder());
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 String newTextFont=(String)selectTextFont.getSelectedItem();
					 if(newTextFont==null) {
						 newTextFont="Times New Roman";
					 } 
					 ((DrawingArea) drawingArea).getTexts().setTextName(newTextFont);
				 } });
		
		JLabel textStyle=new JLabel("Text style:");
		textStyle.setBounds(565,38,60,13);
		frame.add(textStyle);
		
		//Combo box to select text style
		JComboBox selectTextStyle=new JComboBox();
		String[]fontStyles= {"0","1","2"};
		for ( int i = 0; i < fontStyles.length; i++ )
	    {
	      selectTextStyle.addItem(fontStyles[i]);
	    }
		
		selectTextStyle.setBounds(625,34,121,21);
		selectTextStyle.setToolTipText("Select desired text style. 0:Plain; 1:Bold; 2:Italic");
		selectTextStyle.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectTextStyle);
		selectTextStyle.addActionListener(new ActionListener(){  
			 public void actionPerformed(ActionEvent e){
				//popup to purchase if not bought
				 if(purchaseTracker.isTextStyleBought()==false) {
						try {
							String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the text style feature purchased. The price to unlock the feature is $10. If you wish to buy the feature, please enter your credit card number below:",null);
							if(luhnCheck(creditCardNumber))
			    			{
			    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the text style feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
			    				purchaseTracker.setTextStyleBought(true);
			    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-10);
			    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
			    				selectTextStyle.setBorder(BorderFactory.createEmptyBorder());
			    			}
							else {
								JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
								return;
						    }
						}catch(Exception ef){
							JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
			    			return;
						}
					}
				 int newTextStyle=Integer.parseInt((String) selectTextStyle.getSelectedItem());
				 ((DrawingArea) drawingArea).getTexts().setTextStyle(newTextStyle); 
			 } });	
		
		JLabel textSize=new JLabel("Text size:");
		textSize.setBounds(565,61,57,13);
		frame.add(textSize);
		
		//Combo box to select text size
		JComboBox selectTextSize=new JComboBox();
			selectTextSize.addItem("8");
			selectTextSize.addItem("9");
			selectTextSize.addItem("10");
			selectTextSize.addItem("11");
			selectTextSize.addItem("12");
			selectTextSize.addItem("14");
			selectTextSize.addItem("16");
			selectTextSize.addItem("18");
			selectTextSize.addItem("20");
			selectTextSize.addItem("22");
			selectTextSize.addItem("24");
			selectTextSize.addItem("26");
			selectTextSize.addItem("28");
			selectTextSize.addItem("36");
			selectTextSize.addItem("48");
			selectTextSize.addItem("72");
		selectTextSize.setBounds(625,58,121,21);
		selectTextSize.setToolTipText("Select desired text size ");
		selectTextSize.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectTextSize);
			selectTextSize.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isTextSizeBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the text size feature purchased. The price to unlock the feature is $10. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the text size feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setTextSizeBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-10);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				selectTextSize.setBorder(BorderFactory.createEmptyBorder());
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 int newTextSize=Integer.parseInt((String) selectTextSize.getSelectedItem());
					 ((DrawingArea) drawingArea).getTexts().setTextSize(newTextSize);
				 } });	
			
		//Button for selecting a shape to move around the screen
		JButton selectButton=new JButton("Select");
		selectButton.setBounds(752,10,64,21);
		selectButton.setToolTipText("Move a shape or image around the screen");
		selectButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		selectButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(selectButton);
			selectButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isSelectBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the select feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the select feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setSelectBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				selectButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 ((DrawingArea) drawingArea).drawSelect(); 
				 } });
			
		//Button to rotate clicked shape, image, or text
		JButton rotateButton=new JButton("Rotate");
		rotateButton.setBounds(752,34,64,21);
		rotateButton.setToolTipText("Click a drawn shape, text, or image to rotate it by 90 degree intervals");
		rotateButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		rotateButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(rotateButton);
			rotateButton.addActionListener(new ActionListener(){ 
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isRotateBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the rotate feature purchased. The price to unlock the feature is $25. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. Howevever, the rotate feature is not yet implemented. You just got scammed!","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setRotateBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-25);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				rotateButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}else {
		    				JOptionPane.showMessageDialog(frame,"The rotate feature is not yet implemented. You were scammed!","Can't Rotate", JOptionPane.INFORMATION_MESSAGE);
						}
				 } });
		
		//Button to insert image from file into drawing
		JButton importButton=new JButton("<html>Import/Choose<br />Image</html>");
		importButton.setBounds(752,57,64,21);
		importButton.setToolTipText("Choose an image from file to insert into your drawing");
		importButton.setFont(new Font("Dialog", Font.PLAIN, 8));
		importButton.setBorder(BorderFactory.createLineBorder(Color.red));
		frame.add(importButton);
			importButton.addActionListener(new ActionListener(){  
				 public void actionPerformed(ActionEvent e){
					//popup to purchase if not bought
					 if(purchaseTracker.isImportImageBought()==false) {
							try {
								String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have the import/select image feature purchased. The price to unlock the feature is $5. If you wish to buy the feature, please enter your credit card number below:",null);
								if(luhnCheck(creditCardNumber))
				    			{
				    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to the import/select image feature.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
				    				purchaseTracker.setImportImageBought(true);
				    				purchaseTracker.setCostToPurchaseAllFeatures(purchaseTracker.getCostToPurchaseAllFeatures()-5);
				    				purchaseTracker.setFeaturesToPurchase(purchaseTracker.getFeaturesToPurchase()-1);
				    				importButton.setBorder(UIManager.getBorder("Button.border"));
				    			}
								else {
									JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
									return;
							    }
							}catch(Exception ef){
								JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
				    			return;
							}
						}
					 ((DrawingArea) drawingArea).getUserIOActions().importImage((DrawingArea) drawingArea);
				 } }); 
		
		//Button to buy all the remaining purchasable features
		JButton buyAllRemainingFeaturesButton =new JButton("<html> Buy all </br> remaining </br> features </html>");
		buyAllRemainingFeaturesButton.setBounds(821,10,80,68);
		buyAllRemainingFeaturesButton.setToolTipText("Pay to unlock all features");
	    frame.add(buyAllRemainingFeaturesButton);
	    
	    buyAllRemainingFeaturesButton.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent actionEvent) {
	    		//popup to purchase all features if all features aren't bought
	    			if(purchaseTracker.isOpenRecentBought()==false || purchaseTracker.isOpenBought()==false || purchaseTracker.isExportBought()==false || purchaseTracker.isNewProjectBought()==false || purchaseTracker.isEraserBought()==false || purchaseTracker.isFillBought()==false || purchaseTracker.isRectangleBought()==false || purchaseTracker.isCircleBought()==false || purchaseTracker.isLineBought()==false || purchaseTracker.isLineThicknessBought()==false || purchaseTracker.isTextBought()==false || purchaseTracker.isPencilColorBought()==false || purchaseTracker.isFillColorBought()==false || purchaseTracker.isTextColorBought()==false || purchaseTracker.isTextFontBought()==false || purchaseTracker.isTextStyleBought()==false || purchaseTracker.isTextSizeBought()==false || purchaseTracker.isSelectBought()==false || purchaseTracker.isRotateBought()==false || purchaseTracker.isImportImageBought()==false) {
	    				try {
							String creditCardNumber=JOptionPane.showInputDialog(frame,"You do not have " + purchaseTracker.getFeaturesToPurchase() +" features purchased. The price to unlock the remaining features is $" + purchaseTracker.getCostToPurchaseAllFeatures() + ". If you wish to buy the remaining features, please enter your credit card number below:",null);
							if(luhnCheck(creditCardNumber))
			    			{
			    				JOptionPane.showMessageDialog(frame,"Credit Card Accepted. You now have access to all of the features.","Purchase Successful", JOptionPane.INFORMATION_MESSAGE);
			    				purchaseTracker.setPencilBought(true);
			    				purchaseTracker.setSaveBought(true);
			    				purchaseTracker.setSaveAsBought(true);
			    				purchaseTracker.setOpenBought(true);
			    				purchaseTracker.setOpenRecentBought(true);
			    				purchaseTracker.setExportBought(true);
			    				purchaseTracker.setNewProjectBought(true);
			    				purchaseTracker.setEraserBought(true);
			    				purchaseTracker.setFillBought(true);
			    				purchaseTracker.setTextBought(true);
			    				purchaseTracker.setRectangleBought(true);
			    				purchaseTracker.setCircleBought(true);
			    				purchaseTracker.setLineBought(true);
			    				purchaseTracker.setLineThicknessBought(true);
			    				purchaseTracker.setTypeTextBought(true);
			    				purchaseTracker.setPencilColorBought(true);
			    				purchaseTracker.setFillColorBought(true);
			    				purchaseTracker.setTextColorBought(true);
			    				purchaseTracker.setTextFontBought(true);
			    				purchaseTracker.setTextStyleBought(true);
			    				purchaseTracker.setTextSizeBought(true);
			    				purchaseTracker.setSelectBought(true);
			    				purchaseTracker.setImportImageBought(true);
			    				purchaseTracker.setRotateBought(true);
			    				purchaseTracker.setCostToPurchaseAllFeatures(0);
			    				purchaseTracker.setFeaturesToPurchase(0);
			    				
			    				eraserButton.setBorder(UIManager.getBorder("Button.border"));
			    				fillButton.setBorder(UIManager.getBorder("Button.border"));
			    				rectangleButton.setBorder(UIManager.getBorder("Button.border"));
			    				circleButton.setBorder(UIManager.getBorder("Button.border"));
			    				lineButton.setBorder(UIManager.getBorder("Button.border"));
			    				lineThicknessSlider.setBorder(UIManager.getBorder("Button.border"));
			    				textButton.setBorder(UIManager.getBorder("Button.border"));
			    				pencilColorButton.setBorder(UIManager.getBorder("Button.border"));
			    				fillColorButton.setBorder(UIManager.getBorder("Button.border"));
			    				textColorButton.setBorder(UIManager.getBorder("Button.border"));
			    				selectTextFont.setBorder(BorderFactory.createEmptyBorder());
			    				selectTextStyle.setBorder(BorderFactory.createEmptyBorder());
			    				selectTextSize.setBorder(BorderFactory.createEmptyBorder());
			    				selectButton.setBorder(UIManager.getBorder("Button.border"));
			    				rotateButton.setBorder(UIManager.getBorder("Button.border"));
			    				importButton.setBorder(UIManager.getBorder("Button.border"));
			    				openButton.setBorder(UIManager.getBorder("Button.border"));
			    				selectOpenRecent.setBorder(BorderFactory.createEmptyBorder());
			    				exportButton.setBorder(UIManager.getBorder("Button.border"));
			    				newProjectButton.setBorder(UIManager.getBorder("Button.border"));
			    				typedText.setEnabled(true);
			    				
			    				buyAllRemainingFeaturesButton.setEnabled(false);
			    			}
							else {
								JOptionPane.showMessageDialog(frame,"Credit Card Declined. Purchase Failed.","Purchase Failed", JOptionPane.INFORMATION_MESSAGE);
								return;
						    }
						}catch(Exception ef){
							JOptionPane.showMessageDialog(null, "Invalid input/No input", "Input Error", JOptionPane.INFORMATION_MESSAGE);
			    			return;
						}
	    			}
	    			else {
						JOptionPane.showMessageDialog(null, "All features have been purchased", "No features to purchase", JOptionPane.INFORMATION_MESSAGE);
						buyAllRemainingFeaturesButton.setEnabled(false);
	    			}
	    		}
	   });		

		frame.add(drawingArea);
		frame.add(tools);
		frame.add(background);
		
		frame.setVisible(true);
		frame.setTitle("PaintDotOrg");
		background.setVisible(true);
		tools.setVisible(true);
		drawingArea.setVisible(true);
		((DrawingArea)drawingArea).initialize();
	}
	
	public boolean luhnCheck(String card) {
        if (card == null)
            return false;
        char checkDigit = card.charAt(card.length() - 1);
        String digit = calculateCheckDigit(card.substring(0, card.length() - 1));
        return checkDigit == digit.charAt(0);
    }
	
	public String calculateCheckDigit(String card) {
        if (card == null)
            return null;
        String digit;
        /* convert to array of int for simplicity */
        int[] digits = new int[card.length()];
        for (int i = 0; i < card.length(); i++) {
            digits[i] = Character.getNumericValue(card.charAt(i));
        }
        
        /* double every other starting from right - jumping from 2 in 2 */
        for (int i = digits.length - 1; i >= 0; i -= 2)	{
            digits[i] += digits[i];
            
            /* taking the sum of digits grater than 10 - simple trick by substract 9 */
            if (digits[i] >= 10) {
                digits[i] = digits[i] - 9;
            }
        }
        int sum = 0;
        for (int i = 0; i < digits.length; i++) {
            sum += digits[i];
        }
        /* multiply by 9 step */
        sum = sum * 9;
        
        /* convert to string to be easier to take the last digit */
        digit = sum + "";
        return digit.substring(digit.length() - 1);
    }
	
	public PurchaseTracker getPurchaseTracker() {
		return purchaseTracker;
	}

}