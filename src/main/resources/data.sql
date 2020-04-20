DROP TABLE IF EXISTS `About` cascade ;
DROP TABLE IF EXISTS `Game_Platforms` cascade;
DROP TABLE IF EXISTS `Platforms` cascade ;
DROP TABLE IF EXISTS `Games` cascade ;


-- Table `About`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `About` ;

CREATE TABLE IF NOT EXISTS `About` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `version_num` VARCHAR(10) NOT NULL,
  `version_name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Games`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Games` ;

CREATE TABLE IF NOT EXISTS `Games` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `steam_app_id` BIGINT NULL,
  `required_age` INT NULL DEFAULT 0,
  `free` TINYINT(1) NULL,
  `desc` TEXT NULL,
  `website` VARCHAR(255) NULL,
  `dev` VARCHAR(255) NOT NULL,
  `publishers` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `steam_app_id_UNIQUE` (`steam_app_id` ASC));


-- -----------------------------------------------------
-- Table `Platforms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Platforms` ;

CREATE TABLE IF NOT EXISTS `Platforms` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `platform` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `Game_Platforms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Game_Platforms` ;

CREATE TABLE IF NOT EXISTS `Game_Platforms` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `game_id` BIGINT NOT NULL,
  `platform_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_GPxG_PlatformID`
    FOREIGN KEY (`platform_id`)
    REFERENCES `Platforms` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_GPxG_GameID`
    FOREIGN KEY (`game_id`)
    REFERENCES `Games` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);

-- -----------------------------------------------------
-- Data for table `About`
-- -----------------------------------------------------

INSERT INTO `About` ( `version_num`, `version_name`) VALUES ( '00001', 'Xögun I');
INSERT INTO `About` ( `version_num`, `version_name`) VALUES ( '00013', 'Xögun II');
INSERT INTO `About` ( `version_num`, `version_name`) VALUES ( '00026', 'Xögun III');
INSERT INTO `About` ( `version_num`, `version_name`) VALUES ( '00026', 'Xögun III');


-- -----------------------------------------------------
-- Data for table `Games`
-- -----------------------------------------------------

--INSERT INTO `Games` ( `name`, `steam_app_id`, `required_age`, `free`, `desc`, `website`, `dev`, `publishers`) VALUES ( 'Dota 2', 570, 0, true, 'pew pew pew', 'www.dota2.com', 'Valve', 'Valve');
--INSERT INTO `Games` ( `name`, `steam_app_id`, `required_age`, `free`, `desc`, `website`, `dev`, `publishers`) VALUES ( 'Counter Strike GO', 730, 0, true, 'ratatatatata boom', 'www.csgo.com', 'Valve', 'Valve');
--INSERT INTO `Games` ( `name`, `steam_app_id`, `required_age`, `free`, `desc`, `website`, `dev`, `publishers`) VALUES ( 'Monster Hunter', 1450, 0, false, 'muuuuuu', 'www.capcom.com/monsterhunter', 'Capcom', 'Capcom');


-- -----------------------------------------------------
-- Data for table `Platforms`
-- -----------------------------------------------------

INSERT INTO `Platforms` ( `platform`) VALUES ( 'windows');
INSERT INTO `Platforms` ( `platform`) VALUES ( 'mac');
INSERT INTO `Platforms` ( `platform`) VALUES ( 'linux');



-- -----------------------------------------------------
-- Data for table `GamePlatforms`
-- -----------------------------------------------------

-- INSERT into GAME_PLATFORMS (platform_id, game_id) values
--((select p.id from Platforms p where p.platform = 'windows'), (select g.id from Games g where g.steam_app_id = 570)),
--((select p.id from Platforms p where p.platform = 'mac'), (select g.id from Games g where g.steam_app_id = 570));
--
--
-- INSERT into GAME_PLATFORMS (platform_id, game_id) values
--((select p.id from Platforms p where p.platform = 'windows'), (select g.id from Games g where g.steam_app_id = 730)),
--((select p.id from Platforms p where p.platform = 'mac'), (select g.id from Games g where g.steam_app_id = 730)),
--((select p.id from Platforms p where p.platform = 'linux'), (select g.id from Games g where g.steam_app_id = 730));
--
-- INSERT into GAME_PLATFORMS (platform_id, game_id) values
--((select p.id from Platforms p where p.platform = 'windows'), (select g.id from Games g where g.steam_app_id = 1450));

-- -----------------------------------------------------
-- Perhaps it might be a View one day...
-- -----------------------------------------------------

-- SELECT
-- g.id,
-- g.name,
-- CASEWHEN((SELECT count(1) FROM GAME_PLATFORMS gp WHERE gp.game_id = g.id and gp.platform_id = 1) > 0, true, false) AS Windows ,
-- CASEWHEN((SELECT count(1) FROM GAME_PLATFORMS gp WHERE gp.game_id = g.id and gp.platform_id = 2) > 0, true, false) AS Mac,
-- CASEWHEN((SELECT count(1) FROM GAME_PLATFORMS gp WHERE gp.game_id = g.id and gp.platform_id = 3) > 0, true, false) AS Linux
-- FROM GAMES g  WHERE 1=1;