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

create table contribution (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  amount                    integer,
  profile_id                bigint,
  constraint pk_contribution primary key (id))
;

create table coordinate (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  latitude                  double,
  longitude                 double,
  constraint pk_coordinate primary key (id))
;

create table event (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  name                      varchar(255),
  start_time                datetime(6),
  end_time                  datetime(6),
  status                    varchar(255),
  rating                    integer,
  constraint pk_event primary key (id))
;

create table event_place (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  event_id                  bigint,
  place_id                  bigint,
  description               varchar(255),
  constraint pk_event_place primary key (id))
;

create table event_type (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  type_id                   bigint,
  event_id                  bigint,
  constraint pk_event_type primary key (id))
;

create table event_user (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  user_id                   bigint,
  event_id                  bigint,
  description               varchar(255),
  constraint pk_event_user primary key (id))
;

create table place (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  name                      varchar(255),
  short_name                varchar(255),
  address_id                bigint,
  constraint uq_place_address_id unique (address_id),
  constraint pk_place primary key (id))
;

create table profile (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  first_name                varchar(255),
  last_name                 varchar(255),
  registration_number       varchar(255),
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
  description               varchar(255),
  constraint pk_role primary key (id))
;

create table role_user (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  role_id                   bigint,
  user_id                   bigint,
  constraint pk_role_user primary key (id))
;

create table type (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  name                      varchar(255),
  constraint pk_type primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  deleted                   tinyint(1) default 0,
  email                     varchar(255) not null,
  token                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  verified                  tinyint(1) default 0,
  constraint pk_user primary key (id))
;

alter table address add constraint fk_address_coordinate_1 foreign key (coordinate_id) references coordinate (id) on delete restrict on update restrict;
create index ix_address_coordinate_1 on address (coordinate_id);
alter table contribution add constraint fk_contribution_profile_2 foreign key (profile_id) references profile (id) on delete restrict on update restrict;
create index ix_contribution_profile_2 on contribution (profile_id);
alter table event_place add constraint fk_event_place_event_3 foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_event_place_event_3 on event_place (event_id);
alter table event_place add constraint fk_event_place_place_4 foreign key (place_id) references place (id) on delete restrict on update restrict;
create index ix_event_place_place_4 on event_place (place_id);
alter table event_type add constraint fk_event_type_type_5 foreign key (type_id) references type (id) on delete restrict on update restrict;
create index ix_event_type_type_5 on event_type (type_id);
alter table event_type add constraint fk_event_type_event_6 foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_event_type_event_6 on event_type (event_id);
alter table event_user add constraint fk_event_user_user_7 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_event_user_user_7 on event_user (user_id);
alter table event_user add constraint fk_event_user_event_8 foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_event_user_event_8 on event_user (event_id);
alter table place add constraint fk_place_address_9 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_place_address_9 on place (address_id);
alter table profile add constraint fk_profile_user_10 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_profile_user_10 on profile (user_id);
alter table profile add constraint fk_profile_address_11 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_profile_address_11 on profile (address_id);
alter table role_user add constraint fk_role_user_role_12 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_role_user_role_12 on role_user (role_id);
alter table role_user add constraint fk_role_user_user_13 foreign key (user_id) references user (id) on delete restrict on update restrict;
create index ix_role_user_user_13 on role_user (user_id);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table address;

drop table contribution;

drop table coordinate;

drop table event;

drop table event_place;

drop table event_type;

drop table event_user;

drop table place;

drop table profile;

drop table role;

drop table role_user;

drop table type;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

