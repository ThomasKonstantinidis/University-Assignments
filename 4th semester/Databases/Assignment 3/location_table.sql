CREATE TABLE Location
	AS (SELECT DISTINCT id AS listing_id, street, neighbourhood, neighbourhood_cleansed, city, state,
		zipcode, market, smart_location, country_code, country, latitude, longitude,
		is_location_exact
	FROM Listings_copy);
	
ALTER TABLE Listings_copy DROP COLUMN street;
ALTER TABLE Listings_copy DROP COLUMN neighbourhood;
ALTER TABLE Listings_copy DROP COLUMN neighbourhood_cleansed;
ALTER TABLE Listings_copy DROP COLUMN city;
ALTER TABLE Listings_copy DROP COLUMN is_location_exact;
ALTER TABLE Listings_copy DROP COLUMN state;
ALTER TABLE Listings_copy DROP COLUMN market;
ALTER TABLE Listings_copy DROP COLUMN smart_location;
ALTER TABLE Listings_copy DROP COLUMN zipcode;
ALTER TABLE Listings_copy DROP COLUMN country_code;
ALTER TABLE Listings_copy DROP COLUMN country;
ALTER TABLE Listings_copy DROP COLUMN latitude;
ALTER TABLE Listings_copy DROP COLUMN longitude;


	
ALTER TABLE Location ADD FOREIGN KEY (listing_id) REFERENCES Listings_copy(id);

/*I do this ALTER, because the Listings_copy don't contain the Foreign key. That's why the copy command do not copy the foreign too.*/
ALTER TABLE Listings_copy ADD FOREIGN KEY (neighbourhood_cleansed) REFERENCES Neighbourhoods(neighbourhood); 



ALTER TABLE Listings_copy DROP CONSTRAINT listings_copy_neighbourhood_cleansed_fkey;
ALTER TABLE Location ADD FOREIGN KEY (neighbourhood_cleansed) REFERENCES Neighbourhoods(neighbourhood);