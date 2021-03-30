var mUrl = mUrl || {};

//后缀
mUrl.webSuffix=".jhtml";

//======公共地址======

//图片上传
mUrl.uploadImg= webPath +'fileUpload/uploadImg'+mUrl.webSuffix;

//图片上传重新生成文件名
mUrl.uploadImgByNewFileName= webPath +'fileUpload/uploadImgByNewFileName'+mUrl.webSuffix;

//图片上传压缩处理
mUrl.uploadImgCompression= webPath +'fileUpload/uploadImgCompression'+mUrl.webSuffix;


//======公共页面======

//跳转图标选择页面
mUrl.preIconSelect = webPath +'publicPage/preIconSelect'+mUrl.webSuffix+'?callBackFunc=';

//跳转单个用户选择页面
mUrl.preSingleUserSelect= webPath +'publicPage/preSingleUserSelect'+mUrl.webSuffix+'?callBackFunc=';

//根据条件查询所有用户
mUrl.queryAllUserByConditionPaging= webPath +'publicPage/queryAllUserByConditionPaging'+mUrl.webSuffix;

//跳转单个部门选择页面
mUrl.preSingleDeptSelect=webPath+'publicPage/preSingleDeptSelect'+mUrl.webSuffix+'?callBackFunc=';

//根据条件查询部门
mUrl.queryDeptByCondition=webPath+'publicPage/queryDeptByCondition'+mUrl.webSuffix;

//跳转单个菜单选择页面
mUrl.preSingleMenuSelect=webPath+'publicPage/preSingleMenuSelect'+mUrl.webSuffix+'?callBackFunc=';

//根据条件查询菜单
mUrl.queryMenuByCondition=webPath+'publicPage/queryMenuByCondition'+mUrl.webSuffix;

//跳转单个数据字典选择页面
mUrl.preSingleDataDicItemSelect=webPath+'publicPage/preSingleDataDicItemSelect'+mUrl.webSuffix+'?callBackFunc=';

//根据条件查询数据字典明细
mUrl.queryDataDicItemByCondition=webPath+'publicPage/queryDataDicItemByCondition'+mUrl.webSuffix;


//======系统======

//未登录跳转登录页面
mUrl.preNotLogin = webPath+'system/preNotLogin'+mUrl.webSuffix;

//登录
mUrl.login = webPath+'system/login'+mUrl.webSuffix;

//跳转主页面
mUrl.preIndexPage = webPath+'system/preIndex'+mUrl.webSuffix;

//退出登录
mUrl.exitLogin = webPath+'system/exitLogin'+mUrl.webSuffix;

//跳转权限未分配角色页面
mUrl.preUndistributeRole= webPath+'system/preUndistributeRole'+mUrl.webSuffix;

//跳转权限未验证通过页面
mUrl.preNoAutority= webPath+'system/preNoAutority'+mUrl.webSuffix;


//跳转当前用户密码编辑页面
mUrl.preCurrentUserPwdEdit = webPath+'system/preCurrentUserPwdEdit'+mUrl.webSuffix;

//跳转当前用户资料编辑页面
mUrl.preCurrentUserInfoEdit = webPath+'system/preCurrentUserInfoEdit'+mUrl.webSuffix;

//切换当前用户部门
mUrl.changeCurrentUserDept = webPath+'system/changeCurrentUserDept'+mUrl.webSuffix+'?deptId=';

//切换当前用户角色
mUrl.changeCurrentUserRole = webPath+'system/changeCurrentUserRole'+mUrl.webSuffix+'?roleId=';

//初始化用户菜单
mUrl.queryUserMenuByRoleId = webPath+'system/queryUserMenuByRoleId'+mUrl.webSuffix;

//初始化用户手风琴菜单
mUrl.queryUserAccordionMenuByRoleId=webPath+'system/queryUserAccordionMenuByRoleId'+mUrl.webSuffix;

//初始化用户手风琴树
mUrl.queryUserAccordionTreeMenuById=webPath+'system/queryUserAccordionTreeMenuById'+mUrl.webSuffix+'?parentMenuId=';

//跳转个性化页面
mUrl.preIndividuation=webPath+'system/preIndividuation'+mUrl.webSuffix;

