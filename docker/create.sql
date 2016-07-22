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
  beg_time TIME,
  end_time TIME,
  street VARCHAR(255),
  street_numb INTEGER,
  zip INTEGER,
  phone VARCHAR(255),
  menu VARCHAR(255),
  PRIMARY KEY (id)
);

CREATE TABLE flavour (
  name VARCHAR(255),
  checked BOOLEAN,
  PRIMARY KEY (name)
);

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