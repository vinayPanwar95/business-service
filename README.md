# business-service
This service is aimed to have business service

# In this service we have implemented
	1. liquibase
	2. micrometer
	
	Micrometer :
	-> to implement Micrometer health, we have a package metrics which contains 3 files
		1. MetricsProperties : load the properties from application.yaml file
		2. MetricsAutoConfiguration : this will set meterwhiteList and loggingRegistry 
		3. HealthMetricdExportConfiguration : this wll have gauge which will look for Application health 