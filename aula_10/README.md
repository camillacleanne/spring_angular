<h1>Implementando Testes na API com o Spring Testing + JUnit 5 na Loja de Games</h1>


<h2>Boas Práticas</h2>

- [x] Configure as Dependências no arquivo pom.xml
  - [x] Desabilitar as versões anteriores do Junit
- [x] Configure o Banco de Dados de testes
  - [x] Criar a Source Folder /src/test/resources
  - [x] Criar na nova Source Folder o arquivo application.properties
  - [x] Configurar o Banco de Dados de teste
- [x] Crie os métodos construtores na Classe Usuario
  - [x] Construtor com os atributos
  - [x] Construtor vazio
- [x] Crie a Classe de testes na Camada Model: UsuarioTest
- [x] Crie a Classe de testes na Camada Repository: UsuarioRepositoryTest
- [x] Crie a Classe de testes na Camada Controller: UsuarioControllerTest
- [x] Execute os testes com o Junit