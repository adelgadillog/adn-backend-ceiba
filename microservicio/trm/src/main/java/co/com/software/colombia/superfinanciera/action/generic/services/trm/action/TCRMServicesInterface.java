package co.com.software.colombia.superfinanciera.action.generic.services.trm.action;

import javax.xml.rpc.ServiceException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Calendar;

public interface TCRMServicesInterface extends Remote
{
    TcrmResponse queryTCRM(Calendar trmQueryAssociatedDate) throws RemoteException, ServiceException;
}
