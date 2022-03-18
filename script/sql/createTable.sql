use gatherthem;

create table user (
    id UUID primary key not null,
    username varchar(24) not null unique,
    password varchar(1000) not null,
    email varchar(320) not null unique
);

create table authority (
    code varchar(10) primary key not null,
    name varchar(50) not null
);

create table user_authority (
    userId UUID not null,
    authorityCode varchar(10) not null,
    foreign key (userId) references user(id),
    foreign key (authorityCode) references authority(code)
);

insert into user(id, username, password, email)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'Administrateur', '$2a$10$dcjsEDgBzJ8GHntLWbELh.ct8viol8oMFb6uyHysL8H8dr173Lx3K', 'admin@gatherthem.fr'); /* Mot de passe : exemple */

insert into authority(code, name)
values
    ('ADMIN', 'Administrateur'),
    ('PREMIUM', 'Premium');

insert into user_authority(userId, authorityCode)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'ADMIN'),
    ('a3387036-4946-11ec-81d3-0242ac130003', 'PREMIUM');