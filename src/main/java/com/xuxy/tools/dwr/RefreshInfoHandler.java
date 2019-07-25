package com.xuxy.tools.dwr;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.ScriptSessionFilter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

//将此类注入到spring中
@Component
public class RefreshInfoHandler {

    @PostConstruct
    public void init() {
        TimerTask  timerTask = new TimerTask() {
            @Override
            public void run() {
                sendAllPage();
//                sendReflashInfo();
            }
        };
        new Timer().schedule(timerTask,1000,20000);

    }

    //单个页面推送
    public void sendReflashInfo(){
        //这个url是会被spring mvc拦截然后转到你所要更新的界面
        //这个是请求url路径 不是试图的位置路径
        Browser.withPageFiltered("/home", new ScriptSessionFilter() {
            @Override
            public boolean match(ScriptSession scriptSession) {
                return true; //返回true代表执行推送
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();

            @Override
            public void run() {
                //设置要调用的 js及参数
                script.appendCall("test","放入要推送的内容");
                //得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                //遍历每一个ScriptSession 也就是获取到所有的窗口然后将推送的script放入
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                }
            }
        });
    }


    //推送到所有用户下当前看到的所有页面
    public void  sendAllPage() {

        //执行推送
        Browser. withAllSessionsFiltered(new ScriptSessionFilter() {
            @Override
            public boolean match(ScriptSession scriptSession) {
                return true;
            }
        }, new Runnable() {
            private ScriptBuffer script = new ScriptBuffer();

            @Override
            public void run() {
                //设置要调用的 js及参数
                script.appendCall("test","放入要推送的内容");
                //得到所有ScriptSession
                Collection<ScriptSession> sessions = Browser.getTargetSessions();
                //遍历每一个ScriptSession 也就是获取到所有的窗口然后将推送的script放入
                for (ScriptSession scriptSession : sessions) {
                    scriptSession.addScript(script);
                    System.out.println(scriptSession.getPage());
                }
            }
        });
    }

}
