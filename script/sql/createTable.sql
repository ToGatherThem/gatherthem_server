use gatherthem;

create table User (
    id UUID primary key not null,
    username varchar(24) not null unique,
    password varchar(1000) not null,
    mail varchar(320) not null unique
);

create table Authority (
    code varchar(10) primary key not null,
    name varchar(50) not null
);

create table UserAuthority (
    userId UUID not null,
    authorityCode varchar(10) not null,
    foreign key (userId) references User(id),
    foreign key (authorityCode) references Authority(code)
);

insert into User(id, username, password, mail)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'Administrateur', 'password', 'admin@gatherthem.fr');

insert into Authority(code, name)
values
    ('ADMIN', 'Administrateur'),
    ('PREMIUM', 'Premium');

insert into UserAuthority(userId, authorityCode)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'ADMIN'),
    ('a3387036-4946-11ec-81d3-0242ac130003', 'PREMIUM');