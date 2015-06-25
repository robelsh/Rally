import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial") 
public class Simulation extends JFrame implements ActionListener{
	
	Circuit circuit;
	public Vehicule vehicule ;
	
	private int tmp =0;
	private int tmp2=0;
	
	//boutons :
	JComboBox choix_couleur = new JComboBox();
	JComboBox choix_circuit = new JComboBox();
	JComboBox choix_nbtour = new JComboBox();
	JButton bouton_demarrer = new JButton("Nouvelle course");
	JPanel msgarrivee=new JPanel();
	
	//ecoute Jcombobox
	public int cxcir=1;
	public int cxvoi=1;
	public int tour=3;
	
	//nombre de tour sur la simu actuelle:
	public int nbtour;
	public int nbtourinit;

    // para pour chargement circuit
	String source;
	String img_circuit;
	
	
	// pour calcul tps au tour :
	public double[] tps; 

	
	public char data[][]=new char[50][50];  //propriété terrain
	public  int[] coo= new int[2];        // coo de la voiture
	
	
	// arret de la simulation :
	public Boolean stop=false;
	
	
	
	// 1er constructeur :
	public Simulation() {
		
		source="C1.txt";
		img_circuit="1.png";
		nbtour=3;
		nbtourinit=3;
		
		circuit = new Circuit(img_circuit,source);	
		build();  
		
        }
        
	// deuxieme constructeur:
	public Simulation(int choixcircuit,int choixvehicule, int tour) {
		super();	
		
		nbtour=tour;
		nbtourinit=tour;
		
    	
		// initialisation circuit :
		if (choixcircuit==1) {
			source="C1.txt";
			img_circuit="1.png";
		  }
		else if (choixcircuit==2){
			source="C2.txt";
			img_circuit="2.png";
		}
		else if (choixcircuit==3){
			source="C3.txt";
			img_circuit="3.png";
		}
		else if(choixcircuit==4){
			source="C4.txt";
		img_circuit="4.png";
		 }
		
		// choix voiture :
		circuit = new Circuit(img_circuit,source,choixvehicule);	
		
		build();  
        }
        
	
	
