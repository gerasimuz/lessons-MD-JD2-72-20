package com.github.nastyasivko.project_final.dao.config;

import com.github.nastyasivko.project_final.dao.*;
import com.github.nastyasivko.project_final.dao.impl.*;
import com.github.nastyasivko.project_final.dao.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(HibernateConfig.class)
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.github.nastyasivko.project_final.dao.repository")
public class DaoConfig {

    @Autowired
    private ApprovedOrderRepository approvedOrderRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private DeniedOrderRepository deniedOrderRepository;
    @Autowired
    private HotelRoomPagingRepository hotelRoomPagingRepository;
    @Autowired
    private HotelRoomRepository hotelRoomRepository;
    @Autowired
    private LoginUserRepository loginUserRepository;
    @Autowired
    private NewOrderPagingRepository newOrderPagingRepository;
    @Autowired
    private NewOrderRepository newOrderRepository;
    @Autowired
    private UserOrderRepository userOrderRepository;
    @Autowired
    private UserRepository userRepository;

    @Bean
    public CostDao costDao() {
        return new DefaultCostDao(costRepository);
    }

    @Bean
    public HotelDao hotelDao() {
        return new DefaultHotelDao(hotelRoomRepository, hotelRoomPagingRepository);
    }

    @Bean
    public UserAdministratorDao userAdministratorDao() {
        return new DefaultUserAdministratorDao(costRepository, newOrderRepository, approvedOrderRepository, deniedOrderRepository, newOrderPagingRepository, userOrderRepository);
    }

    @Bean
    public HotelRoomDao hotelRoomDao() {
        return new DefaultHotelRoomDao(hotelRoomRepository, approvedOrderRepository);
    }

    @Bean
    public LoginUserDao loginUserDao() {
        return new DefaultLoginUserDao(loginUserRepository);
    }

    @Bean
    public NewOrderDao newOrderDao() {
        return new DefaultNewOrderDao(newOrderRepository);
    }

    @Bean
    public UserOrderDao userOrderDao() {
        return new DefaultUserOrderDao(userOrderRepository);
    }

    @Bean
    public UserDao userDao() {
        return new DefaultUserDao(userRepository, userOrderRepository, approvedOrderRepository, deniedOrderRepository);
    }
}