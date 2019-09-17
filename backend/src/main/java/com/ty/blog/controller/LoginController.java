package com.ty.blog.controller;

import com.ty.blog.base.BaseController;
import com.ty.blog.constant.SecurityConsts;
import com.ty.blog.pojo.ResponseData;
import com.ty.blog.pojo.User;
import com.ty.blog.service.UserService;
import com.ty.blog.shiro.jwt.JwtConfig;
import com.ty.blog.shiro.jwt.JwtRedisCache;
import com.ty.blog.shiro.jwt.JwtUtil;
import com.ty.blog.util.Md5Utils;
import com.ty.blog.util.ResponseUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName: LoginController
 * @Description: 登录功能controller
 * @author zhangtainyi
 * @date 2019/9/17 16:22
 *
 */
@RestController
public class LoginController extends BaseController {


    @Resource
    private JwtRedisCache jwtRedisCache;
    @Resource
    private JwtConfig jwtConfig;
    @Resource
    private UserService userService;

//    @GetMapping("/login")
//    public String login(){
//        //如果已经认证通过，直接跳转到首页
//        if (SecurityUtils.getSubject().isAuthenticated()) {
//            return "redirect:/index";
//        }
//        return "login";
//    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseData login(User user) throws Exception{
        log.info("开始认证...");
        String username = user.getUsername();
        String password = Md5Utils.encrypt(user.getPassword(),username);
        String realPassword = userService.findByUsername(username).getPassword();
        if (realPassword == null) {
            throw new UnknownAccountException();
        } else if (!realPassword.equals(password)) {
            throw new IncorrectCredentialsException();
        }
        String currentTimeMillis = String.valueOf(System.currentTimeMillis());
        String jwt = JwtUtil.sign(user.getUsername(), currentTimeMillis);//生成签名
        jwtRedisCache.put(SecurityConsts.REFRESH_TOKEN + username, currentTimeMillis, jwtConfig.getRefreshTokenExpireTime());//将时间戳存入缓存
        jwtRedisCache.put(SecurityConsts.IP_TOKEN + username, JwtUtil.getIpAddress(request), jwtConfig.getRefreshTokenExpireTime());//将ip存入，防止其他用户使用token侵入
        log.info("当前用户登录ip为:"+JwtUtil.getIpAddress(request));
        log.info("结束认证...");
        return ResponseUtil.success("登录成功", jwt);
    }

    @RequestMapping("/logout")
    public ResponseData logout(){
        SecurityUtils.getSubject().logout();
        return ResponseUtil.success("注销成功");
    }

    @GetMapping("/article")
    @RequiresAuthentication
    public ResponseData article(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseUtil.success("You are already logged in", null);
        } else {
            return ResponseUtil.success("You are guest", null);
        }
    }

//    @RequestMapping("/")
//    public String redirectIndex(){
//        return "redirect:/index";
//    }

    @RequestMapping("/index")
    @RequiresPermissions("user:get")
    public ResponseData index(Model model) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        model.addAttribute("user", user);
        return ResponseUtil.success("success", null);
    }
    @GetMapping("/unauthorized/{message}")
    public ResponseData forbid(@PathVariable String message) {
        return ResponseUtil.failure(403,message);
    }
}
