# Implementando a Camada de Segurança com o Spring Security no Blog Pessoal

Nesta atividade iremos implementar a camada de segurança da aplicação com o Spring Security. 

<h2>Boas Práticas</h2>

- [x] Adicione as Dependências abaixo no arquivo pom.xml
  - [x] spring-boot-starter-security (<a href="https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security/2.5.4"><b>Maven Repository</b></a>)
  - [x] commons-codec (<a href="https://mvnrepository.com/artifact/commons-codec/commons-codec/1.15"><b>Maven Repository</b></a>)
- [x] Configure o Banco de Dados (<b>Já configurado!</b>)
- [x] Crie a Classe Usuario na Camada Model
- [x] Crie o Relacionamento da Classe Usuario com a Classe Postagem
  - [x] Crie o Relacionamento @OneToMany
  - [x] Crie os respectivos Métodos Get e Set
- [x] Crie o Relacionamento da Classe Postagem com a Classe Usuario
  - [x] Crie o Relacionamento @ManyToOne
  - [x] Crie os respectivos Métodos Get e Set
- [x] Crie a Classe UsuarioLogin na Camada Model
  - [x] Insira os atributos
  - [x] Crie os Métodos Get e Set
  - [x] <b>Importante: Não inserir nenhuma anotação nesta classe</b>
- [x] Crie a Classe UsuarioRepository na Camada Repository
  - [x] Crie o Método findByUsuario
  - [x] Crie o Método findByNome
  - [x] Crie o Método findAllByNomeContainingIgnoreCase
- [x] Crie a classe BasicSecurityConfig na Camada Security
- [x] Crie a classe UserDetailsImpl na Camada Security
- [x] Crie a classe UserDetailsService na Camada Security
- [x] Crie a classe UsuarioService na Camada Service
  - [x] Implemente o Método autenticarUsuario
  - [x] Implemente o Método cadastrarUsuario
  - [x] Implemente o Método atualizarUsuario
- [x] Crie a Classe UsuarioController na Camada Controller
  - [x] Crie o Método login
  - [x] Crie o Método postUsuario
  - [x] Crie o Método putUsuario
- [x] Execute os testes no Postman
  - [x] Cadastre um Usuário
  - [x] Faça o login com o Usuário cadastrado para receber o Token
  - [x] Insira o Token no Cabeçalho de todos os métodos do Postman antes de testar


<h2>Referências</h2>

<a href="https://spring.io/projects/spring-security" target="_blank">Documentação Oficial do Spring Security</a>

<a href="https://www.base64url.com/" target="_blank">Conversor Base 64</a>

<a href="https://commons.apache.org/proper/commons-codec/" target="_blank">Dependência commons-codec</a>

