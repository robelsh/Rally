import java.awt.Image;

import javax.swing.ImageIcon;


public class VoitureVerte extends Vehicule  {
	public Image image;
	public ImageIcon voiture_verte; 
	
	
	

	public VoitureVerte (double x, double y, double ad, double fr,double vl){
		super (x, y, 0, Math.PI/2, ad, fr, vl ); // définition des caractéristique de la voiture rose
		
		voiture_verte = new ImageIcon(this.getClass().getResource("v_verte.png"));
		image = voiture_verte.getImage();
		
	}


	public Image getImage() {
		
		return image;	
	}
	    

	public ImageIcon getImageIcon() {
		
		return voiture_verte;	
	}

	
	
	
}
