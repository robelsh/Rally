
public class Course {
	public Simulation simu;
	public int a;
	public int b;
	public int c;
	
	public Course() {
	
    simu =new Simulation(); // etat initial
	
	while(true){   
    a=simu.get_cxcir();
	b=simu.get_cxvoi();
	c=simu.get_tour();
	simu.dispose();
	
	if(a==0 || b==0 || c==0){
	simu= new Simulation();
	   }
	else {simu= new Simulation(a,b,c);	
	  }
    }
	
    }
	
	
	
	
	public static void main(String[] args) {
  	    
	       new Course();

}

}
