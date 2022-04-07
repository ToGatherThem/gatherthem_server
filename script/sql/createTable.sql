use gatherthem;

create table user (
    user_id UUID primary key not null,
    username varchar(24) not null unique,
    password varchar(1000) not null,
    email varchar(320) not null unique
);

create table authority (
    authority_code varchar(10) primary key not null,
    name varchar(50) not null
);

create table user_authority (
    user_id UUID not null,
    authority_code varchar(10) not null,
    foreign key (user_id) references user(user_id),
    foreign key (authority_code) references authority(authority_code)
);

create table template (
    id UUID primary key not null,
    name varchar(50) not null,
    description varchar(1000) not null,
    item_label_name varchar(50) not null,
    visibility varchar(10) not null,
    user_id UUID not null,
    foreign key (user_id) references user(user_id)
);

create table property (
    id UUID primary key not null,
    name varchar(50) not null,
    type varchar(10) not null,
    template_id UUID not null,
    foreign key (template_id) references template(id)
);

create table collection (
    id UUID primary key not null,
    name varchar(50) not null,
    description varchar(1000) not null,
    image varchar(1000) not null,
    creation_date DATE not null,
    user_id UUID not null,
    foreign key (user_id) references user(user_id),
    template_id UUID not null,
    foreign key (template_id) references template(id)
);

create table item (
    id UUID primary key not null,
    label varchar(50) not null,
    creation_date DATE not null,
    obtention_date DATE not null,
    collection_id UUID not null,
    foreign key (collection_id) references collection(id)
);

create table item_property(
    id UUID primary key not null,
    value varchar(1000) not null,
    property_id UUID not null,
    foreign key (property_id) references property(id),
    item_id UUID not null,
    foreign key (item_id) references item(id)
);

insert into user(user_id, username, password, email)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'Administrateur', '$2a$10$dcjsEDgBzJ8GHntLWbELh.ct8viol8oMFb6uyHysL8H8dr173Lx3K', 'admin@gatherthem.fr'); /* Mot de passe : exemple */

insert into authority(authority_code, name)
values
    ('ADMIN', 'Administrateur'),
    ('PREMIUM', 'Premium');

insert into user_authority(user_id, authority_code)
values
    ('a3387036-4946-11ec-81d3-0242ac130003', 'ADMIN'),
    ('a3387036-4946-11ec-81d3-0242ac130003', 'PREMIUM');