CREATE TABLE user
(
  id       BIGINT      NOT NULL
  COMMENT '主键'
    PRIMARY KEY,
  email    VARCHAR(50) NULL
  COMMENT '邮箱',
  username VARCHAR(20) NOT NULL
  COMMENT '用户名',
  password VARCHAR(16) NOT NULL
  COMMENT '密码',
  ctime    BIGINT      NOT NULL,
  mtime    BIGINT      NOT NULL,
  CONSTRAINT user_username_uindex
  UNIQUE (username)
)
  COMMENT '用户表'
  ENGINE = InnoDB;