//跳转常用功能配置页面
mUrl.preComFuncConfig=webPath+'system/preComFuncConfig'+mUrl.webSuffix;

//添加常用功能配置
mUrl.saveComfuncConfig=webPath+'system/saveComfuncConfig'+mUrl.webSuffix;



//======菜单======

//查询菜单列表
mUrl.queryMenuList = webPath+'menu/queryMenuList'+mUrl.webSuffix;

//根据条件分页查询菜单
mUrl.queryMenuByConditionPaging = webPath+'menu/queryMenuByConditionPaging'+mUrl.webSuffix;

//跳转菜单添加页面
mUrl.preMenuAdd = webPath+'menu/preMenuAdd'+mUrl.webSuffix+'?menuId=';

//添加菜单
mUrl.saveMenu= webPath+'menu/saveMenu'+mUrl.webSuffix;


//跳转菜单查看页面
mUrl.preMenuView= webPath+'menu/preMenuView'+mUrl.webSuffix+'?menuId=';

//跳转菜单编辑页面
mUrl.preMenuEdit= webPath+'menu/preMenuEdit'+mUrl.webSuffix+'?menuId=';

//根据id修改菜单
mUrl.updateMenuByMenuId= webPath+'menu/updateMenuByMenuId'+mUrl.webSuffix;

//根据ids删除菜单
mUrl.deleteMenuByIds=webPath+'menu/deleteMenuByIds'+mUrl.webSuffix+'?ids=';

//根据id移动菜单
mUrl.updateMenuParentMenuIdById= webPath+'menu/updateMenuParentMenuIdById'+mUrl.webSuffix;

//根据id移动菜单序号
mUrl.updateMenuSortNumById =  webPath+'menu/updateMenuSortNumById'+mUrl.webSuffix;




//======数据字典======

// 查询数据字典分类列表
mUrl.queryDataDicTypeList = webPath+'dataDic/queryDataDicTypeList'+mUrl.webSuffix+'?queryDicCategory=';

//根数据字典分类id查询数据字典明细
mUrl.queryDataDicItemByIdPaging = webPath+'dataDic/queryDataDicItemByIdPaging'+mUrl.webSuffix;

//跳转数据字典分类添加页面
mUrl.preDataDicTypeAdd = webPath+'dataDic/preDataDicTypeAdd'+mUrl.webSuffix+'?dicCategory=';

//添加数据字典分类
mUrl.saveDataDicType = webPath+'dataDic/saveDataDicType'+mUrl.webSuffix;

//跳转数据字典分类编辑页面
mUrl.preDataDicTypeEdit = webPath+'dataDic/preDataDicTypeEdit'+mUrl.webSuffix+'?dicTypeId=';

//根据id修改数据字典分类
mUrl.updateDataDicTypeById = webPath+'dataDic/updateDataDicTypeById'+mUrl.webSuffix;

//根据ids删除数据字典分类
mUrl.deleteDataDicTypeByIds = webPath+'dataDic/deleteDataDicTypeByIds'+mUrl.webSuffix+'?ids=';

//跳转数据字典明细添加页面
mUrl.preDataDicItemAdd = webPath+'dataDic/preDataDicItemAdd'+mUrl.webSuffix+'?dicTypeId=';

//添加数据字典明细 
mUrl.saveDataDicItem = webPath+'dataDic/saveDataDicItem'+mUrl.webSuffix;

//跳转数据字典明细查看页面
mUrl.preDataDicItemView=webPath+'dataDic/preDataDicItemView'+mUrl.webSuffix+'?dicItemId=';

//跳转数据字典明细编辑页面
mUrl.preDataDicItemEdit=webPath+'dataDic/preDataDicItemEdit'+mUrl.webSuffix+'?dicItemId=';

//根据id修改数据字典明细
mUrl.updateDataDicItemById=webPath+'dataDic/updateDataDicItemById'+mUrl.webSuffix;

//根据ids删除数据字典明细
mUrl.deleteDataDicItemByIds=webPath+'dataDic/deleteDataDicItemByIds'+mUrl.webSuffix+'?ids=';

//数据字典明细移动序号
mUrl.updateDataDicItemSortNumById=webPath+'dataDic/updateDataDicItemSortNumById'+mUrl.webSuffix;

