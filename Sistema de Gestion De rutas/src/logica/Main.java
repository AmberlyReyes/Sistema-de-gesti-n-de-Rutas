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
	        	  System.out.println("Men�:");
	              System.out.println("1. Agregar ubicaci�n");
	              System.out.println("2. Editar ubicaci�n");
	              System.out.println("3. Eliminar ubicaci�n");
	              System.out.println("4. Agregar conexi�n entre ubicaciones");
	              System.out.println("5. Editar conexi�n entre ubicaciones");
	              System.out.println("6. Calcular ruta m�s corta (Dijkstra)");
	              System.out.println("7. Encontrar �rbol de expansi�n m�nima (Prim)");
	              System.out.println("8. Encontrar �rbol de expansi�n m�nima (Kruskal)");
	              System.out.println("9. Optimizar rutas (Floyd-Warshall)");
	              System.out.println("10. Planificar ruta �ptima (tiempo)");
	              System.out.println("11. Planificar ruta �ptima (total distancia)");
	              System.out.println("12. Visualizar matriz de adyacencia");
	              System.out.println("13. Salir");
	              System.out.print("Seleccione una opci�n: ");
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
	                      System.out.println("Opci�n no v�lida. Int�ntalo de nuevo.");
	              }
	          } while (opcion != 14);
	    }

	public static void addLocation(LocationManager routeSystem, Scanner scanner) {
        System.out.print("ID de la ubicaci�n: ");
        int locationId = scanner.nextInt();

        System.out.print("Cuantas aristas tendr�? ");
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
	    System.out.print("Ingrese el ID de la ubicaci�n que desea editar: ");
	    String locationId = scanner.next();

	    Location location = locationManager.buscarLocationByCodigo(locationId);
	    if (location != null) {
	        System.out.println("Ubicaci�n actual: " + location);

	        System.out.print("Ingrese el nuevo nombre para la ubicaci�n: ");
	        String newName = scanner.next();
	        location.setName(newName);

	        System.out.println("Ubicaci�n editada correctamente.");
	    } else {
	        System.out.println("No se encontr� ninguna ubicaci�n con el ID proporcionado.");
	    }
	}
	public static void deleteLocation(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el �ndice de la ubicaci�n que desea eliminar: ");
	    int index = scanner.nextInt();

	    locationManager.deleteLocation(index);
	    System.out.println("Ubicaci�n eliminada correctamente.");
	}

	public static void addConnection(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el �ndice de la ubicaci�n de origen: ");
	    int sourceIndex = scanner.nextInt();

	    System.out.print("Ingrese el �ndice de la ubicaci�n de destino: ");
	    int targetIndex = scanner.nextInt();

	    System.out.print("Ingrese el peso de la conexi�n: ");
	    int weight = scanner.nextInt();

	    System.out.print("Ingrese el tiempo de la conexi�n en minutos: ");
	    int time = scanner.nextInt();

	    // Agregar la conexi�n entre las ubicaciones
	    locationManager.addConnection(sourceIndex, targetIndex, weight, time);
	    System.out.println("Conexi�n entre ubicaciones agregada correctamente.");
	}
	public static void editConnection(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el �ndice de la ubicaci�n de origen: ");
	    int sourceIndex = scanner.nextInt();

	    System.out.print("Ingrese el �ndice de la ubicaci�n de destino: ");
	    int destinationIndex = scanner.nextInt();

	    System.out.print("Ingrese el nuevo peso de la conexi�n: ");
	    int newWeight = scanner.nextInt();

	    System.out.print("Ingrese el nuevo tiempo de la conexi�n en minutos: ");
	    int newTime = scanner.nextInt();

	    // Editar la conexi�n entre las ubicaciones
	    locationManager.addConnection(sourceIndex, destinationIndex, newWeight, newTime);
	    System.out.println("Conexi�n entre ubicaciones editada correctamente.");
	}
	
	public static void calcularRutaMasCorta(LocationManager locationManager, Scanner scanner) {
	    System.out.print("Ingrese el �ndice de la ubicaci�n de inicio: ");
	    int start = scanner.nextInt();

	    System.out.print("Ingrese el �ndice de la ubicaci�n de destino: ");
	    int end = scanner.nextInt();

	    // Calcular la ruta m�s corta utilizando Dijkstra
	    List<Location> shortestPath = locationManager.shortestPathDijkstra(start, end);

	    // Mostrar la ruta m�s corta
	    System.out.println("Ruta m�s corta:");
	    for (Location location : shortestPath) {
	        System.out.println(location);
	    }
	}
	
	public static void encontrarArbolExpansionMinima(LocationManager locationManager) {
	    // Llamar al m�todo para encontrar el �rbol de expansi�n m�nima (Prim)
	    locationManager.minimumSpanningTreePrim();
	}
	public static void encontrarArbolExpansionMinimaKruskal(LocationManager locationManager) {
	    // Llamar al m�todo para encontrar el �rbol de expansi�n m�nima (Kruskal)
	    locationManager.minimumSpanningTreeKruskal();
	}

	public static void optimizarRutasFloydWarshall(LocationManager locationManager) {
	    // Llamar al m�todo para optimizar rutas utilizando Floyd-Warshall
	    locationManager.shortestPathFloydWarshall();
	}

	public static void planificarRutaOptimaTiempo(LocationManager locationManager) {
	    // Llamar al m�todo para minimizar el tiempo de ruta
	    locationManager.minimizeTime();
	}
	public static void planificarRutaOptimaDistancia(LocationManager locationManager) {
	    // Llamar al m�todo para minimizar la distancia de ruta
	    locationManager.minimizeDistance();



	



	}
}

