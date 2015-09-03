# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table address (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  coordinate_id             bigint,
  constraint uq_address_coordinate_id unique (coordinate_id),
  constraint pk_address primary key (id))
;

create table contribution (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  amount                    integer,
  profile_id                bigint,
  constraint pk_contribution primary key (id))
;

create table coordinate (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  latitude                  double,
  longitude                 double,
  constraint pk_coordinate primary key (id))
;

create table event (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  name                      varchar(255),
  start_time                timestamp,
  end_time                  timestamp,
  rating                    integer,
  constraint pk_event primary key (id))
;

create table event_place (
  id                        bigint not null,
  deleted                   boolean,
  event_id                  bigint,
  place_id                  bigint,
  description               varchar(255),
  constraint pk_event_place primary key (id))
;

create table event_type (
  id                        bigint not null,
  deleted                   boolean,
  type_id                   bigint,
  event_id                  bigint,
  constraint pk_event_type primary key (id))
;

create table event_user (
  id                        bigint not null,
  deleted                   boolean,
  user_id                   bigint,
  event_id                  bigint,
  description               varchar(255),
  constraint pk_event_user primary key (id))
;

create table place (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  name                      varchar(255),
  address_id                bigint,
  constraint uq_place_address_id unique (address_id),
  constraint pk_place primary key (id))
;

create table profile (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  first_name                varchar(255),
  last_name                 varchar(255),
  registration_number       varchar(255),
  nickname                  varchar(255),
  birthday                  timestamp,
  user_id                   bigint,
  address_id                bigint,
  constraint uq_profile_user_id unique (user_id),
  constraint uq_profile_address_id unique (address_id),
  constraint pk_profile primary key (id))
;

create table role (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_role primary key (id))
;

create table role_user (
  id                        bigint not null,
  deleted                   boolean,
  role_id                   bigint,
  user_id                   bigint,
  description               varchar(255),
  constraint pk_role_user primary key (id))
;

create table type (
  id                        bigint not null,
  deleted                   boolean,
  name                      varchar(255),
  constraint pk_type primary key (id))
;

create table users (
  id                        bigint not null,
  deleted                   boolean,
  created_at                timestamp,
  updated_at                timestamp,
  email                     varchar(255) not null,
  token                     varchar(255),
  username                  varchar(255),
  password                  varchar(255),
  verified                  boolean,
  constraint pk_users primary key (id))
;

create sequence address_seq;

create sequence contribution_seq;

create sequence coordinate_seq;

create sequence event_seq;

create sequence event_place_seq;

create sequence event_type_seq;

create sequence event_user_seq;

create sequence place_seq;

create sequence profile_seq;

create sequence role_seq;

create sequence role_user_seq;

create sequence type_seq;

create sequence users_seq;

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
alter table event_user add constraint fk_event_user_user_7 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_event_user_user_7 on event_user (user_id);
alter table event_user add constraint fk_event_user_event_8 foreign key (event_id) references event (id) on delete restrict on update restrict;
create index ix_event_user_event_8 on event_user (event_id);
alter table place add constraint fk_place_address_9 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_place_address_9 on place (address_id);
alter table profile add constraint fk_profile_user_10 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_profile_user_10 on profile (user_id);
alter table profile add constraint fk_profile_address_11 foreign key (address_id) references address (id) on delete restrict on update restrict;
create index ix_profile_address_11 on profile (address_id);
alter table role_user add constraint fk_role_user_role_12 foreign key (role_id) references role (id) on delete restrict on update restrict;
create index ix_role_user_role_12 on role_user (role_id);
alter table role_user add constraint fk_role_user_user_13 foreign key (user_id) references users (id) on delete restrict on update restrict;
create index ix_role_user_user_13 on role_user (user_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists address;

drop table if exists contribution;

drop table if exists coordinate;

drop table if exists event;

drop table if exists event_place;

drop table if exists event_type;

drop table if exists event_user;

drop table if exists place;

drop table if exists profile;

drop table if exists role;

drop table if exists role_user;

drop table if exists type;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists address_seq;

drop sequence if exists contribution_seq;

drop sequence if exists coordinate_seq;

drop sequence if exists event_seq;

drop sequence if exists event_place_seq;

drop sequence if exists event_type_seq;

drop sequence if exists event_user_seq;

drop sequence if exists place_seq;

drop sequence if exists profile_seq;

drop sequence if exists role_seq;

drop sequence if exists role_user_seq;

drop sequence if exists type_seq;

drop sequence if exists users_seq;

