# 🎨 Visão Geral da Interface

## 📱 Layout Principal

```
┌─────────────────────────────────────────────────────────────────┐
│                     💉 ImuniData                                 │
│          Sistema de Monitoramento de Vacinação                   │
│    Gestão completa de registros de vacinação por estado...      │
└─────────────────────────────────────────────────────────────────┘

┌─────────────────┬──────────────────┬─────────────────────┐
│  📊 Total      │   💉 Tipos      │     📍 Estados      │
│  Registros     │   Vacinas       │     Cobertos        │
│                │                 │                     │
│  1250          │   8             │   10                │
│                │                 │                     │
│ aplicações     │ vacinas         │ estados             │
│ registradas    │ diferentes      │ registrados         │
└─────────────────┴──────────────────┴─────────────────────┘

┌─────────────────────────────────────────────────────────┐
│  📋 Listagem  |  ➕ Nova Vacinação                      │
├─────────────────────────────────────────────────────────┤
│                                                         │
│ 🔍 Filtros Especializados                            │
│                                                         │
│  ┌────────────┐  ┌────────────┐  ┌──────────────────┐  │
│  │ Vacina ▼   │  │ Estado ▼   │  │ Município        │  │
│  │            │  │            │  │ [Digite aqui...]│  │
│  └────────────┘  └────────────┘  └──────────────────┘  │
│                                                         │
│                     ┌──────────┐  ┌──────────┐        │
│                     │ 🔎 Filtrar  │ ✕ Limpar │        │
│                     └──────────┘  └──────────┘        │
│                                                         │
│  Filtros ativos: 💉 COVID-19 ✕  📍 SP ✕            │
│                                                         │
│ ┌────────────────────────────────────────────────────┐ │
│ │ # │ Município      │ UF │ Vacina │ Dose  │Qtd │...│ │
│ ├────────────────────────────────────────────────────┤ │
│ │ 1 │ São Paulo      │ SP │COVID-19│1ª dose│1500│...│ │
│ │ 2 │ Rio de Janeiro │ RJ │ Gripe  │1ª dose│1200│...│ │
│ │ 3 │ Belo Horizonte │ MG │  BCG   │Única │800 │...│ │
│ │...│...             │...│...     │...   │... │...│   │
│ │   │✎ Editar 🗑 Deletar                            │ │
│ └────────────────────────────────────────────────────┘ │
│                                                         │
└─────────────────────────────────────────────────────────┘
```

---

## 📝 Tela de Novo Registro

```
┌────────────────────────────────────────────────────┐
│          ➕ Novo Registro de Vacinação              │
├────────────────────────────────────────────────────┤
│                                                    │
│  ✓ Registro criado com sucesso!                  │
│                                                    │
│  === Localização ===                              │
│                                                    │
│  Município * ┌──────────────────────────────────┐ │
│              │ Digite o município... [São Paulo]│ │
│              └──────────────────────────────────┘ │
│                                                    │
│  Estado * ┌──────────────────────────────────┐    │
│           │ -- Selecionar -- [SP]            │    │
│           └──────────────────────────────────┘    │
│                                                    │
│  ════ Informações da Vacinação ════               │
│                                                    │
│  Vacina * ┌─────┐  Dose * ┌──────────┐           │
│           │COVID-19 ▼│         │1ª dose ▼      │           │
│           └─────┘      └──────────┘           │
│                                                    │
│  Quantidade * ┌──────┐  Data * ┌──────────────┐  │
│               │ 1500 │         │ 2026-05-10   │  │
│               └──────┘         └──────────────┘  │
│                                                    │
│           ┌──────────────┐  ┌───────────────────┐│
│           │   Cancelar   │  │💾 Atualizar       ││
│           └──────────────┘  └───────────────────┘│
│                                                    │
└────────────────────────────────────────────────────┘
```

---

## 🎯 Componentes Visuais

### Card de Estatística
```
┌──────────────────────────┐
│ 📊 Total de Registros    │
│                          │
│       1250               │
│                          │
│ aplicações registradas   │
└──────────────────────────┘
```

### Filtro Ativo
```
┌─────────────────────────────────────────┐
│ 💉 COVID-19 ✕  📍 SP ✕  🏙️ São Paulo ✕│
└─────────────────────────────────────────┘
```

