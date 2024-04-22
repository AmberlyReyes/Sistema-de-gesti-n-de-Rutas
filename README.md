# Sistema-de-gesti-n-de-Rutas
Sistema de Gestión de Rutas
Este sistema de gestión de rutas proporciona una interfaz para administrar ubicaciones y conexiones entre ellas, así como para calcular rutas óptimas utilizando varios algoritmos.
Clases y Métodos
1.	Clase Location
Representa una ubicación en el sistema de gestión de rutas.
Métodos:
•	getName(): Devuelve el nombre de la ubicación.
•	setName(String name): Establece el nombre de la ubicación.
2.	Clase Edge
Representa una conexión entre dos ubicaciones en el sistema de gestión de rutas.
Métodos:
•	getWeight(): Devuelve el peso de la conexión.
•	getTimeMin(): Devuelve el tiempo mínimo necesario para viajar a través de la conexión.
•	setWeight(int weight): Establece el peso de la conexión.
•	setTimeMin(int timeMin): Establece el tiempo mínimo necesario para viajar a través de la conexión.
3.	Clase LocationManager (controladora)
Administra las ubicaciones y conexiones en el sistema de gestión de rutas, y proporciona métodos para calcular rutas óptimas.
Métodos Principales:
•	addLocation(Location location): Agrega una nueva ubicación al sistema.
•	editLocation(int index, Location location): Edita una ubicación existente.
•	deleteLocation(int index): Elimina una ubicación del sistema.
•	addConnection(int source, int destination, int weight, int timeMin): Agrega una conexión entre dos ubicaciones.
•	deleteConnection(int source, int destination): Elimina una conexión entre dos ubicaciones.
•	shortestPathDijkstra(int start, int end): Calcula la ruta más corta entre dos ubicaciones utilizando el algoritmo de Dijkstra.
•	minimumSpanningTreePrim(): Encuentra el árbol de expansión mínima utilizando el algoritmo de Prim.
•	minimumSpanningTreeKruskal(): Encuentra el árbol de expansión mínima utilizando el algoritmo de Kruskal.
•	shortestPathFloydWarshall(): Optimiza las rutas utilizando el algoritmo de Floyd-Warshall.
•	minimizeTime(): Planifica rutas óptimas minimizando el tiempo de viaje.
•	minimizeDistance(): Planifica rutas óptimas minimizando la distancia total de viaje.
•	buscarLocationByCodigo(String locationId): Busca una ubicación por su ID.
•	loadLocationManager(String RutasDatos): Carga un objeto LocationManager desde un archivo.
Clase FileManager
Administra la carga y almacenamiento de objetos LocationManager desde y hacia archivos.
Ejecución del Programa
El archivo Main.java contiene el punto de entrada del programa. Al ejecutarlo, se muestra un menú interactivo que permite al usuario realizar diversas operaciones, como agregar, editar o eliminar ubicaciones, calcular rutas óptimas, y más. Para obtener más detalles sobre el funcionamiento y uso del programa, perfectamente se puede consultar el código fuente y los comentarios explicativos en el mismo.
