INSERT INTO destinations (id, name, city, country, latitude, longitude, description, rating)
VALUES
    (1,'Eiffel Tower', 'Paris', 'France', 48.8584, 2.2945, 'Iconic iron lattice tower on the Champ de Mars.', 4.8),
    (2,'Louvre Museum', 'Paris', 'France', 48.8606, 2.3376, 'World-famous art museum with the Mona Lisa.', 4.9),
    (3,'Versailles Palace', 'Versailles', 'France', 48.8049, 2.1204, 'Famous royal palace just outside Paris.', 4.8),

    (4,'Colosseum', 'Rome', 'Italy', 41.8902, 12.4922, 'Ancient amphitheatre in the centre of the city of Rome.', 4.7),
    (5,'Pantheon', 'Rome', 'Italy', 41.8986, 12.4768, 'Well-preserved ancient Roman temple, now a church.', 4.8),
    (6,'Amalfi Coast', 'Amalfi', 'Italy', 40.6333, 14.6020, 'Scenic coastline south of Naples with picturesque towns.', 4.9),

    (7,'Sagrada Familia', 'Barcelona', 'Spain', 41.4036, 2.1744, 'Large unfinished Roman Catholic minor basilica.', 4.9),
    (8,'Park Guell', 'Barcelona', 'Spain', 41.4145, 2.1527, 'Colorful public park designed by Antoni Gaudí.', 4.8),
    (9,'Montserrat', 'Montserrat', 'Spain', 41.5910, 1.8370, 'Mountainous area with monastery near Barcelona.', 4.7),

    (10,'Santorini Caldera', 'Oia', 'Greece', 36.4618, 25.3753, 'Stunning volcanic views and white-washed houses.', 4.8),
    (11,'Fira Town', 'Santorini', 'Greece', 36.4175, 25.4280, 'Main town of Santorini with shops and caldera views.', 4.7),
    (12,'Delos Island', 'Delos', 'Greece', 37.4060, 25.2670, 'Ancient sacred island near Mykonos.', 4.6),

    (13,'Alexander Nevsky Cathedral', 'Sofia', 'Bulgaria', 42.6959, 23.3329, 'One of the largest Eastern Orthodox cathedrals in the world.', 4.6),
    (14,'Boyana Church', 'Sofia', 'Bulgaria', 42.6529, 23.3094, 'Medieval church with famous frescoes.', 4.7),
    (15,'Rila Monastery', 'Rila', 'Bulgaria', 42.1333, 23.3333, 'Famous Eastern Orthodox monastery in the mountains.', 4.9),

    (16,'Machu Picchu', 'Cusco', 'Peru', -13.1631, -72.5450, '15th-century Inca citadel located in the Eastern Cordillera.', 5.0),
    (17,'Sacred Valley', 'Cusco', 'Peru', -13.3333, -72.1667, 'Valley with Inca ruins and stunning landscapes.', 4.9),
    (18,'Lake Titicaca', 'Puno', 'Peru', -15.8402, -69.0060, 'Largest lake in South America with floating islands.', 4.8),

    (19,'Grand Canyon', 'Arizona', 'USA', 36.0544, -112.1401, 'Steep-sided canyon carved by the Colorado River.', 4.9),
    (20,'Antelope Canyon', 'Arizona', 'USA', 36.8619, -111.3743, 'Photogenic slot canyon with narrow passageways.', 4.8),
    (21,'Yosemite National Park', 'California', 'USA', 37.8651, -119.5383, 'Famous national park with granite cliffs and waterfalls.', 4.9),

    (22,'Mount Fuji', 'Fujiyoshida', 'Japan', 35.3606, 138.7274, 'Highest mountain in Japan and an active stratovolcano.', 4.8),
    (23,'Lake Kawaguchi', 'Fujikawaguchiko', 'Japan', 35.5167, 138.7667, 'One of the five lakes near Mount Fuji, great views.', 4.7),
    (24,'Hakone', 'Hakone', 'Japan', 35.2320, 139.1060, 'Hot springs and scenic views of Mount Fuji.', 4.8),

    (25,'Dubai Mall', 'Dubai', 'UAE', 25.1972, 55.2797, 'The second largest mall in the world by total land area.', 4.5),
    (26,'Burj Khalifa', 'Dubai', 'UAE', 25.1972, 55.2744, 'World’s tallest building with observation decks.', 4.9),
    (27,'Al Ain Oasis', 'Al Ain', 'UAE', 24.2075, 55.7447, 'Historic oasis city inland in Abu Dhabi Emirate.', 4.7),

    (28,'Sydney Opera House', 'Sydney', 'Australia', -33.8568, 151.2153, 'Multi-venue performing arts centre at Sydney Harbour.', 4.7),
    (29,'Bondi Beach', 'Sydney', 'Australia', -33.8908, 151.2743, 'Popular beach for surfing and relaxation.', 4.8),
    (30,'Blue Mountains', 'Katoomba', 'Australia', -33.7180, 150.3119, 'Scenic mountains near Sydney with cliffs and waterfalls.', 4.9),

    (31,'The Maldives', 'Male', 'Maldives', 1.924992, 73.399658,'The Maldives is renowned for its white sandy beaches, turquoise waters, and vibrant marine life.', 5.0),
    (32,'Baa Atoll','Baa Atoll', 'Maldives', 5.1563, 73.0710, 'Area with coral reefs and amazing diving spots.', 4.9)

