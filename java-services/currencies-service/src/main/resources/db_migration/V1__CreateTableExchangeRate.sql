CREATE TYPE "currencies" AS ENUM (
  'USD',
  'EUR',
  'BRL'
);

CREATE TABLE "exchange_rate" (
  "currency_source" currencies,
  "currency_target" currencies,
  "rate" decimal(5,2),
  "last_updated" timestamp,
  PRIMARY KEY("currency_source", "currency_target")
);