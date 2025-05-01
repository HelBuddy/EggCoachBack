package com.eggcoach.core.domain.security.resolver;

import com.eggcoach.core.domain.security.vo.CustomPrincipal;

public interface CurrentUserDataResolver {

	CustomPrincipal getCurrentUserData();
}
