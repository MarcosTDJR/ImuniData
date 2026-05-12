# 🏗️ Arquitetura Frontend - ImuniData

## Diagrama de Arquitetura

```
┌─────────────────────────────────────────────────────────────┐
│                     React Application                        │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  ┌──────────────────────────────────────────────────────┐   │
│  │           App.js (Componente Raiz)                  │   │
│  └────────────────────┬─────────────────────────────────┘   │
│                       │                                       │
│                       ▼                                       │
│  ┌──────────────────────────────────────────────────────┐   │
│  │     Dashboard.js (Componente Principal)             │   │
│  │  - State Management (registros, filtros, UI)        │   │
│  │  - Orquestração de componentes                      │   │
│  └──┬───────────────────────────────────┬──────────────┘   │
│     │                                   │                    │
│     ▼                                   ▼                    │
│  ┌─────────────┐  ┌─────────────────┐ ┌──────────────┐    │
│  │  Filters    │  │ VaccinationTable│ │ VaccinationForm   │
│  │             │  │                 │ │              │    │
│  │ - Selects  │  │ - Rows         │ │ - Validation │    │
│  │ - Inputs   │  │ - Actions      │ │ - Sections   │    │
│  │ - Tags     │  │ - Loading      │ │ - Buttons    │    │
│  └────────────┘  └─────────────────┘ └──────────────┘    │
│                                                               │
└──────────────────────────┬──────────────────────────────────┘
                           │
                           ▼
                  ┌────────────────────┐
                  │  vacinacaoService  │
                  │  (API Calls)       │
                  │                    │
                  │ - listar()         │
                  │ - filtrar()        │
                  │ - criar()          │
                  │ - atualizar()      │
                  │ - deletar()        │
                  └────────────────────┘
                           │
                           ▼
                  ┌────────────────────┐
                  │   Backend API      │
                  │  http://localhost  │
                  │     :8080/api      │
                  └────────────────────┘
```

---

## Estrutura de Componentes

### Hierarquia de Componentes

```
App
└── Dashboard
    ├── Filters
    ├── VaccinationTable
    └── VaccinationForm
```

### Fluxo de Dados (Data Flow)

```
┌─────────────────────────────────────────────────────────────┐
│                        Dashboard State                       │
├─────────────────────────────────────────────────────────────┤
│                                                               │
│  • registros: Registro[]          # Dados completos         │
│  • filteredRegistros: Registro[]  # Dados filtrados        │
│  • loading: boolean               # Estado de carregamento  │
│  • activeTab: 'listing' | 'form'  # Aba ativa              │
│  • selectedRegistro: Registro     # Registro em edição     │
│  • currentFilters: Filter         # Filtros aplicados      │
│  • stats: Stats                   # Estatísticas            │
│  • message: Message               # Mensagens toast        │
│                                                               │
└─────────────────────────────────────────────────────────────┘
         │                           │                      │
         ▼                           ▼                      ▼
      Filters              VaccinationTable         VaccinationForm
   (props & callbacks)   (props & callbacks)    (props & callbacks)
```

### Props Passados entre Componentes

**Dashboard → Filters:**
```javascript
{
  onFilter: (filters) => void,     // Callback para filtrar
  onReset: () => void              // Callback para limpar
}
```

**Dashboard → VaccinationTable:**
```javascript
{
  data: Registro[],                  // Dados a exibir
  loading: boolean,                  // Indicador de carregamento
  onEdit: (registro) => void,        // Callback para editar
  onDelete: (id) => void             // Callback para deletar
}
```

**Dashboard → VaccinationForm:**
```javascript
{
  onSubmit: (formData) => Promise<void>,  // Callback para salvar
  onCancel: () => void,                   // Callback para cancelar
  initialData?: Registro                  // Para modo edição
}
```

---

## Modelo de Dados

### Tipo Registro

```typescript
interface Registro {
  id: number,
  municipio: string,
  estado: string,
  vacina: string,
  dose: string,
  quantidadeAplicada: number,
  dataRegistro: string  // YYYY-MM-DD
}
```

### Tipo Filter

