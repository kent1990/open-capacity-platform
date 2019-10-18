package com.open.capacity.ribbon.predicate;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import com.open.capacity.ribbon.core.context.RibbonFilterContext;
import com.open.capacity.ribbon.core.context.RibbonFilterContextHolder;

/**
 * A default implementation of {@link DiscoveryEnabledServer} that matches the instance against the attributes
 * registered through
 *
 * @author Jakub Narloch
 * @see DiscoveryEnabledPredicate
 */
public class MetadataAwarePredicate extends DiscoveryEnabledPredicate {

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean apply(DiscoveryEnabledServer server) {

//        final RibbonFilterContext context = RibbonFilterContextHolder.getContext();
       
       
        return true ;
        

        
    }
}
