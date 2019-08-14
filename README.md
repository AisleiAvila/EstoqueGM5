# EstoqueGM5
Prova Prática Java GM5
Instruções para realização de testes na Aplicação estoqueGM5:
a) Criar o banco de dados no Postgresql com o seguinte comando:
  •	create database estoquedb; commit;
b) A aplicação estoqueGM5 acessa banco de dados através dos seguintes parâmetros:
  •	URL: jdbc:postgresql://localhost:5432/estoquedb
  •	Usuário: postgres
  •	Senha: admin

c) Importar a aplicação estoqueGM5 no Eclipse. Foi utilizado o maven para importar as bibliotecas necessários para execução da aplicação;
d)	Executar a aplicação estoqueGMS no servidor Tomcat (foi utilizada a versão 9.0)
e)	Acessar a URL: http://localhost:8080/estoque/login.xhtml
 
f) Ao clicar no botão <Efetue Login> as tabelas serão criadas no banco de dados:
 
g)	Instrução SQL para criar usuário:
•	insert into usuario (id, email,senha) values (1,'aislei@outlook.com.br', 'teste'); commit;

h) Após inclusão do usuário pode-se acessar as funcionalidades da aplicação EstoqueGM5:
  Precisa cadastrar um novo Item
  Cadastrar Item no Estoque
  Clicar no botão <Gravar>
 
