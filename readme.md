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

- client: ```spring-rest-oauth2-jwt```

- secret: ```B6813193F1D7EC8BF5B40```

- Usuário comum: gabrielczar and 123456

- Administrador: gabrielczar.adm and 123456

- Recurso disponivel para todos os usuários: [http://localhost:8080/api/users](http://localhost:8080/api/users)
- Recurso administrativo: [http://localhost:8080/api/users/adm](http://localhost:8080/api/users/adm)

### Exemplos de request

- Gerar token de acesso: 

```http request
curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=usr -d password=pwd
curl spring-rest-oauth2-jwt:B6813193F1D7EC8BF5B40@localhost:8080/oauth/token -d grant_type=password -d username=gabrielczar -d password=123456
```

```json
{
  "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiREEyRDUzMkQxQkVqd3RyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6ImdhYnJpZWxjemFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTUyNzI2MjI5MiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImQ5MTI4NmFmLWMwYjEtNDQ1Ni1hOTVkLTcwYjMyYzVmM2E5ZCIsImNsaWVudF9pZCI6IjY2OTdhMTA1MzMxYzkxMTczYTc2MzgxZWJkMjQ5Mjc4In0.wNFb3iXdcXavNCjSSzorWVwFg27n0eebRS1XrT3Ans8",
  "token_type":"bearer",
  "expires_in":43199,
  "scope":"read write",
  "jti":"d91286af-c0b1-4456-a95d-70b32c5f3a9d"
 }
```

- Acessar recurso:
```http request
curl http://localhost:8080/api/users -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiREEyRDUzMkQxQkVqd3RyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6ImdhYnJpZWxjemFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTUyNzI2MjI5MiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImQ5MTI4NmFmLWMwYjEtNDQ1Ni1hOTVkLTcwYjMyYzVmM2E5ZCIsImNsaWVudF9pZCI6IjY2OTdhMTA1MzMxYzkxMTczYTc2MzgxZWJkMjQ5Mjc4In0.wNFb3iXdcXavNCjSSzorWVwFg27n0eebRS1XrT3Ans8"
```

```json
[
  {
    "id":1,
    "username":"gabrielczar.adm",
    "firstName":"Gabriel",
    "lastName":"Czar",
    "authorities":[
      {
        "id":1,
        "name":"ROLE_ADMIN",
        "description":null,
        "authority":"ROLE_ADMIN"
      }
     ]
  },
  {
    "id":2,
    "username":"gabrielczar",
    "firstName":"Gabriel",
    "lastName":"Czar",
    "authorities":[
      {
        "id":2,
        "name":"ROLE_USER",
        "description":null,
        "authority":"ROLE_USER"
      }
    ]
  }
]
```