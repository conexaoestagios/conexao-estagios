
# 📘 Conexão Estágios

**Conexão Estágios** é uma iniciativa tecnológica que utiliza Inteligência Artificial para conectar estudantes universitários a empresas de forma eficiente, rápida e personalizada.

## 🚀 Objetivo
Facilitar o processo de recrutamento e seleção, aproximando talentos em formação das empresas que buscam novos profissionais para suas equipes.

## 🔍 Como Funciona
- Estudantes se cadastram na plataforma e informam suas preferências, habilidades e áreas de interesse.
- Empresas publicam vagas de estágio e definem os critérios desejados.
- A IA faz o match entre os perfis com maior compatibilidade, otimizando o tempo e aumentando a qualidade das contratações.

## 👥 Público-alvo
- **Estudantes universitários** em busca de estágio.
- **Empresas** que desejam recrutar com mais agilidade e assertividade.

---

## 📑 Conteúdo

- [Sobre](#📘-conexão-estágios)
- [Autenticação de Usuário](#🔐-autenticação-de-usuário)
- [Licença](#📄-licença)

🔗 **Este repositório representa a API do projeto _Conexão Estágios_**, responsável por gerenciar a autenticação, cadastros, segurança e integração com o banco de dados.  
Logo abaixo, você encontrará os detalhes completos sobre o fluxo de autenticação e uso da aplicação.

## 📦 Tecnologias Utilizadas
- Java
- Spring Boot
- JWT para autenticação
- PostgreSQL
- Docker
- Git

## ⚙️ Funcionalidades

## 🔐 Autenticação de Usuário 

A autenticação da aplicação é baseada em **JWT (JSON Web Token)** e permite que usuários façam login com `username` e `password` e acessem rotas protegidas utilizando o token gerado.

### 1. Requisição de Login

**Endpoint**:  
`POST /api/v1/auth/login`

**Headers**:  
`Content-Type: application/json`

`Authentication: no auth`

**Body (JSON)**:
```json
{
  "username": "usuario_exemplo",
  "password": "senha_segura"
}
```

### 2. Resposta com JWT Token
Se as credenciais estiverem corretas, o servidor retorna:

**Status**: 200 OK

**Response Body**:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```

**Obs:** O token contém os seguintes dados:
- `role` (papel de usuário): ADMIN, ESTUDANTE ou EMPRESA  
- `expiresIn`: 7200 segundos (2 horas)

Se as credenciais estiverem incorretas:

**Status**: 401 Unauthorized

**Response Body**:
```json
dados inválidos
```

### 3. Acesso a Rotas Protegidas
Para acessar rotas protegidas, envie o JWT como um **Bearer Token** no header da requisição:

**Headers:**
```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

Sem o token ou com token inválido, o acesso será negado com:

**Status**: 401 Unauthorized

### 4. Proteção de Endpoints por Role
A autenticação também valida a `role` do usuário. Alguns endpoints estão restritos a perfis específicos:

```
Role     | Acesso permitido a rotas
------------------------------------
STUDENT  | Rotas de estudantes
COMPANY  | Rotas de empresas
ADMIN    | Todas as rotas
```

## 📄 Licença

Copyright 2025 **Conexão Estágios**

Este código é licenciado sob uma licença proprietária para o projeto **Conexão Estágios**. O uso, modificação, distribuição ou qualquer outro tipo de utilização do código sem a permissão explícita dos proprietários é estritamente proibido.

Para obter permissão para utilizar o código, entre em contato com **doug.candido@gmail.com**.

Nenhuma das permissões ou direitos acima confere à parte licenciada o direito de sublicenciar ou distribuir o código sem a permissão dos proprietários.
