package com.salesforce.dva.argus.service.metric.transform.plus;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.salesforce.dva.argus.entity.Metric;
import com.salesforce.dva.argus.service.TSDBService;
import com.salesforce.dva.argus.service.broker.DefaultJSONService;
import com.salesforce.dva.argus.service.metric.transform.Transform;
import com.salesforce.dva.argus.service.metric.transform.TransformFactory;
import com.salesforce.dva.argus.system.SystemConfiguration;

public class HeimdallMetricReducerTestFULL {
	private static final String TEST_SCOPE = "core.CHI.SP2.cs15";
	private static final String TEST_SCOPE_ACT = "db.oracle.CHI.SP2.cs15";
	private static final String TEST_SCOPE_CPU = "system.CHI.SP2.cs15";
	private static final String TEST_METRIC = "holder to be changed";
	private static Injector injector;
	private static SystemConfiguration configuration;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configuration=new SystemConfiguration(new Properties());
		configuration.setProperty("service.property.json.endpoint", "https://localhost:443/argusws");
		injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				new FactoryModuleBuilder().build(TransformFactory.class);
				bind(TSDBService.class).to(DefaultJSONService.class);
				bind(SystemConfiguration.class).toInstance(configuration);
			}
		});
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		injector=null;
	}

	@Test
	public void HeimdallTotalAvaTransform_APT(){
		Transform transform=injector.getInstance(HeimdallMetricReducer.class);
		int offset=1000*60;
		long start=0L;
        Metric metric_1 = new Metric(TEST_SCOPE, TEST_METRIC);
        Map<Long, String> datapoints_1 = new HashMap<Long, String>();
        datapoints_1.put(start+1L*offset, "900.0");
        datapoints_1.put(start+2L*offset, "1200.0");
        datapoints_1.put(start+3L*offset, "1000.0");
        datapoints_1.put(start+4L*offset, "1000.0");
        datapoints_1.put(start+5L*offset, "400.0");
        datapoints_1.put(start+6L*offset, "200.0");
        metric_1.setDatapoints(datapoints_1);
        metric_1.setTag("device", "na11-app1-1-chi.ops.sfdc.net");
        metric_1.setMetric("SFDC_type-Stats-name1-System-name2-trustAptRequestTimeRACNode2.Last_1_Min_Avg");
        
        Metric metric_2 = new Metric(TEST_SCOPE, TEST_METRIC);
        Map<Long, String> datapoints_2 = new HashMap<Long, String>();
        datapoints_2.put(start+1L*offset, "1400.0");
        datapoints_2.put(start+2L*offset, "1350.0");
        datapoints_2.put(start+3L*offset, "1350.0");
        datapoints_2.put(start+4L*offset, "950.0");
        datapoints_2.put(start+5L*offset, "950.0");
        datapoints_2.put(start+6L*offset, "50.0");
        metric_2.setDatapoints(datapoints_2);
        metric_2.setTag("device", "na11-app1-2-chi.ops.sfdc.net");
        metric_2.setMetric("SFDC_type-Stats-name1-System-name2-trustAptRequestTimeRACNode2.Last_1_Min_Avg");
        
        Metric metric_3 = new Metric(TEST_SCOPE, TEST_METRIC);
        Map<Long, String> datapoints_3 = new HashMap<Long, String>();
        datapoints_3.put(start+1L*offset, "2.0");
        datapoints_3.put(start+2L*offset, "3.0");
        datapoints_3.put(start+3L*offset, "3.0");
        datapoints_3.put(start+4L*offset, "2.0");
        datapoints_3.put(start+5L*offset, "4.0");
        datapoints_3.put(start+6L*offset, "2.0");
        metric_3.setDatapoints(datapoints_3);
        metric_3.setTag("device", "na11-app1-1-chi.ops.sfdc.net");
        metric_3.setMetric("SFDC_type-Stats-name1-System-name2-trustAptRequestCountRACNode2.Last_1_Min_Avg");
        
        Metric metric_4 = new Metric(TEST_SCOPE, TEST_METRIC);
        Map<Long, String> datapoints_4 = new HashMap<Long, String>();
        datapoints_4.put(start+1L*offset, "1.0");
        datapoints_4.put(start+2L*offset, "9.0");
        datapoints_4.put(start+3L*offset, "2.0");
        datapoints_4.put(start+4L*offset, "2.0");
        datapoints_4.put(start+5L*offset, "9.0");
        datapoints_4.put(start+6L*offset, "1.0");
        metric_4.setDatapoints(datapoints_4);
        metric_4.setTag("device", "na11-app1-2-chi.ops.sfdc.net");
        metric_4.setMetric("SFDC_type-Stats-name1-System-name2-trustAptRequestCountRACNode2.Last_1_Min_Avg");
        
        
        Metric metric_6 = new Metric(TEST_SCOPE, TEST_METRIC);
        Map<Long, String> datapoints_6 = new HashMap<Long, String>();
        datapoints_6.put(start+1L*offset, "0.0");
        datapoints_6.put(start+2L*offset, "0.0");
        datapoints_6.put(start+3L*offset, "0.0");
        datapoints_6.put(start+4L*offset, "0.0");
        datapoints_6.put(start+5L*offset, "0.0");
        datapoints_6.put(start+6L*offset, "0.0");
        metric_6.setDatapoints(datapoints_6);
        metric_6.setTag("device", "na11-app1-1-chi.ops.sfdc.net");
        metric_6.setMetric("SFDC_type-Stats-name1-System-name2-trustAptRequestCountRACNode1.Last_1_Min_Avg");
       
        List<Metric> metrics = new ArrayList<Metric>();
        metrics.add(metric_1);
        metrics.add(metric_2);
        metrics.add(metric_3);
        metrics.add(metric_4);
        metrics.add(metric_6);
        
        List<Metric> result = transform.transform(metrics,Arrays.asList("IMPACT"));

        System.out.println(result);
 
	}
}