

create external table if not exists provider_info (global_id string, npi string, first_name string, last_name string, org_name string, entity_type_code string, state string, gender string, zip string, primary_taxonomy string, tx_type string, tx_classification string, tx_specialization string)
row format delimited
fields terminated by ','

alter table provider_info
drop partition(npi='1861786170', entity_type_code='1', state='TX', zip='78212', primary_taxonomy='101YP2500X', tx_type='Behavioral Health & Social Service Providers', tx_classification='Counselor', tx_specialization='Professional')