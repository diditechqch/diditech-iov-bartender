package com.diditech.iov.gps.app.report.srv.impl;

import com.alibaba.fastjson.JSON;
import com.diditech.iov.gps.app.report.po.RptTrips;
import com.diditech.iov.gps.app.report.srv.ReportGpsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author zhjd <br>
 * @date 2023/3/21 <br>
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ReportTripsServiceImplTest {

    @Autowired
    private ReportGpsService tripService;

    @Test
    void saveDayGps() {
        String test = "[{\"dayTag\":\"2023-03-15\",\"deviceNum\":\"14162036919\",\"distance\":25.80,\"duration\":153,\"endAddress\":\"天津市 南开区 港宁西路 37号 阳光100SOHO 附近 46米\",\"endLat\":39.071013620053186,\"endLatBd\":39.078112,\"endLatGc\":39.07210412154615,\"endLng\":117.14299526830033,\"endLngBd\":117.155868,\"endLngGc\":117.14938311977275,\"endTime\":1678899505000,\"fuelConsumption\":0,\"speedAve\":10,\"speedMax\":79,\"startAddress\":\"天津市 河西区 梅江道 天津广播电视台 内\",\"startLat\":39.068684,\"startLatBd\":39.075805,\"startLatGc\":39.069658,\"startLng\":117.212229,\"startLngBd\":117.224944,\"startLngGc\":117.218503,\"startTime\":1678890325000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":15.0,\"duration\":33,\"endAddress\":\"天津市 河西区 梅江道 天津数字电视大厦 内\",\"endLat\":39.068582800592914,\"endLatBd\":39.075703,\"endLatGc\":39.06955616570575,\"endLng\":117.21219596887518,\"endLngBd\":117.224911,\"endLngGc\":117.21847019643626,\"endTime\":1678902496000,\"fuelConsumption\":0,\"speedAve\":27,\"speedMax\":78,\"startAddress\":\"天津市 南开区 港宁西路 37号 阳光100国际新城-西南门 附近 31米\",\"startLat\":39.0712735441828,\"startLatBd\":39.078374,\"startLatGc\":39.07236437054144,\"startLng\":117.14289689250076,\"startLngBd\":117.155769,\"startLngGc\":117.14928493561125,\"startTime\":1678900483000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":0.6,\"duration\":2,\"endAddress\":\"天津市 河西区 梅江道 6号 宋府花园 附近 35米\",\"endLat\":39.06773948757886,\"endLatBd\":39.074938,\"endLatGc\":39.06870674486369,\"endLng\":117.21772902927091,\"endLngBd\":117.230412,\"endLngGc\":117.2239985833344,\"endTime\":1678925165000,\"fuelConsumption\":0,\"speedAve\":18,\"speedMax\":32,\"startAddress\":\"天津市 河西区 梅江道 天津广播电视台 内\",\"startLat\":39.068695763686115,\"startLatBd\":39.075816,\"startLatGc\":39.06966919835128,\"startLng\":117.21219605281357,\"startLngBd\":117.224911,\"startLngGc\":117.21847029449859,\"startTime\":1678925009000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":1.2,\"duration\":5,\"endAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"endLat\":39.0705635172119,\"endLatBd\":39.077675,\"endLatGc\":39.07153876858663,\"endLng\":117.21159446352192,\"endLngBd\":117.224312,\"endLngGc\":117.21786950885695,\"endTime\":1678927289000,\"fuelConsumption\":0,\"speedAve\":14,\"speedMax\":46,\"startAddress\":\"天津市 河西区 梅江道 6号 宋府花园 东北 59米\",\"startLat\":39.06779987855086,\"startLatBd\":39.074998,\"startLatGc\":39.068767202673556,\"startLng\":117.21769690533637,\"startLngBd\":117.23038,\"startLngGc\":117.22396648995648,\"startTime\":1678926956000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":56.7,\"duration\":55,\"endAddress\":\"天津市 武清区 S324(武宁线) 天津市公安交通管理局武清支队武清支队 内\",\"endLat\":39.3947222783998,\"endLatBd\":39.402077,\"endLatGc\":39.396037115437515,\"endLng\":117.08537689895273,\"endLngBd\":117.098342,\"endLngGc\":117.09183537690325,\"endTime\":1678931982000,\"fuelConsumption\":0,\"speedAve\":61,\"speedMax\":121,\"startAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"startLat\":39.070544003774636,\"startLatBd\":39.077656,\"startLatGc\":39.07151920611087,\"startLng\":117.21162766799975,\"startLngBd\":117.224345,\"startLngGc\":117.21790267912694,\"startTime\":1678928680000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":54.4,\"duration\":61,\"endAddress\":\"天津市 河西区 郁江南道 天津广播电视台 内\",\"endLat\":39.070567987838295,\"endLatBd\":39.077681,\"endLatGc\":39.071543133226335,\"endLng\":117.21169210671515,\"endLngBd\":117.224409,\"endLngGc\":117.21796705935313,\"endTime\":1678939965000,\"fuelConsumption\":0,\"speedAve\":53,\"speedMax\":109,\"startAddress\":\"天津市 武清区 S324(武宁线) 天津市公安交通管理局武清支队武清支队 内\",\"startLat\":39.394677609213076,\"startLatBd\":39.402032,\"startLatGc\":39.39599242200021,\"startLng\":117.08535980239586,\"startLngBd\":117.098325,\"startLngGc\":117.09181825824862,\"startTime\":1678936249000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":24.2,\"duration\":40,\"endAddress\":\"天津市 西青区 尊美街 e35号 杨柳青古镇 内\",\"endLat\":39.135074675401206,\"endLatBd\":39.142398,\"endLatGc\":39.1360993084461,\"endLng\":117.00495296578129,\"endLngBd\":117.017551,\"endLngGc\":117.01113493936553,\"endTime\":1678944759000,\"fuelConsumption\":0,\"speedAve\":36,\"speedMax\":91,\"startAddress\":\"天津市 河西区 郁江南道 天津广播电视台 内\",\"startLat\":39.070559738985274,\"startLatBd\":39.077673,\"startLatGc\":39.07153486138714,\"startLng\":117.21170820645791,\"startLngBd\":117.224425,\"startLngGc\":117.21798314271652,\"startTime\":1678942338000},{\"dayTag\":\"2023-03-16\",\"deviceNum\":\"14162036919\",\"distance\":43.0,\"duration\":73,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"endLat\":39.13332295471213,\"endLatBd\":39.140624,\"endLatGc\":39.13432618523619,\"endLng\":117.22265901044639,\"endLngBd\":117.235349,\"endLngGc\":117.22893380644088,\"endTime\":1678960404000,\"fuelConsumption\":0,\"speedAve\":35,\"speedMax\":80,\"startAddress\":\"天津市 西青区 明清街 A7 杨柳青古镇 内\",\"startLat\":39.134980747174154,\"startLatBd\":39.142296,\"startLatGc\":39.136008495274616,\"startLng\":117.00601060604451,\"startLngBd\":117.018617,\"startLngGc\":117.01219727048846,\"startTime\":1678956024000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":30.4,\"duration\":132,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 内\",\"endLat\":39.13335508749048,\"endLatBd\":39.140656,\"endLatGc\":39.13435835031354,\"endLng\":117.22264191727992,\"endLngBd\":117.235332,\"endLngGc\":117.22891672598038,\"endTime\":1679019282000,\"fuelConsumption\":0,\"speedAve\":13,\"speedMax\":66,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"startLat\":39.13328568580727,\"startLatBd\":39.140587,\"startLatGc\":39.13428886959618,\"startLng\":117.22269116611766,\"startLngBd\":117.235381,\"startLngGc\":117.22896594116543,\"startTime\":1679011341000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":25.9,\"duration\":53,\"endAddress\":\"天津市 河西区 梅江道 天津数字电视大厦 内\",\"endLat\":39.06859879537368,\"endLatBd\":39.075719,\"endLatGc\":39.06957217033468,\"endLng\":117.21219598077997,\"endLngBd\":117.224911,\"endLngGc\":117.21847021034088,\"endTime\":1679025928000,\"fuelConsumption\":0,\"speedAve\":29,\"speedMax\":83,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"startLat\":39.13318000902172,\"startLatBd\":39.140482,\"startLatGc\":39.13418306706203,\"startLng\":117.22277357239226,\"startLngBd\":117.235463,\"startLngGc\":117.22904829268255,\"startTime\":1679022692000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":24.5,\"duration\":43,\"endAddress\":\"天津市 津南区 津沽大街 科技兴院 西 141米\",\"endLat\":38.989762521909974,\"endLatBd\":38.996586,\"endLatGc\":38.990857756593044,\"endLng\":117.39994988836176,\"endLngBd\":117.413079,\"endLngGc\":117.40649872178855,\"endTime\":1679031244000,\"fuelConsumption\":0,\"speedAve\":34,\"speedMax\":89,\"startAddress\":\"天津市 河西区 梅江道 天津数字电视大厦 内\",\"startLat\":39.0685700535766,\"startLatBd\":39.07569,\"startLatGc\":39.06954342850824,\"startLng\":117.21217985527893,\"startLngBd\":117.224895,\"startLngGc\":117.21845409629319,\"startTime\":1679028642000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":2.3,\"duration\":16,\"endAddress\":\"天津市 津南区 S317(二八线) 天津市津南区人民法院 内\",\"endLat\":38.97821861271377,\"endLatBd\":38.985195,\"endLatGc\":38.979306150642685,\"endLng\":117.38925164931399,\"endLngBd\":117.402314,\"endLngGc\":117.39579577243819,\"endTime\":1679035349000,\"fuelConsumption\":0,\"speedAve\":8,\"speedMax\":44,\"startAddress\":\"天津市 津南区 津沽大街 科技兴院 西北 136米\",\"startLat\":38.98997371775702,\"startLatBd\":38.996797,\"startLatGc\":38.9910690761831,\"startLng\":117.3999815490015,\"startLngBd\":117.413111,\"startLngGc\":117.40653040947976,\"startTime\":1679034355000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":42.7,\"duration\":93,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"endLat\":39.133289224413865,\"endLatBd\":39.140591,\"endLatGc\":39.1342923733507,\"endLng\":117.2227403528813,\"endLngBd\":117.23543,\"endLngGc\":117.22901510360587,\"endTime\":1679045893000,\"fuelConsumption\":0,\"speedAve\":27,\"speedMax\":83,\"startAddress\":\"天津市 津南区 S317(二八线) 天津市津南区人民法院 内\",\"startLat\":38.97817036160634,\"startLatBd\":38.985145,\"startLatGc\":38.979257898586035,\"startLng\":117.38935004146681,\"startLngBd\":117.402413,\"startLngGc\":117.39589421762213,\"startTime\":1679040306000},{\"dayTag\":\"2023-03-17\",\"deviceNum\":\"14162036919\",\"distance\":98.0,\"duration\":278,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 内\",\"endLat\":39.13337951009619,\"endLatBd\":39.14068,\"endLatGc\":39.13438282332589,\"endLng\":117.22259471336739,\"endLngBd\":117.235285,\"endLngGc\":117.22886954911603,\"endTime\":1679066073000,\"fuelConsumption\":0,\"speedAve\":21,\"speedMax\":84,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"startLat\":39.13330197242218,\"startLatBd\":39.140603,\"startLatGc\":39.13430519026087,\"startLng\":117.22265902809195,\"startLngBd\":117.235349,\"startLngGc\":117.22893382143297,\"startTime\":1679049361000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":3.0,\"duration\":6,\"endAddress\":\"天津市 河东区 张贵庄路 160号-增16 家乐福(河东店) 内\",\"endLat\":39.12199986229222,\"endLatBd\":39.129273,\"endLatGc\":39.12298853905373,\"endLng\":117.24592738755231,\"endLngBd\":117.25861,\"endLngGc\":117.25220131954346,\"endTime\":1679091853000,\"fuelConsumption\":0,\"speedAve\":30,\"speedMax\":54,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"startLat\":39.133277533442985,\"startLatBd\":39.140579,\"startLatGc\":39.13428069945978,\"startLng\":117.22270823889649,\"startLngBd\":117.235398,\"startLngGc\":117.22898300430747,\"startTime\":1679091471000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":12.3,\"duration\":16,\"endAddress\":\"天津市 河西区 梅江道 天津数字电视大厦 内\",\"endLat\":39.06831436594069,\"endLatBd\":39.075436,\"endLatGc\":39.0692874577348,\"endLng\":117.21229440477808,\"endLngBd\":117.225009,\"endLngGc\":117.21856850683226,\"endTime\":1679095106000,\"fuelConsumption\":0,\"speedAve\":46,\"speedMax\":65,\"startAddress\":\"天津市 河东区 张贵庄路 160号-增16 家乐福(河东店) 内\",\"startLat\":39.122068625242946,\"startLatBd\":39.129342,\"startLatGc\":39.123057342079214,\"startLng\":117.24591137888044,\"startLngBd\":117.258594,\"startLngGc\":117.25218531041348,\"startTime\":1679094101000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":0.6,\"duration\":1,\"endAddress\":\"天津市 河西区 郁江南道 天津广播电视台 内\",\"endLat\":39.07058947714319,\"endLatBd\":39.077703,\"endLatGc\":39.07156459995114,\"endLng\":117.21172433235374,\"endLngBd\":117.224441,\"endLngGc\":117.21799925699437,\"endTime\":1679098542000,\"fuelConsumption\":0,\"speedAve\":36,\"speedMax\":27,\"startAddress\":\"天津市 河西区 梅江道 天津数字电视大厦 内\",\"startLat\":39.068447591394474,\"startLatBd\":39.075567,\"startLatGc\":39.069420926282966,\"startLng\":117.21214755553275,\"startLngBd\":117.224863,\"startLngGc\":117.21842181135969,\"startTime\":1679098426000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":9.4,\"duration\":20,\"endAddress\":\"天津市 西青区 光明路 精武佳苑-东区 西北 268米\",\"endLat\":39.04375694265693,\"endLatBd\":39.051087,\"endLatGc\":39.044850793055765,\"endLng\":117.12990672444275,\"endLngBd\":117.142746,\"endLngGc\":117.13630989568485,\"endTime\":1679101405000,\"fuelConsumption\":0,\"speedAve\":28,\"speedMax\":75,\"startAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"startLat\":39.07040954592355,\"startLatBd\":39.077522,\"startLatGc\":39.07138462961664,\"startLng\":117.2116597860589,\"startLngBd\":117.224377,\"startLngGc\":117.21793474960396,\"startTime\":1679100182000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":19.4,\"duration\":31,\"endAddress\":\"天津市 西青区 柳口路辅路 文昌桥 西 65米\",\"endLat\":39.13055426902699,\"endLatBd\":39.137854,\"endLatGc\":39.13158347307444,\"endLng\":117.00739396641146,\"endLngBd\":117.020008,\"endLngGc\":117.01358620486344,\"endTime\":1679104345000,\"fuelConsumption\":0,\"speedAve\":37,\"speedMax\":80,\"startAddress\":\"天津市 西青区 光明路 精武佳苑-东区 西北 268米\",\"startLat\":39.04364697451745,\"startLatBd\":39.050976,\"startLatGc\":39.04474066726954,\"startLng\":117.12997143981589,\"startLngBd\":117.142811,\"startLngGc\":117.13637451724125,\"startTime\":1679102469000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":0.8,\"duration\":3,\"endAddress\":\"天津市 西青区 明清街 A7 杨柳青古镇 内\",\"endLat\":39.13476123764852,\"endLatBd\":39.142075,\"endLatGc\":39.135789340600745,\"endLng\":117.00617345439342,\"endLngBd\":117.018781,\"endLngGc\":117.01236081415594,\"endTime\":1679107863000,\"fuelConsumption\":0,\"speedAve\":16,\"speedMax\":43,\"startAddress\":\"天津市 西青区 柳口路辅路 文昌桥 西 65米\",\"startLat\":39.13054997652889,\"startLatBd\":39.13785,\"startLatGc\":39.131579080509475,\"startLng\":117.00736124646835,\"startLngBd\":117.019975,\"startLngGc\":117.01355333975403,\"startTime\":1679107626000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":29.1,\"duration\":50,\"endAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"endLat\":39.07055955556335,\"endLatBd\":39.077669,\"endLatGc\":39.071534949561666,\"endLng\":117.21146460708212,\"endLngBd\":117.224183,\"endLngGc\":117.21773977650464,\"endTime\":1679134067000,\"fuelConsumption\":0,\"speedAve\":34,\"speedMax\":90,\"startAddress\":\"天津市 西青区 明清街 A7 杨柳青古镇 内\",\"startLat\":39.13483517683295,\"startLatBd\":39.142149,\"startLatGc\":39.13586332425304,\"startLng\":117.00617339672065,\"startLngBd\":117.018781,\"startLngGc\":117.01236076548231,\"startTime\":1679131024000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":87.9,\"duration\":143,\"endAddress\":\"天津市 滨海新区 航运西路 天津港集团有限公司 西北 345米\",\"endLat\":39.02639383646275,\"endLatBd\":39.033536,\"endLatGc\":39.02733188090411,\"endLng\":117.73238649805108,\"endLngBd\":117.745143,\"endLngGc\":117.73868976537679,\"endTime\":1679143852000,\"fuelConsumption\":0,\"speedAve\":36,\"speedMax\":118,\"startAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"startLat\":39.070510763148924,\"startLatBd\":39.077623,\"startLatGc\":39.07148592708263,\"startLng\":117.21164375063665,\"startLngBd\":117.224361,\"startLngGc\":117.217918742219,\"startTime\":1679135226000},{\"dayTag\":\"2023-03-18\",\"deviceNum\":\"14162036919\",\"distance\":49.0,\"duration\":44,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 内\",\"endLat\":39.13352916134981,\"endLatBd\":39.140829,\"endLatGc\":39.13453262741751,\"endLng\":117.22251226661596,\"endLngBd\":117.235203,\"endLngGc\":117.22878716332399,\"endTime\":1679154983000,\"fuelConsumption\":0,\"speedAve\":66,\"speedMax\":108,\"startAddress\":\"天津市 滨海新区 航运西路 天津港集团有限公司 西北 335米\",\"startLat\":39.026117864413614,\"startLatBd\":39.033263,\"startLatGc\":39.02705564278808,\"startLng\":117.7321747014601,\"startLngBd\":117.74493,\"startLngGc\":117.73847787926525,\"startTime\":1679152295000},{\"dayTag\":\"2023-03-19\",\"deviceNum\":\"14162036919\",\"distance\":31.0,\"duration\":92,\"endAddress\":\"天津市 南开区 青年路 269号 新光里 西 58米\",\"endLat\":39.136759243432124,\"endLatBd\":39.143714,\"endLatGc\":39.13787272936116,\"endLng\":117.15280968796007,\"endLngBd\":117.165759,\"endLngGc\":117.15918910366467,\"endTime\":1679195159000,\"fuelConsumption\":0,\"speedAve\":20,\"speedMax\":73,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东 94米\",\"startLat\":39.133293829015884,\"startLatBd\":39.140595,\"startLatGc\":39.134297029825134,\"startLng\":117.22267509714369,\"startLngBd\":117.235365,\"startLngGc\":117.22894988133245,\"startTime\":1679189632000},{\"dayTag\":\"2023-03-19\",\"deviceNum\":\"14162036919\",\"distance\":77.3,\"duration\":271,\"endAddress\":\"天津市 河西区 隆昌路 23号 天津图书馆(文化中心馆) 东 381米\",\"endLat\":39.084325533805995,\"endLatBd\":39.091453,\"endLatGc\":39.085308537903025,\"endLng\":117.21224549606828,\"endLngBd\":117.224953,\"endLngGc\":117.21852165734391,\"endTime\":1679212372000,\"fuelConsumption\":0,\"speedAve\":17,\"speedMax\":76,\"startAddress\":\"天津市 南开区 青年路 265号 新光里 西 69米\",\"startLat\":39.1368318956056,\"startLatBd\":39.143787,\"startLatGc\":39.137945454257704,\"startLng\":117.15279369357422,\"startLngBd\":117.165743,\"startLngGc\":117.15917314723352,\"startTime\":1679196086000},{\"dayTag\":\"2023-03-19\",\"deviceNum\":\"14162036919\",\"distance\":56.3,\"duration\":198,\"endAddress\":\"天津市 南开区 密云路 汶川里 东南 145米\",\"endLat\":39.126851904469206,\"endLatBd\":39.134344,\"endLatGc\":39.12800905118447,\"endLng\":117.12000682570002,\"endLngBd\":117.132828,\"endLngGc\":117.12643097347127,\"endTime\":1679227061000,\"fuelConsumption\":0,\"speedAve\":17,\"speedMax\":83,\"startAddress\":\"天津市 河西区 隆昌路 23号 天津图书馆(文化中心馆) 东 381米\",\"startLat\":39.08429679784014,\"startLatBd\":39.091424,\"startLatGc\":39.08527980191543,\"startLng\":117.21222938902665,\"startLngBd\":117.224937,\"startLngGc\":117.21850556170797,\"startTime\":1679215177000},{\"dayTag\":\"2023-03-19\",\"deviceNum\":\"14162036919\",\"distance\":2.8,\"duration\":6,\"endAddress\":\"天津市 西青区 闵行路 凯信佳园 西 174米\",\"endLat\":39.12205594546268,\"endLatBd\":39.129507,\"endLatGc\":39.1232242996038,\"endLng\":117.10141332052541,\"endLngBd\":117.114254,\"endLngGc\":117.10784459420665,\"endTime\":1679228463000,\"fuelConsumption\":0,\"speedAve\":28,\"speedMax\":61,\"startAddress\":\"天津市 南开区 密云路 汶川里 东南 145米\",\"startLat\":39.12686389358684,\"startLatBd\":39.134356,\"startLatGc\":39.12802104757655,\"startLng\":117.12000681339188,\"startLngBd\":117.132828,\"startLngGc\":117.12643096270777,\"startTime\":1679228054000},{\"dayTag\":\"2023-03-19\",\"deviceNum\":\"14162036919\",\"distance\":41.8,\"duration\":120,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑-7栋 内\",\"endLat\":39.13332225645438,\"endLatBd\":39.140623,\"endLatGc\":39.13432551079546,\"endLng\":117.22262688639539,\"endLngBd\":117.235317,\"endLngGc\":117.22890169858009,\"endTime\":1679237188000,\"fuelConsumption\":0,\"speedAve\":20,\"speedMax\":77,\"startAddress\":\"天津市 西青区 闵行路 凯信佳园 西 174米\",\"startLat\":39.12205594546268,\"startLatBd\":39.129507,\"startLatGc\":39.1232242996038,\"startLng\":117.10141332052541,\"startLngBd\":117.114254,\"startLngGc\":117.10784459420665,\"startTime\":1679229976000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":15.4,\"duration\":36,\"endAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"endLat\":39.07059703681587,\"endLatBd\":39.077707,\"endLatGc\":39.07157241785451,\"endLng\":117.21149684495448,\"endLngBd\":117.224215,\"endLngGc\":117.21777198810105,\"endTime\":1679271114000,\"fuelConsumption\":0,\"speedAve\":25,\"speedMax\":73,\"startAddress\":\"天津市 河东区 南横街 欣荣馨苑 东南 86米\",\"startLat\":39.13307775361717,\"startLatBd\":39.140381,\"startLatGc\":39.1340806410292,\"startLng\":117.22291921462424,\"startLngBd\":117.235608,\"startLngGc\":117.22919384951037,\"startTime\":1679268913000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":40.9,\"duration\":69,\"endAddress\":\"天津市 静海区 台头西瓜园 西北 370米\",\"endLat\":39.0147603480642,\"endLatBd\":39.021424,\"endLatGc\":39.015626259356026,\"endLng\":116.79603619865246,\"endLngBd\":116.808559,\"endLngGc\":116.80197620149364,\"endTime\":1679279463000,\"fuelConsumption\":0,\"speedAve\":35,\"speedMax\":102,\"startAddress\":\"天津市 河西区 友谊南路辅路 天津广播电视台 内\",\"startLat\":39.070621488808705,\"startLatBd\":39.077734,\"startLatGc\":39.071596704091995,\"startLng\":117.21165892598273,\"startLngBd\":117.224376,\"startLngGc\":117.21793391699835,\"startTime\":1679275305000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":3.3,\"duration\":8,\"endAddress\":\"天津市 静海区 静台路 三堡村 西南 898米\",\"endLat\":38.99076935338377,\"endLatBd\":38.997271,\"endLatGc\":38.99160167555891,\"endLng\":116.80709037888792,\"endLngBd\":116.81961,\"endLngGc\":116.81300892741166,\"endTime\":1679285856000,\"fuelConsumption\":0,\"speedAve\":24,\"speedMax\":68,\"startAddress\":\"天津市 静海区 台头西瓜园 西北 370米\",\"startLat\":39.01479631762965,\"startLatBd\":39.02146,\"startLatGc\":39.01566225110423,\"startLng\":116.79603616952635,\"startLngBd\":116.808559,\"startLngGc\":116.801976176739,\"startTime\":1679285355000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":4.1,\"duration\":6,\"endAddress\":\"天津市 静海区 X544(梁台路) 新立木材厂 南 259米\",\"endLat\":39.018020932678944,\"endLatBd\":39.024876,\"endLatGc\":39.018904192085344,\"endLng\":116.78584597787714,\"endLngBd\":116.798328,\"endLngGc\":116.79180097152305,\"endTime\":1679287467000,\"fuelConsumption\":0,\"speedAve\":41,\"speedMax\":66,\"startAddress\":\"天津市 静海区 静台路 三堡村 西南 898米\",\"startLat\":38.99067969911673,\"startLatBd\":38.997181,\"startLatGc\":38.99151190918963,\"startLng\":116.80712245794389,\"startLngBd\":116.819642,\"startLngGc\":116.81304093876724,\"startTime\":1679287101000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":76.4,\"duration\":126,\"endAddress\":\"天津市 津南区 光明村 东 354米\",\"endLat\":38.919667486435806,\"endLatBd\":38.926551,\"endLatGc\":38.92072068737003,\"endLng\":117.39332126723797,\"endLngBd\":117.406434,\"endLngGc\":117.39985980889631,\"endTime\":1679298696000,\"fuelConsumption\":0,\"speedAve\":36,\"speedMax\":110,\"startAddress\":\"天津市 静海区 X544(梁台路) 新立木材厂 南 259米\",\"startLat\":39.01804859624019,\"startLatBd\":39.024904,\"startLatGc\":39.01893189477356,\"startLng\":116.78583003640428,\"startLngBd\":116.798312,\"startLngGc\":116.79178505385592,\"startTime\":1679291097000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":1.2,\"duration\":17,\"endAddress\":\"天津市 津南区 前进村路 Ｘ４６８/盛塘路出口 东北 390米\",\"endLat\":38.91913091429,\"endLatBd\":38.926009,\"endLatGc\":38.920183834908315,\"endLng\":117.39364820495032,\"endLngBd\":117.406763,\"endLngGc\":117.40018679684133,\"endTime\":1679300695000,\"fuelConsumption\":0,\"speedAve\":4,\"speedMax\":47,\"startAddress\":\"天津市 津南区 光明村 东 354米\",\"startLat\":38.91961850686333,\"startLatBd\":38.926502,\"startLatGc\":38.92067167892018,\"startLng\":117.39332124814092,\"startLngBd\":117.406434,\"startLngGc\":117.399859783461,\"startTime\":1679299666000},{\"dayTag\":\"2023-03-20\",\"deviceNum\":\"14162036919\",\"distance\":45.8,\"duration\":71,\"endAddress\":\"天津市 河东区 南横街 欣荣馨苑 内\",\"endLat\":39.13339919156312,\"endLatBd\":39.1407,\"endLatGc\":39.1344024924161,\"endLng\":117.22262682182803,\"endLngBd\":117.235317,\"endLngGc\":117.22890164374219,\"endTime\":1679315727000,\"fuelConsumption\":0,\"speedAve\":38,\"speedMax\":92,\"startAddress\":\"天津市 津南区 前进村路 光明村 东 392米\",\"startLat\":38.91929028648584,\"startLatBd\":38.92617,\"startLatGc\":38.920343290697666,\"startLng\":117.39354982086286,\"startLngBd\":117.406664,\"startLngGc\":117.4000883980094,\"startTime\":1679311427000}]";
        List<RptTrips> trips = JSON.parseArray(test, RptTrips.class);

        tripService.saveDayGps(trips, true);
    }
}