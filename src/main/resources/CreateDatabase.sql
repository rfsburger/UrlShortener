CREATE DATABASE /*!32312 IF NOT EXISTS*/`urlshortener` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `urlshortener`;

CREATE TABLE `urls` (

  `shortUrl` int NOT NULL AUTO_INCREMENT,
  `longUrl` varchar(250) NOT NULL,
--   might be too short, I just read that the longest URL is actually over 4000 chars long, so not sure how to handle this best

  PRIMARY KEY (`shortUrl`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
