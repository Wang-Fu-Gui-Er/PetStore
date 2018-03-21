package com.yx.service;

import com.yx.mapper.AccountMapper;
import com.yx.mapper.ProfileMapper;
import com.yx.model.Account;
import com.yx.model.AccountExample;
import com.yx.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountMapper accDao;
    @Autowired
    private ProfileMapper proDao;

    // 登录校验
    public List<Account> selectByExample(AccountExample example){
        return accDao.selectByExample(example);
    }

    // 注册用户【设置偏好，即关联】
    //参数record的值是全的，含有record.profile 的 lang和cateId属性
    public int insert(Account record){
        int t1 = accDao.insert(record);
        AccountExample example = new AccountExample();
        example.createCriteria().andUsernameEqualTo(record.getUsername());
        List<Account> accReturn = accDao.selectByExample(example);
        //Profile profile = accReturn.get(0).getProfile();
        //profile.setUserid(accReturn.get(0).getUserid());//设置profile的userId字段
        Account account = accReturn.get(0);
        Profile profile = proDao.selectByPrimaryKey(account.getUserid());
        profile.setCateId(record.getProfile().getCateId());
        profile.setLanguage(record.getProfile().getLanguage());
        profile.setAccount(account);
        account.setProfile(profile);
        int t2 = proDao.updateByPrimaryKey(profile);
        return t1*t2;
    }
}
