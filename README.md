# SpringBoot_Challenge02_Team_Exception_Gang
Week VIII - CompassUOL Challenge

# Desafio de Desenvolvimento de Microsserviços

Este repositório contém a implementação de um sistema de e-commerce com microsserviços. O sistema é composto por três microsserviços: Produto, Pedido e Feedback. Cada um deles fornece funcionalidades específicas e segue as boas práticas de desenvolvimento de software.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- MySQL
- Docker
- JUnit5 e Mockito para testes unitários
- Swagger Open API para documentação
- Postman para testes de requisições

## Estrutura de Pastas

A estrutura de pastas deste projeto segue os princípios de arquitetura de microsserviços, com a separação de responsabilidades, isolamento e reúso de código entre os microsserviços.

Cada microsserviço possui sua própria pasta e segue a seguinte estrutura:

- `src/main/java`: Contém o código fonte do microsserviço.
- `src/test/java`: Contém os testes unitários do microsserviço.
- `src/main/resources`: Contém os arquivos de configuração e scripts SQL.
- `Dockerfile`: O arquivo Dockerfile para criar uma imagem Docker do microsserviço.

## Configuração do Banco de Dados

O sistema utiliza um banco de dados MySQL chamado `db_ecommerce`. Para configurar o banco de dados, siga as instruções a seguir:

1. Crie um banco de dados MySQL chamado `db_ecommerce`.
2. Crie um usuário MySQL chamado `root` com a senha `root`.
3. Atribua ao usuário `root` todos os privilégios no banco de dados `db_ecommerce`.
4. Execute o script SQL do respectivo microsserviço para criar as tabelas e inserir dados iniciais.

## Funcionalidades dos Microsserviços

### Microsserviço Produto

O Microsserviço Produto permite a criação, leitura, atualização e exclusão de produtos.

Endpoints:
- GET /products: Retorna a lista de produtos.
- GET /products/:id: Retorna as informações de um produto específico.
- POST /products: Cria um novo produto.
- PUT /products/:id: Atualiza as informações de um produto existente.
- DELETE /products/:id: Exclui um produto existente.

### Microsserviço Pedido

O Microsserviço Pedido permite que os usuários façam pedidos de produtos de um catálogo.

Endpoints:
- GET /orders: Lista pedidos, ordenados por data de criação.
- GET /orders/:id: Retorna as informações de um pedido específico.
- POST /orders: Cria um novo pedido.
- PUT /orders/:id: Atualiza as informações de um pedido existente.
- POST /orders/:id/cancel: Cancela um pedido existente.

### Microsserviço Feedback

O Microsserviço Feedback permite que os usuários deixem feedbacks sobre produtos ou serviços.

Endpoints:
- GET /feedbacks: Lista todos os feedbacks.
- GET /feedbacks/:id: Retorna as informações de um feedback específico.
- POST /feedbacks: Cria um novo feedback.
- PUT /feedbacks/:id: Atualiza as informações de um feedback existente.
- DELETE /feedbacks/:id: Exclui um feedback existente.

## Testes

Cada microsserviço possui testes unitários que garantem a qualidade do código. A cobertura de testes é de pelo menos 75% para cada microsserviço.

## Documentação

A documentação dos microsserviços foi feita usando o Swagger Open API. Você pode acessar a documentação da API em `/swagger-ui.html` após iniciar o microsserviço.

## Como Executar

Certifique-se de ter o Docker instalado. Para executar os microsserviços, siga os passos abaixo:

1. Clone este repositório:
git clone <URL_DO_REPOSITÓRIO>
cd e-commerce-microservices

2. Para cada microsserviço, acesse a pasta correspondente e execute o seguinte comando para construir a imagem Docker:
docker build -t <NOME_DA_IMAGEM> .

3. Execute a imagem Docker em um contêiner:
docker run -p <PORTA_DESEJADA>:8080 -d <NOME_DA_IMAGEM>

Agora você pode acessar a API em `http://localhost:<PORTA_DESEJADA>`.

**Instrutor:** [Lucas Magno Peixoto Lima]

Este projeto foi desenvolvido como parte de um desafio de desenvolvimento de microsserviços e segue as melhores práticas de desenvolvimento de software. Obrigado por conferir!




