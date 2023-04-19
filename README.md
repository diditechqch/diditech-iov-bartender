# 轻车车联网网关 bartender
![轻车](https://foruda.gitee.com/images/1680483823082728332/1c74535d_12581006.png "logo.png")<br/>
不仅仅是TCP/UDP网关。轻车车联网基于Spring Boot 2.x技术，将硬件接口的复杂对接转化为简单易用的接口和消息推送方式，具备设备管理、数据分析和消息推送等多种能力，可广泛应用于各种车辆监管场景和应用平台。

bartender作为本网关的接口服务，能够为车辆联网监管和部标标准的实现提供强有力的支持，助力车辆监管的智能化和规范化。

## 已开源到Gitee，下载更快
https://gitee.com/diditech/diditech-iov-bartender

## 接口已发布Apifox APIHub，在线调试更方便
[轻车车联网Apifox](https://didi-qingche.apifox.cn)
![在线调试](https://foruda.gitee.com/images/1680576144476934870/17d76ef1_7756143.png "微信截图_20230404103744.png")

## 软件架构
![轻车车联网网关架构](https://foruda.gitee.com/images/1678935051078983063/ee806583_12581006.png "轻车车联网网关 (1).png")

## 产品线路图
![轻车车辆网网关线路图](https://foruda.gitee.com/images/1679983468307442169/dc27da59_7756143.png "轻车网关 产品线路图.png")

## 系统配置要求
| 配置项 |规格  |
| --- | --- |
| CPU内存 | 1w台设备以内2核4G |
| 硬盘 | 每100台设备1年20G |
| 操作系统 | Linux |

## 支持设备类型

| 设备类型 |  协议 |
| --- | --- |
| 康凯斯有线/OBD接电设备  | 康凯斯私有协议 |
| 奇果OBD设备| 奇果私有协议|
| 零一OBD设备| 零一私有协议|
| 车葫芦OBD设备| 车葫芦私有协议|
| 车易控行车记录仪| JT808部标协议|
| 合正行车记录仪| 合正私有协议|
| 新源润无线设备| 新源润私有协议|
| ADAS设备| JT1078部标协议|

## 支持报警类型

| 类别 | 报警项 |
| --- | --- |
| 设备自带报警 | 电瓶拆除报警、非法拆除报警、震动报警、<br>风险地点报警、防劫持报警、开盖报警、<br>位移报警、pseudo base station报警<br>异常驾驶行为-疲劳/打电话/抽烟 |
| 平台判断报警 | 超速报警、进敏感区域报警、出敏感区域报警、<br>掉线报警、停车超时报警、分离报警、<br>出省报警、敏感区域停留报警、未进常用区域超时报警、<br>原地设防报警、进电力围栏报警、<br>出电子围栏报警、围栏内报警、围栏内停车超时报警 |

## 名词说明

| 概念 | 定义 |
| --- | --- |
| 报文 | 硬件上行数据，例如定位、心跳、报警、OBD等信息。 |
| 服务器时间 | 平台接收报文时间。 |
| 定位时间 | 报文中GPS定位时间，即硬件使用自身GPS模块获得的GPS定位时间。 |
| 在线/离线状态 | 平台收到硬件最后一条报文10分钟后，更新设备状态为离线。上报报文期间为在线。 |
| 停车/行驶状态 | 时速大于5视为行驶，否则停车。离线时设备速度为0。 |
| 里程 | 根据上报定位点，按GPS时间排序，累加定位点间距获得。 |
| 停留点 | 从开始停车到行驶的一段时间视为停留点，停留时长最少10分钟。 |
| 轨迹 | 将历史定位数据按定位时间排序绘制的路线。 |
| 轨迹分段 | 将轨迹分隔为多个行程段。分隔条件默认为，点间距超过10米，定位时间间距超过15分钟。 |

# 安装说明
下载 **docker-compose.yml** 以及 **lvn_bootstrap.sh**，如果需要自定义可以使用文本编辑器对其中的参数进行自定义，两个文件放置在相同目录。

## **快捷安装**
直接运行lvn_bootstrap.sh，适用于新服务器直接部署运行，会自动安装相关依赖的环境如docker、docker-compose

## 自定义安装
如已安装对应环境，可跳过该步骤，顺序执行下一个

### 安装docker
```
sudo curl -fsSL https://get.docker.com | bash -s docker --mirror Aliyun
```

### 安装docker-compose
```
sudo curl -L https://get.daocloud.io/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m) >/usr/local/bin/docker-compose
chmod +x /usr/local/bin/docker-compose
```

### 添加项目私库地址
```
echo '{ "insecure-registries": [ "123.234.31.106" ],  "registry-mirrors": [ "https://docker.mirrors.ustc.edu.cn", "https://registry.docker-cn.com" ] }' >>/etc/docker/daemon.json
```

### 启动docker
```
systemctl start docker
systemctl enable docker.service
```

### 定义环境变量
```
export \
  LVN_DEPLOY_PATH=/user/local/lvn/ \
  LVN_CLIENT_ID=yiqiyongche \
  LVN_CLIENT_SECRET='$2a$10$FsEwMOG57LvYUzZMehWnperN1nXnWXZLLWM516HFXn7rMPEoS4Da.' \
  LVN_APP_CODE=03pf80c2bae96vc49b80b917bea776d7123123asfasfas
```

- `LVN_DEPLOY_PATH`: 项目日志以及持续化文件保存的地方
- `LVN_CLIENT_ID`、`LVN_CLIENT_SECRET`、`LVN_APP_CODE`：填写项目授权或试用时分发的KEY、CODE

### 使用账号密码登录docker私库
```
docker login -u admin -p Harbor12345 http://123.234.31.106
```

### 启动
```
docker-compose up -d
```

## 定义声明
### 端口定义
- 9000，网关服务端口
- 9010，授权服务端口
- 8012，应用服务端口
- 7005，告警处理服务端口
- 12061，部标协议TCP端口
- 3306，Mysql数据库端口
- 6379，Mysql数据库端口

### 环境变量

|  环境变量   | 作用  | 是否必填| 示例 |
|  ----  | ----  | ----  |----  |
| LVN_DEPLOY_PATH  | 部署目录，日志以及持久化文件会存储在此目录 | /user/local/lvn/ |
| LVN_CLIENT_ID  | 终端ID | 必填|yiqiyongche |
| LVN_CLIENT_SECRET  | 终端秘钥 | 必填| xxxxxx |
| LVN_BAIDU_GEO_KEY | 百度逆地理编码key |  必填|xxxxxxx,xxxxx |
| LVN_GAODE_WIFI_KEY | 高德智能定位解析key |  非必填|xxxxxxx,xxxxx |
| LVN_GAODE_LBS_KEY | 高德lbs解析key | 非必填|xxxxxxx,xxxxx |

** 本项目逆地理编码、基站解析、热点解析调用两大地图商（百度和高德）的api，调用秘钥（key）需按调用量需求申请对应的配额，代码内默认使用了个人版key，不支持生产环境。
https://lbsyun.baidu.com/apiconsole/center#/home
https://console.amap.com/dev/key/app

# 使用说明

## 1.  API鉴权
初次使用时我们会提供调用终端的鉴权信息`client_id`和`client_secret`，调用鉴权接口可获取凭证`access_token`，之后便可开始使用其他业务API接口。
![输入图片说明](https://foruda.gitee.com/images/1680225808321603597/ac786055_12581006.png "20a04c54-9e0e-49e4-a1e5-bcbede3e4b06.png")

鉴权接口 POST /oauth/token 


此接口返回的token_type和access_token使用`' '`（空格）连接后，在请求header中携带调用业务接口。


> 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|grant_type|query|string| 是 |固定参数|
|client_id|query|string| 是 |客户端ID|
|client_secret|query|string| 是 |客户端密钥

> 成功返回示例

```json
{
  "access_token": "this_is_an_example_access_token",
  "token_type": "bearer",
  "expires_in": 40528,
  "scope": "all",
  "jti": "483fb3cf-e63b-415d-93bd-bdd4f12a8213"
}
```

>返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

> 返回数据结构

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» access_token|string|true|none|鉴权token|none|
|» token_type|string|true|none|鉴权类型|none|
|» expires_in|integer|true|none|多少秒后token过期|none|

## 2.  设备接入与消息接收

系统搭建完成后，若使用第三方硬件，需将硬件配置到对应协议的TCP(UDP)上线端口，若使用我司设备，可联系客服配置。
如图所示，首先添加设备以确保设备上线，若需接收设备实时定位和告警，需开启事件推送。
![输入图片说明](https://foruda.gitee.com/images/1680225820560337209/463187b9_12581006.png "2.png")


### 设备-添加接口 GET /devices/syn


支持相同型号批量添加，使用默认参数，可绕过鉴权。

> 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|clientId|query|string| 是 |客户端ID|
|categoryNo|query|string| 是 |设备型号，长名称|
|deviceNums|query|string| 是 |设备号，批量使用逗号间隔|

> 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

### 设备-批量启停推送接口 GET /devices/batch

若设备导入时没有指定启用状态，则默认启用，不需要批量启用。若导入时指定设备初始状态为停用，则需要使用该接口控制，停用时，不推送定位状态、事件信息。

> 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|devices|query|string| 是 |需要启停的设备编号，多个时用逗号间隔|
|isEnable|query|string| 是 |0：启用 1：停用|

> 返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

3.  验证设备上线
### 设备位置-运维查看设备位置状态接口 GET /opt/location

运维使用查询设备最新定位状态，当设备号不存在时提示“设备号不存在”

> 请求参数

|名称|位置|类型|必选|说明|
|---|---|---|---|---|
|deviceNum|query|string| 是 |none|

> 成功返回示例

```json
{
  "code": 200,
  "data": {
    "categoryNo": "DO90",
    "categoryNoShort": "DO90",
    "createTime": "2022-07-27 05:12:00",
    "deviceNum": "868120250375836",
    "direction": 28,
    "directionName": "东北",
    "gpsTime": "2022-07-26 16:14:49",
    "isMoving": 0,
    "latBd": 29.116056,
    "latGc": 29.109734,
    "lngBd": 119.654767,
    "lngGc": 119.648356,
    "locMode": 9,
    "satCount": 15,
    "speed": 0,
    "startTimeMovingStop": "2022-06-23 07:45:53",
    "startTimeOnOffLine": "2022-07-27 05:12:00",
    "wifiFlag": 1
  },
  "message": "",
  "success": true
}
```

>返回结果

|状态码|状态码含义|说明|数据模型|
|---|---|---|---|
|200|[OK](https://tools.ietf.org/html/rfc7231#section-6.3.1)|成功|Inline|

>返回数据结构

|名称|类型|必选|约束|中文名|说明|
|---|---|---|---|---|---|
|» code|integer|true|none||none|
|» data|object|true|none||none|
|»» categoryNo|string|true|none|设备类型|none|
|»» categoryNoShort|string|true|none|设备类型短名|none|
|»» createTime|string|true|none|创建时间|none|
|»» deviceNum|string|true|none|设备号|none|
|»» direction|integer|true|none|方向|none|
|»» directionName|string|true|none|方向描述|none|
|»» gpsTime|string|true|none|定位时间|none|
|»» isMoving|integer|true|none|行驶中状态 行驶：1；停车：0|none|
|»» latBd|number|true|none|百度坐标纬度|none|
|»» latGc|number|true|none|高德坐标纬度|none|
|»» lngBd|number|true|none|百度坐标经度|none|
|»» lngGc|number|true|none|高德坐标经度|none|
|»» locMode|integer|true|none|定位方式 0-GPS；1-LBS单基站；2-LBS多基站；5-WIFI；9-不定位|none|
|»» satCount|integer|true|none|卫星数 卫星个数|none|
|»» speed|integer|true|none|速度|none|
|»» startTimeMovingStop|string|true|none|行驶/停车 + 起始时刻 年月日 时分秒|none|
|»» startTimeOnOffLine|string|true|none|在线/离线起始时刻 年月日 时分秒|none|
|»» wifiFlag|integer|true|none|设备类型 1：有线|none|
|» message|string|true|none||none|
|» success|boolean|true|none||none|

# 常见问题

## 设备在线总数和各端口在线数

可根据各端口实时在线数量粗略判断端口健康状态，当某端口车辆较少或当前时间大部分车辆在停车状态，则不足以判断端口健康情况。可参考以下SQL query查询在线数量。

```sql

SELECT COUNT(p.ALM_OFFLINE = 0 AND (p.ISSLEEPING=0 OR p.ISSLEEPING IS NULL) OR NULL) AS '在线总数' 
FROM dd_new.position p;

SELECT p.TAG as '端口',
COUNT(p.ALM_OFFLINE = 0 AND (p.ISSLEEPING=0 OR p.ISSLEEPING IS NULL) OR NULL) AS '在线数',
COUNT(*) AS '累计上线数量',
MAX(p.CREATE_TIME) AS '最新服务时间',
NOW() AS '当前时间',
IF(TIMESTAMPDIFF(SECOND,MAX(p.CREATE_TIME),NOW()) <= SECOND(3), '正常', 
IF(COUNT(p.ALM_OFFLINE = 0 AND (p.ISSLEEPING=0 OR p.ISSLEEPING IS NULL) OR NULL) < 100, '设备较少', '异常')) AS '端口健康' -- 该阈值需根据活跃车辆数进行调整
FROM dd_new.position p
GROUP BY p.TAG
ORDER BY MAX(p.CREATE_TIME);

```


## 设备不上线问题
[定位更新回调](api-61168305)接收不到该设备数据，或超过30分钟没有新数据。

运维建议：
1. 确认设备是否已通电，车辆正常行驶。
2. 与网络运营商确认物联网卡为可用状态。
3. 避免将车辆置于地下停车场等信号差的位置，参看设备说明书，根据指示灯判断，确认网络信号良好，GPS信号良好。


## 设备定位不准问题
车辆于地图定位与实际位置有偏差。

运维建议：
1. 确认车辆正常行驶，否则可能导致设备断电。
3. 确认[定位更新回调](api-61168305)接收该设备数据正常，若无更新，怀疑存在设备不上线问题。
4. 若回调数据中定位标识`AV=V`，说明设备搜索不到卫星信号，GPS定位失败，建议开车移动到信号较好的位置。

## 联系我们
![输入图片说明](https://foruda.gitee.com/images/1680243363561665705/660cd7e4_12581006.png "轻车车联网群二维码.png")
