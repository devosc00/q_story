---# --- Created by Ebean DDL
---# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table company (
  id                        bigint not null,
  c_name                    varchar(255),
  orders                    integer,
  constraint pk_company primary key (id))
;

create table history (
  id                        bigint not null,
  name			    varchar(255),
  t_parts                   integer,
  saldo			    integer,
  dates                     timestamp,
  order_counter             integer,
  t_material		    float,
  project_id                bigint,
  constraint pk_history primary key (id))
;

create table material (
  id                        bigint not null,
  m_name                    varchar(255),
  amount                    float,
  t_amount                  float,
  dates                     timestamp,
  constraint pk_material primary key (id))
;

create table project (
  id                        bigint not null,
  name                      varchar(255),
  end_date                  timestamp,
  order_                    integer,
  done_parts                integer,
  mat_count                 float,
  company_id                bigint,
  material_id               bigint ,
  constraint pk_project primary key (id))
;

create sequence company_seq start with 100;

create sequence history_seq start with 100;

create sequence material_seq start with 100;

create sequence project_seq start with 100;


alter table history add constraint fk_history_project_1 foreign key (project_id) references project (id);
create index ix_history_project_1 on history (project_id);
alter table project add constraint fk_project_company_2 foreign key (company_id) references company (id);
create index ix_project_company_2 on project (company_id);
alter table project add constraint fk_project_material_3 foreign key (material_id) references material (id);
create index ix_project_material_3 on project (material_id);



# --- !Downs

drop table if exists company cascade;

drop table if exists history cascade;

drop table if exists material cascade;

drop table if exists project cascade;

drop sequence if exists company_seq;

drop sequence if exists history_seq;

drop sequence if exists material_seq;

drop sequence if exists project_seq;

