# Requests Order (+ ideias)

## Criando uma loja
1. Fazer um post para criar a loja.
2. Fazer um post para criar o endereço.
3. Atualizar o endereço da loja, com o id retornado do post da criação do endereço.

## Criando um carro
1. Cria uma marca ou uma loja.
2. Na tela de criação do carro, fazer um get para alimentar os campos de loja e marca.
3. Definir as informações e a imagem que vai aparecer na lista.
4. Salvar essa foto no cloudinary.
5. Fazer um post do carro, com a url da imagem retornada, e o post do carro vai te retornar o id do mesmo.
6. Fazer um post nas imagens do carro, enviando o id recém criado para o carro.
   1. Pode ser feito em um update também, ou uma tela especifica só para fazer o upload das imagens

## Retornando as informações do carro
1. Fazer um get para pegar as informações do carro.
2. Fazer um outro get para pegar as imagens desse carro.

## Criando um usuário
1. Fazer um post do usuário com a foto de perfil (opcional)
2. Depois de criar o usuário, criar o endereço dele, e então atualizar a tabela do usuário enviando o id retornado do post dos endereços.

## Criando um pedido
1. Fazer um post para criar o pedido.
2. Fazer um post para adicionar os itens do carrinho na tabela de items do pedido (vai usar o id retornado pelo post de criação de pedido)
   1. Pode deixar que eu chamo o endpoint de frete.

## Configurações/Preferências do usuário
1. Vai verificar se existem configurações para o usuário logado, se sim, vai atualiza-las, se não, vai criar.
2. Vai sempre existir apenas um registro de configuração por usuário.

## Carrinho e Favoritos
1. O `GET` sempre vai ser feito para o carrinho ou favoritos do usuário, pois cada usuário pode ter apenas um de cada, ou seja, um carrinho e uma lista de favoritos.