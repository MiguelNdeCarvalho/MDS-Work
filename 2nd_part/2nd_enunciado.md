# Parte 2: Implementação (entrega até 22/06)

Na segunda parte do trabalho os alunos devem implementar o Sistema de Registo de Presenças, tendo como base os uses cases e o diagrama de classes desenvolvido na primeira parte do trabalho (já com as alterações abordadas na discussão). Para tal, devem ser considerados os seguintes pontos:

- Criar issues no gitlab que devem corresponder às várias tarefas necessárias para a  implementação do sistema de forma a cobrir todas as funcionalidades descritas nos use cases.

- Deve existir pelo menos um registo de commit por cada tarefa/issue.

- Devem implementar testes unitários de forma a validar o caminho principal dos vários uses cases e também das extensões. Será valorizado quem utilizar TDD como metodologia.

- No README do projeto devem descrever, em forma de relatório, as decisões tomadas ao longo do trabalho.

Nota: Algumas das funcionalidades/restrições não devem ser implementadas nesta fase do trabalho. São elas:

- Envio automático de emails quando são atingidos 25% ou 50% das faltas.

- Importação de dados do SIIUE (usaremos um conjunto de alunos/cartões fornecido para testes)

- Gráfico de presenças por aula.

- Autenticação/login (embora as classes relativas a este processo devam ser implementadas e alvo de testes JUnit)

Relativamente à implementação do sistema, este deverá ser implementado como um conjunto de duas aplicações que funcionam da seguinte forma:

##    Aplicação 1 - Leitor de cartões

        Consiste numa aplicação (modo terminal) que espera por um input textual (código do cartão). A cada código recebido, identifica o aluno (ou docente) em questão e regista a sua presença no sistema.

##    Aplicação 2 - Gestão do sistema

        Aplicação em modo terminal (ou gráfico, em opção), que apresenta um menu com os items: “Importar dados dos utilizadores”; “Justificar Falta”; “Mostrar Relatório de Faltas”; “Consultar faltas por aluno”; e outros que achem necessários para o bom funcionamento do sistema.

A persistência dos dados deve ser feita usando estruturas de dados comuns existentes no Java (listas, arrays, etc), não recorrendo a qualquer tipo de base de dados. Opcionalmente, poderão ser usadas estruturas em JSON para facilitar a persistência entre invocações dos programas (é fornecido um exemplo de uso).

**A implementação deve respeitar os seguintes requisitos:**

- Usar Java como linguagem de implementação.

- Gestão de versões de todo o projecto usando Git, com commits e pushes regulares para o GitLab do vosso projecto.

- Uso de JUnit para o testes unitários.

- Uso do maven para gestão de dependências.

 
Submissão

A 2ª parte do trabalho deverá ser entregue e discutido em datas diferentes, de acordo com as seguintes datas:

- Data limite para terminar a especificação: Até ao final do dia 22/06/2020

- Discussão/Apresentação da especificação: a definir 

**Só será avaliado o código até à data do último commit realizado antes do prazo final.**

Cada grupo deve submeter (apenas uma vez) o link para a seu projeto no GitLab, juntamente com o número de aluno e nome de todos os elementos que constituem o grupo de trabalho, na página criada para o efeito no Moodle. Por cada dia de atraso na entrega da do trabalho ou por qualquer alteração que seja feita após a data de entrega, dará origem uma penalização de 2^(n-1) valores, onde n é o número de dias em atraso.