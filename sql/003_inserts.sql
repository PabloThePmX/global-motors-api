-- TODO: ORDER ITEMS

-- 1. Insert into "brands"
INSERT INTO "brands" (name, description, country_origin, logo) VALUES
    ('Ferrari', 'Italian luxury sports car manufacturer.', 'IT', 'http://example.com/logos/ferrari.png'),
    ('Porsche', 'German automobile manufacturer specializing in high-performance sports cars, SUVs and sedans.', 'DE', 'http://example.com/logos/porsche.png'),
    ('Tesla', 'American electric vehicle and clean energy company.', 'US', 'http://example.com/logos/tesla.png'),
    ('BMW', 'German multinational corporation which produces luxury vehicles and motorcycles.', 'DE', 'http://example.com/logos/bmw.png'),
    ('Ford', 'American multinational automobile manufacturer.', 'US', 'http://example.com/logos/ford.png');

---

-- 2. Insert into "addresses"
INSERT INTO "addresses" (street, number, neighborhood, city, state, postal_code, complement) VALUES
    ('Avenida Paulista', 1000, 'Bela Vista', 'São Paulo', 'SP', '01310-100', 'Andar 10'),
    ('Rua da Praia', 50, 'Centro', 'Rio de Janeiro', 'RJ', '20090-000', NULL),
    ('Rua Treze de Maio', 200, 'Centro', 'Campinas', 'SP', '13010-070', 'Sala 2'),
    ('Avenida Salgado Filho', 300, 'Lagoa Nova', 'Natal', 'RN', '59056-000', NULL),
    ('Rua Boa Viagem', 450, 'Boa Viagem', 'Recife', 'PE', '51020-000', 'Loja 3');

---

-- 3. Insert into "users"
-- NOTE: In a real scenario, you'd link `current_address` using a subquery
-- to the `id` generated from the `addresses` table.
INSERT INTO "users" (email, password, role, phone_number, first_name, last_name, birthday, document_number, document_type, gender, current_address) VALUES
    ('admin@example.com', 'hashed_password_admin', 'Admin', '11987654321', 'Ana', 'Silva', '1985-01-15', '12345678900', 'Física', 'Feminino', (SELECT id FROM addresses WHERE street = 'Avenida Paulista' AND number = 1000)),
    ('john.doe@example.com', 'hashed_password_john', 'Normal', '21998765432', 'John', 'Doe', '1990-05-20', '98765432100', 'Física', 'Masculino', (SELECT id FROM addresses WHERE street = 'Rua da Praia' AND number = 50)),
    ('owner@example.com', 'hashed_password_owner', 'Owner', '19976543210', 'Carlos', 'Santos', '1978-11-10', '12345678000199', 'Jurídica', 'Masculino', (SELECT id FROM addresses WHERE street = 'Rua Treze de Maio' AND number = 200)),
    ('maria.souza@example.com', 'hashed_password_maria', 'Normal', '84987654321', 'Maria', 'Souza', '1992-03-25', '11223344555', 'Física', 'Feminino', (SELECT id FROM addresses WHERE street = 'Avenida Salgado Filho' AND number = 300)),
    ('pedro.costa@example.com', 'hashed_password_pedro', 'Normal', '81997654321', 'Pedro', 'Costa', '1988-09-01', '55443322111', 'Física', 'Masculino', (SELECT id FROM addresses WHERE street = 'Rua Boa Viagem' AND number = 450));

---

-- 4. Insert into "stores"
-- NOTE: Link `address` and `owner` to the `id`s generated from `addresses` and `users`.
INSERT INTO "stores" (name, address, cnpj, email, phone_number, website, description, owner, is_active) VALUES
    ('LuxCar SP', (SELECT id FROM addresses WHERE street = 'Avenida Paulista' AND number = 1000), '00000000000101', 'luxcar.sp@example.com', '1130001000', 'http://luxcarsp.com', 'Luxury car dealership in São Paulo.', (SELECT id FROM users WHERE email = 'owner@example.com'), TRUE),
    ('Rio Sports Cars', (SELECT id FROM addresses WHERE street = 'Rua da Praia' AND number = 50), '00000000000202', 'riosportscars@example.com', '2130002000', 'http://riosportscars.com', 'Premier sports car dealer in Rio de Janeiro.', (SELECT id FROM users WHERE email = 'admin@example.com'), TRUE),
    ('E-Drive Natal', (SELECT id FROM addresses WHERE street = 'Avenida Salgado Filho' AND number = 300), '00000000000303', 'edrive.natal@example.com', '8430003000', 'http://edrivenatal.com', 'Specializing in electric vehicles.', (SELECT id FROM users WHERE email = 'owner@example.com'), TRUE);

---

