//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB)
// Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source
// schema.
// Generated on: 2012.10.01 at 01:12:11 AM IST
//

package com.nvarghese.beowulf.common.scan.dto.config;

import javax.xml.bind.annotation.XmlRegistry;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.nvarghese.beowulf.common.scan.dto.config package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.nvarghese.beowulf.common.scan.dto.config
	 * 
	 */
	public ObjectFactory() {

	}

	/**
	 * Create an instance of {@link Profile }
	 * 
	 */
	public Profile createProfile() {

		return new Profile();
	}

	/**
	 * Create an instance of {@link ScanSettings }
	 * 
	 */
	public ScanSettings createScanSettings() {

		return new ScanSettings();
	}

	/**
	 * Create an instance of {@link SessionTracking }
	 * 
	 */
	public SessionTracking createSessionTracking() {

		return new SessionTracking();
	}

	/**
	 * Create an instance of {@link SessionSettings }
	 * 
	 */
	public SessionSettings createSessionSettings() {

		return new SessionSettings();
	}

	/**
	 * Create an instance of {@link UrlWhitelist }
	 * 
	 */
	public UrlWhitelist createUrlWhitelist() {

		return new UrlWhitelist();
	}

	/**
	 * Create an instance of {@link Login }
	 * 
	 */
	public Login createLogin() {

		return new Login();
	}

	/**
	 * Create an instance of {@link UrlBlacklist }
	 * 
	 */
	public UrlBlacklist createUrlBlacklist() {

		return new UrlBlacklist();
	}

	/**
	 * Create an instance of {@link ForbiddenParameterNames }
	 * 
	 */
	public ForbiddenParameterNames createForbiddenParameterNames() {

		return new ForbiddenParameterNames();
	}

	/**
	 * Create an instance of {@link KnownSessionIdlist }
	 * 
	 */
	public KnownSessionIdlist createKnownSessionIdlist() {

		return new KnownSessionIdlist();
	}

	/**
	 * Create an instance of {@link BaseUris }
	 * 
	 */
	public BaseUris createBaseUris() {

		return new BaseUris();
	}

	/**
	 * Create an instance of {@link Restrictions }
	 * 
	 */
	public Restrictions createRestrictions() {

		return new Restrictions();
	}

	/**
	 * Create an instance of {@link IrrelevantParameterNames }
	 * 
	 */
	public IrrelevantParameterNames createIrrelevantParameterNames() {

		return new IrrelevantParameterNames();
	}

	/**
	 * Create an instance of {@link Options }
	 * 
	 */
	public Options createOptions() {

		return new Options();
	}

	/**
	 * Create an instance of {@link TestModules }
	 * 
	 */
	public TestModules createTestModules() {

		return new TestModules();
	}

	/**
	 * Create an instance of {@link ImportSpideredUris }
	 * 
	 */
	public ImportSpideredUris createImportSpideredUris() {

		return new ImportSpideredUris();
	}

	/**
	 * Create an instance of {@link ResponseCodeOverrides }
	 * 
	 */
	public ResponseCodeOverrides createResponseCodeOverrides() {

		return new ResponseCodeOverrides();
	}

	/**
	 * Create an instance of {@link ForbiddenMimeTypes }
	 * 
	 */
	public ForbiddenMimeTypes createForbiddenMimeTypes() {

		return new ForbiddenMimeTypes();
	}

	/**
	 * Create an instance of {@link HttpClient }
	 * 
	 */
	public HttpClient createHttpClient() {

		return new HttpClient();
	}

	/**
	 * Create an instance of {@link ReportSettings }
	 * 
	 */
	public ReportSettings createReportSettings() {

		return new ReportSettings();
	}

	/**
	 * Create an instance of {@link TestModule }
	 * 
	 */
	public TestModule createTestModule() {

		return new TestModule();
	}

}