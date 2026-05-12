# 🔗 Guia de Integração Frontend-Backend

## 📋 Status da Integração

| Componente | Frontend | Backend | Status |
|-----------|----------|---------|--------|
| Dashboard | ✅ Pronto | ⏳ Pendente | Aguardando |
| VaccinationTable | ✅ Pronto | ⏳ Pendente | Aguardando |
| Filters | ✅ Pronto | ⏳ Pendente | Aguardando |
| VaccinationForm | ✅ Pronto | ⏳ Pendente | Aguardando |
| Endpoints | ✅ Definidos | ⏳ Desenvolvimento | Em desenvolvimento |

---

## 🎯 Checklist do Backend (Israel)

Para o frontend funcionar completamente, o backend precisa de:

### 1. **Modelo (Model/Entity)**

```java
@Entity
public class RegistroVacinacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    private String municipio;
    
    @NotBlank
    private String estado;
    
    @NotBlank
    private String vacina;
    
    @NotBlank
    private String dose;
    
    @NotNull
    private Integer quantidadeAplicada;
    
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataRegistro;
    
    // getters/setters
}
```

### 2. **Repository**

```java
public interface RegistroVacinacaoRepository extends JpaRepository<RegistroVacinacao, Long> {
    List<RegistroVacinacao> findByVacina(String vacina);
    List<RegistroVacinacao> findByEstado(String estado);
    List<RegistroVacinacao> findByMunicipio(String municipio);
}
```

### 3. **Service**

```java
@Service
public class RegistroVacinacaoService {
    @Autowired
    private RegistroVacinacaoRepository repository;
    
    public List<RegistroVacinacao> listar() { ... }
    public RegistroVacinacao criar(RegistroVacinacao) { ... }
    public RegistroVacinacao atualizar(Long id, RegistroVacinacao) { ... }
    public void deletar(Long id) { ... }
    // mais métodos...
}
```

### 4. **Controller**

```java
@RestController
@RequestMapping("/api/registros-vacinacao")
@CrossOrigin(origins = "http://localhost:3000")
public class RegistroVacinacaoController {
    
    @GetMapping
    public ResponseEntity<List<RegistroVacinacao>> listar() { ... }
    
    @GetMapping("/{id}")
    public ResponseEntity<RegistroVacinacao> obterPorId(@PathVariable Long id) { ... }
    
    @PostMapping
    public ResponseEntity<RegistroVacinacao> criar(@RequestBody RegistroVacinacao) { ... }
    
    @PutMapping("/{id}")
    public ResponseEntity<RegistroVacinacao> atualizar(@PathVariable Long id, 
                                                       @RequestBody RegistroVacinacao) { ... }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) { ... }
    
    // Endpoints especializados
    @GetMapping("/vacina/{vacina}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorVacina(@PathVariable String vacina) { ... }
    
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorEstado(@PathVariable String estado) { ... }
    
    @GetMapping("/municipio/{municipio}")
    public ResponseEntity<List<RegistroVacinacao>> buscarPorMunicipio(@PathVariable String municipio) { ... }
}
```

### 5. **CORS Configuration**

```java
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins("http://localhost:3000", "http://localhost:3001")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600);
    }
}
```

---

## 🔌 Endpoints Esperados

### Base URL
```
http://localhost:8080/api
```

### 1. **Listar Todos (GET)**
```
GET /registros-vacinacao
Response: 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "municipio": "São Paulo",
    "estado": "SP",
    "vacina": "COVID-19",
    "dose": "1ª dose",
    "quantidadeAplicada": 1500,
    "dataRegistro": "2026-05-01"
  },
  ...
]
```

### 2. **Obter por ID (GET)**
```
GET /registros-vacinacao/{id}
Response: 200 OK

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

### 3. **Buscar por Vacina (GET)**
```
GET /registros-vacinacao/vacina/{vacina}
Exemplo: GET /registros-vacinacao/vacina/COVID-19

Response: 200 OK

[
  { ... registros com COVID-19 ... }
]
```

### 4. **Buscar por Estado (GET)**
```
GET /registros-vacinacao/estado/{estado}
Exemplo: GET /registros-vacinacao/estado/SP

Response: 200 OK

