import com.yc.MyConfig;
import com.yc.dao.UserDaoImpl;
import com.yc.service.UserBiz;
import org.ycframework.context.YcAnnotationConfigApplicationContext;
import org.ycframework.context.YcApplicationContext;

/**
 * @program: gitTest
 * description:
 * @author:yyq
 * @create: 2023-07-27 20:01
 */
public class App {
    public static void main(String[] args) {
        YcApplicationContext ac = new YcAnnotationConfigApplicationContext(MyConfig.class);
        UserBiz ub= (UserBiz) ac.getBean("userBizImpl");
        System.out.println(ub);
        ub.add("张三");


    }
}
