create table pet_types
(
    id          serial primary key,
    animal_type varchar(50)
);

create table owners
(
    uuid      varchar(36) primary key,
    firstname varchar(50),
    lastname  varchar(50),
    address   varchar(50)
);
create index owners_names on owners (lastname);

create table pets
(
    uuid          varchar(36) primary key,
    name          varchar(50),
    pet_type_id   integer references pet_types (id),
    owner_uuid    varchar(36) references owners (uuid),
    date_of_birth date
);
create index pets_names on pets (name);

create table visits
(
    uuid        varchar(36) primary key,
    visit_date  timestamp,
    pet_uuid    varchar(36) references pets (uuid),
    description varchar(200)
);
create index visits_dates on visits (visit_date);
