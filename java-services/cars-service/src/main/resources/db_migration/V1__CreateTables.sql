CREATE TABLE "cars" (
  "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
  "price" decimal(12,2) NOT NULL,
  "currency" int NOT NULL,
  "model" varchar(255) NOT NULL,
  "year" int NOT NULL,
  "car_type" int NOT NULL,
  "mileage" decimal(8,2),
  "brand" uuid NOT NULL,
  "store" uuid NOT NULL,
  "worldwide_quantity" int,
  "national_quantity" int,
  "color" int NOT NULL,
  "horse_power" int NOT NULL,
  "category" int NOT NULL,
  "doors" int NOT NULL,
  "traction" int NOT NULL,
  "car_configuration" int NOT NULL,
  "shift" int NOT NULL,
  "acceleration_to_hundred" decimal(10,2),
  "torque" varchar(100),
  "motorization" varchar(100),
  "propulsion" int NOT NULL,
  "is_available" boolean DEFAULT true,
  "default_picture" varchar(255),
  "last_updated" timestamp DEFAULT (now())
);

CREATE TABLE "brands" (
  "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
  "name" varchar(100) NOT NULL,
  "description" varchar(255),
  "country_origin" int NOT NULL,
  "logo" varchar(255),
  "last_updated" timestamp DEFAULT (now())
);

CREATE TABLE "stores" (
  "id" uuid PRIMARY KEY DEFAULT (gen_random_uuid()),
  "name" varchar(255) NOT NULL,
  "address" uuid,
  "cnpj" varchar(14) UNIQUE NOT NULL,
  "email" varchar(150) UNIQUE NOT NULL,
  "phone_number" varchar(11),
  "website" varchar(255),
  "description" varchar(255),
  "owner" uuid NOT NULL,
  "is_active" boolean NOT NULL,
  "last_updated" timestamp DEFAULT (now())
);

CREATE TABLE "car_images" (
  "car" uuid,
  "id" int,
  "image" varchar(255) NOT NULL,
  PRIMARY KEY ("car", "id")
);

ALTER TABLE "cars" ADD FOREIGN KEY ("brand") REFERENCES "brands" ("id");

ALTER TABLE "cars" ADD FOREIGN KEY ("store") REFERENCES "stores" ("id");

ALTER TABLE "car_images" ADD FOREIGN KEY ("car") REFERENCES "cars" ("id");

CREATE SEQUENCE car_images_seq
START WITH 1
INCREMENT BY 1;