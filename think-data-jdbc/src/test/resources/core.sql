drop table if exists simple_example;
create table simple_example
(
    id                 bigint auto_increment comment 'ID'
        primary key,
    string_type        varchar(1024) comment 'string',
    integer_type       integer comment 'integer',
    long_type long comment 'long',
    bigdecimal_type decimal comment 'decimal',
    float_type float comment 'float',
    double_type double comment 'double',
    boolean_type boolean comment 'boolean',
    date_type date comment 'date',
    datetime_type datetime comment 'datetime',
    timestemp_type timestamp comment 'timestemp',
    created_by         bigint        not null comment '创建人',
    created_date       datetime      not null comment '创建时间',
    last_modified_by   bigint        not null comment '最后修改人',
    last_modified_date datetime      not null comment '最后修改时间'
)
    comment '简单示例' charset = utf8;

