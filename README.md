# Passage API

Backend de ERP e Bilhetagem Eletrônica para transporte rodoviário de passageiros.

## Tech Stack

- **Runtime:** Java 21 (LTS)
- **Framework:** Spring Boot 3.x / Spring Modulith
- **Build System:** Apache Maven
- **Database:** PostgreSQL + Flyway
- **Cache & Distributed Locks:** Redis (Spring Data Redis)
- **Messaging:** RabbitMQ (AMQP)
- **Security:** Spring Security (RBAC)
- **Observability & Tools:** Spring Boot Actuator, Lombok, Docker Compose

---

## Arquitetura e Estrutura do Projeto

O projeto adota uma abordagem de **Monólito Modular** acoplado ao **Spring Modulith**. Cada domínio principal é encapsulado em seu próprio pacote.

### Regras de Visibilidade dos Módulos
1. O pacote raiz do módulo (ex: `br.com.passage.api.fleet`) define sua **API Pública** (`FleetApi.java`, DTOs de integração e eventos).
2. Todo o restante do código deve residir obrigatoriamente dentro do subpacote `.internal`.
3. É proibido importar classes do pacote `.internal` de outro módulo. O Spring Modulith valida essa regra via testes.

### Árvore de Pacotes

```text
br.com.passage.api
├── shared                   # EntityBase, exceções globais, VOs e utilitários
├── fleet                    # Gestão de empresas, ônibus e layouts de assentos
│   ├── FleetApi.java
│   └── internal             # web, service, repository, domain
├── route                    # Linhas, rotas e matriz de seccionamento de trechos
│   ├── RouteApi.java
│   └── internal
├── trip                     # Viagens agendadas, horários e alocação de veículos
│   ├── TripApi.java
│   └── internal
├── ticket                   # Reservas, vendas, locks temporários e emissão
│   ├── TicketApi.java
│   ├── events
│   └── internal
└── integration              # Integradores assíncronos (SEFAZ/BP-e, ANTT/Monitriip, Notificações)
    └── internal
```

## Convenções de Desenvolvimento

### 1. Modelo de Dados & EntityBase

Todas as entidades JPA devem herdar de EntityBase (br.com.passage.api.shared):

    id (Long): PK primária interna (uso exclusivo em JOINs e índices do PostgreSQL).

    uuid (UUID): Identificador público usado em endpoints REST e DTOs.

    version (Long): Controle de concorrência otimista via @Version.

    createdAt / updatedAt (Instant): Gerenciados via JPA Auditing.

    Atenção: Não utilize @Data do Lombok em entidades JPA. Implemente equals e hashCode na EntityBase utilizando exclusivamente o uuid.

### 2. Padrão Arquitetural

    1. Estilo: MVC em camadas tradicional (web ➔ service ➔ repository).

    2. Entidades Ricas: Métodos de validação e regras de transição de estado devem residir nas próprias entidades de domínio, mantendo os @Service focados na orquestração.

## Execução Local e Infraestrutura
### 1. Subir Infraestrutura (Containers)

Inicia o PostgreSQL, Redis e RabbitMQ com as configurações padrão do projeto:
```Bash
docker compose up -d
```
### 2. Rodar a Aplicação
```bash
./mvnw spring-boot:run
```
## Testes e Validação Arquitetural

Para validar se os limites de visibilidade dos módulos do Spring Modulith não foram quebrados por importações indevidas:
``` bash
./mvnw test -Dtest=ApiApplicationTests
```