//重新载入数据字典
mUrl.reloadDataDic=webPath+'dataDic/reloadDataDic'+mUrl.webSuffix;

//跳转数据字典明细角色分配页面
mUrl.preDataDicItemRoleDistribute=webPath+'dataDic/preDataDicItemRoleDistribute'+mUrl.webSuffix+'?dicItemId=';

//添加数据字典明细角色映射
mUrl.saveDataDicItemRoleMapping=webPath+'dataDic/saveDataDicItemRoleMapping'+mUrl.webSuffix;

//根据id移动字典明细
mUrl.updateDataDicItemParentIdById=webPath+'dataDic/updateDataDicItemParentIdById'+mUrl.webSuffix;


//======用户======

//根据条件分页查询用户
mUrl.queryUserByConditionPaging = webPath+'user/queryUserByConditionPaging'+mUrl.webSuffix;

//跳转用户添加页面
mUrl.preUserAdd = webPath+'user/preUserAdd'+mUrl.webSuffix;

//添加用户
mUrl.saveUser = webPath+'user/saveUser'+mUrl.webSuffix;


//跳转查看用户
mUrl.preUserView= webPath+'user/preUserView'+mUrl.webSuffix+'?userId=';

//跳转编辑用户
mUrl.preUserEdit= webPath+'user/preUserEdit'+mUrl.webSuffix+'?userId=';

//根据id修改用户
mUrl.updateUserById = webPath+'user/updateUserById'+mUrl.webSuffix;

//跳转用户密码编辑页面
mUrl.preUserPasswordEdit =webPath+'user/preUserPasswordEdit'+mUrl.webSuffix+'?userId=';

//根据id修改密码
mUrl.updateUserPasswordById= webPath+'user/updateUserPasswordById'+mUrl.webSuffix;

//根据ids删除用户
mUrl.deleteUserByIds = webPath+'user/deleteUserByIds'+mUrl.webSuffix+'?ids=';

//跳转用户角色分配页面
mUrl.preUserRoleDistribute = webPath+'user/preUserRoleDistribute'+mUrl.webSuffix+'?userId=';

//保存用户角色映射
mUrl.saveUserRoleMapping = webPath+'user/saveUserRoleMapping'+mUrl.webSuffix;

//跳转用户部门分配页面
mUrl.preUserDeptDistribute= webPath+'user/preUserDeptDistribute'+mUrl.webSuffix+'?userId=';


//保存用户部门映射
mUrl.saveUserDeptMapping= webPath+'user/saveUserDeptMapping'+mUrl.webSuffix

//根据ids重置用户密码
mUrl.resetUserPwdByIds= webPath+'user/resetUserPwdByIds'+mUrl.webSuffix+'?ids='



//======角色======
//根据条件分页查询角色
mUrl.queryRoleByConditionPaging = webPath+'role/queryRoleByConditionPaging'+mUrl.webSuffix;

//跳转角色添加页面
mUrl.preRoleAdd=webPath+'role/preRoleAdd'+mUrl.webSuffix;

//添加角色
mUrl.saveRole=webPath+'role/saveRole'+mUrl.webSuffix;

//跳转角色查看页面
mUrl.preRoleView=webPath+'role/preRoleView'+mUrl.webSuffix+'?roleId=';

//跳转角色编辑页面
mUrl.preRoleEdit=webPath+'role/preRoleEdit'+mUrl.webSuffix+'?roleId=';

//根据id修改角色
mUrl.updateRoleById=webPath+'role/updateRoleById'+mUrl.webSuffix;

//根据ids删除角色
mUrl.deleteRoleByIds=webPath+'role/deleteRoleByIds'+mUrl.webSuffix+'?ids=';

//跳转角色用户查询页面
mUrl.preRoleUsersQuery=webPath+'role/preRoleUsersQuery'+mUrl.webSuffix+'?roleId=';

//跳转角色菜单分配页面
mUrl.preRoleMenuDistribute=webPath+'role/preRoleMenuDistribute'+mUrl.webSuffix+'?roleId=';

