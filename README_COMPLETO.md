# 🏥 ImuniData - Sistema de Monitoramento de Vacinação

Uma plataforma integrada frontend-backend para monitoramento e gerenciamento de registros de vacinação.

## 📋 Estrutura do Projeto

```
ImuniData/
├── api/                          # Backend Spring Boot
│   ├── pom.xml                   # Dependências Maven
│   ├── src/
│   │   ├── main/java/...         # Código principal
│   │   │   ├── controller/       # Endpoints REST
│   │   │   ├── service/          # Lógica de negócio
│   │   │   ├── repository/       # Acesso a dados
│   │   │   ├── model/            # Entidades JPA
│   │   │   └── infra/            # Configurações
│   │   └── resources/
│   │       └── application.properties
│   └── mvnw
│
├── front/                        # Frontend React
│   ├── package.json              # Dependências npm
│   ├── .env                      # Configurações
│   ├── public/
│   │   └── index.html
│   └── src/
│       ├── components/           # Componentes React
│       ├── services/             # Cliente API
│       └── styles/               # CSS
│
└── INTEGRATION_COMPLETE.md       # Documentação de integração
```

## 🚀 Arquitetura

### Backend (Java/Spring Boot)

- **Framework:** Spring Boot 2.7.18
- **Banco de Dados:** H2 (desenvolvimento) / PostgreSQL (produção)
- **ORM:** JPA/Hibernate
- **Porta:** 8080

#### Endpoints REST

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/api/registros-vacinacao` | Lista todos os registros |
| GET | `/api/registros-vacinacao/{id}` | Busca por ID |
| POST | `/api/registros-vacinacao` | Cria novo registro |
| PUT | `/api/registros-vacinacao/{id}` | Atualiza registro |
| DELETE | `/api/registros-vacinacao/{id}` | Deleta registro |
| GET | `/api/registros-vacinacao/vacina/{vacina}` | Filtra por vacina |
| GET | `/api/registros-vacinacao/estado/{estado}` | Filtra por estado |
| GET | `/api/registros-vacinacao/municipio/{municipio}` | Filtra por município |
| GET | `/api/registros-vacinacao/resumo/estado` | Resumo por estado |
| GET | `/api/registros-vacinacao/resumo/vacina` | Resumo por vacina |

### Frontend (React)

- **Framework:** React 18
- **HTTP Client:** Axios
- **Porta:** 3000

#### Componentes

- `Dashboard.js` - Painel principal
- `VaccinationForm.js` - Formulário para adicionar registros
- `VaccinationTable.js` - Tabela de registros
- `Filters.js` - Filtros de busca

## 🔧 Instalação

### Pré-requisitos

- Java 11+
- Node.js 14+
- npm ou yarn

### Backend

```bash
cd api
./mvnw clean install
```

### Frontend

```bash
cd front
npm install
```

## ▶️ Como Executar

### Iniciar o Backend

```bash
cd api
./mvnw spring-boot:run
```

A API estará disponível em: `http://localhost:8080/api`

### Iniciar o Frontend

Em outro terminal:

```bash
cd front
npm start
```

O frontend estará disponível em: `http://localhost:3000`

## 📝 Variáveis de Ambiente

### Frontend (.env)

```
REACT_APP_API_URL=http://localhost:8080/api
REACT_APP_ENV=development
```

### Backend (application.properties)

```properties
spring.application.name=api
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=update
api.vacinas.url=https://apidadosabertos.saude.gov.br/vacinacao/doses-aplicadas-pni-2026
```

## 🧪 Testando a API

### Listar todos os registros

```bash
curl http://localhost:8080/api/registros-vacinacao
```

### Criar novo registro

```bash
curl -X POST http://localhost:8080/api/registros-vacinacao \
  -H "Content-Type: application/json" \
  -d '{
    "municipio": "São Paulo",
    "estado": "SP",
    "estado_nome": "São Paulo",
    "vacina": "COVID-19",
    "vacina_sigla": "CV",
    "dose": "1ª",
    "sexo_paciente": "M",
    "idade_paciente": 25,
    "data_registro": "2026-05-12"
  }'
```

### Filtrar por estado

```bash
curl http://localhost:8080/api/registros-vacinacao/estado/SP
```

### Resumo por vacina

```bash
curl http://localhost:8080/api/registros-vacinacao/resumo/vacina
```

## 📚 Estrutura de Dados

### RegistroVacinacao

```json
{
  "id": 1,
  "municipio": "São Paulo",
  "estado": "SP",
  "estado_nome": "São Paulo",
  "vacina": "COVID-19",
  "vacina_sigla": "CV",
  "dose": "1ª",
  "sexo_paciente": "M",
  "idade_paciente": 25,
  "data_registro": "2026-05-12"
}
```

## 🔄 Fluxo de Desenvolvimento

1. **Desenvolvimento Local**
   - Backend roda em `localhost:8080`
   - Frontend roda em `localhost:3000`
   - Banco H2 em memória

2. **Testes**
   ```bash
   # Backend
   cd api && ./mvnw test
   
   # Frontend
   cd front && npm test
   ```

3. **Build para Produção**
   ```bash
   # Backend
   cd api && ./mvnw clean package -DskipTests
   
   # Frontend
   cd front && npm run build
   ```

## 📦 Deploy

### Docker (Exemplo)

```dockerfile
# Backend
FROM openjdk:11-jre-slim
COPY api/target/api-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080

# Frontend
FROM node:14
WORKDIR /app
COPY front .
RUN npm install && npm run build
EXPOSE 3000
```

## ✅ Checklist de Integração

- ✅ API compila e roda em Java 11
- ✅ Endpoints REST mapeados corretamente
- ✅ Frontend conecta à API
- ✅ CORS configurado
- ✅ Banco de dados funcional
- ✅ Commits realizados
- ✅ Documentação completa

## 🐛 Troubleshooting

### Erro: "Cannot find symbol" na compilação

```bash
cd api
rm -rf target
./mvnw clean compile
```

### Erro: CORS relacionado

Verifique se `.env` tem a URL correta da API:
```
REACT_APP_API_URL=http://localhost:8080/api
```

### H2 Console não acessível

Acesse em: `http://localhost:8080/h2-console`

## 📞 Suporte

Para dúvidas sobre a integração, verifique [INTEGRATION_COMPLETE.md](INTEGRATION_COMPLETE.md)

## 📄 Licença

Projeto desenvolvido para FATEC.

---

**Última atualização:** 12 de maio de 2026
**Status:** ✅ Produção-Ready
