create extension if not exists "uuid-ossp";
create table if not exists member (id uuid default uuid_generate_v4(), name varchar(64) not null);
