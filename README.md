# open-capacity-platform 微服务能力开发平台 
 
简称ocp是基于layui+springcloud的企业级微服务框架(用户权限管理，配置中心管理，应用管理，....),其核心的设计目标是分离前后端，快速开发部署，学习简单，功能强大，提供快速接入核心接口能力，其目标是帮助企业搭建一套类似百度能力开放平台的框架；


### 欢迎进群（群内领资料）

`一键加群`

<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5JSjd5D"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="open-capacity-platform交流" title="open-capacity-platform交流"></a>

![](https://i.imgur.com/kxpc628.png) 

##   技术介绍 
![](https://i.imgur.com/29QKUkG.png)  
##   框架设计
![](https://i.imgur.com/vn03vIX.jpg)

## 开发环境  
redis3.X  
jdk1.8  
MySQL Server 5.6  
maven3.3.9  
sts-3.8.0.RELEASE  

##  框架使用资料   
链接：https://pan.baidu.com/s/10Kae9_YotU5GnneaCk_p5Q 
密码：xqjb


##  项目地址
http://59.110.164.254:8066/login.html 用户名/密码：admin/admin

##  ocp项目演示
 
![](http://img1.ph.126.net/WAraEeweVw2SyTUSG1dT6Q==/3887169428474612491.gif) 


## 阿波罗配置中心演示  
![](http://img2.ph.126.net/-cKtj6Wia_q6YiZKV-IOsQ==/295548725646480248.gif)


## oauth认证方式    
![](https://i.imgur.com/MUCa4x6.gif)
## oauth单点登录   
![](https://i.imgur.com/PwcuvoC.gif)

## 项目组织结构分析 

 ![](https://i.imgur.com/EMvYmyr.png)




## 一. open-capacity-platform能力开放平台管理    
   
01.用户登录
![](https://i.imgur.com/4PKB9CF.png)

02.用户管理
![](https://i.imgur.com/j4ThxL5.png)

03.角色管理

![](https://i.imgur.com/IrtLUDg.png)

04.菜单管理
![](https://i.imgur.com/j269pA8.png)


05.权限管理

![](https://i.imgur.com/s6l27rb.png)


06.注册中心   
 ![](https://i.imgur.com/zwaKg01.png)



07.服务管理

![](https://i.imgur.com/2ufms9u.png)

08.应用管理  
![](https://i.imgur.com/LC7UI5X.png)


09.token管理

![](https://i.imgur.com/Pg80eh1.png)





## 部署 

1.下载代码

```
 git clone  https://gitee.com/owenwangwen/open-capacity-platform.git
```

2.启动对应的服务

a.先启动 register-center 注册中心的 eureka-server 注册服务

b.在启动 api-gateway 网关服务

c.再启动 oauth-center 认证中心 oauth-server 认证服务

d.在启动 business-center 业务中心的 对应服务 file-center user-center back-center

e.启动 monitor-center 监控中心 admin-server zipkin-center


  

灰度发布功能演示  
军哥的项目【[https://github.com/Nepxion/Discovery](https://github.com/Nepxion/Discovery):4.8.0-RC1】  
a.先启动 register-center 注册中心的 eureka-server 注册服务  
b.启动gray-center的zuul-server  
c.启动gray-center中的original-service的【DiscoveryApplicationA1】，【DiscoveryApplicationA2】，【DiscoveryApplicationB1】，【DiscoveryApplicationB2】，【DiscoveryApplicationC1】，【DiscoveryApplicationC2】，【DiscoveryApplicationC3】  
d.启动gray-center的discovery-console  
e.启动gray-center的discovery-console-desktop  

 
灰度管理UI  
用户名:admin      
密码  :admin  
![](https://i.imgur.com/QINO2jZ.png)

页面   
![](https://i.imgur.com/o4Lifbi.png)

基于版本的灰度发布
![](https://i.imgur.com/nWEwwqv.png)
![](https://i.imgur.com/7GeY6ws.png)
![](https://i.imgur.com/jvLTe0N.png)
![](https://i.imgur.com/LfrJKQH.png)

基于规则的灰度发布  
【待续】

请参考
[https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md](https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md)，感谢军哥分享  

 