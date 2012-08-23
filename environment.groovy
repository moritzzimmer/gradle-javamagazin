/*
 * Environment specific configuration file for the gradle build
 */

// cross environment properties
dataSource 				= 'java:jboss/datasources/ExampleDS'

hibernate {
	dialect				= 'org.hibernate.dialect.H2Dialect'
}
 
// environment specific properties
environments {
	dev {
		bootstrap		= true
		
		hibernate {
			showSQL 	= true
			formatSQL 	= true
			hbm2ddl {
				auto 	= 'create-drop'
			}
		}
	}
	
	// test, stage etc.
	
	prod {
		bootstrap		= false
		
		hibernate {
			showSQL 	= false
			formatSQL 	= false
			hbm2ddl {
				auto 	= 'validate'
			}
		}
	}
}
