package org.gradle.api.plugins.aggregatedoc;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * {@link Plugin} to generate an aggregated documentation for all subprojects. 
 * 
 * @author Moritz Zimmer
 */
class AggregateDocsPlugin implements Plugin<Project> {

	@Override
	public void apply(Project project) {
		// convention to configure this plugin
		AggregateDocsConvention convention = new AggregateDocsConvention()
		project.convention.plugins.aggregatedocs = convention
		
		// new task to generate aggregated javadoc
		AggregateJavadocsTask aggregateJavadocsTasks = project.tasks.add('javadocAll', AggregateJavadocsTask)
		aggregateJavadocsTasks.description = 'Generates Javadoc API documentation for the main source code of all subprojects.'
		aggregateJavadocsTasks.group = 'documentation'
		aggregateJavadocsTasks.project = project

		// configure the new task lazily		
		aggregateJavadocsTasks.conventionMapping.title = {convention.javaDocConvention.title}
		aggregateJavadocsTasks.conventionMapping.destinationDir = {convention.javaDocConvention.destinationDir}
		
	}
}
