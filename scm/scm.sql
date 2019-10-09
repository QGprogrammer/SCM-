/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : scm

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 09/10/2019 17:14:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `CategoryID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`CategoryID`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, '水果', NULL);

-- ----------------------------
-- Table structure for checkstock
-- ----------------------------
DROP TABLE IF EXISTS `checkstock`;
CREATE TABLE `checkstock`  (
  `StockID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OriginNum` int(11) NULL DEFAULT NULL,
  `RealNum` int(11) NULL DEFAULT NULL,
  `StockTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateUser` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`StockID`) USING BTREE,
  INDEX `FK_C_S`(`ProductCode`) USING BTREE,
  CONSTRAINT `FK_C_S` FOREIGN KEY (`ProductCode`) REFERENCES `product` (`ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of checkstock
-- ----------------------------
INSERT INTO `checkstock` VALUES (1, '201802051', 20, -30, '2019-08-07 13:46:22', 'wangwenwen', '不知道哪里多的', 'profit');
INSERT INTO `checkstock` VALUES (2, '201802051', -30, -40, '2019-08-07 13:48:46', 'wangwenwen', '11', 'profit');
INSERT INTO `checkstock` VALUES (3, '201802051', -40, -151, '2019-08-07 13:49:49', 'wangwenwen', '11', 'profit');
INSERT INTO `checkstock` VALUES (4, '201802051', -151, 49, '2019-08-07 13:50:30', 'wangwenwen', '偷来的', 'profit');
INSERT INTO `checkstock` VALUES (5, '201902114', 30, 10, '2019-08-07 13:50:44', 'wangwenwen', '过期', 'waste');
INSERT INTO `checkstock` VALUES (6, '201902114', 10, 9, '2019-08-07 14:03:06', 'wangwenwen', '反覆', 'waste');
INSERT INTO `checkstock` VALUES (7, '201802051', 49, 44, '2019-08-13 15:10:47', 'wangwenwen', '1', 'waste');
INSERT INTO `checkstock` VALUES (8, '201802051', 44, 39, '2019-08-13 23:33:13', 'wangwenwen', '11', 'waste');
INSERT INTO `checkstock` VALUES (9, '201802051', 39, 34, '2019-08-14 13:28:21', 'wangwenwen', '4', 'waste');
INSERT INTO `checkstock` VALUES (10, '201802051', 34, 39, '2019-08-14 13:28:28', 'wangwenwen', '4', 'profit');
INSERT INTO `checkstock` VALUES (11, '201802051', 39, 34, '2019-08-14 22:45:05', 'wangwenwen', '过期了', 'waste');
INSERT INTO `checkstock` VALUES (12, '201802051', 34, 14, '2019-08-16 13:50:18', 'wangwenwen', '123', 'waste');
INSERT INTO `checkstock` VALUES (13, '201802051', 14, 64, '2019-08-16 13:53:28', 'wangwenwen', '多了', 'profit');
INSERT INTO `checkstock` VALUES (14, '201902083', 30, 28, '2019-08-16 15:20:18', 'wangwenwen', '1', 'waste');
INSERT INTO `checkstock` VALUES (15, '201802051', 64, 60, '2019-08-16 16:32:09', 'wangwenwen', 'huaile', 'waste');
INSERT INTO `checkstock` VALUES (16, '201802051', 60, 55, '2019-08-16 16:32:38', 'wangwenwen', 'a', 'waste');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer`  (
  `CustomerCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Contactor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Postcode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Fax` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateDate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`CustomerCode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('201903', 'laowang', '1', '1', '1', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for payrecord
-- ----------------------------
DROP TABLE IF EXISTS `payrecord`;
CREATE TABLE `payrecord`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_time` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pay_price` decimal(12, 2) NULL DEFAULT NULL,
  `account` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ordercode` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `pay_type` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payrecord
-- ----------------------------
INSERT INTO `payrecord` VALUES (22, '2019-08-15 10:09:50', 50.00, '财务', '20190813071533', 3);
INSERT INTO `payrecord` VALUES (23, '2019-08-15 10:12:12', 50.00, '财务', '20190814120219', 3);
INSERT INTO `payrecord` VALUES (24, '2019-08-15 10:18:23', 249.80, '财务', '20190813071513', 2);
INSERT INTO `payrecord` VALUES (25, '2019-08-15 10:18:26', 50.00, '财务', '20190813071522', 3);
INSERT INTO `payrecord` VALUES (26, '2019-08-15 10:18:29', 549.80, '财务', '20190813171202', 1);
INSERT INTO `payrecord` VALUES (27, '2019-08-15 10:18:31', 50.00, '财务', '20190814120213', 3);
INSERT INTO `payrecord` VALUES (28, '2019-08-15 10:18:34', 50.00, '财务', '20190814120240', 3);
INSERT INTO `payrecord` VALUES (29, '2019-08-15 10:18:38', 1.00, '财务', '11', 1);
INSERT INTO `payrecord` VALUES (30, '2019-08-15 10:18:41', 50.00, '财务', '20190813071533', 3);
INSERT INTO `payrecord` VALUES (31, '2019-08-15 10:18:44', 50.00, '财务', '20190813071522', 3);
INSERT INTO `payrecord` VALUES (32, '2019-08-15 10:18:46', 249.80, '财务', '20190813071513', 2);
INSERT INTO `payrecord` VALUES (33, '2019-08-15 10:19:27', 50.00, '财务', '20190813071533', 3);
INSERT INTO `payrecord` VALUES (34, '2019-08-15 10:19:43', 50.00, '财务', '20190814120225', 3);
INSERT INTO `payrecord` VALUES (35, '2019-08-15 10:20:09', 299.80, '财务', '20190814120213', 3);
INSERT INTO `payrecord` VALUES (36, '2019-08-15 10:21:56', 199.80, '财务', '20190813071522', 3);
INSERT INTO `payrecord` VALUES (37, '2019-08-16 13:53:37', 299.80, '财务', '20190814120225', 3);
INSERT INTO `payrecord` VALUES (38, '2019-08-16 13:53:41', 50.00, '财务', '20190814120225', 3);
INSERT INTO `payrecord` VALUES (39, '2019-08-16 16:33:01', 50.00, '财务', '20190814120213', 3);
INSERT INTO `payrecord` VALUES (40, '2019-08-16 16:34:02', 50.00, '财务', '20190814120240', 3);

-- ----------------------------
-- Table structure for poitem
-- ----------------------------
DROP TABLE IF EXISTS `poitem`;
CREATE TABLE `poitem`  (
  `POID` decimal(14, 0) NOT NULL,
  `ProductCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UnitPrice` float NOT NULL,
  `Num` int(11) NOT NULL,
  `UnitName` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ItemPrice` float NOT NULL,
  PRIMARY KEY (`POID`, `ProductCode`) USING BTREE,
  INDEX `FK_P_P`(`ProductCode`) USING BTREE,
  CONSTRAINT `FK_PD_PM` FOREIGN KEY (`POID`) REFERENCES `pomain` (`POID`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_P_P` FOREIGN KEY (`ProductCode`) REFERENCES `product` (`ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of poitem
-- ----------------------------
INSERT INTO `poitem` VALUES (20190813071513, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190813071522, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190813071533, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190813171202, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190814120213, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190814120219, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190814120225, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190814120235, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `poitem` VALUES (20190814120244, '201902062', 4.98, 10, 'kg', 49.8);

-- ----------------------------
-- Table structure for pomain
-- ----------------------------
DROP TABLE IF EXISTS `pomain`;
CREATE TABLE `pomain`  (
  `POID` decimal(14, 0) NOT NULL,
  `VenderCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TipFee` float NOT NULL,
  `ProductTotal` float NOT NULL,
  `POTotal` float NOT NULL,
  `PayType` int(11) NOT NULL,
  `PrePayFee` float NOT NULL,
  `Status` int(11) NOT NULL,
  `Remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `StockTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `StockUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PayTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PayUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PrePayTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PrePayUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `EndTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `EndUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`POID`) USING BTREE,
  INDEX `FK_P_V`(`VenderCode`) USING BTREE,
  INDEX `FK_P_S`(`Account`) USING BTREE,
  CONSTRAINT `FK_P_S` FOREIGN KEY (`Account`) REFERENCES `scmuser` (`Account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_P_V` FOREIGN KEY (`VenderCode`) REFERENCES `vender` (`VenderCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pomain
-- ----------------------------
INSERT INTO `pomain` VALUES (11, '1', 'ven', '1', 1, 1, 1, 1, 1, 3, '1', '1', '1', '2019-08-15 10:18:38', 'ven', '', NULL, NULL, NULL);
INSERT INTO `pomain` VALUES (20190813071513, '201903', 'ven', '2019-08-11 17:21:11', 200, 49.8, 249.8, 2, 0, 2, NULL, '', '', '2019-08-15 10:18:46', 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190813071522, '201903', 'ven', '2019-08-11 17:22:13', 200, 49.8, 249.8, 3, 50, 1, NULL, NULL, NULL, NULL, 'ven', '2019-08-15 10:18:44', 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190813071533, '201903', 'ven', '2019-08-11 17:23:10', 250, 49.8, 299.8, 3, 50, 1, NULL, NULL, NULL, NULL, 'ven', '2019-08-15 10:18:41', 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190813171202, '201903', 'ven', '2019-08-11 17:20:00', 500, 49.8, 549.8, 1, 0, 2, NULL, '2019', 'ven11', NULL, 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190814120213, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 5, NULL, '2019', 'ven11', NULL, 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190814120219, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 1, NULL, '2019', 'ven11', NULL, 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190814120225, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 5, NULL, '2019', 'ven11', NULL, 'ven', '2019-08-16 13:53:41', 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190814120235, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 1, NULL, '2019', 'ven11', NULL, 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `pomain` VALUES (20190814120244, '201903', 'ven', '2019-08-15 13:15:20', 400, 49.8, 449.8, 1, 50, 3, '1', NULL, NULL, NULL, 'ven', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `ProductCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CategoryID` int(11) NULL DEFAULT NULL,
  `Name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UnitName` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Price` float NOT NULL,
  `CreateDate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `PONum` int(11) NOT NULL,
  `SONum` int(11) NOT NULL,
  PRIMARY KEY (`ProductCode`) USING BTREE,
  INDEX `FK_P_C`(`CategoryID`) USING BTREE,
  CONSTRAINT `FK_P_C` FOREIGN KEY (`CategoryID`) REFERENCES `category` (`CategoryID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('201802051', 1, '苹果', 'kg', 3.96, '2019-08-06', '', 55, 10, 5);
INSERT INTO `product` VALUES ('201902062', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902083', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 28, 2, 6);
INSERT INTO `product` VALUES ('201902114', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 9, 2, 6);
INSERT INTO `product` VALUES ('201902120', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902134', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902164', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902195', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902201', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902226', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902227', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902229', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902230', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902236', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902239', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902242', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902244', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902249', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902251', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902255', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902257', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902259', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902281', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902283', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);
INSERT INTO `product` VALUES ('201902285', 1, '栗', 'kg', 4.98, '2019-8-3', NULL, 30, 2, 6);

-- ----------------------------
-- Table structure for scmuser
-- ----------------------------
DROP TABLE IF EXISTS `scmuser`;
CREATE TABLE `scmuser`  (
  `Account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateDate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Status` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`Account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of scmuser
-- ----------------------------
INSERT INTO `scmuser` VALUES ('lisi', 'lisi', '123a', '2019-08-14 16:25:51', 0);
INSERT INTO `scmuser` VALUES ('ven', 'ven', 'hahaa3', '2019-08-08 13:19:51', 1);
INSERT INTO `scmuser` VALUES ('ven123', 'ven123', 'aaa', '2019-08-08 14:00:18', 0);
INSERT INTO `scmuser` VALUES ('wangwu', 'wangwu', '5652', '2019-08-16 13:53:03', 0);
INSERT INTO `scmuser` VALUES ('xiaoyu', 'xiaoyu', '123', '2019-08-14 22:44:22', 0);
INSERT INTO `scmuser` VALUES ('zhangsan', 'zhangsan', 'aaa', '2019-08-14 16:16:32', 0);
INSERT INTO `scmuser` VALUES ('zheng', 'zheng', '123', '2019-08-09 13:00:26', 0);

-- ----------------------------
-- Table structure for soitem
-- ----------------------------
DROP TABLE IF EXISTS `soitem`;
CREATE TABLE `soitem`  (
  `SOID` decimal(14, 0) NOT NULL,
  `ProductCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `UnitPrice` float NOT NULL,
  `Num` int(11) NOT NULL,
  `UnitName` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ItemPrice` float NOT NULL,
  PRIMARY KEY (`SOID`, `ProductCode`) USING BTREE,
  INDEX `FK_S_P`(`ProductCode`) USING BTREE,
  CONSTRAINT `FK_S_P` FOREIGN KEY (`ProductCode`) REFERENCES `product` (`ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_S_S` FOREIGN KEY (`SOID`) REFERENCES `somain` (`SOID`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of soitem
-- ----------------------------
INSERT INTO `soitem` VALUES (20190813071513, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190813071522, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190813071533, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190813171202, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190814120213, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190814120219, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190814120225, '201902062', 4.98, 10, 'kg', 49.8);
INSERT INTO `soitem` VALUES (20190814120235, '201902062', 4.98, 10, 'kg', 49.8);

-- ----------------------------
-- Table structure for somain
-- ----------------------------
DROP TABLE IF EXISTS `somain`;
CREATE TABLE `somain`  (
  `SOID` decimal(14, 0) NOT NULL,
  `CustomerCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `TipFee` float NOT NULL,
  `ProductTotal` float NOT NULL,
  `POTotal` float NOT NULL,
  `PayType` int(11) NOT NULL,
  `PrePayFee` float NOT NULL,
  `Status` int(11) NOT NULL,
  `Remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `StockTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `StockUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PayTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PayUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PrePayTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `PrePayUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `EndTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `EndUser` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SOID`) USING BTREE,
  INDEX `FK_S_C`(`CustomerCode`) USING BTREE,
  INDEX `FK_S_U`(`Account`) USING BTREE,
  CONSTRAINT `FK_S_C` FOREIGN KEY (`CustomerCode`) REFERENCES `customer` (`CustomerCode`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_S_U` FOREIGN KEY (`Account`) REFERENCES `scmuser` (`Account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of somain
-- ----------------------------
INSERT INTO `somain` VALUES (20190813071513, '201903', 'ven', '2019-08-11 17:21:11', 200, 49.8, 249.8, 2, 0, 2, NULL, '', '', '2019-08-15 10:18:23', 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190813071522, '201903', 'ven', '2019-08-11 17:22:13', 200, 49.8, 249.8, 3, 50, 3, NULL, NULL, NULL, '2019-08-15 10:21:56', 'ven', '2019-08-15 10:18:26', 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190813071533, '201903', 'ven', '2019-08-11 17:23:10', 250, 49.8, 299.8, 3, 50, 5, NULL, NULL, NULL, NULL, 'ven', '2019-08-15 10:19:27', 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190813171202, '201903', 'ven', '2019-08-11 17:20:00', 500, 49.8, 549.8, 1, 0, 3, NULL, '2019', 'ven11', '2019-08-15 10:18:29', 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190814120213, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 5, NULL, '2019', 'ven11', NULL, 'ven', '2019-08-16 16:33:01', 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190814120219, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 5, NULL, '2019', 'ven11', NULL, 'ven', '2019-08-15 10:12:12', 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190814120225, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 1, NULL, '2019', 'ven11', '2019-08-16 13:53:37', 'ven', '2019-08-15 10:19:43', 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190814120235, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 1, NULL, '2019', 'ven11', NULL, 'ven', NULL, 'ven', NULL, NULL);
INSERT INTO `somain` VALUES (20190814120240, '201903', 'ven', '2019-08-14 12:05:22', 300, 49.8, 349.8, 3, 50, 5, NULL, '2019', 'ven11', NULL, 'ven', '2019-08-16 16:34:02', 'ven', NULL, NULL);

-- ----------------------------
-- Table structure for stockrecord
-- ----------------------------
DROP TABLE IF EXISTS `stockrecord`;
CREATE TABLE `stockrecord`  (
  `StockID` int(11) NOT NULL AUTO_INCREMENT,
  `ProductCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `OrderCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `StockNum` int(11) NOT NULL,
  `StockType` int(11) NOT NULL,
  `StockTime` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `CreateUser` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`StockID`) USING BTREE,
  INDEX `FK_O_P`(`ProductCode`) USING BTREE,
  CONSTRAINT `FK_O_P` FOREIGN KEY (`ProductCode`) REFERENCES `product` (`ProductCode`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stockrecord
-- ----------------------------
INSERT INTO `stockrecord` VALUES (5, '201902114', NULL, 20, 4, '2019-08-07 13:50:44', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (6, '201902114', NULL, 1, 4, '2019-08-07 14:03:06', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (7, '201802051', NULL, 5, 4, '2019-08-13 15:10:47', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (8, '201802051', NULL, 5, 4, '2019-08-13 23:33:13', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (9, '201802051', NULL, 5, 4, '2019-08-14 13:28:21', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (10, '201802051', NULL, 5, 3, '2019-08-14 13:28:28', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (11, '201802051', NULL, 5, 4, '2019-08-14 22:45:05', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (12, '201802051', NULL, 20, 4, '2019-08-16 13:50:18', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (13, '201802051', NULL, 50, 3, '2019-08-16 13:53:28', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (14, '201902083', NULL, 2, 4, '2019-08-16 15:20:18', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (15, '201802051', NULL, 4, 4, '2019-08-16 16:32:09', 'wangwenwen');
INSERT INTO `stockrecord` VALUES (16, '201802051', NULL, 5, 4, '2019-08-16 16:32:38', 'wangwenwen');

-- ----------------------------
-- Table structure for systemmodel
-- ----------------------------
DROP TABLE IF EXISTS `systemmodel`;
CREATE TABLE `systemmodel`  (
  `ModelCode` int(11) NOT NULL AUTO_INCREMENT,
  `ModelName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ModelUri` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`ModelCode`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1111111111 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of systemmodel
-- ----------------------------
INSERT INTO `systemmodel` VALUES (1, 'scmuser', '/scm/scmuser/*');
INSERT INTO `systemmodel` VALUES (2, 'purchase', '/scm/purchase*');
INSERT INTO `systemmodel` VALUES (3, 'stock', '/scm/stock/*');
INSERT INTO `systemmodel` VALUES (4, 'finance', '/scm/finance/*');
INSERT INTO `systemmodel` VALUES (5, 'sale', '/scm/sale/*');
INSERT INTO `systemmodel` VALUES (6, 'report', '/scm/report/*');

-- ----------------------------
-- Table structure for usermodel
-- ----------------------------
DROP TABLE IF EXISTS `usermodel`;
CREATE TABLE `usermodel`  (
  `Account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `ModelCode` int(11) NOT NULL,
  PRIMARY KEY (`Account`, `ModelCode`) USING BTREE,
  INDEX `FK_UM_SM`(`ModelCode`) USING BTREE,
  CONSTRAINT `FK_UM_SM` FOREIGN KEY (`ModelCode`) REFERENCES `systemmodel` (`ModelCode`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_UM_U` FOREIGN KEY (`Account`) REFERENCES `scmuser` (`Account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usermodel
-- ----------------------------
INSERT INTO `usermodel` VALUES ('ven', 1);
INSERT INTO `usermodel` VALUES ('xiaoyu', 1);
INSERT INTO `usermodel` VALUES ('zheng', 1);
INSERT INTO `usermodel` VALUES ('lisi', 2);
INSERT INTO `usermodel` VALUES ('ven123', 2);
INSERT INTO `usermodel` VALUES ('wangwu', 2);
INSERT INTO `usermodel` VALUES ('xiaoyu', 2);
INSERT INTO `usermodel` VALUES ('zheng', 2);
INSERT INTO `usermodel` VALUES ('lisi', 3);
INSERT INTO `usermodel` VALUES ('wangwu', 3);
INSERT INTO `usermodel` VALUES ('xiaoyu', 3);
INSERT INTO `usermodel` VALUES ('zhangsan', 3);
INSERT INTO `usermodel` VALUES ('zheng', 3);
INSERT INTO `usermodel` VALUES ('ven', 4);
INSERT INTO `usermodel` VALUES ('xiaoyu', 4);
INSERT INTO `usermodel` VALUES ('zheng', 4);
INSERT INTO `usermodel` VALUES ('lisi', 5);
INSERT INTO `usermodel` VALUES ('xiaoyu', 5);
INSERT INTO `usermodel` VALUES ('zheng', 5);
INSERT INTO `usermodel` VALUES ('lisi', 6);
INSERT INTO `usermodel` VALUES ('ven', 6);
INSERT INTO `usermodel` VALUES ('ven123', 6);
INSERT INTO `usermodel` VALUES ('wangwu', 6);
INSERT INTO `usermodel` VALUES ('xiaoyu', 6);
INSERT INTO `usermodel` VALUES ('zhangsan', 6);
INSERT INTO `usermodel` VALUES ('zheng', 6);

-- ----------------------------
-- Table structure for vender
-- ----------------------------
DROP TABLE IF EXISTS `vender`;
CREATE TABLE `vender`  (
  `VenderCode` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Contactor` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Postcode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `Tel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `Fax` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `CreateDate` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`VenderCode`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of vender
-- ----------------------------
INSERT INTO `vender` VALUES ('1', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `vender` VALUES ('201903', '华为', '123', '1', '1', '1', '1', '1', '1');

SET FOREIGN_KEY_CHECKS = 1;
