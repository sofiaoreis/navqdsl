# NAVQDSL

NAVQDSL is a DSL for specifying queries in navigation systems graphs. The main goal of the application is to process a query by input and output the result.

The graph used is a small graph created by the developers.

Some Examples of Input:

- Output 10 paths from “A” to “B”: criteria: shortest 
- Output 10 paths from “A” to “B”: criteria: fastest
- Output places with distance <= 3 places from “A” 

#### How to run the project:

Download the 3NAVQDSL.jar, open the terminal and run java -jar 3NAVQDSL.jar in the root folder (where you saved the .jar).
Then test some queries presented in the examples point. 

#### DEALING WITH SYNTACTIC ERRORS: 

While the tree is visited, the application spears exceptions if the verification fails. The exceptions are warnings when the input has no ":", erros when the query does not make sense; and, token and parser erros customized by us. The program ends when finds the first problem, we think that it does not make sense continue the query processing if it's not equal to the examples. If it's a warning, the result is calculated and outputted. 

#### SEMANTIC ANALYSIS:

The application verifies if the chosen nodes exist in the graph.

# INTERMEDIATE REPRESENTATIONS (IRs): 

The application uses a symbol table (hashtable) to make the connection between the data and values. 

#### CODE GENERATION: 

The code generated is from the parser. The Main Class calls the parser, and connects the result with the graph. 

#### OVERVIEW: 

The app has a parser that makes the lexical and sintatic analysis. It creates the symbol table through the traveling of the AST.

The app uses jgrapht to create a graph and perform the queries. Some algorithms from jgrapht were used (algorithms like KShortestPaths which is based on Dijkstra algorithm) to calculate a specific number of shortest paths from a vertex to another. For the third query, the app uses the FloydWarshallShortestPaths algorithm to return all the shortest paths from a vertex to all of the other vertexes, and after that it's filtered according to type and distance.

In the fastest case, the functions getPaths (created by us) return the shortest path, in other words, with low cost. Whereas, in the shortest case, the graph is initialized with all edges equal to 1 and it's considered the number os edges instead of the weight.

In the last query, we consider all the edges equal to 1 and the distance equal to the number of edges.

#### TESTSUITE AND TEST INFRASTRUCTURE: 

In the examples folder, we have tests that validate the querys, whereas in the testsuite folder we have testes where we show the errors handler.

#### PROS: 

Processing a query that will operate in a high volume of information.

#### CONS: 

It's possible that the app is not 100% in terms of errors verification at sintatic and semantic level.
