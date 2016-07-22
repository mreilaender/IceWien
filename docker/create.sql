-- Start machine
-- docker run -d --name test -v /abs/path/to/repo/docker/files:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=admin -e MYSQL_DATABASE=icewien -e MYSQL_USER=icewien -e MYSQL_PASSWORD=icewien mysql
--
USE icewien;

CREATE TABLE test1 (
  col1 INTEGER
);

INSERT INTO test1 VALUES (1);