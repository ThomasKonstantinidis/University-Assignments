//movies number per year
SELECT EXTRACT(YEAR FROM release_date), count(id)
from project.movies_metadata 
group by EXTRACT(YEAR FROM release_date) 
order by EXTRACT(YEAR FROM release_date);



//movies number per genre
SELECT y.x->'name' "name", COUNT(id)
FROM project.movies_metadata
CROSS JOIN LATERAL (SELECT jsonb_array_elements(project.movies_metadata.genres::jsonb) x) y
GROUP BY y.x; 



//movies number per year and genre
SELECT y.x->'name' "name", EXTRACT(YEAR FROM release_date), COUNT(id)
FROM project.movies_metadata
CROSS JOIN LATERAL (SELECT jsonb_array_elements(project.movies_metadata.genres::jsonb) x) y
GROUP BY y.x, EXTRACT(YEAR FROM release_date) 
order by EXTRACT(YEAR FROM release_date);



//average rating per movie genre
SELECT y.x->'name' "name", CAST(avg(cast(vote_average as float)) AS DECIMAL(10,2))
FROM project.movies_metadata
CROSS JOIN LATERAL (SELECT jsonb_array_elements(movies_metadata.genres::jsonb) x) y
GROUP BY y.x;



//number of rating per user
SELECT userid, count(userid)
from project.ratings 
group by userid
order by userid;



//average rating per user
SELECT userid, CAST(avg(cast(rating as float)) AS DECIMAL(10,2))
from project.ratings 
group by userid
order by userid;



//create view table
create table project.View(
	userId int,
	rating_num int,
	Avg_rating decimal(10,2),
	PRIMARY KEY (userId)
);



//insert the proper values
INSERT INTO project.view (userid, rating_num, avg_rating)
SELECT userid, count(userid), CAST(avg(cast(rating as float)) AS DECIMAL(10,2))
from project.ratings 
group by userid
order by userid;


//INSIGHT
// The new View table give us a insight. How strict each user is depends on the number of ratings they have.




