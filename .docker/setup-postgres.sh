psql postgresql://postgres@localhost/postgres < setup-postgres.sql \
  && psql postgresql://postgres@localhost/coreapi_communications -c "CREATE EXTENSION IF NOT EXISTS pg_trgm;"