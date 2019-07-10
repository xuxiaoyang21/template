package com.xuxy.echarts;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Line;

/**
 * @author xuxiaoyang
 * @create 2018/5/9
 * @description echarts图表工具类
 */
public class EchartsUtils {


    /**
    *
    * @author xuxiaoyang
    * @email xuxiaoyang1@supcon.com
    * @date 2018/5/9
    * @description    得到option对象
    */
    public String getOption() {

        String title = "断舍离";
        String[] types = {"断","舍","离"};
        int[][] datas = {{13,456,72,68,242,445,81},
                {123,46,752,78,22,45,841},
                {123,456,72,778,222,45,841}};
        String[] weeks ={"星期一","星期二","星期三","星期四","星期五","星期六","星期日"};
        //创建option对象
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





}