[
  { ... registros de SP ... }
]
```

### 5. **Buscar por Município (GET)**
```
GET /registros-vacinacao/municipio/{municipio}
Exemplo: GET /registros-vacinacao/municipio/São Paulo

Response: 200 OK

[
  { ... registros de São Paulo ... }
]
```

### 6. **Criar Novo (POST)**
```
POST /registros-vacinacao
Content-Type: application/json

{
  "municipio": "Campinas",
  "estado": "SP",
  "vacina": "Gripe",
  "dose": "1ª dose",
  "quantidadeAplicada": 500,
  "dataRegistro": "2026-05-10"
}

Response: 201 CREATED

{
  "id": 9,
  "municipio": "Campinas",
  "estado": "SP",
  "vacina": "Gripe",
  "dose": "1ª dose",
  "quantidadeAplicada": 500,
  "dataRegistro": "2026-05-10"
}
```

### 7. **Atualizar (PUT)**
```
PUT /registros-vacinacao/{id}
Content-Type: application/json

{
  "municipio": "Santos",
  "estado": "SP",
  "vacina": "Gripe",
  "dose": "2ª dose",
  "quantidadeAplicada": 600,
  "dataRegistro": "2026-05-10"
}

Response: 200 OK

{
  "id": 9,
  "municipio": "Santos",
  "estado": "SP",
  "vacina": "Gripe",
  "dose": "2ª dose",
  "quantidadeAplicada": 600,
  "dataRegistro": "2026-05-10"
}
```

### 8. **Deletar (DELETE)**
```
DELETE /registros-vacinacao/{id}
Response: 204 NO CONTENT
```

---

## ✅ Passo-a-Passo de Integração

### Passo 1: Verificar Backend Rodando

```bash
# Terminal 1: Backend
cd /workspaces/ImuniData/api
./mvnw spring-boot:run

# Verificar se está rodando
curl http://localhost:8080/api/registros-vacinacao
# Se responder JSON, está ok!
```

### Passo 2: Voltar ao Serviço Real

Em `front/src/components/Dashboard.js`:

```javascript
// Mudar de:
import vacinacaoService from '../services/mockVacinacaoService';

// Para:
import vacinacaoService from '../services/vacinacaoService';
```

### Passo 3: Reiniciar Frontend

```bash
# Terminal 2: Frontend
cd /workspaces/ImuniData/front
npm start
```

A aplicação agora usará dados REAIS do backend!

### Passo 4: Testar Cada Feature

- [ ] Listar registros
- [ ] Criar novo registro
- [ ] Editar registro
- [ ] Deletar registro
- [ ] Filtrar por vacina
- [ ] Filtrar por estado

---

## 🔍 Debugging de Integração

### 1. **Verificar Connection (GET /registros-vacinacao)**

No terminal:
```bash
curl http://localhost:8080/api/registros-vacinacao
```

Esperado: Array JSON com registros

Problemas:
- `Connection refused` → Backend não está rodando
- `404 Not Found` → Endpoint não existe
- `CORS error` → CORS não configurado no backend

### 2. **Ver Requisições HTTP (DevTools)**

1. Abra a aplicação (F12)
2. Vá para aba "Network"
3. Faça uma ação (clique em filtrar)
4. Observe a requisição:
   - URL: `http://localhost:8080/api/...`
   - Method: GET/POST/PUT/DELETE
   - Status: 200/201/404/etc
   - Response: JSON esperado?

### 3. **Ver Erros no Console**

Abra F12 → Console:

```
CORS error? → Backend precisa de configuração
404 Not Found? → Endpoint não existe
TypeError? → Response format diferente
Network error? → Backend offline
```

### 4. **Mock vs Real Service**

Se estiver com dúvida qual está ativo:

```javascript
// Em Dashboard.js, adicione isto no useEffect:
console.log('Serviço ativo:', vacinacaoService);
```

Se mostra muitos métodos com delay (setTimeout), é Mock.
Se mostra axios baseURL, é Real.

---

## 📡 Dados de Exemplo

### Para Teste Inicial

O backend pode popular com estes dados:

