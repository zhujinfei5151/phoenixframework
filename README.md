# phoenixframework<br>
phoenixframe是一个自动化测试平台，集代码托管，分机（node节点）管理，定时任务，<br>
分布式或并发等方式执行通过phoenix_develop模块调试好的用例。<br>
平台使用SSH4开发，覆盖了webgui，接口，移动mobile等终端的自动化测试与监控。<br>
目前webGUI模块已经完成，兼容chrome，Firefox，IE以及phantomjs驱动。<br>
平台通过phoenix_develop模块在客户端开发及调试代码，然后通过将代码托管到phoenix_web控制端，<br>
控制端通过指派多个phoenix_node端方式执行测试用例。通过使用phoenix_develop开发用例代码的示例，<br>
用例如果在本地调试时没有问题，那么就可以放到控制端进行执行了。<br>
平台网站：http://www.cewan.la，中文搜：测完啦<br>
<br>
最新版本：1.2.7<br>
更新内容：<br>
1、phoenix_webdriver增加链式编程<br>
2、phoenix_webdriver日志中增加enginetype<br>
3、phoenix_webdriver完善chrome，Firefox浏览器支持<br>
<br>
系统名称：自动化测试平台<br> 
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
