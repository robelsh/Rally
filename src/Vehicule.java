import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;



public class Vehicule {
  
    public Boolean debut=false;  // se déclenche dés qu'on touche une touche => debut réel de la simulation
	
	public Image image;
	public ImageIcon voiture;

    public double abscisse;
	public double ordonnee;
	public int int_ord;
	public int int_abs;
	
	public double vitesse;
	public double vitlim;
	public double acceleration;
	public double direction; 
	
	public double cap; 
	public double adherence; 
	
	public double frottement; 
	
	public double dt=0.05; // pas de simulation
	
    public double temps; 
    public double distance;  
    
   
    public int vie=100; // decrementer lorsque collision. si =0  Game Over 
    public int carb;
    public int carbinit=100; // pour gestion carburant (conso dépend de la distance) si =0 Game Over

       
    //listes qui enregistrent les coordonnes
    ArrayList<Double> list_abscisse = new ArrayList<Double>();
    ArrayList<Double> list_ordonnee = new ArrayList<Double>();

	
	//Constructeur
	
	public Vehicule (double x, double y, double v, double theta, double ad, double fr,double vl){
		
		abscisse=x; //abs initiale
		ordonnee=y; //ord initiale
		vitesse=v; // vitesse initiale
		cap=theta;  // cap initiale
		adherence=ad;
		frottement=fr;
		vitlim=vl;
		
		
        for(int i=0; i<10; i++){
            list_abscisse.add((double) x);  //initialisation des 9 premiers termes des listes pour calcul de la direction de la voiture
            list_ordonnee.add((double) y);
        }
	}


	 
	 
	//Methode deplace
	
	public void Deplace() {
		
		
	
		
	/*	if(Math.abs((cap-direction)%(2*Math.PI)) >Math.PI/3 & vitesse>vitlim )
		{
			
			 // avec dérapage 
			
			vitesse= vitesse  -frottement*vitesse;
			
		abscisse= abscisse + vitesse*Math.cos(direction)*dt;
		ordonnee= ordonnee + vitesse*Math.sin(direction)*dt;
		
		}
		else {
		*/
		
		
			// avancement normal
		
			  vitesse= vitesse +adherence*acceleration*dt - frottement*vitesse;
			 
			abscisse= abscisse + vitesse*Math.cos(cap)*dt;
			ordonnee= ordonnee + vitesse*Math.sin(cap)*dt;
		
		
		
		// enregistrement des coo
	if(debut) {  // =>  demarrage tps
		list_abscisse.add(abscisse);    
		list_ordonnee.add(ordonnee);	
	}
		
		
		//co pour affichage : passage en entier arondie.
		int_abs = (int)Math.round(abscisse);
		int_ord = (int)Math.round(ordonnee);
		
		
	}
	


//Methode calcul direction
	
public void Direction() {
    
    //coo vecteur de la voiture :
    
    double abs=list_abscisse.get(list_abscisse.size() - 1);
    double abs10=list_abscisse.get(list_abscisse.size() - 10);
    
    double ord=list_ordonnee.get(list_ordonnee.size() - 1);
    double ord10=list_ordonnee.get(list_ordonnee.size() - 10);
    
    if ((abs-abs10)!=0)   
    	
    	direction = Math.atan((ord-ord10)/(abs-abs10));
    
    }


    

// Methode calcul temps (size d'une des listes de coo *dt)
	
    public void Temps(){

        temps=(list_abscisse.size() - 10)*dt; // moins 10, car on a initialise la liste avec 10 zeros
        
        }
   
 // Methode position de la voiture retourne les coos de la voiture dans tableau du terrain :
    
    
    public int[] coordonnees(int x,int y){

    	int[] coo= new int[2];
    	
    	coo[0]=(int) Math.floor(x/15);
    	coo[1]=(int) Math.floor(y/15);
    	
    	
    	return coo;	
    }
   
    
    
 // Methode calcul de la distance parcourue
     
