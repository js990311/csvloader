CREATE TABLE test_users
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    age   INTEGER,
    email VARCHAR(255),
    name  VARCHAR(255),
    test_korean VARCHAR(255)
);
