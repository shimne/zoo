CREATE TABLE ZOOPU_USER
(
	id BIGINT(20) NOT NULL AUTO_INCREMENT,
	username VARCHAR(20) NOT NULL DEFAULT '',
	password VARCHAR(100) NOT NULL DEFAULT '',
	idNumber VARCHAR(20) NOT NULL DEFAULT '',
	mobile VARCHAR(20) NOT NULL DEFAULT '',
	familyName VARCHAR(10) NOT NULL DEFAULT '',
	lastName VARCHAR(10) NOT NULL DEFAULT '',
	sex VARCHAR(2) NOT NULL DEFAULT '',
	birthday VARCHAR(15) NOT NULL DEFAULT '',
	qq VARCHAR(20) NOT NULL DEFAULT '',
	email VARCHAR(50) NOT NULL DEFAULT '',
	state VARCHAR(20) NOT NULL DEFAULT '',
	city VARCHAR(20) NOT NULL DEFAULT '',
	area VARCHAR(20) NOT NULL DEFAULT '',
	address VARCHAR(200) NOT NULL DEFAULT '',
	currentstate VARCHAR(20) NOT NULL DEFAULT '',
	currentcity VARCHAR(20) NOT NULL DEFAULT '',
	currentarea VARCHAR(20) NOT NULL DEFAULT '',
	currentaddress VARCHAR(200) NOT NULL DEFAULT '',
	postcode VARCHAR(20) NOT NULL DEFAULT '',
	photo VARCHAR(200) NOT NULL DEFAULT '',
	open TINYINT(1) NOT NULL DEFAULT 0,
	disable TINYINT(1) NOT NULL DEFAULT 0,
	registerTime BIGINT(20) NOT NULL DEFAULT 0,
	updateTime BIGINT(20) NOT NULL DEFAULT 0,
	primary key (id)
)ENGINE=MyISAM AUTO_INCREMENT=1 DEFAULT CHARSET=gbk;

ALTER TABLE `ZOOPU_USER`  ADD UNIQUE INDEX `username` (`username`);