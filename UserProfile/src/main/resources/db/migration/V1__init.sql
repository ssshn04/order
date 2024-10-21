CREATE TABLE `t_users` (
   `id` INT AUTO_INCREMENT,
   `name` VARCHAR(50) NOT NULL,
   `surname` VARCHAR(50) NOT NULL,
   `age` INT,
   `nickname` VARCHAR(50) NOT NULL,
   `email` VARCHAR(100) NOT NULL UNIQUE,
   `sex` BOOLEAN NOT NULL,
   `description` VARCHAR(255),
   `role` VARCHAR(20) NOT NULL CHECK (role IN ('Owner', 'Professional')),
   `is_verified` BOOLEAN DEFAULT FALSE,
   `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP,
   `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
);