create table exchange_value (id bigint not null, conversion_multiple numeric(19,2), currency_from varchar(255), port integer not null, currency_to varchar(255), primary key (id));


INSERT INTO exchange_value (id, currency_from, currency_to, conversion_multiple, port) VALUES (1001, 'USD', 'INR', 73, 0);
INSERT INTO exchange_value (id, currency_from, currency_to, conversion_multiple, port) VALUES (1002, 'EUR', 'INR', 87, 0);
INSERT INTO exchange_value (id, currency_from, currency_to, conversion_multiple, port) VALUES (1003, 'AED', 'INR', 20, 0);
