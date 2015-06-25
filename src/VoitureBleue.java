import java.awt.Image;
import javax.swing.ImageIcon;



public class VoitureBleue extends Vehicule{

	public Image image;
	public ImageIcon voiture_bleue; 
	
	
	

	public VoitureBleue (double x, double y, double ad, double fr,double vl){
		super (x, y, 0, Math.PI/2, ad, fr, vl ); 
		
		voiture_bleue = new ImageIcon(this.getClass().getResource("v_bleue.png"));
		image = voiture_bleue.getImage();
		
		
	}


	public Image getImage() {
		
		return image;	
	}

public ImageIcon getImageIcon() {
		
		return voiture_bleue;	
	}
	
}
