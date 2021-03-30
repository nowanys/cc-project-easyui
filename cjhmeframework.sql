/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50171
Source Host           : localhost:3306
Source Database       : cjhmeframework

Target Server Type    : MYSQL
Target Server Version : 50171
File Encoding         : 65001

Date: 2016-12-22 19:30:14
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `c3p0testtable`
-- ----------------------------
DROP TABLE IF EXISTS `c3p0testtable`;
CREATE TABLE `c3p0testtable` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c3p0testtable
-- ----------------------------

-- ----------------------------
-- Table structure for `t_access_url`
-- ----------------------------
DROP TABLE IF EXISTS `t_access_url`;
CREATE TABLE `t_access_url` (
  `accessUrlId` int(20) NOT NULL AUTO_INCREMENT COMMENT '访问地址id',
  `accessUrl` varchar(300) DEFAULT NULL COMMENT '访问地址',
  `accessUrlName` varchar(100) DEFAULT NULL COMMENT '访问地址名称',
  `menuId` int(20) DEFAULT NULL COMMENT '菜单id',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`accessUrlId`)
) ENGINE=InnoDB AUTO_INCREMENT=120 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_access_url
-- ----------------------------
INSERT INTO `t_access_url` VALUES ('1', 'system/preCurrentUserPwdEdit.jhtml', '跳转当前用户密码编辑页面', null, '1', '1', '小咖', '1', '小咖', '2015-08-29 16:16:53', '2015-08-30 18:52:35');
INSERT INTO `t_access_url` VALUES ('2', 'system/preCurrentUserInfoEdit.jhtml', '跳转当前用户资料编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:52:55', null);
INSERT INTO `t_access_url` VALUES ('3', 'system/changeCurrentUserDept.jhtml', '切换当前用户部门', null, '1', '1', '小咖', null, null, '2015-08-30 18:53:13', null);
INSERT INTO `t_access_url` VALUES ('4', 'system/changeCurrentUserRole.jhtml', '切换当前用户角色', null, '1', '1', '小咖', null, null, '2015-08-30 18:53:30', null);
INSERT INTO `t_access_url` VALUES ('5', 'system/preIndividuation.jhtml', '跳转个性化页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:53:51', null);
INSERT INTO `t_access_url` VALUES ('6', 'accessUrl/preAccessUrlManage.jhtml', '跳转访问地址管理页面', null, '1', '1', '小咖', '1', '卡卡', '2015-08-30 18:54:24', '2016-04-02 14:32:39');
INSERT INTO `t_access_url` VALUES ('7', 'accessUrl/queryAccessUrlByConditionPaging.jhtml', '根据条件分页查询访问地址', null, '1', '1', '小咖', null, null, '2015-08-30 18:54:44', null);
INSERT INTO `t_access_url` VALUES ('8', 'accessUrl/preAccessUrlAdd.jhtml', '跳转访问地址添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:55:01', null);
INSERT INTO `t_access_url` VALUES ('9', 'accessUrl/saveAccessUrl.jhtml', '添加访问地址', null, '1', '1', '小咖', null, null, '2015-08-30 18:55:23', null);
INSERT INTO `t_access_url` VALUES ('10', 'accessUrl/preAccessUrlView.jhtml', '跳转访问地址查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:55:43', null);
INSERT INTO `t_access_url` VALUES ('11', 'accessUrl/preAccessUrlEdit.jhtml', '跳转访问地址编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:56:01', null);
INSERT INTO `t_access_url` VALUES ('12', 'accessUrl/updateAccessUrlById.jhtml', '根据id修改访问地址', null, '1', '1', '小咖', null, null, '2015-08-30 18:56:18', null);
INSERT INTO `t_access_url` VALUES ('13', 'accessUrl/deleteAccessUrlByIds.jhtml', '根据ids删除访问地址', null, '1', '1', '小咖', null, null, '2015-08-30 18:56:37', null);
INSERT INTO `t_access_url` VALUES ('14', 'button/preButtonManage.jhtml', '跳转按钮管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:57:03', null);
INSERT INTO `t_access_url` VALUES ('15', 'button/queryButtonByConditionPaging.jhtml', '根据条件分页查询按钮', null, '1', '1', '小咖', null, null, '2015-08-30 18:57:25', null);
INSERT INTO `t_access_url` VALUES ('16', 'button/preButtonAdd.jhtml', '跳转按钮添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:57:44', null);
INSERT INTO `t_access_url` VALUES ('17', 'button/saveButton.jhtml', '添加按钮', null, '1', '1', '小咖', null, null, '2015-08-30 18:58:01', null);
INSERT INTO `t_access_url` VALUES ('18', 'button/preButtonView.jhtml', '跳转按钮查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:58:22', null);
INSERT INTO `t_access_url` VALUES ('19', 'button/preButtonEdit.jhtml', '跳转按钮编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:58:41', null);
INSERT INTO `t_access_url` VALUES ('20', 'button/updateButtonById.jhtml', '根据id修改按钮', null, '1', '1', '小咖', null, null, '2015-08-30 18:59:01', null);
INSERT INTO `t_access_url` VALUES ('21', 'button/deleteButtonByIds.jhtml', '根据ids删除按钮', null, '1', '1', '小咖', null, null, '2015-08-30 18:59:22', null);
INSERT INTO `t_access_url` VALUES ('22', 'dataDic/preDataDicManage.jhtml', '跳转数据字典管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 18:59:56', null);
INSERT INTO `t_access_url` VALUES ('23', 'dataDic/queryDataDicTypeList.jhtml', '查询数据字典分类列表', null, '1', '1', '小咖', null, null, '2015-08-30 19:00:24', null);
INSERT INTO `t_access_url` VALUES ('24', 'dataDic/preDataDicTypeAdd.jhtml', '跳转数据字典分类添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:00:40', null);
INSERT INTO `t_access_url` VALUES ('25', 'dataDic/saveDataDicType.jhtml', '添加数据字典分类', null, '1', '1', '小咖', null, null, '2015-08-30 19:00:59', null);
INSERT INTO `t_access_url` VALUES ('26', 'dataDic/preDataDicTypeEdit.jhtml', '跳转数据字典分类编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:01:18', null);
INSERT INTO `t_access_url` VALUES ('27', 'dataDic/updateDataDicTypeById.jhtml', '根据id修改数据字典分类', null, '1', '1', '小咖', null, null, '2015-08-30 19:01:44', null);
INSERT INTO `t_access_url` VALUES ('28', 'dataDic/deleteDataDicTypeByIds.jhtml', '根据ids删除数据字典分类', null, '1', '1', '小咖', null, null, '2015-08-30 19:02:03', null);
INSERT INTO `t_access_url` VALUES ('29', 'dataDic/queryDataDicItemByIdPaging.jhtml', '根数据字典分类id查询数据字典明细', null, '1', '1', '小咖', '1', '小咖', '2015-08-30 19:02:32', '2015-08-30 19:04:02');
INSERT INTO `t_access_url` VALUES ('30', 'dataDic/preDataDicItemAdd.jhtml', '跳转数据字典明细添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:04:20', null);
INSERT INTO `t_access_url` VALUES ('31', 'dataDic/saveDataDicItem.jhtml', '添加数据字典明细', null, '1', '1', '小咖', null, null, '2015-08-30 19:04:36', null);
INSERT INTO `t_access_url` VALUES ('32', 'dataDic/preDataDicItemView.jhtml', '跳转数据字典明细查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:04:54', null);
INSERT INTO `t_access_url` VALUES ('33', 'dataDic/preDataDicItemEdit.jhtml', '跳转数据字典明细编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:05:17', null);
INSERT INTO `t_access_url` VALUES ('34', 'dataDic/updateDataDicItemById.jhtml', '根据id修改数据字典明细', null, '1', '1', '小咖', null, null, '2015-08-30 19:06:58', null);
INSERT INTO `t_access_url` VALUES ('35', 'dataDic/deleteDataDicItemByIds.jhtml', '根据ids删除数据字典明细', null, '1', '1', '小咖', null, null, '2015-08-30 19:07:15', null);
INSERT INTO `t_access_url` VALUES ('36', 'dataDic/updateDataDicItemSortNumById.jhtml', '数据字典明细移动序号', null, '1', '1', '小咖', null, null, '2015-08-30 19:07:33', null);
INSERT INTO `t_access_url` VALUES ('37', 'dataDic/reloadDataDic.jhtml', '重新载入数据字典', null, '1', '1', '小咖', null, null, '2015-08-30 19:07:54', null);
INSERT INTO `t_access_url` VALUES ('38', 'dataDic/preDataDicItemRoleDistribute.jhtml', '跳转数据字典明细角色分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:08:16', null);
INSERT INTO `t_access_url` VALUES ('39', 'dataDic/saveDataDicItemRoleMapping.jhtml', '添加数据字典明细角色映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:08:34', null);
INSERT INTO `t_access_url` VALUES ('40', 'dept/preDeptManage.jhtml', '跳转部门管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:09:06', null);
INSERT INTO `t_access_url` VALUES ('41', 'dept/queryDeptList.jhtml', '查询所有部门结构', null, '1', '1', '小咖', null, null, '2015-08-30 19:09:22', null);
INSERT INTO `t_access_url` VALUES ('42', 'dept/queryDeptByConditionPaging.jhtml', '根据条件分页查询部门', null, '1', '1', '小咖', null, null, '2015-08-30 19:09:49', null);
INSERT INTO `t_access_url` VALUES ('43', 'dept/preDeptAdd.jhtml', '跳转部门添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:10:09', null);
INSERT INTO `t_access_url` VALUES ('44', 'dept/saveDept.jhtml', '添加部门', null, '1', '1', '小咖', null, null, '2015-08-30 19:10:25', null);
INSERT INTO `t_access_url` VALUES ('45', 'dept/preDeptView.jhtml', '跳转部门查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:10:44', null);
INSERT INTO `t_access_url` VALUES ('46', 'dept/preDeptEdit.jhtml', '跳转部门编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:11:03', null);
INSERT INTO `t_access_url` VALUES ('47', 'dept/updateDeptByDeptId.jhtml', '根据id修改部门', null, '1', '1', '小咖', null, null, '2015-08-30 19:11:20', null);
INSERT INTO `t_access_url` VALUES ('48', 'dept/deleteDeptByIds.jhtml', '根据ids删除部门', null, '1', '1', '小咖', null, null, '2015-08-30 19:11:35', null);
INSERT INTO `t_access_url` VALUES ('49', 'dept/updateDeptParentDeptIdById.jhtml', '根据id移动部门', null, '1', '1', '小咖', null, null, '2015-08-30 19:11:57', null);
INSERT INTO `t_access_url` VALUES ('50', 'dept/preDeptUsersQuery.jhtml', '跳转部门用户查询页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:12:13', null);
INSERT INTO `t_access_url` VALUES ('51', 'fileUpload/uploadImg.jhtml', '图片上传', null, '1', '1', '小咖', null, null, '2015-08-30 19:13:57', null);
INSERT INTO `t_access_url` VALUES ('52', 'fileUpload/uploadImgByNewFileName.jhtml', '图片上传重新生成文件名', null, '1', '1', '小咖', null, null, '2015-08-30 19:14:22', null);
INSERT INTO `t_access_url` VALUES ('53', 'fileUpload/uploadImgCompression.jhtml', '图片上传压缩处理', null, '1', '1', '小咖', null, null, '2015-08-30 19:14:42', null);
INSERT INTO `t_access_url` VALUES ('54', 'loginLog/preLoginLogManage.jhtml', '跳转登录日志管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:15:16', null);
INSERT INTO `t_access_url` VALUES ('55', 'loginLog/queryLoginLogByConditionPaging.jhtml', '根据条件分页查询登录日志', null, '1', '1', '小咖', null, null, '2015-08-30 19:15:39', null);
INSERT INTO `t_access_url` VALUES ('56', 'menu/preMenuManage.jhtml', '跳转菜单管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:15:59', null);
INSERT INTO `t_access_url` VALUES ('57', 'menu/queryMenuList.jhtml', '查询菜单列表', null, '1', '1', '小咖', null, null, '2015-08-30 19:16:15', null);
INSERT INTO `t_access_url` VALUES ('58', 'menu/queryMenuByConditionPaging.jhtml', '根据条件分页查询菜单', null, '1', '1', '小咖', null, null, '2015-08-30 19:16:30', null);
INSERT INTO `t_access_url` VALUES ('59', 'menu/preMenuAdd.jhtml', '跳转菜单添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:17:59', null);
INSERT INTO `t_access_url` VALUES ('60', 'menu/saveMenu.jhtml', '添加菜单', null, '1', '1', '小咖', null, null, '2015-08-30 19:18:15', null);
INSERT INTO `t_access_url` VALUES ('61', 'menu/preMenuView.jhtml', '跳转菜单查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:18:32', null);
INSERT INTO `t_access_url` VALUES ('62', 'menu/preMenuEdit.jhtml', '跳转菜单编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:18:49', null);
INSERT INTO `t_access_url` VALUES ('63', 'menu/updateMenuByMenuId.jhtml', '根据id修改菜单', null, '1', '1', '小咖', null, null, '2015-08-30 19:19:04', null);
INSERT INTO `t_access_url` VALUES ('64', 'menu/deleteMenuByIds.jhtml', '根据ids删除菜单', null, '1', '1', '小咖', null, null, '2015-08-30 19:19:21', null);
INSERT INTO `t_access_url` VALUES ('65', 'menu/updateMenuParentMenuIdById.jhtml', '根据id移动菜单', null, '1', '1', '小咖', null, null, '2015-08-30 19:19:38', null);
INSERT INTO `t_access_url` VALUES ('66', 'menu/updateMenuSortNumById.jhtml', '根据id移动菜单序号', null, '1', '1', '小咖', null, null, '2015-08-30 19:20:03', null);
INSERT INTO `t_access_url` VALUES ('67', 'publicPage/preIconSelect.jhtml', '跳转图标选择页面', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:21:02', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('68', 'publicPage/preSingleUserSelect.jhtml', '跳转单个用户选择页面', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:21:23', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('69', 'publicPage/queryAllUserByConditionPaging.jhtml', '根据条件查询所有用户', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:21:43', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('70', 'publicPage/preSingleDeptSelect.jhtml', '跳转单个部门选择页面', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:22:01', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('71', 'publicPage/queryDeptByCondition.jhtml', '根据条件查询部门', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:22:18', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('72', 'publicPage/preSingleMenuSelect.jhtm', '跳转单个菜单选择页面', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:22:38', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('73', 'publicPage/queryMenuByCondition.jhtml', '根据条件查询菜单', null, '3', '1', '小咖', '1', '卡卡', '2015-08-30 19:23:06', '2015-12-12 20:07:03');
INSERT INTO `t_access_url` VALUES ('74', 'role/preRoleManage.jhtml', '跳转角色管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:25:04', null);
INSERT INTO `t_access_url` VALUES ('75', 'role/queryRoleByConditionPaging.jhtml', '根据条件分页查询角色', null, '1', '1', '小咖', null, null, '2015-08-30 19:25:21', null);
INSERT INTO `t_access_url` VALUES ('76', 'role/preRoleAdd.jhtml', '跳转角色添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:25:38', null);
INSERT INTO `t_access_url` VALUES ('77', 'role/saveRole.jhtml', '添加角色', null, '1', '1', '小咖', null, null, '2015-08-30 19:25:52', null);
INSERT INTO `t_access_url` VALUES ('78', 'role/preRoleView.jhtml', '跳转角色查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:26:08', null);
INSERT INTO `t_access_url` VALUES ('79', 'role/preRoleEdit.jhtml', '跳转角色编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:26:28', null);
INSERT INTO `t_access_url` VALUES ('80', 'role/updateRoleById.jhtml', '根据id修改角色', null, '1', '1', '小咖', null, null, '2015-08-30 19:26:42', null);
INSERT INTO `t_access_url` VALUES ('81', 'role/deleteRoleByIds.jhtml', '根据ids删除角色', null, '1', '1', '小咖', null, null, '2015-08-30 19:26:57', null);
INSERT INTO `t_access_url` VALUES ('82', 'role/preRoleUsersQuery.jhtml', '跳转角色用户查询页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:27:11', null);
INSERT INTO `t_access_url` VALUES ('83', 'role/preRoleMenuDistribute.jhtml', '跳转角色菜单分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:27:28', null);
INSERT INTO `t_access_url` VALUES ('84', 'role/saveRoleMenuMapping.jhtml', '添加角色菜单映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:27:45', null);
INSERT INTO `t_access_url` VALUES ('85', 'role/preRoleButtonDistribute.jhtml', '跳转角色按钮分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:28:01', null);
INSERT INTO `t_access_url` VALUES ('86', 'role/queryRoleButtonMappingById.jhtml', '根据角色id查询角色按钮映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:29:29', null);
INSERT INTO `t_access_url` VALUES ('87', 'role/saveRoleButtonMapping.jhtml', '添加角色按钮映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:29:46', null);
INSERT INTO `t_access_url` VALUES ('88', 'role/preRoleAccessUrlDistribute.jhtml', '跳转角色访问地址分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:30:02', null);
INSERT INTO `t_access_url` VALUES ('89', 'role/saveRoleAccessUrlMapping.jhtml', '添加角色访问地址映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:30:19', null);
INSERT INTO `t_access_url` VALUES ('90', 'systemLog/preSystemLogManage.jhtm', '跳转日志管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:31:05', null);
INSERT INTO `t_access_url` VALUES ('91', 'systemLog/downloadSystemLog.jhtml', '系统日志下载', null, '1', '1', '小咖', null, null, '2015-08-30 19:31:28', null);
INSERT INTO `t_access_url` VALUES ('92', 'systemLog/deleteSystemLog.jhtml', '删除系统日志', null, '1', '1', '小咖', null, null, '2015-08-30 19:31:55', null);
INSERT INTO `t_access_url` VALUES ('93', 'task/preTaskManage.jhtml', '跳转待办管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:32:20', null);
INSERT INTO `t_access_url` VALUES ('94', 'task/queryTaskByConditionPaging.jhtml', '根据条件分页查询待办 ', null, '1', '1', '小咖', null, null, '2015-08-30 19:32:35', null);
INSERT INTO `t_access_url` VALUES ('95', 'task/updateTaskById.jhtml', '根据id修改待办状态', null, '1', '1', '小咖', null, null, '2015-08-30 19:32:51', null);
INSERT INTO `t_access_url` VALUES ('96', 'user/preUserManage.jhtml', '跳转用户管理页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:33:12', null);
INSERT INTO `t_access_url` VALUES ('97', 'user/queryUserByConditionPaging.jhtml', '根据条件分页查询用户', null, '1', '1', '小咖', null, null, '2015-08-30 19:33:29', null);
INSERT INTO `t_access_url` VALUES ('98', 'user/preUserAdd.jhtml', '跳转用户添加页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:33:45', null);
INSERT INTO `t_access_url` VALUES ('99', 'user/saveUser.jhtml', '添加用户', null, '1', '1', '小咖', null, null, '2015-08-30 19:33:59', null);
INSERT INTO `t_access_url` VALUES ('100', 'user/preUserView.jhtml', '跳转用户查看页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:34:16', null);
INSERT INTO `t_access_url` VALUES ('101', 'user/preUserEdit.jhtml', '跳转用户编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:34:31', null);
INSERT INTO `t_access_url` VALUES ('102', 'user/updateUserById.jhtml', '根据id修改用户', null, '1', '1', '小咖', null, null, '2015-08-30 19:34:48', null);
INSERT INTO `t_access_url` VALUES ('103', 'user/preUserPasswordEdit.jhtml', '跳转用户密码编辑页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:35:03', null);
INSERT INTO `t_access_url` VALUES ('104', 'user/updateUserPasswordById.jhtml', '根据id修改密码', null, '1', '1', '小咖', null, null, '2015-08-30 19:35:23', null);
INSERT INTO `t_access_url` VALUES ('105', 'user/deleteUserByIds.jhtml', '根据ids删除用户', null, '1', '1', '小咖', null, null, '2015-08-30 19:35:38', null);
INSERT INTO `t_access_url` VALUES ('106', 'user/preUserRoleDistribute.jhtml', '跳转用户用户分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:35:52', null);
INSERT INTO `t_access_url` VALUES ('107', 'user/saveUserRoleMapping.jhtml', '保存用户角色映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:36:06', null);
INSERT INTO `t_access_url` VALUES ('108', 'user/preUserDeptDistribute.jhtml', '跳转用户部门分配页面', null, '1', '1', '小咖', null, null, '2015-08-30 19:36:22', null);
INSERT INTO `t_access_url` VALUES ('109', 'user/saveUserDeptMapping.jhtml', '保存用户部门映射', null, '1', '1', '小咖', null, null, '2015-08-30 19:36:38', null);
INSERT INTO `t_access_url` VALUES ('110', 'user/resetUserPwdByIds.jhtml', '根据ids重置用户密码', null, '1', '1', '小咖', '1', '小咖', '2015-10-17 18:38:44', '2015-10-17 18:54:02');
INSERT INTO `t_access_url` VALUES ('111', 'dataDic/updateDataDicItemParentIdById.jhtml', '根据id移动字典明细', null, '1', '1', '卡卡', null, null, '2015-12-13 12:57:38', null);
INSERT INTO `t_access_url` VALUES ('112', 'notice/preSysNoticeManage.jhtml', '跳转公告管理页面', null, '1', '1', '卡卡', null, null, '2016-04-02 14:42:12', null);
INSERT INTO `t_access_url` VALUES ('113', 'notice/querySysNoticeByConditionPaging.jhtml', '根据条件分页查询系统公告', null, '1', '1', '卡卡', null, null, '2016-04-02 14:43:05', null);
INSERT INTO `t_access_url` VALUES ('114', 'notice/preSysNoticeAdd.jhtml', '跳转系统公告添加页面', null, '1', '1', '卡卡', '1', '卡卡', '2016-04-03 14:36:32', '2016-04-03 14:44:36');
INSERT INTO `t_access_url` VALUES ('115', 'notice/saveSysNotice.jhtml', '添加系统公告', null, '1', '1', '卡卡', null, null, '2016-04-03 16:12:15', null);
INSERT INTO `t_access_url` VALUES ('116', 'notice/preSysNoticeView.jhtml', '跳转系统公告查看页面', null, '1', '1', '卡卡', null, null, '2016-04-04 15:06:25', null);
INSERT INTO `t_access_url` VALUES ('117', 'notice/preSysNoticeEdit.jhtml', '跳转系统公告编辑页面', null, '1', '1', '卡卡', null, null, '2016-04-04 15:39:29', null);
INSERT INTO `t_access_url` VALUES ('118', 'notice/updateSysNoticeById.jhtml', '根据id修改系统公告', null, '1', '1', '卡卡', null, null, '2016-04-04 15:39:47', null);
INSERT INTO `t_access_url` VALUES ('119', 'notice/deleteSysNoticeByIds.jhtml', '根据ids删除系统公告 ', null, '1', '1', '卡卡', null, null, '2016-04-04 15:40:01', null);

-- ----------------------------
-- Table structure for `t_button`
-- ----------------------------
DROP TABLE IF EXISTS `t_button`;
CREATE TABLE `t_button` (
  `buttonId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `buttonName` varchar(30) DEFAULT NULL COMMENT '按钮名称',
  `buttonType` varchar(30) DEFAULT NULL COMMENT '按钮分类',
  `functionName` varchar(60) DEFAULT NULL COMMENT '函数名称',
  `menuId` int(20) DEFAULT NULL COMMENT '菜单id',
  `busniessMark` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `layoutMark` varchar(10) DEFAULT NULL COMMENT '布局标识',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '样式',
  `buttonDescribe` varchar(200) DEFAULT NULL COMMENT '按钮描述',
  `sortNum` int(2) DEFAULT NULL COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`buttonId`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_button
-- ----------------------------
INSERT INTO `t_button` VALUES ('1', '添加', '1', 'userAdd();', '9', 'USER_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:28:31', '2015-10-17 19:42:25');
INSERT INTO `t_button` VALUES ('2', '查看', '4', 'userView();', '9', 'USER_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:34:08', '2015-10-17 19:42:31');
INSERT INTO `t_button` VALUES ('3', '编辑', '2', 'userEdit();', '9', 'USER_MANAGE', '5', 'icon-edit', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:35:19', '2015-10-17 19:42:37');
INSERT INTO `t_button` VALUES ('4', '删除', '3', 'delUserById();', '9', 'USER_MANAGE', '5', 'icon-del', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:51:33', '2015-10-17 19:42:44');
INSERT INTO `t_button` VALUES ('5', '密码修改', '2', 'userPasswordEdit(); ', '9', 'USER_MANAGE', '5', 'icon-pwd-change', '', '5', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:52:48', '2015-10-17 19:42:50');
INSERT INTO `t_button` VALUES ('6', '角色分配', '5', 'userRoleDistribute();', '9', 'USER_MANAGE', '5', 'icon-config', '', '7', '1', '1', 'admin', '1', '小咖', '2015-06-19 17:56:34', '2015-10-17 19:43:02');
INSERT INTO `t_button` VALUES ('7', '部门分配', '5', 'userDeptDistribute();', '9', 'USER_MANAGE', '5', 'icon-config', '', '8', '1', '1', 'admin', '1', '小咖', '2015-06-19 18:01:58', '2015-10-17 19:43:07');
INSERT INTO `t_button` VALUES ('8', '添加', '1', 'deptAdd();', '7', 'DEPT_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:30:07', '2015-10-17 19:36:54');
INSERT INTO `t_button` VALUES ('9', '查看', '4', 'deptView();', '7', 'DEPT_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:30:46', '2015-10-17 19:37:00');
INSERT INTO `t_button` VALUES ('10', '编辑', '2', 'deptEdit();', '7', 'DEPT_MANAGE', '5', 'icon-edit', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:31:18', '2015-10-17 19:37:05');
INSERT INTO `t_button` VALUES ('11', '删除', '3', 'delDeptById();', '7', 'DEPT_MANAGE', '5', 'icon-del', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:32:02', '2015-10-17 19:37:13');
INSERT INTO `t_button` VALUES ('12', '移动', '5', 'deptMove();', '7', 'DEPT_MANAGE', '5', 'icon-move', '', '5', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:33:06', '2015-10-17 19:37:19');
INSERT INTO `t_button` VALUES ('13', '部门用户', '4', 'deptUsersQuery();', '7', 'DEPT_MANAGE', '5', 'icon-admin', '', '6', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:33:41', '2015-10-17 19:37:26');
INSERT INTO `t_button` VALUES ('14', '添加', '1', 'roleAdd();', '5', 'ROLE_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:36:12', '2015-10-17 19:37:37');
INSERT INTO `t_button` VALUES ('15', '查看', '4', 'roleView();', '5', 'ROLE_MANAGE', '5', 'icon-view', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:36:43', '2015-10-17 19:37:48');
INSERT INTO `t_button` VALUES ('16', '编辑', '2', 'roleEdit();', '5', 'ROLE_MANAGE', '5', 'icon-edit', '', '2', '1', '1', 'admin', '1', '卡卡', '2015-06-20 17:37:10', '2016-04-03 14:29:15');
INSERT INTO `t_button` VALUES ('17', '删除', '3', 'delRoleById();', '5', 'ROLE_MANAGE', '5', 'icon-del', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:38:21', '2015-10-17 19:37:55');
INSERT INTO `t_button` VALUES ('18', '角色用户', '4', 'roleUsersQuery();', '5', 'ROLE_MANAGE', '5', 'icon-admin', '', '5', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:38:55', '2015-10-17 19:38:00');
INSERT INTO `t_button` VALUES ('19', '菜单分配', '5', 'roleMenuDistribute();', '5', 'ROLE_MANAGE', '5', 'icon-config', '', '6', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:39:26', '2015-10-17 19:38:07');
INSERT INTO `t_button` VALUES ('20', '按钮分配', '5', 'roleButtonDistribute();', '5', 'ROLE_MANAGE', '5', 'icon-config', '', '7', '1', '1', 'admin', '1', '小咖', '2015-06-20 17:40:13', '2015-10-17 19:38:14');
INSERT INTO `t_button` VALUES ('21', '添加', '1', 'menuAdd();', '6', 'MENU_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:30:58', '2015-10-17 19:38:30');
INSERT INTO `t_button` VALUES ('22', '查看', '4', 'menuView();', '6', 'MENU_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:31:23', '2015-10-17 19:30:02');
INSERT INTO `t_button` VALUES ('23', '编辑', '2', 'menuEdit();', '6', 'MENU_MANAGE', '5', 'icon-edit', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:31:48', '2015-10-17 19:30:07');
INSERT INTO `t_button` VALUES ('24', '删除', '3', 'delMenuById();', '6', 'MENU_MANAGE', '5', 'icon-del', '', '5', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:32:22', '2015-10-17 19:30:21');
INSERT INTO `t_button` VALUES ('25', '移动', '5', 'menuMove();', '6', 'MENU_MANAGE', '5', 'icon-move', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:32:55', '2015-10-17 19:30:14');
INSERT INTO `t_button` VALUES ('26', '上移', '5', 'menuMoveUp();', '6', 'MENU_MANAGE', '5', 'icon-move-up', '', '7', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:33:19', '2015-10-17 19:30:39');
INSERT INTO `t_button` VALUES ('27', '下移', '5', 'menuMoveDown();', '6', 'MENU_MANAGE', '5', 'icon-move-down', '', '6', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:33:39', '2015-10-17 19:30:32');
INSERT INTO `t_button` VALUES ('28', '添加', '1', 'buttonAdd();', '8', 'BTN_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:34:13', '2015-10-17 19:38:53');
INSERT INTO `t_button` VALUES ('29', '查看', '4', 'buttonView();', '8', 'BTN_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:34:37', '2015-10-17 19:38:58');
INSERT INTO `t_button` VALUES ('30', '编辑', '2', 'buttonEdit();', '8', 'BTN_MANAGE', '5', 'icon-edit', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:35:03', '2015-10-17 19:39:04');
INSERT INTO `t_button` VALUES ('31', '删除', '3', 'delButtonById();', '8', 'BTN_MANAGE', '5', 'icon-del', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:35:27', '2015-10-17 19:39:09');
INSERT INTO `t_button` VALUES ('32', '添加', '1', 'dataDicTypeAdd();', '10', 'DATA_DIC_MANAGE', '3', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:36:48', '2015-10-17 19:39:35');
INSERT INTO `t_button` VALUES ('33', '修改', '2', 'dataDicTypeEdit();', '10', 'DATA_DIC_MANAGE', '3', 'icon-edit', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:37:19', '2015-10-17 19:39:41');
INSERT INTO `t_button` VALUES ('34', '删除', '3', 'delDicTypeById();', '10', 'DATA_DIC_MANAGE', '3', 'icon-del', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:37:55', '2015-10-17 19:39:47');
INSERT INTO `t_button` VALUES ('35', '添加', '1', 'dataDicItenAdd();', '10', 'DATA_DIC_MANAGE', '5', 'icon-add', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:38:37', '2015-10-17 19:39:52');
INSERT INTO `t_button` VALUES ('36', '查看', '4', 'dataDicItenView();', '10', 'DATA_DIC_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:39:08', '2015-10-17 19:40:00');
INSERT INTO `t_button` VALUES ('37', '编辑', '2', 'dataDicItenEdit();', '10', 'DATA_DIC_MANAGE', '5', 'icon-edit', '', '3', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:39:31', '2015-10-17 19:40:10');
INSERT INTO `t_button` VALUES ('38', '删除', '3', 'delDicItemById();', '10', 'DATA_DIC_MANAGE', '5', 'icon-del', '', '4', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:40:08', '2015-12-05 20:44:22');
INSERT INTO `t_button` VALUES ('39', '上移', '5', 'dicItemMoveUp();', '10', 'DATA_DIC_MANAGE', '5', 'icon-move-up', '', '5', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:40:30', '2015-10-17 19:40:22');
INSERT INTO `t_button` VALUES ('40', '下移', '5', 'dicItemMoveDown();', '10', 'DATA_DIC_MANAGE', '5', 'icon-move-down', '', '6', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:40:55', '2015-10-17 19:40:28');
INSERT INTO `t_button` VALUES ('41', '角色分配', '5', 'dicItemRoleDistribute();', '10', 'DATA_DIC_MANAGE', '5', 'icon-config', '', '7', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:41:20', '2015-10-17 19:40:37');
INSERT INTO `t_button` VALUES ('42', '下载', '6', 'downloadFile();', '13', 'SYS_LOG_MANAGE', '5', 'icon-download', '', '1', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:45:43', '2015-10-17 19:40:47');
INSERT INTO `t_button` VALUES ('43', '删除', '3', 'delFile();', '13', 'SYS_LOG_MANAGE', '5', 'icon-del', '', '2', '1', '1', 'admin', '1', '小咖', '2015-06-21 20:46:07', '2015-10-17 19:40:57');
INSERT INTO `t_button` VALUES ('44', '取消待办', '2', 'updateTaskStatus();', '11', 'TASK_MANAGE', '5', 'icon-edit', '', '1', '1', '1', 'admin', '1', '小咖', '2015-07-22 16:46:32', '2015-10-17 19:41:07');
INSERT INTO `t_button` VALUES ('45', '查看', '4', 'taskView();', '11', 'TASK_MANAGE', '5', 'icon-view', '', '2', '1', '1', 'admin', '1', '小咖', '2015-07-22 17:02:50', '2015-10-17 19:41:13');
INSERT INTO `t_button` VALUES ('46', '添加', '1', 'accessUrlAdd();', '19', 'ACCESS_URL_MANAGE', '5', 'icon-add', '', '1', '1', '1', '小咖', '1', '小咖', '2015-08-29 16:12:05', '2015-10-17 19:28:19');
INSERT INTO `t_button` VALUES ('47', '查看', '4', 'accessUrlView();', '19', 'ACCESS_URL_MANAGE', '5', 'icon-view', '', '2', '1', '1', '小咖', '1', '小咖', '2015-08-29 16:28:27', '2015-10-17 19:28:41');
INSERT INTO `t_button` VALUES ('48', '编辑', '2', ' accessUrlEdit();', '19', 'ACCESS_URL_MANAGE', '5', 'icon-edit', '', '3', '1', '1', '小咖', '1', '小咖', '2015-08-29 16:29:06', '2015-10-17 19:29:12');
INSERT INTO `t_button` VALUES ('49', '删除', '3', 'delAccessUrlById();', '19', 'ACCESS_URL_MANAGE', '5', 'icon-del', '', '4', '1', '1', '小咖', '1', '小咖', '2015-08-29 16:29:42', '2015-10-17 19:29:19');
INSERT INTO `t_button` VALUES ('50', '访问地址分配', '5', 'roleAccessUrlDistribute();', '5', 'ROLE_MANAGE', '5', 'icon-config', '', '8', '1', '1', '小咖', '1', '小咖', '2015-08-30 08:59:01', '2015-10-17 19:38:21');
INSERT INTO `t_button` VALUES ('51', '重置密码', '2', 'userPasswordReset();', '9', 'USER_MANAGE', '5', 'icon-pwd-change', '', '6', '1', '1', '小咖', '1', '小咖', '2015-10-17 18:37:50', '2015-10-17 19:42:56');
INSERT INTO `t_button` VALUES ('52', '添加', '1', 'sysNoticeAdd();', '15', 'SYS_NOTICE_MANAGE', '5', 'icon-add', '', '1', '1', '1', '卡卡', '1', '卡卡', '2016-04-03 14:28:27', '2016-04-03 14:42:20');
INSERT INTO `t_button` VALUES ('53', '查看', '4', 'sysNoticeView();', '15', 'SYS_NOTICE_MANAGE', '5', 'icon-view', '', '2', '1', '1', '卡卡', null, null, '2016-04-04 15:06:04', null);
INSERT INTO `t_button` VALUES ('54', '编辑', '2', 'sysNoticeEdit();', '15', 'SYS_NOTICE_MANAGE', '5', 'icon-edit', '', '3', '1', '1', '卡卡', null, null, '2016-04-04 15:38:30', null);
INSERT INTO `t_button` VALUES ('55', '删除', '3', 'delSysNoticeById();', '15', 'SYS_NOTICE_MANAGE', '5', 'icon-del', '', '4', '1', '1', '卡卡', null, null, '2016-04-04 15:39:08', null);

-- ----------------------------
-- Table structure for `t_com_func`
-- ----------------------------
DROP TABLE IF EXISTS `t_com_func`;
CREATE TABLE `t_com_func` (
  `comFuncId` int(20) NOT NULL AUTO_INCREMENT COMMENT '常用功能id',
  `menuId` int(20) DEFAULT NULL COMMENT ' 菜单id',
  `userId` int(20) DEFAULT NULL COMMENT '用户id',
  `roleId` int(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`comFuncId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_com_func
-- ----------------------------
INSERT INTO `t_com_func` VALUES ('13', '5', '1', '1');
INSERT INTO `t_com_func` VALUES ('14', '6', '1', '1');
INSERT INTO `t_com_func` VALUES ('15', '7', '1', '1');
INSERT INTO `t_com_func` VALUES ('16', '8', '1', '1');
INSERT INTO `t_com_func` VALUES ('17', '9', '1', '1');
INSERT INTO `t_com_func` VALUES ('18', '11', '1', '1');
INSERT INTO `t_com_func` VALUES ('19', '12', '1', '1');
INSERT INTO `t_com_func` VALUES ('20', '17', '1', '1');
INSERT INTO `t_com_func` VALUES ('21', '18', '1', '1');
INSERT INTO `t_com_func` VALUES ('22', '20', '1', '1');

-- ----------------------------
-- Table structure for `t_data_dic_item`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_item`;
CREATE TABLE `t_data_dic_item` (
  `dicItemId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dicTypeId` int(20) DEFAULT NULL COMMENT '数据字典分类id',
  `dicTypeCode` char(30) DEFAULT NULL COMMENT '数据字典分类编号',
  `dicItemCode` char(30) DEFAULT NULL COMMENT '明细编号',
  `dicItemParentId` int(20) DEFAULT NULL COMMENT '明细信息父id',
  `dicItemName` varchar(60) DEFAULT NULL COMMENT '名称',
  `dicItemValue` varchar(100) DEFAULT NULL COMMENT '值',
  `spareValue` varchar(100) DEFAULT NULL COMMENT '备用值',
  `defaultValue` varchar(100) DEFAULT NULL COMMENT '默认值',
  `dicItemDesc` varchar(200) DEFAULT NULL COMMENT '描述',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '样式',
  `sortNum` int(20) DEFAULT NULL COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `byRole` char(1) DEFAULT NULL COMMENT '通过角色过滤(1是，2否)',
  PRIMARY KEY (`dicItemId`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_item
-- ----------------------------
INSERT INTO `t_data_dic_item` VALUES ('1', '1', 'STATUS_TYPE', 'NORMAL', '-1', '正常', '1', null, '', '', '', '1', '1', '37', '小杂碎', '1', '卡卡', '2015-06-15 20:16:09', '2015-12-27 09:46:17', null);
INSERT INTO `t_data_dic_item` VALUES ('2', '1', 'STATUS_TYPE', 'LOCK', '-1', '锁定', '2', null, '', '', '', '2', '1', '37', '小杂碎', '1', '卡卡', '2015-06-15 20:16:20', '2015-12-27 09:34:18', null);
INSERT INTO `t_data_dic_item` VALUES ('3', '2', 'QUERY_STATUS_TYPE', 'QUERY_ALL', '-1', '所有', '-1', null, 'false', '', '', '1', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 20:16:39', '2015-06-16 16:37:19', null);
INSERT INTO `t_data_dic_item` VALUES ('4', '2', 'QUERY_STATUS_TYPE', 'QUERY_NORMAL', '-1', '正常', '1', null, 'true', '', '', '2', '1', '37', '小杂碎', '1', '百变小咖', '2015-06-15 20:16:50', '2015-08-06 15:53:07', null);
INSERT INTO `t_data_dic_item` VALUES ('5', '2', 'QUERY_STATUS_TYPE', 'QUERY_LOCK', '-1', '锁定', '2', null, 'false', '', '', '3', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 20:16:59', '2015-06-16 16:37:24', null);
INSERT INTO `t_data_dic_item` VALUES ('6', '3', 'SEX_TYPE', 'MALE', '-1', '男', '1', null, '', '', '', '1', '1', '37', '小杂碎', '1', '卡卡', '2015-06-15 20:26:05', '2015-12-13 13:00:39', null);
INSERT INTO `t_data_dic_item` VALUES ('7', '3', 'SEX_TYPE', 'FEMALE', '-1', '女', '2', null, '', '', '', '2', '1', '37', '小杂碎', '1', '卡卡', '2015-06-15 20:27:54', '2015-12-13 13:00:59', null);
INSERT INTO `t_data_dic_item` VALUES ('8', '4', 'EMAIL_SWITCH_TYPE', 'OPEN', '-1', '开', '1', null, null, '', '', '1', '1', '37', '小杂碎', null, null, '2015-06-15 20:28:20', null, null);
INSERT INTO `t_data_dic_item` VALUES ('9', '4', 'EMAIL_SWITCH_TYPE', 'CLOSE', '-1', '关', '2', null, null, '', '', '2', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 20:28:31', '2015-06-15 20:28:49', null);
INSERT INTO `t_data_dic_item` VALUES ('10', '5', 'SMS_SWITCH_TYPE', 'OPEN', '-1', '开', '1', null, null, '', '', '1', '1', '37', '小杂碎', null, null, '2015-06-15 20:28:56', null, null);
INSERT INTO `t_data_dic_item` VALUES ('11', '5', 'SMS_SWITCH_TYPE', 'CLOSE', '-1', '关', '2', null, null, '', '', '2', '1', '37', '小杂碎', null, null, '2015-06-15 20:29:02', null, null);
INSERT INTO `t_data_dic_item` VALUES ('12', '7', 'QUERY_IS_SUPER_TYPE', 'QUERY_ALL', '-1', '所有', '-1', null, null, '', '', '1', '1', '37', '小杂碎', null, null, '2015-06-16 11:04:27', null, null);
INSERT INTO `t_data_dic_item` VALUES ('13', '7', 'QUERY_IS_SUPER_TYPE', 'QUERY_YES', '-1', '是', '1', null, null, '', '', '2', '1', '37', '小杂碎', null, null, '2015-06-16 11:04:35', null, null);
INSERT INTO `t_data_dic_item` VALUES ('14', '7', 'QUERY_IS_SUPER_TYPE', 'QUERY_NO', '-1', '否', '2', null, null, '', '', '3', '1', '37', '小杂碎', null, null, '2015-06-16 11:04:45', null, null);
INSERT INTO `t_data_dic_item` VALUES ('15', '8', 'IS_SUPER_TYPE', 'YES', '-1', '是', '1', null, null, '', '', '1', '1', '37', '小杂碎', null, null, '2015-06-16 11:16:54', null, null);
INSERT INTO `t_data_dic_item` VALUES ('16', '8', 'IS_SUPER_TYPE', 'NO', '-1', '否', '2', null, null, '', '', '2', '1', '37', '小杂碎', null, null, '2015-06-16 11:17:00', null, null);
INSERT INTO `t_data_dic_item` VALUES ('17', '10', 'BUTTON_CATEGORY_TYPE', 'ADD', '-1', '增加', '1', null, '', '', '', '1', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 12:27:50', '2015-06-19 17:55:37', null);
INSERT INTO `t_data_dic_item` VALUES ('18', '10', 'BUTTON_CATEGORY_TYPE', 'EDIT', '-1', '修改', '2', null, '', '', '', '2', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 12:28:02', '2015-06-19 17:55:32', null);
INSERT INTO `t_data_dic_item` VALUES ('19', '10', 'BUTTON_CATEGORY_TYPE', 'DEL', '-1', '删除', '3', null, '', '', '', '3', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 12:28:22', '2015-06-19 17:55:27', null);
INSERT INTO `t_data_dic_item` VALUES ('20', '10', 'BUTTON_CATEGORY_TYPE', 'QUERY', '-1', '查询', '4', null, '', '', '', '4', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 12:28:31', '2015-06-19 17:55:22', null);
INSERT INTO `t_data_dic_item` VALUES ('21', '9', 'BUTTON_LAYOUT_MARK_TYPE', 'NORTH', '-1', '北', '1', null, '', '', '', '1', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 12:50:31', '2015-06-19 17:40:01', null);
INSERT INTO `t_data_dic_item` VALUES ('22', '9', 'BUTTON_LAYOUT_MARK_TYPE', 'SOUTH', '-1', '南', '2', null, '', '', '', '2', '1', '37', '小杂碎', '1', 'admin', '2015-06-16 13:09:38', '2015-06-19 17:40:05', null);
INSERT INTO `t_data_dic_item` VALUES ('23', '9', 'BUTTON_LAYOUT_MARK_TYPE', 'WEST', '-1', '西', '3', null, '', '', '', '3', '1', '1', 'admin', '1', 'admin', '2015-06-19 17:37:54', '2015-06-19 17:40:09', null);
INSERT INTO `t_data_dic_item` VALUES ('24', '9', 'BUTTON_LAYOUT_MARK_TYPE', 'EAST', '-1', '东', '4', null, '', '', '', '4', '1', '1', 'admin', '1', 'admin', '2015-06-19 17:38:52', '2015-06-19 17:40:12', null);
INSERT INTO `t_data_dic_item` VALUES ('25', '9', 'BUTTON_LAYOUT_MARK_TYPE', 'CENTER', '-1', '中', '5', null, '', '', '', '5', '1', '1', 'admin', '1', 'admin', '2015-06-19 17:39:05', '2015-06-19 17:40:16', null);
INSERT INTO `t_data_dic_item` VALUES ('26', '10', 'BUTTON_CATEGORY_TYPE', 'CONFIG', '-1', '配置', '5', null, '', '', '', '5', '1', '1', 'admin', null, null, '2015-06-19 17:55:08', null, null);
INSERT INTO `t_data_dic_item` VALUES ('27', '10', 'BUTTON_CATEGORY_TYPE', 'DOWNLOAD', '-1', '下载', '6', null, '', '', '', '6', '1', '1', 'admin', null, null, '2015-06-21 20:42:46', null, null);
INSERT INTO `t_data_dic_item` VALUES ('28', '10', 'BUTTON_CATEGORY_TYPE', 'UPLOAD', '-1', '上传', '7', null, '', '', '', '7', '1', '1', 'admin', null, null, '2015-06-21 20:42:59', null, null);
INSERT INTO `t_data_dic_item` VALUES ('29', '11', 'INDIVIDUATION_MENU_TYPE', 'ACCORDION_MENU', '-1', '手风琴菜单(默认)', '1', null, '', '', '', '1', '1', '1', 'admin', null, null, '2015-06-26 21:07:35', null, null);
INSERT INTO `t_data_dic_item` VALUES ('30', '11', 'INDIVIDUATION_MENU_TYPE', 'TREE_MENU', '-1', '树形菜单', '2', null, '', '', '', '2', '1', '1', 'admin', null, null, '2015-06-26 21:07:55', null, null);
INSERT INTO `t_data_dic_item` VALUES ('31', '12', 'INDIVIDUATION_THEME_TYPE', 'DEFAULT', '-1', '蓝色主题', 'blue', '', '', '', '', '2', '1', '1', 'admin', '1', '卡卡', '2015-06-27 16:24:23', '2016-02-06 16:36:24', null);
INSERT INTO `t_data_dic_item` VALUES ('32', '12', 'INDIVIDUATION_THEME_TYPE', 'GREEN', '-1', '绿色主题', 'green', '', '', '', '', '3', '1', '1', 'admin', '1', '卡卡', '2015-06-27 16:24:41', '2016-02-06 16:36:22', null);
INSERT INTO `t_data_dic_item` VALUES ('33', '12', 'INDIVIDUATION_THEME_TYPE', 'GRAY', '-1', '灰色主题（默认）', 'gray', '', '', '', '', '1', '1', '1', 'admin', '1', '卡卡', '2015-07-02 20:46:32', '2016-02-07 10:26:34', null);
INSERT INTO `t_data_dic_item` VALUES ('34', '12', 'INDIVIDUATION_THEME_TYPE', 'LIGHT_BLUE', '-1', '淡蓝色主题', 'light-blue', null, null, null, null, '4', '1', '1', 'admin', null, null, '2015-06-27 16:24:41', null, null);
INSERT INTO `t_data_dic_item` VALUES ('35', '13', 'TASK_STATUS_TYPE', 'ALL', '-1', '所有', '-1', null, 'false', '', '', '1', '1', '1', 'admin', '1', 'admin', '2015-07-14 17:41:10', '2015-07-14 17:45:44', null);
INSERT INTO `t_data_dic_item` VALUES ('36', '13', 'TASK_STATUS_TYPE', 'TREATED', '-1', '已处理', '2', null, 'false', '', '', '3', '1', '1', 'admin', '1', 'admin', '2015-07-14 17:41:42', '2015-07-14 17:46:08', null);
INSERT INTO `t_data_dic_item` VALUES ('37', '13', 'TASK_STATUS_TYPE', 'UNTREATED', '-1', '未处理', '1', null, 'true', '', '', '2', '1', '1', 'admin', '1', 'admin', '2015-07-14 17:41:56', '2015-07-22 16:23:49', null);
INSERT INTO `t_data_dic_item` VALUES ('38', '14', 'DIC_CATEGORY_TYPE', 'SYS_DIC', '-1', '系统字典', '1', null, '1', '', '', '1', '1', '1', '小咖', null, null, '2015-11-18 21:26:06', null, null);
INSERT INTO `t_data_dic_item` VALUES ('39', '14', 'DIC_CATEGORY_TYPE', 'BUSSNIESS_DIC', '-1', '业务字典', '2', null, '', '', '', '2', '1', '1', '小咖', null, null, '2015-11-18 21:27:02', null, null);
INSERT INTO `t_data_dic_item` VALUES ('40', '12', 'INDIVIDUATION_THEME_TYPE', 'DARK_GRAY', '-1', '深灰色主题', 'dark-gray', null, '', '', '', '5', '1', '1', '小咖', null, null, '2015-11-29 15:34:02', null, null);
INSERT INTO `t_data_dic_item` VALUES ('41', '12', 'INDIVIDUATION_THEME_TYPE', 'LIGHT_GREEN', '-1', '浅绿色色主题', 'light-green', null, '', '', '', '6', '1', '1', '卡卡', '1', '卡卡', '2015-12-11 19:47:15', '2015-12-20 15:30:08', null);
INSERT INTO `t_data_dic_item` VALUES ('53', '15', 'dd', 'a', '-1', 'a', 'a', 'd', 'a', '', '', '1', '1', '1', '卡卡', '1', '卡卡', '2016-01-05 13:03:47', '2016-01-05 13:04:53', null);
INSERT INTO `t_data_dic_item` VALUES ('54', '16', 'QUERY_S_TYPE', 'QUERY_ALL', '-1', '所有', '-1', '', 'false', '', '', '1', '1', '1', '卡卡', null, null, '2016-04-02 15:19:32', null, null);
INSERT INTO `t_data_dic_item` VALUES ('55', '16', 'QUERY_S_TYPE', 'QUERY_NORMAL', '-1', '正常', '1', '', 'true', '', '', '2', '1', '1', '卡卡', null, null, '2016-04-02 15:19:58', null, null);
INSERT INTO `t_data_dic_item` VALUES ('56', '16', 'QUERY_S_TYPE', 'QUERY_CLOSE', '-1', '关闭', '2', '', 'false', '', '', '3', '1', '1', '卡卡', null, null, '2016-04-02 15:20:23', null, null);
INSERT INTO `t_data_dic_item` VALUES ('57', '17', 'S_TYPE', 'NORMAL', '-1', '正常', '1', '', '', '', '', '1', '1', '1', '卡卡', null, null, '2016-04-02 15:23:54', null, null);
INSERT INTO `t_data_dic_item` VALUES ('58', '17', 'S_TYPE', 'CLOSE', '-1', '关闭', '2', '', '', '', '', '2', '1', '1', '卡卡', null, null, '2016-04-02 15:24:09', null, null);

-- ----------------------------
-- Table structure for `t_data_dic_item_role_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_item_role_mapping`;
CREATE TABLE `t_data_dic_item_role_mapping` (
  `dirmId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dicItemId` int(20) DEFAULT NULL COMMENT '数据字典明细id',
  `roleId` int(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`dirmId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_item_role_mapping
-- ----------------------------

-- ----------------------------
-- Table structure for `t_data_dic_type`
-- ----------------------------
DROP TABLE IF EXISTS `t_data_dic_type`;
CREATE TABLE `t_data_dic_type` (
  `dicTypeId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `dicTypeCode` char(30) DEFAULT NULL COMMENT '分类编码',
  `dicTypeName` varchar(30) DEFAULT NULL COMMENT '分类名称',
  `dicCategory` char(1) DEFAULT NULL COMMENT '1系统字典，2业务字典',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '样式',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`dicTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_data_dic_type
-- ----------------------------
INSERT INTO `t_data_dic_type` VALUES ('1', 'STATUS_TYPE', '状态类型', '1', 'icon-data-dic', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:51:56', '2015-06-15 19:53:35');
INSERT INTO `t_data_dic_type` VALUES ('2', 'QUERY_STATUS_TYPE', '查询表单的状态', '1', 'icon-search', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:52:11', '2015-06-15 19:53:45');
INSERT INTO `t_data_dic_type` VALUES ('3', 'SEX_TYPE', '性别', '2', 'icon-workbench', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:52:35', '2015-06-15 19:53:53');
INSERT INTO `t_data_dic_type` VALUES ('4', 'EMAIL_SWITCH_TYPE', '邮件开关', '1', 'icon-button', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:52:56', '2015-06-15 19:54:23');
INSERT INTO `t_data_dic_type` VALUES ('5', 'SMS_SWITCH_TYPE', '短信开关', '1', 'icon-button', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:53:06', '2015-06-15 19:54:16');
INSERT INTO `t_data_dic_type` VALUES ('6', 'NOTICE_TYPES_TYPE', '公告类型', '1', 'icon-notice', '1', '37', '小杂碎', '37', '小杂碎', '2015-06-15 19:53:19', '2015-06-15 19:54:29');
INSERT INTO `t_data_dic_type` VALUES ('7', 'QUERY_IS_SUPER_TYPE', '查询表单的超级管理员', '1', 'icon-account', '1', '37', '小杂碎', null, null, '2015-06-16 10:54:59', null);
INSERT INTO `t_data_dic_type` VALUES ('8', 'IS_SUPER_TYPE', '超级管理员', '1', 'icon-account', '1', '37', '小杂碎', null, null, '2015-06-16 10:56:00', null);
INSERT INTO `t_data_dic_type` VALUES ('9', 'BUTTON_LAYOUT_MARK_TYPE', '按钮布局标识', '1', 'icon-button', '1', '37', '小杂碎', null, null, '2015-06-16 12:26:59', null);
INSERT INTO `t_data_dic_type` VALUES ('10', 'BUTTON_CATEGORY_TYPE', '按钮类别', '1', 'icon-button', '1', '37', '小杂碎', null, null, '2015-06-16 12:27:12', null);
INSERT INTO `t_data_dic_type` VALUES ('11', 'INDIVIDUATION_MENU_TYPE', '个性化菜单', '1', 'icon-individuation', '1', '1', 'admin', null, null, '2015-06-26 21:07:03', null);
INSERT INTO `t_data_dic_type` VALUES ('12', 'INDIVIDUATION_THEME_TYPE', '个性化主题', '1', 'icon-individuation', '1', '1', 'admin', null, null, '2015-06-27 16:24:06', null);
INSERT INTO `t_data_dic_type` VALUES ('13', 'TASK_STATUS_TYPE', '待办状态', '1', 'icon-tip', '1', '1', 'admin', '1', 'admin', '2015-07-05 20:50:07', '2015-07-05 20:50:23');
INSERT INTO `t_data_dic_type` VALUES ('14', 'DIC_CATEGORY_TYPE', '数据字典类别', '1', 'icon-category', '1', '1', '小咖', null, null, '2015-11-18 21:17:46', null);
INSERT INTO `t_data_dic_type` VALUES ('15', 'dd', 'dd', '2', 'icon-login', '1', '1', '小咖', '1', '小咖', '2015-11-18 21:59:37', '2015-11-18 22:14:30');
INSERT INTO `t_data_dic_type` VALUES ('16', 'QUERY_S_TYPE', '查询表单的状态', '1', 'icon-search', '1', '1', '卡卡', null, null, '2016-04-02 15:18:08', null);
INSERT INTO `t_data_dic_type` VALUES ('17', 'S_TYPE', '状态类型', '1', 'icon-data-dic', '1', '1', '卡卡', null, null, '2016-04-02 15:23:35', null);

-- ----------------------------
-- Table structure for `t_dept`
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept` (
  `deptId` int(20) NOT NULL AUTO_INCREMENT COMMENT '组织id',
  `deptCode` char(30) DEFAULT NULL COMMENT '组织编号',
  `deptName` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `deptSortName` varchar(50) DEFAULT NULL COMMENT '组织简称',
  `deptLevel` int(11) DEFAULT NULL COMMENT '组织级别',
  `deptDescript` varchar(1000) DEFAULT NULL COMMENT '组织描述',
  `parentDeptId` int(20) DEFAULT NULL COMMENT '上一级组织',
  `deptLeaderId` int(20) DEFAULT NULL COMMENT '组织负责人Id',
  `deptTel` varchar(20) DEFAULT NULL COMMENT '组织联系电话',
  `deptAddr` varchar(400) DEFAULT NULL COMMENT '组织地址',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '图标',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `emailSwitch` char(1) DEFAULT NULL COMMENT '邮件开关(1开启，2关闭)',
  `smsSwitch` char(1) DEFAULT NULL COMMENT '短信开关(1开启，2关闭)',
  PRIMARY KEY (`deptId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES ('1', '001', '杂碎总公司', '杂碎总公司', '1', '杂碎总公司', '-1', '1', null, null, 'icon-dept', '1', null, null, null, null, null, null, null, null);
INSERT INTO `t_dept` VALUES ('2', '002', '子公司', '', '1', '', '1', '1', '', '', 'icon-dept', '1', '1', '百变小咖', '1', '卡卡', '2015-07-30 11:47:42', '2016-12-22 19:20:37', '1', '1');

-- ----------------------------
-- Table structure for `t_file_data`
-- ----------------------------
DROP TABLE IF EXISTS `t_file_data`;
CREATE TABLE `t_file_data` (
  `fileId` int(20) NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `busniessMark` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `busniessId` varchar(30) DEFAULT NULL COMMENT '业务id',
  `fileName` varchar(100) DEFAULT NULL COMMENT '文件名',
  `fileUrl` varchar(300) DEFAULT NULL COMMENT '文件存储路径',
  `fileDescript` varchar(200) DEFAULT NULL COMMENT '文件描述',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`fileId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_file_data
-- ----------------------------
INSERT INTO `t_file_data` VALUES ('1', 'USER_HEAD_IMG', '1', 'd8b5df42-e74e-4a28-b1ce-3fdc5db26cd2.jpg', 'http://localhost:8080/cjhme/images/d8b5df42-e74e-4a28-b1ce-3fdc5db26cd2.jpg', '根据id修改用户时的用户头像！', '1', null, null, '1', '卡卡', '2015-07-14 16:57:16', '2016-12-22 19:27:35');

-- ----------------------------
-- Table structure for `t_login_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_login_log`;
CREATE TABLE `t_login_log` (
  `loginId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `loginIP` varchar(60) DEFAULT NULL COMMENT '登录ip',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `createUserId` int(20) NOT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=366 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_login_log
-- ----------------------------
INSERT INTO `t_login_log` VALUES ('1', '127.0.0.1', '1', '1', 'admin', '2015-07-02 14:51:04');
INSERT INTO `t_login_log` VALUES ('2', '127.0.0.1', '1', '1', 'admin', '2015-07-02 17:32:52');
INSERT INTO `t_login_log` VALUES ('3', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:24:42');
INSERT INTO `t_login_log` VALUES ('4', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:46:50');
INSERT INTO `t_login_log` VALUES ('5', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:48:51');
INSERT INTO `t_login_log` VALUES ('6', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:49:20');
INSERT INTO `t_login_log` VALUES ('7', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:51:37');
INSERT INTO `t_login_log` VALUES ('8', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:51:55');
INSERT INTO `t_login_log` VALUES ('9', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:52:27');
INSERT INTO `t_login_log` VALUES ('10', '127.0.0.1', '1', '1', 'admin', '2015-07-02 20:52:55');
INSERT INTO `t_login_log` VALUES ('11', '127.0.0.1', '1', '1', 'admin', '2015-07-03 14:50:40');
INSERT INTO `t_login_log` VALUES ('12', '127.0.0.1', '1', '1', 'admin', '2015-07-05 20:42:07');
INSERT INTO `t_login_log` VALUES ('13', '127.0.0.1', '1', '1', 'admin', '2015-07-06 10:41:23');
INSERT INTO `t_login_log` VALUES ('14', '127.0.0.1', '1', '1', 'admin', '2015-07-06 18:04:10');
INSERT INTO `t_login_log` VALUES ('15', '127.0.0.1', '1', '1', 'admin', '2015-07-14 12:41:10');
INSERT INTO `t_login_log` VALUES ('16', '127.0.0.1', '1', '1', 'admin', '2015-07-14 13:26:38');
INSERT INTO `t_login_log` VALUES ('17', '127.0.0.1', '1', '1', 'admin', '2015-07-14 14:23:59');
INSERT INTO `t_login_log` VALUES ('18', '127.0.0.1', '1', '1', 'admin', '2015-07-14 16:55:41');
INSERT INTO `t_login_log` VALUES ('19', '127.0.0.1', '1', '1', 'admin', '2015-07-14 17:04:53');
INSERT INTO `t_login_log` VALUES ('20', '127.0.0.1', '1', '1', 'admin', '2015-07-14 17:16:09');
INSERT INTO `t_login_log` VALUES ('21', '127.0.0.1', '1', '1', 'admin', '2015-07-14 17:31:32');
INSERT INTO `t_login_log` VALUES ('22', '127.0.0.1', '1', '1', 'admin', '2015-07-14 17:40:15');
INSERT INTO `t_login_log` VALUES ('23', '127.0.0.1', '1', '1', 'admin', '2015-07-20 15:58:15');
INSERT INTO `t_login_log` VALUES ('24', '127.0.0.1', '1', '1', 'admin', '2015-07-22 16:08:32');
INSERT INTO `t_login_log` VALUES ('25', '127.0.0.1', '1', '1', 'admin', '2015-07-22 16:41:41');
INSERT INTO `t_login_log` VALUES ('26', '127.0.0.1', '1', '1', 'admin', '2015-07-22 16:55:58');
INSERT INTO `t_login_log` VALUES ('27', '127.0.0.1', '1', '1', 'admin', '2015-07-22 17:00:39');
INSERT INTO `t_login_log` VALUES ('28', '127.0.0.1', '1', '1', 'admin', '2015-07-22 17:19:03');
INSERT INTO `t_login_log` VALUES ('29', '127.0.0.1', '1', '1', '百变小咖', '2015-07-29 16:50:46');
INSERT INTO `t_login_log` VALUES ('30', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:47:12');
INSERT INTO `t_login_log` VALUES ('31', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:48:15');
INSERT INTO `t_login_log` VALUES ('32', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:53:42');
INSERT INTO `t_login_log` VALUES ('33', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:53:56');
INSERT INTO `t_login_log` VALUES ('34', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:55:31');
INSERT INTO `t_login_log` VALUES ('35', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 11:55:59');
INSERT INTO `t_login_log` VALUES ('36', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 12:45:17');
INSERT INTO `t_login_log` VALUES ('37', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 12:45:30');
INSERT INTO `t_login_log` VALUES ('38', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 12:49:46');
INSERT INTO `t_login_log` VALUES ('39', '127.0.0.1', '1', '1', '百变小咖', '2015-07-30 14:11:05');
INSERT INTO `t_login_log` VALUES ('40', '127.0.0.1', '1', '1', '百变小咖', '2015-07-31 16:07:04');
INSERT INTO `t_login_log` VALUES ('41', '127.0.0.1', '1', '1', '百变小咖', '2015-08-04 17:09:04');
INSERT INTO `t_login_log` VALUES ('42', '192.168.1.117', '1', '1', '百变小咖', '2015-08-04 17:10:35');
INSERT INTO `t_login_log` VALUES ('43', '127.0.0.1', '1', '1', '百变小咖', '2015-08-04 17:10:56');
INSERT INTO `t_login_log` VALUES ('44', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 14:30:55');
INSERT INTO `t_login_log` VALUES ('45', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 14:30:57');
INSERT INTO `t_login_log` VALUES ('46', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 14:31:02');
INSERT INTO `t_login_log` VALUES ('47', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 14:31:05');
INSERT INTO `t_login_log` VALUES ('48', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 14:31:16');
INSERT INTO `t_login_log` VALUES ('49', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 15:02:19');
INSERT INTO `t_login_log` VALUES ('50', '192.168.1.117', '1', '1', '百变小咖', '2015-08-05 15:20:02');
INSERT INTO `t_login_log` VALUES ('51', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 15:50:39');
INSERT INTO `t_login_log` VALUES ('52', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 16:48:41');
INSERT INTO `t_login_log` VALUES ('53', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 16:48:45');
INSERT INTO `t_login_log` VALUES ('54', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 17:40:39');
INSERT INTO `t_login_log` VALUES ('55', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 18:03:37');
INSERT INTO `t_login_log` VALUES ('56', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 18:03:41');
INSERT INTO `t_login_log` VALUES ('57', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 18:06:19');
INSERT INTO `t_login_log` VALUES ('58', '127.0.0.1', '1', '1', '百变小咖', '2015-08-05 18:06:26');
INSERT INTO `t_login_log` VALUES ('59', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 10:00:23');
INSERT INTO `t_login_log` VALUES ('60', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 10:03:05');
INSERT INTO `t_login_log` VALUES ('61', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 10:04:54');
INSERT INTO `t_login_log` VALUES ('62', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 10:05:11');
INSERT INTO `t_login_log` VALUES ('63', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 10:45:19');
INSERT INTO `t_login_log` VALUES ('64', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 11:29:02');
INSERT INTO `t_login_log` VALUES ('65', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 14:14:16');
INSERT INTO `t_login_log` VALUES ('66', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 15:08:38');
INSERT INTO `t_login_log` VALUES ('67', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 15:11:32');
INSERT INTO `t_login_log` VALUES ('68', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 16:25:09');
INSERT INTO `t_login_log` VALUES ('69', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 17:10:33');
INSERT INTO `t_login_log` VALUES ('70', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 17:11:53');
INSERT INTO `t_login_log` VALUES ('71', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 17:12:01');
INSERT INTO `t_login_log` VALUES ('72', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 17:16:31');
INSERT INTO `t_login_log` VALUES ('73', '127.0.0.1', '1', '1', '百变小咖', '2015-08-06 17:37:24');
INSERT INTO `t_login_log` VALUES ('74', '127.0.0.1', '1', '1', '百变小咖', '2015-08-07 09:43:43');
INSERT INTO `t_login_log` VALUES ('75', '127.0.0.1', '1', '1', '百变小咖', '2015-08-07 09:47:09');
INSERT INTO `t_login_log` VALUES ('76', '127.0.0.1', '1', '1', '百变小咖', '2015-08-07 09:50:50');
INSERT INTO `t_login_log` VALUES ('77', '127.0.0.1', '1', '1', '百变小咖', '2015-08-07 09:51:26');
INSERT INTO `t_login_log` VALUES ('78', '127.0.0.1', '1', '1', '百变小咖', '2015-08-07 09:53:23');
INSERT INTO `t_login_log` VALUES ('79', '127.0.0.1', '1', '1', '小咖', '2015-08-07 10:59:19');
INSERT INTO `t_login_log` VALUES ('80', '127.0.0.1', '1', '1', '小咖', '2015-08-07 11:04:07');
INSERT INTO `t_login_log` VALUES ('81', '127.0.0.1', '1', '1', '小咖', '2015-08-07 15:43:31');
INSERT INTO `t_login_log` VALUES ('82', '127.0.0.1', '1', '1', '小咖', '2015-08-07 16:45:45');
INSERT INTO `t_login_log` VALUES ('83', '127.0.0.1', '1', '1', '小咖', '2015-08-27 19:53:28');
INSERT INTO `t_login_log` VALUES ('84', '127.0.0.1', '1', '1', '小咖', '2015-08-27 19:53:55');
INSERT INTO `t_login_log` VALUES ('85', '127.0.0.1', '1', '1', '小咖', '2015-08-29 16:09:46');
INSERT INTO `t_login_log` VALUES ('86', '127.0.0.1', '1', '1', '小咖', '2015-08-29 16:55:08');
INSERT INTO `t_login_log` VALUES ('87', '127.0.0.1', '1', '1', '小咖', '2015-08-29 20:50:37');
INSERT INTO `t_login_log` VALUES ('88', '127.0.0.1', '1', '1', '小咖', '2015-08-30 08:55:59');
INSERT INTO `t_login_log` VALUES ('89', '127.0.0.1', '1', '1', '小咖', '2015-08-30 09:11:42');
INSERT INTO `t_login_log` VALUES ('90', '127.0.0.1', '1', '1', '小咖', '2015-08-30 09:38:28');
INSERT INTO `t_login_log` VALUES ('91', '127.0.0.1', '1', '1', '小咖', '2015-08-30 09:42:18');
INSERT INTO `t_login_log` VALUES ('92', '127.0.0.1', '1', '1', '小咖', '2015-08-30 09:51:35');
INSERT INTO `t_login_log` VALUES ('93', '127.0.0.1', '1', '1', '小咖', '2015-08-30 10:28:29');
INSERT INTO `t_login_log` VALUES ('94', '127.0.0.1', '1', '1', '小咖', '2015-08-30 13:44:51');
INSERT INTO `t_login_log` VALUES ('95', '127.0.0.1', '1', '1', '小咖', '2015-08-30 14:06:16');
INSERT INTO `t_login_log` VALUES ('96', '127.0.0.1', '1', '1', '小咖', '2015-08-30 15:17:06');
INSERT INTO `t_login_log` VALUES ('97', '127.0.0.1', '1', '1', '小咖', '2015-08-30 15:27:01');
INSERT INTO `t_login_log` VALUES ('98', '127.0.0.1', '1', '1', '小咖', '2015-08-30 18:49:16');
INSERT INTO `t_login_log` VALUES ('99', '127.0.0.1', '1', '1', '小咖', '2015-08-30 19:40:08');
INSERT INTO `t_login_log` VALUES ('100', '127.0.0.1', '1', '1', '小咖', '2015-08-30 19:41:46');
INSERT INTO `t_login_log` VALUES ('101', '127.0.0.1', '1', '1', '小咖', '2015-08-30 19:55:30');
INSERT INTO `t_login_log` VALUES ('102', '127.0.0.1', '1', '1', '小咖', '2015-09-19 20:09:25');
INSERT INTO `t_login_log` VALUES ('103', '127.0.0.1', '1', '1', '小咖', '2015-09-20 19:47:17');
INSERT INTO `t_login_log` VALUES ('104', '127.0.0.1', '1', '1', '小咖', '2015-09-20 19:58:39');
INSERT INTO `t_login_log` VALUES ('105', '127.0.0.1', '1', '1', '小咖', '2015-09-23 20:28:34');
INSERT INTO `t_login_log` VALUES ('106', '127.0.0.1', '1', '1', '小咖', '2015-09-23 20:29:26');
INSERT INTO `t_login_log` VALUES ('107', '127.0.0.1', '1', '1', '小咖', '2015-09-23 20:43:15');
INSERT INTO `t_login_log` VALUES ('108', '127.0.0.1', '1', '1', '小咖', '2015-09-23 20:53:37');
INSERT INTO `t_login_log` VALUES ('109', '127.0.0.1', '1', '1', '小咖', '2015-09-25 22:16:26');
INSERT INTO `t_login_log` VALUES ('110', '127.0.0.1', '1', '1', '小咖', '2015-09-25 22:22:03');
INSERT INTO `t_login_log` VALUES ('111', '127.0.0.1', '1', '1', '小咖', '2015-09-26 15:17:46');
INSERT INTO `t_login_log` VALUES ('112', '127.0.0.1', '1', '1', '小咖', '2015-09-26 15:26:00');
INSERT INTO `t_login_log` VALUES ('113', '127.0.0.1', '1', '1', '小咖', '2015-09-27 07:51:58');
INSERT INTO `t_login_log` VALUES ('114', '127.0.0.1', '1', '1', '小咖', '2015-09-27 08:20:35');
INSERT INTO `t_login_log` VALUES ('115', '127.0.0.1', '1', '1', '小咖', '2015-09-27 09:16:22');
INSERT INTO `t_login_log` VALUES ('116', '127.0.0.1', '1', '1', '小咖', '2015-09-27 09:18:25');
INSERT INTO `t_login_log` VALUES ('117', '127.0.0.1', '1', '1', '小咖', '2015-09-27 10:24:24');
INSERT INTO `t_login_log` VALUES ('118', '127.0.0.1', '1', '1', '小咖', '2015-09-27 11:12:19');
INSERT INTO `t_login_log` VALUES ('119', '127.0.0.1', '1', '1', '小咖', '2015-09-27 11:52:31');
INSERT INTO `t_login_log` VALUES ('120', '127.0.0.1', '1', '1', '小咖', '2015-09-27 12:10:04');
INSERT INTO `t_login_log` VALUES ('121', '127.0.0.1', '1', '1', '小咖', '2015-09-27 14:27:53');
INSERT INTO `t_login_log` VALUES ('122', '127.0.0.1', '1', '1', '小咖', '2015-09-27 15:01:33');
INSERT INTO `t_login_log` VALUES ('123', '127.0.0.1', '1', '1', '小咖', '2015-09-27 19:03:30');
INSERT INTO `t_login_log` VALUES ('124', '127.0.0.1', '1', '1', '小咖', '2015-10-02 20:22:24');
INSERT INTO `t_login_log` VALUES ('125', '127.0.0.1', '1', '1', '小咖', '2015-10-17 13:28:46');
INSERT INTO `t_login_log` VALUES ('126', '127.0.0.1', '1', '1', '小咖', '2015-10-17 13:29:13');
INSERT INTO `t_login_log` VALUES ('127', '127.0.0.1', '1', '1', '小咖', '2015-10-17 14:49:00');
INSERT INTO `t_login_log` VALUES ('128', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:16:15');
INSERT INTO `t_login_log` VALUES ('129', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:36:32');
INSERT INTO `t_login_log` VALUES ('130', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:39:03');
INSERT INTO `t_login_log` VALUES ('131', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:41:15');
INSERT INTO `t_login_log` VALUES ('132', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:53:27');
INSERT INTO `t_login_log` VALUES ('133', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:54:12');
INSERT INTO `t_login_log` VALUES ('134', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:57:11');
INSERT INTO `t_login_log` VALUES ('135', '127.0.0.1', '1', '1', '小咖', '2015-10-17 18:58:23');
INSERT INTO `t_login_log` VALUES ('136', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:01:59');
INSERT INTO `t_login_log` VALUES ('137', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:02:30');
INSERT INTO `t_login_log` VALUES ('138', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:05:38');
INSERT INTO `t_login_log` VALUES ('139', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:05:57');
INSERT INTO `t_login_log` VALUES ('140', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:06:34');
INSERT INTO `t_login_log` VALUES ('141', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:13:47');
INSERT INTO `t_login_log` VALUES ('142', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:21:08');
INSERT INTO `t_login_log` VALUES ('143', '127.0.0.1', '1', '1', '小咖', '2015-10-17 19:36:08');
INSERT INTO `t_login_log` VALUES ('144', '127.0.0.1', '1', '1', '小咖', '2015-11-14 23:00:11');
INSERT INTO `t_login_log` VALUES ('145', '127.0.0.1', '1', '1', '小咖', '2015-11-18 20:29:53');
INSERT INTO `t_login_log` VALUES ('146', '127.0.0.1', '1', '1', '小咖', '2015-11-18 20:30:05');
INSERT INTO `t_login_log` VALUES ('147', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:05:17');
INSERT INTO `t_login_log` VALUES ('148', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:24:30');
INSERT INTO `t_login_log` VALUES ('149', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:28:21');
INSERT INTO `t_login_log` VALUES ('150', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:28:54');
INSERT INTO `t_login_log` VALUES ('151', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:30:19');
INSERT INTO `t_login_log` VALUES ('152', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:31:04');
INSERT INTO `t_login_log` VALUES ('153', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:40:42');
INSERT INTO `t_login_log` VALUES ('154', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:45:47');
INSERT INTO `t_login_log` VALUES ('155', '127.0.0.1', '1', '1', '小咖', '2015-11-18 21:59:13');
INSERT INTO `t_login_log` VALUES ('156', '127.0.0.1', '1', '1', '小咖', '2015-11-18 22:06:08');
INSERT INTO `t_login_log` VALUES ('157', '127.0.0.1', '1', '1', '小咖', '2015-11-18 22:11:24');
INSERT INTO `t_login_log` VALUES ('158', '127.0.0.1', '1', '1', '小咖', '2015-11-21 11:58:05');
INSERT INTO `t_login_log` VALUES ('159', '127.0.0.1', '1', '1', '小咖', '2015-11-21 12:01:39');
INSERT INTO `t_login_log` VALUES ('160', '127.0.0.1', '1', '1', '小咖', '2015-11-21 15:26:11');
INSERT INTO `t_login_log` VALUES ('161', '127.0.0.1', '1', '1', '小咖', '2015-11-21 15:26:57');
INSERT INTO `t_login_log` VALUES ('162', '127.0.0.1', '1', '1', '小咖', '2015-11-22 09:54:06');
INSERT INTO `t_login_log` VALUES ('163', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:03:32');
INSERT INTO `t_login_log` VALUES ('164', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:12:43');
INSERT INTO `t_login_log` VALUES ('165', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:12:54');
INSERT INTO `t_login_log` VALUES ('166', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:13:13');
INSERT INTO `t_login_log` VALUES ('167', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:50:55');
INSERT INTO `t_login_log` VALUES ('168', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:52:15');
INSERT INTO `t_login_log` VALUES ('169', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:52:41');
INSERT INTO `t_login_log` VALUES ('170', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:52:56');
INSERT INTO `t_login_log` VALUES ('171', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:53:13');
INSERT INTO `t_login_log` VALUES ('172', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:53:51');
INSERT INTO `t_login_log` VALUES ('173', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:54:00');
INSERT INTO `t_login_log` VALUES ('174', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:54:31');
INSERT INTO `t_login_log` VALUES ('175', '127.0.0.1', '1', '1', '小咖', '2015-11-22 10:54:50');
INSERT INTO `t_login_log` VALUES ('176', '127.0.0.1', '1', '1', '小咖', '2015-11-25 20:11:19');
INSERT INTO `t_login_log` VALUES ('177', '127.0.0.1', '1', '1', '小咖', '2015-11-25 20:17:00');
INSERT INTO `t_login_log` VALUES ('178', '127.0.0.1', '1', '1', '小咖', '2015-11-25 20:20:09');
INSERT INTO `t_login_log` VALUES ('179', '127.0.0.1', '1', '1', '小咖', '2015-11-25 20:20:39');
INSERT INTO `t_login_log` VALUES ('180', '127.0.0.1', '1', '1', '小咖', '2015-11-25 20:21:09');
INSERT INTO `t_login_log` VALUES ('181', '127.0.0.1', '1', '1', '小咖', '2015-11-25 21:13:31');
INSERT INTO `t_login_log` VALUES ('182', '127.0.0.1', '1', '1', '小咖', '2015-11-25 22:30:59');
INSERT INTO `t_login_log` VALUES ('183', '127.0.0.1', '1', '1', '小咖', '2015-11-25 22:32:58');
INSERT INTO `t_login_log` VALUES ('184', '127.0.0.1', '1', '1', '小咖', '2015-11-29 15:29:19');
INSERT INTO `t_login_log` VALUES ('185', '127.0.0.1', '1', '1', '小咖', '2015-11-29 15:36:14');
INSERT INTO `t_login_log` VALUES ('186', '127.0.0.1', '1', '1', '小咖', '2015-11-29 16:32:46');
INSERT INTO `t_login_log` VALUES ('187', '127.0.0.1', '1', '1', '小咖', '2015-11-30 21:06:32');
INSERT INTO `t_login_log` VALUES ('188', '127.0.0.1', '1', '1', '小咖', '2015-11-30 21:46:49');
INSERT INTO `t_login_log` VALUES ('189', '127.0.0.1', '1', '1', '小咖', '2015-11-30 21:56:50');
INSERT INTO `t_login_log` VALUES ('190', '127.0.0.1', '1', '1', '小咖', '2015-12-03 18:37:43');
INSERT INTO `t_login_log` VALUES ('191', '127.0.0.1', '1', '1', '小咖', '2015-12-03 18:43:49');
INSERT INTO `t_login_log` VALUES ('192', '127.0.0.1', '1', '1', '小咖', '2015-12-03 18:53:37');
INSERT INTO `t_login_log` VALUES ('193', '127.0.0.1', '1', '1', '小咖', '2015-12-05 12:35:14');
INSERT INTO `t_login_log` VALUES ('194', '127.0.0.1', '1', '1', '小咖', '2015-12-05 12:39:33');
INSERT INTO `t_login_log` VALUES ('195', '127.0.0.1', '1', '1', '小咖', '2015-12-05 20:43:54');
INSERT INTO `t_login_log` VALUES ('196', '127.0.0.1', '1', '1', '小咖', '2015-12-05 21:25:17');
INSERT INTO `t_login_log` VALUES ('197', '127.0.0.1', '1', '1', '小咖', '2015-12-06 11:53:39');
INSERT INTO `t_login_log` VALUES ('198', '127.0.0.1', '1', '1', '小咖', '2015-12-06 11:56:47');
INSERT INTO `t_login_log` VALUES ('199', '127.0.0.1', '1', '1', '小咖', '2015-12-06 12:28:49');
INSERT INTO `t_login_log` VALUES ('200', '127.0.0.1', '1', '1', '小咖', '2015-12-06 12:32:05');
INSERT INTO `t_login_log` VALUES ('201', '127.0.0.1', '1', '1', '小咖', '2015-12-06 12:39:30');
INSERT INTO `t_login_log` VALUES ('202', '127.0.0.1', '1', '1', '小咖', '2015-12-06 12:40:07');
INSERT INTO `t_login_log` VALUES ('203', '127.0.0.1', '1', '1', '小咖', '2015-12-06 19:45:47');
INSERT INTO `t_login_log` VALUES ('204', '127.0.0.1', '1', '1', '小咖', '2015-12-07 20:40:28');
INSERT INTO `t_login_log` VALUES ('205', '127.0.0.1', '1', '1', '??', '2015-12-07 21:22:00');
INSERT INTO `t_login_log` VALUES ('206', '127.0.0.1', '1', '1', '??', '2015-12-07 21:23:27');
INSERT INTO `t_login_log` VALUES ('207', '127.0.0.1', '1', '1', '??', '2015-12-08 19:23:35');
INSERT INTO `t_login_log` VALUES ('208', '127.0.0.1', '1', '1', '??', '2015-12-08 19:34:26');
INSERT INTO `t_login_log` VALUES ('209', '127.0.0.1', '1', '1', '哎呀', '2015-12-08 19:36:36');
INSERT INTO `t_login_log` VALUES ('210', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 19:49:32');
INSERT INTO `t_login_log` VALUES ('211', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:00:22');
INSERT INTO `t_login_log` VALUES ('212', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:16:55');
INSERT INTO `t_login_log` VALUES ('213', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:18:20');
INSERT INTO `t_login_log` VALUES ('214', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:19:59');
INSERT INTO `t_login_log` VALUES ('215', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:21:37');
INSERT INTO `t_login_log` VALUES ('216', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:22:55');
INSERT INTO `t_login_log` VALUES ('217', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:25:20');
INSERT INTO `t_login_log` VALUES ('218', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:27:25');
INSERT INTO `t_login_log` VALUES ('219', '127.0.0.1', '1', '1', '卡卡', '2015-12-10 20:35:14');
INSERT INTO `t_login_log` VALUES ('220', '127.0.0.1', '1', '1', '卡卡', '2015-12-11 19:43:40');
INSERT INTO `t_login_log` VALUES ('221', '127.0.0.1', '1', '1', '卡卡', '2015-12-11 19:46:03');
INSERT INTO `t_login_log` VALUES ('222', '127.0.0.1', '1', '1', '卡卡', '2015-12-11 20:09:08');
INSERT INTO `t_login_log` VALUES ('223', '127.0.0.1', '1', '1', '卡卡', '2015-12-11 20:09:24');
INSERT INTO `t_login_log` VALUES ('224', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 16:36:23');
INSERT INTO `t_login_log` VALUES ('225', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 16:50:16');
INSERT INTO `t_login_log` VALUES ('226', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 17:17:51');
INSERT INTO `t_login_log` VALUES ('227', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 17:21:29');
INSERT INTO `t_login_log` VALUES ('228', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 17:22:42');
INSERT INTO `t_login_log` VALUES ('229', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 17:25:15');
INSERT INTO `t_login_log` VALUES ('230', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 18:41:22');
INSERT INTO `t_login_log` VALUES ('231', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 18:44:59');
INSERT INTO `t_login_log` VALUES ('232', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 19:27:37');
INSERT INTO `t_login_log` VALUES ('233', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 19:46:17');
INSERT INTO `t_login_log` VALUES ('234', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 19:51:02');
INSERT INTO `t_login_log` VALUES ('235', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 19:54:16');
INSERT INTO `t_login_log` VALUES ('236', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 20:01:16');
INSERT INTO `t_login_log` VALUES ('237', '127.0.0.1', '1', '1', '卡卡', '2015-12-12 20:09:24');
INSERT INTO `t_login_log` VALUES ('238', '127.0.0.1', '1', '1', '卡卡', '2015-12-13 12:53:41');
INSERT INTO `t_login_log` VALUES ('239', '127.0.0.1', '1', '1', '卡卡', '2015-12-13 12:58:14');
INSERT INTO `t_login_log` VALUES ('240', '127.0.0.1', '1', '1', '卡卡', '2015-12-20 14:36:00');
INSERT INTO `t_login_log` VALUES ('241', '127.0.0.1', '1', '1', '卡卡', '2015-12-20 14:53:19');
INSERT INTO `t_login_log` VALUES ('242', '127.0.0.1', '1', '1', '卡卡', '2015-12-20 15:20:53');
INSERT INTO `t_login_log` VALUES ('243', '127.0.0.1', '1', '1', '卡卡', '2015-12-20 15:33:37');
INSERT INTO `t_login_log` VALUES ('244', '127.0.0.1', '1', '1', '卡卡', '2015-12-20 15:33:56');
INSERT INTO `t_login_log` VALUES ('245', '127.0.0.1', '1', '1', '卡卡', '2015-12-27 08:45:38');
INSERT INTO `t_login_log` VALUES ('246', '127.0.0.1', '1', '1', '卡卡', '2015-12-27 08:55:25');
INSERT INTO `t_login_log` VALUES ('247', '127.0.0.1', '1', '1', '卡卡', '2015-12-27 09:29:54');
INSERT INTO `t_login_log` VALUES ('248', '127.0.0.1', '1', '1', '卡卡', '2015-12-27 09:30:10');
INSERT INTO `t_login_log` VALUES ('249', '127.0.0.1', '1', '1', '卡卡', '2015-12-27 09:52:22');
INSERT INTO `t_login_log` VALUES ('250', '127.0.0.1', '1', '1', '卡卡', '2016-01-05 13:02:17');
INSERT INTO `t_login_log` VALUES ('251', '127.0.0.1', '1', '1', '卡卡', '2016-01-05 14:16:59');
INSERT INTO `t_login_log` VALUES ('252', '127.0.0.1', '1', '1', '卡卡', '2016-01-05 14:50:28');
INSERT INTO `t_login_log` VALUES ('253', '127.0.0.1', '1', '1', '卡卡', '2016-01-07 19:07:32');
INSERT INTO `t_login_log` VALUES ('254', '127.0.0.1', '1', '1', '卡卡', '2016-01-07 19:08:01');
INSERT INTO `t_login_log` VALUES ('255', '127.0.0.1', '1', '1', '卡卡', '2016-01-07 19:10:48');
INSERT INTO `t_login_log` VALUES ('256', '127.0.0.1', '1', '1', '卡卡', '2016-01-07 19:11:32');
INSERT INTO `t_login_log` VALUES ('257', '127.0.0.1', '1', '1', '卡卡', '2016-01-09 10:50:56');
INSERT INTO `t_login_log` VALUES ('258', '127.0.0.1', '1', '1', '卡卡', '2016-01-09 11:04:24');
INSERT INTO `t_login_log` VALUES ('259', '0:0:0:0:0:0:0:1', '1', '1', '卡卡', '2016-01-09 11:07:57');
INSERT INTO `t_login_log` VALUES ('260', '127.0.0.1', '1', '1', '卡卡', '2016-01-09 15:16:32');
INSERT INTO `t_login_log` VALUES ('261', '127.0.0.1', '1', '1', '卡卡', '2016-01-09 20:30:13');
INSERT INTO `t_login_log` VALUES ('262', '127.0.0.1', '1', '1', '卡卡', '2016-01-28 19:47:03');
INSERT INTO `t_login_log` VALUES ('263', '127.0.0.1', '1', '1', '卡卡', '2016-01-30 11:30:50');
INSERT INTO `t_login_log` VALUES ('264', '127.0.0.1', '1', '1', '卡卡', '2016-01-30 13:30:54');
INSERT INTO `t_login_log` VALUES ('265', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 16:55:07');
INSERT INTO `t_login_log` VALUES ('266', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 17:47:39');
INSERT INTO `t_login_log` VALUES ('267', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 18:36:50');
INSERT INTO `t_login_log` VALUES ('268', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 18:39:43');
INSERT INTO `t_login_log` VALUES ('269', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 20:42:37');
INSERT INTO `t_login_log` VALUES ('270', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 20:43:34');
INSERT INTO `t_login_log` VALUES ('271', '127.0.0.1', '1', '1', '卡卡', '2016-01-31 20:54:27');
INSERT INTO `t_login_log` VALUES ('272', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 14:54:36');
INSERT INTO `t_login_log` VALUES ('273', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 15:06:36');
INSERT INTO `t_login_log` VALUES ('274', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 15:23:22');
INSERT INTO `t_login_log` VALUES ('275', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 15:39:14');
INSERT INTO `t_login_log` VALUES ('276', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 16:26:35');
INSERT INTO `t_login_log` VALUES ('277', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 16:38:06');
INSERT INTO `t_login_log` VALUES ('278', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 16:39:22');
INSERT INTO `t_login_log` VALUES ('279', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 18:54:30');
INSERT INTO `t_login_log` VALUES ('280', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 18:55:26');
INSERT INTO `t_login_log` VALUES ('281', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 18:57:28');
INSERT INTO `t_login_log` VALUES ('282', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 18:57:41');
INSERT INTO `t_login_log` VALUES ('283', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 18:57:51');
INSERT INTO `t_login_log` VALUES ('284', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 19:02:29');
INSERT INTO `t_login_log` VALUES ('285', '127.0.0.1', '1', '1', '卡卡', '2016-02-05 19:02:39');
INSERT INTO `t_login_log` VALUES ('286', '127.0.0.1', '1', '1', '卡卡', '2016-02-06 16:03:10');
INSERT INTO `t_login_log` VALUES ('287', '127.0.0.1', '1', '1', '卡卡', '2016-02-06 16:31:25');
INSERT INTO `t_login_log` VALUES ('288', '127.0.0.1', '1', '1', '卡卡', '2016-02-06 16:36:36');
INSERT INTO `t_login_log` VALUES ('289', '127.0.0.1', '1', '1', '卡卡', '2016-02-07 10:06:31');
INSERT INTO `t_login_log` VALUES ('290', '127.0.0.1', '1', '1', '卡卡', '2016-02-07 10:21:49');
INSERT INTO `t_login_log` VALUES ('291', '127.0.0.1', '1', '1', '卡卡', '2016-02-07 10:23:27');
INSERT INTO `t_login_log` VALUES ('292', '127.0.0.1', '1', '1', '卡卡', '2016-02-10 20:34:50');
INSERT INTO `t_login_log` VALUES ('293', '127.0.0.1', '1', '1', '卡卡', '2016-02-10 21:02:30');
INSERT INTO `t_login_log` VALUES ('294', '127.0.0.1', '1', '1', '卡卡', '2016-02-10 21:04:10');
INSERT INTO `t_login_log` VALUES ('295', '127.0.0.1', '1', '1', '卡卡', '2016-02-10 21:25:08');
INSERT INTO `t_login_log` VALUES ('296', '127.0.0.1', '1', '1', '卡卡', '2016-03-23 20:10:32');
INSERT INTO `t_login_log` VALUES ('297', '127.0.0.1', '1', '1', '卡卡', '2016-03-23 20:11:13');
INSERT INTO `t_login_log` VALUES ('298', '127.0.0.1', '1', '1', '卡卡', '2016-03-23 20:11:22');
INSERT INTO `t_login_log` VALUES ('299', '127.0.0.1', '1', '1', '卡卡', '2016-04-01 21:55:06');
INSERT INTO `t_login_log` VALUES ('300', '127.0.0.1', '1', '1', '卡卡', '2016-04-01 22:05:13');
INSERT INTO `t_login_log` VALUES ('301', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 10:44:40');
INSERT INTO `t_login_log` VALUES ('302', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 10:45:23');
INSERT INTO `t_login_log` VALUES ('303', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 13:50:45');
INSERT INTO `t_login_log` VALUES ('304', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 13:55:45');
INSERT INTO `t_login_log` VALUES ('305', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:33:02');
INSERT INTO `t_login_log` VALUES ('306', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:34:17');
INSERT INTO `t_login_log` VALUES ('307', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:34:54');
INSERT INTO `t_login_log` VALUES ('308', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:41:28');
INSERT INTO `t_login_log` VALUES ('309', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:42:30');
INSERT INTO `t_login_log` VALUES ('310', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:43:13');
INSERT INTO `t_login_log` VALUES ('311', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:46:46');
INSERT INTO `t_login_log` VALUES ('312', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:47:27');
INSERT INTO `t_login_log` VALUES ('313', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:51:51');
INSERT INTO `t_login_log` VALUES ('314', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 14:56:41');
INSERT INTO `t_login_log` VALUES ('315', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 15:06:52');
INSERT INTO `t_login_log` VALUES ('316', '127.0.0.1', '1', '1', '卡卡', '2016-04-02 15:22:56');
INSERT INTO `t_login_log` VALUES ('317', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 14:27:36');
INSERT INTO `t_login_log` VALUES ('318', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 14:32:39');
INSERT INTO `t_login_log` VALUES ('319', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 14:36:57');
INSERT INTO `t_login_log` VALUES ('320', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 14:40:38');
INSERT INTO `t_login_log` VALUES ('321', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 14:44:39');
INSERT INTO `t_login_log` VALUES ('322', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 15:43:14');
INSERT INTO `t_login_log` VALUES ('323', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 16:10:51');
INSERT INTO `t_login_log` VALUES ('324', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 16:12:25');
INSERT INTO `t_login_log` VALUES ('325', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 16:27:48');
INSERT INTO `t_login_log` VALUES ('326', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 16:31:35');
INSERT INTO `t_login_log` VALUES ('327', '127.0.0.1', '1', '1', '卡卡', '2016-04-03 16:31:37');
INSERT INTO `t_login_log` VALUES ('328', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 14:39:31');
INSERT INTO `t_login_log` VALUES ('329', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 15:05:12');
INSERT INTO `t_login_log` VALUES ('330', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 15:06:39');
INSERT INTO `t_login_log` VALUES ('331', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 15:15:03');
INSERT INTO `t_login_log` VALUES ('332', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 15:37:19');
INSERT INTO `t_login_log` VALUES ('333', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 15:40:15');
INSERT INTO `t_login_log` VALUES ('334', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 16:00:43');
INSERT INTO `t_login_log` VALUES ('335', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 16:04:29');
INSERT INTO `t_login_log` VALUES ('336', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 16:20:59');
INSERT INTO `t_login_log` VALUES ('337', '127.0.0.1', '1', '1', '卡卡', '2016-04-04 16:21:48');
INSERT INTO `t_login_log` VALUES ('338', '127.0.0.1', '1', '1', '卡卡', '2016-08-24 19:46:43');
INSERT INTO `t_login_log` VALUES ('339', '127.0.0.1', '1', '1', '卡卡', '2016-08-28 09:50:31');
INSERT INTO `t_login_log` VALUES ('340', '127.0.0.1', '1', '1', '卡卡', '2016-08-28 09:57:38');
INSERT INTO `t_login_log` VALUES ('341', '127.0.0.1', '1', '1', '卡卡', '2016-08-30 20:56:31');
INSERT INTO `t_login_log` VALUES ('342', '127.0.0.1', '1', '1', '卡卡', '2016-09-04 15:55:43');
INSERT INTO `t_login_log` VALUES ('343', '127.0.0.1', '1', '1', '卡卡', '2016-09-29 21:59:07');
INSERT INTO `t_login_log` VALUES ('344', '127.0.0.1', '1', '1', '卡卡', '2016-10-02 19:00:58');
INSERT INTO `t_login_log` VALUES ('345', '127.0.0.1', '1', '1', '卡卡', '2016-10-02 19:01:27');
INSERT INTO `t_login_log` VALUES ('346', '127.0.0.1', '1', '1', '卡卡', '2016-10-02 19:19:49');
INSERT INTO `t_login_log` VALUES ('347', '127.0.0.1', '1', '1', '卡卡', '2016-10-02 19:21:36');
INSERT INTO `t_login_log` VALUES ('348', '127.0.0.1', '1', '1', '卡卡', '2016-10-02 19:22:40');
INSERT INTO `t_login_log` VALUES ('349', '127.0.0.1', '1', '1', '卡卡', '2016-10-15 09:40:34');
INSERT INTO `t_login_log` VALUES ('350', '127.0.0.1', '1', '1', '卡卡', '2016-10-15 16:00:02');
INSERT INTO `t_login_log` VALUES ('351', '127.0.0.1', '1', '1', '卡卡', '2016-10-15 18:20:09');
INSERT INTO `t_login_log` VALUES ('352', '127.0.0.1', '1', '1', '卡卡', '2016-10-22 20:46:31');
INSERT INTO `t_login_log` VALUES ('353', '127.0.0.1', '1', '1', '卡卡', '2016-11-17 21:59:37');
INSERT INTO `t_login_log` VALUES ('354', '127.0.0.1', '1', '1', '卡卡', '2016-11-17 21:59:50');
INSERT INTO `t_login_log` VALUES ('355', '127.0.0.1', '1', '1', '卡卡', '2016-11-17 22:00:12');
INSERT INTO `t_login_log` VALUES ('356', '127.0.0.1', '1', '1', '卡卡', '2016-11-17 22:15:13');
INSERT INTO `t_login_log` VALUES ('357', '127.0.0.1', '1', '1', '卡卡', '2016-11-26 22:20:56');
INSERT INTO `t_login_log` VALUES ('358', '127.0.0.1', '1', '1', '卡卡', '2016-11-26 22:21:56');
INSERT INTO `t_login_log` VALUES ('359', '127.0.0.1', '1', '1', '卡卡', '2016-12-08 21:17:33');
INSERT INTO `t_login_log` VALUES ('360', '127.0.0.1', '1', '1', '卡卡', '2016-12-21 20:55:41');
INSERT INTO `t_login_log` VALUES ('361', '127.0.0.1', '1', '1', '卡卡', '2016-12-21 21:03:21');
INSERT INTO `t_login_log` VALUES ('362', '127.0.0.1', '1', '1', '卡卡', '2016-12-22 19:18:07');
INSERT INTO `t_login_log` VALUES ('363', '127.0.0.1', '1', '1', '卡卡', '2016-12-22 19:18:27');
INSERT INTO `t_login_log` VALUES ('364', '127.0.0.1', '1', '1', '卡卡', '2016-12-22 19:22:04');
INSERT INTO `t_login_log` VALUES ('365', '127.0.0.1', '1', '1', '卡卡', '2016-12-22 19:27:31');

-- ----------------------------
-- Table structure for `t_menu`
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `menuId` int(20) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `menuCode` char(30) DEFAULT NULL COMMENT '菜单编号',
  `menuName` varchar(30) DEFAULT NULL COMMENT '名称',
  `iconCls` varchar(30) DEFAULT NULL COMMENT '样式',
  `accessUrl` varchar(300) DEFAULT NULL COMMENT '访问url',
  `parentMenuId` int(20) DEFAULT NULL COMMENT '父菜单id',
  `sortNum` int(20) DEFAULT NULL COMMENT '排序',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`menuId`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES ('1', '001', '系统菜单', 'icon-system', null, '-1', '1', '1', null, null, '37', '奥特曼', '2014-09-28 19:41:19', '2014-12-29 21:10:31');
INSERT INTO `t_menu` VALUES ('2', '002', '个人工作台', 'icon-workbench', '', '1', '3', '1', null, null, '1', 'admin', '2014-09-29 17:17:20', '2015-06-28 21:01:43');
INSERT INTO `t_menu` VALUES ('3', '003', '权限管理', 'icon-account', null, '1', '4', '1', null, null, '1', 'admin', '2014-09-28 19:42:29', '2015-06-28 21:01:43');
INSERT INTO `t_menu` VALUES ('4', '004', '系统管理', 'icon-set', '', '1', '5', '1', '37', '奥特曼', '1', 'admin', '2014-12-21 16:28:19', '2015-06-28 21:01:42');
INSERT INTO `t_menu` VALUES ('5', '005', '角色管理', 'icon-role', 'role/preRoleManage.jhtml', '3', '3', '1', null, null, '37', '小杂碎', '2014-10-16 14:50:03', '2015-06-15 10:03:55');
INSERT INTO `t_menu` VALUES ('6', '006', '菜单管理', 'icon-menu', 'menu/preMenuManage.jhtml', '3', '4', '1', null, null, '37', '奥特曼', '2014-11-21 21:28:03', '2015-03-22 13:57:22');
INSERT INTO `t_menu` VALUES ('7', '007', '部门管理', 'icon-dept', 'dept/preDeptManage.jhtml', '3', '2', '1', null, null, '37', '小杂碎', '2014-11-21 21:28:03', '2015-06-15 10:03:55');
INSERT INTO `t_menu` VALUES ('8', '008', '按钮管理', 'icon-button', 'button/preButtonManage.jhtml', '3', '5', '1', '37', '奥特曼', '1', 'admin', '2014-12-21 16:26:20', '2015-06-26 08:52:22');
INSERT INTO `t_menu` VALUES ('9', '009', '用户管理', 'icon-admin', 'user/preUserManage.jhtml', '3', '1', '1', null, null, '37', '小杂碎', '2014-09-28 19:48:52', '2015-06-15 10:03:52');
INSERT INTO `t_menu` VALUES ('10', '010', '数据字典管理', 'icon-data-dic', 'dataDic/preDataDicManage.jhtml', '4', '1', '1', null, null, '37', '小杂碎', '2014-09-29 17:18:23', '2015-05-31 17:22:53');
INSERT INTO `t_menu` VALUES ('11', '011', '我的待办', 'icon-tip', 'task/preTaskManage.jhtml', '2', '1', '1', '37', '奥特曼', '1', 'admin', '2014-12-21 16:31:41', '2015-07-14 17:16:55');
INSERT INTO `t_menu` VALUES ('12', '012', '我的公告', 'icon-notice', '', '2', '2', '3', '37', '奥特曼', '1', '卡卡', '2014-12-21 16:33:05', '2016-12-22 19:20:14');
INSERT INTO `t_menu` VALUES ('13', '013', '系统日志管理', 'icon-log-file', 'systemLog/preSystemLogManage.jhtml', '4', '2', '1', '37', '小杂碎', '1', 'admin', '2015-04-24 21:25:28', '2015-06-28 14:50:04');
INSERT INTO `t_menu` VALUES ('14', '014', '登录日志管理', 'icon-log-file', 'loginLog/preLoginLogManage.jhtml', '4', '3', '1', '37', '小杂碎', null, null, '2015-04-26 09:30:46', null);
INSERT INTO `t_menu` VALUES ('15', '015', '系统公告管理', 'icon-notice', 'notice/preSysNoticeManage.jhtml', '4', '4', '1', '37', null, '1', '卡卡', '2015-04-26 09:30:46', '2016-04-02 14:43:46');
INSERT INTO `t_menu` VALUES ('16', '005', '示例', 'icon-smile', '', '1', '6', '1', '1', 'admin', '1', 'admin', '2015-06-17 08:23:42', '2015-06-28 21:01:41');
INSERT INTO `t_menu` VALUES ('17', '001', 'highcharts示例', 'icon-report', 'demo/preHighcharts.jhtml', '16', '1', '3', '1', 'admin', '1', '卡卡', '2015-06-17 08:27:33', '2016-10-02 19:17:59');
INSERT INTO `t_menu` VALUES ('18', '002', 'datagrid editing', 'icon-table', 'demo/preDatagridEditing.jhtml', '16', '2', '1', '1', '小咖', '1', '小咖', '2015-08-27 19:55:13', '2015-08-30 10:59:36');
INSERT INTO `t_menu` VALUES ('19', '01', '访问管理', 'icon-web-site', 'accessUrl/preAccessUrlManage.jhtml', '3', '6', '1', '1', '小咖', null, null, '2015-08-27 20:01:00', null);
INSERT INTO `t_menu` VALUES ('20', '003', 'jquery扩展插件', 'icon-move', 'demo/prejQExtend.jhtml', '16', '3', '3', '1', '小咖', '1', '卡卡', '2015-09-27 09:17:46', '2016-10-02 19:20:10');

-- ----------------------------
-- Table structure for `t_notice`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice`;
CREATE TABLE `t_notice` (
  `noticeId` int(10) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `noticeTitle` varchar(200) DEFAULT NULL COMMENT '公告标题',
  `noticeContent` text COMMENT '公告内容',
  `noticeType` char(1) DEFAULT NULL COMMENT '公告类型(1 系统公告，2部门公告，3角色公告）',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2关闭，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `cutoffDate` date DEFAULT NULL COMMENT '截止时间',
  PRIMARY KEY (`noticeId`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice
-- ----------------------------
INSERT INTO `t_notice` VALUES ('1', '我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的我是角色的的的的折的', 'fdf', '2', '1', null, null, null, null, '2016-04-04 16:02:22', null, '2016-04-13');
INSERT INTO `t_notice` VALUES ('2', '我是改了的', '<!doctype html>我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/42.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/134.gif\" border=\"0\" alt=\"\" />是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/27.gif\" border=\"0\" alt=\"\" />是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的我是改了的<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/29.gif\" border=\"0\" alt=\"\" />', '1', '2', null, null, '1', '卡卡', null, '2016-04-04 15:47:04', '2016-04-02');
INSERT INTO `t_notice` VALUES ('3', '1', '<!doctype html>fd', '2', '1', '1', '卡卡', null, null, '2016-04-03 16:13:21', null, '2016-04-23');
INSERT INTO `t_notice` VALUES ('4', '2', '<!doctype html><span style=\"font-family:\'Arial Black\';\">fdfdf</span><span style=\"font-family:NSimSun;\"><span style=\"font-family:Arial Black;\"></span><span style=\"font-family:\'Arial Black\';\"></span></span>', '3', '1', '1', '卡卡', null, null, '2016-04-03 16:14:27', null, '2016-04-30');
INSERT INTO `t_notice` VALUES ('5', '3', '<!doctype html>fdfdf', '2', '1', '1', '卡卡', null, null, '2016-04-03 16:15:18', null, '2016-05-27');
INSERT INTO `t_notice` VALUES ('6', '这也是一个公告', '<!doctype html>这也是一个公告', '1', '1', '1', '卡卡', '1', '卡卡', '2016-04-03 16:16:03', '2016-12-22 19:21:37', '2016-04-23');
INSERT INTO `t_notice` VALUES ('7', '这是一个公告', '<!doctype html>这是一个公告', '1', '1', '1', '卡卡', '1', '卡卡', '2016-04-03 16:16:16', '2016-12-22 19:21:28', '2016-05-07');
INSERT INTO `t_notice` VALUES ('8', 'fdfdf', '<!doctype html>fdf', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:16:24', '2016-12-22 19:21:14', null);
INSERT INTO `t_notice` VALUES ('9', 'aa', '<!doctype html>aa', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:16:39', '2016-12-22 19:21:14', null);
INSERT INTO `t_notice` VALUES ('10', 'bb', '<!doctype html>bbbb', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:17:00', '2016-12-22 19:21:14', null);
INSERT INTO `t_notice` VALUES ('11', 'fdf', '<!doctype html>fdf', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:26:29', '2016-04-04 15:40:39', '2016-04-15');
INSERT INTO `t_notice` VALUES ('12', '我是打', '<!doctype html>我是打我是打我是打<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/31.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/10.gif\" border=\"0\" alt=\"\" />', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:26:52', '2016-04-04 15:40:30', '2016-04-28');
INSERT INTO `t_notice` VALUES ('13', '2', '<!doctype html>2', '1', '2', '1', '卡卡', null, null, '2016-04-03 16:28:05', null, '2016-04-26');
INSERT INTO `t_notice` VALUES ('14', '3', '<!doctype html>3', '1', '2', '1', '卡卡', null, null, '2016-04-03 16:28:26', null, '2016-04-19');
INSERT INTO `t_notice` VALUES ('15', '5', '<!doctype html>5', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:28:34', '2016-04-04 15:40:36', '2016-04-21');
INSERT INTO `t_notice` VALUES ('16', 'fda123', '<!doctype html>fda123fda123<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/30.gif\" border=\"0\" alt=\"\" />', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:31:54', '2016-12-22 19:21:51', '2016-04-04');
INSERT INTO `t_notice` VALUES ('17', 'fd', '<!doctype html>fdfdf', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:32:02', '2016-04-04 15:40:30', '2016-04-22');
INSERT INTO `t_notice` VALUES ('18', 'fd111', '<!doctype html>fdfdfda123fda123fda123fda123fda123<img src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/19.gif\" border=\"0\" alt=\"\" />', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:32:22', '2016-04-04 15:40:36', '2016-04-21');
INSERT INTO `t_notice` VALUES ('19', 'fd', '<!doctype html>fdf', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:32:45', '2016-04-04 16:05:03', '2016-04-06');
INSERT INTO `t_notice` VALUES ('20', 'fd', '<!doctype html>fdf', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:34:12', '2016-04-04 16:04:58', '2016-04-13');
INSERT INTO `t_notice` VALUES ('21', 'fd', '<!doctype html>\n<div>\n	fdfdf\n</div>', '1', '3', '1', '卡卡', '1', '卡卡', '2016-04-03 16:34:53', '2016-12-22 19:21:51', '2016-04-07');
INSERT INTO `t_notice` VALUES ('22', '公告信息', '<!doctype html><img alt=\"\" src=\"http://localhost:8080/cjhmeframework/common/js/kindeditor/plugins/emoticons/images/19.gif\" border=\"0\" />', '1', '1', '1', '卡卡', '1', '卡卡', '2016-04-03 16:46:09', '2016-12-22 19:21:48', '2016-04-14');

-- ----------------------------
-- Table structure for `t_notice_user_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_notice_user_mapping`;
CREATE TABLE `t_notice_user_mapping` (
  `numId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `noticeId` int(20) NOT NULL COMMENT '公告id',
  `userId` int(20) NOT NULL COMMENT '用户id',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2关闭，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`numId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_notice_user_mapping
-- ----------------------------
INSERT INTO `t_notice_user_mapping` VALUES ('1', '1', '1', '1', null, null, null, null, null, null);
INSERT INTO `t_notice_user_mapping` VALUES ('2', '3', '1', '1', null, null, null, null, null, null);
INSERT INTO `t_notice_user_mapping` VALUES ('3', '4', '1', '2', null, null, null, null, null, null);
INSERT INTO `t_notice_user_mapping` VALUES ('4', '5', '2', '1', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `roleCode` char(30) DEFAULT NULL COMMENT '角色编码',
  `roleName` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `roleDescribe` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `isSuper` char(1) DEFAULT '1' COMMENT '是否超级管理员(1否，2是)',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  `emailSwitch` char(1) DEFAULT NULL COMMENT '短信开关(1开启，2关闭)',
  `smsSwitch` char(1) DEFAULT NULL COMMENT '邮件开关(1开启，2关闭)',
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '001', '开发超级管理员', '开发最高权限', '1', '1', '1', 'admin', '37', '小杂碎', '2015-06-16 17:58:16', '2015-06-16 17:59:57', '1', '1');
INSERT INTO `t_role` VALUES ('2', '002', '普通用户', '', '1', '1', '1', '小咖', '1', '卡卡', '2015-08-30 09:20:34', '2016-12-22 19:20:52', '1', '1');

-- ----------------------------
-- Table structure for `t_role_access_url_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_access_url_mapping`;
CREATE TABLE `t_role_access_url_mapping` (
  `rumId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(20) NOT NULL COMMENT '角色id',
  `accessUrlId` int(20) NOT NULL COMMENT '访问地址id',
  PRIMARY KEY (`rumId`)
) ENGINE=InnoDB AUTO_INCREMENT=1693 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_access_url_mapping
-- ----------------------------
INSERT INTO `t_role_access_url_mapping` VALUES ('1581', '1', '1');
INSERT INTO `t_role_access_url_mapping` VALUES ('1582', '1', '2');
INSERT INTO `t_role_access_url_mapping` VALUES ('1583', '1', '3');
INSERT INTO `t_role_access_url_mapping` VALUES ('1584', '1', '4');
INSERT INTO `t_role_access_url_mapping` VALUES ('1585', '1', '5');
INSERT INTO `t_role_access_url_mapping` VALUES ('1586', '1', '6');
INSERT INTO `t_role_access_url_mapping` VALUES ('1587', '1', '7');
INSERT INTO `t_role_access_url_mapping` VALUES ('1588', '1', '8');
INSERT INTO `t_role_access_url_mapping` VALUES ('1589', '1', '9');
INSERT INTO `t_role_access_url_mapping` VALUES ('1590', '1', '10');
INSERT INTO `t_role_access_url_mapping` VALUES ('1591', '1', '11');
INSERT INTO `t_role_access_url_mapping` VALUES ('1592', '1', '12');
INSERT INTO `t_role_access_url_mapping` VALUES ('1593', '1', '13');
INSERT INTO `t_role_access_url_mapping` VALUES ('1594', '1', '14');
INSERT INTO `t_role_access_url_mapping` VALUES ('1595', '1', '15');
INSERT INTO `t_role_access_url_mapping` VALUES ('1596', '1', '16');
INSERT INTO `t_role_access_url_mapping` VALUES ('1597', '1', '17');
INSERT INTO `t_role_access_url_mapping` VALUES ('1598', '1', '18');
INSERT INTO `t_role_access_url_mapping` VALUES ('1599', '1', '19');
INSERT INTO `t_role_access_url_mapping` VALUES ('1600', '1', '20');
INSERT INTO `t_role_access_url_mapping` VALUES ('1601', '1', '21');
INSERT INTO `t_role_access_url_mapping` VALUES ('1602', '1', '22');
INSERT INTO `t_role_access_url_mapping` VALUES ('1603', '1', '23');
INSERT INTO `t_role_access_url_mapping` VALUES ('1604', '1', '24');
INSERT INTO `t_role_access_url_mapping` VALUES ('1605', '1', '25');
INSERT INTO `t_role_access_url_mapping` VALUES ('1606', '1', '26');
INSERT INTO `t_role_access_url_mapping` VALUES ('1607', '1', '27');
INSERT INTO `t_role_access_url_mapping` VALUES ('1608', '1', '28');
INSERT INTO `t_role_access_url_mapping` VALUES ('1609', '1', '29');
INSERT INTO `t_role_access_url_mapping` VALUES ('1610', '1', '30');
INSERT INTO `t_role_access_url_mapping` VALUES ('1611', '1', '31');
INSERT INTO `t_role_access_url_mapping` VALUES ('1612', '1', '32');
INSERT INTO `t_role_access_url_mapping` VALUES ('1613', '1', '33');
INSERT INTO `t_role_access_url_mapping` VALUES ('1614', '1', '34');
INSERT INTO `t_role_access_url_mapping` VALUES ('1615', '1', '35');
INSERT INTO `t_role_access_url_mapping` VALUES ('1616', '1', '36');
INSERT INTO `t_role_access_url_mapping` VALUES ('1617', '1', '37');
INSERT INTO `t_role_access_url_mapping` VALUES ('1618', '1', '38');
INSERT INTO `t_role_access_url_mapping` VALUES ('1619', '1', '39');
INSERT INTO `t_role_access_url_mapping` VALUES ('1620', '1', '40');
INSERT INTO `t_role_access_url_mapping` VALUES ('1621', '1', '41');
INSERT INTO `t_role_access_url_mapping` VALUES ('1622', '1', '42');
INSERT INTO `t_role_access_url_mapping` VALUES ('1623', '1', '43');
INSERT INTO `t_role_access_url_mapping` VALUES ('1624', '1', '44');
INSERT INTO `t_role_access_url_mapping` VALUES ('1625', '1', '45');
INSERT INTO `t_role_access_url_mapping` VALUES ('1626', '1', '46');
INSERT INTO `t_role_access_url_mapping` VALUES ('1627', '1', '47');
INSERT INTO `t_role_access_url_mapping` VALUES ('1628', '1', '48');
INSERT INTO `t_role_access_url_mapping` VALUES ('1629', '1', '49');
INSERT INTO `t_role_access_url_mapping` VALUES ('1630', '1', '50');
INSERT INTO `t_role_access_url_mapping` VALUES ('1631', '1', '51');
INSERT INTO `t_role_access_url_mapping` VALUES ('1632', '1', '52');
INSERT INTO `t_role_access_url_mapping` VALUES ('1633', '1', '53');
INSERT INTO `t_role_access_url_mapping` VALUES ('1634', '1', '54');
INSERT INTO `t_role_access_url_mapping` VALUES ('1635', '1', '55');
INSERT INTO `t_role_access_url_mapping` VALUES ('1636', '1', '56');
INSERT INTO `t_role_access_url_mapping` VALUES ('1637', '1', '57');
INSERT INTO `t_role_access_url_mapping` VALUES ('1638', '1', '58');
INSERT INTO `t_role_access_url_mapping` VALUES ('1639', '1', '59');
INSERT INTO `t_role_access_url_mapping` VALUES ('1640', '1', '60');
INSERT INTO `t_role_access_url_mapping` VALUES ('1641', '1', '61');
INSERT INTO `t_role_access_url_mapping` VALUES ('1642', '1', '62');
INSERT INTO `t_role_access_url_mapping` VALUES ('1643', '1', '63');
INSERT INTO `t_role_access_url_mapping` VALUES ('1644', '1', '64');
INSERT INTO `t_role_access_url_mapping` VALUES ('1645', '1', '65');
INSERT INTO `t_role_access_url_mapping` VALUES ('1646', '1', '66');
INSERT INTO `t_role_access_url_mapping` VALUES ('1647', '1', '74');
INSERT INTO `t_role_access_url_mapping` VALUES ('1648', '1', '75');
INSERT INTO `t_role_access_url_mapping` VALUES ('1649', '1', '76');
INSERT INTO `t_role_access_url_mapping` VALUES ('1650', '1', '77');
INSERT INTO `t_role_access_url_mapping` VALUES ('1651', '1', '78');
INSERT INTO `t_role_access_url_mapping` VALUES ('1652', '1', '79');
INSERT INTO `t_role_access_url_mapping` VALUES ('1653', '1', '80');
INSERT INTO `t_role_access_url_mapping` VALUES ('1654', '1', '81');
INSERT INTO `t_role_access_url_mapping` VALUES ('1655', '1', '82');
INSERT INTO `t_role_access_url_mapping` VALUES ('1656', '1', '83');
INSERT INTO `t_role_access_url_mapping` VALUES ('1657', '1', '84');
INSERT INTO `t_role_access_url_mapping` VALUES ('1658', '1', '85');
INSERT INTO `t_role_access_url_mapping` VALUES ('1659', '1', '86');
INSERT INTO `t_role_access_url_mapping` VALUES ('1660', '1', '87');
INSERT INTO `t_role_access_url_mapping` VALUES ('1661', '1', '88');
INSERT INTO `t_role_access_url_mapping` VALUES ('1662', '1', '89');
INSERT INTO `t_role_access_url_mapping` VALUES ('1663', '1', '90');
INSERT INTO `t_role_access_url_mapping` VALUES ('1664', '1', '91');
INSERT INTO `t_role_access_url_mapping` VALUES ('1665', '1', '92');
INSERT INTO `t_role_access_url_mapping` VALUES ('1666', '1', '93');
INSERT INTO `t_role_access_url_mapping` VALUES ('1667', '1', '94');
INSERT INTO `t_role_access_url_mapping` VALUES ('1668', '1', '95');
INSERT INTO `t_role_access_url_mapping` VALUES ('1669', '1', '96');
INSERT INTO `t_role_access_url_mapping` VALUES ('1670', '1', '97');
INSERT INTO `t_role_access_url_mapping` VALUES ('1671', '1', '98');
INSERT INTO `t_role_access_url_mapping` VALUES ('1672', '1', '99');
INSERT INTO `t_role_access_url_mapping` VALUES ('1673', '1', '100');
INSERT INTO `t_role_access_url_mapping` VALUES ('1674', '1', '101');
INSERT INTO `t_role_access_url_mapping` VALUES ('1675', '1', '102');
INSERT INTO `t_role_access_url_mapping` VALUES ('1676', '1', '103');
INSERT INTO `t_role_access_url_mapping` VALUES ('1677', '1', '104');
INSERT INTO `t_role_access_url_mapping` VALUES ('1678', '1', '105');
INSERT INTO `t_role_access_url_mapping` VALUES ('1679', '1', '106');
INSERT INTO `t_role_access_url_mapping` VALUES ('1680', '1', '107');
INSERT INTO `t_role_access_url_mapping` VALUES ('1681', '1', '108');
INSERT INTO `t_role_access_url_mapping` VALUES ('1682', '1', '109');
INSERT INTO `t_role_access_url_mapping` VALUES ('1683', '1', '110');
INSERT INTO `t_role_access_url_mapping` VALUES ('1684', '1', '111');
INSERT INTO `t_role_access_url_mapping` VALUES ('1685', '1', '112');
INSERT INTO `t_role_access_url_mapping` VALUES ('1686', '1', '113');
INSERT INTO `t_role_access_url_mapping` VALUES ('1687', '1', '114');
INSERT INTO `t_role_access_url_mapping` VALUES ('1688', '1', '115');
INSERT INTO `t_role_access_url_mapping` VALUES ('1689', '1', '116');
INSERT INTO `t_role_access_url_mapping` VALUES ('1690', '1', '117');
INSERT INTO `t_role_access_url_mapping` VALUES ('1691', '1', '118');
INSERT INTO `t_role_access_url_mapping` VALUES ('1692', '1', '119');

-- ----------------------------
-- Table structure for `t_role_button_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_button_mapping`;
CREATE TABLE `t_role_button_mapping` (
  `rbmId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(20) NOT NULL COMMENT '角色id',
  `buttonId` int(20) NOT NULL COMMENT '按钮id',
  PRIMARY KEY (`rbmId`)
) ENGINE=InnoDB AUTO_INCREMENT=637 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_button_mapping
-- ----------------------------
INSERT INTO `t_role_button_mapping` VALUES ('582', '1', '1');
INSERT INTO `t_role_button_mapping` VALUES ('583', '1', '2');
INSERT INTO `t_role_button_mapping` VALUES ('584', '1', '3');
INSERT INTO `t_role_button_mapping` VALUES ('585', '1', '4');
INSERT INTO `t_role_button_mapping` VALUES ('586', '1', '5');
INSERT INTO `t_role_button_mapping` VALUES ('587', '1', '51');
INSERT INTO `t_role_button_mapping` VALUES ('588', '1', '6');
INSERT INTO `t_role_button_mapping` VALUES ('589', '1', '7');
INSERT INTO `t_role_button_mapping` VALUES ('590', '1', '14');
INSERT INTO `t_role_button_mapping` VALUES ('591', '1', '16');
INSERT INTO `t_role_button_mapping` VALUES ('592', '1', '15');
INSERT INTO `t_role_button_mapping` VALUES ('593', '1', '17');
INSERT INTO `t_role_button_mapping` VALUES ('594', '1', '18');
INSERT INTO `t_role_button_mapping` VALUES ('595', '1', '19');
INSERT INTO `t_role_button_mapping` VALUES ('596', '1', '20');
INSERT INTO `t_role_button_mapping` VALUES ('597', '1', '50');
INSERT INTO `t_role_button_mapping` VALUES ('598', '1', '46');
INSERT INTO `t_role_button_mapping` VALUES ('599', '1', '47');
INSERT INTO `t_role_button_mapping` VALUES ('600', '1', '48');
INSERT INTO `t_role_button_mapping` VALUES ('601', '1', '49');
INSERT INTO `t_role_button_mapping` VALUES ('602', '1', '44');
INSERT INTO `t_role_button_mapping` VALUES ('603', '1', '45');
INSERT INTO `t_role_button_mapping` VALUES ('604', '1', '21');
INSERT INTO `t_role_button_mapping` VALUES ('605', '1', '22');
INSERT INTO `t_role_button_mapping` VALUES ('606', '1', '23');
INSERT INTO `t_role_button_mapping` VALUES ('607', '1', '25');
INSERT INTO `t_role_button_mapping` VALUES ('608', '1', '24');
INSERT INTO `t_role_button_mapping` VALUES ('609', '1', '27');
INSERT INTO `t_role_button_mapping` VALUES ('610', '1', '26');
INSERT INTO `t_role_button_mapping` VALUES ('611', '1', '28');
INSERT INTO `t_role_button_mapping` VALUES ('612', '1', '29');
INSERT INTO `t_role_button_mapping` VALUES ('613', '1', '30');
INSERT INTO `t_role_button_mapping` VALUES ('614', '1', '31');
INSERT INTO `t_role_button_mapping` VALUES ('615', '1', '42');
INSERT INTO `t_role_button_mapping` VALUES ('616', '1', '43');
INSERT INTO `t_role_button_mapping` VALUES ('617', '1', '35');
INSERT INTO `t_role_button_mapping` VALUES ('618', '1', '32');
INSERT INTO `t_role_button_mapping` VALUES ('619', '1', '33');
INSERT INTO `t_role_button_mapping` VALUES ('620', '1', '36');
INSERT INTO `t_role_button_mapping` VALUES ('621', '1', '34');
INSERT INTO `t_role_button_mapping` VALUES ('622', '1', '37');
INSERT INTO `t_role_button_mapping` VALUES ('623', '1', '38');
INSERT INTO `t_role_button_mapping` VALUES ('624', '1', '39');
INSERT INTO `t_role_button_mapping` VALUES ('625', '1', '40');
INSERT INTO `t_role_button_mapping` VALUES ('626', '1', '41');
INSERT INTO `t_role_button_mapping` VALUES ('627', '1', '52');
INSERT INTO `t_role_button_mapping` VALUES ('628', '1', '53');
INSERT INTO `t_role_button_mapping` VALUES ('629', '1', '8');
INSERT INTO `t_role_button_mapping` VALUES ('630', '1', '9');
INSERT INTO `t_role_button_mapping` VALUES ('631', '1', '10');
INSERT INTO `t_role_button_mapping` VALUES ('632', '1', '11');
INSERT INTO `t_role_button_mapping` VALUES ('633', '1', '12');
INSERT INTO `t_role_button_mapping` VALUES ('634', '1', '13');
INSERT INTO `t_role_button_mapping` VALUES ('635', '1', '54');
INSERT INTO `t_role_button_mapping` VALUES ('636', '1', '55');

-- ----------------------------
-- Table structure for `t_role_menu_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu_mapping`;
CREATE TABLE `t_role_menu_mapping` (
  `rmmId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `roleId` int(20) NOT NULL COMMENT '角色id',
  `menuId` int(20) NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`rmmId`)
) ENGINE=InnoDB AUTO_INCREMENT=166 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role_menu_mapping
-- ----------------------------
INSERT INTO `t_role_menu_mapping` VALUES ('143', '1', '1');
INSERT INTO `t_role_menu_mapping` VALUES ('144', '1', '2');
INSERT INTO `t_role_menu_mapping` VALUES ('145', '1', '11');
INSERT INTO `t_role_menu_mapping` VALUES ('147', '1', '3');
INSERT INTO `t_role_menu_mapping` VALUES ('148', '1', '9');
INSERT INTO `t_role_menu_mapping` VALUES ('149', '1', '7');
INSERT INTO `t_role_menu_mapping` VALUES ('150', '1', '5');
INSERT INTO `t_role_menu_mapping` VALUES ('151', '1', '6');
INSERT INTO `t_role_menu_mapping` VALUES ('152', '1', '8');
INSERT INTO `t_role_menu_mapping` VALUES ('153', '1', '4');
INSERT INTO `t_role_menu_mapping` VALUES ('154', '1', '10');
INSERT INTO `t_role_menu_mapping` VALUES ('155', '1', '13');
INSERT INTO `t_role_menu_mapping` VALUES ('156', '1', '14');
INSERT INTO `t_role_menu_mapping` VALUES ('157', '1', '15');
INSERT INTO `t_role_menu_mapping` VALUES ('158', '1', '16');
INSERT INTO `t_role_menu_mapping` VALUES ('160', '1', '18');
INSERT INTO `t_role_menu_mapping` VALUES ('161', '1', '19');
INSERT INTO `t_role_menu_mapping` VALUES ('163', '2', '11');
INSERT INTO `t_role_menu_mapping` VALUES ('164', '2', '3');
INSERT INTO `t_role_menu_mapping` VALUES ('165', '2', '9');

-- ----------------------------
-- Table structure for `t_task`
-- ----------------------------
DROP TABLE IF EXISTS `t_task`;
CREATE TABLE `t_task` (
  `taskId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `busniessMark` varchar(100) DEFAULT NULL COMMENT '业务标识',
  `busniessId` varchar(30) DEFAULT NULL COMMENT '业务id',
  `title` varchar(120) DEFAULT NULL COMMENT '标题',
  `taskUrl` varchar(300) DEFAULT NULL COMMENT 'url',
  `taskParameter` varchar(60) DEFAULT NULL COMMENT '待办参数',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2已处理，3删除)',
  `processUserId` int(30) DEFAULT NULL COMMENT '处理人id',
  `processUser` varchar(30) DEFAULT NULL COMMENT '处理人',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`taskId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_task
-- ----------------------------
INSERT INTO `t_task` VALUES ('1', '2', '2', 'csdn332557712', 'http://blog.csdn.net/rotating_windmill/article/details/53207451', ' ', '1', '1', 'admin', '1', '小咖', '1', '小咖', '2015-07-14 17:33:33', '2015-12-03 19:27:49');
INSERT INTO `t_task` VALUES ('2', '1', '1', '百度', 'https://www.baidu.com/', '', '1', '1', 'admin', '1', '小咖', '1', '小咖', '2015-07-14 17:33:54', '2015-12-03 19:27:54');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `userCode` char(30) DEFAULT NULL COMMENT '用户编号',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `realName` varchar(30) DEFAULT NULL COMMENT '真实姓名',
  `sex` char(1) DEFAULT NULL COMMENT '性别：1男，2女，3保密',
  `telephone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `idCard` char(20) DEFAULT NULL COMMENT '身份证',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `emailSwitch` char(1) DEFAULT NULL COMMENT '邮件开关(1开启，2关闭)',
  `smsSwitch` char(1) DEFAULT NULL COMMENT '短信开关(1开启，2关闭)',
  `status` char(1) DEFAULT NULL COMMENT '状态(1正常，2锁定，3删除)',
  `userDescribe` varchar(200) DEFAULT NULL COMMENT '用户描述',
  `createUserId` int(20) DEFAULT NULL COMMENT '创建人id',
  `createUser` varchar(30) DEFAULT NULL COMMENT '创建人',
  `updateUserId` int(20) DEFAULT NULL COMMENT '修改人id',
  `updateUser` varchar(30) DEFAULT NULL COMMENT '修改人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `updateDate` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '001', 'admin', '49534d764b5870587061644469556f4f536f416677773d3d', '卡卡', '1', '', null, '', '1', '1', '1', '', '1', 'admin', '1', '卡卡', null, '2016-12-22 19:27:35');
INSERT INTO `t_user` VALUES ('2', '002', 'user01', '6c756553474a5a657479795370556e64576a4d4245673d3d', '张三', '1', '', '', '', '1', '1', '1', '', '1', '卡卡', null, null, '2016-08-30 20:59:41', null);
INSERT INTO `t_user` VALUES ('3', '003', 'user02', '6c756553474a5a657479795370556e64576a4d4245673d3d', '李白', '1', '', '', '', '1', '1', '1', '', '1', '卡卡', null, null, '2016-08-30 21:00:25', null);
INSERT INTO `t_user` VALUES ('4', '004', 'user03', '6c756553474a5a657479795370556e64576a4d4245673d3d', '钱一', '1', '18950950213', '', '', '1', '1', '1', '', '1', '卡卡', null, null, '2016-08-30 21:01:01', null);
INSERT INTO `t_user` VALUES ('5', '005', 'user04', '6c756553474a5a657479795370556e64576a4d4245673d3d', '王二', '1', '18950950213', '', '', '1', '1', '1', '', '1', '卡卡', null, null, '2016-08-30 21:01:21', null);
INSERT INTO `t_user` VALUES ('6', '006', 'user05', '6c756553474a5a657479795370556e64576a4d4245673d3d', '王五', '1', '', '', '', '1', '1', '1', '', '1', '卡卡', null, null, '2016-08-30 21:01:49', null);

-- ----------------------------
-- Table structure for `t_user_dept_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_dept_mapping`;
CREATE TABLE `t_user_dept_mapping` (
  `udmId` int(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` int(20) NOT NULL COMMENT '用户id',
  `deptId` int(20) NOT NULL COMMENT '部门id',
  PRIMARY KEY (`udmId`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_dept_mapping
-- ----------------------------
INSERT INTO `t_user_dept_mapping` VALUES ('9', '1', '1');

-- ----------------------------
-- Table structure for `t_user_role_mapping`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role_mapping`;
CREATE TABLE `t_user_role_mapping` (
  `urmId` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户角色映射id',
  `userId` int(20) NOT NULL COMMENT '用户id',
  `roleId` int(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`urmId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role_mapping
-- ----------------------------
INSERT INTO `t_user_role_mapping` VALUES ('2', '1', '1');
INSERT INTO `t_user_role_mapping` VALUES ('3', '1', '2');
