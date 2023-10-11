ALTER TABLE consulta_tb
    ADD COLUMN  created_at     TIMESTAMP NOT NULL,
    ADD COLUMN  deleted_at     TIMESTAMP,
    ADD COLUMN  updated_at     TIMESTAMP NOT NULL