### Linha de Tabela
```
┌────┬──────────────┬────┬──────────┬────────┬─────┬─────────┬──────────────┐
│ 1  │ São Paulo    │ SP │ COVID-19 │1ª dose │1500 │05/05/26 │✎ ✎ Edit.. 🗑 │
└────┴──────────────┴────┴──────────┴────────┴─────┴─────────┴──────────────┘
```

### Botões
```
Primário (Roxo)          Secundário (Cinza)       Perigo (Vermelho)
┌──────────────────┐    ┌──────────────────┐    ┌──────────────────┐
│  🔎 Filtrar      │    │  ✕ Limpar        │    │  🗑 Deletar      │
└──────────────────┘    └──────────────────┘    └──────────────────┘

Sucesso (Verde)          Info (Azul)
┌──────────────────┐    ┌──────────────────┐
│  ✎ Editar        │    │  💾 Atualizar    │
└──────────────────┘    └──────────────────┘
```

---

## 🎨 Cores Utilizadas

```
Primária (Roxo):        #667eea  ████████
Secundária (Roxo Dark): #764ba2  ████████
Sucesso (Verde):        #28a745  ████████
Perigo (Vermelho):      #dc3545  ████████
Aviso (Amarelo):        #ffc107  ████████
Info (Azul):            #17a2b8  ████████
Cinza Claro:            #f8f9fa  ████████
Cinza Escuro:           #333333  ████████
```

---

## ⌚ Fluxos de Interação Visuais

### 1. Filtrar por Vacina

```
User clica em "Vacina ▼"
        ↓
Select abre com opções:
  -- Selecionar --
  BCG ✓
  Gripe
  COVID-19
  ...
        ↓
User seleciona "COVID-19"
        ↓
Select mostra "COVID-19"
        ↓
User clica "🔎 Filtrar"
        ↓
Loading aparece (spinner)
        ↓
Tabela atualiza mostrando apenas COVID-19
        ↓
Tag apareça: "💉 COVID-19 ✕"
        ↓
User pode remover com "✕"
```

### 2. Criar Novo Registro

```
User clica "➕ Nova Vacinação"
        ↓
Aba muda (conteúdo anterior desaparece)
        ↓
Formulário vazio aparece
        ↓
User preenche campos
  - Campo vazio alerta: "Campo obrigatório" (vermelho)
        ↓
User clica "➕ Criar Registro"
        ↓
Campos ficarão cinza (disabled)
        ↓
Spinner carrega
        ↓
Sucesso: "✓ Registro criado com sucesso!"
        ↓
Formulário limpo
        ↓
User pode clique "📋 Listagem" para ver novo registro
```

### 3. Editar Registro

```
User clica "✎ Editar" em um registro
        ↓
Aba muda para "➕"
        ↓
Formulário aparece com dados PRÉ-PREENCHIDOS
        ↓
Título muda para "✎ Editar Registro"
        ↓
Botão muda para "💾 Atualizar"
        ↓
User edita campo(s)
        ↓
User clica "💾 Atualizar"
        ↓
Sucesso: "✓ Registro atualizado com sucesso!"
        ↓
Volta para listagem
        ↓
Mudância reflete na tabela
```

### 4. Deletar Registro

```
User clica "🗑 Deletar"
        ↓
Popup aparece: "Tem certeza que deseja deletar este registro?"
        ↓
User clica OK (ou Cancelar)
        ↓
Se OK:
  Loading aparece
        ↓
  Sucesso: "✓ Registro deletado com sucesso!"
        ↓
  Linha desaparece da tabela
```

---

## 📊 Estados Visuais

### Loading
```
         ⟳
      Carregando dados...
```

### Erro
```
⚠️  Erro ao carregar dados. Verifique se o backend está em execução.
```

### Vazio
```
Nenhum registro de vacinação encontrado.
Clique em "Nova Vacinação" para adicionar um registro.
```

### Sucesso
```
✓ Registro criado com sucesso!
```

---

## 📐 Responsividade

### Desktop (1920px+)
```
┌─────────────────────────────────┐
│  Logo │ Nav │ Content           │
│       │     │                   │
│  Table com 8 colunas na mesma linha
│  Buttons lado a lado
└─────────────────────────────────┘
```

### Tablet (768px - 1024px)
```
┌──────────────────┐
│      Logo        │
├──────────────────┤
│    Nav Responsiva │
├──────────────────┤
│    Content       │
│                  │
│ Table scrollável
│ Buttons linha
└──────────────────┘
```

