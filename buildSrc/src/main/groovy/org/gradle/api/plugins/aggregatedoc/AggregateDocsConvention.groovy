package org.gradle.api.plugins.aggregatedoc

import groovy.lang.Closure;

/**
 * Plugin convention to configure the <code>AggregateDocsPlugin</code>
 * 
 * @author zimmer
 *
 */
class AggregateDocsConvention {

	JavadocConvention javaDocConvention = new JavadocConvention()
	
	def aggregatedocs(Closure closure) {
		closure.delegate = this
		closure()
	}
	
	def javadoc(Closure closure) {
		closure.resolveStrategy = Closure.DELEGATE_FIRST
		closure.delegate = javaDocConvention
		closure()
	}
}
