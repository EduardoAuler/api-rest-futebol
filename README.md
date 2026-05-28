# ⚽ API REST Futebol

Backend desenvolvido com Java + Spring Boot para gerenciamento de times, jogadores, partidas e estatísticas de futebol através de uma API REST.

---

# 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven
* MySQL
* Jakarta Validation
* Lombok

---

# 📚 Funcionalidades

* CRUD de times
* CRUD de jogadores
* Gerenciamento de partidas
* Sistema de gols
* Histórico de partidas
* Ranking de artilheiros
* Autenticação
* Relacionamentos entre entidades
* Persistência com JPA/Hibernate

---

# 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

```text
Controller -> Service -> Repository -> Database
```

## Camadas

* **Controller** → recebe requisições HTTP
* **Service** → regras de negócio
* **Repository** → acesso ao banco
* **Entity/Model** → entidades do sistema
* **DTO** → comunicação da API

---

# 📁 Estrutura do projeto

```text
src
 └── main
     ├── java
     │    └── com/seu_pacote
     │         ├── controller
     │         ├── service
     │         ├── repository
     │         ├── model
     │         ├── dto
     │         ├── mapper
     │         └── security
     │
     └── resources
          ├── application.properties
```

---

# ⚙️ Como executar

## 1. Clone o repositório

```bash
git clone https://github.com/EduardoAuler/api-rest-futebol.git
```

## 2. Entre na pasta

```bash
cd api-rest-futebol
```

## 3. Configure o banco de dados

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/futebol_db
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

---

# ▶️ Executando o projeto

Execute com Maven:

```bash
./mvnw spring-boot:run
```

Ou execute a classe principal pela IDE.

---

# 🌐 Endpoints

# 🔐 Autenticação

| Método | Endpoint         | Descrição      |
| ------ | ---------------- | -------------- |
| POST   | `/auth/login`    | Realiza login  |
| POST   | `/auth/register` | Cria uma conta |

## Exemplo Login/Register

```json
{
  "username": "eduardo",
  "password": "123456"
}
```

---

# ⚽ Partidas

| Método | Endpoint                  | Descrição        |
| ------ | ------------------------- | ---------------- |
| POST   | `/match`                  | Cria partida     |
| PATCH  | `/match/finish/{id}`      | Finaliza partida |
| POST   | `/match/add-goal`         | Adiciona gol     |
| POST   | `/match/remove-goal/{id}` | Remove gol       |

## Criar partida

```json
{
  "teamAId": 1,
  "teamBId": 2,
  "minutos": 90
}
```

## Adicionar gol

```json
{
  "matchId": 1,
  "playerId": 5,
  "side": "A"
}
```

---

# 👤 Jogadores

| Método | Endpoint  | Descrição       |
| ------ | --------- | --------------- |
| POST   | `/player` | Cria jogador    |
| GET    | `/player` | Lista jogadores |

## Criar jogador

```json
{
  "name": "Suárez"
}
```

---

# 🛡️ Times

| Método | Endpoint                       | Descrição                |
| ------ | ------------------------------ | ------------------------ |
| POST   | `/team`                        | Cria time                |
| GET    | `/team`                        | Lista times              |
| PATCH  | `/team/{id}`                   | Altera nome do time      |
| PUT    | `/team/{id}/add/{playerId}`    | Adiciona jogador ao time |
| PUT    | `/team/{id}/remove/{playerId}` | Remove jogador do time   |
| DELETE | `/team/{id}`                   | Remove time              |

## Criar time

```json
{
  "name": "Grêmio"
}
```

## Alterar nome do time

```json
{
  "name": "Grêmio FBPA"
}
```

---

# 📊 Estatísticas

| Método | Endpoint                | Descrição             |
| ------ | ----------------------- | --------------------- |
| GET    | `/statistic/artilheiro` | Lista artilheiros     |
| GET    | `/statistic/historico`  | Histórico de partidas |

---

# ⚠️ Como funcionam as partidas

As partidas utilizam os IDs dos times no momento da criação:

```json
{
  "teamAId": 1,
  "teamBId": 2
}
```

Porém, o sistema mantém o histórico das partidas independente das alterações futuras dos times.

Isso significa que:

* jogadores podem ser adicionados/removidos
* nomes dos times podem mudar
* elencos podem mudar com o tempo

Mesmo assim, as partidas continuam preservadas no histórico.

Essa abordagem evita:

* acoplamento excessivo entre entidades
* inconsistências históricas
* problemas de atualização em cascata

---

# 🗄️ Relacionamentos

## Team 1:N Player

```text
Um time possui vários jogadores
Um jogador pertence a um time
```

## Match

```text
Uma partida possui:
- Time A
- Time B
- Gols
- Tempo da partida
- Histórico de eventos
```

---

# ✅ Conceitos aplicados

* API REST
* HTTP Methods
* DTOs
* Relacionamentos JPA
* Bean Validation
* Injeção de dependência
* Tratamento de exceções
* Arquitetura em camadas
* Autenticação
* Persistência de dados

---

# 📌 Melhorias futuras

* Swagger/OpenAPI
* Docker
* Testes automatizados
* Paginação
* Deploy na nuvem
* WebSocket para partidas em tempo real

---

# 👨‍💻 Autor

Desenvolvido por Eduardo Auler

GitHub:
https://github.com/EduardoAuler

---

# 📎 Repositório

https://github.com/EduardoAuler/api-rest-futebol
