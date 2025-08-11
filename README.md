# Login API

Uma API REST simples para cadastro e autenticação de usuários, desenvolvida com Spring Boot, Spring Data JPA e PostgreSQL.

## Funcionalidades

- Cadastro de usuários (`/api/register`)
- Login de usuários (`/api/login`)
- Persistência dos dados em banco relacional (PostgreSQL)
- Segurança configurável com Spring Security

## Endpoints

### 1. Cadastro de Usuário

- **URL:** `/api/register`
- **Método:** `POST`
- **Body (JSON):**
    ```json
    {
      "email": "usuario@email.com",
      "name": "Nome do Usuário",
      "pass": "senha123"
    }
    ```
- **Resposta:** 201 Created ou erro de validação

### 2. Login de Usuário

- **URL:** `/api/login`
- **Método:** `POST`
- **Body (JSON):**
    ```json
    {
      "email": "usuario@email.com",
      "pass": "senha123"
    }
    ```
- **Resposta:** 200 OK (usuário autenticado) ou 401 Unauthorized

## Configuração

### Banco de Dados

No arquivo `application.properties`, configure sua conexão com o PostgreSQL:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/seubanco
spring.datasource.username=seuusuario
spring.datasource.password=suasenha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### Porta da Aplicação

Por padrão, a aplicação roda na porta 8081. Para alterar, edite em `application.properties`:

```
server.port=8081
```

## Segurança

A configuração de segurança permite acesso público aos endpoints `/api/register` e `/api/login`. Os demais endpoints exigem autenticação.

## Como rodar

1. Instale o Java 21 e o PostgreSQL.
2. Configure o banco de dados e o arquivo `application.properties`.
3. Execute:
    ```sh
    ./mvnw spring-boot:run
    ```
   ou, se preferir:
    ```sh
    mvn spring-boot:run
    ```
4. Use ferramentas como Postman ou Insomnia para testar os endpoints.

## Observações

- O projeto utiliza Spring Boot 3.x e Java 21.
- As senhas são armazenadas como texto puro (apenas para fins didáticos). Para produção, utilize hashing seguro (ex: BCrypt).
- O projeto pode ser expandido para JWT, roles, etc.

---