package org.gradle.api.plugins.aggregatedoc

import java.io.File;

import org.gradle.api.file.FileCollection
import org.gradle.api.file.FileTree
import org.gradle.api.tasks.javadoc.Javadoc
import org.gradle.api.InvalidUserDataException

/**
 * 
 * @author zimmer
 *
 */
class AggregateJavadocsTask extends Javadoc {

	@Override
	public FileTree getSource() {
		if (!hasSubprojects()) {
			return super.getSources()
		}
		def sources = project.subprojects.collect {
			it.sourceSets.main.allJava
		}
		return project.files(sources).getAsFileTree()
	}

	@Override
	public FileCollection getClasspath() {
		if (!hasSubprojects()) {
			return super.getClasspath()
		}
		return project.files(project.subprojects.collect {
			it.sourceSets.main.compileClasspath
		})
	}
	
	private boolean hasSubprojects() {
		return project.subprojects
	}
}
