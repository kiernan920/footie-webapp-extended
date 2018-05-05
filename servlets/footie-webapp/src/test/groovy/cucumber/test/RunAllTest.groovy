/*
 *  COPYRIGHT Ericsson 2012
 *
 *  The copyright to the computer program(s) herein is the property of
 *  Ericsson Inc. The programs may be used and/or copied only with written
 *  permission from Ericsson Inc. or in accordance with the terms and
 *  conditions stipulated in the agreement/contract under which the
 *  program(s) have been supplied.
 */

package cucumber.test

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(Cucumber)
@CucumberOptions(plugin = ['pretty', "html:build/cucumber-html-report"], monochrome = true,
        features = 'src/test/resources/features',
        glue = 'src/test/groovy/cucumber/steps')
class RunAllTest {
}
