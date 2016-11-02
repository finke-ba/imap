CREATE TABLE towns (
  id       INTEGER PRIMARY KEY,
  city     VARCHAR(32),
  ru_city  VARCHAR(32)
);

CREATE TABLE boiler (
  id       INT(6),
  name     VARCHAR(64),
  town     INT(6) REFERENCES towns(id),
  address  VARCHAR(64),
  tel      VARCHAR(16)
);