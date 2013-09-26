# phpMyAdmin SQL Dump
# version 2.5.6
# http://www.phpmyadmin.net
#
# Host: mysql.stud.ntnu.no
# Generation Time: Sep 22, 2012 at 02:45 PM
# Server version: 5.0.96
# PHP Version: 5.2.4-2ubuntu5.25
# 
# Database : `yngvesva_blopp`
# 

# --------------------------------------------------------

#
# Table structure for table `AVATARS`
#

CREATE TABLE `AVATARS` (
  `id` int(11) NOT NULL auto_increment,
  `credits` int(11) default '0',
  `inventory_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `inventory_id` (`inventory_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `AVATARS`
#


# --------------------------------------------------------

#
# Table structure for table `AVATAR_INVENTORIES`
#

CREATE TABLE `AVATAR_INVENTORIES` (
  `id` int(11) NOT NULL default '0',
  `shop_item_id` int(11) default NULL,
  `equipped` int(1) NOT NULL default '0',
  `avatar_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `shop_item_id` (`shop_item_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Dumping data for table `AVATAR_INVENTORIES`
#


# --------------------------------------------------------

#
# Table structure for table `AVATAR_ITEM_SLOTS`
#

CREATE TABLE `AVATAR_ITEM_SLOTS` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(128) NOT NULL,
  `equip_limit` int(4) default NULL,
  `x` double NOT NULL,
  `y` double NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `AVATAR_ITEM_SLOTS`
#


# --------------------------------------------------------

#
# Table structure for table `CHILDREN`
#

CREATE TABLE `CHILDREN` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(100) character set utf8 NOT NULL,
  `pers_num` int(11) NOT NULL,
  `medical_plan_id` int(11) default NULL,
  `avatar_id` int(11) default NULL,
  `child_health_state_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `pers_num` (`pers_num`,`medical_plan_id`,`avatar_id`,`child_health_state_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `CHILDREN`
#


# --------------------------------------------------------

#
# Table structure for table `CHILDREN_LOG_DAYS`
#

CREATE TABLE `CHILDREN_LOG_DAYS` (
  `date` date NOT NULL,
  `child_id` int(11) NOT NULL default '0',
  `pollen_state_id` int(11) default NULL,
  PRIMARY KEY  (`date`,`child_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

#
# Dumping data for table `CHILDREN_LOG_DAYS`
#


# --------------------------------------------------------

#
# Table structure for table `CHILD_HEALTH_STATES`
#

CREATE TABLE `CHILD_HEALTH_STATES` (
  `id` int(11) NOT NULL auto_increment,
  `health_state_id` int(11) default NULL,
  `applies_now` int(1) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `CHILD_HEALTH_STATES`
#


# --------------------------------------------------------

#
# Table structure for table `DAY_MEDICINE_DOSES`
#

CREATE TABLE `DAY_MEDICINE_DOSES` (
  `id` int(11) NOT NULL auto_increment,
  `prize` int(8) NOT NULL,
  `planned` int(1) NOT NULL,
  `taken` int(1) NOT NULL,
  `time` datetime NOT NULL,
  `day_date` date NOT NULL,
  `child_id` int(11) default NULL,
  `medicine_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `DAY_MEDICINE_DOSES`
#


# --------------------------------------------------------

#
# Table structure for table `HEALTH_STATES`
#

CREATE TABLE `HEALTH_STATES` (
  `id` int(11) NOT NULL auto_increment,
  `label` varchar(256) character set utf8 NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `HEALTH_STATES`
#


# --------------------------------------------------------

#
# Table structure for table `MEDICAL_PLANS`
#

CREATE TABLE `MEDICAL_PLANS` (
  `id` int(11) NOT NULL auto_increment,
  `label` varchar(256) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `MEDICAL_PLANS`
#


# --------------------------------------------------------

#
# Table structure for table `MEDICAL_PLAN_DOSES`
#

CREATE TABLE `MEDICAL_PLAN_DOSES` (
  `id` int(11) NOT NULL auto_increment,
  `medical_plan_id` int(11) NOT NULL,
  `health_state_id` int(11) NOT NULL,
  `time` time NOT NULL,
  `medicine_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `MEDICAL_PLAN_DOSES`
#


# --------------------------------------------------------

#
# Table structure for table `MEDICINES`
#

CREATE TABLE `MEDICINES` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(256) character set utf8 NOT NULL,
  `instructions_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `MEDICINES`
#


# --------------------------------------------------------

#
# Table structure for table `MEDICINE_INSTRUCTIONS`
#

CREATE TABLE `MEDICINE_INSTRUCTIONS` (
  `id` int(11) NOT NULL auto_increment,
  `url` varchar(256) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `MEDICINE_INSTRUCTIONS`
#


# --------------------------------------------------------

#
# Table structure for table `POLLEN_STATES`
#

CREATE TABLE `POLLEN_STATES` (
  `id` int(11) NOT NULL auto_increment,
  `label` varchar(256) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `POLLEN_STATES`
#


# --------------------------------------------------------

#
# Table structure for table `SHOP_ITEMS`
#

CREATE TABLE `SHOP_ITEMS` (
  `id` int(11) NOT NULL auto_increment,
  `price` int(11) default '0',
  `slot_id` int(11) default NULL,
  `graphic` mediumblob NOT NULL,
  `name` varchar(64) character set utf8 NOT NULL,
  `description` varchar(256) character set utf8 NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

#
# Dumping data for table `SHOP_ITEMS`
#