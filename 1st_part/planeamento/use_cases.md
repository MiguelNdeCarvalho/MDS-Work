# Use Cases

## Login

- Ator principal: Aluno, Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. mostrar página do programa
- Extensões:
  - 2 - conta não encontrada:
    - Username não encontrado
    - Password Inválida
  - 3 - Página de acordo com o utilizador
    - Aluno apenas pode consultar
    - Professor pode alterar presenças nas sua cadeira

## Consulta das presenças

- Ator principal: Aluno, Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. Mostrar página principal do programa
  4. Selecionar a visualização de presenças
  5. Visualizar presenças por aula
- Extensões:
  - 4 - Visualização de acordo com o utilizador
    - Aluno apenas pode consultar a suas faltas
    - Professor escolhe o aluno/docente para consultar as faltas deste

## Justificar/Injustificar as presenças

- Ator principal: Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. Mostrar página principal do programa
  4. Selecionar a visualização de presenças
  5. Altera a presença das aulas que desejar

## Gerar Relatório

-Ator principal: Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. Mostrar página principal do programa
  4. Clicar no botão de **Gerar Relatório**
  5. Envio de um ficheiro PDF sobre as faltas dos alunos para o email do docente.

## Criação/Alteração do Horário de Funcionamento

-Ator principal: Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. Mostrar página principal do programa
  4. Clicar no botão **Horários**
  5. Clicar no botão **Editar**
  6. Inserir/alterar os Horarios da disciplina (dias de semana e horas)
  7. Clicar no botão **Apply**

## Sincronização do Membros com o SIIUE

-Ator principal: Professor
- Comportamento normal de sucesso:
  1. Preencher Username, Password
  2. Clicar no botão de **Login**
  3. Mostrar página principal do programa
  4. Clicar no botão **Alunos**
  5. Clicar no botão **Sincronizar com o SIIUE**

## Passagem do cartão

-Ator principal : Aluno, Professor
- Comportamento normal de sucesso:
  1. Interpretação do cartão após a passagem do mesmo
  2. Sistema identifica o utilizador 
  3. Regista a presença deste
- Extensões:
   - 2 - O utilizador é Professor
       - A aula é validada bem como a presença dos alunos que passaram o cartão  
