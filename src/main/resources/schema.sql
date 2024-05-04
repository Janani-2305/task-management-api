CREATE TABLE IF NOT EXISTS `task` (
  `id` int AUTO_INCREMENT  PRIMARY KEY,
  `name` varchar(100) NOT NULL,
  `description` varchar(300) NOT NULL,
  `priority` int NOT NULL,
  `completed` BOOLEAN DEFAULT 0 NOT NULL,
  `status` varchar(20) NOT NULL,
  `created_at` date DEFAULT NULL,
  `created_by` varchar(20) DEFAULT NULL,
  `updated_at` date DEFAULT NULL,
  `updated_by` varchar(20) DEFAULT NULL
);