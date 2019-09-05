package com.example.service;

import com.example.beans.ItripUser;
import com.example.dao.ItripUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * description:
 * Created by Ray on 2019-09-02
 */
@Service
public class ItripUserServiceImpl implements ItripUserService {
    @Autowired
    private ItripUserMapper itripUserMapper;
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public ItripUser getItripUserById(Long id) throws Exception {
      /*  ItripUser user=new ItripUser();
        user.setUserName("哈哈哈");
        user.setUserCode("hhahah");
        itripUserMapper.insertItripUser(user);

        int a=10/0;*/ //测试事务
        return itripUserMapper.getItripUserById(id);
    }
}
