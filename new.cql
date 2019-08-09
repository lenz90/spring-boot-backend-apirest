INSERT INTO cliente (apellido,create_at,email,foto,id,nombre,region) VALUES ('Sanchez',1556686800000,'lenz.90@gmail.com',null,57,'Lizardo',{id:7,nombre:'Africa'});


select * from cliente;
ALTER TABLE db_springboot_backend.cliente
alter region TYPE FROZEN<regiones_udt> allow alter;

ALTER TABLE db_springboot_backend.cliente
add region FROZEN<regiones_udt>;


INSERT INTO cliente (id,apellido,create_at,email,foto,nombre,region) VALUES (23,'Mendoza',1558585552305,'2@gmail.com',null,'Lucia',{'id':7,'nombre':'Africa'});
-----------------------------------------
----Usuarios autenticacion


CREATE TYPE role_udt (
	role text,
	detail text
);


CREATE TYPE user_udt (
	user text,
	status text
);

CREATE TABLE roles (
	role text,
	detail text,
	users set<frozen<user_udt>>,
	PRIMARY KEY (role)
);


CREATE TABLE usuarios (
	user text,
	password text,
	status text,
	role set<frozen<role_udt>>,
	PRIMARY KEY (user)
);


insert into roles (role, detail) values ('ROLE_USER','');
insert into roles (role, detail) values ('ROLE_ADMIN','');


insert into usuarios (user, password, role, status) values
('lizardo', '$2a$10$dBqsr4iATdls0.lJuolcROGd4MfO9Bcf2i81qQhfODqpcRL2RahQS', 
	{{role:'ROLE_USER',detail:''}}, 'ENABLED'
);

insert into usuarios (user, password, role, status) values
('admin', '$2a$10$OXCz38iv0qVqdAWw1uRA4.Y2Hz/spk/Uc8MxhN8bVEgGbSR5RuM1.', {{role:'ROLE_USER',detail:''},{role:'ROLE_ADMIN',detail:''}}, 'ENABLED');


select * from usuarios;