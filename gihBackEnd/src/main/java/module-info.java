module gihBackEnd {
    requires java.persistence;
    requires jersey.media.json.jackson;
    requires jakarta.ws.rs;
    requires java.sql;
    requires mysql.connector.java;
    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.module.jaxb;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.jaxrs.base;
    requires org.glassfish.hk2.api;
    requires org.glassfish.hk2.locator;
    requires org.glassfish.hk2.utilities;
    requires grizzly.http;
    requires java.xml.bind;
    requires jakarta.xml.bind;
    requires grizzly.http.server;
    requires jersey.container.grizzly2.http;
    requires jersey.server;
    requires jersey.common;
    requires jersey.container.servlet;
    requires jersey.container.servlet.core;
    requires jersey.media.jaxb;
    requires jersey.hk2;
    requires hibernate.entitymanager;
    requires org.hibernate.commons.annotations;
    requires org.hibernate.validator;
    requires org.hibernate.orm.core;
    requires com.google.gson;

    opens ma.uiass.eia.pds.gihBackEnd.model to org.hibernate.orm.core, com.google.gson;
    opens ma.uiass.eia.pds.gihBackEnd.controller to org.glassfish.hk2.locator, jersey.server;
    opens ma.uiass.eia.pds.gihBackEnd.dto to com.fasterxml.jackson.databind, com.google.gson;

    exports ma.uiass.eia.pds.gihBackEnd.dto;
    exports ma.uiass.eia.pds.gihBackEnd.model;
    exports ma.uiass.eia.pds.gihBackEnd.util;
}