/**
 * Copyright (c) 2010-2013, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.action.conditionalaction.internal;

import javax.management.relation.InvalidRelationTypeException;

import org.openhab.core.events.EventPublisher;
import org.openhab.core.items.ItemRegistry;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Extension of the default OSGi bundle activator
 * 
 * @author Juergen Richtsfeld
 * @since 1.7.0
 */
public final class ConditionalActionActivator implements BundleActivator {

	private static Logger logger = LoggerFactory.getLogger(ConditionalActionActivator.class); 
	
	private static BundleContext context;

	public static ServiceTracker<ItemRegistry, ItemRegistry> itemRegistryTracker;
	public static ServiceTracker<EventPublisher, EventPublisher> eventPublisherTracker;
	
	/**
	 * Called whenever the OSGi framework starts our bundle
	 */
	public void start(BundleContext bc) throws Exception {
		context = bc;
		
		itemRegistryTracker = new ServiceTracker<ItemRegistry, ItemRegistry>(bc, ItemRegistry.class, null);
		itemRegistryTracker.open();

		eventPublisherTracker = new ServiceTracker<EventPublisher, EventPublisher>(bc, EventPublisher.class, null);
		eventPublisherTracker.open();

		logger.debug("ConditionalAction action has been started.");
	}

	/**
	 * Called whenever the OSGi framework stops our bundle
	 */
	public void stop(BundleContext bc) throws Exception {
		itemRegistryTracker.close();
		eventPublisherTracker.close();
		context = null;
		logger.debug("ConditionalAction action has been stopped.");
	}
	
	/**
	 * Returns the bundle context of this bundle
	 * @return the bundle context
	 */
	public static BundleContext getContext() {
		return context;
	}
}