﻿//alert( document.documentElement.scrollHeight - 20);
document.write('<!-- 用来产生编辑状态的ActiveX控件的JS脚本-->   ');
document.write('<!-- 因为微软的ActiveX新机制，需要一个外部引入的js-->   ');
document.write('<object id="TANGER_OCX" classid="clsid:A39F1330-3322-4a1d-9BF0-0BA2BB90E970"    ');
//home
//document.write('codebase="officecontrol/OfficeControl.cab#version=5,0,1,1" width="100%" height="578px">   ');
document.write('codebase="officecontrol/OfficeControl.cab#version=5,0,1,6" width="100%" height="'+($(window).height()-33)+'px">   ');
//work
//document.write('codebase="officecontrol/OfficeControl.cab#version=5,0,1,2" width="100%" height="700px">   ');
//document.write('codebase="officecontrol/OfficeControl.cab#version=5,0,1,2" width="100%" height="'+($(window).height()-33)+'px">   ');
document.write('<param name="IsUseUTF8URL" value="-1">   ');
document.write('<param name="IsUseUTF8Data" value="-1">   ');
document.write('<param name="BorderStyle" value="1">   ');
document.write('<param name="BorderColor" value="14402205">   ');
document.write('<param name="TitlebarColor" value="15658734">   ');
document.write('<param name="TitlebarTextColor" value="0">   ');
document.write('<param name="MenubarColor" value="14402205">   ');
document.write('<param name="MenuButtonColor" VALUE="16180947">   ');
document.write('<param name="MenuBarStyle" value="3">   ');
document.write('<param name="MenuButtonStyle" value="7">   ');
document.write('<param name="WebUserName" value="NTKO">   ');
document.write('<param name="Caption" value="NTKO OFFICE文档控件示例演示 http://www.ntko.com">   ');
document.write('<SPAN STYLE="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</SPAN>   ');
document.write('</object>');