```typescript
interface Filter {
  vacina?: string,
  estado?: string,
  municipio?: string
}
```

### Tipo Stats

```typescript
interface Stats {
  totalRegistros: number,
  totalVacinas: number,
  totalEstados: number
}
```

### Tipo Message

```typescript
interface Message {
  texto: string,
  tipo: 'success' | 'error' | 'info'
}
```

---

## Fluxos de Interação

### 1. Listar Registros (GET)

```
Usuário Acessa App
        ↓
useEffect em Dashboard (componentDidMount)
        ↓
vacinacaoService.listar()
        ↓
Aguarda resposta (com loading spinner)
        ↓
Define estado 'registros' e 'filteredRegistros'
        ↓
VaccinationTable renderiza
```

### 2. Filtrar Registros

```
Usuário preence filtros em Filters
        ↓
Clica em "Filtrar"
        ↓
onFilter() é chamado → Dashboard.handleFilter()
        ↓
Service chamado (buscarPorVacina, buscarPorEstado, etc)
        ↓
setFilteredRegistros() atualiza estado
        ↓
VaccinationTable re-renderiza com dados filtrados
```

### 3. Criar Novo Registro

```
Usuário clica em "Nova Vacinação"
        ↓
activeTab muda para 'form'
        ↓
VaccinationForm renderiza (vazio)
        ↓
Usuário preenche formulário
        ↓
Clica em "Criar Registro"
        ↓
Validação local em handleSubmit()
        ↓
Se válido: vacinacaoService.criar() é chamado
        ↓
Se sucesso: novo registro é adicionado ao estado
        ↓
Mensagem de sucesso é exibida
        ↓
Formulário limpo, volta para listagem
```

### 4. Editar Registro

```
Usuário clica em "Editar" na tabela
        ↓
onEdit(registro) é chamado → setSelectedRegistro()
        ↓
activeTab muda para 'form'
        ↓
VaccinationForm renderiza COM dados iniciais
        ↓
useEffect detecta initialData e preenche fieldset()
        ↓
Usuário edita campos
        ↓
Clica em "Atualizar"
        ↓
Validação local
        ↓
Se válido: vacinacaoService.atualizar(id) é chamado
        ↓
Se sucesso: registro é atualizado no estado
        ↓
Mensagem de sucesso
        ↓
Volta para listagem
```

### 5. Deletar Registro

```
Usuário clica em "Deletar"
        ↓
window.confirm() pede confirmação
        ↓
Se confirmado: vacinacaoService.deletar(id)
        ↓
Se sucesso: registro é removido do estado
        ↓
VaccinationTable re-renderiza
        ↓
Mensagem de sucesso
```

---

## Styling Architecture

### Estrutura de CSS

```
styles/
├── global.css        # CSS Reset, variáveis, layouts base
├── dashboard.css     # Grid, tabs, cards de stats
├── filters.css       # Componente de filtros
├── table.css         # Tabela, botões, responsividade
└── form.css          # Formulário, inputs, validação
```

### Sistema de Cores (CSS)

```css
/* Primária */
--primary: #667eea;
--primary-dark: #5568d3;

/* Secundária */
--secondary: #764ba2;

/* States */
--success: #28a745;
--error: #dc3545;
--warning: #ffc107;
--info: #17a2b8;

/* Neutral */
--light: #f8f9fa;
--gray: #ddd;
--dark: #333;
```

### Responsividade

```css
/* Desktop */
.filters-grid { grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); }

/* Tablets */
@media (max-width: 768px) {
  .filters-grid { grid-template-columns: 1fr; }
  .form-row { grid-template-columns: 1fr; }
}

/* Mobile */
@media (max-width: 480px) {
  table { font-size: 12px; }
  .action-buttons { flex-direction: column; }
}
```

---

## Tratamento de Erros

### Try-Catch Pattern

```javascript
try {
  const dados = await vacinacaoService.listar();
  setRegistros(dados);
} catch (error) {
  console.error('Erro ao carregar:', error);
  setMessage({ texto: 'Erro ao carregar dados', tipo: 'error' });
}
```

### Validação de Formulário

