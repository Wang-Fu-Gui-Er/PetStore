package com.yx.controller;

import com.yx.model.Account;
import com.yx.model.AccountExample;
import com.yx.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = {"account"})
public class AccountController {
    @Autowired
    private AccountService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Account> login(@RequestBody Account account){
        AccountExample example = new AccountExample();
        String username = account.getUsername();
        String password = account.getPassword();
        example.createCriteria().andUsernameEqualTo(username).andPasswordEqualTo(password);
        List<Account> list = service.selectByExample(example);
        if (list.size() > 0){
            return new ResponseEntity<Account>(list.get(0),HttpStatus.OK);//200
        } else {
            return new ResponseEntity<Account>(HttpStatus.CONFLICT);//409
        }
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Void> register(@RequestBody Account account){
        Account s = account;
        //注册前查重
        AccountExample example = new AccountExample();
        String username = account.getUsername();
        example.createCriteria().andUsernameEqualTo(username);
        List<Account> list = service.selectByExample(example);
        if (list.size() == 0){  //无重名，可以注册
            if (service.insert(account) > 0){
                return new ResponseEntity<Void>(HttpStatus.CREATED);//201 注册成功
            } else {
                return new ResponseEntity<Void>(HttpStatus.OK);//200 注册失败
            }
        }else { //用户名重复，不可以注册
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);//409 重名
        }
    }
}
