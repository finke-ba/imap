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

CREATE TABLE sensors (
  id_co     INT(10),
  boiler_id INT(6) REFERENCES boiler(id)
);

CREATE TABLE actual_param_values (
  id_pd     INT(6),
  par_value VARCHAR(16),
  date_time DATETIME,
  id_co     INT(10),
  par_name  VARCHAR(16),
  par_dim   VARCHAR(8),
  par_num   INT(4)
);