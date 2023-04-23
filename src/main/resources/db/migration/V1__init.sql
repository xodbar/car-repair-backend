create table client_categories
(
    id       bigserial    not null,
    discount integer      not null,
    name     varchar(255) not null,
    primary key (id)
);

create table clients
(
    id                 bigserial    not null,
    first_name         varchar(255) not null,
    last_name          varchar(255) not null,
    phone              varchar(255) not null,
    client_category_id bigint       not null,
    primary key (id)
);

create table employee
(
    id                  bigserial    not null,
    avatar_url          varchar(255) not null,
    employee_grade      varchar(255),
    first_name          varchar(255) not null,
    is_active           boolean      not null,
    last_name           varchar(255) not null,
    vehicle_category_id bigint       not null,
    work_category_id    bigint       not null,
    primary key (id)
);

create table vehicle_categories
(
    id              bigserial    not null,
    additional_cost integer      not null,
    name            varchar(255) not null,
    primary key (id)
);

create table vehicles
(
    id                  bigserial    not null,
    issue_year          integer      not null,
    license_plate       varchar(255) not null,
    model_name          varchar(255) not null,
    owner_id            bigint       not null,
    vehicle_category_id bigint       not null,
    primary key (id)
);

create table work_category
(
    id   bigserial    not null,
    name varchar(255) not null,
    primary key (id)
);

create table works
(
    id               bigserial not null,
    actual_end_date  date,
    planned_end_date date      not null,
    total_price      integer   not null,
    employee_id      bigint    not null,
    vehicle_id       bigint    not null,
    work_category_id bigint    not null,
    primary key (id)
);

alter table if exists client_categories
    add constraint UK_83qq6wy161damvu6idu5qxo2t unique (name);

alter table if exists clients
    add constraint UK_e3it7h0veoeergtkfqxhos5qj unique (phone);

alter table if exists vehicle_categories
    add constraint UK_bjkbp9k0wqcuuuohueja4y06m unique (name);

alter table if exists vehicles
    add constraint UK_9vovnbiegxevdhqfcwvp2g8pj unique (license_plate);

alter table if exists work_category
    add constraint UK_s11sme0xddwwdgowsyhy11oku unique (name);

alter table if exists clients
    add constraint FKopcnp8lfq2eyjj9cbfpuo4y0v foreign key (client_category_id) references client_categories;

alter table if exists employee
    add constraint FKhutc9uyluh3ngbvylpnty5c70 foreign key (vehicle_category_id) references vehicle_categories;

alter table if exists employee
    add constraint FKgf5j80pfamm9a8dgqcerj7goc foreign key (work_category_id) references work_category;

alter table if exists vehicles
    add constraint FKorialero2nslrlj5w7qvx4x60 foreign key (owner_id) references clients;

alter table if exists vehicles
    add constraint FK58qf8rkm9ce9hoxiaf041kh7s foreign key (vehicle_category_id) references vehicle_categories;

alter table if exists works
    add constraint FKtfkx8tgh06pkif7m2cfxfro0v foreign key (employee_id) references employee;

alter table if exists works
    add constraint FKfadyaigbo82plah66senjs5ft foreign key (vehicle_id) references vehicles;

alter table if exists works
    add constraint FK69ye4m61vhhqdnwax7ehpqxsk foreign key (work_category_id) references work_category;
