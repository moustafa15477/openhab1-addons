/**
 * Copyright (c) 2010-2020 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.digitalstrom.internal.client.connection;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openhab.binding.digitalstrom.internal.client.constants.JSONApiResponseKeysEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alexander Betker
 * @author Alex Maier
 * @since 1.3.0
 */
public class JSONResponseHandler {

    private static final Logger logger = LoggerFactory.getLogger(JSONResponseHandler.class);

    public JSONResponseHandler() {

    }

    public boolean checkResponse(JSONObject jsonResponse) {
        if (jsonResponse == null) {
            return false;
        } else if (jsonResponse.get(JSONApiResponseKeysEnum.RESPONSE_OK.getKey()) != null) {
            return jsonResponse.get(JSONApiResponseKeysEnum.RESPONSE_OK.getKey()).toString()
                    .equals(JSONApiResponseKeysEnum.RESPONSE_SUCCESSFUL.getKey());
        } else {
            logger.error("error in json request. Error message : "
                    + jsonResponse.get(JSONApiResponseKeysEnum.RESPONSE_MESSAGE).toString());
        }
        return false;
    }

    public JSONObject toJSONObject(String jsonResponse) {
        if (jsonResponse != null && !jsonResponse.trim().equals("")) {
            try {
                return (JSONObject) new JSONParser().parse(jsonResponse);
            } catch (ParseException e) {
                logger.error(e.getLocalizedMessage());
            }
        }
        return null;
    }

    public JSONObject getResultJSONObject(JSONObject jsonObject) {
        if (jsonObject != null) {
            return (JSONObject) jsonObject.get(JSONApiResponseKeysEnum.RESULT.getKey());
        }
        return null;
    }

}