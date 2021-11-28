package com.practica.cajanegra;

import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SuiteDisplayName("Practica Caja Negra VV 2021")
@SelectPackages({"com.practica.cajanegra.pruebasadiciones",
        "com.practica.cajanegra.pruebasconsultas",
        "com.practica.cajanegra.pruebasborrado",
        "com.practica.cajanegra.pruebasextra"
})
public class PracticaCajaNegra
{
    
}
