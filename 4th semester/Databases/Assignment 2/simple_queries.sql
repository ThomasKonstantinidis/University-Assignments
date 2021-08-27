/* Find the minumum of reviewers name whose maximum nights of the accommodation are 5.
Output: 1 row
*/
SELECT Min(Reviews.reviewer_name)
FROM Reviews, Listings 
WHERE Reviews.listing_id = Listings.id
AND Listings.maximum_nights < 3;

/* Find all room types and their minimum nights in which more than 4 guests can join. (Do not show duplicates of compination(room type & min nights)).
Output: 16 rows
*/	
SELECT DISTINCT Listings_Summary.room_type,Listings_Summary.minimum_nights
FROM Listings_Summary, Listings 
WHERE Listings_Summary.id = Listings.id
AND Listings.guests_included > '4'
ORDER BY Listings_Summary.room_type,Listings_Summary.minimum_nights;

/* Find reviewers comments ( Not the duplicates ) who stayed between 11 and 13 maximum_nights. 
Output: 100 rows
*/
SELECT DISTINCT Reviews.comments
FROM Reviews, Calendar
WHERE Calendar.listing_id = Reviews.listing_id
AND Calendar.maximum_nights BETWEEN 11 AND 13
LIMIT 100;

/* Find neighbourhoos which starts with A and show the names and the coordinates of 0_0_0_0.
Output: 8 rows
*/
SELECT Neighbourhoods.neighbourhood, Geolocation.geometry_coordinates_0_0_0_0
FROM Neighbourhoods, Geolocation
WHERE Neighbourhoods.neighbourhood = Geolocation.properties_neighbourhood
AND Neighbourhoods.neighbourhood LIKE 'Α%';

/* Find names, prices and adjusted prices of a specific id.
Output: 2 rows
*/
SELECT Reviews.reviewer_name, Calendar.price, Calendar.adjusted_price
FROM Reviews
LEFT OUTER JOIN Calendar
ON Reviews.listing_id = Calendar.listing_id
WHERE Reviews.listing_id = 30818327;

/* Find the name of reviewer that went to the "Downtown..." hotel. Also show his/her comments and the houserules.
Output: 1 rows
*/
SELECT Listings.name, Reviews.reviewer_name, Reviews.comments, Listings.house_rules 
FROM Reviews
RIGHT OUTER JOIN Listings
ON Listings.id = Reviews.listing_id
WHERE Listings.name = 'Downtown adorable apartment with Lycabettus view';

/* Find the sum maximum nights of each id for a special date.
Output: 2874 rows 
*/
SELECT Listings.id, SUM(Calendar.maximum_nights)
From Listings
INNER JOIN Calendar 
on Listings.id=Calendar.listing_id
WHERE Calendar.date = '2020-03-29'
GROUP BY Listings.id,Calendar.price;

/*Find 10 results of max availability 365 for each id. Essentially show me the year availability of each id.
Output: 10 rows
*/
SELECT Calendar.listing_id,MAX(Listings.availability_365)
FROM Calendar
RIGHT OUTER JOIN Listings
ON Listings.id = Calendar.listing_id 
GROUP BY Calendar.listing_id
LIMIT 10;

/*Find average bedrooms per person whose name starts Ema. Show only 3 decimals.
Output: 16 rows
*/
SELECT Reviews.reviewer_name, ROUND(AVG(Listings.bedrooms), 3) AS bedroomsperperson
FROM Listings, Reviews
WHERE Listings.id = Reviews.listing_id
AND Reviews.reviewer_name LIKE 'Ema%'
GROUP BY reviewer_name;

/* Find how much reviewers had reviews and stayed smaller than 6 days.
Output: 1 row
*/
SELECT COUNT(Reviews.reviewer_id)
FROM Reviews, Listings 
WHERE Reviews.listing_id = Listings.id
AND Listings.maximum_nights < 6;

/* Find reviewer name and his/her comments if they are less than 3.
Output: 3 row
*/
SELECT Reviews.reviewer_name, Reviews.comments
FROM Reviews
WHERE Reviews.reviewer_name IN (
	SELECT Listings.name
	FROM Listings,Reviews
	WHERE Reviews.listing_id = Listings.id 
	AND Listings.number_of_reviews < 3);
	
	
/* Find neighbourhoods ( not the duplicates ) which has this zipcode or the other.
Output: 10 row
*/
SELECT DISTINCT Neighbourhoods.neighbourhood
FROM Neighbourhoods 
INNER JOIN Listings
ON Neighbourhoods.neighbourhood=Listings.neighbourhood_cleansed
WHERE zipcode='11742' OR zipcode='11635';