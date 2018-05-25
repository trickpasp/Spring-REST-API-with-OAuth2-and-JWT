# Spring REST API with OAuth2 and JWT

### Para executar a aplicação

- Maven

```
mnv clean spring-boot:run
```

- Docker 

```
mvn clean package docker:build
mvn docker:start
```

### Informações adicionais

- client: ```6697a105331c91173a76381ebd249278```

- secret: ```B6813193F1D7EC8BF5B40183CAC2C160A946E43DFAA300C053292```

- Usuário comum: gabrielczar and 123456

- Administrador: gabrielczar.adm and 123456

- Recurso disponivel para todos os usuários: [http://localhost:8080/api/users](http://localhost:8080/api/users)
- Recurso administrativo: [http://localhost:8080/api/users/adm](http://localhost:8080/api/users/adm)