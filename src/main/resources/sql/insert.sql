DELETE FROM coupon;
DELETE FROM product;

ALTER SEQUENCE product_id_seq RESTART WITH 1;
ALTER SEQUENCE coupon_id_seq RESTART WITH 1;

INSERT INTO product (id, name, price) VALUES
                                          (1, 'Iphone', 100.00),
                                          (2, 'Наушники', 20.00),
                                          (3, 'Чехол', 10.00);

INSERT INTO coupon (id, active, code, discount_percent) VALUES
                                                            (1, true, 'P10', 10),
                                                            (2, true, 'P100', 100),
                                                            (3, true, 'P30', 30);