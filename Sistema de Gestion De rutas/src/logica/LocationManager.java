package logica;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import javafx.util.Pair;




public class LocationManager implements Serializable {
	
	private static final long serialVersionUID = 1760598463252095534L;
	
	 private Edge[][] matrizAdyacencia;
	 private List<Location> locations;
	


	 public LocationManager(int numLocations) {
	    
		 matrizAdyacencia = new Edge[numLocations][numLocations];
	     locations = new ArrayList<>();

	    }
	   public void setAdjacencyMatrix(Edge[][] matrizAdyacencia) {
	        this.matrizAdyacencia = matrizAdyacencia;
	    }
	   
	   public void getAdjacencyMatrix(Edge[][] matrizAdyacencia) {
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
	        resizeAdjacencyMatrix();
	    }

	    /**
	     * Método para redimensionar la matriz de adyacencia cuando se agregan o eliminan ubicaciones.
	     * Se utiliza para mantener la matriz actualizada con el número correcto de ubicaciones.
	     */
	    private void resizeAdjacencyMatrix() {
	        // Obtener el nuevo tamaño de la matriz basado en el número de ubicaciones
	        int newSize = locations.size();
	        // Crear una nueva matriz de adyacencia con el nuevo tamaño
	        Edge[][] newMatrix = new Edge[newSize][newSize];

	        // Copiar los valores de la matriz antigua a la nueva, si existe la matriz antigua
	        if (matrizAdyacencia != null) {
	            // Obtener el tamaño de la matriz antigua
	            int oldSize = matrizAdyacencia.length;
	            // Iterar sobre la matriz antigua y copiar los valores a la nueva matriz
	            for (int i = 0; i < oldSize; i++) {
	                for (int j = 0; j < oldSize; j++) {
	                    newMatrix[i][j] = matrizAdyacencia[i][j];
	                }
	            }
	        }

	        // Asignar la nueva matriz de adyacencia a la matriz actual
	        matrizAdyacencia = newMatrix;
	    }

	    //edita ubicaciones (nodos) segun su indice
	    public void editLocation(int index, Location location) {
	        if (index >= 0 && index < locations.size()) {
	            locations.set(index, location);
	        }
	    }

	    public void deleteLocation(int index) {
	        // Verificar si el índice está dentro del rango válido de la lista de ubicaciones
	        if (index >= 0 && index < locations.size()) {
	            // Eliminar la ubicación en el índice especificado
	            locations.remove(index);
	            // Iterar sobre las filas de la matriz de adyacencia desde el índice eliminado
	            for (int i = index; i < locations.size(); i++) {
	                // Actualizar los valores de las filas i a i+1 para reflejar la eliminación
	                for (int j = 0; j < matrizAdyacencia.length; j++) {
	                    matrizAdyacencia[i][j] = matrizAdyacencia[i + 1][j];
	                }
	                // Actualizar los valores de las columnas i a i+1 para reflejar la eliminación
	                for (int j = 0; j < matrizAdyacencia.length; j++) {
	                    matrizAdyacencia[j][i] = matrizAdyacencia[j][i + 1];
	                }
	            }
	        }
	    }
	   
	    public void addConnection(int source, int destination, int weight, int timeMin) {
	        // Verificar si los identificadores de ubicación de origen y destino son válidos
	        if (source >= 0 && source < locations.size() && destination >= 0 && destination < locations.size()) {
	            // Establecer la conexión desde la ubicación de origen a la de destino
	            matrizAdyacencia[source][destination] = new Edge(weight, timeMin);
	            // Establecer la conexión desde la ubicación de destino a la de origen (grafo no dirigido)
	            matrizAdyacencia[destination][source] = new Edge(weight, timeMin);
	        } else {
	            // Mostrar un mensaje de error si los identificadores de ubicación son inválidos
	            System.out.println("Invalid location IDs entered.");
	        }
	    }
	     
	    
	    public void deleteConnection(int source, int destination) {
	        if (source >= 0 && source < locations.size() &&
	            destination >= 0 && destination < locations.size()) {
	            if (matrizAdyacencia[source][destination] != null && matrizAdyacencia[destination][source] != null) {
	                matrizAdyacencia[source][destination] = null;
	                matrizAdyacencia[destination][source] = null; 
	            } else {
	                System.out.println("No existe conexión entre las ubicaciones especificadas.");
	            }
	        } else {
	            System.out.println("Ubicaciones especificadas fuera de rango.");
	        }
	    }
	    public void displayAdjacencyMatrix() {
	        if (matrizAdyacencia == null || matrizAdyacencia.length == 0) {
	            System.out.println("La matriz de adyacencia está vacía.");
	            return;
	        }

	        // Imprimir encabezados de columnas
	        System.out.print("   |");
	        for (int i = 0; i < matrizAdyacencia.length; i++) {
	            System.out.print(" " + i + " |");
	        }
	        System.out.println();
	        
	        // Imprimir separador
	        System.out.print("---+");
	        for (int i = 0; i < matrizAdyacencia.length; i++) {
	            System.out.print("----");
	        }
	        System.out.println();

	        // Imprimir filas de la matriz
	        for (int i = 0; i < matrizAdyacencia.length; i++) {
	            System.out.print(" " + i + " |");
	            for (int j = 0; j < matrizAdyacencia[i].length; j++) {
	                if (matrizAdyacencia[i][j] != null) {
	                    System.out.print(" " + matrizAdyacencia[i][j].getWeight() + " |");
	                } else {
	                    System.out.print(" 0 |"); // Si no hay conexión, se imprime 0
	                }
	            }
	            System.out.println();
	        }
	    }




