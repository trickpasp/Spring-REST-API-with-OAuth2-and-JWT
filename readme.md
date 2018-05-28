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
curl spring-rest-oauth2-jwt:B6813193F1D7EC8BF5B40@localhost:8080/oauth/token \
    -d grant_type=password -d username=gabrielczar -d password=123456
```

```json
{
  "access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiREEyRDUzMkQxQkVqd3RyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6ImdhYnJpZWxjemFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImV4cCI6MTcyNzMwNTQ3OCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6ImY4MTJkYzBlLWZiZmItNDQ1My04ZDI2LTY5NmM2NWQ0MzA5ZiIsImNsaWVudF9pZCI6InNwcmluZy1yZXN0LW9hdXRoMi1qd3QifQ.ExobK5qYHzSxVpoPUvT8uQwBfZwsefYWsEjsxJopni0",
  "token_type":"bearer",
  "refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsiREEyRDUzMkQxQkVqd3RyZXNvdXJjZWlkIl0sInVzZXJfbmFtZSI6ImdhYnJpZWxjemFyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6ImY4MTJkYzBlLWZiZmItNDQ1My04ZDI2LTY5NmM2NWQ0MzA5ZiIsImV4cCI6MTUyOTg5NjQ5MCwiYXV0aG9yaXRpZXMiOlsiUk9MRV9VU0VSIl0sImp0aSI6IjZlNWMyNDRhLTU5NDQtNDNhMy05ZjJlLTkwMWQ1ZDFkNTJjYiIsImNsaWVudF9pZCI6InNwcmluZy1yZXN0LW9hdXRoMi1qd3QifQ.E3w8BWVdtFyw6eBwBokXIXRtE3-oxdwH0JgLf-13wfo",
  "expires_in":604800,
  "scope":"read write",
  "jti":"f812dc0e-fbfb-4453-8d26-696c65d4309f"
}
```

- Renovar Token:
```http request
curl -X POST -H 'Authorization: Basic dGVzdGNsaWVudDpzZWNyZXQ=' -d 'refresh_token=fdb8fdbecf1d03ce5e6125c067733c0d51de209c&grant_type=refresh_token' localhost:8080/oauth/token
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