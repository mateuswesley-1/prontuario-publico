# REST API prontuário eletrônico
API simplificada para um projeto de prontuário eletrônico

Para utilizar a API é necessário criar um arquivo .env com as variáveis de configuração do banco de dados, substituindo as variaveis com <> pelos valores desejados:
```
DATABASE_HOST=localhost  
DATABASE_PORT=<port>  
DATABASE_NAME=prontuario  
DATABASE_USERNAME=postgres  
DATABASE_PASSWORD=<password>  
```
### Exemplo:
```
DATABASE_HOST=localhost  
DATABASE_PORT=5331  
DATABASE_NAME=prontuario  
DATABASE_USERNAME=postgres  
DATABASE_PASSWORD=minha_senha  
```
Essas variáveis serão utilizadas pelo arquivo de configuração do container do banco de dados [docker-compose.yml](docker-compose.yml) e pelo arquivo de configuração do projeto [application-dev.yml](/src/main/resources/application-dev.yml).

## Rodando o projeto
### 1. Configurando container do banco de dados:

Primeiro certifique-se de ter o [docker](https://www.docker.com/products/docker-desktop/) instalado na sua máquina.
Na raiz do projeto rodar o comando:
```
$ docker compose up -d
[+] Running 14/14
 ✔ db 13 layers [⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿]      0B/0B      Pulled                                                                                                                            22.3s 
   ✔ a803e7c4b030 Pull complete                                                                                                                                                        8.8s 
   ✔ 009c876521a0 Pull complete                                                                                                                                                        8.8s 
   ✔ 9c412905cca2 Pull complete                                                                                                                                                        9.2s 
   ✔ 6463d4bf467a Pull complete                                                                                                                                                        9.3s 
   ✔ bd8b983728ed Pull complete                                                                                                                                                       10.3s 
   ✔ febc167f3560 Pull complete                                                                                                                                                       10.6s 
   ✔ d73c81c4ade3 Pull complete                                                                                                                                                       10.7s 
   ✔ 34b3b0ac6e9e Pull complete                                                                                                                                                       10.9s 
   ✔ 9bd86d074f4e Pull complete                                                                                                                                                       19.0s 
   ✔ 406f63329750 Pull complete                                                                                                                                                       19.0s 
   ✔ ec40772694b7 Pull complete                                                                                                                                                       19.1s 
   ✔ 7d3dfa1637e9 Pull complete                                                                                                                                                       19.1s 
   ✔ e217ca41159f Pull complete                                                                                                                                                       19.2s 
[+] Running 2/2
 ✔ Network prontuario-publico_default  Created                                                                                                                                         0.1s 
 ✔ Container postgres                  Started                                                                                                                                         7.4s                                                                                                                                         
```
Dessa forma o docker rodará os serviços configurados no arquivo [docker-compose.yml](docker-compose.yml), baixando as imagens necessárias e construindo os containers.

```
$ docker compose ps
NAME                IMAGE               COMMAND                  SERVICE             CREATED             STATUS              PORTS
postgres            postgres            "docker-entrypoint.s…"   db                  3 minutes ago       Up 3 minutes        0.0.0.0:5331->5432/tcp
```




