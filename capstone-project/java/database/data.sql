BEGIN TRANSACTION;

INSERT INTO users (username,password_hash,role,email) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER', 'admin@gmail.com');
INSERT INTO users (username,password_hash,role,email) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN', 'user@gmail.com');


INSERT INTO type (id, name) VALUES (1, 'bar');
INSERT INTO type (id, name) VALUES (2, 'museum');
INSERT INTO type (id, name) VALUES (3, 'stadium');
INSERT INTO type (id, name) VALUES (4, 'park');
INSERT INTO type (id, name) VALUES (5, 'restaurant');



INSERT INTO attraction (name, description, hours_of_operation, address, images, social_media, type_id, latitude, longitude)
VALUES
('McGillin''s Olde Ale House', 'One of the nation''s oldest pubs, offering a historic atmosphere with a wide selection of beers.', 'Open daily from 11:00 AM to 2:00 AM (Kitchen closes at 1:00 AM)', '1310 Drury Street, Philadelphia, PA 19107', 'img.png', 'instagram.com/mcgillinsoldealehouse', 1, 39.94845, -75.16378),
('Khyber Pass Pub', 'A cozy tavern known for its stellar craft beer selection and Southern-inspired menu.', 'Monday to Friday: 11:00 AM to 2:00 AM; Saturday and Sunday: 10:00 AM to 2:00 AM', '56 S 2nd Street, Philadelphia, PA 19106', 'img.png', 'instagram.com/khyberpasspub', 1, 39.9486537, -75.1467147),
('Assembly Rooftop Lounge', 'Located at the Logan Hotel, this bar impresses with views of the Benjamin Franklin Parkway and the Philadelphia Museum of Art.', 'Monday to Thursday: 4:00 PM to 12:00 AM; Friday and Saturday: 4:00 PM to 1:00 AM; Sunday: 4:00 PM to 10:00 PM',
'1840 Benjamin Franklin Parkway, Philadelphia, PA 19103', 'img.png', 'instagram.com/assemblyphl', 1, 39.9569418,-75.1722105),
('48 Record Bar', 'Nestled above Sassafras in Old City, this intimate cocktail lounge doubles as a vinyl listening room, attracting music lovers with its top-notch acoustics.', 'Tuesday to Saturday: 5:00 PM to 12:00 AM; Closed on Sunday and Monday', ' 48 S 2nd Street, Philadelphia, PA 19106', 'img.png', 'instagram.com/48recordbar', 1, 39.948801,-75.1441287),
('Independence Beer Garden', 'An open-air spot with games, snacks, and 40 taps, offering a vibrant atmosphere near historic sites.','Seasonal hours; typically open daily from 11:00 AM to 10:00 PM during the summer months', '100 S Independence Mall West, Philadelphia, PA 19106', 'img.png', 'instagram.com/phlbeergarden', 1, 39.950275,-75.153331),

('The Franklin Institute', 'A leading science museum with interactive exhibits suitable for all ages.', 'Open daily from 9:30 AM to 5:00 PM',
 	'222 N 20th Street, Philadelphia, PA 19103', 'img.png', 'instagram.com/franklininstitute', 2, 39.9583, -75.1726),
('Mutter Museum', 'A medical museum located at 19 S. 22nd Street, known for its unique medical artifacts.', 'Open Monday to Sunday from 10:00 AM to 5:00 PM',
 	'19 S 22nd Street, Philadelphia, PA 19103', 'img.png', 'instagram.com/muttermuseum', 2, 39.9533, -75.1766),
('National Constitution Center', 'An interactive museum dedicated to the U.S. Constitution and its history.', 'Open Monday to Saturday from 10:00 AM to 5:00 PM and Sunday 12:00 PM to 5:00 PM',
 	'525 Arch Street, Philadelphia, PA 19106', 'img.png', 'instagram.com/constitutionctr', 2, 39.9528, -75.1481),
('Museum of the American Revolution', 'Offers comprehensive exhibits on the nation''s Revolutionary War history.', 'Open daily from 10:00 AM to 5:00 PM',
 	'101 S 3rd Street, Philadelphia, PA 19106', 'img.png', 'instagram.com/amrevmuseum', 2, 39.9489, -75.1467),
('Philadelphia Museum of Art', 'One of the largest art museums in the country, featuring diverse collections and the famous ''Rocky Steps''',
 	'Open Wednesday and Friday from 10:00 AM to 8:45 PM and Thursday, Saturday, and Sunday from 10:00 AM to 5:00 PM. Closed Monday and Tuesday.',
 	'2600 Benjamin Franklin Parkway, Philadelphia, PA 19130', 'img.png', 'instagram.com/philamuseum', 2, 39.9656, -75.1800),

