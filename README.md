# Autenticacion y crud de usuarios Data Service Layer 
#Mysql mariadb 15
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `first_name` varchar (100) NOT NULL, 
  `last_name` varchar(100) NOT NULL, 
  `date_birth` date NOT NULL, 
  `address` varchar(150) NOT NULL, 
  `token` varchar(200) DEFAULT NULL, 
  `password` varchar(120) NOT NULL,
  `mobile_phone` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

##insert inicial usuario de pruebas
INSERT INTO `test`.`user` (`username`, `first_name`, `last_name`, `date_birth`, `address`, `password`, `mobile_phone`, `email`, `active`) VALUES ('dmotta', 'david', 'motta', '1986-12-20', 'kra 72 # 53a-18', '$2a$12$mVccf1.K8DXLzouwisrKlelpO.UgPuVgone58CbylevylD2Jv6z6u', '3103278750', 'david.motta21@gmail.com', b'1');

