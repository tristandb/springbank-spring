--
-- Database: `springbank_test_suite`
--

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `bank_account`
--

CREATE TABLE bank_account
(
  account_id     BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  balance        DOUBLE             NOT NULL,
  holder_user_id BIGINT             NOT NULL
);
CREATE INDEX FK8jud4i10ibvbti0xtla8e88h5
  ON bank_account (holder_user_id);

--
-- Gegevens worden geëxporteerd voor tabel `bank_account`
--

INSERT INTO bank_account (account_id, balance, holder_user_id) VALUES
  (6, 238322, 3),
  (7, 23, 4),
  (8, 1000000000, 4);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `card`
--

CREATE TABLE card
(
  card_id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  card_number     VARCHAR(255)       NOT NULL,
  expiration_date DATE               NOT NULL,
  pin             VARCHAR(255)       NOT NULL,
  bank_account_id BIGINT             NOT NULL,
  user_id         BIGINT             NOT NULL
);
CREATE UNIQUE INDEX UK9kj2dtyu7a4w0jh34p95o6yqd
  ON card (bank_account_id, card_number);
CREATE INDEX FKl4gbym62l738id056y12rt6q6
  ON card (user_id);

--
-- Gegevens worden geëxporteerd voor tabel `card`
--

INSERT INTO card (card_number, expiration_date, pin, bank_account_id, user_id) VALUES
  ('0901', '2022-07-05', '5434', 6, 3),
  ('6095', '2022-07-05', '6957', 7, 4),
  ('9053', '2022-07-05', '1671', 8, 4),
  ('5630', '2022-08-08', '3465', 7, 3);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `iban`
--

CREATE TABLE iban
(
  iban_id         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  iban            VARCHAR(255)       NOT NULL,
  bank_account_id BIGINT             NOT NULL
);
CREATE UNIQUE INDEX UK_2w6dmssco96gvn3l6b4igkul4
  ON iban (iban);
CREATE UNIQUE INDEX UK8abuopppa8rev5cy06ayc82u5
  ON iban (bank_account_id, iban);
CREATE UNIQUE INDEX UK_pm6uut04sgelgdkhgb90a0tn7
  ON iban (bank_account_id);

--
-- Gegevens worden geëxporteerd voor tabel `iban`
--

INSERT INTO iban (iban, bank_account_id) VALUES
  ('NL86SPRI0752582963', 6),
  ('NL83SPRI0114480386', 7),
  ('NL15SPRI0749536255', 8);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `transaction`
--

CREATE TABLE transaction
(
  transaction_id    BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  amount            DOUBLE             NOT NULL,
  date              DATETIME           NOT NULL,
  message           VARCHAR(255),
  target_name       VARCHAR(255),
  source_account_id BIGINT,
  target_account_id BIGINT             NOT NULL
);
CREATE INDEX FK14pfnxb2ly7iuu3w84q3nhmin
  ON transaction (source_account_id);
CREATE INDEX FK4wmdsx0mrxc47wfv1wpi78u1
  ON transaction (target_account_id);

--
-- Gegevens worden geëxporteerd voor tabel `transaction`
--

INSERT INTO transaction (amount, date, message, target_name, source_account_id, target_account_id) VALUES
  (100, '2017-08-08 00:00:00', 'Hier heb je geld', 'J. Jannes', 7, 6),
  (10, '2017-08-02 00:00:00', 'Doe er iets leuks mee', 'K. Ham', 6, 7),
  (50, '2017-07-02 00:00:00', 'cadeau', 'H. Oost', 7, 8),
  (25, '2017-08-01 00:00:00', 'dinges', 'H. Oost', 6, 8);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user`
--

CREATE TABLE user
(
  user_id          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
  bsn              VARCHAR(255)       NOT NULL,
  date_of_birth    DATE               NOT NULL,
  email            VARCHAR(255)       NOT NULL,
  initials         VARCHAR(255)       NOT NULL,
  name             VARCHAR(255)       NOT NULL,
  password         VARCHAR(255)       NOT NULL,
  street_address   VARCHAR(255)       NOT NULL,
  surname          VARCHAR(255)       NOT NULL,
  telephone_number VARCHAR(255)       NOT NULL,
  username         VARCHAR(255)       NOT NULL
);
CREATE UNIQUE INDEX UK_e9nea19uafpjcwt3920ksylhf
  ON user (bsn);
CREATE UNIQUE INDEX UK_sb8bbouer5wak8vyiiy4pf2bx
  ON user (username);

--
-- Gegevens worden geëxporteerd voor tabel `user`
--

INSERT INTO user (bsn, date_of_birth, email, initials, name, password, street_address, surname, telephone_number, username)
VALUES
  ('32432435', '1954-10-14', 'd@d.nl', 'B.', 'Bernard', 'dd', 'Yffiniac', 'Hinault', '0612345678',
   'bernard@hinault.fr'),
  ('123456789', '1970-01-01', 'info@svenkonings.nl', 'S.', 'Sven', 'sven', 'Brink 123, Ons Dorp', 'Konings',
   '0612345678', 'sven@konings.nl'),
  ('435456553', '1947-12-01', 'xyz@test.nl', 'D.', 'Dagobert', 'dagdag', 'Pakhuislaan 1, 1234AB Duckstad', 'Duck',
   '05312312312', 'dagobertduck@gmail.com'),
  ('571376046', '1954-02-19', 'donald@gmail.com', 'D', 'Duck', 'kwikkwekkwak', '1313 Webfoot Walk, Duckburg', 'Donald',
   '+316 12345678', 'duckd');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `user_bank_account`
--

CREATE TABLE user_bank_account
(
  user_id    BIGINT NOT NULL,
  account_id BIGINT NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (account_id, user_id)
);
CREATE INDEX FKjn71dv46t5vlrjbqroobqfxcl
  ON user_bank_account (user_id);

--
-- Gegevens worden geëxporteerd voor tabel `user_bank_account`
--

INSERT INTO user_bank_account (user_id, account_id) VALUES
  (3, 6),
  (3, 7),
  (4, 7),
  (4, 8);
