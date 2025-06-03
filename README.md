# Projeto MDM (Master Data Management) & DEM (Data Evolution Management)

Este repositório contém uma prova de conceito de um sistema MDM composto por microserviços integrados (MDM e DEM), projetado para gerenciar múltiplos domínios de dados mestres e suportar processos ETL (Extract, Transform, Load) para a evolução dos dados.

## Sumário

* [Visão Geral do Projeto](#visão-geral-do-projeto)
* [Arquitetura do Sistema](#arquitetura-do-sistema)
* [Estrutura do Repositório](#estrutura-do-repositório)
* [Tecnologias Utilizadas](#tecnologias-utilizadas)
* [Pré-requisitos](#pré-requisitos)
* [Como Rodar o Projeto](#como-rodar-o-projeto)
* [Documentação das APIs](#documentação-das-apis)
* [Endpoints da API](#endpoints-da-api)
* [Fluxos de Dados](#fluxos-de-dados)
* [Integração de Novos Domínios](#integração-de-novos-domínios)

---

## Visão Geral do Projeto

O objetivo principal deste projeto é demonstrar a capacidade de um sistema de Gestão de Dados Mestres (MDM) e Gerenciamento da Evolução de Dados (DEM) para:

* **Centralizar e Unificar Dados Mestres:** Oferecer uma fonte única e confiável para dados críticos da empresa (ex: países, produtos, clientes).
* **Garantir Qualidade dos Dados:** Implementar regras e processos para manter a integridade e consistência dos dados.
* **Gerenciar a Evolução dos Dados (DEM):** Suportar a transformação e o enriquecimento dos dados mestres ao longo do tempo, através de processos ETL.
* **Flexibilidade e Escalabilidade:** Arquitetura baseada em microserviços para facilitar a expansão e manutenção.

---

## Arquitetura do Sistema

O sistema é composto por dois microserviços principais:

1.  **`mdm-service`**: Responsável pela gestão dos dados mestres. Atualmente, gerencia o domínio de `Países`. Pode ser estendido para outros domínios (ex: `Produtos`, `Clientes`).
2.  **`dem-service`**: Focado nos processos de evolução e transformação de dados. Este serviço interage com o `mdm-service` para orquestrar e aplicar mudanças nos dados mestres.


![Arquitetura do Sistema](/AUX/diagrama.png)

---

## Estrutura do Repositório

O repositório está organizado em módulos Maven, cada um representando um microserviço:

* `mdm-service/`: Contém o código-fonte e configurações do serviço de gerenciamento de dados mestres.
* `dem-service/`: Contém o código-fonte e configurações do serviço de evolução de dados.
* `README.md`: Este arquivo, descrevendo o projeto em alto nível.
* `AUX/` (Opcional): Diretório para documentação adicional, diagramas, etc.

---

## Tecnologias Utilizadas

* **Backend:** Java 17+, Spring Boot 3.5.0
* **Base de Dados:** H2
* **Ferramenta de Build:** Maven
* **Documentação API:** Springdoc OpenAPI (Swagger UI)
* **Gerenciamento de Dependências:** Lombok

---

## Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

* **Java Development Kit (JDK) 17 ou superior**
* **Maven 3.6.0 ou superior**
* **H2** (instalado localmente)

---

## Como Rodar o Projeto

### 1. Configurar o Banco de Dados

Não há necessidade de usar Docker já que o banco H2 será instanciado localmente

#### Para o mdm-service
cd mdm-service
mvn spring-boot:run

#### Abra outro terminal para o dem-service
cd ../dem-service
mvn spring-boot:run


---

## 🌐 Endpoints da API

Esta seção oferece um resumo dos principais endpoints disponíveis em cada microserviço. Para o detalhamento completo de cada um, incluindo parâmetros, exemplos de requisição/resposta e modelos de dados, consulte a documentação interativa via Swagger UI.

### MDM Service (Porta: 8080)

**Controlador:** `CountryController` (`/countries`)
Gerencia os dados mestres de países.

* **`POST /countries`**
    * Cria um novo país.
    * **Exemplo de corpo da requisição:**
        ```json
        {
            "isoCode": "BRA",
            "commonName": "Brazil",
            "officialName": "Federative Republic of Brazil",
            "region": "Americas",
            "subregion": "South America",
            "area": 8515767.0,
            "population": 213993437,
            "geoLocation": {
                "latitude": -10.0,
                "longitude": -55.0
            },
            "capitals": ["Brasília"],
            "currencies": [
                {
                    "code": "BRL",
                    "name": "Brazilian Real",
                    "symbol": "R$"
                }
            ],
            "translations": [
                {
                    "languageCode": "por",
                    "officialName": "República Federativa do Brasil",
                    "commonName": "Brasil"
                }
            ]
        }
        ```

* **`GET /countries`**
    * Lista todos os países cadastrados.

* **`GET /countries/{id}`**
    * Busca um país específico por seu código ISO.

* **`PUT /countries/{id}`**
    * Atualiza os dados de um país existente pelo seu código ISO.
    * **Exemplo de corpo da requisição (atualiza o nome oficial do Brasil):**
        ```json
        {
            "isoCode": "BRA",
            "commonName": "Brazil",
            "officialName": "República Federativa do Brasil",
            "region": "Americas",
            "subregion": "South America",
            "area": 8515767.0,
            "population": 213993437,
            "geoLocation": {
                "latitude": -10.0,
                "longitude": -55.0
            },
            "capitals": ["Brasília"],
            "currencies": [
                {
                    "code": "BRL",
                    "name": "Brazilian Real",
                    "symbol": "R$"
                }
            ],
            "translations": [
                {
                    "languageCode": "por",
                    "officialName": "República Federativa do Brasil",
                    "commonName": "Brasil"
                }
            ]
        }
        ```

* **`DELETE /countries/{id}`**
    * Deleta um país específico por seu código ISO.

* **`DELETE /countries`**
    * Deleta todos os países cadastrados no sistema.

### DEM Service (Porta: 8081)

**Controlador:** `EtlController` (`/etl`)
Gerencia a execução de processos de ETL (Extract, Transform, Load).

* **`GET /etl/{domain}`**
    * Inicia um processo ETL para o domínio de dados especificado.
    * **Parâmetro de Path:** `domain` (String) - O nome do domínio para o qual o ETL será executado (ex: `countries`).
    * **Exemplo de uso:** `GET http://localhost:8081/etl/countries`
    * **Exemplo de Resposta (200 OK):** `"ETL para 'countries' iniciado com sucesso!"`
    * **Exemplo de Resposta (400 Bad Request):** `"Domínio 'xyz' não reconhecido ou suportado."`
    * **Exemplo de Resposta (500 Internal Server Error):** `"Erro ao processar ETL para 'countries': Erro de conexão com o banco de dados."`

---

---

## 🧩 Integração de Novos Domínios

A arquitetura modular do projeto simplifica a adição de novos domínios de dados mestres, como **Produtos**, **Clientes** ou **Fornecedores**. Para integrar um novo domínio, siga estes passos gerais:

### No `mdm-service` (Gerenciamento de Dados Mestres):

1.  **Defina a Entidade:** Crie a classe Java para a nova entidade (por exemplo, `ProductEntity`), anotando-a com as anotações **JPA** apropriadas para o mapeamento do banco de dados.
2.  **Crie o Repositório:** Desenvolva uma interface de repositório (como `ProductRepository`) que estenda **`JpaRepository`** para gerenciar as operações de persistência de dados.
3.  **Implemente o Controller:** Crie um novo controlador REST (por exemplo, `ProductController`), seguindo o padrão do `CountryController`, para expor endpoints **CRUD** (Create, Read, Update, Delete) para o novo domínio.
4.  **Documente a API:** Adicione as anotações **Springdoc OpenAPI** ao novo controller e seus métodos. Isso garantirá que a API seja automaticamente documentada no **Swagger UI** do `mdm-service`.

### No `dem-service` (Evolução e Transformação de Dados):

1.  **Adapte o Serviço ETL:** Se o novo domínio exigir processos específicos de evolução ou transformação de dados, atualize o `EtlService` para incluir a lógica necessária. Você pode fazer isso adicionando novos métodos ou estratégias de ETL que respondam ao nome do novo domínio.

---
