/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50621
Source Host           : 127.0.0.1:3306
Source Database       : bbs

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2017-03-01 14:35:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `adminName` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`adminName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('admin', 'e807f1fcf82d132f9bb018ca6738a19f');

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `announceId` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `content` text,
  `announceTime` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`announceId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', 'Fate/Grand Order讨论区版规2017年2月18日修订版', '本版遵循[NGA通用版规2016年1月修订版]进行管理，一切总版规不允许的行为在本版同样不允许。2', '1488268561');

-- ----------------------------
-- Table structure for categories
-- ----------------------------
DROP TABLE IF EXISTS `categories`;
CREATE TABLE `categories` (
  `categoryId` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`categoryId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of categories
-- ----------------------------
INSERT INTO `categories` VALUES ('1', '艺术审美E-', '对于游戏内的现存状况的一些看法与推测');
INSERT INTO `categories` VALUES ('2', '理性蒸发A+', '对于游戏里发生的一些事的吐槽');
INSERT INTO `categories` VALUES ('3', '国服活动', '对于国服活动所写的攻略,一些有用的建议或者疑问');
INSERT INTO `categories` VALUES ('4', '日服活动', '对于国服活动所写的攻略,一些有用的建议或者疑问');

-- ----------------------------
-- Table structure for inform
-- ----------------------------
DROP TABLE IF EXISTS `inform`;
CREATE TABLE `inform` (
  `informId` int(11) NOT NULL AUTO_INCREMENT,
  `informUserId` varchar(14) DEFAULT NULL,
  `content` text,
  `replyId` int(11) DEFAULT NULL,
  `informDate` varchar(10) DEFAULT NULL,
  `hasRead` int(1) DEFAULT NULL,
  PRIMARY KEY (`informId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of inform
-- ----------------------------
INSERT INTO `inform` VALUES ('2', '116', '迷之女主角X 回复了你的回复 为啥这次星战好多dalao都开始删好友了', '2', '1487836076', '0');
INSERT INTO `inform` VALUES ('6', '116', '迷之女主角X 回复了你的回复 为啥这次星战好多dalao都开始删好友了', '7', '1487837640', '0');
INSERT INTO `inform` VALUES ('8', '116', '迷之女主角Y 回复了你的回复 为啥这次星战好多dalao都开始删好友了', '9', '1487840374', '0');
INSERT INTO `inform` VALUES ('10', '115', '迷之女主角X 回复了你的帖子 FGO情人节-巧克力~女士的大惊小怪 -活动副本/敌方配置/兑换奖励', '26', '1488337174', '0');
INSERT INTO `inform` VALUES ('11', '115', '迷之女主角Z 回复了你的帖子 FGO情人节-巧克力~女士的大惊小怪 -活动副本/敌方配置/兑换奖励', '27', '1488337195', '0');

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `replyId` int(11) NOT NULL AUTO_INCREMENT,
  `replymanId` varchar(14) DEFAULT NULL,
  `topicId` int(11) DEFAULT NULL,
  `content` text,
  `replyDate` varchar(45) DEFAULT NULL,
  `quoteReplyId` int(11) DEFAULT NULL,
  `quoteUserId` varchar(14) DEFAULT NULL,
  PRIMARY KEY (`replyId`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reply
-- ----------------------------
INSERT INTO `reply` VALUES ('1', '116', '2', '反正nga大佬这么多，被删了在好友帖里面再找几个新大腿呗', '1487835966', '0', null);
INSERT INTO `reply` VALUES ('2', '114', '2', '唉，好吧......求个交友贴地址，顺便大佬是ios还是安卓，求py', '1487836076', '1', '116');
INSERT INTO `reply` VALUES ('3', '114', '2', '求各位大佬的py码，新任很勤快，可以贡献大量友情点', '1487837073', '0', null);
INSERT INTO `reply` VALUES ('4', '115', '2', '100,100,528,007,你要的py码', '1487837168', '0', null);
INSERT INTO `reply` VALUES ('5', '116', '2', '加好友还是在汇总的好友帖里面去吧，单开贴会被删', '1487837250', '4', '115');
INSERT INTO `reply` VALUES ('6', '114', '2', '谢谢大佬！', '1487837284', '4', '115');
INSERT INTO `reply` VALUES ('7', '114', '2', '求个好友不至于吧。。。还要删帖', '1487837640', '5', '116');
INSERT INTO `reply` VALUES ('8', '116', '2', '有专门的好友集中贴，你去那求呗，这贴也活不了多久了', '1487837870', '7', '114');
INSERT INTO `reply` VALUES ('9', '115', '2', '好的，谢谢提醒', '1487840374', '5', '116');
INSERT INTO `reply` VALUES ('10', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927201', '0', null);
INSERT INTO `reply` VALUES ('11', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927226', '0', null);
INSERT INTO `reply` VALUES ('12', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927227', '0', null);
INSERT INTO `reply` VALUES ('13', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927228', '0', null);
INSERT INTO `reply` VALUES ('14', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927229', '0', null);
INSERT INTO `reply` VALUES ('15', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927231', '0', null);
INSERT INTO `reply` VALUES ('16', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927231', '0', null);
INSERT INTO `reply` VALUES ('17', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927233', '0', null);
INSERT INTO `reply` VALUES ('18', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927233', '0', null);
INSERT INTO `reply` VALUES ('19', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927234', '0', null);
INSERT INTO `reply` VALUES ('20', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927235', '0', null);
INSERT INTO `reply` VALUES ('21', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927238', '0', null);
INSERT INTO `reply` VALUES ('22', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927240', '0', null);
INSERT INTO `reply` VALUES ('23', '114', '2', '各位大佬求带下新人啊，谢谢了', '1487927242', '0', null);
INSERT INTO `reply` VALUES ('24', '115', '2', '因本帖楼主持续顶贴，造成混乱，该贴即将被删除', '1487927295', '0', null);
INSERT INTO `reply` VALUES ('25', '115', '4', '先占一楼为后面补充内容用', '1488337127', '0', null);
INSERT INTO `reply` VALUES ('26', '114', '4', '前排支持大佬！', '1488337174', '0', null);
INSERT INTO `reply` VALUES ('27', '116', '4', '太感谢大佬的辛勤攻略啦', '1488337195', '0', null);

-- ----------------------------
-- Table structure for sticky
-- ----------------------------
DROP TABLE IF EXISTS `sticky`;
CREATE TABLE `sticky` (
  `topicId` int(11) DEFAULT NULL,
  `stickTime` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sticky
-- ----------------------------
INSERT INTO `sticky` VALUES ('4', '1488251792');
INSERT INTO `sticky` VALUES ('5', '1488337633');
INSERT INTO `sticky` VALUES ('6', '1488337636');

-- ----------------------------
-- Table structure for topic
-- ----------------------------
DROP TABLE IF EXISTS `topic`;
CREATE TABLE `topic` (
  `topicId` int(11) NOT NULL AUTO_INCREMENT,
  `postmanId` varchar(14) DEFAULT NULL,
  `postDate` varchar(10) DEFAULT NULL,
  `categoryId` int(11) DEFAULT NULL,
  `title` varchar(60) DEFAULT NULL,
  `content` text,
  `lastReplyTime` varchar(10) DEFAULT NULL,
  `lastReplyId` varchar(14) DEFAULT NULL,
  `comments` int(11) DEFAULT NULL,
  `browseCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`topicId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of topic
-- ----------------------------
INSERT INTO `topic` VALUES ('2', '114', '1487820015', '2', '为啥这次星战好多dalao都开始删好友了', '这次星战我被X毛大佬，船长大佬，金时大佬还有好几个不知名的dalao删好友了。我也很努力啊0.0，礼装全是满破的，支援要么是师匠，要么是B提拉，或者就是拉拉队lily', '1487927295', '115', '24', '18');
INSERT INTO `topic` VALUES ('4', '115', '1488250951', '3', 'FGO情人节-巧克力~女士的大惊小怪 -活动副本/敌方配置/兑换奖励', '第一次写，排版是照抄论坛里面之前的活动，不抄白不抄...因为我没打过日服，如果有错误的话麻烦dalao们指出，我是看着资料站写的，难保没错误情人节是个小活动，基本没啥好顾虑的，反正就是最简单的那种刷刷刷活动活动期间还有个花嫁尼禄的体验关，送一个呼符，懒的写！', '1488337195', '116', '3', '1');
INSERT INTO `topic` VALUES ('5', '115', '1488337447', '4', 'Fate Grand Order 1.5 亚种特异点 恶性隔绝魔境 新宿 新宿幻灵事件 开幕！ 番组生放送梶田再临', '在修复扭曲的历史时，发生了一些重大的纰漏，这是由被排斥的狂乱打造的脚本，是被忘却的世纪末的神话，前所未有的大规模杀人事件，最终以亚种特异点的的形式完成，完全犯罪计划，启动——幻灵啊，在背德之城疯狂起舞吧', '', '', '0', '1');
INSERT INTO `topic` VALUES ('6', '115', '1488337580', '4', '第一次高难度副本复刻 监狱塔中复仇鬼在恸哭 关卡配置攻略(新增隐藏关更新完毕)', '由于奥妮酱在做空境，所以日服部分的攻略依然是我来代打，那目前监狱塔复刻已知的会额外增加一关，目前未知，旧关卡部分如下。', '', '', '0', '0');
INSERT INTO `topic` VALUES ('7', '116', '1488337757', '2', '我的小莫宝具本，不见了……来自(疑似)星际选手的求助', '小莫的宝具本应该是第四章通关时开放……我在这里查到的……但是并没有看到有开放提示，然后我看了下小莫宝具本的地点……！！！我的地图上竟然没有海德公园！！请问各位大佬，这……这该怎么办，是有特殊的触发条件吗？', '', '', '0', '0');
INSERT INTO `topic` VALUES ('8', '116', '1488337895', '3', '低价代充现在早就不是汇率了， 貌似很多人还是不知道', '先直接贴个链接。。。[http://tech.qq.com/a/20170216/005071.htm]大体上就是利用低价格档充值的时候苹果不会验证你卡里是不是有那么多钱，会直接充值成功，但你卡里钱不够是不会扣的。这样子运营商收不到钱的，跟汇率那种并不是一个概念，查出来是一定会被封的。而且比黑卡还容易查，只要搜一下一段时间内谁大量充了6块档位就好。奉劝大家还是谨慎选择代充吧，太低的价格一定有问题，但是半高不高的价格很有迷惑性，你也不知道他是咋冲的，被封了你也没处说理去。。。', '', '', '0', '0');
INSERT INTO `topic` VALUES ('9', '116', '1488337959', '3', '就算不能特攻 我师匠开绿魔放时候也会开神杀', '技能一个亮着多难受 ', '', '', '0', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(14) NOT NULL,
  `nickName` varchar(10) DEFAULT NULL,
  `iconUrl` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('114', '迷之女主角X', '/file/picture/20170224151153_998.jpg');
INSERT INTO `user` VALUES ('115', '迷之女主角Y', '/file/picture/20170224153415_658.png');
INSERT INTO `user` VALUES ('116', '迷之女主角Z', '/file/picture/20170224153335_185.jpg');
