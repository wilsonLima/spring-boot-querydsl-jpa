
DROP TABLE IF EXISTS tb_paciente;

CREATE TABLE tb_paciente (
    id bigint not null auto_increment,
    nome varchar(255),
    sexo varchar(255),
    data_registro date,
    data_nascimento date,
    cpf varchar(255),
    ativo boolean,
    rua varchar(255),
    numero varchar(255),
    distrito varchar(255),
    cidade varchar(255),
    estado varchar(255),
    cep varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_medico;

CREATE TABLE tb_medico (
    id bigint not null auto_increment,
    nome varchar(255),
    sexo varchar(255),
    cd_especialidade bigint,
    data_registro date,
    data_demissao date,
    crm varchar(255),
    ativo boolean,
    rua varchar(255),
    numero varchar(255),
    distrito varchar(255),
    cidade varchar(255),
    estado varchar(255),
    cep varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_especialidade;

CREATE TABLE tb_especialidade (
    id bigint not null auto_increment,
    nome varchar(255),
    PRIMARY KEY (id)
);

DROP TABLE IF EXISTS tb_consulta;

CREATE TABLE tb_consulta (
    id bigint not null auto_increment,
    cd_medico bigint,
    cd_paciente bigint,
    status_consulta varchar(255),
    data_hora_consulta TIMESTAMP,
    valor double,
    descricao varchar(255),
    PRIMARY KEY (id)
);