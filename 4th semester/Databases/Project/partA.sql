//Create of Ratings table
create table project.Ratings(
	userId int,
	movieId int,
	rating varchar(10),
	timestamp varchar(100)
);


//Create of Credits table
create table project.Credits(
   casts TEXT,
   crew TEXT,
   id int
);


//Create of Keywords table
create table project.Keywords(
	id int,
	keywords TEXT
);


//Create of Links table
create table project.Links(
	movieId int,
	imdbId int,
	tmdbId int
);


//Create of Movies_metadata table
create table project.Movies_metadata(
	adult boolean,
	belongs_to_collection TEXT,
	budget int,
	genres TEXT,
	homepage varchar(1000),
	id int,
	imdb_id varchar(100),
	original_language varchar(100),
	original_title varchar(1000),
	overview varchar(1000),
	popularity varchar(1000),
	poster_path varchar(1000),
	production_companies TEXT,
	production_countries TEXT,
	release_date date,
	revenue varchar(100),
	runtime varchar(100),
	spoken_languages varchar(1000),
	status varchar(100),
	tagline varchar(1000),
	title varchar(1000),
	video boolean,
	vote_average varchar(100),
	vote_count varchar(100)
);



//I import them from pgadmin console with Escape '"' only for the tables which had the TEXT data.
//For the shell the commands, i think are: 
\copy Credits FROM 'C:\Users\User\Desktop\csv\credits.csv' DELIMITERS ',' QUOTE '''' CSV HEADER;
\copy Keywords FROM 'C:\Users\User\Desktop\csv\keywords.csv' DELIMITER ',' QUOTE '''' CSV HEADER;
\copy Links FROM 'C:\Users\User\Desktop\csv\links.csv' DELIMITER ',' QUOTE '''' CSV HEADER;
\copy Movies_metadata FROM 'C:\Users\User\Desktop\csv\movies_metadata.csv' DELIMITER ',' QUOTE '''' CSV HEADER;
\copy Ratings FROM 'C:\Users\User\Desktop\csv\ratings.csv' DELIMITER ',' QUOTE '''' CSV HEADER;




//Create copy tables with no duplicates
CREATE TABLE project.Credits_Copy AS SELECT DISTINCT id FROM project.Credits;
CREATE TABLE project.Keywords_Copy AS SELECT DISTINCT id FROM project.Keywords;
CREATE TABLE project.Links_Copy AS SELECT DISTINCT movieid, imdbid, tmdbid FROM project.Links;
CREATE TABLE project.Movies_metadata_Copy AS SELECT SELECT DISTINCT id FROM project.Movies_metadata;




//Add the other columns to the proper table
ALTER TABLE project.Credits_Copy ADD COLUMN casts TEXT;
ALTER TABLE project.Credits_Copy ADD COLUMN crew int;
ALTER TABLE project.keywords_copy ADD COLUMN keywords TEXT;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN adult boolean;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN belongs_to_collection TEXT;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN budget int;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN genres TEXT;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN homepage varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN imdb_id varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN original_language varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN original_title varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN overview varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN popularity varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN poster_path varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN production_companies TEXT;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN production_countries TEXT;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN release_date date;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN revenue varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN runtime varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN spoken_languages varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN status varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN tagline varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN title varchar(1000);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN video boolean;
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN vote_average varchar(100);
ALTER TABLE project.Movies_metadata_Copy ADD COLUMN vote_count varchar(100);

	


//Insert the data for each existing no duplicate id
UPDATE project.credits_copy
SET casts=project.credits.casts ,
crew=project.credits.crew
FROM
project.credits
WHERE
project.credits_copy.id = project.credits.id;


UPDATE project.keywords_copy
SET keywords = project.keywords.keywords
FROM
project.keywords
WHERE
project.keywords_copy.id = project.keywords.id;


UPDATE project.movies_metadata_copy
SET adult = project.movies_metadata.adult,
	belongs_to_collection = project.movies_metadata.belongs_to_collection,
	budget = project.movies_metadata.budget,
	genres = project.movies_metadata.genres,
	homepage = project.movies_metadata.homepage,
	imdb_id = project.movies_metadata.imdb_id,
	original_language = project.movies_metadata.original_language,
	original_title = project.movies_metadata.original_title,
	overview = project.movies_metadata.overview,
	popularity = project.movies_metadata.popularity,
	poster_path = project.movies_metadata.poster_path,
	production_companies = project.movies_metadata.production_companies,
	production_countries = project.movies_metadata.production_countries,
	release_date = project.movies_metadata.release_date,
	revenue = project.movies_metadata.revenue,
	runtime = project.movies_metadata.runtime,
	spoken_languages = project.movies_metadata.spoken_languages,
	status = project.movies_metadata.status,
	tagline = project.movies_metadata.tagline,
	title = project.movies_metadata.title,
	video = project.movies_metadata.video,
	vote_average = project.movies_metadata.vote_average,
	vote_count = project.movies_metadata.vote_count
FROM
project.movies_metadata
WHERE
project.movies_metadata_copy.id = project.movies_metadata.id;






//Delete the movies not exists in movies_metadata_copy
delete
from project.links_copy 
where tmdbid not in(
 select id
	from project.movies_metadata_copy);
	
	
// afou esbisa osa to movies_metadata_copy den eixe apo to links_copy
// to links_copy exei ola osa uparxoun ston movies_metadata_copy
// ara sbinw kai osa den uparxoun apo to ratings sto links_copy
// giati den tha uparxoun kai ston movies_metadata_copy.
delete
from project.ratings 
where movieid not in(
	select movieid
	from project.links_copy);
	

//Rename the copy tables after droped the first tables which have duplicates, except Ratings.
DROP TABLE project.credits;
DROP TABLE project.Keywords;
DROP TABLE project.Movies_metadata;
DROP TABLE project.Links;

ALTER TABLE project.credits_copy RENAME TO Credits;
ALTER TABLE project.keywords_copy RENAME TO Keywords;
ALTER TABLE project.links_copy RENAME TO Links;
ALTER TABLE project.movies_metadata_copy RENAME TO Movies_metadata;



//Create the primary and foreign keys.
delete from project.credits_copy where id is null;
ALTER TABLE project.credits_copy add primary key(id);
ALTER TABLE project.keywords add primary key(id);
ALTER TABLE project.links add primary key(movieid);
ALTER TABLE project.movies_metadata add primary key(id);
ALTER TABLE project.ratings add primary key(userid, movieid);

ALTER TABLE project.ratings ADD FOREIGN KEY (movieid) REFERENCES project.links(movieid);
ALTER TABLE project.links ADD FOREIGN KEY (tmdbid) REFERENCES project.movies_metadata(id);
ALTER TABLE project.keywords ADD FOREIGN KEY (id) REFERENCES project.movies_metadata(id);
ALTER TABLE project.credits ADD FOREIGN KEY (id) REFERENCES project.movies_metadata(id);