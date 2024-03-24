/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80036
 Source Host           : localhost:3306
 Source Schema         : supermarket_db

 Target Server Type    : MySQL
 Target Server Version : 80036
 File Encoding         : 65001

 Date: 24/03/2024 16:10:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity_rule
-- ----------------------------
DROP TABLE IF EXISTS `activity_rule`;
CREATE TABLE `activity_rule`  (
  `goods_id` int NOT NULL COMMENT '参加活动的商品编号',
  `base_money` double(10, 1) NULL DEFAULT NULL COMMENT '达到多少金额能够享受减免',
  `reduce_money` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '减免的金额',
  PRIMARY KEY (`goods_id`) USING BTREE,
  CONSTRAINT `activity_rule_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '减免表（活动规则列表）' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of activity_rule
-- ----------------------------
INSERT INTO `activity_rule` VALUES (1, 1.0, '3');
INSERT INTO `activity_rule` VALUES (2, 2.0, '2');
INSERT INTO `activity_rule` VALUES (6, 10.0, '3');
INSERT INTO `activity_rule` VALUES (7, 1.0, '1');
INSERT INTO `activity_rule` VALUES (11, 5.0, '1.5');
INSERT INTO `activity_rule` VALUES (13, 3.0, '1');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `goods_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `price` double(10, 1) NOT NULL COMMENT '单价',
  `publish_time` datetime NOT NULL COMMENT '录入时间',
  `type_id` int NOT NULL COMMENT '所属类型编号',
  `goods_desc` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品描述',
  `goods_stock` int NULL DEFAULT NULL COMMENT '库存量',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type_id`(`type_id` ASC) USING BTREE,
  INDEX `goods_name`(`goods_name` ASC) USING BTREE,
  INDEX `price`(`price` ASC) USING BTREE,
  CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `goods_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '红富士苹果', 12.5, '2023-11-18 13:48:28', 1, '非常甜的苹果', 199);
INSERT INTO `goods` VALUES (2, '西红柿', 6.0, '2023-11-07 10:24:13', 2, '营养美味的番茄', 195);
INSERT INTO `goods` VALUES (3, '西瓜', 10.0, '2023-11-23 23:52:40', 1, '美味可口的西瓜', 197);
INSERT INTO `goods` VALUES (4, '脐橙', 8.0, '2023-11-24 10:34:03', 1, '橙红鲜艳，光洁美观', 144);
INSERT INTO `goods` VALUES (5, '西葫芦', 3.0, '2023-11-24 10:35:04', 2, '含有较多营养物质', 92);
INSERT INTO `goods` VALUES (6, '硒砂瓜', 2.0, '2023-11-24 10:35:55', 1, '果肉鲜嫩、甘甜如蜜', 189);
INSERT INTO `goods` VALUES (7, '赣南脐橙', 8.0, '2023-11-30 14:46:14', 1, '汁多味美', 168);
INSERT INTO `goods` VALUES (8, '上海青', 3.5, '2023-12-05 00:36:49', 2, '又名瓢儿白，可以保持血管弹性，提供人体所需矿物质、维生素。', 74);
INSERT INTO `goods` VALUES (9, '油麦菜', 1.4, '2023-12-05 00:38:59', 2, '叶用莴苣，具有极高的营养价值', 45);
INSERT INTO `goods` VALUES (11, '沙地红薯', 0.9, '2023-12-05 00:43:20', 2, '含有丰富的烟胺酸和纤维物质，并有大量的糖分', 109);
INSERT INTO `goods` VALUES (12, '沙田柚', 5.0, '2023-12-09 14:24:39', 1, '有丰富的维生素', 100);
INSERT INTO `goods` VALUES (13, '老南瓜', 2.0, '2023-12-11 14:10:59', 2, '蔬菜越嫩越好吃，南瓜越老越美味', 49);