        private void build() {
        	
        	
        	// pour enregistrer le temps
        	tps=new double[nbtour+1];
        	
        	
 
       // titre :
        	ImageIcon titre = new ImageIcon(this.getClass().getResource("titre.png"));
        	JLabel titre2 = new JLabel(titre);
    		titre2.setBounds(875,20,titre.getIconWidth(),titre.getIconHeight());
    		titre2.setIcon(titre);
    		add(titre2,null);
        	
        	
        // Choix couleur voiture :	
        	JLabel txt_couleur = new JLabel(" Couleur de la voiture : ");
        	txt_couleur.setBounds(850,375,225, 75); 
            add(txt_couleur,null);
     	
    		
            choix_couleur.addItem("Bleue");
            choix_couleur.addItem("Verte");
            choix_couleur.addItem("Rose");
            choix_couleur.addItem("Rouge");
            choix_couleur.setBounds(850,400,225, 75); 
        	choix_couleur.setFocusable(false); //pour éviter perturber course
            choix_couleur.addActionListener(this);
        	add(choix_couleur,null);
            
         // Choix du circuit : 
            JLabel txt_circuit = new JLabel(" Choix du circuit : ");
        	txt_circuit.setBounds(850,325,225, 75); 
            add(txt_circuit,null);
                
            choix_circuit.addItem("Circuit 1");
            choix_circuit.addItem("Circuit 2");
            choix_circuit.addItem("Circuit 3");
            choix_circuit.addItem("Circuit 4");
            choix_circuit.setBounds(850,350,225, 75);
            choix_circuit.setFocusable(false);
            add(choix_circuit,null);
            
         // Choix du nombre de tours :
            JLabel txt_tour = new JLabel(" Nombre de tours : ");
        	txt_tour.setBounds(850,275,225, 75); 
            add(txt_tour,null);
            
            
            choix_nbtour.addItem("3");
            choix_nbtour.addItem("5");
            choix_nbtour.addItem("7");
            choix_nbtour.setBounds(850,300,225, 75);
            choix_nbtour.setFocusable(false);
            add(choix_nbtour,null);
            
        	

         //  Reinitialisation :
            
            bouton_demarrer.setBounds(770,460,400, 75);
            bouton_demarrer.setFocusable(false);
            bouton_demarrer.addActionListener(this);
    		add(bouton_demarrer,null);    		
           
    	
    		
    		// Message d'arrivée :
    	  		msgarrivee.setFocusable(false);
    			msgarrivee.setDoubleBuffered(true);
    			msgarrivee.setBounds(800, 540, 350, 200); 
    	        
    		   add(msgarrivee);
    		
    		
    		
    		
    	
    	// Compteur de vitesse, de temps (..) : 
    		
    		
    		Font font = new Font("Arial",Font.BOLD,14);

    		
    		JLabel compteur_vitesse = new JLabel();
    		compteur_vitesse.setBounds(790,75,300, 120);
    		compteur_vitesse.setFont(font);
    		add(compteur_vitesse,null);		
    		
    		JLabel compteur_temps = new JLabel();
    		compteur_temps.setBounds(790,50,350, 120);
    		compteur_temps.setFont(font);
    		add(compteur_temps,null);		
    		
    		JLabel compteur_distance = new JLabel();
    		compteur_distance.setBounds(790,100,300, 120);
    		compteur_distance.setFont(font);
    		add(compteur_distance,null);		
    		
    		JLabel compteur_tour = new JLabel();
    		compteur_tour.setBounds(790,25,300, 120);
    		compteur_tour.setFont(font);
    		add(compteur_tour,null);

    		JLabel compteur_vie = new JLabel();
    		compteur_vie.setBounds(790,125,300, 120);
    		compteur_vie.setFont(font);
    		add(compteur_vie,null);
    		
    		JLabel compteur_carb = new JLabel();
    		compteur_carb.setBounds(790,150,300,120);
    		compteur_carb.setFont(font);
    		add(compteur_carb,null);
    			    		
    		
    		
    		
    		add(circuit,null);  //JPanel
    		
    		  

        	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1200, 770);
            getContentPane().setLayout(null);
            setLocationRelativeTo(null);
            setTitle("Rallye Breizh Furious");
            setResizable(false);
            setVisible(true);
            
   
            int[] coo= new int[2];
            
            
            
        	vehicule=  circuit.get_vehicule();
        	data=circuit.getTable();
            
        // image du vehicule séléctionné;	
            JLabel image = new JLabel(vehicule.getImageIcon());
    		image.setBounds(875,227,vehicule.getImageIcon().getIconWidth(),vehicule.getImageIcon().getIconHeight());
    		image.setIcon(vehicule.getImageIcon());
    		add(image,2);
    		

        	
            
          //  Simulation :  
        	
   
			
			  //initialisation
			 vehicule.Deplace();
			 vehicule.Direction(); 
             vehicule.Distance(); 
             vehicule.Temps();
             vehicule.Carburant();
           
			
		
			
             
