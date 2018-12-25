/*
Navicat MySQL Data Transfer

Target Server Type    : MYSQL
Target Server Version : 50172
File Encoding         : 65001

Date: 2018-12-25 15:57:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `lp_users`
-- ----------------------------
DROP TABLE IF EXISTS `lp_users`;
CREATE TABLE `lp_users` (
  `ID` varchar(64) NOT NULL COMMENT '唯一标识',
  `ACCOUNT` varchar(50) NOT NULL COMMENT '登录账号',
  `NAME` varchar(50) DEFAULT NULL COMMENT '用户名',
  `REMARKS` varchar(500) DEFAULT NULL COMMENT '说明',
  `PASSWORD` varchar(150) DEFAULT NULL COMMENT '密码',
  `IP` varchar(50) DEFAULT NULL COMMENT 'IP 地址 绑定IP时有效',
  `MAC` varchar(30) DEFAULT NULL COMMENT 'MAC地址 绑定mac时有效',
  `ISAUTOEXPIRE` int(11) DEFAULT '0' COMMENT '是否自动过期  1[自动到期]',
  `ISBINDIP` int(11) NOT NULL DEFAULT '0' COMMENT '是否绑定IP',
  `ISBINDMAC` int(11) NOT NULL DEFAULT '0' COMMENT '是否绑定MAC',
  `LASTTIME` date DEFAULT NULL COMMENT '上次登录时间',
  `LASTIP` varchar(50) DEFAULT NULL COMMENT '上次登录IP',
  `LASTMAC` varchar(30) DEFAULT NULL COMMENT '上次登录MAC',
  `LOGINCOUNT` int(11) DEFAULT '0' COMMENT '登陆次数',
  `CREATEDATE` timestamp NULL DEFAULT NULL COMMENT '用户创建日期',
  `UPDATETIME` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户更新时间',
  `STATE` int(11) DEFAULT NULL COMMENT '用户状态-1删除 0审核 1正常 2:冻结',
  `ICN` varchar(100) DEFAULT NULL COMMENT '身份证号',
  `UNITCODE` varchar(100) DEFAULT NULL COMMENT '单位编码【所属机构编码】',
  `PHONE` varchar(100) DEFAULT NULL COMMENT '用户手机（存放手机号码：占用）',
  `UTYPE` int(11) DEFAULT '1' COMMENT '账户类型 业务系统查询1即可【 0、系统内置用户 1、datacenter的用户 2、待定 3用户自定义】',
  `UVERSION` int(11) DEFAULT NULL COMMENT '用户当前版本号 3.01版本 存为301 3.00版本存为300 ',
  `KEYPATH` varchar(100) DEFAULT NULL COMMENT '秘钥路径',
  `EXPIREDATE` timestamp NULL DEFAULT NULL COMMENT '过期时间',
  `EMAIL` varchar(100) DEFAULT NULL COMMENT '邮箱地址',
  `CONACCOUNT` varchar(100) DEFAULT NULL COMMENT '关联账号信息（用于解决不同账号对应同一实体类人问题-概括将一人多账号问题）',
  `APPTYPE` varchar(100) DEFAULT NULL COMMENT '区分不同系统的用户',
  `RC1` varchar(64) DEFAULT NULL,
  `RC2` varchar(64) DEFAULT NULL COMMENT '单位名称（手动输入的名称）',
  `RC3` int(11) DEFAULT NULL COMMENT '访问接口是否加密1 加密  0 不加密',
  `OPTACCOUNT` varchar(64) DEFAULT '' COMMENT '操作账号',
  `RC4` varchar(64) DEFAULT NULL COMMENT 'apikey(默认为id+account后再用MD5加密生成)',
  `RC5` varchar(64) DEFAULT NULL,
  `RC6` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of lp_users
-- ----------------------------
INSERT INTO `lp_users` VALUES ('100', 'test', '测试用户', '对接cas接口数据', 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:53:29', '2018-11-13 16:54:15', '1', '', '1111', '21321321321', '1', null, '', '2018-03-12 00:00:00', '1121390867@qq.com', '', null, '001_01', '测试解放了', '0', null, null, null, null);
INSERT INTO `lp_users` VALUES ('200', 'meet', 'LPV2.0测试用户', '测试账号', 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:53:47', '2018-11-13 16:54:35', '1', '', '13123213', '18703212354', '0', null, '/root/user/test', '2018-07-01 00:00:00', '861634943@qq.com', 'test', null, null, null, '0', null, null, null, null);
INSERT INTO `lp_users` VALUES ('3b4dcde9d23f434f9f7cae4ec48228fa', 'datacenter', '数据交换平台', '', 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:57', '2018-11-13 16:53:44', '1', '', '44444', '18709898987', '0', null, '', '2018-09-19 00:00:00', 'tesst@163.com', '', null, null, null, '1', null, null, null, null);
INSERT INTO `lp_users` VALUES ('1c2beefbb9724453a02722c09aafc001', 'test_perm', '测试账号11111', '无', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '1', '0', '0', null, null, null, '0', '2018-11-16 13:37:24', '2018-11-16 13:37:24', '-1', '', '0', '17731767408', '1', null, '', '2018-10-24 00:00:00', 'datacenter@gla.net.cn', '', null, null, null, '1', '', null, null, null);
INSERT INTO `lp_users` VALUES ('4a45c9b1784c4699927bc8bd79f5a733', 'test1', 'test1', '', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-15 10:12:58', '2018-11-15 10:12:58', '1', '', '0', '', '1', null, '', null, 'test1@163.com', '', null, null, null, '0', '', null, null, null);
INSERT INTO `lp_users` VALUES ('ca3cfe3c95dc45d38cd023778ffe6361', 'test2', 'test2', '', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 10:47:07', '2018-11-19 10:47:07', '1', '', '0', '', '1', null, '', null, 'test2@163.com', '', null, null, null, '0', '', 'testkey', null, null);
INSERT INTO `lp_users` VALUES ('c4c0d8a99c29411e942e2e69bf9df794', 'test3', 'test3', '', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-15 10:13:10', '2018-11-15 10:13:10', '1', '', '0', '', '1', null, '', null, 'test3@163.com', '', null, null, null, '0', '', null, null, null);
INSERT INTO `lp_users` VALUES ('4a7223cfda39400385733cf3411320e3', 'test4', 'test4', '', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:53:11', '2018-11-13 16:53:58', '2', '', '0', '', '1', null, '', null, '', '', null, null, null, '0', '', null, null, null);
INSERT INTO `lp_users` VALUES ('aa1f9b2d2cf04e5eb997b9306eef0b04', 'test5', 'test5', '', 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:53:14', '2018-11-13 16:54:03', '1', '', '0', '', '1', null, '', null, '', '', null, null, null, '0', '', null, null, null);
INSERT INTO `lp_users` VALUES ('d9faa6186d4942deb959d94f0dddc536', 'A202', null, null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 09:17:07', '2018-11-19 09:17:59', '1', null, '1111', '', '1', null, null, null, '201676554@qq.com', null, null, null, '测试名称', null, '', '9393ba932dba6d936c2d08ba466d6993', null, null);
INSERT INTO `lp_users` VALUES ('4f096af242c441b386236f9b69c7e84c', 'M572', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:07', '2018-11-13 16:52:07', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('eb2b7711af774be58f9229efaabc4a14', 'U251', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:09', '2018-11-13 16:52:09', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('9d1dd68655e44901aaf4adbbc4ab6690', 'J977', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:12', '2018-11-13 16:52:12', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('c4bb9690284d495b8dac422e62b41229', 'B689', null, null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-16 13:33:48', '2018-11-16 13:34:37', '1', null, '1111', '1245784', '1', null, null, null, '@163.com', null, null, null, '第一家单位', null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('c52f98cba55141e2a0ea1c9048984108', 'V925', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:13', '2018-11-13 16:52:13', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('fa922a74fb0b4bf180f8e72d338ef50d', 'G520', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:17', '2018-11-13 16:52:17', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('2b5f182260dc40cbaa1fa442e1ea912d', 'K287', null, null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 11:26:43', '2018-11-19 11:26:43', '1', null, '0', '11', '1', null, null, null, '2016765854@qq.com', null, null, null, 'test', null, '', '7676a576aaa57b76c0aac1a5a77bf876', null, null);
INSERT INTO `lp_users` VALUES ('7a4b36f46917479ea5a099a340873de9', 'Q363', null, null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 13:55:58', '2018-11-19 13:56:50', '1', null, '1111', '15661567816', '1', null, null, null, '861634943@qq.com', null, null, null, '西藏国路安', null, '', 'b9b925b99d25b1b95d9d3625d7b151b9', null, null);
INSERT INTO `lp_users` VALUES ('c7c1758f14034af8a392e93db1e74131', 'M547', null, null, 'fcfc92fc7492b5fc7b74cf92b8b537fc', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 09:19:37', '2018-11-19 09:20:29', '1', null, '0', 'd', '1', null, null, null, '201676554', null, null, null, 'test', null, '', 'f3f39ff36e9ff3f3266ea99f0bf37bf3', null, null);
INSERT INTO `lp_users` VALUES ('21306716795c40aa8dd056e97552465a', 'H790', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:18', '2018-11-13 16:52:18', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('b0b67ed9cbc54ed5b90b5e11d693daa9', 'S350', 'wolf', null, 'e1e1dce149dc59e1be49e0dcf25988e1', null, null, '0', '0', '0', null, null, null, '0', '2018-11-13 16:52:22', '2018-11-13 16:52:22', '1', null, '7777', null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('7606bcb6fd104a62873cf3144d278a8b', 'Q273', null, null, 'd5d5d1d547d126d51447cbd1b2268ed5', null, null, '0', '0', '0', null, null, null, '0', '2018-11-19 11:29:44', '2018-11-19 11:29:44', '1', null, '1111', '18888886666', '1', null, null, null, '122131263@qq.com', null, null, null, '测试用户新增3', null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('201', '201', null, null, null, null, null, null, '1', '1', null, null, null, '0', null, '2018-12-25 12:05:45', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('202', '202', null, null, '123456', null, null, null, '1', '1', null, null, null, '0', null, '2018-12-25 10:25:51', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('203', '201', null, null, null, null, null, null, '1', '1', null, null, null, '0', null, '2018-12-25 12:06:30', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
INSERT INTO `lp_users` VALUES ('205', '201', null, null, null, null, null, null, '1', '1', null, null, null, '0', null, '2018-12-25 12:13:09', null, null, null, null, '1', null, null, null, null, null, null, null, null, null, '', null, null, null);
