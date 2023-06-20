-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema fuel_station
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema fuel_station
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema fuel_station
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `fuel_station` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `fuel_station` ;

-- -----------------------------------------------------
-- Table `fuel_station`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`customer` (
  `idc` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(25) NOT NULL,
  `car_number` VARCHAR(16) NOT NULL,
  PRIMARY KEY (`idc`))
ENGINE = InnoDB
AUTO_INCREMENT = 10
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fuel_station`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`employee` (
  `ide` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(25) NOT NULL,
  `hire_date_start` DATETIME NOT NULL,
  `hire_date_end` DATETIME NOT NULL,
  `salary_per_month` DECIMAL(6,2) NOT NULL,
  `employee_type` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`ide`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fuel_station`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`item` (
  `idi` INT NOT NULL AUTO_INCREMENT,
  `code` VARCHAR(20) NOT NULL,
  `description` VARCHAR(30) NOT NULL,
  `item_type` VARCHAR(20) NOT NULL,
  `price` DECIMAL(6,2) NOT NULL,
  `cost` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`idi`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fuel_station`.`ledger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`ledger` (
  `year` DATETIME NOT NULL,
  `month` DATETIME NOT NULL,
  `income` DECIMAL(6,2) NOT NULL,
  `expenses` DECIMAL(6,2) NOT NULL,
  `total` DECIMAL(6,2) NOT NULL,
  PRIMARY KEY (`year`, `month`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fuel_station`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`transaction` (
  `idt` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `payment_method` VARCHAR(25) NOT NULL,
  `total_value` DECIMAL(9,2) NOT NULL,
  `idc` INT NOT NULL,
  `ide` INT NOT NULL,
  PRIMARY KEY (`idt`, `idc`, `ide`),
  INDEX `fk_transaction_customer_idx` (`idc` ASC) VISIBLE,
  INDEX `fk_transaction_employee1_idx` (`ide` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_customer`
    FOREIGN KEY (`idc`)
    REFERENCES `fuel_station`.`customer` (`idc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_employee1`
    FOREIGN KEY (`ide`)
    REFERENCES `fuel_station`.`employee` (`ide`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `fuel_station`.`transaction_line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `fuel_station`.`transaction_line` (
  `idtl` INT NOT NULL AUTO_INCREMENT,
  `quantity` INT NOT NULL,
  `item_price` DECIMAL(6,2) NOT NULL,
  `net_value` DECIMAL(6,2) NULL DEFAULT NULL,
  `discount_percent` INT NOT NULL,
  `discount_value` DECIMAL(6,2) NOT NULL,
  `total_value` DECIMAL(6,2) NOT NULL,
  `idt` INT NOT NULL,
  `idc` INT NOT NULL,
  `idi` INT NOT NULL,
  PRIMARY KEY (`idtl`, `idt`, `idc`, `idi`),
  INDEX `fk_transaction_line_transaction1_idx` (`idt` ASC, `idc` ASC) VISIBLE,
  INDEX `fk_transaction_line_item1_idx` (`idi` ASC) VISIBLE,
  CONSTRAINT `fk_transaction_line_transaction1`
    FOREIGN KEY (`idt` , `idc`)
    REFERENCES `fuel_station`.`transaction` (`idt` , `idc`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_transaction_line_item1`
    FOREIGN KEY (`idi`)
    REFERENCES `fuel_station`.`item` (`idi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
