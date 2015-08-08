# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table person (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  first_name                varchar(255),
  last_name                 varchar(255),
  constraint pk_person primary key (id))
;

create table role (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  name                      varchar(255),
  constraint pk_role primary key (id))
;

create table role_user (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  role_id                   bigint,
  user_id                   bigint,
  constraint pk_role_user primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

alter table role_user add constraint fk_role_user_role_1 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_role_user_role_1 on role_user (role_id);
alter table role_user add constraint fk_role_user_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_role_user_user_2 on role_user (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table person;

drop table role;

drop table role_user;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

