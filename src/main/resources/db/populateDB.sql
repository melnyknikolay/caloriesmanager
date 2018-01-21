DELETE FROM user_roles;
DELETE FROM meals;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (user_id, description, calories) VALUES
  (100000, 'завтрак', '200'),
  (100000, 'обед', '500'),
  (100000, 'ужин', '300'),
  (100001, 'завтрак', '150'),
  (100001, 'обед','1600'),
  (100001, 'ужин', '700');