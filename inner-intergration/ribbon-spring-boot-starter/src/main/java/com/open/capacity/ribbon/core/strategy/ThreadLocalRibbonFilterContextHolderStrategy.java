package com.open.capacity.ribbon.core.strategy;

/*
 * Copyright 2004, 2005, 2006 Acegi Technology Pty Limited
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import org.springframework.util.Assert;

import com.open.capacity.ribbon.core.context.DefaultRibbonFilterContext;
import com.open.capacity.ribbon.core.context.RibbonFilterContext;

/**
 * A <code>ThreadLocal</code>-based implementation of
 * {@link RibbonFilterContexHolderStrategy}.
 *
 * @author Ben Alex
 *
 * @see java.lang.ThreadLocal
 * @see org.springframework.security.core.context.web.RibbonFilterContexPersistenceFilter
 */
public class ThreadLocalRibbonFilterContextHolderStrategy implements
RibbonFilterContextHolderStrategy {
	// ~ Static fields/initializers
	// =====================================================================================

	private static final ThreadLocal<RibbonFilterContext> contextHolder = new ThreadLocal<>();

	// ~ Methods
	// ========================================================================================================

	public void clearContext() {
		contextHolder.remove();
	}

	public RibbonFilterContext getContext() {
		RibbonFilterContext ctx = contextHolder.get();

		if (ctx == null) {
			ctx = createEmptyContext();
			contextHolder.set(ctx);
		}

		return ctx;
	}

	public void setContext(RibbonFilterContext context) {
		Assert.notNull(context, "Only non-null RibbonFilterContex instances are permitted");
		contextHolder.set(context);
	}

	public RibbonFilterContext createEmptyContext() {
		return new DefaultRibbonFilterContext();
	}
}
