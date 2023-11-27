-- tb_uf
CREATE TABLE tb_uf
(
    id_uf BIGINT       NOT NULL,
    sigla CHAR(2)      NOT NULL,
    nome  VARCHAR(32)  NOT NULL,

    CONSTRAINT pk_uf PRIMARY KEY (id_uf)
);

CREATE TABLE tb_cargo (
  id_cargo   BIGINT           NOT NULL, 
  nome       VARCHAR(16)      UNIQUE NOT NULL,
  salario    DOUBLE PRECISION NOT NULL,

  CONSTRAINT pk_cargo PRIMARY KEY (id_cargo)
);

-- tb_estabelecimento
CREATE TABLE tb_estabelecimento (
  id_estabelecimento        BIGSERIAL   NOT NULL,
  id_uf_estabelecimento     BIGINT      NOT NULL,
  endereco_estabelecimento  VARCHAR(64) NOT NULL,

  CONSTRAINT pk_estabelecimento    PRIMARY KEY (id_estabelecimento),
  CONSTRAINT fk_uf_estabelecimento FOREIGN KEY (id_uf_estabelecimento) REFERENCES tb_uf (id_uf)
);

-- tb_pessoa_fisica
CREATE TABLE tb_pessoa_fisica (
  id_pessoa_fisica  BIGSERIAL       NOT NULL,
  cpf               CHAR(11) UNIQUE NOT NULL,
  nome              VARCHAR(32)     NOT NULL,
  sobrenome         VARCHAR(64)     NOT NULL,

  CONSTRAINT pk_pessoa_fisica PRIMARY KEY (id_pessoa_fisica)
);

-- tb_funcionario
CREATE TABLE tb_funcionario (
  id_funcionario                BIGSERIAL NOT NULL,
  id_pessoa_fisica_funcionario  BIGINT    UNIQUE NOT NULL,

  CONSTRAINT pk_funcionario               PRIMARY KEY (id_funcionario),
  CONSTRAINT fk_pessoa_fisica_funcionario FOREIGN KEY (id_pessoa_fisica_funcionario) REFERENCES tb_pessoa_fisica (id_pessoa_fisica)
);

-- tb_cliente
CREATE TABLE tb_cliente (
  id_cliente                BIGSERIAL NOT NULL,
  id_pessoa_fisica_cliente  BIGINT    UNIQUE NOT NULL,

  CONSTRAINT pk_cliente               PRIMARY KEY (id_cliente),
  CONSTRAINT fk_pessoa_fisica_cliente FOREIGN KEY (id_pessoa_fisica_cliente) REFERENCES tb_pessoa_fisica (id_pessoa_fisica)
);

-- tb_telefone
CREATE TABLE tb_telefone (
  id_telefone               BIGSERIAL NOT NULL,
  id_pessoa_fisica_telefone BIGINT    NOT NULL,
  num_telefone              CHAR(11)  UNIQUE NOT NULL,

  CONSTRAINT pk_telefone PRIMARY KEY (id_telefone),
  CONSTRAINT fk_pessoa_fisica_telefone FOREIGN KEY (id_pessoa_fisica_telefone) REFERENCES tb_pessoa_fisica (id_pessoa_fisica)
);

-- tb_lotacao
CREATE TABLE tb_lotacao (
  id_lotacao_funcionario      BIGINT NOT NULL,
  id_lotacao_cargo            BIGINT NOT NULL,
  id_lotacao_estabelecimento  BIGINT NOT NULL,

  CONSTRAINT pk_lotacao                 PRIMARY KEY (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento),
  CONSTRAINT fk_lotacao_funcionario     FOREIGN KEY (id_lotacao_funcionario)     REFERENCES tb_funcionario (id_funcionario),
  CONSTRAINT fk_lotacao_cargo           FOREIGN KEY (id_lotacao_cargo)           REFERENCES tb_cargo (id_cargo),
  CONSTRAINT fk_lotacao_estabelecimento FOREIGN KEY (id_lotacao_estabelecimento) REFERENCES tb_estabelecimento (id_estabelecimento)
);

-- tb_venda
CREATE TABLE tb_venda (
  id_venda                  BIGSERIAL  NOT NULL,
  id_venda_funcionario      BIGINT     NOT NULL,
  id_venda_estabelecimento  BIGINT     NOT NULL,
  id_venda_cargo            BIGINT     NOT NULL DEFAULT 1,
  id_venda_cliente          BIGINT     NOT NULL,
  data_hora_venda           TIMESTAMP  NOT NULL DEFAULT now(),

  CONSTRAINT pk_venda             PRIMARY KEY (id_venda),
  CONSTRAINT fk_venda_lotacao     FOREIGN KEY (id_venda_funcionario, id_venda_estabelecimento, id_venda_cargo)
                                  REFERENCES tb_lotacao (id_lotacao_funcionario, id_lotacao_estabelecimento, id_lotacao_cargo),
  CONSTRAINT fk_venda_cliente     FOREIGN KEY (id_venda_cliente)     REFERENCES tb_cliente (id_cliente),
  CONSTRAINT ck_venda_cargo CHECK (id_venda_cargo IN (2))
);

-- tb_produto
CREATE TABLE tb_produto (
  id_produto BIGSERIAL        NOT NULL,
  nome       VARCHAR(64)      NOT NULL UNIQUE,
  valor      DOUBLE PRECISION NOT NULL,

  CONSTRAINT pk_produto PRIMARY KEY (id_produto)
);

-- tb_rl_venda_produto
CREATE TABLE tb_rl_venda_produto (
  id_rl_venda_produto_v BIGINT   NOT NULL,
  id_rl_venda_produto_p BIGINT   NOT NULL,
  quantidade            INTEGER  NOT NULL DEFAULT 1,

  CONSTRAINT pk_rl_venda_produto   PRIMARY KEY (id_rl_venda_produto_v, id_rl_venda_produto_p),
  CONSTRAINT fk_rl_venda_produto_v FOREIGN KEY (id_rl_venda_produto_v) REFERENCES tb_venda (id_venda),
  CONSTRAINT fk_rl_venda_produto_p FOREIGN KEY (id_rl_venda_produto_p) REFERENCES tb_produto (id_produto)
);