//添加角色菜单映射
mUrl.saveRoleMenuMapping=webPath+'role/saveRoleMenuMapping'+mUrl.webSuffix;

//跳转角色按钮分配页面
mUrl.preRoleButtonDistribute=webPath+'role/preRoleButtonDistribute'+mUrl.webSuffix+'?roleId=';

//根据角色id查询角色按钮映射
mUrl.queryRoleButtonMappingById=webPath+'role/queryRoleButtonMappingById'+mUrl.webSuffix+'?roleId=';

//添加角色按钮映射
mUrl.saveRoleButtonMapping=webPath+'role/saveRoleButtonMapping'+mUrl.webSuffix;

//跳转角色访问地址分配页面
mUrl.preRoleAccessUrlDistribute=webPath+'role/preRoleAccessUrlDistribute'+mUrl.webSuffix+'?roleId=';

//添加角色访问地址映射
mUrl.saveRoleAccessUrlMapping=webPath+'role/saveRoleAccessUrlMapping'+mUrl.webSuffix;

//======部门======

//查询所有部门结构
mUrl.queryDeptList=webPath+'dept/queryDeptList'+mUrl.webSuffix;

//根据条件分页查询部门
mUrl.queryDeptByConditionPaging=webPath+'dept/queryDeptByConditionPaging'+mUrl.webSuffix;

//跳转部门添加页面
mUrl.preDeptAdd=webPath+'dept/preDeptAdd'+mUrl.webSuffix+'?deptId=';

//添加部门
mUrl.saveDept=webPath+'dept/saveDept'+mUrl.webSuffix;

//跳转部门查看页面
mUrl.preDeptView=webPath+'dept/preDeptView'+mUrl.webSuffix+'?deptId=';

//跳转部门编辑页面
mUrl.preDeptEdit=webPath+'dept/preDeptEdit'+mUrl.webSuffix+'?deptId=';

//根据id修改部门
mUrl.updateDeptByDeptId=webPath+'dept/updateDeptByDeptId'+mUrl.webSuffix;

//根据ids删除部门
mUrl.deleteDeptByIds=webPath+'dept/deleteDeptByIds'+mUrl.webSuffix+'?ids=';

//根据id移动部门
mUrl.updateDeptParentDeptIdById=webPath+'dept/updateDeptParentDeptIdById'+mUrl.webSuffix;

//跳转部门用户查询页面
mUrl.preDeptUsersQuery=webPath+'dept/preDeptUsersQuery'+mUrl.webSuffix+'?deptId=';


//======按钮======

//根据条件分页查询按钮
mUrl.queryButtonByConditionPaging=webPath+'button/queryButtonByConditionPaging'+mUrl.webSuffix;

//跳转按钮添加页面
mUrl.preButtonAdd=webPath+'button/preButtonAdd'+mUrl.webSuffix;

//添加按钮
mUrl.saveButton=webPath+'button/saveButton'+mUrl.webSuffix;

//跳转按钮查看页面
mUrl.preButtonView=webPath+'button/preButtonView'+mUrl.webSuffix+'?buttonId=';

//跳转按钮编辑页面
mUrl.preButtonEdit=webPath+'button/preButtonEdit'+mUrl.webSuffix+'?buttonId=';

//根据id修改按钮
mUrl.updateButtonById=webPath+'button/updateButtonById'+mUrl.webSuffix;

//根据ids删除按钮
mUrl.deleteButtonByIds=webPath+'button/deleteButtonByIds'+mUrl.webSuffix+'?ids=';

//======系统日志======

//系统日志下载
mUrl.downloadSystemLog=webPath+'systemLog/downloadSystemLog'+mUrl.webSuffix+'?filePath=';

//删除系统日志
mUrl.deleteSystemLog=webPath+'systemLog/deleteSystemLog'+mUrl.webSuffix+'?filePath=';

//======登录日志======

//根据条件分页查询登录日志
mUrl.queryLoginLogByConditionPaging=webPath+'loginLog/queryLoginLogByConditionPaging'+mUrl.webSuffix;

//======公告管理======

//根据条件分页查询系统公告
mUrl.querySysNoticeByConditionPaging=webPath+'notice/querySysNoticeByConditionPaging'+mUrl.webSuffix;

