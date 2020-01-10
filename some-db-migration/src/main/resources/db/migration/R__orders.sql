CREATE TABLE IF NOT EXISTS `orders`    (
  `id`      INT(11)         NOT NULL    AUTO_INCREMENT,
  `remark`    VARCHAR(50)    NOT NULL,
  PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8;

INSERT INTO orders (id, remark)
SELECT 1, 'remark' FROM dual
WHERE not exists (select * from orders where orders.id = 1);