	 // Método para encontrar la ruta más corta entre dos ubicaciones utilizando el algoritmo de Dijkstra
	    public List<Location> shortestPathDijkstra(int start, int end) {
	        // Llama al método principal de Dijkstra con minimizeTime establecido en false por defecto
	        return shortestPathDijkstra(start, end, false);
	    }

	    // Método principal para encontrar la ruta más corta entre dos ubicaciones
	    public List<Location> shortestPathDijkstra(int start, int end, boolean minimizeTime) {
	        // Arreglo para almacenar las distancias más cortas desde el nodo de inicio hasta cada nodo
	        int[] dist = new int[locations.size()];
	        // Arreglo para rastrear los nodos visitados durante el proceso
	        boolean[] visited = new boolean[locations.size()];
	        // Arreglo para almacenar el nodo padre de cada nodo en el camino más corto
	        int[] parent = new int[locations.size()];

	        // Inicializa todas las distancias a valores infinitos, excepto la distancia al nodo de inicio, que se establece en 0
	        Arrays.fill(dist, Integer.MAX_VALUE);
	        dist[start] = 0;

	        // Itera para actualizar las distancias más cortas a todos los nodos
	        for (int count = 0; count < locations.size() - 1; count++) {
	            // Selecciona el nodo no visitado más cercano con la distancia más corta
	            int u = minDistance(dist, visited);
	            visited[u] = true; // Marca el nodo como visitado

	            // Actualiza las distancias a los nodos adyacentes si es más corto pasar por u
	            for (int v = 0; v < locations.size(); v++) {
	                if (!visited[v] && matrizAdyacencia[u][v] != null) {
	                    int altWeight = minimizeTime ? matrizAdyacencia[u][v].getTimeMin() : matrizAdyacencia[u][v].getWeight();
	                    if (dist[u] != Integer.MAX_VALUE && dist[u] + altWeight < dist[v]) {
	                        dist[v] = dist[u] + altWeight;
	                        parent[v] = u;
	                    }
	                }
	            }
	        }
	        // Reconstruye el camino más corto desde el nodo de inicio hasta el nodo final y lo devuelve como una lista de ubicaciones
	        return getPath(start, end, parent);
	    }

	    // Método auxiliar para encontrar el nodo no visitado más cercano con la distancia más corta
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

	    // Método auxiliar para reconstruir el camino más corto desde el nodo de inicio hasta el nodo final
	    private List<Location> getPath(int start, int end, int[] parent) {
	        List<Location> path = new ArrayList<>();
	        for (int at = end; at != start; at = parent[at]) {
	            path.add(locations.get(at));
	        }
	        path.add(locations.get(start));
	        Collections.reverse(path);
	        return path;
	    }

	    
	   
	    public void minimumSpanningTreePrim() {
	    	// Implementar el algoritmo de Prim
	        // Este método debería imprimir el árbol de expansión mínima
	        boolean[] inMST = new boolean[locations.size()];
	        int[] parent = new int[locations.size()];
	        int[] key = new int[locations.size()];
	        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>(locations.size(), Comparator.comparingInt(Pair::getValue));

	        // Inicializar arrays
	        Arrays.fill(key, Integer.MAX_VALUE);
	        Arrays.fill(parent, -1);
	        key[0] = 0;
	        pq.offer(new Pair<>(0, 0)); // Comenzar con el primer vértice

	        while (!pq.isEmpty()) {
	            int u = pq.poll().getKey();
	            inMST[u] = true;
	            // Actualizar valores de clave y punteros de padres para vértices adyacentes
	            for (int v = 0; v < locations.size(); v++) {
	                if (!inMST[v] && matrizAdyacencia[u][v] != null && matrizAdyacencia[u][v].getWeight() < key[v]) {
	                    key[v] = matrizAdyacencia[u][v].getWeight();
	                    parent[v] = u;
	                    pq.offer(new Pair<>(v, key[v]));
	                }
	            }
	        }
	        // Print the edges to construct the MST
	    }
	 // Clase para representar un conjunto disjunto utilizando la estructura de datos de conjunto disjunto
	    class DisjointSet {
	        int[] parent; // Arreglo para almacenar los padres de los elementos en el conjunto

	        // Constructor para inicializar el conjunto disjunto con n elementos
	        public DisjointSet(int n) {
	            parent = new int[n];
	            // Inicializa cada elemento como su propio padre al principio
	            for (int i = 0; i < n; i++) {
	                parent[i] = i;
	            }
	        }