            while(nbtour!=-1 ){
            	
            	
            	
        		try{  
        			Thread.sleep(50);  // Pas de simulation en ms 
        			}catch(InterruptedException e){}
            	
        			
        			// MàJ des coo
            	coo=vehicule.coordonnees(vehicule.get_abs(), vehicule.get_ord());
            	
            
            	// gestion collision/deplacement
            	if (data[coo[0]][coo[1]]=='0'){
            	  vehicule.Collision();
            	}else vehicule.Deplace();
            	
            	 
            	
            	
            // compteur de tour	
            	
            	if (data[coo[0]][coo[1]]=='2' && tmp==0) tmp=1;// tmp pour s'assurer  changement tour une seule fois par passage sur la ligne
            	
                if (tmp==1){   
                	tps[nbtour]=vehicule.get_tps(); //enregistre temps au passage sur la ligne
              		nbtour=nbtour-1;
              		tmp=2;	}
            	
                if(data[coo[0]][coo[1]]=='1') tmp=0; 
              	
              	
              // regeneration dans la zone noir et jaune : 
                
                if(data[coo[0]][coo[1]]=='3') { 
                	
                	tmp2=tmp2+1;
                	
                	if( tmp2%5==0) {
                		if (vehicule.get_carb()<99) vehicule.set_carbinit(vehicule.get_carbinit()+2);
                		if (vehicule.get_carb()==99) vehicule.set_carbinit(vehicule.get_carbinit()+1);
                		if (vehicule.get_vie()<100) vehicule.set_vie(vehicule.get_vie()+1);
                	
        
                 }
                }
                

                
              //MàJ des differents paramètres :
             
                  vehicule.Direction();
                  vehicule.Distance(); 
                  vehicule.Temps();
                  vehicule.Carburant();
                
            		
            
            	// MàJ affichage :
                
                 circuit.repaint(); 
                 
        	
                 
                 
               //MàJ des compteurs (avec arrondis et coeff pour rendre cohérents)
                 
                compteur_vitesse.setText("Vitesse  (km/h)  " + Math.abs(Math.floor(8*vehicule.get_vit())/10));  
                compteur_temps.setText("Temps  (s)  " + Math.floor(10*vehicule.get_tps())/10); 
                compteur_distance.setText("Distance (m)  " + Math.floor(vehicule.get_dist())/2); 
                compteur_tour.setText("Nombre de tours restant : " + nbtour ); 
                compteur_vie.setText("Vie : " + vehicule.get_vie()+ "%" ); 
                compteur_carb.setText("Carburant : " + vehicule.get_carb()+ "%" ); 
                
              
               // Game over :
                
                
                if(vehicule.get_carb()==0 ||  vehicule.get_vie()==0 ) { 
              break;
            	}
                
                // si on appuie sur nouvelle course :
                
                if(stop){ 
                	break;
                }
        
             
        }
           
     
            
    		  
    	        
