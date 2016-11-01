DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (
  id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  mobile varchar(15) DEFAULT NULL COMMENT '手机号',
  password varchar(100) DEFAULT NULL COMMENT '密码',
  status int(2) DEFAULT NULL COMMENT '状态',
  icon varchar(255) DEFAULT NULL COMMENT '头像',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '用户表';