-- ----------------------------
-- Table structure for goods_code
-- ----------------------------
DROP TABLE IF EXISTS `goods_code`;
CREATE TABLE `goods_code`  (
  `gid` int NOT NULL COMMENT '商品id',
  `gcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品条形码码文',
  PRIMARY KEY (`gid`) USING BTREE,
  CONSTRAINT `goods_code_ibfk_1` FOREIGN KEY (`gid`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_code
-- ----------------------------
INSERT INTO `goods_code` VALUES (1, '6f0ac6366c30462ab4f607703beb083f');
INSERT INTO `goods_code` VALUES (2, '4dfb7c91f1404662b3e808ea6b10e5e6');
INSERT INTO `goods_code` VALUES (3, '13f6cba7793f453da4581df3662a26e3');
INSERT INTO `goods_code` VALUES (4, '51f8b598feb14a319ac6dfa0dfdaf95d');
INSERT INTO `goods_code` VALUES (5, 'a2996ad30abf42a3a537ead6457ed3b1');
INSERT INTO `goods_code` VALUES (6, 'a952707252dc4ca8b6acc695128e7114');
INSERT INTO `goods_code` VALUES (7, '613aac27c60b4c089162a62dfed10ac5');
INSERT INTO `goods_code` VALUES (8, 'f2c8c144543041b2a9a0fb3f81ab2166');
INSERT INTO `goods_code` VALUES (9, 'b818559a3594441aa44f6cb24698e5fd');
INSERT INTO `goods_code` VALUES (11, 'abab83d2b10e4367aa9fc9e1ec847778');
INSERT INTO `goods_code` VALUES (12, '7121e80b8bc74a14a1aa801e66037ed9');
INSERT INTO `goods_code` VALUES (13, 'de96139085fd4fb4856cba02ce381a58');

-- ----------------------------
-- Table structure for goods_type
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `type_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '商品类型表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of goods_type
-- ----------------------------
INSERT INTO `goods_type` VALUES (1, '水果');
INSERT INTO `goods_type` VALUES (2, '蔬菜');
INSERT INTO `goods_type` VALUES (3, '玩具');

-- ----------------------------
-- Table structure for market_user
-- ----------------------------
DROP TABLE IF EXISTS `market_user`;
CREATE TABLE `market_user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码（加密密文）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of market_user
-- ----------------------------
INSERT INTO `market_user` VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'https://c-ssl.duitang.com/uploads/blog/202206/12/20220612164733_72d8b.jpg');
INSERT INTO `market_user` VALUES (2, 'tom', 'e10adc3949ba59abbe56e057f20f883e', 'https://c-ssl.dtstatic.com/uploads/blog/202207/09/20220709150824_97667.thumb.1000_0.jpg');
INSERT INTO `market_user` VALUES (3, '邱浩洋', 'e10adc3949ba59abbe56e057f20f883e', 'https://img.xiaohongshu.com/avatar/5fdf289c8d6612e8be117f4b.jpg@80w_80h_90q_1e_1c_1x.jpg');

-- ----------------------------
-- Table structure for sell_record
-- ----------------------------
DROP TABLE IF EXISTS `sell_record`;
CREATE TABLE `sell_record`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `sell_time` datetime NULL DEFAULT NULL COMMENT '售出时间',
  `goods_id` int NULL DEFAULT NULL COMMENT '商品编号',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `num` int NULL DEFAULT NULL COMMENT '购买数量',
  `price` double(10, 1) NULL DEFAULT NULL COMMENT '单价',
  `receivable` double(10, 1) NULL DEFAULT NULL COMMENT '应收金额',
  `receipts` double(10, 1) NULL DEFAULT NULL COMMENT '实收金额',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `goods_id`(`goods_id` ASC) USING BTREE,
  CONSTRAINT `sell_record_ibfk_1` FOREIGN KEY (`goods_id`) REFERENCES `goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '售出记录表(销售记录)' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sell_record
-- ----------------------------
INSERT INTO `sell_record` VALUES (1, '2023-12-03 05:40:12', 3, '西瓜', 3, 10.0, 30.0, 25.0);
INSERT INTO `sell_record` VALUES (2, '2023-12-04 18:06:59', 6, '硒砂瓜', 1, 2.0, 2.0, 2.0);
INSERT INTO `sell_record` VALUES (3, '2023-12-04 18:07:12', 2, '西红柿', 3, 6.0, 18.0, 18.0);
INSERT INTO `sell_record` VALUES (4, '2023-12-05 00:58:30', 6, '硒砂瓜', 10, 2.0, 20.0, 17.0);
INSERT INTO `sell_record` VALUES (5, '2023-12-05 18:15:45', 8, '上海青', 3, 3.5, 10.5, 10.5);
INSERT INTO `sell_record` VALUES (6, '2023-12-06 00:57:07', 11, '沙地红薯', 6, 0.9, 5.4, 5.4);
INSERT INTO `sell_record` VALUES (7, '2023-12-07 19:15:45', 1, '红富士苹果', 1, 12.5, 12.5, 9.5);
INSERT INTO `sell_record` VALUES (8, '2023-12-07 19:16:29', 4, '脐橙', 6, 8.0, 48.0, 48.0);
INSERT INTO `sell_record` VALUES (9, '2023-12-07 19:16:38', 5, '西葫芦', 2, 3.0, 6.0, 6.0);
INSERT INTO `sell_record` VALUES (10, '2023-12-07 19:17:04', 7, '赣南脐橙', 6, 8.0, 48.0, 48.0);
INSERT INTO `sell_record` VALUES (11, '2023-12-07 19:17:57', 11, '沙地红薯', 5, 0.9, 4.5, 4.5);
INSERT INTO `sell_record` VALUES (12, '2023-12-07 19:18:02', 9, '油麦菜', 5, 1.4, 7.0, 7.0);
INSERT INTO `sell_record` VALUES (13, '2023-12-07 19:18:08', 8, '上海青', 3, 3.5, 10.5, 10.5);
INSERT INTO `sell_record` VALUES (14, '2023-12-08 23:10:29', 5, '西葫芦', 6, 3.0, 18.0, 18.0);
INSERT INTO `sell_record` VALUES (15, '2023-12-09 15:06:03', 7, '赣南脐橙', 6, 8.0, 48.0, 47.0);
INSERT INTO `sell_record` VALUES (16, '2023-12-09 15:06:15', 2, '西红柿', 2, 6.0, 12.0, 10.0);
INSERT INTO `sell_record` VALUES (17, '2023-12-11 18:09:55', 13, '老南瓜', 1, 2.0, 2.0, 2.0);

SET FOREIGN_KEY_CHECKS = 1;
