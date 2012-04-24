package com.darkmi.system.dao;

import org.springframework.stereotype.Component;
import org.springside.modules.orm.hibernate.HibernateDao;

import com.darkmi.entity.system.Company;

/**
 * 公司对象的泛型DAO类.
 * 
 * @author darkmi
 */
@Component
public class CompanyDao extends HibernateDao<Company, Long> {
}

