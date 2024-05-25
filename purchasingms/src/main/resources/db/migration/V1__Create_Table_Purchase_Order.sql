create table if not exists `tb_purchase_order` (
    `id` bigint not null auto_increment,
    `name` varchar(80),
    `cpf_client` varchar(12),
    `cep` varchar(8),
    `product` bigint,
    `purchase_value` numeric(38,2),
    `date_purchase` date,
    primary key (`id`)
);