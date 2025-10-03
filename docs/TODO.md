## Geral
* [X] Modelo ER
* [X] Definição dos Microsserviços
* [X] Documentação dos Endpoints
  * [ ] Revisar   
* [X] Mapa de requisições
* [ ] Swagger
* [ ] Trocar ENUMs por IDs
* [ ] Ver o que será feito para os fretes
* [ ] Ver como deve ser buscada a config do banco para o java.
* [ ] Verificar o que significa o `ws`. 

## Banco
* [X] Script de criação
* [ ] Trigger para tabela de arquivos
* [ ] Trigger para atualizar `last_update` 

## Gateway
* [ ] Colocar rotas
* [ ] Proteger as rotas necessárias

## User Services
* [ ] Colocar NotFound no get dos favoritos.
* [ ] Colocar a coluna IsActive.

## Order Services
* [ ] Colocar NotFound no get do carrinho.
* [ ] Criar o número do pedido e retornar (não é o ID) 

## Auth Service
* [ ] Testar com o gateway
* [ ] Salvar o endereço do usuário

## Cars Service
* [ ] Finalizar

## Currencies
* [ ] Ajustar

## Docker
* [ ] Criar containers
* [ ] Criar compose


---------
Primeiro ajustar os servicos em c#
Ajustar os DTOs (Request e Response)
Criar um novo servico somente para enderecos
Criar um DTO de enderecos onde vao ser usados
  Pegar e criar a GUID e so enviar essa GUID, dessa forma ambos sao criados atomicamente.
Colocar migrations em cada, com o nome global_motors_SERVICO
  Remover coisas de DB first
Colocar como int pois quem vai definir essas infos de endereco vai ser o proprio
Quando cria no banco de address, ele envia novamente para que finalize o cadastro "principal"


Orders OK 
Addresses OK