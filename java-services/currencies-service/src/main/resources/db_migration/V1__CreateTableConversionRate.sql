CREATE TYPE "currencies" AS ENUM (
  'USD',
  'EUR',
  'BRL'
);

CREATE TABLE "conversion_rate" (
  "currency" currencies PRIMARY KEY,
  "rate" decimal(5,2),
  "last_updated" timestamp
);