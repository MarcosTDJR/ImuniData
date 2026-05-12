# Como Usar o Mock Service

Durante o desenvolvimento, você pode usar o `mockVacinacaoService` para testar a interface sem depender do backend.

## Passo 1: Importar o Mock Service

No arquivo `src/components/Dashboard.js`, altere este trecho:

```javascript
// De:
import vacinacaoService from '../services/vacinacaoService';

// Para:
import vacinacaoService from '../services/mockVacinacaoService';
```

## Passo 2: Executar a Aplicação

```bash
npm start
```

## Funcionalidades Testáveis com o Mock

- ✅ Listar todos os registros
- ✅ Filtrar por tipo de vacina
- ✅ Filtrar por estado
- ✅ Buscar por município
- ✅ Criar novo registro
- ✅ Editar registro existente
- ✅ Deletar registro
- ✅ Ver estatísticas
- ✅ Validação de formulário

## Dados de Teste Incluídos

O mock inclui 8 registros de vacinação:

1. São Paulo - COVID-19
2. Rio de Janeiro - Gripe
3. Belo Horizonte - BCG
4. Brasília - Tétano
5. Salvador - Hepatite B
6. Recife - Poliomielite
7. Porto Alegre - Meningite
8. Fortaleza - Hepatite A

## Voltando ao Serviço Real

Quando o backend estiver pronto, mude novamente para:

```javascript
import vacinacaoService from '../services/vacinacaoService';
```

E certifique-se de:

1. Que o backend está rodando em `http://localhost:8080`
2. Que o CORS está habilitado no backend
3. Configurar a variável `REACT_APP_API_URL` se necessário

## Desenvolvendo com o Mock

O mock mantém os dados em memória, então:
- Dados criados durante a sessão serão perdidos ao recarregar
- Não há chamadas reais de HTTP
- É ótimo para testes de UI e fluxos
