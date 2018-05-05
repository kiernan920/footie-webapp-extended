package cucumber.steps

import cucumber.rest.FootieAppRestAdapter
import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

FootieAppRestAdapter restAdapter = new FootieAppRestAdapter()

World {
    new TestData()
}

When(~"^query the username and password") { ->
    lastExecutionResult = restAdapter.queryUserNameAndPasswordExist("root", "root")
}

Then(~/^the response is json output '([^']*)'$/) { String jsonOutput ->
    assert lastExecutionResult.toString() == jsonOutput
}


