package com.xuxy.controllers;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA
 *
 * @author:xuxiaoyang
 * @Date: 2017/8/16  17:15
 * @Description: 百度echart测试  java装载
 */
@Controller
@RequestMapping("/echart")
public class EchartsController {

    /**
     * 初始化图表进行装载
     * 柱状图 实例
     * @param
     * @return
     */
    @RequestMapping(value = "/init",method = RequestMethod.POST)
    @ResponseBody
    public String init() throws UnsupportedEncodingException {

        String[] citis = { "广州", "深圳", "珠海", "汕头", "韶关", "佛山" };
        int[] datas = { 6030, 7800, 5200, 3444, 2666, 5708 };
        String[] colors = { "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)", "rgb(2,111,230)", "rgb(186,73,46)", "rgb(78,154,97)" };
        String title = "地市数据";
        String title1 = "abc";
        String title2 = "bcd";

        Option option = new Option();

        option.title(title); // 标题
        // 工具栏 功能 是否显示 或者显示多少都可以在这里设置
        option.toolbox().show(true).feature(
                       // Tool.mark, // 辅助线
                        Tool.dataView, // 数据视图
                        new MagicType(Magic.line, Magic.bar),// 线图、柱状图切换
                        Tool.restore,// 还原
                        Tool.saveAsImage                );// 保存为图片

        option.tooltip().
                  show(true).//显示工具提示功能
                  formatter("{a} <br/>{c} : {b}");//显示工具提示,设置提示格式

        option.legend(title,title1,title2);// 图例

        Bar bar = new Bar(title);// 图类别(柱状图)
        Bar bar1 = new Bar(title1);
        Bar bar2 = new Bar(title2);
        CategoryAxis category = new CategoryAxis();// 轴分类
        category.data(citis);// 轴数据类别
          // 循环数据
      /*  for (int i = 0; i < citis.length; i++) {
               int data = datas[i];
               String color = colors[i];
               // 类目对应的柱状图
               Map<String, Object> map = new HashMap<String, Object>(2);
               map.put("value", data);
               map.put("itemStyle", new ItemStyle().normal(new Normal().color(color)));
               bar.data(map);
        }*/
        bar.data(6030, 7800, 5200, 3444, 2666, 5708 );
        bar1.data( 7800, 5200, 3444, 2666, 5708,3423);
        bar2.data( 7800, 503, 3444, 263, 508,3423);


      // 横轴为类别、纵轴为值
           option.xAxis(category);// x轴
           option.yAxis(new ValueAxis());// y轴


       option.series(bar,bar1,bar2);
       //String name =  option.toString();
       return new Gson().toJson(option) ;

    }

    /**
     * 饼图 测试用例
     * @return
     */
    @RequestMapping(value = "/pie",method = RequestMethod.POST,
    produces = {"application/text;charset=utf-8"})
    @ResponseBody
    public String pie(){
        //需要的数据
        String title = "搜索引擎统计";
        String[] searchs = {"百度","必应","豆瓣","搜狗"};
        int[] datas = {123,456,789,555};

        //创建option对象
        Option option = new GsonOption();

        //设置标题  二级标题  标题位置
        option.title().text(title).subtext("二级标题").x("center");

        //设置工具栏 展示  能标记
        option.toolbox().show(true).feature(Tool.mark);

        //设置显示工具格式
        option.tooltip().show(true).formatter("{a}</br>{b}：{c}/个");

        //设置图例  图例位置  图例对齐方式 竖列对齐
        option.legend().data(searchs).x("left").orient(Orient.vertical);

        //填充数据
        Pie pie = new Pie();//创建饼图对象

        //设置饼图的标题 半径、位置
        pie.name(title).radius("55%").center("50%","50%");

        //填充数据
        for(int i = 0; i < searchs.length; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",datas[i]);//填充饼图数据
            map.put("name",searchs[i]);//填充饼图数据对应的搜索引擎
            pie.data(map);
        }
        option.series(pie); //设置数据

        return option.toString();
    }

    /**
     * 折线图的 测试用例
     * @return
     */
    @RequestMapping(value = "/line",method =RequestMethod.POST,
    produces = {"application/text;charset=UTF-8"})
    @ResponseBody()
    public String line(){
        String title = "断舍离";
        String[] types = {"断","舍","离"};
        int[][] datas = {{13,456,72,68,242,445,81},
                        {123,46,752,78,22,45,841},
                        {123,456,72,778,222,45,841}};
        String[] weeks ={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};

        Option option = new GsonOption();

        option.title().text(title).x("left");
        option.legend().data(types).x("center");

        option.tooltip().show(true);
        option.toolbox().show(true).feature(Tool.magicType,Tool.dataZoom);

        CategoryAxis categoryAxis = new CategoryAxis();

        categoryAxis.data(weeks);
        option.xAxis(categoryAxis);
        option.yAxis(new ValueAxis());

        //循环为了获取多条折线数据
       for(int i =0;i< types.length;i++){

           //创建line对象
           Line line = new Line();
           String type = types[i];
           line.name(type);
           for(int j = 0; j<weeks.length;j++){

               line.data(datas[i][j]);

           }
            option.series(line);
       }
        return option.toString();
    }

