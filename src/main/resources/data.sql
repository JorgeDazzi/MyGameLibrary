DROP TABLE IF EXISTS api_about;

CREATE TABLE api_about (
  id bigint AUTO_INCREMENT  PRIMARY KEY,
  version_num VARCHAR(10) NOT NULL,
  version_name VARCHAR(20) NOT NULL
);

INSERT INTO api_about (version_num, version_name) VALUES
  ('00001', 'Xögun'),
  ('00013', 'Xögun II'),
  ('00026', 'Xögun III');

INSERT into Library
(id, desc, dev, free, name, publishers, required_age, steam_app_id, website) VALUES
(null, 'dashdashboom', 'Valve', true, 'Dota 2', 'Valve', 0, 570, 'www.dota2.com'),
(null, 'pewpewboom', 'Valve', true, 'DmC 5', 'Valve', 0, 730, 'www.dmc.com');


INSERT into Platforms (platform) values
('windows'),
('mac'),
('linux');


 INSERT into GAME_PLATFORMS (platform_id, game_id) values
((select p.id from Platforms p where p.platform = 'windows'), (select g.id from Library g where g.steam_app_id = 570)),
((select p.id from Platforms p where p.platform = 'mac'), (select g.id from Library g where g.steam_app_id = 570));
--((select p.id from Platforms p where p.platform = 'linux'), (select g.id from Library g where g.steam_app_id = 570));

--Select l.name, p.platform from library l, platforms pp
--join game_platforms g on g.game_id = l.id
--join platforms p on p.id = g.platform_id where 1=1;