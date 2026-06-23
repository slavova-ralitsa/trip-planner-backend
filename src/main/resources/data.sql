INSERT INTO destinations ( name, city, country, latitude, longitude, description, rating)
VALUES
    ('Eiffel Tower', 'Paris', 'France', 48.8584, 2.2945, 'Iconic iron lattice tower on the Champ de Mars.', 4.8),
    ('Louvre Museum', 'Paris', 'France', 48.8606, 2.3376, 'World-famous art museum with the Mona Lisa.', 4.9),
    ('Versailles Palace', 'Versailles', 'France', 48.8049, 2.1204, 'Famous royal palace just outside Paris.', 4.8),

    ('Colosseum', 'Rome', 'Italy', 41.8902, 12.4922, 'Ancient amphitheatre in the centre of the city of Rome.', 4.7),
    ('Pantheon', 'Rome', 'Italy', 41.8986, 12.4768, 'Well-preserved ancient Roman temple, now a church.', 4.8),
    ('Amalfi Coast', 'Amalfi', 'Italy', 40.6333, 14.6020, 'Scenic coastline south of Naples with picturesque towns.', 4.9),

    ('Sagrada Familia', 'Barcelona', 'Spain', 41.4036, 2.1744, 'Large unfinished Roman Catholic minor basilica.', 4.9),
    ('Park Guell', 'Barcelona', 'Spain', 41.4145, 2.1527, 'Colorful public park designed by Antoni Gaudí.', 4.8),
    ('Montserrat', 'Montserrat', 'Spain', 41.5910, 1.8370, 'Mountainous area with monastery near Barcelona.', 4.7),

    ('Santorini Caldera', 'Oia', 'Greece', 36.4618, 25.3753, 'Stunning volcanic views and white-washed houses.', 4.8),
    ('Fira Town', 'Santorini', 'Greece', 36.4175, 25.4280, 'Main town of Santorini with shops and caldera views.', 4.7),
    ('Delos Island', 'Delos', 'Greece', 37.4060, 25.2670, 'Ancient sacred island near Mykonos.', 4.6),

    ('Alexander Nevsky Cathedral', 'Sofia', 'Bulgaria', 42.6959, 23.3329, 'One of the largest Eastern Orthodox cathedrals in the world.', 4.6),
    ('Boyana Church', 'Sofia', 'Bulgaria', 42.6529, 23.3094, 'Medieval church with famous frescoes.', 4.7),
    ('Rila Monastery', 'Rila', 'Bulgaria', 42.1333, 23.3333, 'Famous Eastern Orthodox monastery in the mountains.', 4.9),

    ('Machu Picchu', 'Cusco', 'Peru', -13.1631, -72.5450, '15th-century Inca citadel located in the Eastern Cordillera.', 5.0),
    ('Sacred Valley', 'Cusco', 'Peru', -13.3333, -72.1667, 'Valley with Inca ruins and stunning landscapes.', 4.9),
    ('Lake Titicaca', 'Puno', 'Peru', -15.8402, -69.0060, 'Largest lake in South America with floating islands.', 4.8),

    ('Grand Canyon', 'Arizona', 'USA', 36.0544, -112.1401, 'Steep-sided canyon carved by the Colorado River.', 4.9),
    ('Antelope Canyon', 'Arizona', 'USA', 36.8619, -111.3743, 'Photogenic slot canyon with narrow passageways.', 4.8),
    ('Yosemite National Park', 'California', 'USA', 37.8651, -119.5383, 'Famous national park with granite cliffs and waterfalls.', 4.9),

    ('Mount Fuji', 'Fujiyoshida', 'Japan', 35.3606, 138.7274, 'Highest mountain in Japan and an active stratovolcano.', 4.8),
    ('Lake Kawaguchi', 'Fujikawaguchiko', 'Japan', 35.5167, 138.7667, 'One of the five lakes near Mount Fuji, great views.', 4.7),
    ('Hakone', 'Hakone', 'Japan', 35.2320, 139.1060, 'Hot springs and scenic views of Mount Fuji.', 4.8),

    ('Dubai Mall', 'Dubai', 'UAE', 25.1972, 55.2797, 'The second largest mall in the world by total land area.', 4.5),
    ('Burj Khalifa', 'Dubai', 'UAE', 25.1972, 55.2744, 'World’s tallest building with observation decks.', 4.9),
    ('Al Ain Oasis', 'Al Ain', 'UAE', 24.2075, 55.7447, 'Historic oasis city inland in Abu Dhabi Emirate.', 4.7),

    ('Sydney Opera House', 'Sydney', 'Australia', -33.8568, 151.2153, 'Multi-venue performing arts centre at Sydney Harbour.', 4.7),
    ('Bondi Beach', 'Sydney', 'Australia', -33.8908, 151.2743, 'Popular beach for surfing and relaxation.', 4.8),
    ('Blue Mountains', 'Katoomba', 'Australia', -33.7180, 150.3119, 'Scenic mountains near Sydney with cliffs and waterfalls.', 4.9),

    ('The Maldives', 'Male', 'Maldives', 1.924992, 73.399658,'The Maldives is renowned for its white sandy beaches, turquoise waters, and vibrant marine life.', 5.0),
    ('Baa Atoll','Baa Atoll', 'Maldives', 5.1563, 73.0710, 'Area with coral reefs and amazing diving spots.', 4.9),

    ( 'Jemaa el-Fna', 'Marrakech', 'Morocco', 31.6258, -7.9891, 'Bustling main square in the old medina filled with food stalls and entertainers.', 4.7),
    ( 'Hassan II Mosque', 'Casablanca', 'Morocco', 33.6086, -7.6327, 'Stunning oceanside mosque with the world''s tallest minaret.', 4.9),
    ( 'Fes el-Bali Medina', 'Fes', 'Morocco', 34.0653, -4.9739, 'UNESCO-listed medieval medina, one of the world''s largest car-free urban areas.', 4.8),
    ( 'Aït Benhaddou', 'Ouarzazate', 'Morocco', 31.0472, -7.1319, 'Ancient fortified ksar along the former caravan route between the Sahara and Marrakech.', 4.9),

    ( 'Belem Tower', 'Lisbon', 'Portugal', 38.6916, -9.2160, 'Iconic 16th-century fortress on the Tagus River and UNESCO World Heritage site.', 4.7),
    ( 'Pena Palace', 'Sintra', 'Portugal', 38.7877, -9.3906, 'Colorful Romanticist palace perched atop the Sintra mountains.', 4.8),
    ( 'Douro Valley', 'Pinhão', 'Portugal', 41.1858, -7.5444, 'Terraced hillside wine region along the Douro River, a UNESCO World Heritage site.', 4.9),
    ( 'Cape Roca', 'Sintra', 'Portugal', 38.7831, -9.5006, 'Westernmost point of continental Europe with dramatic Atlantic Ocean cliffs.', 4.7),

    ( 'Rijksmuseum', 'Amsterdam', 'Netherlands', 52.3600, 4.8852, 'National museum housing Dutch Golden Age paintings including Rembrandt and Vermeer.', 4.9),
    ( 'Keukenhof Gardens', 'Lisse', 'Netherlands', 52.2697, 4.5469, 'Expansive flower gardens famous for their spectacular tulip displays each spring.', 4.8),
    ( 'Anne Frank House', 'Amsterdam', 'Netherlands', 52.3752, 4.8839, 'Historic canal house where Anne Frank wrote her diary during World War II.', 4.8),
    ( 'Kinderdijk Windmills', 'Kinderdijk', 'Netherlands', 51.8833, 4.6333, 'UNESCO-listed network of 19 windmills in a stunning polder landscape.', 4.8),

    ( 'Prague Castle', 'Prague', 'Czech Republic', 50.0903, 14.4013, 'Largest ancient castle complex in the world overlooking the city.', 4.8),
    ( 'Charles Bridge', 'Prague', 'Czech Republic', 50.0865, 14.4114, 'Medieval stone bridge decorated with baroque statues spanning the Vltava River.', 4.7),
    ( 'Český Krumlov Castle', 'Český Krumlov', 'Czech Republic', 48.8128, 14.3175, 'Fairy-tale UNESCO-listed castle town with a well-preserved medieval old town.', 4.9),
    ( 'Old Town Square', 'Prague', 'Czech Republic', 50.0875, 14.4213, 'Historic square featuring the famous Astronomical Clock and Gothic Tyn Church.', 4.7),

    ( 'Schönbrunn Palace', 'Vienna', 'Austria', 48.1845, 16.3122, 'Former imperial summer residence with baroque gardens and 1,441 rooms.', 4.8),
    ( 'Hallstatt Village', 'Hallstatt', 'Austria', 47.5622, 13.6493, 'Picturesque lakeside village in the Salzkammergut region, a UNESCO World Heritage site.', 4.9),
    ( 'Grossglockner Road', 'Heiligenblut', 'Austria', 47.0743, 12.8376, 'Austria''s highest mountain pass road with breathtaking Alpine panoramas.', 4.8),
    ( 'Belvedere Palace', 'Vienna', 'Austria', 48.1914, 16.3806, 'Baroque palace complex housing Klimt''s famous painting The Kiss.', 4.8),

    ( 'Matterhorn', 'Zermatt', 'Switzerland', 45.9763, 7.6586, 'One of the highest and most iconic peaks in the Alps on the Swiss-Italian border.', 5.0),
    ( 'Rhine Falls', 'Schaffhausen', 'Switzerland', 47.6780, 8.6145, 'The largest plain waterfall in Europe, a dramatic natural spectacle.', 4.8),
    ( 'Jungfraujoch', 'Grindelwald', 'Switzerland', 46.5472, 7.9853, 'Top of Europe railway station at 3,454m with glacier views and an ice palace.', 4.9),
    ( 'Lake Geneva', 'Lausanne', 'Switzerland', 46.5197, 6.6323, 'Stunning crescent-shaped lake on the Swiss-French border with the Jet d''Eau fountain.', 4.8),

    ( 'Golden Circle', 'Þingvellir', 'Iceland', 64.2559, -20.6044, 'Popular tourist route covering geysers, waterfalls and a national park.', 4.9),
    ( 'Blue Lagoon', 'Grindavík', 'Iceland', 63.8800, -22.4496, 'Famous geothermal spa set in a dramatic lava field landscape.', 4.7),
    ( 'Skógafoss Waterfall', 'Skógar', 'Iceland', 63.5320, -19.5116, 'Majestic 60-metre waterfall on the Skógá River with a rainbow in sunny weather.', 4.9),
    ( 'Jökulsárlón Glacier Lagoon', 'Vatnajökull', 'Iceland', 64.0784, -16.2306, 'Glacial lake filled with floating icebergs calved from the Breiðamerkurjökull glacier.', 5.0),

    ( 'Geirangerfjord', 'Geiranger', 'Norway', 62.1040, 7.2067, 'UNESCO-listed fjord surrounded by towering mountains and cascading waterfalls.', 5.0),
    ( 'Bryggen Wharf', 'Bergen', 'Norway', 60.3975, 5.3244, 'Historic Hanseatic wharf with colourful wooden buildings, a UNESCO World Heritage site.', 4.8),
    ( 'Trolltunga', 'Odda', 'Norway', 60.1238, 6.7390, 'Dramatic rock formation jutting horizontally out of a mountain 700m above Lake Ringedalsvatnet.', 4.9),
    ( 'Lofoten Islands', 'Svolvær', 'Norway', 68.2328, 14.5680, 'Stunning Arctic archipelago with dramatic peaks, fishing villages, and the northern lights.', 5.0),

    ( 'Hagia Sophia', 'Istanbul', 'Turkey', 41.0086, 28.9802, 'Ancient cathedral and mosque that has dominated Istanbul''s skyline for 1,500 years.', 4.9),
    ( 'Cappadocia', 'Göreme', 'Turkey', 38.6431, 34.8289, 'Surreal landscape of fairy chimneys and cave dwellings, famous for hot-air ballooning.', 5.0),
    ( 'Pamukkale', 'Denizli', 'Turkey', 37.9197, 29.1194, 'Natural terraces of white travertine pools fed by thermal mineral waters.', 4.8),
    ( 'Ephesus', 'Selçuk', 'Turkey', 37.9395, 27.3408, 'Remarkably preserved ancient Greek city with the remains of the Library of Celsus.', 4.9),

    ( 'Pyramids of Giza', 'Giza', 'Egypt', 29.9792, 31.1342, 'Ancient wonder of the world — the only one still standing.', 5.0),
    ( 'Luxor Temple', 'Luxor', 'Egypt', 25.6996, 32.6392, 'Large ancient Egyptian temple complex on the east bank of the Nile.', 4.8),
    ( 'Abu Simbel', 'Aswan', 'Egypt', 22.3372, 31.6258, 'Massive rock temples of Ramesses II relocated to avoid the Nile flood waters.', 4.9),
    ( 'Valley of the Kings', 'Luxor', 'Egypt', 25.7402, 32.6014, 'Royal burial ground containing tombs of pharaohs including Tutankhamun.', 4.9),

    ( 'Taj Mahal', 'Agra', 'India', 27.1751, 78.0421, 'Iconic white marble mausoleum and UNESCO World Heritage site.', 5.0),
    ( 'Amber Fort', 'Jaipur', 'India', 26.9855, 75.8513, 'Majestic hilltop fort blending Rajput and Mughal architecture.', 4.8),
    ( 'Kerala Backwaters', 'Alleppey', 'India', 9.4981, 76.3388, 'Network of serene canals, lagoons, and lakes lined with coconut palms.', 4.8),
    ( 'Varanasi Ghats', 'Varanasi', 'India', 25.3176, 83.0062, 'Sacred riverside steps along the Ganges, one of the world''s oldest living cities.', 4.7),

    ( 'Wat Phra Kaew', 'Bangkok', 'Thailand', 13.7516, 100.4920, 'Temple of the Emerald Buddha within the grounds of the Grand Palace.', 4.9),
    ( 'Phi Phi Islands', 'Krabi', 'Thailand', 7.7407, 98.7784, 'Stunning tropical island group with turquoise waters and dramatic limestone cliffs.', 4.8),
    ( 'Doi Inthanon', 'Chiang Mai', 'Thailand', 18.5877, 98.4862, 'Thailand''s highest peak with waterfalls, hilltribe villages, and royal pagodas.', 4.7),
    ( 'Ayutthaya', 'Ayutthaya', 'Thailand', 14.3556, 100.5659, 'Ancient capital with sprawling temple ruins, a UNESCO World Heritage site.', 4.8),

    ( 'Chichen Itza', 'Yucatán', 'Mexico', 20.6843, -88.5678, 'Ancient Mayan city and UNESCO World Heritage site with the iconic El Castillo pyramid.', 4.9),
    ( 'Teotihuacan', 'Mexico City', 'Mexico', 19.6925, -98.8438, 'Ancient Mesoamerican city featuring the massive Pyramids of the Sun and Moon.', 4.8),
    ( 'Cenote Ik Kil', 'Yucatán', 'Mexico', 20.6686, -88.5736, 'Spectacular open-air sinkhole cenote near Chichen Itza used for sacred Mayan rituals.', 4.8),
    ( 'Copper Canyon', 'Chihuahua', 'Mexico', 27.5325, -107.7108, 'Series of canyons larger and deeper than the Grand Canyon, home to the Tarahumara people.', 4.9),

    ( 'Christ the Redeemer', 'Rio de Janeiro', 'Brazil', -22.9519, -43.2105, 'Iconic Art Deco statue of Jesus Christ atop Corcovado mountain.', 4.9),
    ( 'Iguazu Falls', 'Foz do Iguaçu', 'Brazil', -25.6953, -54.4367, 'Spectacular system of waterfalls on the border of Brazil and Argentina.', 5.0),
    ( 'Amazon Rainforest', 'Manaus', 'Brazil', -3.1190, -60.0217, 'World''s largest tropical rainforest with unparalleled biodiversity.', 5.0),
    ( 'Lençóis Maranhenses', 'Barreirinhas', 'Brazil', -2.5198, -43.1297, 'Vast coastal dunes filled with crystal-clear seasonal lagoons.', 4.9),

    ( 'Milford Sound', 'Fiordland', 'New Zealand', -44.6413, 167.8974, 'Dramatic fiord in Fiordland National Park with towering peaks and waterfalls.', 5.0),
    ( 'Hobbiton', 'Matamata', 'New Zealand', -37.8572, 175.6822, 'Real-life movie set from The Lord of the Rings films set in rolling green hills.', 4.7),
    ( 'Tongariro Alpine Crossing', 'Ruapehu', 'New Zealand', -39.1561, 175.5927, 'World-class one-day hike across volcanic craters, emerald lakes, and ancient lava flows.', 4.9),
    ( 'Abel Tasman National Park', 'Nelson', 'New Zealand', -40.8912, 172.9972, 'Stunning coastal park with golden beaches, turquoise sea, and native forest.', 4.9)
