create table if not exists tb_cliente(
	id_cliente int(5) not null auto_increment,
    nm_cliente varchar(60) not null,
    phone varchar(11) not null,
    email varchar(60) not null,
	constraint primary key(id_cliente)
);