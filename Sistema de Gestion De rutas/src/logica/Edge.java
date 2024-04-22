package logica;

import java.io.Serializable;

public class Edge implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5390522306586332467L;
	private int weight;
     private int timeMin;

     public Edge(int weight, int timeMin) {
         this.weight = weight;
         this.timeMin = timeMin;
     }

     public int getWeight() {
         return weight;
     }

     public int getTimeMin() {
         return timeMin;
     }

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public void setTimeMin(int timeMin) {
		this.timeMin = timeMin;
	}
     
 }


