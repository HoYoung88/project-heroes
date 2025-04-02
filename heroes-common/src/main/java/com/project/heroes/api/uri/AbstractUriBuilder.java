package com.project.heroes.api.uri;

import com.project.heroes.api.properties.OpenNexonApiProperties.PathParams;
import java.net.URI;
import org.springframework.web.util.UriComponentsBuilder;

public abstract class AbstractUriBuilder {

    protected void validateParams(PathParams pathParams, Object[] paramValues) {
        if (pathParams == null || paramValues == null || pathParams.getParams().length != paramValues.length) {
            throw new IllegalArgumentException("Parameter names and values must have the same length.");
        }
    }

    protected URI buildUri(PathParams pathParams, Object... paramValues) {
        validateParams(pathParams, paramValues);
        String[] paramNames = pathParams.getParams();
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(pathParams.getPath());

        for (int i = 0; i < paramNames.length; i++) {
            uriBuilder.queryParam(paramNames[i], paramValues[i]);
        }

        return uriBuilder.build().toUri();
    }

}