ON CONFLICT (name) DO NOTHING;

INSERT INTO app_users (name, username, email, birthday, password)
VALUES
    ( 'Ralica Petrova', 'ralica_p', 'ralica_s@gmail.com', '2000-01-15', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    ( 'Ivan Georgiev', 'ivan_g', 'ivan_g@gmail.com', '1995-05-20', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    ( 'Ivailo Kolev', 'ivailo_k', 'ivailo_k@gmail.com', '1988-11-02', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    ( 'Maria Popova', 'maria_p', 'maria_p@gmail.com', '1993-08-24', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    ( 'Vili Lazarova', 'vili_l', 'vili_l@gmail.com', '1999-04-12', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a'),
    ( 'Cveti Todorova', 'cveti_t', 'cveti_t@gmail.com', '2002-12-30', '$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.7uqqQ3a')
ON CONFLICT (username) DO NOTHING;

INSERT INTO trip (user_id, name, start_date, end_date,created_date)
VALUES
    (1,'Summer Vacation', '2025-07-20','2025-07-28','2025-07-02'),
    (2,'Spain','2025-05-01','2025-05-06','2025-05-01'),
    (3,'Hiking trip', '2025-06-18','2025-06-24','2025-06-10'),
    (3,'Motherland trip','2024-11-12','2024-11-20','2024-10-13'),
    (4,'Italy','2025-09-10','2025-09-15','2025-09-02'),
    (5,'Love city','2025-03-27','2025-04-01','2025-03-22'),
    (6,'Greece','2025-08-22','2025-08-26','2025-07-02');

INSERT INTO trip_destination ( trip_id, destination_id, day_index)
VALUES
    (1,31,1),
    (1,31,2),
    (1,31,3),
    (1,31,4),
    (1,32,5),
    (1,32,6),
    (1,32,7),
    (1,32,8),

    (2,7,1),
    (2,7,2),
    (2,8,3),
    (2,9,4),
    (2,9,5),

    (3,23,1),
    (3,23,2),
    (3,22,3),
    (3,22,4),
    (3,24,5),
    (3,24,6),

    (4,13,1),
    (4,13,2),
    (4,13,3),
    (4,13,4),
    (4,13,5),
    (4,14,6),
    (4,14,7),
    (4,14,8),
    (4,15,9),

    (5,4,1),
    (5,4,2),
    (5,5,3),
    (5,6,4),
    (5,6,5),

    (6,1,1),
    (6,1,2),
    (6,2,3),
    (6,2,4),
    (6,2,5),
    (6,3,6),

    (7,10,1),
    (7,10,2),
    (7,11,3),
    (7,11,4);

INSERT INTO user_favourites (user_id, trip_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 3);






