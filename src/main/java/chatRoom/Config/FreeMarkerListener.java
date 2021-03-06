package chatRoom.Config;

import freemarker.template.Configuration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//在 Servlet API 中有一个 ServletContextListener 接口，
// 它能够监听 ServletContext 对象的生命周期，
// 实际上就是监听 Web 应用的生命周期。

//当web服务启动时，并创建了servletContext域对象后，
// 会取得这个web已用的虚拟路径，并保存在servletContext域对象中，
// 这样就可以在整个web应用中直接从域中获取虚拟路径

@WebListener
public class FreeMarkerListener implements ServletContextListener {
    //读取配置都是key-value的形式，
    //key值
    public static final String TEMPLATE_KEY="_template_";
    //value值是Configuration (freemarker.template)

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //配置版本
        Configuration cfg=new Configuration(Configuration.VERSION_2_3_0);
        //加载ftl的路径
        //绝对路径
        try {
            cfg.setDirectoryForTemplateLoading(new File("E:/For Git/items/chatRoomWebSocket/src/main/webapp"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //配置页面编码
        cfg.setDefaultEncoding(StandardCharsets.UTF_8.displayName());




        sce.getServletContext().setAttribute(TEMPLATE_KEY,cfg);

    }
    //终止时再调用
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