-- 5. Insert into "cars"
-- NOTE: Link `brand` and `store` to the `id`s generated from `brands` and `stores`.
INSERT INTO "cars" (price, currency, model, year, car_type, mileage, brand, store, worldwide_quantity, national_quantity, color, horse_power, category, doors, traction, car_configuration, shift, acceleration_to_hundred, torque, motorization, propulsion, is_available, default_picture) VALUES
    (350000.00, 'BRL', 'F8 Tributo', 2023, 'Novo', 150.00, (SELECT id FROM brands WHERE name = 'Ferrari'), (SELECT id FROM stores WHERE name = 'LuxCar SP'), 10, 2, 'Vermelho', 710, 'Esportivo', 2, 'Traseira', 'Coupé', 'Semi-Automático', 2.9, '770 Nm', 'V8 Twin-Turbo', 'Gasolina', TRUE, 'http://example.com/cars/f8_tributo.jpg'),
    (120000.00, 'USD', 'Model S Plaid', 2024, 'Novo', 50.00, (SELECT id FROM brands WHERE name = 'Tesla'), (SELECT id FROM stores WHERE name = 'E-Drive Natal'), 20, 5, 'Branco', 1020, 'Elétrico', 4, '4x4', 'Sedan', 'Automático', 1.99, '1420 Nm', 'Dual Motor', 'Elétrico', TRUE, 'http://example.com/cars/model_s_plaid.jpg'),
    (250000.00, 'BRL', '911 Carrera S', 2022, 'Novo', 1000.00, (SELECT id FROM brands WHERE name = 'Porsche'), (SELECT id FROM stores WHERE name = 'Rio Sports Cars'), 8, 3, 'Preto', 450, 'Esportivo', 2, 'Traseira', 'Coupé', 'Automático', 3.5, '530 Nm', 'Flat-Six Turbo', 'Gasolina', TRUE, 'http://example.com/cars/911_carrera_s.jpg'),
    (75000.00, 'USD', 'X5 xDrive40i', 2023, 'Novo', 250.00, (SELECT id FROM brands WHERE name = 'BMW'), (SELECT id FROM stores WHERE name = 'LuxCar SP'), 15, 4, 'Azul', 335, 'SUV', 5, '4x4', 'Sedan', 'Automático', 5.3, '450 Nm', 'Inline-6 Turbo', 'Gasolina', TRUE, 'http://example.com/cars/x5.jpg'),
    (60000.00, 'BRL', 'Mustang GT', 2021, 'Novo', 5000.00, (SELECT id FROM brands WHERE name = 'Ford'), (SELECT id FROM stores WHERE name = 'Rio Sports Cars'), 12, 6, 'Amarelo', 460, 'Esportivo', 2, 'Traseira', 'Coupé', 'Manual', 4.3, '569 Nm', 'V8', 'Gasolina', TRUE, 'http://example.com/cars/mustang_gt.jpg');

-- 1. Insert into "conversion_rate"
INSERT INTO "conversion_rate" (currency, rate, last_updated) VALUES
    ('USD', 1.00, '2025-06-30 17:45:00'),
    ('EUR', 0.92, '2025-06-30 17:45:00'),
    ('BRL', 5.40, '2025-06-30 17:45:00');

---

-- 2. Insert into "system_info"
INSERT INTO "system_info" (id, version) VALUES
    (1, '1.0.0');

---

-- 3. Insert into "user_settings" (linking to existing users)
INSERT INTO "user_settings" ("user", language, displayed_currency) VALUES
    ((SELECT id FROM users WHERE email = 'admin@example.com'), 'EN-US', 'EUR'),
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), 'PT-BR', 'BRL'),
    ((SELECT id FROM users WHERE email = 'owner@example.com'), 'EN-US', 'USD');

---

-- 4. Insert into "car_images" (linking to existing cars)
-- Note: car_images has an auto-incrementing 'id' but also relies on 'car' uuid.
INSERT INTO "car_images" (car, image) VALUES
    ((SELECT id FROM cars WHERE model = 'F8 Tributo'), 'http://example.com/cars/f8_tributo_side.jpg'),
    ((SELECT id FROM cars WHERE model = 'F8 Tributo'), 'http://example.com/cars/f8_tributo_interior.jpg'),
    ((SELECT id FROM cars WHERE model = 'Model S Plaid'), 'http://example.com/cars/model_s_plaid_front.jpg'),
    ((SELECT id FROM cars WHERE model = '911 Carrera S'), 'http://example.com/cars/911_carrera_s_rear.jpg'),
    ((SELECT id FROM cars WHERE model = 'Mustang GT'), 'http://example.com/cars/mustang_gt_engine.jpg');

---

-- 5. Insert into "favorite_cars" (linking to existing users and cars)
INSERT INTO "favorite_cars" ("user", car) VALUES
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), (SELECT id FROM cars WHERE model = 'Model S Plaid')),
    ((SELECT id FROM users WHERE email = 'admin@example.com'), (SELECT id FROM cars WHERE model = 'F8 Tributo')),
    ((SELECT id FROM users WHERE email = 'maria.souza@example.com'), (SELECT id FROM cars WHERE model = 'X5 xDrive40i'));

---

-- 6. Insert into "cart_items" (linking to existing users and cars)
INSERT INTO "cart_items" ("user", car) VALUES
    ((SELECT id FROM users WHERE email = 'pedro.costa@example.com'), (SELECT id FROM cars WHERE model = 'Mustang GT')),
    ((SELECT id FROM users WHERE email = 'john.doe@example.com'), (SELECT id FROM cars WHERE model = '911 Carrera S'));

---

-- 7. Insert into "orders" (linking to existing users)
INSERT INTO "orders" (order_number, final_price, buyer, status, delivered, payment_type) VALUES
    ('ORD-20250630-0001', 350000.00, (SELECT id FROM users WHERE email = 'john.doe@example.com'), 'Aguardando pagamento', FALSE, 'Pix'),
    ('ORD-20250630-0002', 120000.00, (SELECT id FROM users WHERE email = 'pedro.costa@example.com'), 'Pagamento confirmado', FALSE, 'Transferência Bancária'),
    ('ORD-20250630-0003', 250000.00, (SELECT id FROM users WHERE email = 'maria.souza@example.com'), 'Carro a caminho', FALSE, 'Débito');

---