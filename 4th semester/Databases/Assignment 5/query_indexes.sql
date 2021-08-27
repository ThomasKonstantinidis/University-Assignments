EXPLAIN ANALYZE
SELECT Host.id, COUNT(*) 
FROM Listing_4, Host 
WHERE Host.id = Listing_4.host_id 
GROUP BY Host.id;

EXPLAIN ANALYZE
SELECT id, price 
FROM Listing_4, Price 
WHERE guests_included > 5
AND price > 40;

/* Find neighbourhoods which starts with A and show the smart location and the coordinates of 0_0_0_0.
Output: 48 rows
*/
EXPLAIN ANALYZE
SELECT smart_location, Geolocation.geometry_coordinates_0_0_0_0
FROM Location, Geolocation
WHERE Location.neighbourhood_cleansed = Geolocation.properties_neighbourhood
AND Location.neighbourhood_cleansed LIKE 'Α%'
GROUP BY smart_location, Geolocation.geometry_coordinates_0_0_0_0;

/* Find the availability of the year and the price of apartments that their price are more than 75 even if the price is null.
Output: 1827 rows
*/
EXPLAIN ANALYZE
SELECT Listing_4.availability_365, Price.price
FROM Listing_4
LEFT OUTER JOIN Price
ON Price.listing_id = Listing_4.id 
GROUP BY Listing_4.availability_365, Price.price
HAVING Price.price > 75;

/* Find rooms which security_deposit are more than 300 and show the price, availability of month and bed_type.
Output: 676 rows
*/
EXPLAIN ANALYZE
SELECT Room.bed_type, Room.price, Listing_4.availability_30
FROM Listing_4, Room
WHERE Listing_4.id = Room.listing_id
AND Room.security_deposit > '300';

/* Find and show hosts name and acceptance_rate even if are null, where last_scraped was after 2020-03-17. Also show name and house_rules.
Output: 2450 rows
*/
EXPLAIN ANALYZE
SELECT Listing_4.name, Host.name, Host.acceptance_rate, Listing_4.house_rules 
FROM Host
RIGHT OUTER JOIN Listing_4
ON Listing_4.host_id = Host.id
WHERE Listing_4.last_scraped > '2020-03-17';

/* Find the hosts which are since 2009-09-08 and show the url, location and description.
Output: 6 rows
*/
EXPLAIN ANALYZE
SELECT Host.url, Host.location, Listing_4.description
FROM Host, Listing_4
WHERE Host.id = Listing_4.host_id
GROUP BY Host.url, Host.location, Listing_4.description, Host.since
HAVING Host.since = '2009-09-08';