package com.darkmi.system.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.system.Ftp;

/**
 * Description: FTP对象的泛型DAO
 * Copyright (c) 永新视博
 * All Rights Reserved.
 * @version 1.0  2010-12-6 上午08:29:41 laojiang created
 */
@Component
public class FtpDao extends HibernateDao<Ftp, Long> {

}