     public void Distance() {
   
             double abs=list_abscisse.get(list_abscisse.size()-1);
             double ord=list_ordonnee.get(list_abscisse.size()-1);
             
             double abs1=list_abscisse.get(list_abscisse.size()-2);
             double ord1=list_ordonnee.get(list_abscisse.size()-2);
     
             if (debut)  distance= distance + Math.sqrt((abs-abs1)*(abs-abs1)+(ord-ord1)*(ord-ord1));  
             else ;

     }
 	
     // Methode pour la gestion de carburant :
     
     public void Carburant(){
    	 
    	 
    	 carb= (int) (carbinit-Math.floor(distance/50));
     }
     
     
     // Methode pour dessiner voiture
     
     public Graphics2D draw(Vehicule v,Graphics g) {
    

         Graphics2D g2d = (Graphics2D)g;
         g2d.rotate(v.get_cap(), v.get_abs(),v.get_ord());
         g2d.drawImage(v.getImage(), v.get_abs(), v.get_ord(), 30,15,null);
   
         return g2d;
     }
     
    
   // Methode collision brique : 
     
     public void Collision(){
    	 
    	 // on remet voiture sur la piste
    	 set_abs(list_abscisse.get(list_abscisse.size() - 7));
    	 set_ord(list_ordonnee.get(list_ordonnee.size() - 7));
    	 
    	// elle s'arrete : (pas nul pouretre sur qu'elle se décolle)
    	 
    	 if (vitesse>0) set_vit(-10);
    	   else set_vit(5);
    	 
  	  // on augmente le dégat 
    	 
    	 vie=vie-10;
     }
    
    //guetteur et setteurs des variables
    
public int get_abs(){
    	return int_abs;
    	
    }

public void set_abs(double x)
{
	
  int_abs = (int)Math.round(x);
  abscisse = x;   //on met le "vrai" abscisse et pas l'arrondi
}

public int get_ord(){
	return int_ord;
	
}

public void set_ord(double y)
{
  int_ord = (int)Math.round(y);
  ordonnee = y;   
}

public double get_tps(){
	return temps;
	
}

public double get_dist(){
	return distance;
	
}

public double get_vit(){
	return vitesse;
	
}


public void set_vit(double v){
  vitesse = v;   
}



public void set_cap(double c){
	  cap = c;   
	}


public void set_vie(int d){
	  vie = d;   
	}
public int get_vie(){
	return vie;
	
}

public void set_carbinit(int c){
	  carbinit = c;   
	}
public int get_carbinit() {
    return carbinit;
}
public int get_carb() {
    return carb;
}





public Image getImage() {
	
	return image;	
}


public ImageIcon getImageIcon() {
	
	return voiture;	
}





public double get_dir() {
    return direction;
}


public double get_cap() {
    return cap;
}





    
    
// Ecoute Clavier pour modification cap acceleration
	public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case 38 : // touche du haut
                acceleration =  1;
                debut=true; // Pour demarrer la course
                break;
            case 40 : // touche du bas
                acceleration = - 0.25;
               
                break;
		}
		
        switch(e.getKeyCode()){
            case 39 : // touche de droite
            	if(vitesse>0)
                cap=cap + Math.PI/30;
            	else cap=cap - Math.PI/20;
                break;
                
            case 37 : // touche de gauche 
            	if(vitesse>0)
            		cap=cap - Math.PI/30;  
            	else cap=cap + Math.PI/20;
                break;
		}
		
	}



   public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode()){
	            case 38 : // touche du haut
	                acceleration = 0;
	                break;
	            case 40: // touche du bas
	            	acceleration =0;
	            	break;
	            	
		
	}
    
   }

    
    
    
    
    
    
   //  Main : Test
	/*public static void main(String[] args) {
		
		Vehicule A=new Vehicule (0, 0, 0, Math.PI/2, 100, 1, 50); //initialisation cap a PI/2 a cause methode direction
		int[] coo= new int[2];
		
		
		
		
       for(int i=0; i<1000; i++){
    A.Deplace();  // simulation
    A.Direction(); //mise a jour de la direction
    coo=A.coordonnees(A.get_abs(), A.get_ord());
   
	System.out.println("abs "+A.get_abs());
	System.out.println("i "+coo[0]);
	
	
	System.out.println("ord "+A.get_ord());
	System.out.println("j "+coo[1]);
	

	}	*/
}







