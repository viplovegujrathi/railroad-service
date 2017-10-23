# Railroad Services #

This project is a collection of business intelligence methods to provide answers to predetermined questions.

### Problem definition ###

The local commuter railroad services a number of towns in Kiwiland.  Because of monetary concerns, all of the tracks are 'one-way.'  That is, a route from Kaitaia to Invercargill does not imply the existence of a route from Invercargill to Kaitaia.  In fact, even if both of these routes do happen to exist, they are distinct and are not necessarily the same distance!
 
The purpose of this problem is to help the railroad provide its customers with information about the routes.  In particular, you will compute the distance along a certain route, the number of different routes between two towns, and the shortest route between two towns.
 
* **Input:**  A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.
 
* **Output:** For test input 1 through 5, if no such route exists, output 'NO SUCH ROUTE'.  Otherwise, follow the route as given; do not make any extra stops!  For example, the first problem means to start at city A, then travel directly to city B (a distance of 5), then directly to city C (a distance of 4).

1. The distance of the route A-B-C.
2. The distance of the route A-D.
3. The distance of the route A-D-C.
4. The distance of the route A-E-B-C-D.
5. The distance of the route A-E-D.
6. The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
7. The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).
8. The length of the shortest route (in terms of distance to travel) from A to C.
9. The length of the shortest route (in terms of distance to travel) from B to B.
10. The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.


### Running the solution ###

This project was built using Maven to manage dependencies, package, test and build the solution.

* To test the application, on the root folder of the project execute: **gradle test**
* To generate a jar file of the solution, on the root folder of the project execute: **gradle jar**
* To run the application, after the package generation, on the root folder of the project execute: **java -jar target/railroad-services-1.0.jar _<path to the input file>_**

### Design ###

Using inversion of control and dependency injection principles, I tried to design stateless objects to execute operations over immutable data objects.

To calculate the three main classes of problems of this solution I used:

* Distance along a certain route: a simple graph path walking algorithm accumulating the distance walked.
* Number of different routes between two towns: a iterative deep first traversal algorithm with different stop conditions accordingly with the question.
* Shortest route between two towns: Dijkstra's algorithm implementation using a priority queue.

To store the railroad graph I created an object *Railroad* with a map of the distances between the nodes (*Map<Edge, Integer>*), a map with a set of the adjacents of each node (*Map<Node, Set<Node>>*) and a set of the nodes (*Set<Node>*).

### Dependencies ###

* Java JDK version 1.7
* JUnit version 4.12
* Gradle version 2.12