```json
[
  {
    "municipio": "São Paulo",
    "estado": "SP",
    "vacina": "COVID-19",
    "dose": "1ª dose",
    "quantidadeAplicada": 1500,
    "dataRegistro": "2026-05-01"
  },
  {
    "municipio": "Rio de Janeiro",
    "estado": "RJ",
    "vacina": "Gripe",
    "dose": "1ª dose",
    "quantidadeAplicada": 1200,
    "dataRegistro": "2026-05-02"
  },
  {
    "municipio": "Belo Horizonte",
    "estado": "MG",
    "vacina": "BCG",
    "dose": "Dose única",
    "quantidadeAplicada": 800,
    "dataRegistro": "2026-05-03"
  }
]
```

---

## 🚨 Erros Comuns

### CORS Error
```
Access to XMLHttpRequest at 'http://localhost:8080/...' from origin 
'http://localhost:3000' has been blocked by CORS policy...
```

**Solução:** Adicionar @CrossOrigin no Controller

### 404 Not Found
```
GET http://localhost:8080/api/registros-vacinacao 404
```

**Solução:** Verificar se endpoint existe no Backend

### Port Already in Use
```
Port 8080 is already in use
```

**Solução:** Matar processo ou usar outra porta

### Empty Response
```
GET request sucede mas retorna array vazio
```

**Solução:** Backend não tem dados. Adicionar dados de teste.

---

## 🧪 Teste de Integração Completo

### Cenário 1: Listar Registros

```
1. Frontend carrega
2. Dashboard.useEffect chama vacinacaoService.listar()
3. Axios faz GET /api/registros-vacinacao
4. Backend retorna Json array
5. Estado 'registros' é atualizado
6. Tabela renderiza dados
✅ Sucesso
```

### Cenário 2: Criar Registro

```
1. Usuário clica "➕ Nova Vacinação"
2. Preenche formulário
3. Clica "➕ Criar Registro"
4. VaccinationForm valida
5. Dashboard.handleFormSubmit() chama vacinacaoService.criar()
6. Axios faz POST /api/registros-vacinacao com body
7. Backend cria e retorna novo registro (com ID)
8. Estado 'registros' é atualizado
9. Tabela renderiza novo registro
✅ Sucesso
```

### Cenário 3: Editar Registro

```
1. Usuário clica "✎ Editar"
2. VaccinationForm preenche com dados
3. Edita campo
4. Clica "💾 Atualizar"
5. Dashboard.handleFormSubmit() chama vacinacaoService.atualizar(id)
6. Axios faz PUT /api/registros-vacinacao/{id} com body
7. Backend atualiza e retorna
8. Estado 'registros' é atualizado
9. Tabela renderiza dado alterado
✅ Sucesso
```

### Cenário 4: Deletar Registro

```
1. Usuário clica "🗑 Deletar"
2. window.confirm() pede confirmação
3. Se confirmado: Dashboard.handleDelete() chama vacinacaoService.deletar(id)
4. Axios faz DELETE /api/registros-vacinacao/{id}
5. Backend deleta registro
6. Estado 'registros' remove item
7. Tabela re-renderiza
✅ Sucesso
```

---

## 📝 Checklist Final

Antes de considerar integração completa:

- [ ] Backend retorna dados em JSON correto
- [ ] Todos endpoints retornam 200/201 status correto
- [ ] CORS configurado para http://localhost:3000
- [ ] Frontend pode listar registros
- [ ] Frontend pode criar registro
- [ ] Frontend pode editar registro
- [ ] Frontend pode deletar registro
- [ ] Frontend pode filtrar por vacina
- [ ] Frontend pode filtrar por estado
- [ ] Mensagens de sucesso/erro aparecem
- [ ] Sem erros no console DevTools

---

## 🎉 Próximos Passos

1. ✅ **Frontend pronto** (feito)
2. 🔄 **Backend em desenvolvimento** (Israel)
3. 🔗 **Integração** (próximo passo)
4. 🧪 **Testes** (após integração)
5. 🚀 **Deploy** (final)

---

## 📞 Suporte de Integração

Se encontrar problemas:

1. Verifique o arquivo `ARCHITECTURE.md` para entender os fluxos
2. Use DevTools (F12) para verificar requisições
3. Consulte o `vacinacaoService.js` para entender as calls
4. Ative logging no backend com @Slf4j
5. Teste isoladamente cada endpoint com Postman/curl

---

Pronto para integrar! 🚀
