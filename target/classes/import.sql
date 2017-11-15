insert into product (id, name, description, parent_product_id) values (1, 'Product 1', 'OneImage', null);
insert into product (id, name, description, parent_product_id) values (2, 'Product 2', 'OneImage', null);
insert into product (id, name, description, parent_product_id) values (3, 'Product 3', 'TwoImagesTwoChilds', null);
insert into product (id, name, description, parent_product_id) values (4, 'Product 4', '', 3);
insert into product (id, name, description, parent_product_id) values (5, 'Product 5', '', 3);

insert into image (id, type, product_id) values (1, 1, 1);
insert into image (id, type, product_id) values (2, 2, 2);
insert into image (id, type, product_id) values (3, 3, 3);
insert into image (id, type, product_id) values (4, 4, 3);
insert into image (id, type, product_id) values (5, 2, 5);
insert into image (id, type, product_id) values (6, 1, 5);
