-- Users
CREATE TABLE user_credentials (
 credential_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 username VARCHAR(20) NOT NULL,
 password_hash VARCHAR(128) NOT NULL,
 enabled TINYINT(3) DEFAULT 1 NOT NULL
);

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
 birthday DATE NOT NULL,
 avatar LONGBLOB
);

ALTER TABLE user_profile_data ADD CONSTRAINT PK_user_profile_data PRIMARY KEY (credential_id);
ALTER TABLE user_profile_data ADD CONSTRAINT FK_user_profile_data_0 FOREIGN KEY (credential_id) REFERENCES user_credentials (credential_id);

CREATE TABLE user_roles (
 user_role_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 role VARCHAR(45) NOT NULL,
 credential_id INT(10) NOT NULL
);

ALTER TABLE user_roles ADD CONSTRAINT FK_user_roles_0 FOREIGN KEY (credential_id) REFERENCES user_credentials (credential_id);

-- Advertisements
CREATE TABLE advertisements (
 advertisement_id INT(10) NOT NULL,
 title VARCHAR(128) NOT NULL,
 link VARCHAR(128),
 text TEXT NOT NULL,
 author_credential_id INT(10) NOT NULL,
 expire_date DATE NOT NULL
);

ALTER TABLE advertisements ADD CONSTRAINT PK_advertisements PRIMARY KEY (advertisement_id);
ALTER TABLE advertisements ADD CONSTRAINT FK_advertisements_0 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);

-- Channels
CREATE TABLE channel_profile_data (
 channel_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 leader_credential_id INT(10) NOT NULL,
 name VARCHAR(128) NOT NULL,
 description VARCHAR(256) NOT NULL,
 open BIT(0) DEFAULT 1 NOT NULL,
 creation_date DATETIME NOT NULL
);

ALTER TABLE channel_profile_data ADD CONSTRAINT FK_channel_profile_data_leader FOREIGN KEY (leader_credential_id) REFERENCES user_credentials (credential_id);

CREATE TABLE channel_subscribers (
 channel_id INT(10) NOT NULL,
 subscriber_credential_id INT(10) NOT NULL,
 subscriber_status VARCHAR(63) NOT NULL,
 subscription_date DATETIME NOT NULL
);

ALTER TABLE channel_subscribers ADD CONSTRAINT PK_channel_subscribers PRIMARY KEY (channel_id, subscriber_credential_id);
ALTER TABLE channel_subscribers ADD CONSTRAINT FK_channel_subscribers_subscriber FOREIGN KEY (subscriber_credential_id) REFERENCES user_profile_data (credential_id);

CREATE TABLE channel_posts (
 channel_post_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 channel_id INT(10) NOT NULL,
 text TEXT NOT NULL,
 creation_date DATETIME NOT NULL
);

ALTER TABLE channel_posts ADD CONSTRAINT FK_channel_posts_channel FOREIGN KEY (channel_id) REFERENCES channel_profile_data (channel_id);

CREATE TABLE channel_post_comments (
 channel_post_comment_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 channel_post_id INT(10) NOT NULL,
 author_credential_id INT(10) NOT NULL,
 text TEXT NOT NULL,
 creation_date DATETIME NOT NULL
);

ALTER TABLE channel_post_comments ADD CONSTRAINT FK_channel_post_comments_post FOREIGN KEY (channel_post_id) REFERENCES channel_posts (channel_post_id);
ALTER TABLE channel_post_comments ADD CONSTRAINT FK_channel_post_comments_author FOREIGN KEY (author_credential_id) REFERENCES user_profile_data (credential_id);

-- Posts
CREATE TABLE posts (
 post_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 text TEXT NOT NULL,
 author_credential_id INT(10) NOT NULL,
 creation_date DATETIME NOT NULL
);

ALTER TABLE posts ADD CONSTRAINT FK_posts_0 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);

CREATE TABLE comments (
 comment_id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
 text TEXT NOT NULL,
 post_id INT(10) NOT NULL,
 author_credential_id INT(10) NOT NULL,
 creation_date DATETIME NOT NULL
);

ALTER TABLE comments ADD CONSTRAINT FK_comments_0 FOREIGN KEY (post_id) REFERENCES posts (post_id);
ALTER TABLE comments ADD CONSTRAINT FK_comments_1 FOREIGN KEY (author_credential_id) REFERENCES user_credentials (credential_id);

-- Connections
CREATE TABLE user_connections (
 credential_id_1 INT(10) NOT NULL,
 credential_id_2 INT(10) NOT NULL
);

ALTER TABLE user_connections ADD CONSTRAINT PK_user_connections PRIMARY KEY (credential_id_1,credential_id_2);
ALTER TABLE user_connections ADD CONSTRAINT FK_user_connections_0 FOREIGN KEY (credential_id_1) REFERENCES user_profile_data (credential_id);
ALTER TABLE user_connections ADD CONSTRAINT FK_user_connections_1 FOREIGN KEY (credential_id_2) REFERENCES user_profile_data (credential_id);

-- Messages
CREATE TABLE messages (
  id INT(10) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  text TEXT NOT NULL,
  created_at TIMESTAMP NOT NULL,
  user_from INT(10) NOT NULL,
  user_to INT(10) NOT NULL,
  status VARCHAR(16),
  is_viewed bit(1) DEFAULT 0 NOT NULL
);

ALTER TABLE messages ADD CONSTRAINT fk_messages_from FOREIGN KEY (user_from) REFERENCES user_profile_data (credential_id);
ALTER TABLE messages ADD CONSTRAINT fk_messages_to   FOREIGN KEY (user_to)   REFERENCES user_profile_data (credential_id);
