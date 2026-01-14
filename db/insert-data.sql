insert into pet_types(animal_type)
values ('dog');
insert into pet_types(animal_type)
values ('cat');
insert into pet_types(animal_type)
values ('rabbit');
insert into pet_types(animal_type)
values ('guinea pig');
insert into pet_types(animal_type)
values ('iguana');
insert into pet_types(animal_type)
values ('turtle');

insert into owners(uuid, firstname, lastname, address)
values (gen_random_uuid(), 'Marketa', 'Solanikova', 'My address 2, 04001 Kosice');
insert into owners(uuid, firstname, lastname, address)
values (gen_random_uuid(), 'Dagmar', 'Struckelova', 'Other address 340, 01142 Zemplinska Teplica');
insert into owners(uuid, firstname, lastname, address)
values (gen_random_uuid(), 'Janko', 'Hrasko', 'Zelena 23, 05574 Presov');

insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Izzy', '2024-04-07', 1, uuid
from owners
where lastname = 'Solanikova';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Ziva', '2015-06-21', 1, uuid
from owners
where lastname = 'Struckelova';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Laga', '2023-10-08', 1, uuid
from owners
where lastname = 'Struckelova';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Drobec', '2024-04-07', 6, uuid
from owners
where lastname = 'Struckelova';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Strucik', '2024-04-07', 2, uuid
from owners
where lastname = 'Hrasko';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Luskacik', '2024-04-07', 4, uuid
from owners
where lastname = 'Hrasko';
insert into pets(uuid, name, date_of_birth, pet_type_id, owner_uuid)
select gen_random_uuid(), 'Fazulka', '2024-04-07', 3, uuid
from owners
where lastname = 'Hrasko';

insert into visits(uuid, visit_date, pet_uuid, description)
select gen_random_uuid(), '2025-01-05 10:30:00', uuid, 'Ockovanie'
from pets
where name = 'Ziva';
insert into visits(uuid, visit_date, pet_uuid, description)
select gen_random_uuid(), '2025-01-05 10:30:00', uuid, 'Ockovanie'
from pets
where name = 'Laga';
