Update Room set amenities = REPLACE(amenities,'{', '');
Update Room set amenities = REPLACE(amenities,'}', '');
Update Room set amenities = REPLACE(amenities,'"', '');

CREATE TABLE Amenity  
  AS (SELECT DISTINCT regexp_split_to_table(amenities, ',') amenity_name
  FROM Room);
  

ALTER TABLE Amenity ADD COLUMN amenity_id int;

ALTER TABLE Amenity ALTER COLUMN amenity_id SERIAL PRIMARY KEY;



CREATE TABLE Connection_Am_Ro AS
(SELECT temp.listing_id as listing_id,amenity_id as id from Amenity,
	(SELECT Amenity.amenity_name, Room.listing_id as listing_id from Amenity,Room ) AS temp
	where temp.amenity_name = Amenity.amenity_name);
	
	
	
ALTER TABLE Connection_Am_Ro
ADD PRIMARY KEY(listing_id, id);


ALTER TABLE Room
ADD PRIMARY KEY(listing_id);


ALTER TABLE Connection_Am_Ro
add foreign key(id) REFERENCES Amenity(amenity_id),
add foreign key(listing_id) REFERENCES Room(listing_id);


ALTER TABLE Room
DROP COLUMN amenities;














