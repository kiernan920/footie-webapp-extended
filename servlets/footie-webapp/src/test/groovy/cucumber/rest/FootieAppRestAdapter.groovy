/*
 *  COPYRIGHT Ericsson 2012
 *
 *  The copyright to the computer program(s) herein is the property of
 *  Ericsson Inc. The programs may be used and/or copied only with written
 *  permission from Ericsson Inc. or in accordance with the terms and
 *  conditions stipulated in the agreement/contract under which the
 *  program(s) have been supplied.
 */

package cucumber.rest

import org.apache.http.HttpStatus

import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

/**
 * User REST adapter
 * Exposes REST interface of footiedb-webapp.war
 */
class FootieAppRestAdapter extends AbstractRestAdapter {

    final static String CHECK_PASSWORD_ENDPOINT = '/footie-webapp/rest/users/check/root/root'

    /**
     * Executes a footie-webapp service query.
     *
     * @param jsonInput the query string
     * @return String response, success is JSON string, failure is <ERROR CODE> <REASON>, e.g. 404 Not Found
     */
    def queryUserNameAndPasswordExist(String username, String password) {
        Response response = RelaxedClientBuilder.newClient()
                .target(BASE_URL)
                .path(CHECK_PASSWORD_ENDPOINT)
                .request()
                .get()

        if (response.status == HttpStatus.SC_OK) {
            return response.readEntity(String.class)
        }else {
            return response.status + " " + response.statusInfo
        }
    }
}
