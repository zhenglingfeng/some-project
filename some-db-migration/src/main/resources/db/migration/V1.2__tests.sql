CREATE TABLE IF NOT EXISTS `tests`    (
  `id`      INT(11)         NOT NULL    AUTO_INCREMENT,
  `remark`    VARCHAR(50)    NOT NULL,
  `content`    VARCHAR(50)    NOT NULL,
  PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;
