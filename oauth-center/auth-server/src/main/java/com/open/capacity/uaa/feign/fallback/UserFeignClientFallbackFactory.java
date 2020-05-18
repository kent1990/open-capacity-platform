package com.open.capacity.uaa.feign.fallback;

import com.open.capacity.common.auth.details.LoginAppUser;
import com.open.capacity.uaa.feign.UserFeignClient;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserFeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
	@Override
	public UserFeignClient create(Throwable throwable) {
		return new UserFeignClient() {

			@Override
			public LoginAppUser findByUsername(String username) {
				log.error("通过用户名查询用户异常:{}", username, throwable);
				return null ;
			}

			@Override
			public LoginAppUser findByMobile(String mobile) {
				log.error("通过手机号查询用户异常:{}", mobile, throwable);
				return null;
			}

		};
	}
}
