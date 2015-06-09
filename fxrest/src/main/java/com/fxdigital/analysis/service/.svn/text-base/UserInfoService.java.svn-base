package com.fxdigital.analysis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fxdigital.analysis.dao.BaseDao;
import com.fxdigital.hibernate.bean.UserInfoBean;



@Service
public class UserInfoService {

    @Autowired
    private BaseDao baseDao;

    public UserInfoBean queryUserInfoById(String id) {
        return (UserInfoBean) baseDao.load(UserInfoBean.class, id);
    }

    public void addUserInfo() {
        try {
        	UserInfoBean userInfo=new UserInfoBean();
            userInfo.setAddress("32132");
            baseDao.save(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
