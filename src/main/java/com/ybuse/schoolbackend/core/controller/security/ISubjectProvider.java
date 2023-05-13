package com.ybuse.schoolbackend.core.controller.security;


/**
 * api key 签发对象提供者接口类
 */
public interface ISubjectProvider {
    String getSubject();
}
