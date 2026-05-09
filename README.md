# ImuniData - Sistema de Monitoramento de Vacinação

## 1. Problema e Solução
**Problema:** A falta de conscientização e a dificuldade de acesso a dados claros sobre a cobertura vacinal dificultam a gestão de saúde pública e a percepção do cidadão sobre a importância da imunização. A dispersão de informações torna complexo o acompanhamento de quais regiões estão com baixa adesão.

**Solução:** O ImuniData resolve este problema através de uma API robusta e simplificada que centraliza o registro e a consulta de doses aplicadas. Ao organizar os dados por município, estado e tipo de vacina, o sistema permite que gestores e usuários visualizem rapidamente a cobertura vacinal, facilitando a identificação de gargalos e promovendo a conscientização sobre a necessidade de atualização do ciclo vacinal de forma intuitiva e eficiente.

## 2. Dicionário de Dados
A entidade principal do sistema é a `Vacina`, que representa um registro de aplicação de imunizante.

| Campo | Tipo | Descrição | Restrições |
| :--- | :--- | :--- | :--- |
| `id` | Integer | Identificador único do registro (gerado automaticamente). | Chave Primária |
| `municipio` | String | Nome da cidade onde a vacina foi aplicada. | Obrigatório |
| `estado` | String | Sigla ou nome do estado da aplicação. | Obrigatório |
| `vacina` | String | Nome do imunizante aplicado (ex: COVID-19, Gripe). | Obrigatório |
| `dose` | String | Identificação da dose (ex: 1ª dose, Reforço). | Obrigatório |
| `quantidadeAplicada` | Integer | Quantidade de doses registradas naquela entrada. | Obrigatório, $\ge 0$ |
| `dataRegistro` | String | Data em que a vacinação foi registrada. | Obrigatório |

```json

{
    "municipio": "Campinas",
    "estado": "SP",
    "vacina": "COVID-19",
    "dose": "1ª dose",
    "quantidadeAplicada": 120,
    "dataRegistro": "2026-05-10"
}

```

## 3. Mapeamento de Rotas (API)
Todas as rotas seguem o prefixo `/vacina`.

| Método | Endpoint | Descrição | Corpo da Requisição | Retorno Sucesso | Retorno Erro |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **GET** | `/vacina` | Lista todas as vacinas | N/A | `200 OK` + `List<Vacina>` | `500 Internal Server Error` |
| **GET** | `/vacina/{id}` | Busca vacina por ID | N/A | `200 OK` + `Vacina` | `404 Not Found` |
| **POST** | `/vacina` | Cadastra nova vacina | JSON da entidade `Vacina` | `201 Created` + `Vacina` | `400 Bad Request` |
| **PATCH** | `/vacina/{id}` | Atualiza dados parciais | JSON com campos a alterar | `200 OK` + `Vacina` | `404 Not Found` |
| **DELETE** | `/vacina/{id}` | Remove registro | N/A | `204 No Content` | `404 Not Found` |

## 4. Justificativa Técnica

### Arquitetura MVC (Model-View-Controller)
O projeto foi implementado seguindo o padrão **MVC**, garantindo a separação de responsabilidades e facilitando a manutenção do código:
- **Model (`Vacina`)**: Define a estrutura de dados e as regras de validação da entidade, isolando a representação dos dados do restante da aplicação.
- **View (JSON/ResponseEntity)**: Como se trata de uma API REST, a "View" é a representação dos dados em formato JSON retornada ao cliente através de `ResponseEntity`, permitindo que diferentes front-ends consumam a mesma lógica.
- **Controller (`VacinaController`)**: Atua como a camada de entrada, gerenciando as requisições HTTP, validando os inputs e direcionando as chamadas para a camada de serviço.
- **Service (`VacinaService`)**: Camada de lógica de negócio, onde residem as regras de atualização e processamento, evitando que o Controller tenha conhecimento de como os dados são manipulados no banco.

### Segurança contra Valores Nulos com `Optional`
Para evitar o erro clássico `NullPointerException` e tornar o código mais expressivo, foi utilizado o tipo `Optional<T>` em métodos de busca e atualização:
- **Prevenção de Erros**: Em vez de retornar `null` quando um registro não é encontrado, o método retorna um `Optional`. Isso obriga o desenvolvedor a tratar explicitamente a ausência do valor.
- **Fluidez**: O uso de métodos como `.map()` e `.orElseGet()` no Controller permite transformar a presença ou ausência de um objeto diretamente em respostas HTTP (`200 OK` ou `404 Not Found`) de forma concisa e segura.

### Tratamento de Erros Centralizado
A implementação do `TratadorDeErros` utilizando `@RestControllerAdvice` permite que a API capture exceções de validação (`MethodArgumentNotValidException`) de forma global. Isso garante que o cliente receba sempre uma resposta padronizada e amigável em caso de erro de preenchimento (400 Bad Request), independentemente de qual endpoint foi acessado.