    @RequestMapping(value = "/sourthPie",method = RequestMethod.POST)
    @ResponseBody()
    public String  sourthPie(){

        //实现简单的南丁格尔图
        Option option = new GsonOption();

        String[] types = {"123","ewe","abc","nmn"};
        int[] datas = {123,232,3434,3333};
        option.backgroundColor("#2c343c");


        Pie pie = new Pie();
        //pie.roseType(RoseType.area);//显示方式  角度 半径  面积
        for(int i = 0; i<datas.length;i++){

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",datas[i]);
            map.put("name",types[i]);
            pie.data(map);
        }


        option.series(pie);
        return option.toString();
    }


    /**
     * 柱状图 图表option 的封装  测试用例--路段人流数据
     * @return
     */
    @RequestMapping(value = "/bar",method = RequestMethod.POST,
            produces = {"application/text;charset=UTF-8"})
    @ResponseBody()
    public String bar(String name, HttpServletRequest request, HttpServletResponse response){



        //由于是测试用例首先声明  1.标题   2.x轴代表的路段  3.路段数据
        //标题
        String title = "路段人流数据统计";
        //路段
        String[] roads = {"长江路","长河路","长安路","西湖路","建安路"};
        //路段数据
        int[] datas = {123,153,643,234,453};


        //####开始构建option对象 ######
        //这里先做最简单的测试用用例 其他渲染的属性 大家可以在了解之后自行测试使用
        //1.创建option对象    有两种方式 一种是直接创建option但是在封装好option之后要使用json转换工具进行格式的转换
        //这里使用第二种方式直接构建GsonOption 通过toString方法可转换成json
        Option option = new GsonOption();

        //2.设置标题  可选
        option.title().text(title).x("left");//将标题传入即可 并且支持链式调用 设置显示位置 居左

        //3.设置图例  可选
        option.legend("路段数据");
        option.legend("123");
        //4.设置工具栏  可选
        option.toolbox().show(true).feature(Tool.mark,
                Tool.magicType); //设置可标记

        //5.设置显示工具
        option.tooltip().show(true).
                formatter("{a}</br>{b}:{c}");//设置显示的格式 当鼠标放到柱状图上时的显示格式

        //6.设置x轴数据
        CategoryAxis categoryAxis = new CategoryAxis();
        categoryAxis.data(roads);
        option.xAxis(categoryAxis);

        //7.设置y轴 这里不给指定数据  自动调整
        ValueAxis valueAxis = new ValueAxis();
        option.yAxis(valueAxis);

        //8.填充柱状图数据
        Bar bar = new Bar();
        bar.name("路段数据");
        bar.type(SeriesType.bar);
        for(int i = 0; i < roads.length; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",datas[i]);
            map.put("name",roads[i]);
            bar.data(map);//指定路段装入指定数据
        }
        //装入数据表中
        option.series(bar);
        Line line = new Line();
        line.name("123");
        line.type(SeriesType.line);
        for(int i = 0; i < roads.length; i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("value",datas[i]);
            map.put("name",roads[i]);
            line.data(map);//指定路段装入指定数据
        }
        option.series(line);
        //option进行json格式处理
        String result = option.toString();
        return result ;
    }


    @RequestMapping(value = "map",method = RequestMethod.POST,
    produces = {"application/text;charset=utf-8"})
    @ResponseBody()
    public String map(){

        Option option = new GsonOption();

        option.tooltip().trigger(Trigger.item).formatter("{b}");
        //创建地图类
        com.github.abel533.echarts.series.Map map = new com.github.abel533.echarts.series.Map();
        //设置地图名称
        map.setName("中国");
        //设置地图类型
        map.setType(SeriesType.map);
        //设置地图地域
        map.setMapType("china");
        map.selectedMode("multiple");
        //设置是否显示省份
        map.itemStyle().normal().label().show(true);
        map.itemStyle().emphasis().label().show(true);

        Map<String,Object> map1 = new HashMap<String,Object>();
        //选中广东省
        map1.put("name","广东");
        map1.put("selected",true);
        map.data(map1);
        //填充数据
        option.series(map);

        //返回option json字符串
        return option.toString();

    }

}
