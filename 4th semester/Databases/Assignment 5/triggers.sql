CREATE FUNCTION list_count_dec()
RETURNS trigger 
AS
'BEGIN
UPDATE Host 
SET listings_count = listings_count - 1
WHERE id = OLD.host_id;
RETURN OLD;
END;
'
LANGUAGE plpgsql;

CREATE FUNCTION list_count_inc()
RETURNS trigger 
AS
'BEGIN
UPDATE Host 
SET listings_count = listings_count + 1
WHERE id = NEW.host_id;
RETURN NEW;
END;
'
LANGUAGE plpgsql;


CREATE TRIGGER Hostriggerdel 
AFTER DELETE 
ON Listing_4
FOR EACH ROW
EXECUTE PROCEDURE list_count_dec();


CREATE TRIGGER Hostriggerins 
AFTER INSERT 
ON Listing_4
FOR EACH ROW
EXECUTE PROCEDURE list_count_inc();





/* When a reviewer deleted or 
inserted, increase or decrease
number_of_reviews on listing
*/

CREATE FUNCTION rev_count_dec()
RETURNS trigger 
AS 
'BEGIN
UPDATE Listing_4 
SET number_of_reviews = number_of_reviews - 1
WHERE id = OLD.listing_id;
RETURN OLD;
END;
'
LANGUAGE plpgsql;

CREATE FUNCTION rev_count_inc()
RETURNS trigger 
AS 
'BEGIN
UPDATE Listing_4 
SET number_of_reviews = number_of_reviews + 1
WHERE id = NEW.listing_id;
RETURN NEW;
END;
'
LANGUAGE plpgsql;


CREATE TRIGGER Revtriggerdel 
AFTER DELETE 
ON Review_4
FOR EACH ROW
EXECUTE PROCEDURE rev_count_dec();


CREATE TRIGGER Revtriggerins 
AFTER INSERT 
ON Review_4
FOR EACH ROW
EXECUTE PROCEDURE rev_count_inc();