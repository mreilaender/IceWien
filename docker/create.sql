-- Start machine
-- docker run -d --name test -v /abs/path/to/repo/docker:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=icewien -e MYSQL_USER=icewien -e MYSQL_PASSWORD=icewien mysql
--
-- notes
-- -----
-- SHA-256 instead of md5 for hashing password
USE icewien;

CREATE TABLE parlor (
  id INTEGER AUTO_INCREMENT,
  name VARCHAR(255),
  latitude DECIMAL(10,8),
  longitude DECIMAL(10,8),
  opening_time TIME,
  closing_time TIME,
  street VARCHAR(255),
  street_numb INTEGER,
  zip INTEGER,
  phone VARCHAR(255),
  menu VARCHAR(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE flavour (
  name VARCHAR(255),
  checked BOOLEAN DEFAULT FALSE,
  PRIMARY KEY (name)
);

INSERT INTO flavour (name, checked) VALUES ('Vanille', TRUE), ('Haselnuss', TRUE), ('Fiocco', TRUE), ('Cacao', TRUE), ('Cookie', TRUE);
INSERT INTO flavour (name, checked) VALUES ('Zitrone', TRUE), ('Stracciatella', TRUE), ('Cranberry', TRUE), ('Mango-Creme', TRUE), ('Raffaello', TRUE);
INSERT INTO flavour (name, checked) VALUES ('Kaffee', TRUE), ('Malaga', TRUE), ('Nocciolone', TRUE), ('Pistazie', TRUE);
INSERT INTO flavour (name, checked) VALUES ('Marille', TRUE), ('Amarena', TRUE), ('Banane', TRUE), ('Erdbeer', TRUE);

CREATE TABLE user (
  uid INTEGER AUTO_INCREMENT,
  email VARCHAR(255),
  username VARCHAR(255),
  password BINARY(64),
  isAdmin BOOLEAN,
  PRIMARY KEY (uid)
);

CREATE TABLE vote (
  date TIMESTAMP,
  ranking INTEGER(5),
  uid INTEGER,
  PRIMARY KEY (date, uid),
  FOREIGN KEY (uid) REFERENCES user (uid)
);