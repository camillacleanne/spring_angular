<h1>Implementando os Métodos Curtir, Descurtir e TrendProducts na Loja de Games</h1>


<h2>Boas Práticas</h2>

- [x] Curtir e Descurtir Produtos
  - [x] Classe Produtos
    - [x] Criar o atributo inteiro curtidos
    - [x] Criar os métodos Get e Set
  - [x] Criar a classe ProdutoService
    - [x] Implementar os métodos Curtir, Descurtir e buscarProdutoPeloId
  - [x] Classe ProdutoController
    - [x] Criar os endpoints curtir e descurtir do tipo PUT
- [x] Trend Products por Categoria
  - [x] Classe Categoria
    - [x] Criar o atributo inteiro numeroProdutos com a anotação @Transient
    - [x] Criar os métodos Get e Set
  - [x] Interface ProdutoRepository
    - [x] Criar o método contarProdutos utilizando a anotação @Query
  - [x] Criar a classe CategoriaService
    - [x] Implementar o método trendProducts
  - [x] CategoriaController
    - [x] Criar o endpoint trendproducts do tipo GET

