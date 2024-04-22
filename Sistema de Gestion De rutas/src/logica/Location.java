package logica;

import java.io.Serializable;

public class Location implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 5320719089451615058L;
	String name;

	    public Location(String name) {
	        this.name = name;
	    }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	    
	    
}


