ALTER TABLE users DROP COLUMN role;
SET FOREIGN_KEY_CHECKS = 0;

CREATE TABLE user_roles (
  `user_role_id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `user_id` INT(20) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  FOREIGN KEY(`user_id`) REFERENCES `users`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX fk_username_idx ON `user_roles`(`user_id`);
CREATE UNIQUE INDEX uni_username_role ON `user_roles`(`role`, `user_id`);

ALTER TABLE users ADD enabled TINYINT(4) DEFAULT '1' NOT NULL;

INSERT INTO users(user_name, password, enabled) VALUES ('admin', 'admin', 1);
INSERT INTO user_roles(user_id, role) VALUES (
  (SELECT id from users WHERE user_name = 'admin'),'ROLE_ADMIN');


