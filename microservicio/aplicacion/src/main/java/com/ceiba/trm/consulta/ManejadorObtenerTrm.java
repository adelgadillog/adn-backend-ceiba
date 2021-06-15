package com.ceiba.trm.consulta;

import com.ceiba.pedido.modelo.dto.DtoPedido;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import com.ceiba.trm.modelo.DtoTrm;
import com.ceiba.trm.puerto.dao.DaoTrm;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.util.List;

@Component
public class ManejadorObtenerTrm {

    private final DaoTrm daoTrm;

    public ManejadorObtenerTrm(DaoTrm daoTrm){
        this.daoTrm = daoTrm;
    }

    public DtoTrm ejecutar() throws ServiceException, RemoteException { return this.daoTrm.obtener(); }
}
