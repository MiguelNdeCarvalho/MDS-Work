# User Stories

- O João é um aluno exemplar, ele passa o cartão no início da aula, tendo a sua presença marcada.
- O Miguel é um aluno desatento, não passou o cartão no início da auala e só se lembrou passado 1h de aula. O Miguel decidiu falar com o professor e o professor decide alterar o valor da presença para **1**.
- O Rui acha-se espertinho, ele passa o cartão no início da aula e vai-se embora. O professor vê este compotamente e decide injustificar a presença, isto é, colocar o valor a **0**.
- O Rui passa o cartão no início da aula e assiste apenas a 1H da aula, o professor observa este comportamento e muda o valor da presença para metade **0,5**.
- Um funcionário da UE insere o horário
- No início de cada aula o sistema coloca a assiduidade de cada aluno com a seguinta expressão: `n_aulas_assistidas * n_aulas_total * 100`. Caso o resultado seja inferior a 50%, o sistema envia um email de aviso para o respetivo aluno., 
- Se o professor faltar à aula, não serão contabilizadas as faltas
- Caso o professor se esqueça de passar o cartão no início da aula, o sistema irá pensar que o professor faltou e as faltas não serão contabilizadas. Deverá então justificar a sua falta e a dos alunos
- No final do semestre, o professor irá receber um relatório gerado pelo sistema.