# ⚡ Guia de Início Rápido - Frontend ImuniData

## 🎯 Objetivo

Este guia permite que você tenha a aplicação fronten
d rodando em menos de 5 minutos.

---

## 📋 Pré-requisitos

- Node.js 14+ instalado
- npm ou yarn
- Uma linha de comando (Terminal/PowerShell/CMD)

### Verificar Instalação

```bash
node --version   # v14.0.0 ou superior
npm --version    # 6.0.0 ou superior
```

---

## 🚀 Passo 1: Navegar para o Diretório Frontend

```bash
cd /workspaces/ImuniData/front
```

---

## 📦 Passo 2: Instalar Dependências

```bash
npm install
```

**O que acontece:**
- Baixa todas as dependências do `package.json`
- Cria pasta `node_modules/`
- Pode levar 2-3 minutos

---

## ⚙️ Passo 3: Configurar Variáveis de Ambiente

**Opção A: Usar padrão (Recomendado para primeira vez)**

Apenas execute o próximo passo. O padrão é `http://localhost:8080/api`

**Opção B: Configurar customizado**

```bash
cp .env.example .env
# Editar .env com seu editor
# Exemplo com backend em outra porta:
# REACT_APP_API_URL=http://localhost:9000/api
```

---

## ▶️ Passo 4: Iniciar o Frontend

**Se você quer testar TUDO sem backend (RECOMENDADO):**

1. Abra `src/components/Dashboard.js`
2. Na linha 3, mude:
   ```javascript
   // De:
   import vacinacaoService from '../services/vacinacaoService';
   
   // Para:
   import vacinacaoService from '../services/mockVacinacaoService';
   ```
3. Execute:
   ```bash
   npm start
   ```

**Se você já tem o backend rodando em http://localhost:8080:**

```bash
npm start
```

---

## 🌐 Passo 5: Acessar a Aplicação

Seu navegador deve abrir automaticamente em:

```
http://localhost:3000
```

Se não abrir, acesse manualmente esse endereço.

---

## ✅ Verificar se Está Funcionando

Você deve ver:

1. **Header roxo** com o título "💉 ImuniData - Sistema de Monitoramento de Vacinação"
2. **3 Cards** com estatísticas:
   - 📊 Total de Registros
   - 💉 Tipos de Vacinas
   - 📍 Estados Cobertos
3. **2 Abas**: "📋 Listagem" e "➕ Nova Vacinação"
4. **Tabela** com registros de vacinação (se usando mock)

---

## 🎮 Testando as Funcionalidades

### Com Mock Service (Sem Backend)

#### 1. Listar Registros
- ✅ Você deve ver 8 registros na tabela

#### 2. Filtrar por Vacina
- Clique em "📋 Listagem"
- Em "Filtros Especializados", selecione "COVID-19"
- Clique em "🔎 Filtrar"
- ✅ Apenas COVID-19 deve aparecer

#### 3. Filtrar por Estado
- Selecione "SP" em Estado
- Clique em "🔎 Filtrar"
- ✅ Apenas SP deve aparecer

#### 4. Buscar por Município
- Digite "São Paulo" em Município
- Clique em "🔎 Filtrar"
- ✅ Apenas São Paulo deve aparecer

#### 5. Limpar Filtros
- Clique em "✕ Limpar"
- ✅ Todos os registros devem voltar

#### 6. Criar Novo Registro
- Clique em "➕ Nova Vacinação"
- Preencha os campos:
  - Município: "Campinas"
  - Estado: "SP"
  - Vacina: "Gripe"
  - Dose: "1ª dose"
  - Quantidade: "500"
  - Data: Deixar a data de hoje
- Clique em "➕ Criar Registro"
- ✅ Mensagem de sucesso aparece
- ✅ Novo registro aparece na tabela

#### 7. Editar Registro
- Clique em "✎ Editar" em qualquer registro
- Mude o município para "Santos"
- Clique em "💾 Atualizar"
- ✅ Registro atualizado
- ✅ Novo nome apareça na tabela

#### 8. Deletar Registro
- Clique em "🗑 Deletar"
- Confirme a exclusão
- ✅ Registro removido da tabela

#### 9. Validação
- Clique em "➕ Nova Vacinação"
- NÃO preencha nenhum campo
- Clique em "➕ Criar Registro"
- ✅ Erros em vermelho aparecem

---

## 🔧 Troubleshooting

### Problema: "Port 3000 is already in use"

