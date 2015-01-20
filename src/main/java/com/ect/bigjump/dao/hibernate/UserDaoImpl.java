package com.ect.bigjump.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.ect.bigjump.dao.UserDao;
import com.ect.bigjump.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User, Long> implements UserDao{

}
