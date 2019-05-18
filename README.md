# API REST Planetas Star Wars

Essa é uma API REST criada na fase de testes para a vaga de Back-End na B2W Digital.  
Esta aplicação foi escrita em Java, utilizando o framework Spring e o banco de dados MongoDB.  
A aplicação e o banco de dados rodam em containers Docker.

## Dependências da aplicação

- Java 8
- Maven
- Docker
- Docker Compose

## Instruções para construir e executar a aplicação

Dentro da pasta rest_api, executar os comandos:

- Para construir a aplicação:

```shell
$ mvn package
```
- Para executar a aplicação:

```shell
$ docker-compose up
```

## Testando a API

Para facilitar o processo de teste da API, a coleção de requisições Http prontas que estão no arquivo Teste_API_REST.postman_collection.json podem ser importadas na ferramenta Postman.

## Adicionar um planeta (com nome, clima e terreno)

#### POST localhost:8080/api/planets

Header: `Content-Type: application/json`  
Body:
```json
{
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains"
}
```

Utiliza o nome fornecido para descrobrir quantas aparições em filmes o planeta possui, através da [API Star Wars](https://swapi.co/), e retorna o planeta criado, por exemplo:
```json
{
    "id": "5cddfcd4a7b11b000142b67f",
    "name": "Coruscant",
    "climate": "temperate",
    "terrain": "cityscape, mountains",
    "appears": 4
}
```

## Listar planetas do banco de dados

#### GET localhost:8080/api/planets

Retorna uma lista com todos os planetas na base de dados, por exemplo:
```json
[
    {
        "id": "5cddfcdca7b11b000142b683",
        "name": "Alderaan",
        "climate": "temperate",
        "terrain": "grasslands, mountains",
        "appears": 2
    },
    {
        "id": "5cddfcd8a7b11b000142b681",
        "name": "Naboo",
        "climate": "temperate",
        "terrain": "grassy hills, swamps, forests, mountains",
        "appears": 4
    }
]
```
## Listar planetas da API do Star Wars

#### GET localhost:8080/api/swapi/planets

Repassa a resposta da API Star Wars.

A API Star Wars utiliza paginação em seus resultados. A API criada no teste suporta tal paginação.

#### GET localhost:8080/api/swapi/planets?page="Número da página"

## Buscar por nome no banco de dados

#### GET localhost:8080/api/planets?name="Nome a ser procurado"

Retorna uma lista com todos os planetas que se encaixam no termo pesquisado, por exemplo(`name=an`):

```json
[
    {
        "id": "5cddfcdca7b11b000142b683",
        "name": "Alderaan",
        "climate": "temperate",
        "terrain": "grasslands, mountains",
        "appears": 2
    },
    {
        "id": "5cddfcd4a7b11b000142b67f",
        "name": "Coruscant",
        "climate": "temperate",
        "terrain": "cityscape, mountains",
        "appears": 4
    }
]
```

## Buscar por ID no banco de dados

#### GET localhost:8080/api/planets/"ID do planeta"

Retorna o planeta com o ID igual ao fornecido na URL da requisição, por exemplo (`planets/5cddfcdca7b11b000142b683`):

```json
{
    "id": "5cddfcdca7b11b000142b683",
    "name": "Alderaan",
    "climate": "temperate",
    "terrain": "grasslands, mountains",
    "appears": 2
}
```
## Remover planeta

#### DELETE localhost:8080/api/planets/"ID do planeta"

Remove o planeta com o ID igual ao fornecido na URL da requisição e retorna o status 200 OK.

## Atualizar planeta

#### PUT localhost:8080/api/planets/"ID do planeta"

Header: `Content-Type: application/json`  
Body:
```json
{
    "name": "Utapau",
    "climate": "temperate, arid, windy",
    "terrain": "scrublands, savanna, canyons, sinkholes",
    "appears": 1
}
```

Atualiza o planeta com o ID igual ao fornecido na URL da requisição e retorna o planeta atualizado, por exemplo (`planets/5cddfcdca7b11b000142b683`):

```json
{
    "id": "5cddfcdca7b11b000142b683",
    "name": "Utapau",
    "climate": "temperate, arid, windy",
    "terrain": "scrublands, savanna, canyons, sinkholes",
    "appears": 1
}
```

<br>

## Observações

A inicialização do container do MongoDB pode falhar caso uma estância do MongoDB já esteja rodando na maquina.  
Finalizar o processo local do MongoDB antes de iniciar os containers resolve o problema.
