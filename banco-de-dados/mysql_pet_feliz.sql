CREATE DATABASE pet_feliz;
USE pet_feliz;

CREATE TABLE petshop (
  id_petshop BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  nome_petshop VARCHAR(50) NOT NULL,
  cnpj_petshop VARCHAR(21) NOT NULL UNIQUE,
  email_petshop VARCHAR(150) NOT NULL UNIQUE,
  senha_petshop VARCHAR(50) NOT NULL,
  telefone_petshop VARCHAR(20) NOT NULL UNIQUE,
  PRIMARY KEY (id_petshop)
);

CREATE TABLE cliente (
  id_cliente BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  nome_cliente VARCHAR(100) NOT NULL,
  cpf_cliente VARCHAR(17) NOT NULL UNIQUE,
  email_cliente VARCHAR(150) NOT NULL UNIQUE,	
  senha_cliente VARCHAR(50) NOT NULL,
  telefone_cliente VARCHAR(20) NOT NULL UNIQUE,
  id_petshop BIGINT NOT NULL,
  PRIMARY KEY (id_cliente),
  FOREIGN KEY (id_petshop) REFERENCES petshop (id_petshop)
);

CREATE TABLE produto (
  id_produto BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
  nome_produto VARCHAR(100) NOT NULL,
  descricao_produto VARCHAR(500),
  preco_produto DOUBLE NOT NULL,
  estoque_produto INT NOT NULL,
  id_petshop BIGINT NOT NULL,
  PRIMARY KEY (id_produto),
  FOREIGN KEY (id_petshop) REFERENCES petshop (id_petshop)
);

CREATE TABLE endereco (
  id_endereco BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  cep VARCHAR(11) NOT NULL UNIQUE,
  estado VARCHAR(45) NOT NULL,
  cidade VARCHAR(45) NOT NULL,
  bairro VARCHAR(45) NOT NULL,
  rua VARCHAR (50) NOT NULL,
  numero INT NOT NULL,
  id_cliente BIGINT NOT NULL UNIQUE,
  PRIMARY KEY (id_endereco),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente)
);

CREATE TABLE pedido (
  id_pedido BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  data_pedido_emissao DATE NOT NULL,
  descricao_pedido VARCHAR(60),
  qtde_item_pedido INT NOT NULL
);

CREATE TABLE animal (
  id_animal BIGINT NOT NULL AUTO_INCREMENT UNIQUE,
  nome_animal VARCHAR(30) NOT NULL,
  raca_animal VARCHAR(20) NOT NULL,
  idade_animal INT NOT NULL,
  descricao_animal VARCHAR(100),
  sexo_animal VARCHAR(50) NOT NULL,
  id_cliente BIGINT NOT NULL,
  id_petshop BIGINT NOT NULL,
  PRIMARY KEY (id_animal),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
  FOREIGN KEY (id_petshop) REFERENCES petshop (id_petshop)
);

CREATE TABLE cliente_pedido (
  id_cliente_pedido BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  id_cliente BIGINT NOT NULL,
  id_pedido BIGINT NOT NULL,
  PRIMARY KEY (id_cliente_pedido),
  FOREIGN KEY (id_cliente) REFERENCES cliente (id_cliente),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido)
);

CREATE TABLE produto_pedido (
  id_produto_pedido BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  qtde_item_pedido INT NOT NULL,
  id_pedido BIGINT NOT NULL,
  id_produto BIGINT NOT NULL,
  PRIMARY KEY (id_produto_pedido),
  FOREIGN KEY (id_pedido) REFERENCES pedido (id_pedido),
  FOREIGN KEY (id_produto) REFERENCES produto (id_produto)
);