('Citizens Bank Park', 'Home to the Philadelphia Phillies, offering tours and game-day experiences.', 'Open during Philly games or other events', '1 Citizens Bank Way, Philadelphia, PA 19148', 'image.png', 'instagram.com/citizensbankpark', 3, 39.9060613, -75.1690701),
('Lincoln Financial Field', 'Home of the Philadelphia Eagles, known for its enthusiastic fan base.', 'Open during Eagle games or other events', 'One Lincoln Financial Field Way, Philadelphia, PA 19148', 'image.png', 'none', 3, 39.9013736, -75.1700964),
('Wells Fargo Center', 'Hosts the Philadelphia 76ers and Flyers, along with concerts and events.', 'Open during 76ers or Flyers games', '3601 S Broad St, Philadelphia, PA 19148', 'image.png', 'instagram.com/wellsfargocenter', 3, 39.9012056, -75.1745544),
('Franklin Field', 'A historic stadium at the University of Pennsylvania, hosting various sports events.', 'Open during University uses', '235 S 33rd St, Philadelphia, PA 19104', 'image.png', 'none', 3, 39.9502199, -75.1949801),
('Subaru Park', 'Located just outside Philadelphia, it''s home to the Philadelphia Union soccer team.', 'Open during soccer or other events', '2501 Seaport Dr, Chester, PA 19013', 'image.png', 'instagram.com/thefootballerzdistrict', 3, 39.8326898, -75.3809695),

('Rittenhouse Square', 'A historic public park surrounded by shops and restaurants, ideal for relaxation and people-watching.', '24 Hours Daily', '18th and Walnut Streets, Philadelphia, PA 19103', 'img.png', 'instagram.com/rittenhousesquare', 4, -75.1719, 39.9495),
('Franklin Delano Roosevelt (FDR) Park', 'Located along the Delaware River, this 348-acre park offers walking paths and scenic views.', '6 AM-9 PM Daily', '1500 Pattison Avenue &, S Broad St, Philadelphia, PA 19145', 'img.png', 'instagram.com/phillyfdrpark', 4, -75.1838, 39.9021),
('Spruce Street Harbor Park', 'A seasonal park with hammocks, food vendors, and colorful lights along the waterfront.','Friday 4-10 PM, Saturday 11 AM-10 PM, Sunday 11 AM-6 PM', '301 S Christopher Columbus Blvd, Philadelphia, PA 19106', 'img.png', 'instagram.com/sprucestreetharborpark', 4, -75.1415, 39.9444),
('John F. Collins Park', 'A hidden gem at 1707 Chestnut Street, offering a peaceful retreat in the midst of the city.', 'Sunday 12-5 PM, Monday 9AM-5PM, Tuesday-Saturday 9AM-6:30PM', '1707 Chestnut St, Philadelphia, PA 19103', 'img.png', 'N/A', 4, -75.1716097, 39.9518002),
('Washington Square Park', 'A historic square with open spaces and the Tomb of the Unknown Soldier.', '24 Hours Daily', '210 W Washington Square, Philadelphia, PA 19106', 'img.png', 'N/A', 4, -75.1523939, 39.9470367),

('Bastia', 'Honored as Best New Restaurant in 2024, it offers Mediterranean cuisine inspired by Corsica and Sardinia.', 'Wed-Thurs 5-10PM, Sat 10AM-2PM 5-10PM, Sun 10AM-2PM 5-9PM, Mon-Tues 5-9PM','1401 E Susquehanna Ave, Philadelphia, PA 19125','https://cdn.prod.website-files.com/66c5184bb3980b51b9347341/66c7652fb4c124c55f9e91f8_Bastia-Gallery-6.jpg', 'instagram.com/bastia_fishtown', 5, 39.9752503, -75.1263785),
('Rex at the Royal', 'Under Chef Angie Brown, this restaurant revitalizes the Southern dining scene with standout dishes.', 'Tues-Thurs 4-10PM, Friday 4-11PM, Sat 11AM-11PM, Sun 11AM-9PM', '1524 South St, Philadelphia, PA 19146','https://lh3.googleusercontent.com/p/AF1QipMT6LEEUNEGeO2cTgDUwMatWxg7kXwAN96x8HET=s680-w680-h510','instagram.com/rexphl', 5, 39.9437809, -75.1681729),
('Provenance', 'Named Best Tasting Menu Experience, featuring over 20 courses blending French and Korean flavors.','Weds-Fri 5:30-10PM, Sat 5-10PM', '408 S 2nd St, Philadelphia, PA 19147', 'https://lh3.googleusercontent.com/p/AF1QipPkwRnTEr-Lht5aBEhaLXOxlUN_I3bZyxQFyjHo=s680-w680-h510', 'instagram.com/provenancephl', 5, 39.9428742, -75.1456449),
('Little Nonna''s', 'A cozy Italian eatery known for its classic dishes and charming outdoor seating.','Everyday 5-10PM', '1234 Locust St, Philadelphia, PA 19107', 'https://lh3.googleusercontent.com/p/AF1QipPSwYyss5hJAAgBoimJ_VlgCJxO2oPc6pkZxe2J=s680-w680-h510', 'instagram.com/littlenonnas', 5, 39.9477967, -75.1618964);

COMMIT TRANSACTION;
