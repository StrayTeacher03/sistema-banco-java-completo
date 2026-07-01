ALTER TABLE IF EXISTS usuarios ALTER COLUMN senha TYPE VARCHAR(128);

INSERT INTO usuarios (login, senha, nome, perfil)
VALUES ('KeslleyAntonio', 'eb239d50750205b27c1924d573028fe5ba6f5c7e19f05ce1bcbd3871d9f282af', 'Keslley Antonio de Almeida Dornelas Junior', 'ADMIN')
ON CONFLICT (login) DO UPDATE SET senha = EXCLUDED.senha, nome = EXCLUDED.nome, perfil = EXCLUDED.perfil;