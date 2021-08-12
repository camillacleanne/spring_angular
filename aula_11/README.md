<h1>Implementando os Métodos Curtir, Descurtir e TrendTopics</h1>


<h2>Boas Práticas</h2>

- [x] Curtir e Descurtir postagens
  - [x] Classe Postagem
    - [x] Criar o atributo inteiro curtidas
    - [x] Criar os métodos Get e Set
  - [x] Criar a classe PostagemService
    - [x] Implementar os métodos Curtir, Descurtir e buscarPostagemPeloId
  - [x] Classe PostagemController
    - [x] Criar os endpoints curtir e descurtir do tipo PUT
- [x] Trend Topics por temas
  - [x] Classe Tema
    - [x] Criar o atributo inteiro qtdTema com a anotação @Transient
    - [x] Criar os métodos Get e Set
  - [x] Interface PostagemRepository
    - [x] Criar o método countPosts utilizando a anotação @Query
  - [x] Criar a classe TemaService
    - [x] Implementar o método trendTopics
  - [x] TemaController
    - [x] Criar o endpoint trendtopics do tipo GET

