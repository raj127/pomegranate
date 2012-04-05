package com.darkmi.system.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.system.Config;

/**
 * Description: 配置对象的泛型DAO.
 * Copyright (c) darkmi.com
 * All Rights Reserved.
 * @version 1.0  2012-03-17 上午08:31:05 darkmi created.
 */
@Component
public class ConfigDao extends HibernateDao<Config, Long> {
}
