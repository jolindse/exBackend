ALTER TABLE users DROP COLUMN role;
ALTER TABLE users ADD COLUMN enabled TINYINT(4) NOT NULL DEFAULT '1';

CREATE TABLE user_roles(
  user_roles_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  role varchar(20) NOT NULL DEFAULT 'ROLE_USER',
  user_id INT(20),

  FOREIGN KEY (user_id) REFERENCES users(id)
);

INSERT INTO users(user_name, password, enabled)
     VALUES('admin', 'admin', 1);

INSERT INTO user_roles(role, user_id) VALUES (
  'ROLE_ADMIN',
  (SELECT id FROM users WHERE user_name = 'admin')
);
