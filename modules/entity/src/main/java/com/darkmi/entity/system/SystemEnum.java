package com.darkmi.entity.system;

public enum SystemEnum {
	CMS, RTM, APM, ODM, RM, SM, PPS, ENS, NVS, CS, PS, SYSTEM, REPORT;
	public String getLabel() {

		switch (this) {
		case CMS:
			return "内容管理";
		case RTM:
			return "时移管理";
		case APM:
			return "注入管理";
		case ODM:
			return "点播管理";
		case RM:
			return "资料管理";
		case SM:
			return "会话管理";
		case PPS:
			return "页面发布";
		case ENS:
			return "授权认证";
		case NVS:
			return "页面导航";
		case CS:
			return "配置流";
		case PS:
			return "回传服务";
		case SYSTEM:
			return "系统";
		case REPORT:
			return "报表";
		}

		return super.toString();
	}
}
