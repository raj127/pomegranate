<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ page import="org.springside.modules.security.springsecurity.SpringSecurityUtils" %>
<div id="hd">
	<div id="title">
	<table class="noborder" width="100%" background="${ctx}/images/top_bg.jpg" >
	  <tr><td align="left" valign="top" height="69px" id="headtableBO">作业规程编制与管理系统 </td>
	<td align="right" class="headtable" id="headtable"><% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){%>您好, <%=SpringSecurityUtils.getCurrentUserName()%><%} %><% if(!"anonymousUser".equals(SpringSecurityUtils.getCurrentUserName())){%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${ctx}/images/editing.gif" border="0px" align="top" /><security:authorize ifAnyGranted="ROLE_修改密码"><a href="${ctx}/system/modify-password.action" id="n10"> 修改密码</a></security:authorize> &nbsp;<img src="${ctx}/images/exit.png" border="0px" align="top" /><a href="${ctx}/j_spring_security_logout" id="n11" onclick="return confirm('确定退出吗？')">退出登录</a>&nbsp;&nbsp;<%} %></td></tr></table>
	</div>
	<div class="header">
	<div class="padder">
		<div class="nav">
			<div class="navLaftBg">
				<div class="navRightBg">
					<div class="mainNav">
						<security:authorize ifAnyGranted="ROLE_浏览首页">
							<a href="${ctx}/index.action" id="n1" class="actived">首页</a>
						</security:authorize>
						<security:authorize ifAnyGranted="ROLE_浏览用户">
							<a href="${ctx}/system/user.action" id="n2">系统管理</a>
						</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览电影">
							<a href="${ctx}/task/task.action" id="n3">任务管理</a>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览录制列表">
							<a href="${ctx}/rtm/recording.action?state=NORMAL" id="n4">规程编制</a>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览元数据">
							<a href="${ctx}/apm/package-asset.action" id="n5">规程审批</a>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览OFFERING">
							<a href="${ctx}/odm/offering.action" id="n6">规程查询</a>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览授权管理">
							<a href="${ctx}/ens/customer.action" id="n7">规程快讯</a>
							</security:authorize>
							<security:authorize ifAnyGranted="ROLE_浏览视频服务器">
							<a href="${ctx}/rm/video-server.action" id="n8">在线帮助</a>
							</security:authorize>
					</div>
				</div>
			</div>
		</div>
		<div class="secondNav">
			<security:authorize ifAnyGranted="ROLE_浏览用户">
				<div id="subNav2" class="subNav2">
					<a id="subNav201" href="${ctx}/system/user.action" >用户列表</a>
					<a id="subNav202" href="${ctx}/system/role.action" >角色列表</a>
					<a id="subNav203" href="${ctx}/system/ftp.action" >FTP列表</a>
					<a id="subNav204" href="${ctx}/system/config.action" >配置列表</a>
					<a id="subNav205" href="${ctx}/system/log.action" >日志列表</a>
				</div>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_浏览电影">
					<div id="subNav3" class="subNav3">
						<a id="subNav301" href="${ctx}/task/task.action" >任务列表</a>
						<a id="subNav302" href="${ctx}/task/chapter.action" >查看目录</a>
						<a id="subNav303" href="${ctx}/cms/media-category.action" >查询任务</a>
					</div>
			</security:authorize>
				<security:authorize ifAnyGranted="ROLE_浏览录制列表">
				<div id="subNav4" class="subNav4">
					<a  id="subNav401" href="${ctx}/rtm/channel.action" >工资帐套管理</a>
					<a  id="subNav402" href="${ctx}/rtm/program.action" >人员帐套设置</a>
					<a  id="subNav403" href="${ctx}/rtm/recording.action?state=normal" >工资表管理</a>
					<a  id="subNav404" href="${ctx}/rtm/program-oplog.action" >工资表汇总</a>
					<a  id="subNav405" href="${ctx}/rtm/channel-upload.action" >工资表格式调整</a>
					<a  id="subNav406" href="${ctx}/rtm/program-history.action" >计时工资</a>
					<a  id="subNav407" href="${ctx}/rtm/encoder.action" >计件工资</a>
					<a  id="subNav408" href="${ctx}/rtm/file-state.action" >月末处理</a>
				</div>
	</security:authorize>
			<security:authorize ifAnyGranted="ROLE_浏览元数据">
			  <div id="subNav5" class="subNav5">
			     	<a id="subNav501" href="${ctx}/apm/package-asset.action" >操作员管理</a>
			     	<a id="subNav502" href="${ctx}/apm/movie-asset.action" >Movie Asset</a>
			     	<a id="subNav503" href="${ctx}/apm/title-asset.action" >Title Asset</a>
			     	<a id="subNav504" href="${ctx}/apm/preview-asset.action" >Preview Asset</a>
			     	<a id="subNav505" href="${ctx}/apm/still-image-asset.action" >海报</a>
		      </div>
		</security:authorize>
				<security:authorize ifAnyGranted="ROLE_浏览OFFERING">
			  <div id="subNav6" class="subNav6">
			     	<a id="subNav601" href="${ctx}/odm/provider.action" >供应商列表</a>
			     	<a id="subNav602" href="${ctx}/odm/product.action" >产品列表</a>
			     	<a id="subNav603" href="${ctx}/odm/service.action" >业务列表</a>
<!--			     	<a id="subNav604" href="${ctx}/odm/category.action" >分类列表</a>-->
<!--			     	<a id="subNav605" href="${ctx}/odm/asset-still-image.action" >海报列表</a>-->
			     	<a id="subNav606" href="${ctx}/odm/offering.action" >offering列表</a>
			     	<a id="subNav607" href="${ctx}/odm/offering-history.action" >offering历史列表</a>
		      </div>
			</security:authorize>
			<security:authorize ifAnyGranted="ROLE_浏览授权管理">
				<div id="subNav7" class="subNav7">
					<a id="subNav701" href="${ctx}/ens/customer.action" >客户列表</a>
					<a id="subNav702" href="${ctx}/ens/equipment.action" >设备列表</a>
					<a id="subNav703" href="${ctx}/ens/subscription.action" >订购列表</a>
					<a id="subNav704" href="${ctx}/ens/purchase.action" >购买列表</a>
				</div>
		</security:authorize>
			<security:authorize ifAnyGranted="ROLE_浏览视频服务器">
				<div id="subNav8" class="subNav8">
					<a id="subNav801" href="${ctx}/rm/video-server.action" >视频服务器</a>
					<a id="subNav802" href="${ctx}/rm/vsnic.action" >网卡</a>
					<a id="subNav803" href="${ctx}/rm/service-group.action" >ServiceGroup</a>
					<a id="subNav804" href="${ctx}/rm/ipqam.action" >ipqam</a>
					<a id="subNav805" href="${ctx}/rm/ts.action" >ts</a>
					<a id="subNav806" href="${ctx}/rm/page-server.action" >PageServer</a>
					<!-- a id="subNav806" href="${ctx}/rm/service-group-cache.action" >ServiceGroup队列</a> -->
				</div>
				</security:authorize>
				<security:authorize ifAnyGranted="ROLE_浏览SRM会话">
				<div id="subNav12" class="subNav12">
					<a id="subNav1201" href="${ctx}/sm/vod-asset.action" >元数据</a>
					<a id="subNav1202" href="${ctx}/sm/vod-customer.action" >客户列表</a>
					<a id="subNav1203" href="${ctx}/sm/buy-transaction.action" >购买列表</a>
<!--					<a id="subNav1207" href="${ctx}/sm/buy-transaction-history.action" >购买历史列表</a>-->
					<a id="subNav1204" href="${ctx}/sm/play-transaction.action" >点播列表</a>
<!--					<a id="subNav1208" href="${ctx}/sm/play-transaction-history.action" >点播历史列表</a>-->
					<a id="subNav1205" href="${ctx}/sm/session.action" >会话列表</a>
					<a id="subNav1206" href="${ctx}/sm/session-history.action" >会话历史</a>
<!--					<a id="subNav1209" href="${ctx}/sm/session-history-history.action" >会话历史的历史</a>-->
					<!-- a id="subNav1207" href="${ctx}/sm/setup-errors.action" >机顶盒错误</a> -->
				</div>
				</security:authorize>
				<security:authorize ifAnyGranted="ROLE_浏览页面发布">
				<div id="subNav9" class="subNav9">
				<table class="noborder">
					<tr align="left">
<!--					 	<td><a id="subNav911" href="${ctx}/pps/service-info.action" >业务列表</a></td>-->
					 	<td><a id="subNav914" href="${ctx}/pps/offering-info-movie.action" >电影列表</a></td>
						<td><a id="subNav915" href="${ctx}/pps/offering-info-tv.action" >电视剧列表</a></td>
						<td><a id="subNav916" href="${ctx}/pps/offering-info-ktv.action" >KTV列表</a></td>
						<td><a id="subNav913" href="${ctx}/pps/offering-still-images.action" >海报列表</a></td>
						<td><a id="subNav917" href="${ctx}/pps/offering-info-recommended.action" >推荐列表</a></td>
						<td><a id="subNav910" href="${ctx}/pps/bookmark-movie.action" >书签列表</a></td>
						<td><a id="subNav909" href="${ctx}/pps/favorite.action" >最近浏览</a></td>
						<td><a id="subNav908" href="${ctx}/pps/channel-info.action" >频道列表</a></td>
						<td><a id="subNav912" href="${ctx}/pps/program-info.action" >节目列表</a></td>
						<td><a id="subNav920" href="${ctx}/pps/singer.action" >歌手管理</a></td>
						<td><a id="subNav921" href="${ctx}/pps/iphone-menu.action" >菜单管理</a></td>
					</tr>
<!--					<tr align="left">-->
<!--						<td><a id="subNav904" href="${ctx}/pps/customer-info.action"" >客户列表</a></td>-->
<!--						<td><a id="subNav905" href="${ctx}/pps/style-info.action" >风格列表</a></td>-->
<!--						<td><a id="subNav906" href="${ctx}/pps/group-info.action" >分组列表</a></td>-->
<!--						<td><a id="subNav907" href="${ctx}/pps/templates-info.action" >模板列表</a></td>-->
<!--						<td><a id="subNav919" href="${ctx}/pps/image-upload.action" >上传图片</a></td>-->
<!--					</tr>-->
				</table>
				</div>
			</security:authorize>
			 <security:authorize ifAnyGranted="ROLE_浏览报表">
				<div id="subNav10" class="subNav10">
					<a id="subNav1001" href="${ctx}/report/index.action" >全部点播</a>
				</div>
			</security:authorize>
			 <security:authorize ifAnyGranted="ROLE_回传服务">
				<div id="subNav14" class="subNav14">
					<a id="subNav1401" href="${ctx}/ps/phone-customer.action" >客户列表</a>
					<a id="subNav1402" href="${ctx}/ps/srm-session.action" >视频流会话列表</a>
					<a id="subNav1403" href="${ctx}/ps/srm-session-history.action" >视频流会话历史</a>
<!--					<a id="subNav1406" href="${ctx}/ps/srm-session-history-history.action" >视频流会话历史的历史</a>-->
					<a id="subNav1404" href="${ctx}/ps/ps-session.action" >页面流会话列表</a>
					<a id="subNav1405" href="${ctx}/ps/ps-session-history.action" >页面流会话历史</a>
<!--					<a id="subNav1407" href="${ctx}/ps/ps-session-history-history.action" >页面流会话历史的历史</a>-->
				</div>
				</security:authorize>
		</div>
	</div>
</div>
	
</div>