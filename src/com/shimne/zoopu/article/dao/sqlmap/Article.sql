CREATE TABLE ZOOPU_CHANNEL
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	parentId BIGINT(20) NOT NULL DEFAULT '',
	name VARCHAR(50) NOT NULL DEFAULT '',
	description VARCHAR(100) DEFAULT '',
	creatorId BIGINT(20) NOT NULL DEFAULT 0,
	createTime BIGINT(20) NOT NULL DEFAULT 0,
	updaterId BIGINT(20) NOT NULL DEFAULT 0,
	updateTime BIGINT(20) NOT NULL DEFAULT 0,
	primary key (id)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=gbk;

CREATE TABLE ZOOPU_ARTICLE
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	channelId BIGINT(20) NOT NULL DEFAULT 0,
	title VARCHAR(100) NOT NULL DEFAULT '',
	subTitle VARCHAR(100) NOT NULL DEFAULT '',
	keywords VARCHAR(100) NOT NULL DEFAULT '',
	content text,
	summary VARCHAR(500) NOT NULL DEFAULT '',
	author VARCHAR(20) NOT NULL DEFAULT '',
	source VARCHAR(100) NOT NULL DEFAULT '',
	outUrl VARCHAR(100) NOT NULL DEFAULT '',
	icon VARCHAR(100) NOT NULL DEFAULT '',
	articleTime VARCHAR(20) NOT NULL DEFAULT '',
	top TINYINT(1) NOT NULL DEFAULT 0,
	status TINYINT(1) NOT NULL DEFAULT 0,
	creatorId BIGINT(20) NOT NULL DEFAULT 0,
	createTime BIGINT(20) NOT NULL DEFAULT 0,
	updaterId BIGINT(20) NOT NULL DEFAULT 0,
	updateTime BIGINT(20) NOT NULL DEFAULT 0,
	primary key (id)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=gbk;