### Mobile (< 768px)
```
┌────────────┐
│ ☰ Logo     │
├────────────┤
│  Filtros   │
│  empilhados │
├────────────┤
│   Table    │
│ scrollável  │
│  horizontal │
├────────────┤
│  Buttons   │
│ full width │
└────────────┘
```

---

## 🎭 Estados de Componentes

### Campo de Texto

**Normal:**
```
Município
┌──────────────────────────────────┐
│ Digite o município...            │
└──────────────────────────────────┘
```

**Hover:**
```
┌──────────────────────────────────┐
│ Digite o município...            │ ← Background muda
└──────────────────────────────────┘
```

**Focus:**
```
┌──────────────────────────────────┐
│ Digite o município...   |         │ ← Cursor | aparece
└──────────────────────────────────┘ ← Border Roxo brilhante
```

**Erro:**
```
┌──────────────────────────────────┐
│ Digite o município...            │
└──────────────────────────────────┘ ← Border Vermelho
Campo obrigatório ← Mensagem vermelha
```

### Button

**Normal:**
```
┌──────────────┐
│ 🔎 Filtrar   │ ← Roxo
└──────────────┘
```

**Hover:**
```
┌──────────────┐
│ 🔎 Filtrar   │ ← Roxo mais escuro
└──────────────┘
```

**Active:**
```
┌──────────────┐
│ 🔎 Filtrar   │ ← Roxo escuro + sombra
└──────────────┘
```

**Disabled:**
```
┌──────────────┐
│ 🔎 Filtrar   │ ← Cinza (não clicável)
└──────────────┘
```

---

## 🎬 Animações

### Fade In (Componentes aparecem)
- Duração: 0.3s
- Exemplo: Tabela carrega

### Slide In (Abas mudam)
- Duração: 0.3s
- Exemplo: Nova aba selecionada

### Spinner Rotation (Loading)
- Duração: 1s
- Rotação: 360° continuamente

### Button Hover Transition
- Duração: 0.3s
- Mudança de cor smooth

---

## 📦 Tamanhos de Componentes

```
Header Height: 100px

Stats Grid:
- Desktop: 3 colunas
- Tablet: 2 colunas
- Mobile: 1 coluna
- Card Height: ~120px
- Card Padding: 20px

Tabs Height: 50px

Filters Container:
- Padding: 20px
- Gap between fields: 15px

Table:
- Row Height: 50px
- Header Height: 45px
- Cell Padding: 12px

Form:
- Max Width: 600px
- Input Height: 40px
- Input Padding: 10px 12px
- Gap between groups: 20px
```

---

## 🔤 Tipografia

```
Main Title (Header):
- Font: Segoe UI
- Size: 32px
- Weight: Bold
- Color: Branco

Subtitle (Header):
- Font: Segoe UI
- Size: 16px
- Weight: Normal
- Color: Branco (90% opacity)

Card Titles:
- Font: Segoe UI
- Size: 14px
- Weight: 500 (semibold)
- Color: #666

Card Values:
- Font: Segoe UI
- Size: 28px
- Weight: Bold
- Color: #333

Table Headers:
- Font: Segoe UI
- Size: 14px
- Weight: 600
- Color: #333
- Text Transform: Uppercase

Form Labels:
- Font: Segoe UI
- Size: 14px
- Weight: 500
- Color: #333

Inputs:
- Font: Segoe UI
- Size: 14px
- Weight: Normal
```

---

## 🖼️ Ícones Utilizados

```
💉 - Vacina (Título, Cards)
📊 - Estatísticas
📍 - Localização, Estado
📋 - Listagem
➕ - Novo
🔍 - Busca/Filtrar
🗑 - Deletar
✎ - Editar
✓ - Sucesso
✕ - Fechar/Remover
⟳ - Loading
📱 - Mobile
🏙️ - Município
🎨 - Design
```

---

## ✨ Efeitos Visuais

- **Sombras:** Cards e componentes têm sombra suave
- **Borders Roundings:** 4-8px
- **Transições:** 0.3s ease em todos os estados
- **Gradientes:** Header tem gradient roxo
- **Hover States:** Linha da tabela muda cor ao passar mouse
- **Scroll:** Tabela tem scroll horizontal em mobile

---

Esta é a visualização completa da interface do ImuniData! 🎨✨
