@echo off
echo [INFO] ȷ��Ĭ��JDK�汾ΪJDK6.0�����ϰ汾,������JAVA_HOME.

echo [INFO] �粻������Maven�ٷ���վ, �޸ı��ļ�ȥ������һ�е�ע��.
rem set OFF_LINE=-o

set MVN=mvn
set ANT=ant
set MAVEN_OPTS=%MAVEN_OPTS% -XX:MaxPermSize=512m

if exist "tools\maven\apache-maven-2.2.1\" set MVN="%cd%\tools\maven\apache-maven-2.2.1\bin\mvn.bat"
if exist "tools\ant\apache-ant-1.8.1\" set ANT="%cd%\tools\ant\apache-ant-1.8.1\bin\ant.bat"
echo Maven����Ϊ%MVN%
echo Ant����Ϊ%ANT%

echo [Step 2] ��װVODLite2.0 ����modules��Ŀ������Maven�ֿ�, ����Eclipse��Ŀ�ļ�.
call %MVN% %OFF_LINE% clean install -Dmaven.test.skip=true
#call %MVN% %OFF_LINE% eclipse:clean eclipse:eclipse

:end
pause