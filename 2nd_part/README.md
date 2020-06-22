# 2ª Parte Trabalho MDS (Implementação)

Nesta 2ª Parte realizámos a implementação do planeamento feito na [1ª parte do Trabalho](../1st_part/)

## Comandos para executar o trabalho

- Para **compilar** o trabalho usar o comando `mvn clean compile assembly:single` dentro da pasta [PrensenceManager](PresenceManager/)
- Para **executar** o trabalho usar o comando `target/PresenceManager-1.0-SNAPSHOT-jar-with-dependencies.jar` dentro da pasta [PrensenceManager](PresenceManager/)
- Para **executar os testes** usar o comando `mvn test` dentro da pasta [PrensenceManager](PresenceManager/)

## Decisões tomadas durante a implementação

Primeiramente começámos por arranjar tudo o que tínhamos de errado nos diagramas e nos requísitos. Além disso, demos setup do Maven através do ficheiro [pom.xml](PresenceManager/pom.xml) onde adicionámos as dependências necessárias, o [`Junit v4.11`](https://mvnrepository.com/artifact/junit/junit), o [`Json-Simple v1.1.1`](https://mvnrepository.com/artifact/com.googlecode.json-simple/json-simple) e por último o [`Mockito-Core v3.3.3`](https://mvnrepository.com/artifact/org.mockito/mockito-core).
De seguida desenvolvemos as classes básicas que permitem o funcionamento do programa, mas deparámos com um problema, ao compilarmos o programa e executarmos o ficheiro `.jar` compilado, o java não sabia qual classe deveria ser a principal (main). Então tivemos que adicionar um `manifest` ao plugin `maven-jar-plugin` para quando executarmos o executável ele abrir logo a classe certa.
Posteriormente deparámo-nos com um novo problema, quando executávamos o ficheiro `.jar` compilado do programa, tínhamos um erro referente a falta da classe do `json-simple`ou seja, como se a dependência não tivesse sido incluida no maven. Para solucionar esta situação tivemos que deixar de usar o plugin `maven-jar-plugin` e passámos a usar o `maven-assembly-plugin` que cria um package (.jar) com todas as dependências que era o que nós necessitávamos.
Seguidamente desenvolvemos as classes restantes e começámos a escrever os testes necessários.
No início pensámos em dividir os utilizadores em 2 grupos, os professores e os alunos, mas durante a implementação decidimos não fazer essa distinção e meter ambos os grupos como utilizadores.