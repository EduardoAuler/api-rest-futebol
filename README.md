# ⚽ API REST Futebol

Backend desenvolvido com Java + Spring Boot para gerenciamento de partidas de futebol, times, jogadores e estatísticas através de uma API REST.

---

# 🚀 Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data JPA
* Hibernate
* Spring Security
* Maven
* MySQL
* Jakarta Validation
* Lombok

---

# 📚 Funcionalidades

* Autenticação de usuários
* Cadastro de times
* Cadastro de jogadores
* Criação de partidas
* Controle de gols durante a partida
* Finalização de partidas
* Histórico de partidas
* Ranking de artilheiros
* Detalhamento dos gols por partida
* Relacionamentos JPA
* Persistência de dados

---

# 🏗️ Arquitetura

O projeto segue uma arquitetura em camadas:

```text
Controller -> Service -> Repository -> Database
```

### Camadas

* **Controller** → recebe requisições HTTP
* **Service** → regras de negócio
* **Repository** → acesso aos dados
* **Entity** → entidades persistidas
* **DTO** → comunicação entre API e cliente
* **Mapper** → conversão entre entidades e DTOs

---

# 📁 Estrutura do projeto

```text
src
 └── main
     ├── java
     │
     ├── controller
     ├── service
     ├── repository
     ├── model
     ├── dto
     ├── mapper
     ├── security
     └── exception
     │
     └── resources
         └── application.properties
```

---

# ⚙️ Como executar

## 1. Clonar o repositório

```bash
git clone https://github.com/EduardoAuler/api-rest-futebol.git
```

## 2. Entrar na pasta

```bash
cd api-rest-futebol
```

## 3. Configurar banco de dados

No arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/futebol
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
```

## 4. Executar aplicação

```bash
./mvnw spring-boot:run
```

ou execute a classe principal pela IDE.

---

# 🔐 Autenticação

## Endpoints

| Método | Endpoint         | Descrição         |
| ------ | ---------------- | ----------------- |
| POST   | `/auth/register` | Registrar usuário |
| POST   | `/auth/login`    | Realizar login    |

### Registro

```http
POST /auth/register
```

```json
{
  "username": "eduardo",
  "password": "123456"
}
```

### Login

```http
POST /auth/login
```

```json
{
  "username": "eduardo",
  "password": "123456"
}
```

---

# 👤 Jogadores

## Endpoints

| Método | Endpoint  | Descrição        |
| ------ | --------- | ---------------- |
| POST   | `/player` | Criar jogador    |
| GET    | `/player` | Listar jogadores |

### Criar jogador

```http
POST /player
```

```json
{
  "name": "Suárez"
}
```

---

# 🛡️ Times

## Endpoints

| Método | Endpoint                       | Descrição                 |
| ------ | ------------------------------ | ------------------------- |
| POST   | `/team`                        | Criar time                |
| GET    | `/team`                        | Listar times              |
| PATCH  | `/team/{id}`                   | Alterar nome do time      |
| PUT    | `/team/{id}/add/{playerId}`    | Adicionar jogador ao time |
| PUT    | `/team/{id}/remove/{playerId}` | Remover jogador do time   |
| DELETE | `/team/{id}`                   | Excluir time              |

### Criar time

```http
POST /team
```

```json
{
  "name": "Grêmio"
}
```

### Alterar nome

```http
PATCH /team/1
```

```json
{
  "name": "Grêmio FBPA"
}
```

---

# ⚽ Partidas

## Endpoints

| Método | Endpoint                  | Descrição         |
| ------ | ------------------------- | ----------------- |
| POST   | `/match`                  | Criar partida     |
| PATCH  | `/match/finish/{id}`      | Finalizar partida |
| POST   | `/match/add-goal`         | Registrar gol     |
| POST   | `/match/remove-goal/{id}` | Remover gol       |

### Criar partida

```http
POST /match
```

```json
{
  "teamAId": 1,
  "teamBId": 2,
  "minutos": 60
}
```

### Registrar gol

```http
POST /match/add-goal
```

```json
{
  "matchId": 1,
  "playerId": 5,
  "side": "TEAM_A"
}
```

### Finalizar partida

```http
PATCH /match/finish/1
```

---

# 📊 Estatísticas

## Endpoints

| Método | Endpoint                  | Descrição                    |
| ------ | ------------------------- | ---------------------------- |
| GET    | `/statistic/artilheiro`   | Ranking de artilheiros       |
| GET    | `/statistic/historico`    | Histórico de partidas        |
| GET    | `/statistic/details/{id}` | Detalhes dos gols da partida |

---

## Ranking de artilheiros

```http
GET /statistic/artilheiro
```

### Exemplo de resposta

```json
[
  {
    "name": "Suárez",
    "goals": 15
  },
  {
    "name": "Cristaldo",
    "goals": 10
  }
]
```

---

## Histórico de partidas

```http
GET /statistic/historico
```

Retorna todas as partidas já finalizadas pelo usuário.

---

## Detalhes de uma partida

```http
GET /statistic/details/1
```

Retorna a quantidade de gols marcados por cada jogador em uma partida específica.

### Exemplo de resposta

```json
[
  {
    "name": "Suárez",
    "goals": 2,
    "teamSide": "A"
  },
  {
    "name": "Cristaldo",
    "goals": 1,
    "teamSide": "A"
  },
  {
    "name": "Valencia",
    "goals": 1,
    "teamSide": "B"
  }
]
```

### Campos retornados

| Campo    | Tipo   | Descrição                   |
| -------- | ------ | --------------------------- |
| name     | String | Nome do jogador             |
| goals    | Long   | Quantidade de gols          |
| teamSide | Enum   | Time ao qual o gol pertence |

Essa consulta utiliza uma projeção DTO com JPQL e agregação através de `COUNT()` e `GROUP BY`, retornando o total de gols marcados por cada jogador na partida.

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

## Team → Players

```text
1 Time possui N Jogadores
1 Jogador pertence a 1 Time
```

## Match → Goals

```text
1 Partida possui N Gols
1 Gol pertence a 1 Partida
```

## Player → Goals

```text
1 Jogador pode possuir N Gols
1 Gol pertence a 1 Jogador
```

---

# 🧠 Conceitos aplicados

* API REST
* Spring Boot
* Spring Security
* DTO Pattern
* Mapper Pattern
* JPA/Hibernate
* Bean Validation
* Relacionamentos JPA
* Injeção de Dependência
* Tratamento Global de Exceções
* JPQL
* Projeções DTO
* COUNT()
* GROUP BY
* Consultas customizadas
* Arquitetura em camadas


# 👨‍💻 Autor

Eduardo Auler

GitHub:
https://github.com/EduardoAuler

---

# 📎 Repositório

https://github.com/EduardoAuler/api-rest-futebol
