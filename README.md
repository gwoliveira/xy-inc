# xy-inc

[![Build Status](https://travis-ci.org/gwoliveira/xy-inc.svg?branch=master)](https://travis-ci.org/gwoliveira/xy-inc) [![Coverage Status](https://coveralls.io/repos/github/gwoliveira/xy-inc/badge.svg?branch=master)](https://coveralls.io/github/gwoliveira/xy-inc?branch=master) [![Codacy Badge](https://api.codacy.com/project/badge/grade/4d83c12ea7414be4834b40e2408b5b82)](https://www.codacy.com/app/gwoliveira/xy-inc) 

# Instalação:
  1. instalar o [Compose](https://docs.docker.com/compose/install/) 
  2. executar o comando ```bash  docker-compose run gwoliveira/restzup ``` 
  3. abrir no navegador (http://localhost:9000) para acessar o client

## Arquitetura backend:
  * [MongoDB] (https://www.mongodb.org/)
    * como tem um schema flexivel facilita a criação de modelos de forma dinânica.
  * [Spring Boot](http://projects.spring.io/spring-boot/)
    * container spring auto configuravel, economia de tempo de setup do projeto.
    * injeção de dependencias.
  * [Juni](http://junit.org/)
    * ferramenta para criação de testes unitários java.
  * [Mockito](http://mockito.org/)
    * framework de criação dos mocks para os testes.
    * 
## Arquitetura frontend:
  * [Bootstrap](http://getbootstrap.com/)
  * [AngularJS](https://angularjs.org/)

## Ferramentas de integração continua:
  * [GitHub](https://github.com)
    * repositorio git.
  * [Travis](https://travis-ci.org)
    * ferramenta de build.
  * [Coveralls](https://coveralls.io)
    * analise de cobertura de testes.
  * [Codacy](https://www.codacy.com)
    * analise de codigo.
    
