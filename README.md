# Consulta de Créditos Constituídos – API + Front-End

Este projeto consiste em uma aplicação fullstack para consulta de créditos constituídos com base no número da NFS-e ou número do crédito. 

## 🔍 Funcionalidades

- Consulta de créditos constituídos por número da NFS-e.
- Consulta de detalhes de um crédito específico por número do crédito.
- Interface web para consulta e visualização dos dados.
- Containerização com Docker e geração de imagem via Jib.
- Integração com mensageria via Kafka.
- Testes automatizados com JUnit e Mockito.

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
git clone https://github.com/seu-usuario/consulta-creditos-api.git
cd consulta-creditos-api
```

### 2. Subir com Docker (recomendado)

```bash
docker-compose up --build
```

A aplicação será acessível em:
- Back-end: http://localhost:8080/api/creditos
- Front-end: http://localhost:4200

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

---

## 📃 Licença

```
                                 Apache License
                           Version 2.0, January 2004
                        http://www.apache.org/licenses/
```

[Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0)
