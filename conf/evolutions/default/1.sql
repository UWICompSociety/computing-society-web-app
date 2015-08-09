# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  coordinate_id             bigint,
  constraint uq_address_coordinate_id unique (coordinate_id),
  constraint pk_address primary key (id))
;

create table coordinate (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  latitude                  double,
  longitude                 double,
  constraint pk_coordinate primary key (id))
;

create table profile (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  first_name                varchar(255),
  last_name                 varchar(255),
  nickname                  varchar(255),
  birthday                  datetime(6),
  user_id                   bigint,
  address_id                bigint,
  constraint uq_profile_user_id unique (user_id),
  constraint uq_profile_address_id unique (address_id),
  constraint pk_profile primary key (id))
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
  verified                  tinyint(1) default 0,
  constraint pk_user primary key (id))
;

alter table address add constraint fk_address_coordinate_1 foreign key (coordinate_id) references coordinate (id) on delete restrict on update restrict;
create index ix_address_coordinate_1 on address (coordinate_id);
alter table profile add constraint fk_profile_user_2 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_profile_user_2 on profile (user_id);
alter table profile add constraint fk_profile_address_3 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_profile_address_3 on profile (address_id);
alter table role_user add constraint fk_role_user_role_4 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_role_user_role_4 on role_user (role_id);
alter table role_user add constraint fk_role_user_user_5 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_role_user_user_5 on role_user (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table coordinate;

drop table profile;

drop table role;

drop table role_user;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

