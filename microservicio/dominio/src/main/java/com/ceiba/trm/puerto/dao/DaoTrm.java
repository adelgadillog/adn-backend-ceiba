package com.ceiba.trm.puerto.dao;


import com.ceiba.trm.modelo.DtoTrm;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;

public interface DaoTrm {


    DtoTrm obtener() throws ServiceException, RemoteException;
}
