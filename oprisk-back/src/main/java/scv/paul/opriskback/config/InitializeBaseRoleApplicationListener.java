package scv.paul.opriskback.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
//import scv.paul.opriskback.dao.RoleDAO;

/***
 * Инициализация базовых ролей в БД чере ApplicationListener

@Component
public class InitializeBaseRoleApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
    private RoleDAO roleDao;

    @Autowired
    public void setRoleDao(RoleDAO roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        roleDao.init();
    }
}
*/