**Solução:**
```bash
# Usar outra porta
PORT=3001 npm start
```

### Problema: "BLANK" página ou erro 404

1. Verifique se você está em `http://localhost:3000`
2. Abra o console do navegador (F12)
3. Procure por erros vermelhos
4. Se vir erro de CORS, o backend não está rodando

### Problema: "Failed to compile"

```bash
# Limpar caches e reinstalar
rm -rf node_modules package-lock.json
npm install
npm start
```

### Problema: Tabela vazia / Sem dados

Se estiver usando o serviço real (não mock):
1. Certifique-se que o backend está rodando em `http://localhost:8080`
2. Verifique se os endpoints retornam dados
3. Abra o DevTools (F12) e verifique a aba Network

---

## 📱 Testar em Mobile

### Visualização Responsiva do Navegador

```
F12 → Ctrl+Shift+M (ou Cmd+Shift+M no Mac)
```

A interface se adapta para:
- ✅ Desktop (1920px+)
- ✅ Tablet (768px - 1024px)
- ✅ Mobile (< 768px)

---

## 🔌 Integrar com Backend Real

Quando o backend estiver pronto:

1. **Verifique se o backend está rodando:**
   ```bash
   curl http://localhost:8080/api/registros-vacinacao
   ```

2. **Volte ao código real (se estava usando mock):**
   
   Em `src/components/Dashboard.js`:
   ```javascript
   // Mude de volta para (comentar a linha do mock):
   import vacinacaoService from '../services/vacinacaoService';
   ```

3. **Recarregue a página** (F5)

4. ✅ Agora usando dados reais do backend!

---

## 📝 Estrutura de Pastas

```
front/
├── node_modules/           ← Dependências (gerado por npm)
├── src/
│   ├── components/         ← Componentes React
│   ├── services/           ← Chamadas à API
│   ├── styles/             ← Estilos CSS
│   ├── App.js              ← Componente raiz
│   └── index.js            ← Entry point
├── public/
│   └── index.html          ← HTML base
├── package.json            ← Dependências e scripts
└── .env                    ← Variáveis de ambiente
```

---

## 🛑 Parar a Aplicação

```bash
# No terminal, pressione:
Ctrl + C
```

---

## 🔄 Reiniciar a Aplicação

```bash
npm start
```

---

## 📚 Documentação Completa

Para aprender mais sobre o projeto:

- [README.md](README.md) - Documentação completa
- [ARCHITECTURE.md](ARCHITECTURE.md) - Arquitetura e design
- [IMPLEMENTATION_SUMMARY.md](IMPLEMENTATION_SUMMARY.md) - Tudo que foi implementado
- [MOCK_SERVICE_GUIDE.md](MOCK_SERVICE_GUIDE.md) - Como usar o mock

---

## ✨ Próximos Passos

1. **Teste todas as funcionalidades** acima ✅
2. **Explore o código** dos componentes
3. **Modifique estilos** em `src/styles/`
4. **Integre com backend** do Israel
5. **Deploy** para produção

---

## 🎓 Personalizações Comuns

### Mudar Cores

Editar `src/styles/global.css`:
```css
.header {
  background: linear-gradient(135deg, #667eea, #764ba2);  /* ← Mude aqui */
}
```

### Adicionar Novo Filtro

1. Editar `src/components/Filters.js`
2. Adicionar novo select
3. Editar `Dashboard.js` para processar filtro

### Mudar Título da Página

Editar `public/index.html`:
```html
<title>Meu novo título</title>
```

---

## 💡 Dicas Úteis

1. **DevTools do React:**
   - Instale a extensão "React Developer Tools"
   - Inspecione componentes em tempo real

2. **Atalhos do Navegador:**
   - F5 - Recarregar página
   - F12 - DevTools
   - Ctrl+Shift+M - Modo responsivo

3. **Debugging:**
   - Use `console.log()` nos componentes
   - Abra a aba Console do DevTools
   - Use o mock para não depender do backend

---

## 🎉 Você Está Pronto!

Sua aplicação frontend está **100% pronta** para:

- ✅ Desenvolvimento local
- ✅ Testes com mock
- ✅ Integração com backend
- ✅ Deploy para produção

Divirta-se desenvolvendo! 🚀

---

## ❓ Dúvidas?

Consulte:
- Arquivos `.md` nesta pasta
- Comentários no código dos componentes
- Documentação oficial do React: https://react.dev
