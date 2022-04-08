use gatherthem;

create table user
(
    user_id  UUID primary key not null,
    username varchar(24)      not null unique,
    password varchar(1000)    not null,
    email    varchar(320)     not null unique
);

create table authority
(
    authority_code varchar(10) primary key not null,
    name           varchar(50)             not null
);

create table user_authority
(
    user_id        UUID        not null,
    authority_code varchar(10) not null,
    foreign key (user_id) references user (user_id),
    foreign key (authority_code) references authority (authority_code)
);

create table template
(
    id              UUID primary key not null,
    name            varchar(50)      not null,
    description     varchar(1000),
    item_label_name varchar(50)      not null,
    visibility      varchar(10)      not null,
    parent_id       UUID,
    foreign key (parent_id) references template (id),
    owner_id         UUID             not null,
    foreign key (owner_id) references user (user_id)
);

create table property
(
    id          UUID primary key not null,
    name        varchar(50)      not null,
    type        varchar(10)      not null,
    template_id UUID             not null,
    foreign key (template_id) references template (id)
);

create table collection
(
    id            UUID primary key not null,
    name          varchar(50)      not null,
    description   varchar(1000),
    image         varchar(1000),
    creation_date DATE default CURRENT_DATE,
    owner_id       UUID             not null,
    foreign key (owner_id) references user (user_id),
    template_id   UUID             /*not null TODO add template*/,
    foreign key (template_id) references template (id)
);

create table item
(
    id             UUID primary key not null,
    label          varchar(50)      not null,
    creation_date  DATE default CURRENT_DATE,
    obtention_date DATE             not null,
    collection_id  UUID             not null,
    foreign key (collection_id) references collection (id)
);

create table item_property
(
    id          UUID primary key not null,
    value       varchar(1000)    not null,
    property_id UUID             not null,
    foreign key (property_id) references property (id),
    item_id     UUID             not null,
    foreign key (item_id) references item (id)
);

insert into user(user_id, username, password, email)
values ('a3387036-4946-11ec-81d3-0242ac130003', 'Administrateur',
        '$2a$10$dcjsEDgBzJ8GHntLWbELh.ct8viol8oMFb6uyHysL8H8dr173Lx3K',
        'admin@gatherthem.fr'); /* Mot de passe : exemple */

insert into authority(authority_code, name)
values ('ADMIN', 'Administrateur'),
       ('PREMIUM', 'Premium');

insert into user_authority(user_id, authority_code)
values ('a3387036-4946-11ec-81d3-0242ac130003', 'ADMIN'),
       ('a3387036-4946-11ec-81d3-0242ac130003', 'PREMIUM');

insert into template(id, name, description, item_label_name, visibility, owner_id)
values ('cec5f7b6-cfe3-4368-978c-22bb3010bf1f', 'Livre', '', 'Titre', 'PUBLIC', 'a3387036-4946-11ec-81d3-0242ac130003'),
       ('b54d5c56-e6d4-4dff-8f9d-b8d727838b35', 'Film', '', 'Titre', 'PUBLIC', 'a3387036-4946-11ec-81d3-0242ac130003');

insert into template(id, name, description, item_label_name, visibility, parent_id, owner_id)
values ('804a1231-e398-452d-8d0c-ce660383a8d3', 'DVD', '', 'Titre', 'PUBLIC',
        'b54d5c56-e6d4-4dff-8f9d-b8d727838b35', 'a3387036-4946-11ec-81d3-0242ac130003'),
       ('2b3baadf-baa4-4de6-b386-f85bdc1e0c3d', 'Blu-ray', '', 'Titre', 'PUBLIC',
        'b54d5c56-e6d4-4dff-8f9d-b8d727838b35', 'a3387036-4946-11ec-81d3-0242ac130003');

insert into property(id, name, type, template_id)
values
    /* Properties de Livre */
    ('4e9f8a75-bf23-4e48-986a-02a809fd59e6', 'Auteur', 'STRING', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    ('f267d75e-c2e4-4701-a539-5bb191736207', 'Editeur', 'STRING', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    ('66913731-e7b0-4fcc-82c7-08ddffb768c6', 'Date de sortie', 'DATE', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    ('b563504f-f7fa-4c86-8d14-2e7d3b7f3679', 'Genre', 'STRING', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    ('ced5456f-6110-4356-b9ec-ba1ff7d9c704', 'Nombre de pages', 'INTEGER', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    ('0875de54-3992-411e-8787-c2010bcc73fe', 'Langue', 'STRING', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
    /* Properties de Film */
    ('eecfc1d0-e964-4e51-a39d-8364b44a932f', 'Auteur', 'STRING', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35'),
    ('7166959a-bafb-4413-8355-ef78252b2f9c', 'Editeur', 'STRING', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35'),
    ('ad743ce0-2109-4b63-a4c6-c3876cd2e182', 'Date de sortie', 'DATE', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35'),
    ('0f9595cb-b819-460c-bb89-2e7f6d0f47bc', 'Genre', 'STRING', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35'),
    ('04257a99-b1c8-4ad5-9cca-f05afe0f7972', 'Durée', 'TIME', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35'),
    ('a719204d-46bd-43e7-a34b-12f59e42e65c', 'Langue', 'STRING', 'b54d5c56-e6d4-4dff-8f9d-b8d727838b35');

insert into collection(id, name, description, image, owner_id, template_id)
values ('014edff9-8a67-4ce2-9f68-88b3e94f7171', 'Mes livres de fantasy', 'Et même que ils sont magiques mes livres', '',
        'a3387036-4946-11ec-81d3-0242ac130003', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f'),
       ('c79343b8-835e-4a92-98ae-3de3593912b7', 'Mes livres en plastique', 'Ils flottent', '', 'a3387036-4946-11ec-81d3-0242ac130003', 'cec5f7b6-cfe3-4368-978c-22bb3010bf1f');

insert into item(id, label, obtention_date, collection_id)
values ('d2cb5219-dbf2-4feb-b84d-0e2965160c82', concat('Harry Potter à l', char(39), 'école des sorcies'), '2018-03-02', '014edff9-8a67-4ce2-9f68-88b3e94f7171');

insert into item_property(id, value, property_id, item_id)
values
    ('d7bcae41-aa78-49a1-8402-975824cfb409', 'J. K. Rowling', '4e9f8a75-bf23-4e48-986a-02a809fd59e6', 'd2cb5219-dbf2-4feb-b84d-0e2965160c82'),
    ('da8a05d3-11d2-4e30-a289-fe495bbd18f3', 'Gallimard jeunesse', 'f267d75e-c2e4-4701-a539-5bb191736207', 'd2cb5219-dbf2-4feb-b84d-0e2965160c82'),
    ('29073894-ff8e-40a5-a202-dc38e97b5465', '2017-10-12', '66913731-e7b0-4fcc-82c7-08ddffb768c6', 'd2cb5219-dbf2-4feb-b84d-0e2965160c82'),
    ('4911371b-aba9-4822-a0e0-a9657628f164', '320', 'ced5456f-6110-4356-b9ec-ba1ff7d9c704', 'd2cb5219-dbf2-4feb-b84d-0e2965160c82');