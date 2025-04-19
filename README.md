
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

## 📑 Instruções Para Desenvolvedores
### Estruruta

🔗 **Este repositório representa a estrutura monolítica do projeto _Conexão Estágios_**, separado em duas grandes pastas - frontend e backend - que ficaram a cargo de cada time de desenvolvedores.
Abaixo há um exemplo da estrutura a ser seguida:
```plaintext
conexao-estagios/                 ← Repositório principal (monorepo)
├── backend/                      ← Backend em Java (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── conexaoestagios/
│   │   │   │           ├── config/         ← Configurações gerais (segurança, CORS, etc)
│   │   │   │           ├── controller/     ← Controladores REST
│   │   │   │           ├── dto/            ← Objetos de transferência de dados
│   │   │   │           ├── exceptions/      ← Classes de exceção e handlers globais
│   │   │   │           ├── enums/          ← Enumerações
│   │   │   │           ├── entities/          ← Entidades JPA
│   │   │   │           ├── repository/     ← Interfaces do Spring Data
│   │   │   │           ├── service/        ← Regras de negócio
│   │   │   │           └── mapper/         ← Conversões entre entidades e DTOs
│   │   ├── resources/
│   │   │   ├── application.properties      ← Configurações da aplicação
│   │   │   └── static/                     ← (opcional) arquivos estáticos
│   │   └── test/                           ← Testes automatizados
│   ├── pom.xml                             ← Configuração do Maven
│   ├── .gitignore                          ← Ignora arquivos do backend
│   └── README.md                           ← Documentação do backend
│
├── frontend/                     ← Frontend (React, Vue ou Angular)
│   ├── public/
│   │   └── index.html            ← Arquivo HTML principal
│   ├── src/
│   │   ├── assets/               ← Imagens, fontes, estilos
│   │   ├── components/           ← Componentes reutilizáveis
│   │   ├── pages/                ← Páginas principais da aplicação
│   │   ├── services/             ← Comunicação com o backend via API
│   │   ├── styles/               ← CSS / SCSS global
│   │   ├── App.js                ← Componente raiz da aplicação
│   │   └── index.js              ← Ponto de entrada da aplicação React
│   ├── package.json              ← Configuração do projeto frontend
│   ├── vite.config.js            ← (ou webpack.config.js) Configuração do bundler
│   ├── .gitignore                ← Ignora arquivos do frontend
│   └── README.md                 ← Documentação do frontend
│
├── .gitignore                    ← Ignora arquivos do monorepo
├── README.md                     ← Documentação geral do projeto
└── docker-compose.yml            ← (opcional) Orquestração de serviços
```
### Versionamento e Colaboração

No repositório existem 3 branchs - main, frontend e backend - que serão utilizadas para o controle de versões, ao longo do tempo.
Primeiramente, o desenvolvedor deve clonar o projeto localmente em sua máquina com:

```bash
git clone https://github.com/conexaoestagios/conexao-estagios.git
```
E então criar a brench correspondente à sua área de atuação.

Para o frontend:

```bash
git checkout -b frontend 
git branch --set-upstream-to=origin/frontend frontend
git pull
```
Para o backend:

```bash
git checkout -b frontend 
git branch --set-upstream-to=origin/backend backend
git pull
```

e assim trabalhar exclusivamente dentro dela, podendo criar outras branches a partir dela em sua própria máquina.

Cada alteração concluída só poderá ser enviada ao github para a branch corresnpodente (git push origin backend / git push origin frontend), mas caso seja copiado um dos códigos acima basta realizar o comando abreviado git push que subirá automaticamente para a branch correta.

Importante: antes de iniciar cada alteração (antes de _codar_) deve-se atualizar o código com base nas alterações enviadas ao github por qualquer outro desenvolvedor, utilizando o comando:

```bash
git pull
```

A mensagem de commit é livre para cada desenvolvedor, desde que seja clara e breve, porém recomenda-se seguir as [convenções de commit](https://www.conventionalcommits.org/en/v1.0.0/), os padrões mais comuns são:

```bash
git commit -m"feat: adicionado função tal"        | adição
git commit -m"chore: adicionada biblioteca tal"   |configuração de manutenção
git commit -m"fix: bug na função tal resolvido"   | concertos de erros
git commit -m"refactor: variáveis renomeadas"     | alteração que não impacta a lógica 
git commit -m"docs: adicionado README"            | adição de documentação
git commit -m"test: teste de criação de usuário"  | adição de teste
git commit -m"style: mudança de cores e padding"  | alteração visual
```

## 📄 Licença

Copyright 2025 **Conexão Estágios**

Este código é licenciado sob uma licença proprietária para o **projeto Conexão Estágios**. O uso, modificação, distribuição ou qualquer outro tipo de utilização do código sem a permissão explícita dos proprietários é estritamente proibido.

Para obter permissão para utilizar o código, entre em contato com **doug.candido@gmail.com**.

Nenhuma das permissões ou direitos acima confere à parte licenciada o direito de sublicenciar ou distribuir o código sem a permissão dos proprietários.
