**PROJECT TITLE: NAVQDSL
**GROUP: 3NAVQDSL

NAME1: Sofia Oliveira Reis, NR1: 201200742, GRADE1: 20, CONTRIBUTION: 33%

NAME2: Sara Linhas Paiva, NR2: 200904090, GRADE2: 20, CONTRIBUTION: 33%

NAME3: Filipe Leitão Ribeiro, NR3: 201104129, GRADE3: 20, 
CONTRIBUTION: 33%

** Como correr o projeto:

Ir à pasta do projeto e correr o comando java -jar 3NAVQDSL.jar 
Abrir ficheiro de exemplo txt da pasta examples ou testsuit, copiar input e colar na consola.

** SUMMARY: 

O nosso projeto é uma DSL para especificar queries que irão atuar em bases de dados grandes. O objetivo da nossa aplicação é processar uma query que é introduzida no programa como input, e depois aplicá-la a um grafo. 

O grafo que usamos, é um grafo pequeno que utilizamos para teste. Inicialmente íamos usar um grafo maior mas as suas ligações não era muito boas para o tipo de testes que queríamos realizar.

Os três tipos de queries que o programa processa são:

- Output 10 paths from “A” to “B”: criteria: shortest 
- Output 10 paths from “A” to “B”: criteria: fastest
- Output places with distance <= 3 places from “A” 
 
**DEALING WITH SYNTACTIC ERRORS: 

Enquanto percorremos a árvore, vamos lançando exceções caso as verificações falhem. As exceções são Warnings quando faltam dois pontos, erros quando a query não faz sentido e erros de token e parser personalizados por nós.
O programa termina quando encontra o primeiro problema, visto que, não faz sentido processar a query se não for igual ao definido. 
Caso seja um warning, o resultado é lançado na mesma. 
**SEMANTIC ANALYSIS:

Tivemos alguns problemas em encontrar regras de semântica. A que encontramos foi verificar, se os nodes escolhidos para fazer a query, se encontram no grafo. Visto que, só faz sentido fazer a query se os nodes existirem.  
**INTERMEDIATE REPRESENTATIONS (IRs): 

Para representação intermédia usamos uma tabela de símbolos, representada por uma hastable, para fazer a ligação entre dados e valores. 
**CODE GENERATION: 

O código gerado é o do parser, depois adicionamos uma classe para o Main onde chamamos o parser e onde fazemos a ligação entre o obtido do parser e o grafo. É basicamente onde são processadas as queres. 
**OVERVIEW: 

Fazemos o parser onde realizamos a análise lexical, sintática e onde é criada a tabela de símbolos. A última é criada à medida que é percorrida a AST. 

Usamos o jgrapht para criar o grafo e realizar as queries. Usamos alguns algoritmos do jgrapht como o KShortestPaths, que tem por base o Dijkstra, para calcular um número específicos de shortest paths de um vértice até a outro. 
Para a terceira query usamos o FloydWarshallShortestPaths para retornar todos os caminhos mais pequenos de um ponto a todos os outros e depois filtramos de acordo com o tipo de compare e a distância.

No caso de fastest, a função getPaths criada por nós, retorna o caminho mais curto, ou seja, com menor custo, visto que, as arestas têm valores variados. Enquanto, que no caso de shortest, o grafo é inicializado com as arestas todas a 1 e considera-se o número de arestas em vez do peso. 

Na última query, consideramos também as arestas todas a 1 e a distância o número e arestas.  
**TESTSUITE AND TEST INFRASTRUCTURE: 

Na pasta examples possuímos testes que validam as querys, enquanto que na pasta testsuite possuímos testes em que mostramos o tratamento de erros.

Examples
- query1.txt: Testar a primeira query do projeto, espera-se que encontre os dois caminhos curtos do vértice 1 a 5
- query2.txt: Testar query do projeto, espera-se que encontre no máximo os 10 caminhos mais curtos do vértice 1 a 5
- query3.txt: Testar query do projeto, espera-se que encontre no máximo os 10 caminhos mais rápido/menos pesado do vértice 1 a 5 
- query4.txt: Testar query do projeto, espera-se que encontre no máximo os 3caminhos mais rápido/menos pesado do vértice 1 a 5 
- query5 .txt: Testar query do projeto, espera-se que encontre os places com distância menos ou igual a 2 a partir do vértice 1 

TestSuite
- COMP1.txt: Erro personalizado relativamente a escrever mal a query. Obtém erro personalizado com a indicação de que queries são esperadas.
- COMP2.txt: Warning relativo à falta de : na query que obtém resultado na mesma.
- COMP3.txt: Erro relativo à ausência de tipo de critério. Obtém-se um erro de parser.
- COMP4.txt: Erro relativo à ausência dos vértices. Obtém-se erro de parser.
- COMP5.txt: Erro obtido por número inválido. Obtém-se erro de token.
- COMP6.txt: Erro obtido por falta de um bocado da query. Obtém-se erro de parser.

**TASK DISTRIBUITION: 

Sara: 
	-  Erros da Análise Sintática
	-  Análise Lexical
	-  AST
	- Análise Semântica

Sofia:
	-  Erros da Análise Sintática
	-  Análise Lexical
	-  AST
	- Análise Semântica
	- Algoritmos usados nas queries
	- Representação Intermédia
	- Inclusão do jgrapht

Filipe:
	-  Análise Lexical
	-  AST
	- Algoritmos usados nas queries


**PROS: 

Processar uma query que posteriormente vai atuar num grande volume de informação. 

**CONS: 

Possivelmente, não estar a 100% a verificação de erros a nível sintático e semântico. 
