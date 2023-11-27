INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (1, 2, 1);
INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (2, 1, 1);

INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (1, 1, 2);
INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (3, 2, 2);

INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (1, 1, 3);
INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (3, 2, 3);

INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (3, 1, 4);
INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (4, 2, 4);
INSERT INTO tb_lotacao (id_lotacao_funcionario, id_lotacao_cargo, id_lotacao_estabelecimento) VALUES (5, 2, 4);

INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (1, '48999424779');
INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (1, '48999752827');
INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (1, '48999212823');

INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (2, '48999244123');

INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (3, '48999224173');
INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (3, '48999994378');

INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (4, '48999194379');
INSERT INTO tb_telefone (id_pessoa_fisica_telefone, num_telefone) VALUES (4, '48999844378');

INSERT INTO tb_venda (
    id_venda_funcionario,
    id_venda_estabelecimento,
    id_venda_cargo,
    id_venda_cliente,
    data_hora_venda
) values (1, 1, 2, 1, now()),
         (3, 2, 2, 1, now());

INSERT INTO tb_rl_venda_produto (
    id_rl_venda_produto_v,
    id_rl_venda_produto_p,
    quantidade
) VALUES (1, 1, 1), (1, 2, 2), (1, 3, 1), (2, 4, 3), (2, 5, 3);