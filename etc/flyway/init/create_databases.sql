CREATE DATABASE cvdb_backend;
CREATE USER cvdb_backend WITH PASSWORD 'cvdb_backend';
GRANT ALL PRIVILEGES ON DATABASE cvdb_backend to cvdb_backend;

CREATE DATABASE cvdb_backend_test;
CREATE USER cvdb_backend_test WITH PASSWORD 'cvdb_backend_test';
GRANT ALL PRIVILEGES ON DATABASE cvdb_backend_test to cvdb_backend_test;