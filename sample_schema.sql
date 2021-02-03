DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
    `id` varchar(36) NOT NULL,
    `badge` varchar(36) NOT NULL,
    `name` varchar(100) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

DROP TABLE IF EXISTS `sequencer`;
CREATE TABLE `sequencer` (
    `id` varchar(36) NOT NULL,
    `prefix` varchar(36) NOT NULL,
    `next_sequence` int(11) NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB;

INSERT INTO `sequencer` VALUES ('id-1','WAL',100);