------Git(falta branching)--------

- Criar um repositório numa directoria existente

	$ git init

- Começar a “monitorizar” o projeto (tracking)

	$ git add *.c
	$ git add LICENSE
	$ git commit -m 'initial project version'

- Clonar um repositório existente

	+ ○ git clone [url] [directory]
	+ ○ url: http, https, ssh

	$ git clone https://github.com/mariomourao/random-gift.git Random
	$ git clone https://github.com/mariomourao/random-gift.git (directoria onde este comando é executado)

- Registar alterações no repositório

  *untracked(??): o ficheiro encontra-se dentro do repositório, mas não está a ser "tracked" pelo repositorio
  
  *unmodified: o ficheiro (que anteriormente foi commited no repositorio) não foi alterado até ao momento    
  
  *modified(M): o ficheiro (que anteriormente foi commited no repositorio) foi alterado, as novas alteraçoes precisam
  de ser commited 
  
  *staged(A): o ficheiro foi adicionado ao traking (add file) e está pronto a ser commited (stage). Quando for commited
  o ficheiro passa a ser unmodified (as alteraçoes no repositorio foram aplicadas)

  //NOTA: um ficheiro unmodified ou modified pode ser removido do traking (remove file), passando a ser untraked

- Verificar o estado do ficheiros
	
	+ ○ git status

	$ git status
	*On branch master
	*nothing to commit, working directory clean

- Verificar o estado após adicionar um novo ficheiro

	$ echo 'My Project' > README
	$ git status
	*On branch master
	*Untracked files:
	* (use "git add <file>..." to include in what will be committed)
	* README
	*nothing added to commit but untracked files present (use "git add" to track)

-Para Monitorizar (fazer o tracking) de novos ficheiros:

 	+$ git add README

	//NOTA: as "ALTERAÇÔES" do README podem ter sido staged, porem se fizermos novas alterações antes de 
	fazer o commit, será necessario repetir o commando para dar stage ás novas "ALTERAÇÔES" do README

O ficheiro fica staged:

	$ git status
	*On branch master
	*Changes to be committed:
	* (use "git reset HEAD <file>..." to unstage)
	* new file: README

- O comando git diff permite consultar as alterações que ainda não foram
marcadas para stage.

-Fazer commit das alterações

	$ git commit -m "Story 182: Fix benchmarks for speed"
	*[master 463dc4f] Story 182: Fix benchmarks for speed
	*2 files changed, 2 insertions(+)
	*create mode 100644 README

- Evitar a área de staging:

	+○ git commit -a -m “some commit message”

	$ git commit -a -m 'added new benchmarks'
	*[master 83e38c7] added new benchmarks
	* 1 file changed, 5 insertions(+), 0 deletions(-)

- Apagar ficheiros (depois é necessário dar commit)

	+ git rm filename

	$ git rm PROJECTS.md
	*rm 'PROJECTS.md'
	$ git status
	*On branch master
	*Changes to be committed:
	* (use "git reset HEAD <file>..." to unstage)
	* deleted: PROJECTS.md

- Fazer “untrack” de um ficheiro

	● Fazer “untrack” de um ficheiro
	● Remover o ficheiro da area de “stage”, Mas mantê-lo na directoria de trabalho
	● Útil quando nos esquecemos de adicionar um ficheiro ao .gitignore

	-.gitignore: ficheiro que diz tipos de files que devem ser ignorados, ex:logs

	$ git rm --cached README

- Mover Ficheiros

	+git mv file_from file_to


	$ git mv README.md README
	$ git status
	*On branch master
	*Changes to be committed:
	* (use "git reset HEAD <file>..." to unstage)
	* renamed: README.md -> README

- Consultar o histórico de commits 

	$ git log
	*commit ca82a6dff817ec66f44342007202690a93763949
	*Author: Scott Chacon <schacon@gee-mail.com>
	*Date: Mon Mar 17 21:52:11 2008 -0700
	* changed the version number
	
	*commit 085bb3bcb608e1e8451d4b2432f8ecbe6306e7e7
	*Author: Scott Chacon <schacon@gee-mail.com>
	*Date: Sat Mar 15 16:40:33 2008 -0700
 	* removed unnecessary test

- Corrigir o último commit

	○ Ficheiros esquecidos ou mensagem de commit errada
	+ git commit --amend

	$ git commit -m 'initial commit'
	$ git add forgotten_file
	$ git commit --amend

-Fazer unstage de um ficheiro

	 +git reset HEAD file

	$ git add *
	$ git status
	*On branch master
	*Changes to be committed:
	* (use "git reset HEAD <file>..." to unstage)
	* renamed: README.md -> README
	* modified: CONTRIBUTING.md

- Esquecer as alterações a um ficheiro
	
	+git checkout -- filename

	*Changes not staged for commit:
	* (use "git add <file>..." to update what will be committed)
	* (use "git checkout -- <file>..." to discard changes in working
	*directory)
	* modified: CONTRIBUTING.md

	$ git checkout -- CONTRIBUTING.md
	$ git status
	*On branch master
	*Changes to be committed:
	* (use "git reset HEAD <file>..." to unstage)
	* renamed: README.md -> README

