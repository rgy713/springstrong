/*
Navicat MySQL Data Transfer

Source Server         : rgc
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : qiangrun

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2017-05-18 10:17:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tbl_banner
-- ----------------------------
DROP TABLE IF EXISTS `tbl_banner`;
CREATE TABLE `tbl_banner` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '유일식별키',
  `banner_img` varchar(256) COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '배너이미지',
  `banner_title_en_US` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_title_ko_KR` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_title_zh_CN` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_title_ja_JP` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_content_en_US` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_content_ko_KR` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_content_zh_CN` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `banner_content_ja_JP` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='배너정보를 보관하는 표';

-- ----------------------------
-- Records of tbl_banner
-- ----------------------------
INSERT INTO `tbl_banner` VALUES ('1', 'banner_1494816124000.jpg', 'Video prossecing technology', '동영상가공기술', '视频处理技术', '動画加工技術', '2D and 3D video editing, chroma marking, streaming, etc.', '2차원,3차원 동영상편집, 크로마킹,스트리밍 등등', '2D和3D视频编辑，色度标记，流媒体等', '2次元、3次元動画の編集、クロマーキング、ストリーミング等');
INSERT INTO `tbl_banner` VALUES ('2', '02-boxed.jpg', 'Media production technology', '미디어제작기술', '媒体制作技术', 'メディア制作技術', 'Jogger project, ufer app, casting, html5 making technology', '죠거프로젝트, 우퍼,캐스팅,Html5제작기술', '九格项目，Ufer应用，Casting，HTML5的生产技术', 'ジョゴプロジェクトは、ウーファーアプリ、キャスティング、Html5製作技術');

-- ----------------------------
-- Table structure for tbl_home_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_home_product`;
CREATE TABLE `tbl_home_product` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '유일식별키',
  `product_img` varchar(256) COLLATE utf8mb4_unicode_520_ci NOT NULL COMMENT '상품이미지',
  `product_name_ko_KR` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_name_en_US` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_name_zh_CN` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_name_ja_JP` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_content_ko_KR` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_content_en_US` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_content_zh_CN` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  `product_content_ja_JP` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci;

-- ----------------------------
-- Records of tbl_home_product
-- ----------------------------
INSERT INTO `tbl_home_product` VALUES ('1', 'img-01.jpg', '3D 동영상제작프로그람', '3D movie making program', '3D影片制作程序', '3Dビデオ制作プログラム', '3D 동영상제작프로그람', '3D movie making program', '3D影片制作程序', '3Dビデオ制作プログラム');
INSERT INTO `tbl_home_product` VALUES ('2', 'img-02.jpg', '2D 동영상제작프로그람', '2D movie making program', '2D影片制作程序', '2Dビデオ制作プログラム', '2D 동영상제작프로그람', '2D movie making program', '2D影片制作程序', '2Dビデオ制作プログラム');
INSERT INTO `tbl_home_product` VALUES ('3', 'img-03.jpg', '죠거미디어제작프로그람', 'Jogger media making program', '九格制作程序', 'ジョガービデオ制作プログラム', '죠거미디어제작프로그람', 'Jogger media making program', '九格制作程序', 'ジョガービデオ制作プログラム');

-- ----------------------------
-- Table structure for tbl_product_info
-- ----------------------------
DROP TABLE IF EXISTS `tbl_product_info`;
CREATE TABLE `tbl_product_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '유일식별키',
  `product_id` int(11) unsigned NOT NULL COMMENT '제품아이디',
  `image` varchar(256) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '화상파일이름을 보관하는 마당',
  `content_ko_KR` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '설명문 조선어',
  `content_en_US` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '설명문 영어',
  `content_zh_CN` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '설명문 중어',
  `content_ja_JP` varchar(1024) COLLATE utf8mb4_unicode_520_ci DEFAULT NULL COMMENT '설명문 일어',
  PRIMARY KEY (`id`),
  KEY `tbl_product_info_ibfk_1` (`product_id`),
  CONSTRAINT `tbl_product_info_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `tbl_home_product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_520_ci COMMENT='제품의 상세정보를 보관하는 표';

-- ----------------------------
-- Records of tbl_product_info
-- ----------------------------
INSERT INTO `tbl_product_info` VALUES ('1', '1', 'img-01.jpg', '3D동영상제작프로그람', '3D movie making program', '3D影片制作程序', '3Dビデオ制作プログラム');
INSERT INTO `tbl_product_info` VALUES ('2', '1', 'img-02.jpg', '죠거미디어제작프로그람', 'Jogger media making program', '九格制作程序', 'ジョガービデオ制作プログラム');
INSERT INTO `tbl_product_info` VALUES ('3', '2', 'img-01.jpg', '3D동영상제작프로그람', '3D movie making program', '3D影片制作程序', '3Dビデオ制作プログラム');
