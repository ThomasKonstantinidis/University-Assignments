CREATE TABLE Price
	AS (SELECT DISTINCT id AS listing_id, price, weekly_price, monthly_price, security_deposit, cleaning_fee,
		guests_included, extra_people, minimum_nights, maximum_nights,
		minimum_minimum_nights, maximum_minimum_nights, minimum_maximum_nights,
		maximum_maximum_nights, minimum_nights_avg_ntm, maximum_nights_avg_ntm
	FROM Listings_copy);
	
	
UPDATE Price SET price = replace(price, '$', '');
UPDATE Price SET price = replace(price, ',', '');

UPDATE Price SET weekly_price = replace(weekly_price, '$', '');
UPDATE Price SET weekly_price = replace(weekly_price, ',', '');

UPDATE Price SET monthly_price = replace(monthly_price, '$', '');
UPDATE Price SET monthly_price = replace(monthly_price, ',', '');

UPDATE Price SET security_deposit = replace(security_deposit, '$', '');
UPDATE Price SET security_deposit = replace(security_deposit, ',', '');

UPDATE Price SET cleaning_fee = replace(cleaning_fee, '$', '');
UPDATE Price SET cleaning_fee = replace(cleaning_fee, ',', '');

UPDATE Price SET extra_people = replace(extra_people, '$', '');
UPDATE Price SET extra_people = replace(extra_people, ',', '');




ALTER TABLE Price ALTER COLUMN price TYPE NUMERIC(10,2) USING price::numeric;
ALTER TABLE Price ALTER COLUMN weekly_price TYPE NUMERIC(10,2) USING weekly_price::numeric;
ALTER TABLE Price ALTER COLUMN monthly_price TYPE NUMERIC(10,2) USING monthly_price::numeric;
ALTER TABLE Price ALTER COLUMN security_deposit TYPE NUMERIC(10,2) USING security_deposit::numeric;
ALTER TABLE Price ALTER COLUMN cleaning_fee TYPE NUMERIC(10,2) USING cleaning_fee::numeric;
ALTER TABLE Price ALTER COLUMN extra_people TYPE NUMERIC(10,2) USING extra_people::numeric;
ALTER TABLE Price ALTER COLUMN minimum_nights_avg_ntm TYPE NUMERIC(10,1) USING minimum_nights_avg_ntm::numeric;
ALTER TABLE Price ALTER COLUMN maximum_nights_avg_ntm TYPE NUMERIC(10,0) USING maximum_nights_avg_ntm::numeric;



ALTER TABLE Listings_copy DROP COLUMN price;
ALTER TABLE Listings_copy DROP COLUMN weekly_price;
ALTER TABLE Listings_copy DROP COLUMN monthly_price;
ALTER TABLE Listings_copy DROP COLUMN guests_included;
ALTER TABLE Listings_copy DROP COLUMN extra_people;
ALTER TABLE Listings_copy DROP COLUMN minimum_nights;
ALTER TABLE Listings_copy DROP COLUMN maximum_nights;
ALTER TABLE Listings_copy DROP COLUMN minimum_minimum_nights;
ALTER TABLE Listings_copy DROP COLUMN maximum_minimum_nights;
ALTER TABLE Listings_copy DROP COLUMN minimum_maximum_nights;
ALTER TABLE Listings_copy DROP COLUMN security_deposit;
ALTER TABLE Listings_copy DROP COLUMN maximum_maximum_nights;
ALTER TABLE Listings_copy DROP COLUMN minimum_nights_avg_ntm;
ALTER TABLE Listings_copy DROP COLUMN maximum_nights_avg_ntm;
ALTER TABLE Listings_copy DROP COLUMN cleaning_fee;



ALTER TABLE Price ADD FOREIGN KEY (listing_id) REFERENCES Listings_copy(id);