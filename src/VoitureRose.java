import java.awt.Image;

import javax.swing.ImageIcon;


public class VoitureRose extends Vehicule {
	public Image image;
	public ImageIcon voiture_rose; 
	
	
	

	public VoitureRose (double x, double y, double ad, double fr,double vl){
		super (x, y, 0, Math.PI/2, ad, fr, vl ); 
		
		voiture_rose = new ImageIcon(this.getClass().getResource("v_rose.png"));
		image = voiture_rose.getImage();
		
	}


	public Image getImage() {
		
		return image;	
	}
	
public ImageIcon getImageIcon() {
		
		return voiture_rose;	
	}
}
