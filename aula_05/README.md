# Implementando a Camada de Segurança com o Spring Security no Blog Pessoal

Nesta atividade iremos implementar a cama de segurança da aplicação com o Spring Security. 



<h2>Boas Práticas</h2>

- [ ] Configure as Dependências no arquivo pom.xml
  - [ ] spring-boot-starter-security
  - [ ] commons-codec
- [ ] Configure o Banco de Dados
- [ ] Crie a Classe Usuario na Camada Model
  - [ ] Crie o Relacionamento (One to Many) com a Classe Postagem
  - [ ] Crie os Métodos Get e Set
- [ ] Crie o Relacionamento (Many to one) entre as Classes Postagem e Usuario na Classe Postagem na Camada Model
- [ ] Crie a Classe UsuarioLogin na Camada Model
  - [ ] Crie os Métodos Get e Set
- [ ] Crie a Classe UsuarioRepository Camada Repository
  - [ ] Crie o Método findByUsuario()
- [ ] Crie a Classe UsuarioController na Camada Controller
  - [ ] Crie o Método Login
  - [ ] Crie o Método Post
  - [ ] Crie o Método Put
  - [ ] Crie o Método GetAll
  - [ ] Crie o Método GetById()
- [ ] Crie a classe BasicSecurityConfig na Camada Security
- [ ] Crie a classe UserDetailsImpl na Camada Security
- [ ] Crie a classe UserDetailsService na Camada Security
- [ ] Crie a classe UsuarioService na Camada Service
  - [ ] Crie o Método Login
  - [ ] Crie o Método Post
  - [ ] Crie o Método Put
- [ ] Execute os testes no Postman
  - [ ] Cadastrar Usuário
  - [ ] Logar com o Usuário para receber o Token
  - [ ] Inserir o Token no Cabeçalho de todos os métodos antes de testar

<h2>Criptografia</h2>

<a href="https://github.com/rafaelq80/Spring/tree/main/aula_05/criptografia" target="_blank">Conteúdo de apoio sobre Criptografia</a>

<h2>Referências</h2>

<a href="https://spring.io/projects/spring-security" target="_blank">Documentação Oficial do Spring Security</a>

<a href="https://jwt.io/" target="_blank">Página Oficial do JWT</a>

<a href="https://www.base64url.com/" target="_blank">Conversor - Base 64</a>

<a href="https://commons.apache.org/proper/commons-codec/" target="_blank">Dependência commons-codec</a>

