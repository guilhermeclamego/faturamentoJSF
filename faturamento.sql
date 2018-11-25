--Criação da tabela Categoria
create table categoria(
	id int not null constraint pk_categoria primary key,
	descricao varchar(30) not null);
  

--Criação da tabela Produto  
create table Produto(
	id int not null constraint pk_produto primary key,
	descricao varchar(50) not null,
	valorCusto numeric(16,4) not null,
	estoque int default 0 not null ,
	categoriaId int not null, 
  constraint fk_produto_categoria foreign key  (categoriaId) references categoria (id));
  
--Criação da tabela Municipios, apenas para ficar bonito  
create table municipios(
	codigo_ibge int not null constraint pk_municipios primary key,
	municipio varchar(100) not null,
	uf varchar(2) not null);	
  
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200010, 'Rio do Sul', 'SC');
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200020, 'Aurora', 'SC');
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200030, 'Curitiba', 'PR');
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200040, 'Londrina', 'PR');
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200050, 'Porto Alegre', 'RS');
INSERT INTO MUNICIPIOS (CODIGO_IBGE, MUNICIPIO, UF) VALUES (5200060, 'Caxias do Sul', 'RS');
  
	
--Criação da tabela Cliente  
create table cliente(
	id int not null constraint pk_cliente primary key,
	nome varchar(50) not null,
	cpf varchar(14),
	email varchar(50),
	municipioid int not null,
	bairro varchar(50),
	rua varchar(50),
	numero varchar(10),
	telefone varchar(20),
  datanascimento date,
	constraint fk_cliente_municipio foreign key (municipioId) references municipios(codigo_ibge));  
  
  
  --Criação da Tabela Pedidos
  create table pedidoCliente(
	clienteId int not null, 
	produtoId int not null, 
	status int default 0 not null,
    constraint pk_pedidoCliente primary key (clienteId, produtoId),
	constraint fk_pedidoCliente_cliente foreign key  (clienteId) references cliente (id),	
	constraint fk_pedidoCliente_produto foreign key  (produtoId) references produto (id));	
  
  --Criação da Tabela Usuario
  create table usuario(
    usuarioid int not null constraint pk_usuario primary key,
    nome varchar(50) not null,
    login varchar(10) not null,
    senha varchar(50) not null
  )
  
  --Criaçoã da tabela Funcionario
  create table funcionario(
    funcionarioid int not null constraint pk_funcionario primary key,
    cpf varchar(14) not null,
    nome varchar(50) not null,
    endereco varchar(100) not null,
    email varchar(50),
    usuario int not null
  )
  
  
  --Criação da tabela log_auditoria
  create table log_auditoria (
	tabela varchar(50) pk_auditoria primary key,
	usuario int,
	datahora timestamp,
	valorantigo text,
	valornovo text,
	tipo int --0 insert, 1 update e 2 exclusão
  )
  
  
  
  
  
CREATE OR REPLACE FUNCTION categoria_log_func()
RETURNS trigger AS $categoria_trigger$
BEGIN
INSERT INTO log_auditoria
(tabela, usuario, datahora, valorantigo, valornovo, tipo)
VALUES
('categoria', 1, current_timestamp, new.descricao, new.descricao, 1);
RETURN NEW;
END;
$categoria_trigger$ LANGUAGE plpgsql;


CREATE TRIGGER log_categoria
AFTER INSERT ON categoria
FOR EACH ROW
EXECUTE PROCEDURE categoria_log_func();
  
  