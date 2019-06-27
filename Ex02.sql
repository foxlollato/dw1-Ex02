create table ex2_perfil(
perfil_id number primary key,
perfil varchar(40)
);

insert into ex2_perfil values(1,'aluno');
insert into ex2_perfil values(2,'professor');
insert into ex2_perfil values(3,'administrador');

create table ex2_usuario(
nome varchar(40),
email varchar(40) primary key,
senha varchar(40),
perfil_id number,
ativa number default 0,
constraint perfil_fk foreign key (perfil_id) references ex2_perfil(perfil_id)
);

commit;

