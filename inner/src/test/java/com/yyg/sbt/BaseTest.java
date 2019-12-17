package com.yyg.sbt;


import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.StringUtils;
import org.springframework.web.context.WebApplicationContext;

import java.util.UUID;

/**
 * <p>Description: [测试基类，其他测试类可继承该类]</p>
 *
 * @version 1.0
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootInnerApplication.class)
@WebAppConfiguration
public class BaseTest {
    @Autowired
    protected WebApplicationContext context;
    protected MockMvc mockMvc;
    protected MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        session = new MockHttpSession();
        session.setAttribute("sessionId", StringUtils.replace(UUID.randomUUID().toString(), "-", ""));
//        UserInfo userInfo = new UserInfo();
//        userInfo.setEmployeeId("123456");
//        userInfo.setUserName("测试供应商登陆");
//        userInfo.setEmail("123456@139.com");
        //session.setAttribute(SsoConstant.CURRENT_USER, userInfo);
        log.debug("sessionId: {}", session.getAttribute("sessionId"));
    }

}