 // message de fin :
    		
    			  
   if(nbtour!=-1){ //Game Over :
	      
	     Font font2 = new Font("Arial",Font.BOLD,45);  
	   
    	JLabel gover = new JLabel("<html>  <br>" +" "+" "+" "+" "+" " +" " + "GAME OVER </html>") ;
	    gover.setFont(font2);  
        gover.setBounds(0, 0, 100, 100);
        msgarrivee.setBackground(Color.red);
    	msgarrivee.add(gover);
   }
     else {  // Arrivee :
    	 
    	   compteur_tour.setText("Nombre de tours restant : " + 0 ); 
    	   
    	    Font font3 = new Font("Arial",Font.BOLD,20);  
    	    JLabel arrivee;
    	    
    		if(nbtourinit==3){
    			arrivee = new JLabel("<html>  Arrivée ! <br>" + "" +
    				"<html> Tour 1 :" + Math.floor(10*(tps[2]-tps[3]))/10 +"s"+"<br>"+
    				"<html> Tour 2 :" + Math.floor(10*(tps[1]-tps[2]))/10 +"s"+ "<br>"+
    				"<html>Tour 3 :" + Math.floor(10*(tps[0]-tps[1]))/10 +"s"+	"</html>");
    			
    		} else if (nbtourinit==5)	{
    			arrivee = new JLabel("<html>  Arrivée ! <br>" + "" +
    	         	"<html> Tour 1 :" + Math.floor(10*(tps[4]-tps[5]))/10 +"s"+"<br>"+
    				"<html> Tour 2 :" + Math.floor(10*(tps[3]-tps[4]))/10 +"s"+ "<br>"+
    				"<html>Tour 3 :" + Math.floor(10*(tps[2]-tps[3]))/10 +"s"+ "<br>"+
    				"<html> Tour 4 :" + Math.floor(10*(tps[1]-tps[2]))/10 +"s"+ "<br>"+
    				"<html> Tour 5 :" + Math.floor(10*(tps[0]-tps[1]))/10 +"s"+"</html>");} 
    		else {
    			arrivee = new JLabel("<html>  Arrivée ! <br>" + "" +
    	         	"<html> Tour 1 :" + Math.floor(10*(tps[6]-tps[7]))/10 +"s"+"<br>"+
    				"<html> Tour 2 :" + Math.floor(10*(tps[5]-tps[6]))/10 +"s"+ "<br>"+
    				"<html>Tour 3 :" + Math.floor(10*(tps[4]-tps[5]))/10 +"s"+"<br>"+
    				"<html>Tour 4 :" + Math.floor(10*(tps[3]-tps[4]))/10 +"s"+ "<br>"+
    				"<html>Tour 5 :" + Math.floor(10*(tps[2]-tps[3]))/10 +"s"+ "<br>"+
    				"<html>Tour 6 :" + Math.floor(10*(tps[1]-tps[2]))/10 +"s"+ "<br>"+
    				"<html>Tour 7 :" + Math.floor(10*(tps[0]-tps[1]))/10 +"s"+ "</html>");} 
    		
    	
    	    arrivee.setFont(font3);  
            arrivee.setBounds(0, 0, 100, 100);
            msgarrivee.setBackground(Color.green);
        	msgarrivee.add(arrivee);
        	
        	  try {
        			Thread.sleep(7000);           // pour avoir le temps de lire
        		} catch (InterruptedException e) {
        		
        			e.printStackTrace();
        		} 

     
     
     }
    		
            
     try {
		Thread.sleep(3000);           // entre 2 simulations
	} catch (InterruptedException e) {
	
		e.printStackTrace();
	} 

        
        
 }
        
        
        // ecoute des boutons
      
        public void actionPerformed(ActionEvent e) {
    		
    		if (e.getSource() == bouton_demarrer){
    			
            // jcombobox choix nb tour :
    			if(choix_nbtour.getSelectedItem()=="3") {
    				tour=3;
    			}
    			else if (choix_nbtour.getSelectedItem()=="5") {
    				tour=5;
    			}
    			else if (choix_nbtour.getSelectedItem()=="7") {
    				tour=7;
    			}
    			
    			
    		// jcombobox choix circuit :
    			if(choix_circuit.getSelectedItem()=="Circuit 1") {
    				cxcir=1;
    			}
    			else if (choix_circuit.getSelectedItem()=="Circuit 2") {
    				cxcir=2;
    			}
    			else if (choix_circuit.getSelectedItem()=="Circuit 3") {
    				cxcir=3;
    			}
    			
    			else if (choix_circuit.getSelectedItem()=="Circuit 4") {
    				cxcir=4;
    			}
    	  // jcombobox choixvehicule :
    			
    			
    			if(choix_couleur.getSelectedItem()=="Bleue") {
    				cxvoi=1;
    			}
    			else if (choix_couleur.getSelectedItem()=="Verte") {
    				cxvoi=2;
    			}
    			else if (choix_couleur.getSelectedItem()=="Rose") {
    				cxvoi=3;
    			}
    			
    			else if (choix_couleur.getSelectedItem()=="Rouge") {
    				cxvoi=4;
    			}
    			
    		 stop=true;
    			
    			
    		}
 
    	
    		
        }
        
        
        
        // guetteurs des choix de boutons
        public int get_tour() {
            return tour;
            }
        
        
        public int get_cxcir() {
            return cxcir;
            }
      
        
        public int get_cxvoi() {
            return cxvoi;
            }
        
        
       
    
     /*// test unitaire :
  	  public static void main(String[] args) {
  	    
  		 	       new Simulation(1,1,3);
  	
  	    }*/

  	
}
        
        
        
       
	
	

