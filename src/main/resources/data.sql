insert into actor(USERNAME, PASSWORD_HASH, ROLE) values 
	('admin', '$2a$08$n3IFBWwmwtAaSRBDXv3aluqLekx2z90gY513n1q9NFxb7vBIEbyqS', 'ADMIN'),
	('inventory', '$2a$08$LEsBHoQza8dWY.x4CS/Z1.1AHbRA6IXf02ytwYbDrICAFTcWfy2Py', 'INVENTORY_MANAGER'),
	('sales', '$2a$08$qj.9VQoxxOQ5rlBFeC.Oueum/XfzqLY/dYn6i71TUiQO75R7vJICW', 'SALES_MANAGER'),
	('cashier', '$2a$08$ynwmmX0LlJlrSE0Vi5llkua1emvm/1lE2N2THUr39908xqpAaTp3u', 'CASHIER'),
	('customer', '$2a$08$vw2C/hqXY95IuxcYSm0iZeo9KTjjPn0StlNpMXA3UN2FUXAieAwEm', 'CUSTOMER');
	
insert into category(NAME, DISCOUNT, DESCRIPTION) values 
	('C1', 10, 'Festive offer'),
	('C2', 5, 'VISA discount');
	
insert into product(NAME, BARCODE, CATEGORY, PRICE, DESCRIPTION) values 
	('P1', 'ABC 72G 893', select id from category where name='C1', 230, ''),
	('P2', 'XYZ 82A 803', null, 540, ''),
	('P3', 'QSA 13H 932', select id from category where name='C2', 1200, ''),
	('P4', 'GHJ 25K 940', select id from category where name='C2', 110, ''),
	('P5', 'BJS 32L 893', select id from category where name='C1', 512, ''),
	('P6', 'HDJ 81J 933', null, 1720, '');