ON CONFLICT (id) DO NOTHING;

INSERT INTO app_users (id,email,password)
VALUES
    (1,'ralica_s@gmail.com','$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    (2,'ivan_g@gmail.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    (3,'ivailo_k@gmail.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    (4,'maria_p@gmail.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    (5,'vili_l@gmail.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    (6,'cveti_t@gmail.com', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a')
ON CONFLICT (id) DO NOTHING;

INSERT INTO trip (id, user_id, name, start_date, end_date,created_date)
VALUES
    (1,1,'Summer Vacation', '2025-07-20','2025-07-28','2025-07-02'),
    (2,2,'Spain','2025-05-01','2025-05-06','2025-05-01'),
    (3,3,'Hiking trip', '2025-06-18','2025-06-24','2025-06-10'),
    (4,3,'Motherland trip','2024-11-12','2024-11-20','2024-10-13'),
    (5,4,'Italy','2025-09-10','2025-09-15','2025-09-02'),
    (6,5,'Love city','2025-03-27','2025-04-01','2025-03-22'),
    (7,6,'Greece','2025-08-22','2025-08-26','2025-07-02')
ON CONFLICT (id) DO NOTHING;

INSERT INTO trip_destination (id, trip_id, destination_id, day_index)
VALUES
    (1,1,31,1),
    (2,1,31,2),
    (3,1,31,3),
    (4,1,31,4),
    (5,1,32,5),
    (6,1,32,6),
    (7,1,32,7),
    (8,1,32,8),

    (9,2,7,1),
    (10,2,7,2),
    (11,2,8,3),
    (12,2,9,4),
    (13,2,9,5),

    (14,3,23,1),
    (15,3,23,2),
    (16,3,22,3),
    (17,3,22,4),
    (18,3,24,5),
    (19,3,24,6),

    (20,4,13,1),
    (21,4,13,2),
    (22,4,13,3),
    (23,4,13,4),
    (24,4,13,5),
    (25,4,14,6),
    (26,4,14,7),
    (27,4,14,8),
    (28,4,15,9),

    (29,5,4,1),
    (30,5,4,2),
    (31,5,5,3),
    (32,5,6,4),
    (33,5,6,5),

    (34,6,1,1),
    (35,6,1,2),
    (36,6,2,3),
    (37,6,2,4),
    (38,6,2,5),
    (39,6,3,6),

    (40,7,10,1),
    (41,7,10,2),
    (42,7,11,3),
    (43,7,11,4)

ON CONFLICT (id) DO NOTHING;

INSERT INTO user_favourites (id,user_id, destination_id)
VALUES
    (1,1, 6),
    (2,2, 3),
    (3,3, 4),
    (4,4, 1),
    (5,5, 9),
    (6,6, 8),
    (7,6, 7)
ON CONFLICT (id) DO NOTHING;








