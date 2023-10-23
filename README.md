# REST API prontuário eletrônico
A imagem docker da API está disponível no [docker hub](https://hub.docker.com/repository/docker/mateus314/prontuario-api/general)
O banco de dados usado foi o postgreSQL, também utilizando um container docker.


## 1) Executando a aplicação:

A aplicação pode ser executada usando sua imagem docker, todas as configurações necessárias estão no arquivo [docker-compose.yml](docker-compose.yml). Para isso executamos o seguinte comando na raiz da aplicação:

``` 
mateu@kimJonUn MINGW64 ~/.../prontuario-publico (main)
$ docker compose up -d
[+] Running 10/10
 ✔ prontuario-api 9 layers [⣿⣿⣿⣿⣿⣿⣿⣿⣿]      0B/0B      Pulled                                                                                                                 10.6s 
   ✔ 707e32e9fc56 Already exists                                                                                                                                               0.0s 
   ✔ 8e560b9ae2a6 Already exists                                                                                                                                               0.0s 
   ✔ ddf40dd63c73 Pull complete                                                                                                                                                6.5s 
   ✔ b25222345e66 Pull complete                                                                                                                                                6.6s 
   ✔ b9d4c0fe5879 Pull complete                                                                                                                                                6.6s 
   ✔ 31e228b5786b Pull complete                                                                                                                                                7.4s 
   ✔ dfc43954d63a Pull complete                                                                                                                                                7.5s 
   ✔ 6eb64e808ff3 Pull complete                                                                                                                                                7.8s 
   ✔ a97b64d7f45d Pull complete                                                                                                                                                7.8s 
[+] Running 3/3
 ✔ Network prontuario-publico_db  Created                                                                                                                                      0.1s 
 ✔ Container postgres             Started                                                                                                                                      1.3s 
 ✔ Container prontuario-api       Started                                                                                                                                      1.6s 
```

Podemos também verificar se não ocorreu nenhum erro na execução da aplicação:
```
mateu@kimJonUn MINGW64 ~/.../prontuario-publico (main)
$ docker logs prontuario-api

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v3.1.4)

2023-10-21T23:19:21.320Z  INFO 1 --- [           main] c.d.prontuario.ProntuarioApplication     : Starting ProntuarioApplication using Java 20.0.2 with PID 1 (/app/classes started by root in /)
.....

2023-10-21T23:19:26.272Z  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2023-10-21T23:19:26.281Z  INFO 1 --- [           main] c.d.prontuario.ProntuarioApplication     : Started ProntuarioApplication in 5.759 seconds (process running for 6.545)  
```



