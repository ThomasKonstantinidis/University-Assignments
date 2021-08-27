CREATE TABLE Room
	AS (SELECT DISTINCT id AS listing_id, accommodates, bathrooms, bedrooms, beds, bed_type,
		amenities, square_feet, price, weekly_price, monthly_price, security_deposit
	FROM Listings_copy);


	
ALTER TABLE Listings_copy DROP COLUMN accommodates;
ALTER TABLE Listings_copy DROP COLUMN bathrooms;
ALTER TABLE Listings_copy DROP COLUMN bedrooms;
ALTER TABLE Listings_copy DROP COLUMN beds;
ALTER TABLE Listings_copy DROP COLUMN bed_type;
ALTER TABLE Listings_copy DROP COLUMN amenities;
ALTER TABLE Listings_copy DROP COLUMN square_feet;
ALTER TABLE Listings_copy DROP COLUMN price;
ALTER TABLE Listings_copy DROP COLUMN weekly_price;
ALTER TABLE Listings_copy DROP COLUMN monthly_price;
ALTER TABLE Listings_copy DROP COLUMN security_deposit;


ALTER TABLE Room ADD FOREIGN KEY (listing_id) REFERENCES Listings_copy(id);