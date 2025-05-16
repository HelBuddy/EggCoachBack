package com.eggcoach.core.security.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.eggcoach.core.domain.security.resolver.CurrentUserDataResolver;
import com.eggcoach.core.domain.security.vo.CustomPrincipal;
import com.eggcoach.core.security.resolver.annotation.CurrentUserData;

import io.micrometer.common.lang.NonNull;

@Component
public class CurrentUserDataArgResolver implements HandlerMethodArgumentResolver, CurrentUserDataResolver {

	@Override
	public CustomPrincipal getCurrentUserData() {

		Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();

		return loggedInUser instanceof AnonymousAuthenticationToken ?
			new CustomPrincipal("ANONYMOUSE","ANONYMOUSE","ANONYMOUSE","ANONYMOUSE") :
			(CustomPrincipal)loggedInUser.getPrincipal();
	}

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(CurrentUserData.class);
	}

	@Override
	public Object resolveArgument(@NonNull MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		@NonNull NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) {

		return getCurrentUserData();
	}

}
