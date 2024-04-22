package logica;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

public class Main implements Serializable{

	public static void main(String[] args) {
		 Scanner scanner = new Scanner(System.in);
	        LocationManager locationManager = new LocationManager(3); 
	        int opcion;
	        do {
	        	  System.out.println("Menú:");
	              System.out.println("1. Agregar ubicación");
	              System.out.println("2. Editar ubicación");
	              System.out.println("3. Eliminar ubicación");
	              System.out.println("4. Agregar conexión entre ubicaciones");
	              System.out.println("5. Editar conexión entre ubicaciones");
	              System.out.println("6. Calcular ruta más corta (Dijkstra)");
	              System.out.println("7. Encontrar árbol de expansión mínima (Prim)");
	              System.out.println("8. Encontrar árbol de expansión mínima (Kruskal)");
	              System.out.println("9. Optimizar rutas (Floyd-Warshall)");
	              System.out.println("10. Planificar ruta óptima (tiempo)");
	              System.out.println("11. Planificar ruta óptima (total distancia)");
	              System.out.println("12. Visualizar matriz de adyacencia");
	              System.out.println("13. Salir");
	              System.out.print("Seleccione una opción: ");
	              opcion = scanner.nextInt();

	              switch (opcion) {
	                  case 1:
	                      addLocation(locationManager, scanner);
	                      break;
	                  case 2:
	                	  editLocation(locationManager, scanner);
	                      break;
	                  case 3:
	                	  deleteLocation(locationManager, scanner);
	                      break;
	                  case 4:
	                	  addConnection(locationManager, scanner);
	                      break;
	                  case 5:
	                	  editConnection(locationManager, scanner);
	                      break;
	                  
	                  case 6:
	                	  calcularRutaMasCorta(locationManager, scanner);
	                      break;
	                  case 7:
	                	  encontrarArbolExpansionMinima(locationManager);
	                      break;
	                  case 8:
	                	  encontrarArbolExpansionMinimaKruskal(locationManager);
	                      break;
	                  case 9:
	                	  optimizarRutasFloydWarshall(locationManager);
	                      break;
	                  case 10:
	                	  planificarRutaOptimaTiempo(locationManager);
	                      break;
	                  case 11:
	                	  planificarRutaOptimaDistancia(locationManager);
	                      break;
	                  case 12:
	                      locationManager.displayAdjacencyMatrix();
	                      break;
	                  case 13:
	                      System.out.println("Saliendo del programa...");
	                      break;
	                  default:
	                      System.out.println("Opción no válida. Inténtalo de nuevo.");
	              }
	          } while (opcion != 14);
	    }

	public static void addLocation(LocationManager routeSystem, Scanner scanner) {
        System.out.print("ID de la ubicación: ");
        int locationId = scanner.nextInt();

        System.out.print("Cuantas aristas tendrá? ");
        int numEdges = scanner.nextInt();

        for (int i = 0; i < numEdges; i++) {
            System.out.print("Nodo por conectar: ");
            int targetLocationId = scanner.nextInt();
            System.out.print("Peso de la arista: ");
            int weight = scanner.nextInt();
            System.out.print("Tiempo (en minutos): ");
            int time = scanner.nextInt();

            routeSystem.addConnection(locationId, targetLocationId, weight, time);
        }
    }
	public static void editLocation(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el ID de la ubicación que desea editar: ");
	    String locationId = scanner.next();

	    Location location = locationManager.buscarLocationByCodigo(locationId);
	    if (location != null) {
	        System.out.println("Ubicación actual: " + location);

	        System.out.print("Ingrese el nuevo nombre para la ubicación: ");
	        String newName = scanner.next();
	        location.setName(newName);

	        System.out.println("Ubicación editada correctamente.");
	    } else {
	        System.out.println("No se encontró ninguna ubicación con el ID proporcionado.");
	    }
	}
	public static void deleteLocation(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el índice de la ubicación que desea eliminar: ");
	    int index = scanner.nextInt();

	    locationManager.deleteLocation(index);
	    System.out.println("Ubicación eliminada correctamente.");
	}

	public static void addConnection(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el índice de la ubicación de origen: ");
	    int sourceIndex = scanner.nextInt();

	    System.out.print("Ingrese el índice de la ubicación de destino: ");
	    int targetIndex = scanner.nextInt();

	    System.out.print("Ingrese el peso de la conexión: ");
	    int weight = scanner.nextInt();

	    System.out.print("Ingrese el tiempo de la conexión en minutos: ");
	    int time = scanner.nextInt();

	    // Agregar la conexión entre las ubicaciones
	    locationManager.addConnection(sourceIndex, targetIndex, weight, time);
	    System.out.println("Conexión entre ubicaciones agregada correctamente.");
	}
	public static void editConnection(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el índice de la ubicación de origen: ");
	    int sourceIndex = scanner.nextInt();

	    System.out.print("Ingrese el índice de la ubicación de destino: ");
	    int destinationIndex = scanner.nextInt();

	    System.out.print("Ingrese el nuevo peso de la conexión: ");
	    int newWeight = scanner.nextInt();

	    System.out.print("Ingrese el nuevo tiempo de la conexión en minutos: ");
	    int newTime = scanner.nextInt();

	    // Editar la conexión entre las ubicaciones
	    locationManager.addConnection(sourceIndex, destinationIndex, newWeight, newTime);
	    System.out.println("Conexión entre ubicaciones editada correctamente.");
	}
	
	public static void calcularRutaMasCorta(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el índice de la ubicación de inicio: ");
	    int start = scanner.nextInt();

	    System.out.print("Ingrese el índice de la ubicación de destino: ");
	    int end = scanner.nextInt();

	    // Calcular la ruta más corta utilizando Dijkstra
	    List<Location> shortestPath = locationManager.shortestPathDijkstra(start, end);

	    // Mostrar la ruta más corta
	    System.out.println("Ruta más corta:");
	    for (Location location : shortestPath) {
	        System.out.println(location);
	    }
	}
	
	public static void encontrarArbolExpansionMinima(LocationManager locationManager) {
	    // Llamar al método para encontrar el árbol de expansión mínima (Prim)
	    locationManager.minimumSpanningTreePrim();
	}
	public static void encontrarArbolExpansionMinimaKruskal(LocationManager locationManager) {
	    // Llamar al método para encontrar el árbol de expansión mínima (Kruskal)
	    locationManager.minimumSpanningTreeKruskal();
	}

	public static void optimizarRutasFloydWarshall(LocationManager locationManager) {
	    // Llamar al método para optimizar rutas utilizando Floyd-Warshall
	    locationManager.shortestPathFloydWarshall();
	}

	public static void planificarRutaOptimaTiempo(LocationManager locationManager) {
	    // Llamar al método para minimizar el tiempo de ruta
	    locationManager.minimizeTime();
	}
	public static void planificarRutaOptimaDistancia(LocationManager locationManager) {
	    // Llamar al método para minimizar la distancia de ruta
	    locationManager.minimizeDistance();



	



	}
}

