# phoenixframework
自动化测试平台，包含WEB、Mobile、接口、安全测试模块。通过对测试代码的动态编译，执行，进行测试。
网站：http://www.cewan.la

最新版本：1.2.6
更新内容：
1、phoenix_webdriver模块新增在代码中直接使用定位信息，而无需在先将定位信息录入数据库
2、phoenix_webdriver新增phantomjs驱动，即无浏览器界面执行，在js兼容性方面比httpunit好
3、phoenix_web修复无法新增用户bug
4、phoenix_web增加了定时任务时间配置说明
5、phoenix_webdriver修复2个小bug

系统名称：自动化测试平台 
系统介绍： <br>
【支持的部署方式】：J2EE，Jenkins，maven，J2SE，分布式部署，Jetty部署 <br>
【技术说明】：Apache quartz，Webmagic，httpunit，selendroid，<br>
selenide，Spring+SpringMVC+Hibernate4，Executor，Forkjoin，Maven项目管理，<br>
Bootstrap，JQuery，JDK动态编译+反射+执行，DWR，highchat <br>
【权限管理】：方法级别的权限控制 <br>
【覆盖系统类型】：WEB GUI自动化测试，接口自动化测试，Android/IOS app自动化测试，<br>
WEB GUI自动化监控，接口自动化监控，数据库测试，简单安全性测试 <br>
【消息通知】：Email异步发送，短信异步发送，在线日志检视，统计报表生成<br>
【模块介绍】<br>
phoenix_develop：用例代码开发模块<br>
phoenix_node:分布式执行node节点<br>
phoenix_web:平台控制端<br>
phoenix_webdriver:webGUI自动化测试模块<br>
phoenix_mobiledriver:移动设备测试模块<br>
phoenix_interface：接口测试系统<br>
phoenix_db:数据库操作模块，对hibernate4的封装<br>