package com.ceiba.trm.adaptador.dao;

import co.com.software.colombia.superfinanciera.action.generic.services.trm.action.TCRMServicesInterfaceProxy;
import co.com.software.colombia.superfinanciera.action.generic.services.trm.action.TcrmResponse;
import com.ceiba.trm.modelo.DtoTrm;
import com.ceiba.trm.puerto.dao.DaoTrm;
import org.springframework.stereotype.Component;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.Date;

@Component
public class DaoObtenerTrm implements DaoTrm {


    private static final String VALUE_QUERY_FORMAT = "#0.00";
    private static final String WEB_SERVICE_URL = "https://www.superfinanciera.gov.co/SuperfinancieraWebServiceTRM/TCRMServicesWebService/TCRMServicesWebService?WSDL";
    private static final String MENSAJE = "Fecha consulta: ";

    @Override
    public DtoTrm obtener() throws ServiceException, RemoteException {
        DecimalFormat decimalFormat = new DecimalFormat(VALUE_QUERY_FORMAT);
        TCRMServicesInterfaceProxy proxy = new TCRMServicesInterfaceProxy(WEB_SERVICE_URL);
        TcrmResponse tcrmResponse = proxy.queryTCRM(null);

        return new DtoTrm(decimalFormat.format(tcrmResponse.getValue()), MENSAJE + new Date());
    }
}
