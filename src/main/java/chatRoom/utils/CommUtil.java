package chatRoom.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class CommUtil {

    public CommUtil() {
    }

    public static Properties loadProperties(String filename)
    {
        Properties properties=new Properties();
//获取类加载器下的 和它同目录的所有文件,找 db.properities
        InputStream in=CommUtil.class.getClassLoader().getResourceAsStream(filename);

        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    private static final Gson gson=new GsonBuilder().create();

    public static String objectToJson(Object obj)
    {return gson.toJson(obj);}
    public static Object jsonToObject(String jsonstr,Class objclass)
    {return gson.fromJson(jsonstr,objclass);}

    public static boolean strIsnull(String str)
    {return str==null||str.equals("");
        //顺序不可颠倒，否则str变成空指针了。

}}