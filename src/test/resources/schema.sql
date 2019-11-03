drop table transaction if exists;

create table transaction (
     id bigint auto_increment,
     amount_buy decimal(19,2),
     amount_sell decimal(19,2),
     currency_from varchar(255),
     currency_to varchar(255),
     originating_country varchar(255),
     rate double precision,
     time_placed datetime not null,
     user_id bigint not null,
     primary key (id)) engine=MyISAM