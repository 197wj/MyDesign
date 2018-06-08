package com.design.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.design.model.sys.SysLog;
import com.design.model.sys.SysUser;
import com.design.service.log.LogService;
import com.design.service.user.SysUserService;



/**
  * <h1>日志注解切入类</h1>
  * @ClassName: LogAspect
  * @Description: 增加日志、回收站增加数据
 */
@Component
@Aspect  
public class LogAspect {  
    @Autowired  
    private LogService logService; //日志记录Service  
    
    @Autowired  
    private SysUserService userService; //用户Service  
      
    /**
      * <h2>日志记录，暂用方案（使用注解方式）</h2>
      * @Title: sysLogPointCut
      * @return void    返回类型
     */
    @Pointcut("@annotation(com.design.aop.SysLog)")  
    public void sysLogPointCut() { }  
    
    /** 
     * 操作服务日志(后置通知) 
     * @param joinPoint 
     * @param rtv 
     * @throws Throwable 
     */  
    @AfterReturning(value="sysLogPointCut()")  
    public void operServiceLog(JoinPoint joinPoint) throws Throwable{    
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	SysUser user = this.userService.getUserFromRequest(request);
    	if (user !=null) {
    		//判断参数  
            if(joinPoint.getArgs() == null){//没有参数  
                return;  
            }  
            Map<String, String> map = getSysLog(joinPoint);
            //获取操作类型
            String operType = map.get("operType");
            //获取操作对象
            String operObject = map.get("operObject"); 
            //获取操作路径
            String operPath = map.get("operPath"); 
            //远程主机ip地址
            String ipAddress = request.getRemoteAddr();
            //创建日志对象  
            SysLog log = new SysLog();
            log.setUserId(Integer.valueOf(user.getId().toString()));
            log.setUserName(user.getName());
            log.setOperType(operType);
            log.setOperObject(operObject);
            log.setOperPath(operPath);
            log.setIpAddress("0:0:0:0:0:0:0:1".equals(ipAddress)?"127.0.0.1":ipAddress);
            log.setAddTime(new Date());
            logService.insert(log);
		}
    }  
    
    /**
      * <h2>处理SysLog注解的参数(日志)</h2>
      * @Title: getMthodLog
      * @Description: operType:操作类型，operObject:操作对象，operPath:操作路径
      * @return Map<String,String>    返回类型
     */
    @SuppressWarnings("rawtypes")
	public static Map<String, String> getSysLog(JoinPoint joinPoint) throws Exception {  
        String targetName = joinPoint.getTarget().getClass().getName();  
        String methodName = joinPoint.getSignature().getName();  
        Object[] arguments = joinPoint.getArgs();  
  
        Class targetClass = Class.forName(targetName);  
        Method[] method = targetClass.getMethods();  
        
        Map<String, String> map = new HashMap<String, String>();
        
        for (Method m : method) {  
            if (m.getName().equals(methodName)) {  
                Class[] tmpCs = m.getParameterTypes();  
                if (tmpCs.length == arguments.length) {  
                    com.design.aop.SysLog sysLog = m.getAnnotation(com.design.aop.SysLog.class);  
                    if (sysLog != null) {  
                    	map.put("operType", sysLog.operType());
                        map.put("operObject", sysLog.operObject());
                        map.put("operPath", sysLog.operPath());
                    }  
                    break;  
                }  
            }  
        }  
        return map;  
    } 
    
}  