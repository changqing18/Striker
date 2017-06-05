package data;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 28713 on 2017/5/31.
 */
public class SessionFactoryUtil {
    private static final String RESOURCE="data/mybatis-config.xml";
    private static SqlSessionFactory sqlSessionFactory=null;
    private static void initFactory(){
        try(InputStream in= Resources.getResourceAsStream(RESOURCE)) {
            if(sqlSessionFactory==null){
                Logger log= LogManager.getLogger();
                log.info("SqlSessionFactory is initializing");
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(in);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static SqlSessionFactory getSqlSessionFactory(){
        initFactory();
        return sqlSessionFactory;
    }
}
