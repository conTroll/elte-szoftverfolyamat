CREATE TABLE user_credentials (
 credential_id INT(10) NOT NULL,
 username VARCHAR(20) NOT NULL,
 password_hash VARCHAR(128) NOT NULL,
 enabled TINYINT(3) DEFAULT 1 NOT NULL
);

ALTER TABLE user_credentials ADD CONSTRAINT PK_user_credentials PRIMARY KEY (credential_id);
SET FOREIGN_KEY_CHECKS = 0;
alter table user_credentials CHANGE credential_id credential_id INT( 10 ) not null AUTO_INCREMENT ;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE user_profile_data (
 credential_id INT(10) NOT NULL,
 email VARCHAR(128) NOT NULL,
 full_name VARCHAR(128) NOT NULL,
 short_name VARCHAR(32) NOT NULL,
 public_habitat BIT(1) DEFAULT 1 NOT NULL,
 habitat VARCHAR(128) NOT NULL,
 public_job_and_workplace BIT(1) DEFAULT 1 NOT NULL,
 job VARCHAR(128) NOT NULL,
 workplace VARCHAR(128),
 public_birthday BIT(1) DEFAULT 1 NOT NULL,
 birthday DATE NOT NULL
);

ALTER TABLE user_profile_data ADD CONSTRAINT PK_user_profile_data PRIMARY KEY (credential_id);


CREATE TABLE user_roles (
 user_role_id INT(10) NOT NULL,
 role VARCHAR(45) NOT NULL,
 credential_id INT(10) NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT PK_user_roles PRIMARY KEY (user_role_id);
SET FOREIGN_KEY_CHECKS = 0;
alter table user_roles CHANGE user_role_id user_role_id INT( 10 ) not null AUTO_INCREMENT ;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE advertisements (
 advertisement_id INT(10) NOT NULL,
 title VARCHAR(128) NOT NULL,
 link VARCHAR(128),
 text TEXT NOT NULL,
 author_credential_id INT(10) NOT NULL,
 expire_date DATE NOT NULL
);

ALTER TABLE advertisements ADD CONSTRAINT PK_advertisements PRIMARY KEY (advertisement_id);


CREATE TABLE channel_profile_data (
 credential_id INT(10) NOT NULL,
 email VARCHAR(128) NOT NULL,
 name VARCHAR(128) NOT NULL,
 description VARCHAR(256) NOT NULL,
 open BIT(0) DEFAULT 1 NOT NULL
);

ALTER TABLE channel_profile_data ADD CONSTRAINT PK_channel_profile_data PRIMARY KEY (credential_id);


CREATE TABLE channel_subscribers (
 channel_credential_id INT(10) NOT NULL,
 subscriber_credential_id INT(10) NOT NULL
);

ALTER TABLE channel_subscribers ADD CONSTRAINT PK_channel_subscribers PRIMARY KEY (channel_credential_id,subscriber_credential_id);

CREATE TABLE posts (
 post_id INT(10) NOT NULL,
 text TEXT NOT NULL,
 author_credential_id INT(10) NOT NULL
);

ALTER TABLE posts ADD CONSTRAINT PK_posts PRIMARY KEY (post_id);
alter table posts CHANGE post_id post_id INT( 10 ) not null AUTO_INCREMENT;
ALTER TABLE posts ADD COLUMN creation_date DATETIME NOT NULL;


CREATE TABLE user_connections (
 credential_id_1 INT(10) NOT NULL,
 credential_id_2 INT(10) NOT NULL
);

ALTER TABLE user_connections ADD CONSTRAINT PK_user_connections PRIMARY KEY (credential_id_1,credential_id_2);


CREATE TABLE comments (
 comment_id INT(10) NOT NULL,
 text TEXT NOT NULL,
 post_id INT(10) NOT NULL,
 author_credential_id INT(10) NOT NULL
);

ALTER TABLE comments ADD CONSTRAINT PK_comments PRIMARY KEY (comment_id);
alter table comments CHANGE comment_id comment_id INT( 10 ) not null AUTO_INCREMENT;
ALTER TABLE comments ADD COLUMN creation_date DATETIME NOT NULL;


CREATE TABLE messages (
  id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  text TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  user_from INT(10) NOT NULL,
  user_to INT(10) NOT NULL,
  status VARCHAR(16)
);

ALTER TABLE user_profile_data ADD CONSTRAINT FK_user_profile_data_0 FOREIGN KEY (credential_id) REFERENCES user_credentials (credential_id);


ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_0 FOREIGN KEY (credential_id) REFERENCES user_credentials (credential_id);

ALTER TABLE advertisements ADD CONSTRAINT FK_advertisements_0 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);


ALTER TABLE channel_profile_data ADD CONSTRAINT FK_channel_profile_data_0 FOREIGN KEY (credential_id) REFERENCES user_credentials (credential_id);


ALTER TABLE channel_subscribers ADD CONSTRAINT FK_channel_subscribers_0 FOREIGN KEY (channel_credential_id) REFERENCES channel_profile_data (credential_id);
ALTER TABLE channel_subscribers ADD CONSTRAINT FK_channel_subscribers_1 FOREIGN KEY (subscriber_credential_id) REFERENCES user_profile_data (credential_id);

ALTER TABLE posts ADD CONSTRAINT FK_posts_0 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);


ALTER TABLE user_connections ADD CONSTRAINT FK_user_connections_0 FOREIGN KEY (credential_id_1) REFERENCES user_profile_data (credential_id);
ALTER TABLE user_connections ADD CONSTRAINT FK_user_connections_1 FOREIGN KEY (credential_id_2) REFERENCES user_profile_data (credential_id);


ALTER TABLE comments ADD CONSTRAINT FK_comments_0 FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE comments ADD CONSTRAINT FK_comments_1 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);

ALTER TABLE messages ADD CONSTRAINT fk_messages_from FOREIGN KEY (user_from) REFERENCES user_profile_data (credential_id);
ALTER TABLE messages ADD CONSTRAINT fk_messages_to   FOREIGN KEY (user_to)   REFERENCES user_profile_data (credential_id);
