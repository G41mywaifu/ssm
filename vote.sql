/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2021/3/12 22:04:40                           */
/*==============================================================*/


drop table if exists tb_admin;

drop table if exists tb_answer_opt;

drop table if exists tb_answer_txt;

drop table if exists tb_question;

drop table if exists tb_question_opt;

drop table if exists tb_survey;

/*==============================================================*/
/* Table: tb_admin                                              */
/*==============================================================*/
create table tb_admin
(
   id                   int(11) not null auto_increment,
   account              varchar(50),
   password             varchar(50),
   name                 varchar(50),
   phone                varchar(50),
   remark               varchar(200),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_answer_opt                                         */
/*==============================================================*/
create table tb_answer_opt
(
   id                   int(11) not null auto_increment,
   survey_id            int(11),
   question_id          int(11),
   opt_id               int(11),
   type                 varchar(20) comment '1radio|2checkbox',
   create_time          datetime,
   voter                varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_answer_txt                                         */
/*==============================================================*/
create table tb_answer_txt
(
   id                   int(11) not null auto_increment,
   survey_id            int(11),
   question_id          int(11),
   result               varchar(200),
   create_time          datetime,
   voter                varchar(50),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_question                                           */
/*==============================================================*/
create table tb_question
(
   id                   int(11) not null auto_increment,
   title                varchar(200),
   remark               varchar(200),
   type                 int(1) comment '1radio|2checkbox|3text|4textarea',
   required             int(1) comment '0�Ǳ���1����',
   check_style          varchar(50) comment 'text;number;date',
   order_style          int(1) comment '0˳��1���',
   show_style           int(1) comment '1;2;3;4',
   test                 int(1) comment '0������1����',
   score                int(3),
   orderby              int(11),
   creator              int(11),
   create_time          datetime,
   survey_id            int(11),
   primary key (id)
);

/*==============================================================*/
/* Table: tb_question_opt                                       */
/*==============================================================*/
create table tb_question_opt
(
   id                   int(11) not null auto_increment,
   survey_id            int(11),
   question_id          int(11),
   type                 varchar(20) comment '1radio|2checkbox',
   opt                  varchar(200),
   orderby              int(11),
   answer               int(1) comment 'Ĭ��ΪNULL��1��',
   primary key (id)
);

/*==============================================================*/
/* Table: tb_survey                                             */
/*==============================================================*/
create table tb_survey
(
   ID                   int(11) not null auto_increment,
   title                varchar(100),
   remark               varchar(200),
   bounds               int(1) comment '0:������;1:����',
   start_time           datetime,
   end_time             datetime,
   rules                int(1) comment '0����;1����',
   password             varchar(50),
   url                  varchar(200),
   state                varchar(50) comment '������ִ���С�����',
   logo                 varchar(200),
   bgimg                varchar(200),
   anon                 int(1) comment '0������1������',
   creator              int(11),
   create_time          datetime,
   primary key (ID)
);

alter table tb_answer_opt add constraint FK_Reference_2 foreign key (opt_id)
      references tb_question_opt (id) on delete restrict on update restrict;

alter table tb_question add constraint FK_Reference_1 foreign key (survey_id)
      references tb_survey (ID) on delete restrict on update restrict;

