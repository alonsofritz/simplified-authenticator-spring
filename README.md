# Autenticador Simplificado
Projeto simplificado de um autenticador de usuários utilizando JWT, Spring Boot, Spring Security, JPA, MySQL, SOLID e Clean Architecture.

## Índice

1. [Estrutura de Pastas](#estrutura-de-pastas)
   
   2. [Diferença entre Services na Application e Domain](#diferença-entre-services-na-application-e-domain)
   3. [Diferença entre Mappers da Adapters e da Application](#diferença-entre-mappers-da-adapters-e-da-application)
   4. [Diferença entre Use Cases e Services da Application](#diferença-entre-use-cases-e-services-da-application)
   5. [Resumo das Diferenças](#resumo-das-diferenças)
6. [Helper](#helper)
    1. [Gerar chave privada](#gerar-chave-privada)
    2. [Gerar chave pública a partir da chave privada](#gerar-chave-pública-a-partir-da-chave-privada)

## Estrutura de Pastas

```
src
└── main
    └── java
        └── com
            └── seu
                └── projeto
                    ├── application
                    │   ├── dto                 // Data Transfer Objects
                    │   ├── exceptions          // Exceções específicas da aplicação
                    │   ├── services            // Serviços da camada de aplicação
                    │   ├── usecases            // Casos de uso (interações da aplicação)
                    │   └── mappers             // Mapeadores entre entidades e DTOs
                    │
                    ├── domain
                    │   ├── entities            // Entidades de domínio
                    │   ├── repositories         // Interfaces de repositórios
                    │   ├── services            // Lógica de negócios
                    │   ├── specifications       // Especificações de domínio (predicados)
                    │   └── events               // Eventos de domínio
                    |   └── valueobjects        // Objetos de valor
                    |   └── factories            // Fábricas de entidades
                    │
                    ├── adapters
                    │   ├── controllers          // Controladores (REST, gRPC, etc.)
                    │   ├── gateways             // Interfaces para sistemas externos
                    │   ├── presenters           // Formatação de dados para saída
                    │   └── mappers              // Mapeadores de entidades para DTOs (separados dos da aplicação)
                    │
                    └── infra
                        ├── configuration        // Configurações gerais (Spring, segurança, etc.)
                        ├── database             // Configuração de banco de dados (JPA, JDBC)
                        │   ├── entities         // Entidades específicas para o banco de dados
                        │   ├── repositories      // Implementações dos repositórios
                        │   └── migrations        // Scripts de migração do banco de dados
                        │
                        ├── messaging            // Configuração de mensageria (RabbitMQ, Kafka, etc.)
                        ├── security             // Configuração de segurança (Spring Security, autenticação)
                        ├── logging              // Configuração de logging (Logback, SLF4J, etc.)
                        ├── cache                // Configuração de cache (Redis, Memcached)
                        └── external             // Integrações com serviços externos (APIs, SDKs)

```

### 1. **application**
- **dto**: Contém os **Data Transfer Objects**, que são objetos usados para transportar dados entre diferentes camadas da aplicação, especialmente entre a camada de apresentação (adaptadores) e a camada de aplicação. Eles geralmente não contêm lógica de negócios, mas sim estruturas de dados simples.
- **exceptions**: Exceções específicas que podem ser lançadas durante a execução dos casos de uso ou serviços. Isso ajuda a centralizar o tratamento de erros.
- **services**: Serviços da camada de aplicação que orquestram a lógica de negócios e a interação entre os casos de uso. Eles podem chamar múltiplos casos de uso e coordenar suas operações.
- **usecases**: Casos de uso que representam interações específicas que os usuários podem ter com a aplicação. Cada caso de uso é responsável por uma ação ou tarefa, encapsulando a lógica necessária para completá-la.
- **mappers**: Mapeadores que transformam entidades de domínio em DTOs e vice-versa. Eles são úteis para separar a lógica de conversão e manter os casos de uso mais limpos.

### 2. **domain**
- **entities**: Contém as entidades de domínio, que são representações dos objetos de negócio e contêm a lógica relacionada ao estado e ao comportamento desses objetos.
- **repositories**: Interfaces que definem operações de acesso a dados para as entidades de domínio. Os repositórios abstraem a lógica de persistência e permitem que a aplicação interaja com a camada de dados.
- **services**: Lógica de negócios que não se encaixa em uma entidade específica. Por exemplo, serviços que realizam operações complexas envolvendo múltiplas entidades. Esses serviços podem incluir regras de negócios que são fundamentais para a lógica da aplicação.
- **specifications**: Predicados ou critérios que podem ser usados para consultar entidades. Eles ajudam a separar a lógica de consulta da lógica de domínio.
- **events**: Representam eventos que ocorrem no domínio, como alterações de estado. Eles podem ser usados para implementar padrões como Event Sourcing ou CQRS.
- **valueobjects**: Objetos que representam valores (como moeda ou endereço) e não têm identidade própria. Eles são imutáveis e geralmente são usados para encapsular regras de validação e lógica.
- **factories**: Classes responsáveis pela criação de entidades ou objetos complexos, encapsulando a lógica de construção.

### 3. **adapters**
- **controllers**: Controladores que recebem as solicitações do usuário e orquestram as chamadas aos casos de uso apropriados. Eles também formatam as respostas a serem enviadas de volta ao cliente.
- **gateways**: Interfaces que abstraem a comunicação com sistemas externos, como APIs ou serviços de terceiros. Eles permitem que a aplicação se integre com serviços externos sem acoplamento forte.
- **presenters**: Formatação de dados para saída. Eles preparam os dados que serão retornados ao usuário, possivelmente convertendo dados de domínio em formatos mais amigáveis.
- **mappers**: Mapeadores específicos para conversão de entidades em DTOs (separados da aplicação). Esses mapeadores são usados para transformar os dados que vêm do banco ou de outras fontes em estruturas que a camada de apresentação pode usar.

### 4. **infra**
- **configuration**: Configurações gerais da aplicação, como as configurações do Spring, segurança, etc. Esse pacote pode conter classes de configuração e inicialização.
- **database**: Configuração específica de banco de dados. Pode conter entidades JPA, repositórios concretos que implementam as interfaces definidas na camada de domínio, e scripts de migração.
- **messaging**: Configuração de sistemas de mensageria, como RabbitMQ ou Kafka. Isso pode incluir tópicos, filas e configurações para a publicação e assinatura de mensagens.
- **security**: Configurações de segurança, incluindo autenticação e autorização usando frameworks como o Spring Security.
- **logging**: Configuração de logging, como integração com Logback ou SLF4J, permitindo a captura de logs da aplicação.
- **cache**: Configuração de sistemas de cache, como Redis ou Memcached, para otimizar o desempenho da aplicação.
- **external**: Integrações com serviços externos, como APIs ou SDKs. Esse pacote pode conter classes e configurações para se conectar e interagir com serviços fora da aplicação.

### Diferença entre Services na Application e Domain

- **Services da Application**:
    - Responsáveis por orquestrar a lógica de interação entre os casos de uso. Eles lidam com a lógica de aplicação, como controle de fluxo, validação de entrada e coordenação de chamadas a múltiplos casos de uso.
    - Exemplo: Um serviço de aplicação pode ser responsável por validar um usuário, chamar o caso de uso de registro e, em seguida, gerar um token.

- **Services da Domain**:
    - Contêm a lógica de negócios que está intrinsecamente relacionada às entidades de domínio. Eles podem incluir operações complexas que envolvem regras de negócio, mas não estão diretamente ligadas ao controle de fluxo da aplicação.
    - Exemplo: Um serviço de domínio pode ter a responsabilidade de calcular o preço total de um pedido, considerando regras específicas de negócio, como descontos ou promoções.


### Diferença entre Mappers da Adapters e da Application

#### Mappers da Application
- **Responsabilidade**: Os mappers da camada **application** são responsáveis por transformar entidades de domínio em **Data Transfer Objects (DTOs)** e vice-versa. Eles atuam na conversão de dados que serão usados internamente pela aplicação, garantindo que as entidades de domínio sejam representadas adequadamente nas interações de uso dos casos.
- **Objetivo**: O objetivo principal é facilitar a comunicação entre a lógica de negócios (casos de uso) e a apresentação (controladores), mantendo a separação de preocupações e a clareza na manipulação dos dados.
- **Exemplo**: Se um caso de uso precisa de um DTO para receber dados de entrada de um controlador, o mapper da aplicação converte esse DTO em uma entidade de domínio.

```java
public class UserMapper {
    public static User toEntity(UserDTO userDTO) {
        return new User(userDTO.getUsername(), userDTO.getPassword());
    }

    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername());
    }
}
```

#### Mappers da Adapters
- **Responsabilidade**: Os mappers da camada **adapters** são encarregados de adaptar os dados entre a camada de apresentação (como uma API REST ou um serviço gRPC) e a estrutura interna da aplicação. Eles garantem que as informações sejam formatadas corretamente para serem enviadas ou recebidas de clientes ou sistemas externos.
- **Objetivo**: O foco está na integração com o mundo exterior, ajustando a forma como os dados são apresentados e recebidos, garantindo que as interfaces externas se comuniquem efetivamente com a lógica interna da aplicação.
- **Exemplo**: Quando um controlador precisa responder a uma requisição com dados de um usuário, o mapper da adapters converte a entidade de domínio em um DTO apropriado para a resposta da API.

```java
public class UserAdapterMapper {
    public static UserDTO toDTO(User user) {
        return new UserDTO(user.getUsername(), user.getPermissions());
    }
}
```

### Diferença entre Use Cases e Services da Application

#### Use Cases
- **Definição**: Os **use cases** representam ações ou tarefas específicas que os usuários podem executar na aplicação. Cada caso de uso encapsula a lógica necessária para realizar essa ação e possui um ponto de entrada e saída bem definidos.
- **Responsabilidade**: Eles se concentram em um fluxo específico e contêm a lógica que orquestra a interação entre diferentes componentes da aplicação, como repositórios e serviços de domínio.
- **Exemplo**: Um caso de uso `RegisterUser` pode ser responsável por registrar um novo usuário no sistema.

```java
@Component
public class RegisterUser {
    private final UserRepository userRepository;

    public RegisterUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void execute(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        userRepository.save(user);
    }
}
```

#### Services
- **Definição**: Os **services** na camada de **application** atuam como orquestradores que integram e gerenciam a lógica de aplicação. Eles podem chamar múltiplos casos de uso e também podem incluir lógica adicional que não se encaixa em um único caso de uso.
- **Responsabilidade**: Eles facilitam a reutilização de lógica comum entre diferentes casos de uso e gerenciam o fluxo de dados através de diferentes componentes da aplicação.
- **Exemplo**: Um serviço `UserService` pode gerenciar a lógica de registro e também enviar um e-mail de boas-vindas após o registro de um usuário.

```java
@Service
public class UserService {
    private final RegisterUser registerUser;
    private final EmailService emailService;

    public UserService(RegisterUser registerUser, EmailService emailService) {
        this.registerUser = registerUser;
        this.emailService = emailService;
    }

    public void register(UserDTO userDTO) {
        registerUser.execute(userDTO);
        emailService.sendWelcomeEmail(userDTO.getEmail());
    }
}
```

### Resumo das Diferenças

1. **Mappers**:
    - **Application Mappers**: Transformam entidades de domínio em DTOs e vice-versa, focando na comunicação entre lógica de negócios e controladores.
    - **Adapters Mappers**: Adaptam dados entre a estrutura interna da aplicação e o formato esperado por interfaces externas, como APIs.

2. **Use Cases vs. Services**:
    - **Use Cases**: Representam ações específicas que o usuário pode realizar e encapsulam a lógica para essa ação. Eles são chamados diretamente pelos controladores.
    - **Services**: Integram e orquestram a lógica de múltiplos casos de uso e podem incluir lógica adicional. Eles facilitam a reutilização e a coordenação entre diferentes partes da aplicação.

## Helper
### Gerar chave privada
`openssl genpkey -algorithm RSA -out private_key.pem -pkeyopt rsa_keygen_bits:2048`

### Gerar chave pública a partir da chave privada
`openssl rsa -pubout -in private_key.pem -out public_key.pem`
