# Implementando a Camada de Segurança com o Spring Security na Loja de Games

Nesta atividade iremos implementar a camada de segurança da aplicação com o Spring Security. 

<h2>Boas Práticas</h2>

- [x] Configure as Dependências no arquivo pom.xml
  - [x] spring-boot-starter-security
  - [x] commons-codec
- [x] Configure o Banco de Dados
- [x] Crie a Classe Usuario na Camada Model
  - [x] Crie o Relacionamento (One to Many) com a Classe Postagem
  - [x] Crie os Métodos Get e Set
- [x] Crie o Relacionamento (Many to one) entre as Classes Postagem e Usuario na Classe Postagem na Camada Model
- [x] Crie a Classe UsuarioLogin na Camada Model
  - [x] Crie os Métodos Get e Set
- [x] Crie a Classe UsuarioRepository Camada Repository
  - [x] Crie o método findAllByNomeContainingIgnoreCase()
  - [x] Crie o Método findByUsuario()
  - [x] Crie o Método findByNome()
- [x] Crie a Classe UsuarioController na Camada Controller
  - [x] Crie o Método Login
  - [x] Crie o Método Post
  - [x] Crie o Método Put
  - [x] Crie o Método GetAll
  - [x] Crie o Método GetById()
- [x] Crie a classe BasicSecurityConfig na Camada Security
- [x] Crie a classe UserDetailsImpl na Camada Security
- [x] Crie a classe UserDetailsService na Camada Security
- [x] Crie a classe UsuarioService na Camada Service
  - [x] Crie o Método Login
  - [x] Crie o Método Post
  - [x] Crie o Método Put
- [x] Execute os testes no Postman
  - [x] Cadastrar Usuário
  - [x] Logar com o Usuário para receber o Token
  - [x] Inserir o Token no Cabeçalho de todos os métodos antes de testar


<h2>Referências</h2>

<a href="https://spring.io/projects/spring-security" target="_blank">Documentação Oficial do Spring Security</a>

<a href="https://jwt.io/" target="_blank">Página Oficial do JWT</a>

<a href="https://commons.apache.org/proper/commons-codec/" target="_blank">Dependência commons-codec</a>

