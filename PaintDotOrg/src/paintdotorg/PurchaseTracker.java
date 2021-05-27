package paintdotorg;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

public class PurchaseTracker {
	
	    //variables to keep track of cost to purchase all features and the number of features still available to purchase
		private int costToPurchaseAllFeatures=300;
		private int featuresToPurchase=20;
		
		//variables to keep track of actions being locked or not
		private boolean pencilBought=true;
		private boolean saveBought=true;
		private boolean saveAsBought=true;
		private boolean openBought=false;
		private boolean openRecentBought=false;
		private boolean exportBought=false;
		private boolean newProjectBought=false;
		private boolean eraserBought=false;
		private boolean fillBought=false;
		private boolean textBought=false;
		private boolean rectangleBought=false;
		private boolean circleBought=false;
		private boolean lineBought=false;
		private boolean lineThicknessBought=false;
		private boolean typeTextBought=true;
		private boolean pencilColorBought=false;
		private boolean fillColorBought=false;
		private boolean textColorBought=false;
		private boolean textFontBought=false;
		private boolean textStyleBought=false;
		private boolean textSizeBought=false;
		private boolean selectBought=false;
		private boolean importImageBought=false;
		private boolean rotateBought=false;
		
		//GETTERS and SETTERS
		public int getCostToPurchaseAllFeatures() {
			return costToPurchaseAllFeatures;
		}
		public void setCostToPurchaseAllFeatures(int costToPurchaseAllFeatures) {
			this.costToPurchaseAllFeatures = costToPurchaseAllFeatures;
		}
		public int getFeaturesToPurchase() {
			return featuresToPurchase;
		}
		public void setFeaturesToPurchase(int featuresToPurchase) {
			this.featuresToPurchase = featuresToPurchase;
		}
		public boolean isPencilBought() {
			return pencilBought;
		}
		public void setPencilBought(boolean pencilBought) {
			this.pencilBought = pencilBought;
		}
		public boolean isSaveBought() {
			return saveBought;
		}
		public void setSaveBought(boolean saveBought) {
			this.saveBought = saveBought;
		}
		public boolean isSaveAsBought() {
			return saveAsBought;
		}
		public void setSaveAsBought(boolean saveAsBought) {
			this.saveAsBought = saveAsBought;
		}
		public boolean isOpenBought() {
			return openBought;
		}
		public void setOpenBought(boolean openBought) {
			this.openBought = openBought;
		}
		public boolean isOpenRecentBought() {
			return openRecentBought;
		}
		public void setOpenRecentBought(boolean openRecentBought) {
			this.openRecentBought = openRecentBought;
		}
		public boolean isExportBought() {
			return exportBought;
		}
		public void setExportBought(boolean exportBought) {
			this.exportBought = exportBought;
		}
		public boolean isNewProjectBought() {
			return newProjectBought;
		}
		public void setNewProjectBought(boolean newProjectBought) {
			this.newProjectBought = newProjectBought;
		}
		public boolean isEraserBought() {
			return eraserBought;
		}
		public void setEraserBought(boolean eraserBought) {
			this.eraserBought = eraserBought;
		}
		public boolean isFillBought() {
			return fillBought;
		}
		public void setFillBought(boolean fillBought) {
			this.fillBought = fillBought;
		}
		public boolean isTextBought() {
			return textBought;
		}
		public void setTextBought(boolean textBought) {
			this.textBought = textBought;
		}
		public boolean isRectangleBought() {
			return rectangleBought;
		}
		public void setRectangleBought(boolean rectangleBought) {
			this.rectangleBought = rectangleBought;
		}
		public boolean isCircleBought() {
			return circleBought;
		}
		public void setCircleBought(boolean circleBought) {
			this.circleBought = circleBought;
		}
		public boolean isLineBought() {
			return lineBought;
		}
		public void setLineBought(boolean lineBought) {
			this.lineBought = lineBought;
		}
		public boolean isLineThicknessBought() {
			return lineThicknessBought;
		}
		public void setLineThicknessBought(boolean lineThicknessBought) {
			this.lineThicknessBought = lineThicknessBought;
		}
		public boolean isTypeTextBought() {
			return typeTextBought;
		}
		public void setTypeTextBought(boolean typeTextBought) {
			this.typeTextBought = typeTextBought;
		}
		public boolean isPencilColorBought() {
			return pencilColorBought;
		}
		public void setPencilColorBought(boolean pencilColorBought) {
			this.pencilColorBought = pencilColorBought;
		}
		public boolean isFillColorBought() {
			return fillColorBought;
		}
		public void setFillColorBought(boolean fillColorBought) {
			this.fillColorBought = fillColorBought;
		}
		public boolean isTextColorBought() {
			return textColorBought;
		}
		public void setTextColorBought(boolean textColorBought) {
			this.textColorBought = textColorBought;
		}
		public boolean isTextFontBought() {
			return textFontBought;
		}
		public void setTextFontBought(boolean textFontBought) {
			this.textFontBought = textFontBought;
		}
		public boolean isTextStyleBought() {
			return textStyleBought;
		}
		public void setTextStyleBought(boolean textStyleBought) {
			this.textStyleBought = textStyleBought;
		}
		public boolean isTextSizeBought() {
			return textSizeBought;
		}
		public void setTextSizeBought(boolean textSizeBought) {
			this.textSizeBought = textSizeBought;
		}
		public boolean isSelectBought() {
			return selectBought;
		}
		public void setSelectBought(boolean selectBought) {
			this.selectBought = selectBought;
		}
		public boolean isImportImageBought() {
			return importImageBought;
		}
		public void setImportImageBought(boolean importImageBought) {
			this.importImageBought = importImageBought;
		}
		public boolean isRotateBought() {
			return rotateBought;
		}
		public void setRotateBought(boolean rotateBought) {
			this.rotateBought = rotateBought;
		}
		
}