//跳转系统公告添加页面
mUrl.preSysNoticeAdd=webPath+'notice/preSysNoticeAdd'+mUrl.webSuffix;

//添加系统公告
mUrl.saveSysNotice=webPath+'notice/saveSysNotice'+mUrl.webSuffix;

//跳转角色查看页面
mUrl.preSysNoticeView=webPath+'notice/preSysNoticeView'+mUrl.webSuffix+'?noticeId=';

//跳转系统公告编辑页面
mUrl.preSysNoticeEdit=webPath+'notice/preSysNoticeEdit'+mUrl.webSuffix+'?noticeId=';

//根据id修改系统公告
mUrl.updateSysNoticeById=webPath+'notice/updateSysNoticeById'+mUrl.webSuffix;

//根据ids删除系统公告 
mUrl.deleteSysNoticeByIds=webPath+'notice/deleteSysNoticeByIds'+mUrl.webSuffix+'?ids=';



//======待办管理======

//跳转待办管理页面
mUrl.preTaskManage=webPath+'task/preTaskManage'+mUrl.webSuffix;

//根据条件分页查询待办 
mUrl.queryTaskByConditionPaging=webPath+'task/queryTaskByConditionPaging'+mUrl.webSuffix;

//根据id修改待办状态
mUrl.updateTaskById=webPath+'task/updateTaskById'+mUrl.webSuffix+'?taskId=';


//======访问地址======

//根据条件分页查询访问地址
mUrl.queryAccessUrlByConditionPaging=webPath+'accessUrl/queryAccessUrlByConditionPaging'+mUrl.webSuffix;

//跳转访问地址添加页面
mUrl.preAccessUrlAdd=webPath+'accessUrl/preAccessUrlAdd'+mUrl.webSuffix;

//添加访问地址
mUrl.saveAccessUrl=webPath+'accessUrl/saveAccessUrl'+mUrl.webSuffix;

//跳转访问地址查看页面
mUrl.preAccessUrlView=webPath+'accessUrl/preAccessUrlView'+mUrl.webSuffix+'?accessUrlId=';

//跳转访问地址编辑页面
mUrl.preAccessUrlEdit=webPath+'accessUrl/preAccessUrlEdit'+mUrl.webSuffix+'?accessUrlId=';

//根据id修改访问地址
mUrl.updateAccessUrlById=webPath+'accessUrl/updateAccessUrlById'+mUrl.webSuffix;

//根据ids删除访问地址
mUrl.deleteAccessUrlByIds=webPath+'accessUrl/deleteAccessUrlByIds'+mUrl.webSuffix+'?ids=';



//======demo======
mUrl.saveDemo=webPath+'demo/saveDemo'+mUrl.webSuffix;

mUrl.updateDemo=webPath+'demo/updateDemo'+mUrl.webSuffix;

mUrl.deleteDemo=webPath+'demo/deleteDemo'+mUrl.webSuffix+'?id=';

mUrl.queryDemoPaging=webPath+'demo/queryDemoPaging'+mUrl.webSuffix;

mUrl.queryDemoById=webPath+'demo/queryDemoById'+mUrl.webSuffix+'?id=';






//ajax扩展
(function() {
	var oldajaxfuc = jQuery.ajax;
	
	jQuery.extend({
		ajax: function( url, options ){
			if ( typeof url === "object" ) {
				options = url;
				url = undefined;
			}
			
			//扩展beforeSend
			/*options.beforeSend = function(xhr, st){
			   //parent.showMask();
			}*/
			
			
			//扩展success
			var oldSuccessFunc = options.success;
			options.success = function(ret) {
			    // parent.closeMask();
			    
				if(ret.result=="SESSION_TIME_OUT") {
					window.top.location.href=mUrl.preNotLogin;
					return;
				} else if(ret.result=="UNDISTRIBUTED_ROLE") {
					window.top.location.href=mUrl.preUndistributeRole;
					return;
				}else if(ret.result=="NO_AUTHORITY"){
				    window.top.location.href=mUrl.preNoAutority;
				    return;
				}else{
				   oldSuccessFunc.apply(this, arguments);
				}
			}
			
			
			oldajaxfuc(url, options);
		}
	});
	
})();




