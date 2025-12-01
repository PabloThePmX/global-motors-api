CREATE TABLE "exchange_rate" (
  "currency_source" int,
  "currency_target" int,
  "rate" decimal(5,2),
  "last_updated" timestamp,
  PRIMARY KEY("currency_source", "currency_target")
);