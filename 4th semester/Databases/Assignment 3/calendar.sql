UPDATE Calendar_Copy SET price = replace(price, '$', '');
UPDATE Calendar_Copy SET price = replace(price, ',', '');

UPDATE Calendar_Copy SET adjusted_price = replace(price, '$', '');
UPDATE Calendar_Copy SET adjusted_price = replace(price, ',', '');

ALTER TABLE Calendar_Copy ALTER COLUMN price TYPE NUMERIC(10,2) USING price::numeric;
ALTER TABLE Calendar_Copy ALTER COLUMN adjusted_price TYPE NUMERIC(10,2) USING price::numeric;