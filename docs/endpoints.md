# API Endpoints

## Cars Service

### Cars
- **GET** `/cars/` - Lista todos os carros  
- **GET** `/cars/{id_carro}` - Busca o carro pelo ID
- **POST** `/cars/` - Cria um novo carro  
- **PUT** `/cars/{id_carro}` - Atualiza um carro existente  

### Brands
- **GET** `/cars/brands/` - Lista todas as marcas
- **GET** `/cars/brands/{id_marca}` - Busca a marca pelo ID
- **POST** `/cars/brands/` - Cria uma nova marca  
- **PUT** `/cars/brands/{id_marca}` - Atualiza uma marca existente  

### Stores
- **GET** `/cars/stores/` - Lista todas as lojas
- **GET** `/cars/stores/{id_loja}` - Busca a loja pelo ID 
- **POST** `/cars/stores/` - Cria uma nova loja  
- **PUT** `/cars/stores/{id_loja}` - Atualiza uma loja  

### Car Images
- **GET** `/cars/car-images/{id_carro}` - Retorna todas as imagens de um carro
- **POST** `/cars/car-images/` - Salva a imagem de um carro  
- **PUT** `/cars/car-images/{id_carro}/{id_imagem}` - Atualiza uma imagem do carro
- **DELETE** `/cars/car-images/{id_carro}/{id_imagem}` - Apaga uma imagem do carro

## Auth / Users Service

### User Settings
- **GET** `/users/user-settings/{id_usuario}` - Busca para ver se já existe a configuração do usuário
- **POST** `/users/user-settings/` - Caso o **GET** retornou 404, chama o **POST** para criar uma configuração para o usuário
- **PUT** `/users/user-settings/{id_usuario}` - Atualiza as configurações do usuário (sempre vai existir apenas um registro por usuário)

### User Favorites
- **GET** `/users/user-favorites/{id_usuario}` - Retorna todos os carros favoritados do usuário
- **POST** `/users/user-favorites/` - Adiciona um novo item aos favoritos do usuário
- **DELETE** `/users/user-favorites/{id_usuario}/{id_carro}` - Remove o carro dos favoritos do usuário

### System Info
- **GET** `/users/system-info` - Retorna as informações do sistema (como a versão)

### Auth
* *FALTA DEFINIR*

## Orders Service

### Orders
- **GET** `/orders/{id_usuario}` - Listar os pedidos do usuário
- **POST** `/orders/` - Cria um novo pedido 
- **PUT** `/orders/{id_pedido}` - Atualiza um pedido

### Order Items
- **GET** `/orders/{id_pedido}/items` - Retorna os itens do pedido

### Shippings
- **GET** `orders/shippings/{id_frete}` - Busca por algum frete
- **POST** `/orders/shippings/` - Cria um novo frete (vai usar o endereço salvo na tabela do usuário)
  - *Pode deixar que em um primeiro momento, o backend vai criar o frete*.
- **PUT** `/orders/shippings/{id_frete}` - Atualiza o frete (status, se já foi entregue, etc) 

### Cart
- **GET** `/orders/cart/{id_usuario}` - Listar todos os itens do carrinho do usuário do ID 
- **POST** `/orders/cart/` - Adiciona um carro no carrinho do usuário  
- **DELETE** `/orders/cart/{id_usuario}/{id_carro}` - Remove um carro do carrinho do usuário

## Currencies Service

- **GET** `/currencies/{value}/{current_currency}/{target_currency}` - Converte um valor, de uma moeda para outra

## Addresses Service
- **GET** `/addresses/users/{id_usuario}` - Retorna o endereço do usuário
- **GET** `/addresses/stores/{id_loja}` - Retorna o endereço da loja
- **POST** `/addresses/users/` - Cria um endereço para o usuário
- **POST** `/addresses/stores/` - Cria um endereço para a loja
- **PUT** `/addresses/users/{id_usuario}` - Atualiza o endereço do usuário  
- **PUT** `/addresses/stores/{id_loja}` - Atualiza o endereço da loja