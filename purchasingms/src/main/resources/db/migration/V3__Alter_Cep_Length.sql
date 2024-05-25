alter table `tb_purchase_order`
rename column `cep` to `cep_tmp`;

alter table `tb_purchase_order`
add column `cep` varchar(10);

update `tb_purchase_order`
set `cep` = `cep_tmp`;

alter table `tb_purchase_order`
drop column `cep_tmp`;