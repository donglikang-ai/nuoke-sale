/*
 Navicat Premium Data Transfer

 Source Server         : local-5.7
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3307
 Source Schema         : nuoke_

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 07/11/2019 18:46:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_fault
-- ----------------------------
DROP TABLE IF EXISTS `sys_fault`;
CREATE TABLE `sys_fault`  (
                            `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                            `fault_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
                             `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                             `notice` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_order
-- ----------------------------
DROP TABLE IF EXISTS `sys_order`;
CREATE TABLE `sys_order`  (
                            `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                            `openid` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                            `terminal_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `fault_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `mobile` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `address` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `terminal_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `fault_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `fault_info` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `status` tinyint(2) NULL DEFAULT NULL,
                            `repairman_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `repairman_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `repairman_tel` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `create_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `do_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            `close_date` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_repairman
-- ----------------------------
DROP TABLE IF EXISTS `sys_repairman`;
CREATE TABLE `sys_repairman`  (
                                `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                                `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `age` int(2) NULL DEFAULT NULL,
                                `sex` varchar(16) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `id_card` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_terminal
-- ----------------------------
DROP TABLE IF EXISTS `sys_terminal`;
CREATE TABLE `sys_terminal`  (
                               `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
                               `terminal_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
