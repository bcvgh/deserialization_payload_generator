
 <h1 align="center">deserialization_payload_generator</h1>
	
## 项目简介：
一个简易的基于woodpecker框架开发的反序列化利用插件，支持多种方式生成序列化payload，如命令执行、java字节码加载、bcel字节码加载。

## 提示
### 目前支持的Gadget
`"URLDNS","CC1","CC2","CC3","CC4","CC5","CC6","CC7","CC8","CC9","CC11","CCK1","CCK2","CCK3","CCK4","CB1","CB1_183","Hibernate1","Hibernate2","C3P0URLClassLoader","C3P0ELInject","URLDNS","JRMPListener","JRMPClient"`

（以上Gadget构造脸均来自互联网的大师傅们分享）

另外项目也加入了一些特定系统的反序列化漏洞Gadget，如Hibernate_fr

### 保命Tip
目前项目中支持的Gadget可能不是很多，一部分是因为某些Gadget受Jdk版本限制无法兼容到项目中，另一部分纯粹是因为我还没有复现...

(^-^)
因为项目是我边学java反序列化边写的，可能对Gadget理解不是很深，如果代码实现有误，还请请喷（手动狗头）


## 使用
### 支持参数
```
Gadget                 使用的利用链
Type                   利用类型，支持command,bytecode,bcel
Command                命令
ResourcePath           需要加载的文件绝对路径
UrlClassLocation       远程恶意类地址
DnsLog                 dnslog地址
Ip                     Ip
Port                   端口
Zip                    压缩方式
Encode                 编码方式
LDAP/RMI               LDAP或RMI地址
OutPath                输出文件
```

### 使用简述
1.
设置参数生成payload后输出到指定文件后，burp paste from file即可（切勿直接复制粘贴）
<img width="992" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/8e5d0f39-70aa-4473-a271-f412cd967e5b">

<img width="1189" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/41af914a-3867-4aa9-9818-50644e38d343">

1.
也可配合JMG等其他内存马工具实现内存马注入
<img width="992" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/9970a15c-ce0e-4bd9-99cb-e8dcd9a14cef">
<img width="997" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/1a9c935b-b12b-4b35-a5d7-37834f695eff">

<img width="1288" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/89fa1693-55d0-4326-ae29-c169cba685ae">
<img width="692" alt="image" src="https://github.com/bcvgh/deserialization_payload_generator/assets/56790427/d5d355a8-321b-4547-ae25-88a96f2c7f4b">

