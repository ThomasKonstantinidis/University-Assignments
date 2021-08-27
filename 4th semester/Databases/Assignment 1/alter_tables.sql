ALTER TABLE Calendar ADD FOREIGN KEY (listing_id) REFERENCES Listings(id);
ALTER TABLE Geolocation ADD FOREIGN KEY (properties_neighbourhood) REFERENCES Neighbourhoods(neighbourhood);
ALTER TABLE Listings ADD FOREIGN KEY (neighbourhood_cleansed) REFERENCES Neighbourhoods(neighbourhood);
ALTER TABLE Reviews ADD FOREIGN KEY (listing_id) REFERENCES Listings(id);
ALTER TABLE Reviews_Summary ADD FOREIGN KEY (listing_id) REFERENCES Listings(id);


