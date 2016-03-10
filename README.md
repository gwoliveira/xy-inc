# xy-inc

[![Build Status](https://travis-ci.org/gwoliveira/xy-inc.svg?branch=master)](https://travis-ci.org/gwoliveira/xy-inc) [![Coverage Status](https://coveralls.io/repos/github/gwoliveira/xy-inc/badge.svg?branch=master)](https://coveralls.io/github/gwoliveira/xy-inc?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/grade/4d83c12ea7414be4834b40e2408b5b82)](https://www.codacy.com/app/gwoliveira/xy-inc) 

# Instalação:
  1. instale o [Compose](https://docs.docker.com/compose/install/) 
  2. clone este repositório ```git clone https://github.com/gwoliveira/xy-inc.git```
  3. execute o comando ``` docker-compose up ``` 
  4. abra no navegador <http://localhost:9000> para acessar o cliente
  5. a API esta exposta no endereço <http://localhost:8080>

## Arquitetura backend:
  * [MongoDB] (https://www.mongodb.org/)
    * como tem um schema flexivel facilita a criação de modelos de forma dinâmica.
  * [Spring Boot](http://projects.spring.io/spring-boot/)
    * container spring auto configurável, economia de tempo de setup do projeto.
    * injeção de dependências.
  * [Junit](http://junit.org/)
    * ferramenta para criação de testes unitários java.
  * [Mockito](http://mockito.org/)
    * framework de criação dos mocks para os testes.

## Arquitetura frontend:
  * [Bootstrap](http://getbootstrap.com/)
  * [AngularJS](https://angularjs.org/)

## Ferramentas de integração continua:
  * [GitHub](https://github.com)
    * repositório git.
  * [Travis](https://travis-ci.org)
    * ferramenta de build.
  * [Coveralls](https://coveralls.io)
    * analise de cobertura de testes.
  * [Codacy](https://www.codacy.com)
    * analise de código.
    
## Deploy
  * [Docker](http://www.docker.com/)
  * [Docker Compose](http://www.docker.com/products/docker-compose)
 
### Fique a vontade para abrir issues 
<https://github.com/gwoliveira/xy-inc/issues>

