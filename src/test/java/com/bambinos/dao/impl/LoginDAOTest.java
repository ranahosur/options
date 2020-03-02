package com.bambinos.dao.impl;

import com.bambinos.JunitConstants;
import com.bambinos.dao.LoginDAO;
import com.bambinos.model.BambinoConstants;
import com.bambinos.model.Login;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@ContextConfiguration(locations = { "classpath:/bambinos/config/user-beans.xml" })
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class LoginDAOTest {
    @Autowired
    public LoginDAO loginDAO;

    public static final String password = "B@mbino123";
    public static final String password2 = "test123";

    @Test
    public void runAllTests(){
        Login login = loginDAO.findLogin(BambinoConstants.ADMIN_USER);
        if(login == null){
            createLogin();
        }
        assertEquals(login.getPassword(),"B@mbino123");
        loginDAO.updatePassword(BambinoConstants.ADMIN_USER,password2);
        Login login2 = loginDAO.findLogin(BambinoConstants.ADMIN_USER);
        assertEquals(login2.getPassword(),password2);
//        loginDAO.deleteLogin(login.getLoginId());
//        login = loginDAO.findLogin(BambinoConstants.ADMIN_USER);
//        assertNull(login);

    }


    @Test
    public void createLogin() {
        Login login = new Login();
        login.setParentId(JunitConstants.FIRST_ADMIN);
        login.setParentTable(BambinoConstants.TBL_ADMIN);
        login.setLoginType("Admin");
        login.setRoleType(BambinoConstants.ROLE_ADMIN);
        login.setUsername(BambinoConstants.ADMIN_USER);
        login.setPassword(password);
        login.setActive("Y");
        loginDAO.create(login);
    }

    @Test
    public void testLoginFind() {
        Login login = loginDAO.findLogin(BambinoConstants.ADMIN_USER);
        assertEquals(login.getPassword(),password);
        loginDAO.deleteLogin(login.getLoginId());
    }
}
