package logica;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LocationManager {
	
	 private int[][] matrizAdyacencia;
	 private List<Location> locations;


	 public LocationManager(int numLocations) {
	    
		 matrizAdyacencia = new int[numLocations][numLocations];
		 locations = new ArrayList<>();

	    }

		public int[][] getAdjacencyMatrix() {
			return matrizAdyacencia;
		}


		public void setAdjacencyMatrix(int[][] matrizAdyacencia) {
			this.matrizAdyacencia = matrizAdyacencia;
		}


		public List<Location> getLocations() {
			return locations;
		}


		public void setLocations(List<Location> locations) {
			this.locations = locations;
		}

	    

	    public void addLocation(Location location) {
	        locations.add(location);
	    }

	    public void editLocation(int index, Location location) {
	        if (index >= 0 && index < locations.size()) {
	            locations.set(index, location);
	        }
	    }

	    public void deleteLocation(int index) {
	        if (index >= 0 && index < locations.size()) {
	            locations.remove(index);
	            for (int i = index; i < locations.size(); i++) {
	                for (int j = 0; j <  matrizAdyacencia.length; j++) {
	                	 matrizAdyacencia[i][j] =  matrizAdyacencia[i + 1][j];
	                }
	                for (int j = 0; j <  matrizAdyacencia.length; j++) {
	                	 matrizAdyacencia[j][i] =  matrizAdyacencia[j][i + 1];
	                }
	            }
	        }
	    }

	    public void addConnection(int source, int destination, int weight) {
	        if (source >= 0 && source < locations.size() &&
	            destination >= 0 && destination < locations.size()) {
	        	 matrizAdyacencia[source][destination] = weight;
	        	 matrizAdyacencia[destination][source] = weight; // Assuming undirected graph
	        }
	    }

	    public List<Location> shortestPathDijkstra(int start, int end) {
	        int[] dist = new int[locations.size()];
	        boolean[] visited = new boolean[locations.size()];
	        int[] parent = new int[locations.size()];

	        Arrays.fill(dist, Integer.MAX_VALUE);
	        dist[start] = 0;

	        for (int count = 0; count < locations.size() - 1; count++) {
	            int u = minDistance(dist, visited);
	            visited[u] = true;
	            for (int v = 0; v < locations.size(); v++) {
	                if (!visited[v] &&  matrizAdyacencia[u][v] != 0 &&
	                        dist[u] != Integer.MAX_VALUE &&
	                        dist[u] +  matrizAdyacencia[u][v] < dist[v]) {
	                    dist[v] = dist[u] +  matrizAdyacencia[u][v];
	                    parent[v] = u;
	                }
	            }
	        }

	        return getPath(start, end, parent);
	    }

	    private int minDistance(int[] dist, boolean[] visited) {
	        int min = Integer.MAX_VALUE;
	        int minIndex = -1;
	        for (int i = 0; i < locations.size(); i++) {
	            if (!visited[i] && dist[i] < min) {
	                min = dist[i];
	                minIndex = i;
	            }
	        }
	        return minIndex;
	    }

	    private List<Location> getPath(int start, int end, int[] parent) {
	        List<Location> path = new ArrayList<>();
	        for (int at = end; at != start; at = parent[at]) {
	            path.add(locations.get(at));
	        }
	        path.add(locations.get(start));
	        Collections.reverse(path);
	        return path;
	    }


}
