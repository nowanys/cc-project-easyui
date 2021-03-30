package com.cjhme.system.main.constant;

/**
 * <p>
 * Title: CommonConstants.java
 * </p>
 * Description: 公共常量
 * <p>
 * Modify histoty:
 * 
 * @author cjh
 * @version 1.0
 * @created Oct 13, 2014 3:59:32 PM
 */
public class CommonConstants {

	// ==================系统信息==================

	/**
	 * 后台用户登录session
	 */
	public static String MANAGE_USER_SESSION = "manageUserSession";
	
	
	/**
	 * 跳转未登录页面
	 */
	public static String PRE_NOT_LOGIN_PAGE="/system/preNotLogin.jhtml";
	
	/**
	 * 跳转权限未分配角色页面
	 */
	public static String PRE_AUTHORITY_UNDISTRIBUTED_ROLE="/system/preUndistributeRole.jhtml";
	
	/**
	 * 跳转权限未验证通过页面
	 */
	public static String PRE_AUTHORITY_NO_AUTHORITY="/system/preNoAutority.jhtml";

	/**
	 * 登录过期或未登录返回json
	 */
	public static String LOING_EXPIRED_OR_NOT_LOGIN_JSON = "{\"result\":\"SESSION_TIME_OUT\"}";
	
	/**
	 * 权限验证未分配角色json
	 */
	public static String AUTHORITY_UNDISTRIBUTED_ROLE = "{\"result\":\"UNDISTRIBUTED_ROLE\"}";
	
	/**
	 * 权限验证未验证通过json
	 */
	public static String AUTHORITY_NO_AUTHORITY = "{\"result\":\"NO_AUTHORITY\"}";
	
	
	/**
	 * 默认密码
	 */
	public static String DEFAULT_PWD="111111";

	// ==================返回类型==================

	/**
	 * 成功
	 */
	public static String RESULT_SUCCESS = "success";

	/**
	 * 失败
	 */
	public static String RESULT_FAILURE = "failure";

	/**
	 * 错误
	 */
	public static String RESULT_ERROR = "error";
	
	/**
	 * 未登录
	 */
	public static String RESULT_NOT_LOGIN="notLogin";
	
	/**
	 * 登录失效
	 */
	public static String RESULT_LOGIN_INVALID="loginInvalid";
	
	/**
	 * 未知返回
	 */
	public static String RESULT_UNKNOWN="resultUnknown";

	// ==================流程状态==================

	/**
	 * 保存状态
	 */
	public static String SAVE_STATUS = "1";

	/**
	 * 提交审批状态
	 */
	public static String SUBMIT_STATUS = "2";

	/**
	 * 审批完成状态
	 */
	public static String COMPLETE_STATUS = "2";

	/**
	 * 驳回状态
	 */
	public static String REJECT_STATUS = "2";

}