```javascript
const validateForm = () => {
  const newErrors = {};
  
  if (!formData.municipio.trim()) {
    newErrors.municipio = 'Campo obrigatório';
  }
  
  setErrors(newErrors);
  return Object.keys(newErrors).length === 0;
};
```

### Mensagens ao Usuário

```javascript
mostrarMensagem(texto, tipo) {
  setMessage({ texto, tipo });  // 'success' | 'error' | 'info'
  setTimeout(() => setMessage(null), 3000);  // Auto-desaparece
}
```

---

## Otimizações Implementadas

1. **Renderização Condicional**
   - Componentes renderizam apenas quando necessário
   - Loading spinners durante requisições

2. **Event Handling**
   - Confirmação antes de deletar
   - Validação antes de enviar

3. **User Feedback**
   - Toast messages em tempo real
   - Indicadores de loading
   - Inline error messages

4. **Performance**
   - useEffect com dependencies corretas
   - Filtros locais rápidos
   - API calls apenas quando necessário

---

## Extensibilidade

### Adicionar Novo Filtro

```javascript
// 1. Adicionar select em Filters.js
<select name="novoFiltro" value={filters.novoFiltro} onChange={handleInputChange}>

// 2. Adicionar lógica em Dashboard.handleFilter()
if (filters.novoFiltro) {
  dados = await vacinacaoService.buscarPorNovoFiltro(filters.novoFiltro);
}
```

### Adicionar Novo Campo no Formulário

```javascript
// 1. Adicionar ao state inicial
const [formData, setFormData] = useState({
  ...
  novoCampo: '',
});

// 2. Adicionar validação
if (!formData.novoCampo) {
  newErrors.novoCampo = 'Campo obrigatório';
}

// 3. Adicionar input
<input name="novoCampo" value={formData.novoCampo} onChange={handleInputChange} />
```

---

## Integração com Backend

### URL Base

```javascript
const API_BASE_URL = process.env.REACT_APP_API_URL || 'http://localhost:8080/api';
```

### Configuração CORS (Backend necessário)

```java
// Spring Boot
@Configuration
public class CorsConfig {
  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
          .allowedOrigins("http://localhost:3000")
          .allowedMethods("GET", "POST", "PUT", "DELETE")
          .allowCredentials(true);
      }
    };
  }
}
```

### Estrutura Esperada de Resposta

```json
{
  "id": 1,
  "municipio": "São Paulo",
  "estado": "SP",
  "vacina": "COVID-19",
  "dose": "1ª dose",
  "quantidadeAplicada": 1500,
  "dataRegistro": "2026-05-01"
}
```

---

## Debugging

### Variáveis de Debug no Frontend

```javascript
// Em vacinacaoService.js
console.log('API Request:', url);
console.log('Response:', response.data);
console.error('Request Error:', error);

// Em Dashboard.js (Descomente para debug)
console.log('Estado atual:', {
  registros,
  filteredRegistros,
  currentFilters,
  stats
});
```

### React DevTools

Instale extensão do navegador para inspecionar componentes e props em tempo real.

---

## Testing

### Testavel com Mock Service

```bash
# Editar Dashboard.js
import vacinacaoService from '../services/mockVacinacaoService';

# Todos os fluxos funcionam sem backend
npm start
```

### Testes Manual

- [ ] Listar todos os registros
- [ ] Filtrar por vacina
- [ ] Filtrar por estado
- [ ] Filtrar por município
- [ ] Criar novo registro
- [ ] Editar registro existente
- [ ] Deletar registro
- [ ] Validação de formulário
- [ ] Responsividade em mobile

---

## Deployment

### Build para Produção

```bash
npm run build
```

Cria pasta `build/` com arquivos otimizados.

### Deploy em Servidor

```bash
# Copiar build para servidor
scp -r build/* usuario@servidor:/var/www/html

# Ou usar Docker
docker build -t imunidata-frontend .
docker run -d -p 80:3000 imunidata-frontend
```

---

## Documentação Adicional

- [README.md](README.md) - Instruções de uso
- [MOCK_SERVICE_GUIDE.md](MOCK_SERVICE_GUIDE.md) - Teste com mock
- [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - Resumo de features
