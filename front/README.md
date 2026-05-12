# ImuniData Frontend - Configuração Local

## Variáveis de Ambiente

Copie este arquivo para `.env` e configure conforme necessário:

```
# URL da API Backend
REACT_APP_API_URL=http://localhost:8080/api

# Ambiente
REACT_APP_ENV=development
```

## Instalação e Execução

### 1. Instalar Dependências
```bash
npm install
```

### 2. Iniciar o Servidor de Desenvolvimento
```bash
npm start
```

A aplicação estará disponível em `http://localhost:3000`

### 3. Build para Produção
```bash
npm run build
```

## Funcionalidades

- ✅ **Dashboard**: Visualização de estatísticas gerais
- ✅ **Listagem**: Tabela com todos os registros de vacinação
- ✅ **Filtros**: Busca por tipo de vacina, estado e município
- ✅ **CRUD Completo**: Criar, ler, atualizar e deletar registros
- ✅ **Formulário**: Interface intuitiva para inserção de dados
- ✅ **Responsivo**: Design adaptável para diferentes resoluções

## Estrutura de Pastas

```
front/
├── src/
│   ├── components/
│   │   ├── Dashboard.js          # Componente principal
│   │   ├── VaccinationTable.js   # Tabela de registros
│   │   ├── Filters.js            # Componente de filtros
│   │   └── VaccinationForm.js    # Formulário de cadastro
│   ├── services/
│   │   └── vacinacaoService.js   # Serviço de API
│   ├── styles/
│   │   ├── global.css            # Estilos globais
│   │   ├── dashboard.css         # Estilos do dashboard
│   │   ├── filters.css           # Estilos dos filtros
│   │   ├── table.css             # Estilos da tabela
│   │   └── form.css              # Estilos do formulário
│   ├── App.js                    # Componente raiz
│   └── index.js                  # Entry point
├── public/
│   └── index.html                # HTML principal
├── package.json                  # Dependências
├── .env.example                  # Variáveis de ambiente exemplo
└── README.md                     # Este arquivo
```

## Principais Componentes

### Dashboard
- Exibe estatísticas gerais
- Gerencia o estado global da aplicação
- Controla a navegação entre abas

### VaccinationTable
- Apresenta os registros em formato de tabela
- Ações de edição e exclusão
- Formatação de datas

### Filters
- Filtros por tipo de vacina
- Filtros por estado
- Busca por município

### VaccinationForm
- Validação de dados
- Criar novos registros
- Editar registros existentes
- Feedback visual de sucesso/erro

## Integração com Backend

O frontend está pronto para integrar com a API REST do backend. As chamadas são feitas através do serviço `vacinacaoService.js`.

### Endpoints Esperados:

- `GET /api/registros-vacinacao` - Lista todos os registros
- `GET /api/registros-vacinacao/{id}` - Obtém um registro específico
- `GET /api/registros-vacinacao/vacina/{vacina}` - Busca por tipo de vacina
- `GET /api/registros-vacinacao/estado/{estado}` - Busca por estado
- `POST /api/registros-vacinacao` - Cria novo registro
- `PUT /api/registros-vacinacao/{id}` - Atualiza um registro
- `DELETE /api/registros-vacinacao/{id}` - Deleta um registro

## Tecnologias Utilizadas

- React 18.2.0
- Axios 1.6.0
- CSS Puro (Flexbox e Grid)
- ES6+

## Requisitos

- Node.js 14+
- npm ou yarn

## Notas Importantes

- Certifique-se de que o backend está rodando antes de iniciar o frontend
- Configure a URL da API no arquivo `.env`
- O CORS deve estar habilitado no backend para aceitar requisições do frontend
