package co.com.software.colombia.superfinanciera.action.generic.services.trm.action;

import javax.xml.rpc.ServiceException;
import javax.xml.rpc.Stub;
import java.rmi.RemoteException;
import java.util.Calendar;

public class TCRMServicesInterfaceProxy implements TCRMServicesInterface
{
	private String endPoint;

	private TCRMServicesInterface tCRMServicesInterface = null;

	private static final String ENDPOINT_PROPERTY = "javax.xml.rpc.service.endpoint.address";

	public TCRMServicesInterfaceProxy(String endPoint) throws ServiceException
	{
		this.endPoint = endPoint;
		initTCRMServicesInterfaceProxy();
	}

	private void initTCRMServicesInterfaceProxy() throws ServiceException
	{

			this.tCRMServicesInterface = (new TCRMServicesWebServiceLocator()).getTCRMServicesWebServicePort();
			if (this.tCRMServicesInterface != null)
			{
				if (endPoint != null)
					((Stub) this.tCRMServicesInterface)._setProperty( ENDPOINT_PROPERTY, this.endPoint);
				else
					this.endPoint = (String) ((Stub) this.tCRMServicesInterface)._getProperty(ENDPOINT_PROPERTY);
			}
	}

	public TcrmResponse queryTCRM(Calendar trmQueryAssociatedDate) throws RemoteException, ServiceException
	{
		if (this.tCRMServicesInterface == null)
			initTCRMServicesInterfaceProxy();
		return this.tCRMServicesInterface.queryTCRM(trmQueryAssociatedDate);
	}

}