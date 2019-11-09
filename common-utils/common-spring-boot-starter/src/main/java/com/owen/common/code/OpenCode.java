package com.owen.common.code;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slf4j.LoggerFactory;


/**
 * 错误编码
 *
 * 实现带有构造器的枚举
 *
 *
 */
public enum OpenCode implements  Code{
	// 通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
	// 赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错

	SUCCESS("0000", "成功"),
	ERROR("1000","错误"),
	ERROR_BADREQUEST("0001", "错误请求"),
	ERROR_SN("0003", "终端号错误"),
	ERROR_RANDOM("0004","随机数错误"),
	ERROR_BADCMD("0005","cmd错误"),
	ERROR_DATABASE("0006","数据库错误"),
	ERROR_DBTOOMANYCONN("0007","数据库连接数过多"),
	ERROR_LOADKEY("0008", "加载秘钥错误"),

	ERROR_DECRYPT("0009", "解密错误"),
	ERROR_PARAM_TYPE("0010","参数类型错误"),
	ERROR_BADJSON("0011","json解析错误"),
	ERROR_SIGN("0012","签名错误"),
	ERROR_STATUS("0013","状态错误"),
	ERROR_NOFILE("0014", "无此文件"),
	ERROR_UPLOADFILE("0015", "非法上传文件"),
	ERROR_BADCHAR("0016", "非法字符"),
	ERROR_PARAM("0017", "参数错误"),
	ERROR_OPENID("0018", "OPENID获取失败"),
	ERROR_FORBIDDEN("0019", "禁止访问"),
	//ERROR_NODOWNLOADER("0020", "无下载服务器"),
	ERROR_DOWNLOADERBUSY("0020", "当前下载负荷太大，请稍后重试"),
	ERROR_DOWNLOADERSETUP("0021", "下载服务器未配置"),
	ERROR_LICENSEEXPIRED("0022", "license已过期"),
	ERROR_REGCODE("0023", "无效注册码"),

	ERROR_SYSTEM("2001", "系统错误"),

	//public static int errcode_load_key=8;  //加载秘钥
	ERROR_CANDO("8000", "没有权限"),
	ERROR_PASSWORD("8002", "用户名或密码错误"),
	ERROR_TOKEN("8003", "token失效"),

	ERROR_PASSFORMAT("8004", "密码必须包含字母和数字"),
	ERROR_SESSION("8005", "session失效"),

	ERROR_RESPONSEISNULL("9001", "接口返回为空"),
	ERROR_RESPONSE503("9503", "接口暂时无法访问"),
	ERROR_COMMUNICATE("9999", "接口超时"),
	ERROR_UNKNOW("0099", "未知错误"),
	ERROR_DATA_DUPLICATION("0024", "数据重复"),

	ERROR_PARAM_NULL("4000","参数为空"),
	ERROR_ROLENAME("4001","角色名重复"),
	ERROR_ROLECODE("4002","角色编号重复"),
	ERROR_ROLECODE_NOCHANGE("4003","角色编号不能更改"),
	ERROR_MODULENAME("4004","模块名重复"),
	ERROR_MODULECODE("4005","模块编号重复"),
	ERROR_DATAOPERATION("4006","数据操作失败"),
	ERROR_PERMISSIONNAME("4007","权限名重复"),
	ERROR_PERMISSIONCODE("4008","权限编号重复"),
	ERROR_NOTEXIT("4009","对象不存在"),
	ERROR_MODULECODE_NOCHANGE("4010","模块编号不能更改"),
	ERROR_ROLECODE_MATCH("4011","角色编号不是字母数字组合"),
	ERROR_APPLYSETEXIT("4050","该机构或厂商或用户已存在设置"),
	ERROR_APPLYSET_NULL("4051","该机构或厂商用户没有设置工作流，请先添加"),

	ERROR_DATE_IS_NULL("6018","日期不能为空"),
	ERROR_FILE_IS_NULL("6019","文件夹不存在"),
	ERROR_ROOT_IS_NULL("6020","根路径为空"),
	ERROR_LOG_IS_NULL("6021","日志为空"),
	ERROR_DATE("6022","日期格式化错误"),
	PAGE_SURPLUS("6023","当前页数超出最大页数"),
	PATH_OR_NAME_IS_NULL("6024","路径或者文件名为空"),
	INST_IS_NULL("6025","当前用户无所属机构或者厂商"),
	DATA_IS_NULL("6026","数据为空")
	;
	//ERROR_TOKEN8003=许可令牌失效
	private  final String msg;
	private  String add_msg=""; //额外错误信息
	private final String code;

	private static org.slf4j.Logger logger = LoggerFactory.getLogger(OpenCode.class);

	public Code[] codeValues(){
		return OpenCode.values();
	}
	// 构造器默认也只能是private, 从而保证构造函数只能在内部使用
	OpenCode(String code, String msg) {
		this.msg = msg;
		this.code = code;
		//logger.info("");
	}

	public OpenCode addMsg(String msg){

		this.add_msg=msg;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	@Override
	public String getJson() {
		return null;
	}

	@Override
	public String getJson(JSONObject obj) {
		return null;
	}

	public String getCode() {
		return code;
	}

	public String toString(){

		return this.getJson();
	}

	public static String success(Object obj) {
		JSONObject biz = new JSONObject();
		biz.put("respcode",OpenCode.SUCCESS.getCode());
		biz.put("respmsg",OpenCode.SUCCESS.getMsg());
		biz.put("data",obj);
		return JSON.toJSONStringWithDateFormat(biz,"yyyy-MM-dd HH:mm", SerializerFeature.WriteDateUseDateFormat);
	}
	public static String success() {
		JSONObject biz=new JSONObject();
		biz.put("respcode",OpenCode.SUCCESS.getCode());
		biz.put("respmsg",OpenCode.SUCCESS.getMsg());
		return biz.toJSONString();
	}

	public static JSONObject successObj() {
		JSONObject biz=new JSONObject();
		biz.put("respcode",OpenCode.SUCCESS.getCode());
		biz.put("respmsg",OpenCode.SUCCESS.getMsg());
		return biz;
	}
	public static JSONObject setSystemError(String msg) {
		JSONObject biz=new JSONObject();
		biz.put("respcode",OpenCode.ERROR_SYSTEM.getCode());
		biz.put("respmsg",OpenCode.ERROR_SYSTEM.getMsg());
		return biz;
	}
	public static JSONObject setSystemError() {
		JSONObject biz=new JSONObject();
		biz.put("respcode",OpenCode.ERROR_SYSTEM.getCode());
		biz.put("respmsg",OpenCode.ERROR_SYSTEM.getMsg());
		return biz;
	}

	public static String setError(String code,String msg) {
		JSONObject biz=new JSONObject();
		biz.put("respcode",code);
		biz.put("respmsg",msg);
		return biz.toJSONString();
	}
}