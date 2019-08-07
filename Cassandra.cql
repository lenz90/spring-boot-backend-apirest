/*Creando la base de datos */

//CREATE KEYSPACE db_springboot_backend WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };

/*CREATE TABLE cliente ( 
id int, 
nombre text, 
apellido text,
email text, 
create_at timestamp,
region FROZEN<TUPLE<regiones_udt>>, 
PRIMARY KEY (id) 
);

CREATE TABLE ids (
	name text,
	code int,
	PRIMARY KEY (name)
);

CREATE TABLE regiones (
	id int,
	nombre text,
	PRIMARY KEY (id)
)*/

insert into regiones (id, nombre) values (1, 'Sudamérica');
insert into regiones (id, nombre) values (2, 'Centroamérica');
insert into regiones (id, nombre) values (3, 'Sudamérica');
insert into regiones (id, nombre) values (4, 'Norteamérica');
insert into regiones (id, nombre) values (5, 'Europa');
insert into regiones (id, nombre) values (6, 'Asia');
insert into regiones (id, nombre) values (7, 'Africa');
insert into regiones (id, nombre) values (8, 'Oceanía');
insert into regiones (id, nombre) values (9, 'Antartida');



CREATE TYPE regiones_udt (
	id int,
	nombre text
);




/*Data */

//drop table cliente;
//truncate cliente;
//drop table ids;
/*insert into cliente(id,nombre,apellido,email,create_at) values(1,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(2,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(3,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(4,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at,region) values(5,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()),({id:1,nombre:'Sudamérica'}));

*/


//insert into ids(name, code) values('cliente',5);

select * from regiones;
select * from cliente;
select * from ids;



insert into cliente(id,nombre,apellido,email,create_at) values(17,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(18,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(19,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(20,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(21,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(22,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(23,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(24,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(25,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(26,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(27,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(28,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(29,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(30,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(31,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(32,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(33,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(34,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(35,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(36,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(37,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(38,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(39,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(40,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(41,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(42,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(43,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(44,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(45,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(46,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(47,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(48,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(49,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(50,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(51,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(52,'Lizardo','Mamani','lenz.90@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(53,'Lucia','Mendoza','2@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(54,'Mauro','Perez','3@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(55,'Juana','Lopez','4@gmail.com',toTimeStamp(now()));
insert into cliente(id,nombre,apellido,email,create_at) values(56,'Pablo','Jacinto','5@gmail.com',toTimeStamp(now()));

INSERT INTO ids (name,code) VALUES ('cliente',56);
