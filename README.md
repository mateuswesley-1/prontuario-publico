# Prontuário Eletrônico API

Este é um guia passo-a-passo para configurar e executar a API do prontuário eletrônico. A API está contida em um Docker Hub e usa PostgreSQL como banco de dados. Você pode encontrar a imagem Docker da API [aqui](https://hub.docker.com/repository/docker/mateus314/prontuario-api/general).

## Pré-Requisitos

Antes de começar, você precisará ter os seguintes requisitos instalados em seu sistema:

- **Docker:** Certifique-se de ter o Docker instalado em sua máquina. Você pode baixá-lo [aqui](https://www.docker.com/get-started).

- **docker-compose:** Este projeto utiliza o docker-compose para facilitar a execução dos contêineres. Certifique-se de ter o docker-compose instalado. Para instalar, siga as instruções [aqui](https://docs.docker.com/compose/install/).

## Executando a Aplicação

1. **Clone o Repositório:**

   ```shell
   git clone https://github.com/mateuswesley-1/prontuario-publico
   cd prontuario-publico
   ```

2. **Execute os Contêineres:**

   No diretório onde o arquivo `docker-compose.yml` está localizado, execute o seguinte comando:

   ```shell
   docker-compose up -d
   ```

   Este comando iniciará os contêineres necessários para a API e o banco de dados PostgreSQL.

3. **Verifique se a Aplicação Está Rodando:**

   Você pode verificar se a aplicação foi iniciada sem erros executando o seguinte comando:

   ```shell
   docker logs prontuario-api
   ```

   Certifique-se de que não há mensagens de erro nos logs.

## Autenticação

Para autenticar na API, você precisa enviar uma requisição POST para `http://localhost:8088/credenciais` com um JSON contendo as credenciais desejadas no corpo da requisição. Em seguida, você pode obter o token de acesso em `http://localhost:8088/token`.

Exemplo usando cURL:

```shell
curl -X POST -H "Content-Type: application/json" -d '{"username":"seu_usuario", "password":"sua_senha"}' http://localhost:8088/credenciais
```

Após obter o token, adicione-o ao cabeçalho de suas solicitações para acessar outros endpoints da API:

```shell
curl -H "Authorization: SeuTokenAqui" http://localhost:8088/seu-endpoint
```

## Documentação da API

Todos os endpoints disponíveis podem ser encontrados na documentação da API, que pode ser acessada através do Swagger. Uma vez que a aplicação esteja em execução, você pode acessar [localhost:8088/swagger-ui/index.html](http://localhost:8088/swagger-ui/index.html) para explorar e testar os endpoints disponíveis.

