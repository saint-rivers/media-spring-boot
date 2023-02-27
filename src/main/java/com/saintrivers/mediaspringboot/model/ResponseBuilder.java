package com.saintrivers.mediaspringboot.model;

public interface ResponseBuilder<R, D> {

    R mainResponse(D domain);
}