- Repositórios remotos

	+ Versões do projecto, alojadas noutro local
	+ Essencial para colaborar num projecto

		● push
		− Enviar dados para o repositório
		● pull
		− Descarregar dados do repositório

	$ git remote
	*origin (no inicio de cada repositorio "git clone" só existe este repositorio)

- Adicionar um remote

	+git remote add <shortname> <url>

	*$ git remote
	*origin
	*$ git remote add pb https://github.com/paulboone/ticgit
	*$ git remote -v
	*origin https://github.com/schacon/ticgit (fetch)
	*origin https://github.com/schacon/ticgit (push)
	*pb https://github.com/paulboone/ticgit (fetch)
	*pb https://github.com/paulboone/ticgit (push)


- Fetching e Pulling do remote

	+git fetch [remote-name]
	
		○ Descarrega todos os dados do projecto a partir de um remote
		○ Quando se faz clone de um projecto

	■ Faz “Fetch” do projecto
	■ Adiciona um remoto chamado “origin”
		
		○ Não junta (merge) os dados remotos com os locais
	
	■ Tem que ser feito manualmente

	+git pull

		○ fetch
		○ merge

- Enviar dados para o remote
	
	+push [remote-name] [branch-name]	

	● Pushing (empurrar)
	● Partilhar alterações com outros

		○ Permissões de escrita
		○ Existiram “alterações novas”

	■ Alguém fez push para o origin
	■ Ao fazer push as alterações serão rejeitadas
	■ É necessário fazer pull(puxar) as novas alterações, e fazer novo push(empurrar)

- Colocar tags em pontos específicos na história do projecto
	
	● Versões
	● Pontos no tempo onde houve Releases

-Listar tags

	+git tag
	
	$ git tag
	*v0.1
	*v1.3

- Criar tags
	
	● “apontador” para um commit específico
	● colocar uma tag no commit actual

- Criar tag

	+git tag <nome da tag> <id do commit>

	$ git log --pretty=oneline
	*15027957951b64cf874c3557a0f3547bd83b3ff6 Merge branch 'experiment'
	*a6b4c97498bd301d84096da251c98a07c7723e65 beginning write support
	*0d52aaab4479697da7686c15f77a3d64d9165190 one more thing
	
	$ git tag -a v1.2 0d52aaab
	
- Enviar tag para o remote

	Por default, git push não transfere as tags para os servidores remotos

	+git push origin [tagname]

	$ git push origin v1.5
	*Counting objects: 14, done.
	*Delta compression using up to 8 threads.
	*Compressing objects: 100% (12/12), done.
	*Writing objects: 100% (14/14), 2.05 KiB | 0 bytes/s, done.
	*Total 14 (delta 3), reused 0 (delta 0)
	*To git@github.com:schacon/simplegit.git
 	** [new tag] v1.5 -> v1.5


- Fazer “checkout” de tags
	
	+git checkout -b <branchname> <tagname>

	Cria um “branch” novo baseado numa tag específica
	
	$ git checkout -b version2 v2.0.0
	*Switched to a new branch 'version2'

-------------------------------------------------Branching----------------------------------------
				(convem ver as imagens do slide para compreender)


- Branch: 

	● Apontador móvel para um commit
	● Branch default: master
	● Aponta para o último commit
	● Em cada commit, o apontador anda para a frente
	
	*HEAD: Apontador especial para o branch atual

- Criar um branch novo

	+ git branch testing (o branch aponta para onde o HEAD aponta na criação)

- Mudar de branch

	+$ git checkout testing (mudar o HEAD para testing)

- Merging

	+$ git merge hotfix (o branch atual vai apontar para a versão onde o hotfix aponta)

	- Quando um merge não corre bem

	$ git merge iss53
	*Auto-merging index.html
	*CONFLICT (content): Merge conflict in index.html
	*Automatic merge failed; fix conflicts and then commit the result.
	$ git status
	*On branch master
	*You have unmerged paths.
 	*(fix conflicts and run "git commit")
	*Unmerged paths:
	* (use "git add <file>..." to mark resolution)
	* both modified: index.html
	*no changes added to commit (use "git add" and/or "git commit -a")

	- Conflitos

	*<<<<<<< HEAD:index.html
	*<div id="footer">contact : email.support@github.com</div>
	*=======
	*<div id="footer">;
	* please contact us at support@github.com
	*</div>;
	*>>>>>>>; iss53:index.html

	Above “======” → HEAD (master branch)
	Below “======” → branch iss54 

	- reparar o conflito

	$ git mergetool

	- Após reparar o conflito

	$ git status
	*On branch master
	*All conflicts fixed but you are still merging.
	* (use "git commit" to conclude merge)
	*Changes to be committed:
 	*modified: index.html


- Apagar o branch

	+$ git branch -d hotfix

- Listar branches

	$ git branch
 	*iss53
	** master
	* testing

- Fluxo de branches

	Podem existir vários branches
		
		● master
		
			○ branch principal (produção)

		● develop

			○ branch de desenvolvimento, contém código “estável”
			○ testar estabilidade e integração
			○ usado para fazer merge dos branches dos issues/tarefas

		● topic
		
			○ para desenvolver issue/tarefas

