package com.ceiba.trm.controlador;

import com.ceiba.trm.consulta.ManejadorObtenerTrm;
import com.ceiba.trm.modelo.DtoTrm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;

@RestController
@RequestMapping("/trm")
@Api(tags={"Controlador consulta TRM del dia"})
public class ConsultaControladorTrm {

    private final ManejadorObtenerTrm manejadorObtenerTrm;

    public ConsultaControladorTrm(ManejadorObtenerTrm manejadorObtenerTrm) {
        this.manejadorObtenerTrm = manejadorObtenerTrm;
    }

    @GetMapping
    @ApiOperation("Consultar TRM del dia")
    public DtoTrm obtener() throws ServiceException, RemoteException {
        return this.manejadorObtenerTrm.ejecutar();
    }

}
