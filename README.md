# API REST B2W

## Dependências da aplicação

- Java 8
- Maven
- Docker
- Docker Compose

## Instruções para executar a aplicação

Dentro da pasta rest_api, executar os comandos:

```shell
$ mvn package
$ docker-compose up
```

## Testando a API

Para facilitar o processo de teste da API, o arquivo B2W.postman_collection.json pode ser importado na ferramenta Postman.

## Adicionar um planeta

#### POST localhost:8080/api/planets
```json
header  Content-Type : application/json
body    { "name": "Coruscant", "climate": "temperate", "terrain": "cityscape, mountains" }
```

## Listar planetas do banco de dados

#### GET localhost:8080/api/planets

## Listar planetas da API do Star Wars

#### GET localhost:8080/api/swapi/planets

A API pública do Star Wars utiliza paginação em seus resultados. A API criada no teste suporta tal paginação.

#### GET localhost:8080/api/swapi/planets?page="Número da página"

## Buscar por nome no banco de dados

#### GET localhost:8080/api/planets?name="Nome a ser procurado"

## Buscar por ID no banco de dados

#### GET localhost:8080/api/planets/"ID do planeta"

## Remover planeta

#### DELETE localhost:8080/api/planets/"ID do planeta"

## Atualizar planeta

#### PUT localhost:8080/api/planets/"ID do planeta"

```json
header  Content-Type : application/json
body    { "name": "Utapau", "climate": "temperate, arid, windy", "terrain": "scrublands, savanna, canyons, sinkholes",	"appears": 1 }
```

<br>

## Observações

A inicialização do container do MongoDB pode falhar caso uma estância do MongoDB já esteja rodando na maquina.