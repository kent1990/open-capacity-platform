# open-capacity-platform 微服务能力开发平台 
 
简称ocp是基于layui+springcloud的企业级微服务框架(用户权限管理，配置中心管理，应用管理，....),其核心的设计目标是分离前后端，快速开发部署，学习简单，功能强大，提供快速接入核心接口能力，其目标是帮助企业搭建一套类似百度能力开放平台的框架；


### 欢迎进群（群内领资料）

`一键加群`
<a target="_blank" href="https://jq.qq.com/?_wv=1027&k=5JSjd5D"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="open-capacity-platform交流" title="open-capacity-platform交流"></a>

![1群](https://images.gitee.com/uploads/images/2019/0110/231914_3b9c6b66_1441068.png) 
![2群](https://images.gitee.com/uploads/images/2019/0126/130006_e6933516_1147840.png)

##  项目地址
http://59.110.164.254:8066/login.html 用户名/密码：admin/admin

##   技术介绍 
 ![](https://i.imgur.com/Ncp3Esz.png)
 
## 开发环境  
redis3.X  
jdk1.8  
MySQL Server 5.6  
maven3.3.9  
sts-3.8.0.RELEASE  

## 技术文档   ##
[正式文档](https://www.kancloud.cn/owenwangwen/open-capacity-platform)  

## 项目组织结构分析  
![](https://i.imgur.com/GEmNF0q.png)



## 一. open-capacity-platform能力开放平台管理    
   
01.用户登录
![](https://images.gitee.com/uploads/images/2019/0110/231915_3ca22dbe_1441068.png)

02.用户管理
![](https://images.gitee.com/uploads/images/2019/0110/231916_74fcbc85_1441068.png)

03.角色管理

![](https://images.gitee.com/uploads/images/2019/0110/231918_c10b5124_1441068.png)

04.菜单管理
![](https://images.gitee.com/uploads/images/2019/0110/231924_0ab3f997_1441068.png)


05.权限管理

![](https://images.gitee.com/uploads/images/2019/0110/231923_4e42ff5d_1441068.png)


06.注册中心   
 ![](https://images.gitee.com/uploads/images/2019/0110/231926_e8da388c_1441068.png)


07.配置中心  
![](https://images.gitee.com/uploads/images/2019/0110/231927_a081ed4b_1441068.png)  
![](https://images.gitee.com/uploads/images/2019/0126/125449_9b960f05_1147840.png)  


08.服务管理

![](https://images.gitee.com/uploads/images/2019/0126/125449_baa02383_1147840.png)

09.应用管理  
![](https://images.gitee.com/uploads/images/2019/0110/231932_6e2ce5f5_1441068.png)


10.token管理

![](https://images.gitee.com/uploads/images/2019/0126/125449_7a3dec37_1147840.png)

11.日志中心  
![](https://images.gitee.com/uploads/images/2019/0126/125450_75ac20ef_1147840.png)  

11.prometheus监控  
![](https://i.imgur.com/8GAR0MR.png)  

12.spring boot admin 监控  
![](https://i.imgur.com/stvC8OU.png)
![](https://i.imgur.com/S7f655G.png)




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

ocp灰度发布功能(参考dev分支)    
![](https://images.gitee.com/uploads/images/2019/0126/125450_b42073c5_1147840.png)  
![](https://images.gitee.com/uploads/images/2019/0126/125450_66e3a8db_1147840.png)  
![](https://images.gitee.com/uploads/images/2019/0126/125451_28b1bc41_1147840.png)  
  
 
灰度管理UI  
用户名:admin      
密码  :admin  
![](https://images.gitee.com/uploads/images/2019/0126/125451_c3b6224d_1147840.png)

页面   
![](https://images.gitee.com/uploads/images/2019/0126/125452_3164c04c_1147840.png)

基于版本的灰度发布
![](https://images.gitee.com/uploads/images/2019/0126/125452_4b935973_1147840.png)
![](https://images.gitee.com/uploads/images/2019/0126/125452_565e9022_1147840.png)
![](https://images.gitee.com/uploads/images/2019/0126/125452_3f970930_1147840.png)
![](https://images.gitee.com/uploads/images/2019/0126/125453_79912ce1_1147840.png)

基于规则的灰度发布  
【待续】

请参考
[https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md](https://github.com/Nepxion/Docs/blob/master/discovery-doc/README_QUICK_START.md)，感谢军哥分享  

 
容器化部署  

主机管理  
![](https://images.gitee.com/uploads/images/2019/0126/125453_6682dba8_1147840.png)
ocp应用平台    
 ![](https://images.gitee.com/uploads/images/2019/0126/125453_3831567a_1147840.png)      

注册中心    
![](https://images.gitee.com/uploads/images/2019/0126/125454_b04fbc0d_1147840.png)    
![](https://images.gitee.com/uploads/images/2019/0126/125454_1f9ce4e8_1147840.png)  
![](https://images.gitee.com/uploads/images/2019/0126/125454_3ece0005_1147840.png)     


用户中心    
![](https://images.gitee.com/uploads/images/2019/0126/125454_272e0e79_1147840.png)    
![](https://images.gitee.com/uploads/images/2019/0126/125455_0f0278dd_1147840.png)   
 

认证中心   
![](https://images.gitee.com/uploads/images/2019/0126/125455_05a5b463_1147840.png)    
![](https://images.gitee.com/uploads/images/2019/0126/125455_4827ecff_1147840.png)  

api网关   
![](https://images.gitee.com/uploads/images/2019/0126/125456_7cf25a83_1147840.png)  
![](https://images.gitee.com/uploads/images/2019/0126/125456_bbac1fb9_1147840.png)

负载均衡器  
![](https://images.gitee.com/uploads/images/2019/0126/125456_5c697b5f_1147840.png)


后台中心  
![](https://images.gitee.com/uploads/images/2019/0126/125456_e21d4fb0_1147840.png)
![](https://images.gitee.com/uploads/images/2019/0126/125456_18382021_1147840.jpeg)

资源消耗情况
![](https://images.gitee.com/uploads/images/2019/0126/125457_397161e8_1147840.png)

链路跟踪
![](https://i.imgur.com/S3RvjFc.png)
![](https://i.imgur.com/1VFd9vx.png)
![](https://i.imgur.com/03Yq5ov.png)
![](https://i.imgur.com/drPeW4E.png)