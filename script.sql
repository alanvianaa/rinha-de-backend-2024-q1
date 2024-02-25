CREATE TABLE clientes
(
    id     SERIAL PRIMARY KEY,
    nome   VARCHAR(50) NOT NULL,
    limite INTEGER     NOT NULL,
    saldo  INTEGER     NOT NULL
);
CREATE INDEX idx_cliente_id ON clientes (id);

CREATE TABLE transacoes
(
    id           SERIAL PRIMARY KEY,
    id_cliente   INTEGER REFERENCES clientes (id) ON DELETE CASCADE,
    valor        INTEGER     NOT NULL,
    tipo         CHAR(1)     NOT NULL,
    descricao    VARCHAR(10) NOT NULL,
    realizada_em TIMESTAMP   NOT NULL DEFAULT NOW()
);
CREATE INDEX idx_transacoes_id_cliente ON transacoes (id_cliente);

DO
$$
    BEGIN
        INSERT INTO clientes (nome, limite, saldo)
        VALUES ('o barato sai caro', 1000 * 100, 0),
               ('zan corp ltda', 800 * 100, 0),
               ('les cruders', 10000 * 100, 0),
               ('padaria joia de cocaia', 100000 * 100, 0),
               ('kid mais', 5000 * 100, 0);
    END;
$$;
