# Requisitos

## Resquisitos do Utilizador

- Fornecer registos das presenças (dos alunos e professores) nas aulas de MDS;
- Permitir editar os horários das aulas (data, hora, duração e tipo das aulas);
- Contabilizar a presença dos alunos e dos docentes através da passagem dos cartões;
- Permitir consultar e alterar o estado das faltas (justificadas ou injustificadas);
- Gerar um relatório das faltas dos alunos e das aulas não lecionadas por cada professor, no final do semestre.

## Requisitos do Sistema

- O Sistema deve importar a lista de alunos inscritos nas cadeiras a partir do SIIUE;

- Quando o docente não passa o seu cartão, considera-se que não existiu aula e não são contabilizadas as faltas;

- Quando um aluno atingir 25% e 50% de aulas não assistidas, o sistema deve enviar um email ao aluno e outro aos docentes a informar sobre esta situação;

- Se um aluno passar o cartão depois de decorrida 1h desde o inicio da aula, apenas será considerada meia presença;

- A cada hora de aula, o sistema deve avaliar as condições acima, enviando automaticamente os emails necessários;

- A qualquer momento, o docente poderá alterar o estado de faltas (justificadas ou injustificadas);

- No final do semestre é gerado um relatório com a seguinte informação:

	- Lista de alunos com o número de presenças e a respectiva percentagem;

	- Gráfico de presenças por aula;

	- Lista de alunos com entre 25% e 50% de faltas;
	
	- Lista de alunos com mais de 50% de faltas;