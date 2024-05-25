alter table `tb_purchase_order`
rename column `cpf_client` to `cpf_client_tmp`;

alter table `tb_purchase_order`
add column `cpf_client` varchar(14);

update `tb_purchase_order`
set `cpf_client` = `cpf_client_tmp`;

alter table `tb_purchase_order`
drop column `cpf_client_tmp`;