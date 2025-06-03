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
* [Contribuição](#contribuição)
* [Licença](#licença)

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

*(**Ação:** Substitua o diagrama de texto por uma imagem real do diagrama de arquitetura, se possível. Ferramentas como Draw.io, Lucidchart ou Mermaid no próprio Markdown podem ajudar.)*

---

## Estrutura do Repositório

O repositório está organizado em módulos Maven, cada um representando um microserviço:

* `mdm-service/`: Contém o código-fonte e configurações do serviço de gerenciamento de dados mestres.
* `dem-service/`: Contém o código-fonte e configurações do serviço de evolução de dados.
* `README.md`: Este arquivo, descrevendo o projeto em alto nível.
* `docs/` (Opcional): Diretório para documentação adicional, diagramas, etc.

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

# Para o mdm-service
cd mdm-service
mvn spring-boot:run

# Abra outro terminal para o dem-service
cd ../dem-service
mvn spring-boot:run