package com.practica.cajanegra;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SuiteDisplayName("Caja Negra - Pruebas de Adiciones")
@SelectPackages({ "com.practica.cajanegra.pruebasadiciones" })
public class PruebasAdiciones {

}