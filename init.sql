CREATE TABLE clientes
(
    id     SERIAL PRIMARY KEY,
    nome   VARCHAR(50) NOT NULL,
    limite INTEGER     NOT NULL
);

CREATE TABLE transacoes
(
    id           SERIAL PRIMARY KEY,
    id_cliente   INTEGER REFERENCES clientes ON DELETE CASCADE,
    valor        INTEGER     NOT NULL,
    tipo         CHAR(1)     NOT NULL,
    descricao    VARCHAR(10) NOT NULL,
    realizada_em TIMESTAMP   NOT NULL DEFAULT NOW()
);

CREATE TABLE saldos
(
    id_cliente INTEGER PRIMARY KEY REFERENCES clientes ON DELETE CASCADE,
    valor      INTEGER NOT NULL
);

DO
$$
    BEGIN
        INSERT INTO clientes (nome, limite)
        VALUES ('o barato sai caro', 1000 * 100),
               ('zan corp ltda', 800 * 100),
               ('les cruders', 10000 * 100),
               ('padaria joia de cocaia', 100000 * 100),
               ('kid mais', 5000 * 100);
    END;
$$