	        // Método para encontrar el conjunto al que pertenece un elemento específico
	        public int find(int x) {
	            // Si el elemento no es su propio padre, se busca recursivamente el padre del padre hasta encontrar el conjunto raíz
	            if (parent[x] != x) {
	                parent[x] = find(parent[x]); // La compresión de la ruta se aplica para optimizar la búsqueda
	            }
	            return parent[x]; // Retorna el conjunto al que pertenece el elemento x
	        }

	        // Método para unir dos conjuntos en uno solo
	        public void union(int x, int y) {
	            int xParent = find(x); // Encuentra el conjunto al que pertenece x
	            int yParent = find(y); // Encuentra el conjunto al que pertenece y
	            parent[yParent] = xParent; // Establece el padre del conjunto y como el conjunto x, fusionando los conjuntos
	        }
	    }

	     
		public void minimumSpanningTreeKruskal() {
			// TODO Auto-generated method stub
			
	        //  imprimir el árbol de expansión mínima
	        List<Pair<Integer, Pair<Integer, Integer>>> edges = new ArrayList<>();
	        // Llenar la lista de aristas con todas las aristas de la matriz de adyacencia
	        for (int i = 0; i < locations.size(); i++) {
	            for (int j = i + 1; j < locations.size(); j++) {
	                if (matrizAdyacencia[i][j] != null) {
	                    edges.add(new Pair<>(matrizAdyacencia[i][j].getWeight(), new Pair<>(i, j)));
	                }
	            }
	        }
	        // Ordenar las aristas basadas en los pesos
	        Collections.sort(edges, Comparator.comparingInt(Pair::getKey));

	        DisjointSet ds = new DisjointSet(locations.size());
	        // Iterar a través de las aristas ordenadas y agregarlas al MST si no forman un ciclo
	        for (Pair<Integer, Pair<Integer, Integer>> edge : edges) {
	            int u = edge.getValue().getKey();
	            int v = edge.getValue().getValue();
	            int weight = edge.getKey();
	            if (ds.find(u) != ds.find(v)) {
	                // Imprimir la arista para construir el MST
	                System.out.println(u + " - " + v + ": " + weight);
	                ds.union(u, v);
	            }
	        }
		}
		public void shortestPathFloydWarshall() {
			// TODO Auto-generated method stub
			int n = matrizAdyacencia.length;
    	    int[][] dist = new int[n][n];

    	    // Initialize distance matrix with direct distances
    	    for (int i = 0; i < n; i++) {
    	        System.arraycopy(matrizAdyacencia[i], 0, dist[i], 0, n);
    	    }

    	    // Apply Floyd-Warshall algorithm
    	    for (int k = 0; k < n; k++) {
    	        for (int i = 0; i < n; i++) {
    	            for (int j = 0; j < n; j++) {
    	                if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
    	                    dist[i][k] + dist[k][j] < dist[i][j]) {
    	                    dist[i][j] = dist[i][k] + dist[k][j];
    	                }
    	            }
    	        }
    	    }

    	    // Print the shortest path matrix
    	    System.out.println("Resultado:");
    	    for (int i = 0; i < n; i++) {
    	        for (int j = 0; j < n; j++) {
    	            if (dist[i][j] == Integer.MAX_VALUE) {
    	                System.out.print("INF\t");
    	            } else {
    	                System.out.print(dist[i][j] + "\t");
    	            }
    	        }
    	        System.out.println();
    	    }
		}
		public void minimizeTime() {
			// TODO Auto-generated method stub
			for (int i = 0; i < locations.size(); i++) {
	            for (int j = i + 1; j < locations.size(); j++) {
	                List<Location> path = shortestPathDijkstra(i, j, true);
	                System.out.println("Minima ruta cuestion de tiempo " + locations.get(i) + " a " + locations.get(j) + ": " + path);
	            }
	        }
			
		}
		public void minimizeDistance() {
			// TODO Auto-generated method stub
			 for (int i = 0; i < locations.size(); i++) {
		            for (int j = i + 1; j < locations.size(); j++) {
		                List<Location> path = shortestPathDijkstra(i, j, false);
		                System.out.println("Minima ruta cuestion de distancia total" + locations.get(i) + " a " + locations.get(j) + ": " + path);
		            }
		        }
			
		}
		
		public Location buscarLocationByCodigo(String locationId) {
			Location aux = null;
			boolean encontrado = false;
			int i=0;
			while (!encontrado && i<locations.size()) {
				if(locations.get(i).getName().equalsIgnoreCase(locationId)){
					aux = locations.get(i);
					encontrado = true;
				}
				i++;
			}
			
			return aux;
		}
		//manejador de archivos
		public class FileManager {
		    public LocationManager loadLocationManager(String RutasDatos) {
		        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RutasDatos))) {
		            LocationManager manager = (LocationManager) ois.readObject();
		            System.out.println("LocationManager cargada efectivamente.");
		            return manager;
		        } catch (IOException | ClassNotFoundException e) {
		            e.printStackTrace();
		            return null;
		        }
		    }
		}
}
