package tools.sapcx.commerce.reporting.domain;

import static org.assertj.core.api.Assertions.assertThat;

import de.hybris.bootstrap.annotations.UnitTest;
import de.hybris.platform.servicelayer.interceptor.InterceptorContext;
import de.hybris.platform.servicelayer.interceptor.InterceptorException;
import de.hybris.platform.servicelayer.internal.model.ServicelayerJobModel;

import org.junit.Before;
import org.junit.Test;

import tools.sapcx.commerce.reporting.model.ReportGenerationScheduleModel;
import tools.sapcx.commerce.toolkit.testing.itemmodel.InMemoryModelFactory;
import tools.sapcx.commerce.toolkit.testing.testdoubles.core.InterceptorContextStub;
import tools.sapcx.commerce.toolkit.testing.testdoubles.search.FlexibleSearchServiceStub;

@UnitTest
public class ReportGeneratorCronJobInitDefaultInterceptorTests {
	private InterceptorContext interceptorContext;
	private ReportGenerationScheduleInitDefaultInterceptor interceptor;

	@Before
	public void setUp() throws Exception {
		interceptorContext = InterceptorContextStub.interceptorContext().stub();

		interceptor = new ReportGenerationScheduleInitDefaultInterceptor();
		interceptor.setJobCode("jobCode");
	}

	@Test
	public void ifJobIsNotFound_cronJobWillRemainWithoutAJob() throws InterceptorException {
		ReportGenerationScheduleModel cronJob = InMemoryModelFactory.createTestableItemModel(ReportGenerationScheduleModel.class);

		interceptor.setFlexibleSearchService(new FlexibleSearchServiceStub());
		interceptor.onInitDefaults(cronJob, interceptorContext);

		assertThat(cronJob.getJob()).isNull();
	}

	@Test
	public void emptyCronJobWillBeFilledWithJob() throws InterceptorException {
		ReportGenerationScheduleModel cronJob = InMemoryModelFactory.createTestableItemModel(ReportGenerationScheduleModel.class);
		ServicelayerJobModel job = InMemoryModelFactory.createTestableItemModel(ServicelayerJobModel.class);
		job.setCode("jobCode");

		interceptor.setFlexibleSearchService(new FlexibleSearchServiceStub(job));
		interceptor.onInitDefaults(cronJob, interceptorContext);

		assertThat(cronJob.getJob()).isEqualTo(job);
	}

}
