# Sistema de Monitoramento de Vacinação (ImuniData)

## Entrega -> 15/05/2026

## Objetivo

- desenvolver solução fullstack (java + react)
- consulta e analise sobre **cobertura vacinal por região** (sudeste) e **faixa etária**

## Estrutura técnica requisitada

-  Controller
    -  Retorne `ResponseEntity` com status adequado (200, 201, 404)
- Service
    - Lógica de negócio
    - Leitura do CSV (OpenCSV ou similar) para popular o banco
- Repository
    - estende JpaRepository
    - métodos a mais (findByVacina, findByEstado) 
- Model
    - RegistroVacinacao
        - id, municipio, estado, vacina, dose, quantidadeAplicada, dataRegistro
- Persistência (Recomendado banco H2)
- Integração com front
    - Listar aplicações
    - Filtrar por tipo de vacina
    - Exibir resumos por estado

## Funcionalidades Esperadas

### Backend

- Carga de dados
    - método para popular o banco com amostra de dados reais
    - Recomendações:
        - ([OpenDataSUS](https://dadosabertos.saude.gov.br/))
        - Portal da Transparência sobre imunização
- Consultas especializadas
    - Filtro por **tipo de vacina**, **estado/região**
- Gestão completa (CRUD)
    - cadastro novos registros;
    - editar dados;
    - excluir dados incorretos

### Frontend

- Dashboard
    - Tabela organizada com o histórico de vacinação
- Filtro em tempo real
- Formulário de inserção

## Documentação

Entrega do repositório com README contendo:

- Dicionário de dados (explicação cada campo entidade)
- Tabela com mapeamento de rotas
- Print de funcionamento (capturas do front + testes postman)
- Justificativa técnica (explicar importância do uso do Optional para segurança contra valores nulos)

## Apresentação

- Explicação código (4min) 
    - mostrar organização de pastas e uso de anotações '@'
- Demonstração prática (7min)
    - cadastrar pelo front
    - mostrar persistência dos dados
- Tratamento de erro (4min)
    - mostrar 404 ao buscar ID inexistente
