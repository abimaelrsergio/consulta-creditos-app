# Consulta de Créditos Constituídos – API + Front-End

Este projeto consiste em uma aplicação fullstack para consulta de créditos constituídos com base no número da NFS-e ou número do crédito. 

## 🔍 Funcionalidades

- Consulta de créditos constituídos por número da NFS-e.
- Consulta de detalhes de um crédito específico por número do crédito.
- Interface web para consulta e visualização dos dados.
- Containerização com Docker e geração de imagem via Jib.
- Integração com mensageria via Kafka.
- Testes automatizados com JUnit e Mockito.
- Documentação interativa da API com Swagger OpenAPI.

---

## 🛠️ Tecnologias Utilizadas

### Back-End
- Java 21+
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Jib Maven Plugin (build de imagem Docker)
- JUnit 5 + Mockito
- Springdoc OpenAPI (Swagger UI)

### Front-End
- Angular 11+
- Bootstrap 

### Infraestrutura
- Docker
- Docker Compose
- Kafka 

---

## 📁 Estrutura do Projeto

```
consulta-creditos-api/
├── credito-api/        # Projeto Spring Boot
├── frontend/           # Projeto Angular
├── docker/             # Dockerfiles e docker-compose.yml
├── .gitignore
└── README.md
```

---

## ▶️ Como Executar o Projeto

### Pré-requisitos

- Java 21+
- Node.js e Angular CLI
- Docker e Docker Compose

### 1. Clonar o repositório

```bash
git clone https://github.com/abimaelrsergio/consulta-creditos-app.git
cd consulta-creditos-app
```

### 2. Subir com Docker (recomendado)

```bash
cd docker
docker-compose up --build
```

A aplicação será acessível em:
- Front-end: http://localhost:4200
- Kafdrop: http://localhost:9000
- Swagger UI: http://localhost:8080/swagger-ui/index.html
- Back-end: http://localhost:8080/api/creditos
- Kafka Broker (interno): kafka:9092

> O Kafka é configurado com a imagem `apache/kafka:3.9.1` em modo KRaft (sem ZooKeeper), e os tópicos são criados automaticamente. A aplicação `credito-api` está preparada para publicar mensagens via Kafka.

### ⚙️ Observação importante sobre o Front-End Angular (produção com Docker)

Antes de subir o projeto com `docker-compose up`, é necessário compilar o front-end em modo de produção. Para isso, execute o seguinte comando dentro da pasta `frontend`:

```bash
cd frontend
npm install
npm run build --prod
```

Esse comando irá gerar os arquivos de produção dentro da pasta `dist/frontend/browser`. Esses arquivos serão usados pelo contêiner NGINX no Docker para servir a aplicação Angular.

Depois disso, volte ao diretório raiz do projeto e suba normalmente com:

```bash
docker-compose up --build -d
```

A interface web estará disponível em: [http://localhost:4200/consulta](http://localhost:4200/consulta)

### 3. Executar manualmente

#### Back-End

```bash
cd credito-api
./mvnw spring-boot:run
```

#### Front-End

```bash
cd frontend
npm install
ng serve
```

#### Banco de Dados (PostgreSQL local)

Se você estiver executando a aplicação fora do Docker, será necessário subir manualmente o banco de dados PostgreSQL com o seguinte comando:

```bash
docker run --name credito-db-test -e POSTGRES_DB=credito_db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -p 5432:5432 -d postgres:17.5
```

#### Kafka e Kafdrop localmente (modo manual, sem Docker Compose)

```bash
# 1. Crie a rede
docker network create kafka-net

# 2. Suba o Kafka
docker run -d   --name kafka-local   --network kafka-net   -p 9092:9092   -p 29092:29092   -e KAFKA_NODE_ID=1   -e KAFKA_PROCESS_ROLES=broker,controller   -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@kafka-local:9093   -e KAFKA_LISTENERS=PLAINTEXT://:9092,PLAINTEXT_DOCKER://:29092,CONTROLLER://:9093   -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092,PLAINTEXT_DOCKER://kafka-local:29092   -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=PLAINTEXT:PLAINTEXT,PLAINTEXT_DOCKER:PLAINTEXT,CONTROLLER:PLAINTEXT   -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER   -e KAFKA_AUTO_CREATE_TOPICS_ENABLE=true   -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1   apache/kafka:3.9.1

# 3. Suba o Kafdrop (painel web para visualizar os tópicos Kafka)
docker run -d   --name kafdrop   --network kafka-net   -p 9000:9000   -e KAFKA_BROKER_CONNECT=kafka-local:29092   obsidiandynamics/kafdrop:4.1.1-SNAPSHOT
```

Acesse o Kafdrop em: [http://localhost:9000](http://localhost:9000)

---

## 🏗️ Gerar Imagem Docker com Jib (sem Dockerfile)

A imagem Docker do projeto Spring Boot pode ser gerada diretamente com o plugin Jib:

```bash
cd credito-api
./mvnw compile jib:dockerBuild
```

A imagem será criada localmente com o nome:
```
abimaelrsergio/creditoapi:1.0.0
```

Você pode personalizar a tag conforme a versão declarada no `pom.xml`.

---

## 🧪 Testes

### Back-End

```bash
cd credito-api
./mvnw test
```

---

## 📦 API – Endpoints

### `GET /api/creditos/{numeroNfse}`

Retorna os créditos associados à NFS-e informada.

### `GET /api/creditos/credito/{numeroCredito}`

Retorna os detalhes de um crédito específico.

---

## 📬 Mensageria 

Sempre que uma consulta é realizada, uma mensagem é publicada em um tópico Kafka contendo os dados da operação, simulando um cenário de auditoria.

O Apache Kafka está configurado na versão `3.9.1`, rodando em modo KRaft (sem ZooKeeper). A aplicação se conecta ao broker usando a URL `kafka:9092` via variável de ambiente `SPRING_KAFKA_BOOTSTRAP_SERVERS`.

---

## 📝 Padrão de Commits

Este projeto segue o padrão [Conventional Commits](https://www.conventionalcommits.org/) para mensagens de commit.

Esse padrão facilita a organização do histórico, permite automações como geração de changelog e melhora a legibilidade das mudanças no projeto.

---

## 📃 Licença

```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
```

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
