INSERT INTO user_ (id, enabled, username, password) VALUES (1, TRUE, 'user1', '123456');
INSERT INTO user_ (id, enabled, username, password) VALUES (2, FALSE, 'user2', '123456');
INSERT INTO user_ (id, enabled, username, password) VALUES (3, TRUE, 'user3', '123456');

INSERT INTO city (id, name) VALUES (1, 'Da Nang');
INSERT INTO city (id, name) VALUES (2, 'HCM');
INSERT INTO city (id, name) VALUES (3, 'Ha Noi');
INSERT INTO city (id, name) VALUES (4, 'Can Tho');
INSERT INTO city (id, name) VALUES (5, 'Hai Phong');

INSERT INTO user_city (user_id, city_id) VALUES (1, 1);
INSERT INTO user_city (user_id, city_id) VALUES (1, 2);
INSERT INTO user_city (user_id, city_id) VALUES (1, 3);
INSERT INTO user_city (user_id, city_id) VALUES (2, 1);
INSERT INTO user_city (user_id, city_id) VALUES (2, 3);
INSERT INTO user_city (user_id, city_id) VALUES (3, 1);
INSERT INTO user_city (user_id, city_id) VALUES (3, 2);
INSERT INTO user_city (user_id, city_id) VALUES (3, 3);
INSERT INTO user_city (user_id, city_id) VALUES (3, 4);
