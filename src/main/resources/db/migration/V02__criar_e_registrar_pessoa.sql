CREATE TABLE pessoa (
  codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(30) NOT NULL,
  ativo BOOLEAN NOT NULL,
  logradouro VARCHAR(30),
  numero VARCHAR(30),
  complemento VARCHAR(30),
  bairro VARCHAR(30),
  cep VARCHAR(30),
  cidade VARCHAR(30),
  estado VARCHAR(30)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Carlos Pereira Andrade', true, 'Qnp 16 Conjunto G', '16A', 'Casa 16-A', 'Ceilândia Sul', '7200000', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Pedro Vaz de Caminha', true, 'Qnp 32 Conjunto A', '12', null, 'Ceilândia Sul', '7200000', 'Brasília', 'DF');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Pedro Santos', true, 'Rua da Bateria', '23', null, 'Morumbi', '54.212-12', 'Goiânia', 'GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Ricardo Pereira', true, 'Rua do Motorista', '123', 'Apto 302', 'Aparecida', '38.400-12', 'Salvador', 'BA');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Josué Mariano', true, 'Av Rio Branco', '321', null, 'Jardins', '56.400-12', 'Natal', 'RN');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Pedro Barbosa', true, 'Av Brasil', '100', null, 'Tubalina', '77.400-12', 'Porto Alegre', 'RS');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Henrique Medeiros', true, 'Rua do Sapo', '1120', 'Apto 201', 'Centro', '12.400-12', 'Rio de Janeiro', 'RJ');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Carlos Santana', true, 'Rua da Manga', '433', null, 'Centro', '31.400-12', 'Belo Horizonte', 'MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Leonardo Oliveira', true, 'Rua do Músico', '566', null, 'Segismundo Pereira', '38.400-00', 'Uberlândia', 'MG');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado) VALUES ('Isabela Martins', true, 'Rua da Terra', '1233', 'Apto 10', 'Vigilato', '99.400-12', 'Manaus', 'AM');