
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;




@SuppressWarnings("serial")  
public class Circuit extends JPanel {
	
	public Vehicule vehicule;

	FileReader fichier = null;
	BufferedReader lecteur = null;
	String tmp[] = new String[50];
	char data[][]=new char[50][50];
	
	
	

	
	public Circuit(String img_circuit,String source) {
		super();
		
		
		vehicule = new VoitureBleue(50, 285, 800 , 0.2 , 10);
			
		build(vehicule,img_circuit,source);  
	
		
		
	}
	
	
	
public Circuit(String img_circuit,String source,int i) {
		super();
		
		if (i==1) vehicule = new VoitureBleue(50, 285,800, 0.2 , 10);
		else if (i==2)	vehicule = new VoitureVerte(50, 285, 500 , 0.09 , 10);
		else if (i==3) 	vehicule = new VoitureRose(50, 285, 250 , 0.07 , 10);
		else vehicule = new VoitureRouge(50, 285, 100 , 0.01 , 10);
		
		
		build(vehicule,img_circuit,source);  
		
	}
	
	
	private void build(Vehicule vehicule,String img_circuit,String source){
		
		// Dimension et definition du jPanel
		addKeyListener(new TAdapter());
		setFocusable(true);
		setDoubleBuffered(true);
		setBounds(0, 0, 750, 750); //Dimension panel où affichage course
		
		
		
		// Image de fond
		 ImageIcon image_circuit = new ImageIcon(this.getClass().getResource(img_circuit)); //chargement image de fond
         JLabel fond = new JLabel(image_circuit); 
         Image image = image_circuit.getImage(); 
         BufferedImage bi = new BufferedImage(750, 750, BufferedImage.TYPE_INT_ARGB); 
        
        
         Graphics g = bi.createGraphics(); 
         g.drawImage(image, 10, 0, 750, 750, null); 
         ImageIcon image_redimensionnee = new ImageIcon(bi); 
         
         add(fond);
         fond.setIcon(image_redimensionnee);

		
		
		
		
		// Recupération des caractéristiques du terrain :
		
		try {
			//ouverture du fichier 
			fichier = new FileReader(source);
		} catch (FileNotFoundException e) {
			System.out.println("ouverture du fichier" + source + "impossible");
			System.out.println(e);
		}
		
		try {
			BufferedReader buff = new BufferedReader(fichier);
			String l;
			int i = 0;
			int j = 0;

			
			while ((l=buff.readLine())!=null){
				tmp[j] = l;
				for(i=0; i<50;i++){
					data[i][j] = tmp[j].charAt(i);
				}
				j++;
				}

			buff.close(); 
		}		
		catch (Exception f){
			System.out.println(f.toString());
		}	
	}
	
	
	
	
	
	// Paint du JPanel circuit
    public void paint(Graphics g){
        super.paint(g);
    
      vehicule.draw(vehicule,g);
        

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    

 
  // KeyAdapter pour récupérer les propriétés des différents voitures 
    private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
            vehicule.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
            vehicule.keyPressed(e);
        }
    }


    
    
    // getteur du véhicule joueur
    public Vehicule get_vehicule() {
        return vehicule;
    }
    
  

    // guetteur tableau données terrain
	public char[][] getTable(){
		return this.data;
	}
	
   

	

	// Test unitaire pour vérifier si tableau données bien chargé.
	
	/*public static void main(String[] args) {
      
		char[][] Tab = new char[50][50];
		Circuit c = new Circuit();
		Tab = c.getTable();
		System.out.println(Tab[49][43]);
	}
	*/
	


	
}
