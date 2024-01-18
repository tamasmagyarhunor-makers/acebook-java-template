DROP TABLE IF EXISTS test_table;

CREATE TABLE test_table (
  id bigserial PRIMARY KEY,
  test_title varchar(50) NOT NULL,
  test_content varchar(50) NOT NULL,
  test_boolean boolean NOT NULL
);