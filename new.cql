INSERT INTO cliente (apellido,create_at,email,foto,id,nombre,region) VALUES ('Sanchez',1556686800000,'lenz.90@gmail.com',null,57,'Lizardo',{id:7,nombre:'Africa'});


select * from cliente;
ALTER TABLE db_springboot_backend.cliente
alter region TYPE FROZEN<regiones_udt> allow alter;

ALTER TABLE db_springboot_backend.cliente
add region FROZEN<regiones_udt>;


INSERT INTO cliente (id,apellido,create_at,email,foto,nombre,region) VALUES (23,'Mendoza',1558585552305,'2@gmail.com',null,'Lucia',{'id':7,'nombre':'Africa'});
-----------------------------------------
----Usuario autenticacion

CREATE TABLE ids (
	name text,
	code int,
	PRIMARY KEY (name)
);