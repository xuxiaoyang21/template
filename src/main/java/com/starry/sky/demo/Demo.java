package com.starry.sky.demo;


import com.starry.sky.demo.storm.WordCounter;
import com.starry.sky.demo.storm.WordNormalizer;
import com.starry.sky.demo.storm.WordReader;
import org.apache.axis2.databinding.types.xsd.String;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;

import java.util.*;

/**
 * @author xuxiaoyang
 * @create 2018/5/2
 * @description
 */
public class Demo {
    private Vector vector;

//
//    public static void main(String[] args) {
////        //切割字符串
////        String str = "123,456,789,458,753,159,476,752";
////        String[] strs = str.split("\\,"); //分割自负床
////        List<String> strings = Arrays.asList(strs);
////        System.out.println(strs);
////        String string = StringUtils.stringToOracle(strings);
//
//    }

    public static void main(String[] args) throws InterruptedException {
        //定义拓扑
        TopologyBuilder builder = new TopologyBuilder();
        builder.setSpout("word-reader", new WordReader());
        builder.setBolt("word-normalizer", new WordNormalizer()).shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCounter(), 2).fieldsGrouping("word-normalizer", new Fields("word"));

        //配置
        Config conf = getConfig();

        //运行拓扑
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology("Getting-Started-Topologie", conf, builder.createTopology());
        Thread.sleep(1000);
        // cluster.shutdown();

        List<String> list = new ArrayList<>();
        list.iterator();

        LinkedList list1 = new LinkedList();

        list1.iterator();

        Vector vector = new Vector();



        System.out.println();

        ClassLoader loader = ClassLoader.getSystemClassLoader();
        char[] chars = new char[0];
        String string = new String();
    }

    private static Config getConfig() {
        Config conf = new Config();
        conf.put("wParameter info (within method call arguments)ordsFile", "src/main/resources/words.txt");
        conf.setDebug(false);
        return conf;
    }


}
