import java.awt.Image;

import javax.swing.ImageIcon;


public class VoitureRouge  extends Vehicule  {
	public Image image;
	public ImageIcon voiture_rouge; 
	
	
	

	public VoitureRouge (double x, double y, double ad, double fr,double vl){
		super (x, y, 0, Math.PI/2, ad, fr, vl );
		
		voiture_rouge = new ImageIcon(this.getClass().getResource("v_rouge.png"));
		image = voiture_rouge.getImage();
		
	}


	public Image getImage() {
		
		return image;	
	}
	
public ImageIcon getImageIcon() {
		
		return voiture_rouge;	
	}
}
